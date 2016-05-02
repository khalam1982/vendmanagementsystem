/**
 * VendMgmtSysServlet.java
 * Purpose: Unit test class for Vend transactions
 *
 * @author ramasap1
 */
package com.centrica.unittest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.SOAPException;

import meter.enterprise.uk.co.britishgas.MeteringAssetBasic;

import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import org.apache.axis2.databinding.types.Token;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.axis2.transport.http.HttpTransportProperties.Authenticator;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;

import smartmeterprocessing.enterprise.uk.co.britishgas.CreateVendMessage;
import smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber;
import smartmeterprocessing.enterprise.uk.co.britishgas.ReverseVendMessage;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendAmount;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendCreditType_type1;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestTimestamp;
import smartmeterprocessing.enterprise.uk.co.britishgas.VoidVendRequestMessage;
import trilliantami.www.trilliantcommontypes.AdjustCredit;
import trilliantami.www.trilliantcommontypes.Credit;
import trilliantami.www.trilliantcommontypes.CreditType;
import trilliantami.www.trilliantcommontypes.Currency;
import trilliantamiservice.AMIInterfaceImplServiceStub;
import trilliantamiservice.AdjustCredit11;
import trilliantamiservice.AdjustCreditResponseE;
import ch.iec.tc57._2009.enddevicecontrols.ServiceDeliveryPoint_type0;
import ch.iec.www.tc57._2008.schema.message.HeaderType;

import com.centrica.vms.common.DeviceTypeConstants;
import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.HESLookUpFailedException;
import com.centrica.vms.model.LookUpParam;
import com.centrica.vms.model.ServiceId;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.external.service.HeadendCommonService;
import com.centrica.vms.scheduler.external.service.SAPService;
import com.centrica.vms.scheduler.model.UtilityJobDetails;
import com.centrica.vms.scheduler.service.HEMessageCIMService;
import com.centrica.vms.scheduler.service.HEMessagePhase2BService;
import com.centrica.vms.scheduler.service.IHEMessageService;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceipt;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceiptResponse;
import com.centrica.vms.ws.sap.adjustment.model.ResponseCode;
import com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDelivery;
import com.centrica.vms.ws.sap.service.CreateVend;
import com.centrica.vms.ws.sap.service.ReverseVend;
import com.centrica.vms.ws.sap.service.VoidVend;
import com.centrica.vms.ws.service.VMSServiceImpl;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;

import common.enterprise.uk.co.britishgas.BG_Amount;
import common.enterprise.uk.co.britishgas.BG_CurrencyAmount;
import common.enterprise.uk.co.britishgas.BG_Identifier;

/**
 * VendMgmtSysServlet.java
 * Purpose: Unit test for Vend transactions
 * @author ramasap1
 * 
 */
@SuppressWarnings("serial")
/**
 * Unit test methods for Vend transactions 
 * @see HttpServlet
 * @see Logger
 */
public class VendMgmtSysServlet extends HttpServlet {

	private Logger logger = ESAPI.getLogger(getClass().getName());

	private static final int TIMEZONE_GMT	= 1;
	private static final int TIMEZONE_BST	= 2;
	private static int tz = VendMgmtSysServlet.TIMEZONE_BST;
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	/*
	 * The servlet GET method
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletInputStream servletInputStream = req.getInputStream();
		logger.info(Logger.EVENT_UNSPECIFIED,"In do get method");
		System.out.println("here");
		String method;
		String array[] = new String[3];
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(servletInputStream));
			String inputLine;
			while ((inputLine = in.readLine()) != null){
				logger.info(Logger.EVENT_UNSPECIFIED,"inputLine "+inputLine);
				array = inputLine.split(";");
			}
				
			in.close();
			if(array.length==1){
				method  = array[0];
				System.out.println("Method " + method);
				if(method.equals("reverse")){
					createReverseVend();
				}else if (method.equals("reverseAndVoid")) {
					createReverseAndVoidVend();
				}else if(method.equals("void")){
					createVoidVend();
				}else if(method.equals("createElectric")){
					tz = VendMgmtSysServlet.TIMEZONE_BST;
					createVendElectric("1-99");
				}else if(method.equals("voidAck")) {
					tz = VendMgmtSysServlet.TIMEZONE_BST;
					createVoidAckElectric("1-99");
				}else if(method.equals("reverseAck")) {
					tz = VendMgmtSysServlet.TIMEZONE_BST;
					createReverseAckElectric("1-99");
				}else if(method.equals("createGMTElectric")){
					tz = VendMgmtSysServlet.TIMEZONE_GMT;
					createVendElectric("1-99");
				}else if(method.equals("createPhase3Electric")){
					createPhase3VendElectric("1-99");
				}else if(method.equals("createGas")){
					createVendGas("1-99");
					/* Drop 2 - KARTHIK starts here */
				}else if(method.equals("createPhase3Gas")){
					createPhase3VendGas("1-99");
				}else if(method.equals("adjustElectric")){
					adjustVendElectric("1-8");
				}else if(method.equals("adjustGas")){
					adjustVendGas("1-8");
				}else if(method.equals("adjustPanEmpty")){
					adjustPanEmpty("1-8");
				}else if(method.equals("adjustPanIsDigit")){
					adjustPanIsDigit("1-8");
				}else if(method.equals("adjustPanLength")){
					adjustPanLength("1-8");
				}else if(method.equals("validatePanIsDigit")){
					validatePanIsDigit("1-99");
				}else if(method.equals("validatePanLength")){
					validatePanLength("1-99");
				} else if(method.equals("validatePanFound")) {
					validatePanFound("1-99");
				}else if (method.equals("validateMSNFound")) {
					validateMSNFound("5-99");
				} else if (method.equals("validateMPxNFound")) {
					validateMPxNFound("5-99");
				}else if(method.equals("adjustPanValid")){
					adjustPanValid("1-8");
				}else if(method.equals("validatePan")){
					validatePan("1-99");
				}else if(method.equals("adjustHoldingPeriod")){
					adjustHoldingPeriod();
				}else if(method.equals("adjustSource")){
					adjustSource();
				}else if(method.equals("validate_hp")){
					validateHoldingPeriod();
				}else if(method.equals("validateSource")){
					validateSource();
				} else if (method.equals("processUnsentPhase2BMessages")) {
					processUnsentPhase2BMessages();
				} else if (method.equals("processUnsentPhase3Messages")) {
					processUnsentPhase3Messages();
					/* Drop 2 - KARTHIK ends here */
				}else if (method.equals("adjustMsnNotFound")) {
					//adjustMsnNotFound();
				}else if(method.equals("adjustMsnValid")){
					//adjustMsnValid();
				}else if(method.equals("voidADP")){
					createVoidVendADP();
				}else if(method.equals("voidADP_Gas")){
					createVoidVendADP_Gas("0-0");
				}else if(method.equals("createReversalAckElectric")){
					createReversalAckElectric("1-6");
				}else if(method.equals("validateTransactionId")){
					validateTransactionId();
				} else if (method.equals("adjustAck")) {
					generateAdjustmentAcknowledgement();
				} else if (method.equals("voidAdjustment")) {
					createVoidAdjustment();
				} else if (method.equals("resendAdjustment")) {
					resendVoidAdjustment();
				}else{
					createAcknowledgePaymentDelivery("100");
				}
				
			}else{
				logger.info(Logger.EVENT_UNSPECIFIED,"Array length is not expected");
			}
			
		}catch(IOException ex){
			logger.error(Logger.EVENT_FAILURE,"IO Exception : " + ex.getMessage());
		}catch(InterruptedException ex){
			logger.error(Logger.EVENT_FAILURE,"Interrupted Exception : " + ex.getMessage());
		}catch (Throwable t){
			logger.error(Logger.EVENT_FAILURE,"Caught throwable in VendMgmtSysServlet"+t.getMessage());
		}
	}

	/**
	 * Test the functionality of Re-sending the SOAP body to HES Phase 3 
	 */
	private void resendVoidAdjustment() {
		UtilityJobDetails utilityJobDetails = new UtilityJobDetails();
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		Integer count = vmsTransactionDAO.getResendTransactionCount(DeviceTypeEnum.CIM.getDeviceType());
		utilityJobDetails.setCount(count.toString());
		utilityJobDetails.setUserName("unittest");
		try  {
			VendServiceDetails vendServiceDetails = (VendServiceDetails) new VMSUtils().getVendServiceDetails(
				DeviceTypeConstants.DEVICE_TYPE_CIM_PHASE_3);
			IHEMessageService vmsUtilityService = new HEMessageCIMService(new URL(vendServiceDetails.getUrl()));
			vmsUtilityService.processUnsentMessages(utilityJobDetails);
			vmsUtilityService = null;
		} catch (DBException dbx) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception " + dbx.getMessage());
		} catch (NamingException nx) {
			logger.error(Logger.EVENT_FAILURE,"Naming Exception " + nx.getMessage());
		} catch (MalformedURLException mex) {
			logger.error(Logger.EVENT_FAILURE,"Malformed URL Exception " + mex.getMessage());
		} catch (ADBException e) {
			logger.error(Logger.EVENT_FAILURE,"ADB Exception " + e.getMessage());
		} catch (RemoteException e) {
			logger.error(Logger.EVENT_FAILURE,"Remote Exception " + e.getMessage());
		} catch (LoginFault e) {
			logger.error(Logger.EVENT_FAILURE,"LoginFault Exception " + e.getMessage());
		} catch (AccessDeniedFault e) {
			logger.error(Logger.EVENT_FAILURE,"AccessDeniedFault Exception " + e.getMessage());
		} catch (UnexpectedErrorFault e) {
			logger.error(Logger.EVENT_FAILURE,"UnexpectedErrorFault Exception " + e.getMessage());
		} catch (SOAPException e) {
			logger.error(Logger.EVENT_FAILURE,"SOAP Exception " + e.getMessage());
		} catch (IOException e) {
			logger.error(Logger.EVENT_FAILURE,"IO Exception " + e.getMessage());
		} catch (HESLookUpFailedException e) {
			logger.error(Logger.EVENT_FAILURE,"HESLookUpFailedException " + e.getMessage());
		} 
		
	}

	private void createVoidAdjustment() {
		VendServiceDetails vendServiceDetails = null;
		AMIInterfaceImplServiceStub amiServiceStub = null;
		try {
			vendServiceDetails = (VendServiceDetails) new VMSUtils().
			getVendServiceDetails(DeviceTypeConstants.DEVICE_TYPE_CIM_PHASE_3);
			
			vendServiceDetails = new SAPService().lookUp(LookUpParam.MPXN, "1234567", ServiceId.CREDIT_ADJUSTMENT, vendServiceDetails, "ISU_200000001");
			
			String url=vendServiceDetails.getUrl();
			logger.info(Logger.EVENT_UNSPECIFIED,"Headend URL "+url);
			HttpTransportProperties.Authenticator basicAuthentication = new HttpTransportProperties.Authenticator();
			basicAuthentication.setUsername(vendServiceDetails.getUserName());
			basicAuthentication.setPassword(vendServiceDetails.getPassword());
			List<String> authSchemes = new ArrayList<String>();
			authSchemes.add(Authenticator.BASIC);
			basicAuthentication.setAuthSchemes(authSchemes);
			amiServiceStub = new AMIInterfaceImplServiceStub(url);
			amiServiceStub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, basicAuthentication);
			amiServiceStub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, "false");
			HeadendCommonService headendCommonService = new HeadendCommonService();
			AdjustCredit11 adjustCredit14 = prepareApplyAdjustCreditRequest();
			AdjustCreditResponseE adjustCreditResponse;
			adjustCreditResponse = amiServiceStub.adjustCredit(adjustCredit14);
			int status = headendCommonService.processApplyAdjustCreditResponse(adjustCreditResponse,adjustCredit14);
			System.out.println("Status of the web service request : " + status);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"NamingException::" + e.getMessage());
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"AxisFault::" + e.getMessage());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"RemoteException::" + e.getMessage());
		} catch (HESLookUpFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(Logger.EVENT_FAILURE,"HESLookUpFailedException::" + e.getMessage());
		}
	}

	/**
	 * @return
	 */
	public AdjustCredit11 prepareApplyAdjustCreditRequest() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareApplyAdjustCreditRequest method");
		AdjustCredit11 adjustCredit14 = new AdjustCredit11();
		
		HeaderType headerType = adjustCredit14.getAdjustCredit().getArg0().getHeader();
		prepareApplyAdjustCreditHeader(headerType);
		adjustCredit14.getAdjustCredit().getArg0().setHeader(headerType);
		
		AdjustCredit adjustCredit = new AdjustCredit();
		ServiceDeliveryPoint_type0 deliveryPointType = new ServiceDeliveryPoint_type0();
		deliveryPointType.setMRID("1234567"); // set MPXN here
		adjustCredit.setServiceDeliveryPoint(deliveryPointType);
		
		Credit credit = new Credit();
		prepareApplyAdjustCreditValues(credit);
		adjustCredit.setCredit(credit);
		
		adjustCredit14.getAdjustCredit().getArg0().getPayload().addAdjustCredit(adjustCredit);
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareApplyAdjustCreditRequest method");
		return adjustCredit14;
	}
	
	/**
	 * @param jobDetails
	 * @param credit
	 */
	private void prepareApplyAdjustCreditValues(Credit credit) {
		credit.setAmount(Long.parseLong("300")); // set the value here
		credit.setCurrency(Currency.GBP);
		credit.setType(CreditType.credit);
		Calendar c = Calendar.getInstance();
		c.setTime(Calendar.getInstance().getTime());
		credit.setStartTime(c);
	}

	/**
	 * @param jobDetails
	 * @param headerType
	 */
	private void prepareApplyAdjustCreditHeader(HeaderType headerType) {
		headerType.setNoun("AdjustCredit");
		headerType.setVerb("create");
		headerType.setRevision("1.0");
		Calendar c = Calendar.getInstance();
		c.setTime(Calendar.getInstance().getTime());
		headerType.setTimestamp(c);
		headerType.setSource("VMS");
		headerType.setMessageID("ISU_200000001"); // set the transaction ID
	}


	/**
	 * Test method to generate the adjustment response from PI
	 */
	private void generateAdjustmentAcknowledgement() {
		AcknowledgePaymentDelivery acknowledgePaymentDelivery0 = new AcknowledgePaymentDelivery();
		com.centrica.vms.ws.sap.adjustment.model.DeliveryReceipt param = 
			new com.centrica.vms.ws.sap.adjustment.model.DeliveryReceipt();
		param.setTransactionID("ISU_2000000001");
		ResponseCode responseCode = new ResponseCode();
		responseCode.setResponseCode(new Integer(100));
		param.setDeliveryStatus(responseCode);
		acknowledgePaymentDelivery0.setDeliveryReceipt(param);
		new VMSServiceImpl().acknowledgeAdjustResponse(acknowledgePaymentDelivery0);
	}

	/**
	 * Method to create the reverse vend request
	 * @param 
	 * @return
	 * @throws RemoteException
	 */
	private void createReverseVend()
			throws RemoteException {
		ReverseVend reverseVend46 = new ReverseVend();
		String originalTxnID = createVendElectric("1-99");
		ReverseVendMessage reverseVendMessage = new ReverseVendMessage();
		setVendRequestKeyReversal(reverseVendMessage);
		setVendOrgRequestKeyReversal(reverseVendMessage,originalTxnID);
		setVendRequestTimestampReversal(reverseVendMessage);
		reverseVend46.setReverseTxnRequest(reverseVendMessage);
		new VMSServiceImpl().reversePayment(reverseVend46);
	
	}
	
	/**
	 * Method to create the reverse vend request and then void the reversal
	 * @param
	 * @return
	 * @throws RemoteException
	 */
	private void createReverseAndVoidVend()
			throws RemoteException {
		String pan= "9826170633605561530";
		String msn="MSN201697";
		ReverseVend reverseVend46 = new ReverseVend();
		String originalTxnID = createVendElectric("1-99");
		ReverseVendMessage reverseVendMessage = new ReverseVendMessage();
		setVendRequestKeyReversal(reverseVendMessage);
		setVendOrgRequestKeyReversal(reverseVendMessage,originalTxnID);
		setVendRequestTimestampReversal(reverseVendMessage);
		reverseVend46.setReverseTxnRequest(reverseVendMessage);
		new VMSServiceImpl().reversePayment(reverseVend46);
		VoidVend voidVend = new VoidVend();
		originalTxnID = reverseVend46.getReverseTxnRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
		VoidVendRequestMessage voidVendRequestMessage = new VoidVendRequestMessage();
		setVoidVendRequestMsn(msn, voidVendRequestMessage);
		setVoidVendRequestPan(pan, voidVendRequestMessage);
		setVendRequestKeyVoid(voidVendRequestMessage);
		setVendOrgRequestKeyVoid(voidVendRequestMessage,originalTxnID);
		setVendRequestTimestampVoid(voidVendRequestMessage);
		voidVend.setVoidTxnRequest(voidVendRequestMessage);
		new VMSServiceImpl().voidPayment(voidVend);
	
	}
	
	/**
	 * Method to create the void vend
	 * @param
	 * @return
	 * @throws RemoteException
	 */
	private void createVoidVend()
			throws RemoteException {
		VoidVend voidVend = new VoidVend();
		String pan= "9000000000000000033";
		String msn="MSN201697";
		String originalTxnID = createVendElectric("1-99");
		VoidVendRequestMessage voidVendRequestMessage = new VoidVendRequestMessage();
		setVendRequestKeyVoid(voidVendRequestMessage);
		setVendOrgRequestKeyVoid(voidVendRequestMessage,originalTxnID);
		setVendRequestTimestampVoid(voidVendRequestMessage);
		setVoidVendRequestMsn(msn, voidVendRequestMessage);
		setVoidVendRequestPan(pan, voidVendRequestMessage);
		voidVend.setVoidTxnRequest(voidVendRequestMessage);
		new VMSServiceImpl().voidPayment(voidVend);
	
	}

	/**
	 * Method to set request pan in the void request
	 * @param String
	 * @param VoidVendRequestMessage
	 * @return
	 */
	private void setVoidVendRequestPan(String pan,
			VoidVendRequestMessage voidVendRequestMessage) {
		PanNumber panNumber = new PanNumber();
		panNumber.setPanNumber(pan);
		voidVendRequestMessage.setVendRequestPAN(panNumber);
	}

	/**
	 * Method to set request msn in the void request
	 * @param String
	 * @param voidVendRequestMessage
	 * @return 
	 */
	private void setVoidVendRequestMsn(String msn,
			VoidVendRequestMessage voidVendRequestMessage) {
		MeteringAssetBasic meteringAssetBasic = new MeteringAssetBasic();
		meteringAssetBasic.setManufacturerSerialNumber(msn);
		voidVendRequestMessage.setVendRequestMSN(meteringAssetBasic);
	}
	
	/**
	 * Method to create the void vend after holding period
	 * @param
	 * @return
	 * @throws RemoteException
	 */
	private void createVoidVendADP()
			throws RemoteException,InterruptedException {
		VoidVend voidVend = new VoidVend();
		String pan= "9000000000000000033";
		String msn="MSN201697";
		String originalTxnID = createVendElectric("1-99");
		Thread.sleep(80000);
		VoidVendRequestMessage voidVendRequestMessage = new VoidVendRequestMessage();
		setVendRequestKeyVoid(voidVendRequestMessage);
		setVendOrgRequestKeyVoid(voidVendRequestMessage,originalTxnID);
		setVendRequestTimestampVoid(voidVendRequestMessage);
		setVoidVendRequestMsn(msn, voidVendRequestMessage);
		setVoidVendRequestPan(pan, voidVendRequestMessage);
		voidVend.setVoidTxnRequest(voidVendRequestMessage);
		new VMSServiceImpl().voidPayment(voidVend);
	
	}
	
	/**
	 * Method to create the void vend gas after holding period
	 * @param
	 * @return
	 * @throws RemoteException
	 */
	private void createVoidVendADP_Gas(String source)
			throws RemoteException,InterruptedException {
		VoidVend voidVend = new VoidVend();
		String pan= "9000000000000000003";
		String msn="MSN20000000127";
		String originalTxnID = createVendGas(source);
		Thread.sleep(80000);
		VoidVendRequestMessage voidVendRequestMessage = new VoidVendRequestMessage();
		setVendRequestKeyVoid(voidVendRequestMessage);
		setVendOrgRequestKeyVoid(voidVendRequestMessage,originalTxnID);
		setVendRequestTimestampVoid(voidVendRequestMessage);
		setVoidVendRequestMsn(msn, voidVendRequestMessage);
		setVoidVendRequestPan(pan, voidVendRequestMessage);
		voidVend.setVoidTxnRequest(voidVendRequestMessage);
		new VMSServiceImpl().voidPayment(voidVend);
	
	}

	@Override
	/*
	 * The servlet POST method
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return 
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}


	/**
	 * Method to prepare payment request
	 * @param String
	 * @param String
	 * @param VendCreditType_type1
	 * @return CreateVend
	 */
	/* Drop 2 - KARTHIK starts here */
	private CreateVend preparePaymentRequest(String pan,String msn, VendCreditType_type1 type, String source) {
		/* Drop 2 - KARTHIK ends here */
		logger.debug(Logger.EVENT_SUCCESS,"Entering preparePaymentRequest method");
		CreateVend paymentRequest = new CreateVend();
		/* Drop 2 - KARTHIK starts here */
		CreateVendMessage createVendMessage = createVendMessage(pan,msn,type,source);
		/* Drop 2 - KARTHIK ends here */
		paymentRequest.setPaymentRequest(createVendMessage);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving preparePaymentRequest method");
		return paymentRequest;
	}

	
	/**
	 * Method to create the vend message
	 * @param String
	 * @param String
	 * @param VendCreditType_type1
	 * @return CreateVendMessage
	 */
	/* Drop 2 - KARTHIK starts here */
	private CreateVendMessage createVendMessage(String pan,String msn,VendCreditType_type1 type, String source) {
		/* Drop 2 - KARTHIK ends here */
		logger.debug(Logger.EVENT_SUCCESS,"Entering createVendMessage method");
		CreateVendMessage createVendMessage = new CreateVendMessage();
		/* Drop 2 - KARTHIK starts here */
		setVendRequestAdditional(createVendMessage, type, source);
		/* Drop 2 - KARTHIK ends here */
		MeteringAssetBasic meteringAssetBasic = new MeteringAssetBasic();
		meteringAssetBasic.setManufacturerSerialNumber(msn);
		createVendMessage.setVendMSN(meteringAssetBasic);
		PanNumber panNumber = new PanNumber();
		panNumber.setPanNumber(pan);
		createVendMessage.setPanNumber(panNumber);
		setVendAmount(createVendMessage);
		setVendRequestKey(createVendMessage);
		VendRequestTimestamp vendRequestTimestamp = new VendRequestTimestamp();
		setVendRequestTimestamp(createVendMessage, vendRequestTimestamp);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createVendMessage method");
		return createVendMessage;
	}

	/**
	 * Method to set the vend request timestamp
	 * @param createVendMessage
	 * @param vendRequestTimestamp
	 * @return 
	 */
	private void setVendRequestTimestamp(CreateVendMessage createVendMessage,
			VendRequestTimestamp vendRequestTimestamp) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  setVendRequestTimestamp method");
		Calendar timeStamp = Calendar.getInstance();
		timeStamp.setTime(new Date());
		//TODO: to be removed. tested for GMT.
/*		if (tz == VendMgmtSysServlet.TIMEZONE_GMT) {
			timeStamp.set(Calendar.MONTH, Calendar.NOVEMBER);
			int hour = timeStamp.get(Calendar.HOUR);
			hour = hour + 1;
			timeStamp.set(Calendar.HOUR, hour);
		}*/
		// TBD: Commented out for local testing. should revert later
		/*try {
			new VMSUtils().adjustTimeToBST(timeStamp);
		} catch (java.io.IOException ex) {
			logger.error(Logger.EVENT_FAILURE,"IO Exception caught");
		}*/
		vendRequestTimestamp.setVendRequestDateTime(timeStamp);
		createVendMessage.setVendRequestTimestamp(vendRequestTimestamp);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendRequestTimestamp method");
	}

	/**
	 * Method to set the vend request key
	 * @param createVendMessage
	 * @return
	 */
	private void setVendRequestKey(CreateVendMessage createVendMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendRequestKey method");
		VendRequestKey vendRequestKey = new VendRequestKey();
		StringBuffer transactionID = new StringBuffer("ISU_200");
		transactionID.append(generateTransactionID());
		BG_Identifier identifier = new BG_Identifier();
		Token token = new Token();
		token.setValue(transactionID.toString());
		identifier.setBG_IdentifierContent(token);
		vendRequestKey.setVendRequestIdentifier(identifier);
		createVendMessage.setVendRequestKey(vendRequestKey);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendRequestKey method");
	}
	/**
	 * Method to set the vend request key
	 * @param createVendMessage
	 * @return
	 */
	private void setInvalidVendRequestKey(CreateVendMessage createVendMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendRequestKey method");
		VendRequestKey vendRequestKey = new VendRequestKey();
		StringBuffer transactionID = new StringBuffer("ISU_200");
		transactionID.append(generateTransactionID() + "123451234512345");
		BG_Identifier identifier = new BG_Identifier();
		Token token = new Token();
		token.setValue(transactionID.toString());
		identifier.setBG_IdentifierContent(token);
		vendRequestKey.setVendRequestIdentifier(identifier);
		createVendMessage.setVendRequestKey(vendRequestKey);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendRequestKey method");
	}

	/**
	 * Method to set the vend amount
	 * @param createVendMessage
	 * @return
	 */
	private void setVendAmount(CreateVendMessage createVendMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendAmount method");
		VendAmount vendAmount = new VendAmount();
		BG_CurrencyAmount currencyAmount = new BG_CurrencyAmount();
		BG_Amount amount = new BG_Amount();
		int creditValue = 1000;
		amount.setBG_Amount(new BigDecimal(creditValue));
		currencyAmount.setAmount(amount);
		vendAmount.setVendAmount(currencyAmount);
		currencyAmount.setCurrencyType("POUND");
		createVendMessage.setVendAmount(vendAmount);

		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendAmount method");
	}

	/**
	 * Method to set the vend request additional info
	 * @param createVendMessage
	 * @param VendCreditType_type1
	 * @return
	 */
	private void setVendRequestAdditional(CreateVendMessage createVendMessage, VendCreditType_type1 type, String source) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendRequestAdditional method");
		VendRequestAdditional vendRequestAdditional = new VendRequestAdditional();
		vendRequestAdditional.setVendCreditType(type);
		/* Drop 2 - KARTHIK starts here */
		if (type == VendCreditType_type1.PURCHASE) {
			vendRequestAdditional.setVendHoldingPeriod("-1");
			vendRequestAdditional.setVendSource(source);
		} else if (type == VendCreditType_type1.ADJUSTMENT) {
			vendRequestAdditional.setVendHoldingPeriod("0");
			vendRequestAdditional.setVendSource("5-99");
		}
		/* Drop 2 - KARTHIK ends here */
		createVendMessage.setVendAdditional(vendRequestAdditional);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendRequestAdditional method");
	}
	
	/* Drop 2 - KARTHIK starts here */
	/**
	 * Method to set the vend request additional info
	 * @param createVendMessage
	 * @param VendCreditType_type1
	 * @return
	 */
	private void validateVendRequestHoldingPeriod(CreateVendMessage createVendMessage, VendCreditType_type1 type) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering validateVendRequestHoldingPeriod method");
		VendRequestAdditional vendRequestAdditional = new VendRequestAdditional();
		vendRequestAdditional.setVendCreditType(type);
		vendRequestAdditional.setVendHoldingPeriod("-1");
		vendRequestAdditional.setVendSource("5-99");
		createVendMessage.setVendAdditional(vendRequestAdditional);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving validateVendRequestHoldingPeriod method");
	}
	
	/**
	 * Method to set the vend request additional info
	 * @param createVendMessage
	 * @param VendCreditType_type1
	 * @return 
	 */
	private void validateVendRequestSource(CreateVendMessage createVendMessage, VendCreditType_type1 type) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering validateVendRequestSource method");
		VendRequestAdditional vendRequestAdditional = new VendRequestAdditional();
		vendRequestAdditional.setVendCreditType(type);
		vendRequestAdditional.setVendHoldingPeriod("0");
		vendRequestAdditional.setVendSource("");
		createVendMessage.setVendAdditional(vendRequestAdditional);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving validateVendRequestSource method");
	}
	/* Drop 2 - KARTHIK ends here */
	
	/**
	 * Method to set the vend original request key for reversal
	 * @param ReverseVendMessage
	 * @param String
	 * @return 
	 */
	private void setVendOrgRequestKeyReversal(
			ReverseVendMessage reverseVendMessage,String originalTxnID) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendOrgRequestKeyReversal method");
		VendRequestKey vendRequestKey = new VendRequestKey();
		BG_Identifier identifier = new BG_Identifier();
		Token token = new Token();
		token.setValue(originalTxnID);
		identifier.setBG_IdentifierContent(token);
		vendRequestKey.setVendRequestIdentifier(identifier);
		reverseVendMessage.setOriginalVendRequestKey(vendRequestKey);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendOrgRequestKeyReversal method");
	}
	
	/**
	 * Method to set the vend original request key for void
	 * @param VoidVendRequestMessage
	 * @param String
	 * @return
	 */
	private void setVendOrgRequestKeyVoid(
			VoidVendRequestMessage voidVendRequestMessage,String originalTxnID) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendOrgRequestKeyReversal method");
		VendRequestKey vendRequestKey = new VendRequestKey();
		BG_Identifier identifier = new BG_Identifier();
		Token token = new Token();
		token.setValue(originalTxnID);
		identifier.setBG_IdentifierContent(token);
		vendRequestKey.setVendRequestIdentifier(identifier);
		voidVendRequestMessage.setOriginalVendRequestKey(vendRequestKey);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendOrgRequestKeyReversal method");
	}

	/**
	 * Method to set the vend request key for reversal
	 * @param ReverseVendMessage
	 * @return
	 */
	private void setVendRequestKeyReversal(ReverseVendMessage reverseVendMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendRequestKeyReversal method");
		VendRequestKey vendRequestKey = new VendRequestKey();
		StringBuffer transactionID = new StringBuffer("ISU_200");
		transactionID.append(generateTransactionID());
		BG_Identifier identifier = new BG_Identifier();
		Token token = new Token();
		token.setValue(transactionID.toString());
		identifier.setBG_IdentifierContent(token);
		vendRequestKey.setVendRequestIdentifier(identifier);
		reverseVendMessage.setVendRequestKey(vendRequestKey);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendRequestKeyReversal method");
	}
	
	/**
	 * Method to set the vend request key for void
	 * @param voidVendRequestMessage
	 * @return
	 */
	private void setVendRequestKeyVoid(VoidVendRequestMessage voidVendRequestMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendRequestKeyVoid method");
		VendRequestKey vendRequestKey = new VendRequestKey();
		StringBuffer transactionID = new StringBuffer("ISU_200");
		transactionID.append(generateTransactionID());
		BG_Identifier identifier = new BG_Identifier();
		Token token = new Token();
		token.setValue(transactionID.toString());
		identifier.setBG_IdentifierContent(token);
		vendRequestKey.setVendRequestIdentifier(identifier);
		voidVendRequestMessage.setVendRequestKey(vendRequestKey);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendRequestKeyVoid method");
	}

	/**
	 * Method to set the vend request timestamp for reversal
	 * @param ReverseVendMessage
	 * @return
	 */
	private void setVendRequestTimestampReversal(
			ReverseVendMessage reverseVendMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  setVendRequestTimestampReversal method");
		VendRequestTimestamp vendRequestTimestamp = new VendRequestTimestamp();
		Calendar timeStamp = Calendar.getInstance();
		timeStamp.setTime(new Date());
		vendRequestTimestamp.setVendRequestDateTime(timeStamp);
		reverseVendMessage.setVendRequestTimeStamp(vendRequestTimestamp);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendRequestTimestampReversal method");
	}
	
	/**
	 * Method to set the vend request timestamp for void
	 * @param VoidVendRequestMessage
	 * @return
	 */
	private void setVendRequestTimestampVoid(
			VoidVendRequestMessage voidVendRequestMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  setVendRequestTimestampVoid method");
		VendRequestTimestamp vendRequestTimestamp = new VendRequestTimestamp();
		Calendar timeStamp = Calendar.getInstance();
		timeStamp.setTime(new Date());
		vendRequestTimestamp.setVendRequestDateTime(timeStamp);
		voidVendRequestMessage.setVendRequestTimeStamp(vendRequestTimestamp);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendRequestTimestampVoid method");
	}
	
	/**
	 * Method to create the electric vend
	 * @param  
	 * @return String
	 */
	private String createVendElectric(String source){
		String msn="MSN201697";
		String pan = "9000000000000000033";
		/* Drop 2 - KARTHIK starts here */
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.PURCHASE, source);
		/* Drop 2 - KARTHIK ends here */
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/** Method to simulate the ACK received for voided vend.
	 * To test this make sure the head end is down.
	 * @param source
	 * @return
	 */
	private String createVoidAckElectric(String source) {
		VoidVend voidVend = new VoidVend();
		String msn="";
		String pan = "9000000000000000003";
		String originalTxnID = createVendElectric(source);
		try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"InterruptedException::" + e.getMessage());
		}
		VoidVendRequestMessage voidVendRequestMessage = new VoidVendRequestMessage();
		setVendRequestKeyVoid(voidVendRequestMessage);
		setVendOrgRequestKeyVoid(voidVendRequestMessage,originalTxnID);
		setVendRequestTimestampVoid(voidVendRequestMessage);
		setVoidVendRequestMsn(msn, voidVendRequestMessage);
		setVoidVendRequestPan(pan, voidVendRequestMessage);
		voidVend.setVoidTxnRequest(voidVendRequestMessage);
		new VMSServiceImpl().voidPayment(voidVend);
		try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"InterruptedException::" + e.getMessage());
		}
		createAcknowledgePaymentDelivery(originalTxnID);
		return null;
	}

	/** Method to simulate the ACK received for reversed vend.
	 * To test this make sure the head end is down.
	 * @param source
	 * @return
	 */
	private String createReversalAckElectric(String source) {
		ReverseVend reverseVend = new ReverseVend();
		String originalTxnID = createVendElectric(source);
		try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"InterruptedException::" + e.getMessage());
		}
		ReverseVendMessage reverseVendMessage = new ReverseVendMessage();
		setVendRequestKeyReversal(reverseVendMessage);
		setVendOrgRequestKeyReversal(reverseVendMessage,originalTxnID);
		setVendRequestTimestampReversal(reverseVendMessage);
		reverseVend.setReverseTxnRequest(reverseVendMessage);
		new VMSServiceImpl().reversePayment(reverseVend);
		try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"InterruptedException::" + e.getMessage());
		}
		createAcknowledgePaymentDelivery(originalTxnID);
		return null;
	}
	
	
	/** Method to simulate the ACK received for reversed vend
	 * To test this make sure the head end is down.
	 * @param source
	 * @return
	 */
	private String createReverseAckElectric(String source) {
		String originalTxnID = createVendElectric(source);
		try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"InterruptedException::" + e.getMessage());
		}
		ReverseVend reverseVend46 = new ReverseVend();
		ReverseVendMessage reverseVendMessage = new ReverseVendMessage();
		setVendRequestKeyReversal(reverseVendMessage);
		setVendOrgRequestKeyReversal(reverseVendMessage,originalTxnID);
		setVendRequestTimestampReversal(reverseVendMessage);
		reverseVend46.setReverseTxnRequest(reverseVendMessage);
		new VMSServiceImpl().reversePayment(reverseVend46);
		return null;
	}
	
	
	
	/* Drop 2 - KARTHIK starts here */
	/**
	 * Method to adjust the electric vend 
	 * @param 
	 * @return String
	 */
	private String adjustVendElectric(String source){
		String msn="MSN201697";
		String pan = "9000000000000000033";
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.ADJUSTMENT, source);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to create the electric vend
	 * @param  
	 * @return String
	 */
	private String createPhase3VendElectric(String source){
		String msn="MSN201697";
		String pan = "9726170633605597864";
		/* Drop 2 - KARTHIK starts here */
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.PURCHASE,source);
		/* Drop 2 - KARTHIK ends here */
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/* Drop 2 - KARTHIK starts here */
	/**
	 * Method to validate holding period for adjustment request 
	 * @param 
	 * @return String
	 */
	private String adjustHoldingPeriod(){
		String pan="9826170633605552380";
		String msn = "MSN201697";
		VendCreditType_type1 type = VendCreditType_type1.ADJUSTMENT;
		
		CreateVend createVend42 = new CreateVend();
		CreateVendMessage createVendMessage = new CreateVendMessage();
		validateVendRequestHoldingPeriod(createVendMessage, type);
		MeteringAssetBasic meteringAssetBasic = new MeteringAssetBasic();
		meteringAssetBasic.setManufacturerSerialNumber(msn);
		createVendMessage.setVendMSN(meteringAssetBasic);
		PanNumber panNumber = new PanNumber();
		panNumber.setPanNumber(pan);
		createVendMessage.setPanNumber(panNumber);
		setVendAmount(createVendMessage);
		setVendRequestKey(createVendMessage);
		VendRequestTimestamp vendRequestTimestamp = new VendRequestTimestamp();
		setVendRequestTimestamp(createVendMessage, vendRequestTimestamp);
		createVend42.setPaymentRequest(createVendMessage);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to validate holding period for purchase request 
	 * @param 
	 * @return String
	 */
	private String validateHoldingPeriod(){
		String pan="9826170633605552380";
		String msn = "MSN201697";
		VendCreditType_type1 type = VendCreditType_type1.PURCHASE;
		
		CreateVend createVend42 = new CreateVend();
		CreateVendMessage createVendMessage = new CreateVendMessage();
		validateVendRequestHoldingPeriod(createVendMessage, type);
		MeteringAssetBasic meteringAssetBasic = new MeteringAssetBasic();
		meteringAssetBasic.setManufacturerSerialNumber(msn);
		createVendMessage.setVendMSN(meteringAssetBasic);
		PanNumber panNumber = new PanNumber();
		panNumber.setPanNumber(pan);
		createVendMessage.setPanNumber(panNumber);
		setVendAmount(createVendMessage);
		setVendRequestKey(createVendMessage);
		VendRequestTimestamp vendRequestTimestamp = new VendRequestTimestamp();
		setVendRequestTimestamp(createVendMessage, vendRequestTimestamp);
		createVend42.setPaymentRequest(createVendMessage);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to validate source for adjustment request 
	 * @param
	 * @return String
	 */
	private String adjustSource(){
		String pan="9826170633605552380";
		String msn = "MSN201697";
		VendCreditType_type1 type = VendCreditType_type1.ADJUSTMENT;
		
		CreateVend createVend42 = new CreateVend();
		CreateVendMessage createVendMessage = new CreateVendMessage();
		validateVendRequestSource(createVendMessage, type);
		MeteringAssetBasic meteringAssetBasic = new MeteringAssetBasic();
		meteringAssetBasic.setManufacturerSerialNumber(msn);
		createVendMessage.setVendMSN(meteringAssetBasic);
		PanNumber panNumber = new PanNumber();
		panNumber.setPanNumber(pan);
		createVendMessage.setPanNumber(panNumber);
		setVendAmount(createVendMessage);
		setVendRequestKey(createVendMessage);
		VendRequestTimestamp vendRequestTimestamp = new VendRequestTimestamp();
		setVendRequestTimestamp(createVendMessage, vendRequestTimestamp);
		createVend42.setPaymentRequest(createVendMessage);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to validate source for purchase request 
	 * @param
	 * @return String
	 */
	private String validateSource(){
		String pan="9826170633605552380";
		String msn = "MSN201697";
		VendCreditType_type1 type = VendCreditType_type1.PURCHASE;
		
		CreateVend createVend42 = new CreateVend();
		CreateVendMessage createVendMessage = new CreateVendMessage();
		validateVendRequestSource(createVendMessage, type);
		MeteringAssetBasic meteringAssetBasic = new MeteringAssetBasic();
		meteringAssetBasic.setManufacturerSerialNumber(msn);
		createVendMessage.setVendMSN(meteringAssetBasic);
		PanNumber panNumber = new PanNumber();
		panNumber.setPanNumber(pan);
		createVendMessage.setPanNumber(panNumber);
		setVendAmount(createVendMessage);
		setVendRequestKey(createVendMessage);
		VendRequestTimestamp vendRequestTimestamp = new VendRequestTimestamp();
		setVendRequestTimestamp(createVendMessage, vendRequestTimestamp);
		createVend42.setPaymentRequest(createVendMessage);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to validate source for purchase request 
	 * @param
	 * @return String
	 */
	private String validateTransactionId(){
		String pan="9826170633605552380";
		String msn = "MSN201697";
		VendCreditType_type1 type = VendCreditType_type1.PURCHASE;
		
		CreateVend createVend42 = new CreateVend();
		CreateVendMessage createVendMessage = new CreateVendMessage();
		validateVendRequestSource(createVendMessage, type);
		MeteringAssetBasic meteringAssetBasic = new MeteringAssetBasic();
		meteringAssetBasic.setManufacturerSerialNumber(msn);
		createVendMessage.setVendMSN(meteringAssetBasic);
		PanNumber panNumber = new PanNumber();
		panNumber.setPanNumber(pan);
		createVendMessage.setPanNumber(panNumber);
		setVendAmount(createVendMessage);
		setInvalidVendRequestKey(createVendMessage);
		VendRequestTimestamp vendRequestTimestamp = new VendRequestTimestamp();
		setVendRequestTimestamp(createVendMessage, vendRequestTimestamp);
		createVend42.setPaymentRequest(createVendMessage);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to adjust the electric vend
	 * @param  
	 * @return String
	 */
	private String adjustPanEmpty(String source){
		String pan="";
		String msn = "MSN201697";
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.ADJUSTMENT,source);
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to adjust the electric vend 
	 * @param 
	 * @return String
	 */
	private String adjustPanIsDigit(String source){
		String pan="1234567890abcdef123";
		String msn = "MSN201697";
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.ADJUSTMENT,source);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to adjust the electric vend
	 * @param  
	 * @return String
	 */
	private String adjustPanLength(String source){
		String pan="1234567890";
		String msn = "MSN201697";
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.ADJUSTMENT,source);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}

	/**
	 * Method to validate the pan is digit for purchase request 
	 * @param 
	 * @return String
	 */
	private String validatePanIsDigit(String source){
		String pan="1234567890abcdef123";
		String msn = "MSN201697";
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.PURCHASE,source);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to vaalidate the pan length for purchase request
	 * @param  
	 * @return String
	 */
	private String validatePanLength(String source){
		String pan="1234567890";
		String msn = "MSN201697";
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.PURCHASE,source);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}

	
	/**
	 * Method to validate the pan entry in records
	 * @param  
	 * @return String
	 */
	private String validatePanFound(String source){
		String pan="9826170633605554077";
		String msn = "MSN201697";
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.PURCHASE,source);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to validate the pan entry in records
	 * @param  
	 * @return String
	 */
	private String validateMSNFound(String source){
		String pan="9826170633605552562";
		String msn = "MSN201697";
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.PURCHASE, source);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to validate the MPxN entry in records
	 * @param  
	 * @return String
	 */
	private String validateMPxNFound(String source){
		String pan="9826170633605597864";
		String msn = "MSN201697";
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.PURCHASE,source);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to validate the given PAN for adjustment request 
	 * @param 
	 * @return String
	 */
	private String adjustPanValid(String source){
		String pan="1234567890111122223";
		String msn = "MSN201697";
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.ADJUSTMENT,source);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/**
	 * Method to validate the given PAN for purchase request 
	 * @param
	 * @return String
	 */
	private String validatePan(String source){
		String pan="1234567890111122223";
		String msn = "MSN201697";
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.PURCHASE,source);
		
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/* Drop 2 - KARTHIK ends here */
	
	/**
	 * Method to create the electric vend 
	 * @param 
	 * @return String
	 */
	private String createVendGas(String source){
		String pan="9000000000000000003";
		String msn = "MSN20000000127";
		/* Drop 2 - KARTHIK starts here */
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.PURCHASE,source);
		/* Drop 2 - KARTHIK ends here */
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/* Drop 2 - KARTHIK starts here */
	/**
	 * Method to adjust the gas vend 
	 * @param
	 * @return String
	 */
	private String adjustVendGas(String source){
		String pan="9000000000000000003";
		String msn = "MSN20000000127";
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.ADJUSTMENT,source);
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	/* Drop 2 - KARTHIK ends here */
	
	/**
	 * Method to create the electric vend for Phase 3 meters 
	 * @param 
	 * @return String
	 */
	private String createPhase3VendGas(String source){
		String pan="9726170633605554071";
		String msn = "MSN20000000127";
		/* Drop 2 - KARTHIK starts here */
		CreateVend createVend42 = preparePaymentRequest(pan,msn, VendCreditType_type1.PURCHASE,source);
		/* Drop 2 - KARTHIK ends here */
		new VMSServiceImpl().generatePaymentCode(createVend42);
		return createVend42.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().getBG_IdentifierContent().toString();
	}
	
	/* Drop 2 - KARTHIK starts here */
	
	/**
	 * Method to create the acknowledgement payment delivery receipt
	 * @param 
	 * @return
	 */
	private void createAcknowledgePaymentDelivery(String transactionID){
		DeliveryReceipt deliveryReceipt = new DeliveryReceipt();
	//	String transactionID = "100"; //Transaction ID must be present in the table
		deliveryReceipt.setDeliveryStatus(com.centrica.vms.ws.headend.ack.model.ResponseCode.value1);
		deliveryReceipt.setDescription("testcase");
		deliveryReceipt.setTransactionID(transactionID);
		DeliveryReceiptResponse deliveryReceiptResponse = new VMSServiceImpl().acknowledgePaymentDelivery(deliveryReceipt);
		logger.info(Logger.EVENT_UNSPECIFIED,"Status " + deliveryReceiptResponse.getDeliveryStatus());
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id " + deliveryReceiptResponse.getTransactionID());
	
	}
	
	/**
	 * Method to generate the transaction id
	 * @param
	 * @return String
	 */
	private String generateTransactionID(){
		Random random = new Random();
		int transactionID = random.nextInt(9999);
		logger.info(Logger.EVENT_UNSPECIFIED,"transactionID is" +transactionID);
		return new Integer(transactionID).toString();
	}
	
	/*
	 * Method to process the unsent HE messages for Phase 2 meters
	 * @param 
	 * @return
	 */
	private void processUnsentPhase2BMessages() {
		
		UtilityJobDetails utilityJobDetails = new UtilityJobDetails();
		String userName = "test";
		utilityJobDetails.setUserName(userName);
		utilityJobDetails.setCount("20");
		Boolean requestStatus = false;
		
		try {
			VendServiceDetails vendServiceDetails = (VendServiceDetails) new VMSUtils().getVendServiceDetails(
					DeviceTypeConstants.DEVICE_TYPE_PHASE_2B);
			IHEMessageService vmsUtilityService = new HEMessagePhase2BService(new URL(vendServiceDetails.getUrl()));
			requestStatus = vmsUtilityService.processUnsentMessages(utilityJobDetails);
		} catch (DBException dbx) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception " + dbx.getMessage());
		} catch (NamingException nx) {
			logger.error(Logger.EVENT_FAILURE,"Naming Exception " + nx.getMessage());
		} catch (MalformedURLException mex) {
			logger.error(Logger.EVENT_FAILURE,"Malformed URL Exception " + mex.getMessage());
		} catch (ADBException e) {
			logger.error(Logger.EVENT_FAILURE,"ADB Exception " + e.getMessage());
		} catch (RemoteException e) {
			logger.error(Logger.EVENT_FAILURE,"Remote Exception " + e.getMessage());
		} catch (LoginFault e) {
			logger.error(Logger.EVENT_FAILURE,"LoginFault Exception " + e.getMessage());
		} catch (AccessDeniedFault e) {
			logger.error(Logger.EVENT_FAILURE,"AccessDeniedFault Exception " + e.getMessage());
		} catch (UnexpectedErrorFault e) {
			logger.error(Logger.EVENT_FAILURE,"UnexpectedErrorFault Exception " + e.getMessage());
		} catch (SOAPException e) {
			logger.error(Logger.EVENT_FAILURE,"SOAP Exception " + e.getMessage());
		} catch (IOException e) {
			logger.error(Logger.EVENT_FAILURE,"IO Exception " + e.getMessage());
		} catch (HESLookUpFailedException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"HESLookUpFailedException " + e.getMessage());
		}
		
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend code status : " + requestStatus);
	}
	
	/*
	 * Method to process the unsent HE messages for Phase 2 meters
	 * @param 
	 * @return
	 */
	private void processUnsentPhase3Messages() {
		
		UtilityJobDetails utilityJobDetails = new UtilityJobDetails();
		String userName = "test";
		utilityJobDetails.setUserName(userName);
		utilityJobDetails.setCount("20");
		Boolean requestStatus = false;
		
		try {
			VendServiceDetails vendServiceDetails = (VendServiceDetails) new VMSUtils().getVendServiceDetails(
					DeviceTypeConstants.DEVICE_TYPE_PHASE_3);
			IHEMessageService vmsUtilityService = new HEMessagePhase2BService(new URL(vendServiceDetails.getUrl()));
			requestStatus = vmsUtilityService.processUnsentMessages(utilityJobDetails);
		} catch (DBException dbx) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception " + dbx.getMessage());
		} catch (NamingException nx) {
			logger.error(Logger.EVENT_FAILURE,"Naming Exception " + nx.getMessage());
		} catch (MalformedURLException mex) {
			logger.error(Logger.EVENT_FAILURE,"Malformed URL Exception " + mex.getMessage());
		} catch (ADBException e) {
			logger.error(Logger.EVENT_FAILURE,"ADB Exception " + e.getMessage());
		} catch (RemoteException e) {
			logger.error(Logger.EVENT_FAILURE,"Remote Exception " + e.getMessage());
		} catch (LoginFault e) {
			logger.error(Logger.EVENT_FAILURE,"LoginFault Exception " + e.getMessage());
		} catch (AccessDeniedFault e) {
			logger.error(Logger.EVENT_FAILURE,"AccessDeniedFault Exception " + e.getMessage());
		} catch (UnexpectedErrorFault e) {
			logger.error(Logger.EVENT_FAILURE,"UnexpectedErrorFault Exception " + e.getMessage());
		} catch (SOAPException e) {
			logger.error(Logger.EVENT_FAILURE,"SOAP Exception " + e.getMessage());
		} catch (IOException e) {
			logger.error(Logger.EVENT_FAILURE,"IO Exception " + e.getMessage());
		} catch (HESLookUpFailedException e) {
			logger.error(Logger.EVENT_FAILURE,"HESLookUpFailedException " + e.getMessage());
		}
		
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend code status : " + requestStatus);
	}

}
