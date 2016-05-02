/**
 * HeadendCommonService.java
 * Purpose: Head end common service
 * @author ramasap1
 */
package com.centrica.vms.scheduler.external.service;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.databinding.ADBException;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import trilliantami.www.adjustcreditmessages.AdjustCreditRequestMessage;
import trilliantami.www.trilliantcommontypes.AdjustCredit;
import trilliantami.www.trilliantcommontypes.AdjustCreditValues;
import trilliantami.www.trilliantcommontypes.Credit;
import trilliantami.www.trilliantcommontypes.CreditType;
import trilliantami.www.trilliantcommontypes.Currency;
import trilliantamiservice.AdjustCredit11;
import trilliantamiservice.AdjustCreditE;
import trilliantamiservice.AdjustCreditResponseE;
import ch.iec.tc57._2009.enddevicecontrols.ServiceDeliveryPoint_type0;
import ch.iec.www.tc57._2008.schema.message.HeaderType;

import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.model.JobDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.ApplyVendCodeRequest;
import com.trilliantnetworks.unity.ws.ApplyVendCodeResponse;
import com.trilliantnetworks.unity.ws.Login;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.LoginResponse;
import com.trilliantnetworks.unity.ws.Logout;
import com.trilliantnetworks.unity.ws.PrepaymentKeyRecord;
import com.trilliantnetworks.unity.ws.PrepaymentKeyStatus;
import com.trilliantnetworks.unity.ws.RequestSource;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;
import com.trilliantnetworks.unity.ws.UnityServiceStub;
import com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyRequest;
import com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyResponse;
import com.trilliantnetworks.unity.ws.header.SessionHeader;
import com.trilliantnetworks.unity.ws.prepay.StatusCode;
import com.trilliantnetworks.unity.ws.prepay.VendCodeType;
import com.trilliantnetworks.unity.ws.result.LoginResult;
/**
 * Methods for Head end common service
 */
public class HeadendCommonService {
	
	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	private static final Map<String, Integer> statusMap = new HashMap<String, Integer>();
	
	public HeadendCommonService() {
		
		initializeStatusMap();
	}

	/**
	 * To prepare unityservicestub
	 * @param VendServiceDetails
	 * @param String url
	 * @return UnityServiceStub
	 * @throws AxisFault
	 */
	public UnityServiceStub prepareUnityServiceStub(
			VendServiceDetails vendServiceDetails, String url) throws AxisFault {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareUnityServiceStub method");
		UnityServiceStub unityServiceStub = new UnityServiceStub(url);
		int timeoutSeconds = new Integer(vendServiceDetails.getTimeout()).intValue();
		unityServiceStub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeoutSeconds);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareUnityServiceStub method");
		return unityServiceStub;
	}
	
	/**
	 * Method to login to the headend application
	 * @param UnityServiceStub
	 * @param VendServiceDetails
	 * @return LoginResponse
	 * @throws LoginFault
	 * @throws AccessDeniedFault
	 * @throws UnexpectedErrorFault
	 * @throws RemoteException
	 * @throws ADBException
	 */
	public LoginResponse loginService(UnityServiceStub unityServiceStub,VendServiceDetails vendServiceDetails) throws LoginFault, AccessDeniedFault,
			UnexpectedErrorFault, RemoteException, ADBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering login method");
		String userName = vendServiceDetails.getUserName();
		String password = vendServiceDetails.getPassword();
		Login loginDeatils = new Login();
		loginDeatils.setUsername(userName);
		loginDeatils.setPassword(password);
		LoginResponse loginResponse = unityServiceStub.login(loginDeatils);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving login method");
		return loginResponse;
	}
	
	
	/**
	 * Method to create the session header
	 * @param LoginResponse
	 * @param UnityServiceStub
	 * @return UnityServiceStub
	 * @throws ADBException
	 */
	public UnityServiceStub prepareSessionHeader(LoginResponse loginResponse,UnityServiceStub unityServiceStub)
			throws ADBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareSessionHeader entering");
		LoginResult loginResult = loginResponse.getResult();
		String sessionID = loginResult.getSessionId();
		logger.info(Logger.EVENT_UNSPECIFIED,"URL "+ loginResult.getServerUrl());
		logger.info(Logger.EVENT_UNSPECIFIED,"sessionID "+ sessionID);
		addSessionHeader(unityServiceStub, sessionID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareSessionHeader leavinh");
		return unityServiceStub;

	}
	
	/**
	 * Method to logout the session
	 * @param UnityServiceStub
	 * @param String sessionID
	 * @return
	 * @throws AccessDeniedFault
	 * @throws UnexpectedErrorFault
	 * @throws RemoteException
	 * @throws ADBException
	 */
	public void logoutService(UnityServiceStub unityServiceStub,String sessionID) throws AccessDeniedFault,UnexpectedErrorFault,RemoteException,ADBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering logoutService method");
		SessionHeader sessionHeader = new SessionHeader();
		sessionHeader.setSessionId(sessionID);
		ServiceClient serviceClient = unityServiceStub._getServiceClient();
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMElement omElement = sessionHeader.getOMElement(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","SessionHeader"), fac);
		serviceClient.removeHeaders();
		serviceClient.addHeader(omElement);
		Logout logOut = new Logout();
		unityServiceStub.logout(logOut);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving logoutService method");
	}
	
	/**
	 * Method to add the session header
	 * @param UnityServiceStub
	 * @param String sessionID
	 * @return
	 * @throws ADBException
	 */
	public void addSessionHeader(UnityServiceStub unityServiceStub,
			String sessionID) throws ADBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering addSessionHeader entering");
		SessionHeader sessionHeader = new SessionHeader();
		sessionHeader.setSessionId(sessionID);
		ServiceClient serviceClient = unityServiceStub._getServiceClient();
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMElement omElement = sessionHeader.getOMElement(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","SessionHeader"), fac);
		serviceClient.addHeader(omElement);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving addSessionHeader entering");
	}
	
	/**
	 * Method to prepare the request for headend
	 * @param JobDetails
	 * @return ApplyVendCodeRequest
	 */
	public ApplyVendCodeRequest prepareApplyVendCodeRequest(
			JobDetails jobDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareApplyVendCodeRequest method");
		ApplyVendCodeRequest applyVendCodeRequest = new ApplyVendCodeRequest();
		applyVendCodeRequest.setPaymentCardNumber(jobDetails.getPan());
		applyVendCodeRequest.setTransactionId(jobDetails.getTransactionID());
		applyVendCodeRequest.setValue(new Integer(jobDetails.getCreditValue())
		.intValue());
		setVendCodeTimestamp(jobDetails, applyVendCodeRequest);
		setVendCode(jobDetails, applyVendCodeRequest);
		setVendCodeType(jobDetails, applyVendCodeRequest);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareApplyVendCodeRequest method");
		return applyVendCodeRequest;
	}
	
	/**
	 * Prepares the request. This is a pre-requisite before invoking the headend system for updating the prepayment key.
	 * @param jobDetails - the details fetched from the scheduled job.
	 * @return UpdatePrepaymentKeyRequest - the request object for headend system.
	 */
	public UpdatePrepaymentKeyRequest prepareUpdatePPKeyRequest(JobDetails jobDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareUpdatePPKeyRequest method");
		UpdatePrepaymentKeyRequest request = new UpdatePrepaymentKeyRequest();
		PrepaymentKeyRecord record = new PrepaymentKeyRecord();
		record.setDeviceSerialNo(jobDetails.getMsn());
		record.setPrepaymentKey(jobDetails.getPpKey());
		record.setTransactionId(jobDetails.getTransactionID());
		request.setPrepaymentKeyRecords( new PrepaymentKeyRecord[] {record});
		Calendar timestamp=Calendar.getInstance();
		timestamp.setTime(jobDetails.getTimestamp());
		request.setTimeStamp(timestamp);
		request.setSource(RequestSource.VMS); 
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareUpdatePPKeyRequest method");
		return request;
	}
	
	/**
	 * Method to prepare the adjust credit request for headend
	 * @param JobDetails
	 * @return ApplyVendCodeRequest
	 */
	public AdjustCredit11 prepareApplyAdjustCreditRequest(
			JobDetails jobDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareApplyAdjustCreditRequest method");
		AdjustCredit11 adjustCredit14 = new AdjustCredit11();

		adjustCredit14.setAdjustCredit(new AdjustCreditE());
		adjustCredit14.getAdjustCredit().setArg0(new AdjustCreditRequestMessage());
	
		HeaderType headerType = new HeaderType();
		
		prepareApplyAdjustCreditHeader(jobDetails, headerType);
		adjustCredit14.getAdjustCredit().getArg0().setHeader(headerType);
		
		AdjustCredit adjustCredit = new AdjustCredit();
		ServiceDeliveryPoint_type0 deliveryPointType = new ServiceDeliveryPoint_type0();
		deliveryPointType.setMRID(jobDetails.getMpxn());
		adjustCredit.setServiceDeliveryPoint(deliveryPointType);
		
		Credit credit = new Credit();
		prepareApplyAdjustCreditValues(jobDetails, credit);
		adjustCredit.setCredit(credit);
		
		adjustCredit.setType("ADJUSTMENT");
		
		adjustCredit14.getAdjustCredit().getArg0().setPayload(new AdjustCreditValues());
		adjustCredit14.getAdjustCredit().getArg0().getPayload().addAdjustCredit(adjustCredit);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareApplyAdjustCreditRequest method");
		return adjustCredit14;
	}
	
	/**
	 * @param jobDetails
	 * @param credit
	 */
	private void prepareApplyAdjustCreditValues(JobDetails jobDetails, Credit credit) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareApplyAdjustCreditValues method");
		credit.setAmount(Long.parseLong(jobDetails.getCreditValue()));
		credit.setCurrency(Currency.GBP); 
		credit.setType(CreditType.credit);
		Calendar c = Calendar.getInstance();
		c.setTime(jobDetails.getTimestamp());
		credit.setStartTime(c);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareApplyAdjustCreditValues method");
	}

	/**
	 * @param jobDetails
	 * @param headerType
	 */
	private void prepareApplyAdjustCreditHeader(JobDetails jobDetails, HeaderType headerType) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareApplyAdjustCreditHeader method");
		headerType.setNoun("AdjustCredit");
		headerType.setVerb("create");
		headerType.setRevision("1.0");
		Calendar c = Calendar.getInstance();
		c.setTime(jobDetails.getTimestamp());
		headerType.setTimestamp(c);
		headerType.setSource("VMS");
		headerType.setMessageID(jobDetails.getTransactionID());
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareApplyAdjustCreditHeader method");
	}

	/**
	 * Method to set the vend code created timestamp
	 * @param JobDetails
	 * @param ApplyVendCodeRequest
	 * @return
	 */
	private void setVendCodeTimestamp(JobDetails jobDetails,
			ApplyVendCodeRequest applyVendCodeRequest) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendCodeTimestamp method");
		Calendar timestamp=Calendar.getInstance();
		timestamp.setTime(jobDetails.getTimestamp());
		applyVendCodeRequest.setTimestamp(timestamp);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendCodeTimestamp method");
	}

	/**
	 * Method to set the vend code in the request
	 * @param JobDetails
	 * @param ApplyVendCodeRequest
	 * @return
	 */
	private void setVendCode(JobDetails jobDetails,
			ApplyVendCodeRequest applyVendCodeRequest) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendCode method");
		Integer deviceTypeId = jobDetails.getDeviceType();
		if(jobDetails.getPaymentType().equals(TransactionType.GPositiveAdjustment.toString()) ||
				jobDetails.getPaymentType().equals(TransactionType.GNegativeAdjustment.toString())||
				     jobDetails.getPaymentType().equals(TransactionType.VoidGNegativeAdjustment.toString())){
			applyVendCodeRequest.setVendCode(jobDetails.getVendCode());
		}else if (deviceTypeId == DeviceTypeEnum.PH2B.getDeviceType()){
			applyVendCodeRequest.setVendCode(new VMSUtils().stringToHexString(jobDetails.getVendCode().toCharArray()));
		}else {
			applyVendCodeRequest.setVendCode(jobDetails.getVendCode()); 
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendCode method");
	}
	
	/**
	 * Method to set the vend code type
	 * @param JobDetails
	 * @param ApplyVendCodeRequest
	 * @return
	 */
	private void setVendCodeType(JobDetails jobDetails,
			ApplyVendCodeRequest applyVendCodeRequest) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendCodeType method");
		String paymentType = jobDetails.getPaymentType();
		if (paymentType.equals(TransactionType.ElectricPurchase.toString())
				|| paymentType.equals(TransactionType.GasPurchase.toString())) {
			applyVendCodeRequest.setType(VendCodeType.Purchase);
		} else {
			applyVendCodeRequest.setType(VendCodeType.Adjustment);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendCodeType method");
	}
	
	/**
	 * Method to process the vend code response
	 * 
	 * @param ApplyVendCodeResponse
	 * @param ApplyVendCodeRequest
	 * @return int
	 */
	public int processApplyVendCodeResponse(
			ApplyVendCodeResponse applyVendCodeResponse,
			ApplyVendCodeRequest applyVendCodeRequest) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processApplyVendCodeResponse method");
		StatusCode statusCode = applyVendCodeResponse.getDeliveryStatus();
		int status = 0;
		if (applyVendCodeResponse.getTransactionId().equals(
				applyVendCodeRequest.getTransactionId())) {
			logger.info(Logger.EVENT_UNSPECIFIED,"matching transaction id");
			status = statusCode.getValue();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processApplyVendCodeResponse method");
		return status;
	}
	
	/**
	 * Maps the status from the Head end response for updatePrepaymentKey
	 * 
	 * @param updatePrepaymentKeyRequest
	 * @param updatePrepaymentKeyResponse
	 * @return status
	 */
	public int processUpdatePrepaymentKeyResponse(
			UpdatePrepaymentKeyRequest updatePrepaymentKeyRequest,
			UpdatePrepaymentKeyResponse updatePrepaymentKeyResponse) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processUpdatePrepaymentKeyResponse method");
		PrepaymentKeyStatus[] responses = updatePrepaymentKeyResponse.getUpdatePrepaymentKeyResults();
		PrepaymentKeyRecord prepaymentKeyRecord = updatePrepaymentKeyRequest.getPrepaymentKeyRecords()[0];
		int status = 0;
		
		//TODO:: Why are these arrays in request and response. We are doing only single update right?
		for (PrepaymentKeyStatus response : responses) {
			if (response.getTransactionId().equals(prepaymentKeyRecord.getTransactionId())) {
				logger.info(Logger.EVENT_UNSPECIFIED,"matching transaction id");
				status = response.getDeliveryStatus().getValue();
				break;
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processUpdatePrepaymentKeyResponse method");
		return status;
	}

	/**
	 * Method to generate adjust credit response
	 * @param adjustCreditResponse
	 * @param adjustCredit14
	 * @return
	 */
	public int processApplyAdjustCreditResponse(
			AdjustCreditResponseE  adjustCreditResponse,
			AdjustCredit11 adjustCredit14) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processApplyAdjustCreditResponse method");
		String statusCode = adjustCreditResponse.getAdjustCreditResponse().get_return().getReply().getReplyCode();
		int status = 0;
		HeaderType headerType = adjustCredit14.getAdjustCredit().getArg0().getHeader();
		String transactionID = headerType.getMessageID();
		if( transactionID.equals(adjustCreditResponse.getAdjustCreditResponse().get_return().getHeader().getCorrelationID()) ) {
			logger.info(Logger.EVENT_UNSPECIFIED,"matching transaction id");
			logger.info(Logger.EVENT_UNSPECIFIED,"Status Code returned in response " + statusCode);
			status = mapHeadEndAdjustCreditStatus(statusCode);
			
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processApplyAdjustCreditResponse method");
		return status;
	}
	
	/**
	 * Maps Head End Reply Code to VMS Status Code
	 * 
	 * @param heStatusCode of type String
	 * @return integer
	 */
	private int mapHeadEndAdjustCreditStatus(final String heStatusCode) {
		
		final Integer mapStatus = statusMap.get(heStatusCode);
		return mapStatus != null ? mapStatus : 300;
		
	}
	
	/**
	 * Initializes Status Map
	 */
	private void initializeStatusMap() {

		statusMap.put("0.0", 200);
		statusMap.put("5.22", 310);
		statusMap.put("5.23", 310);
		statusMap.put("5.26", 310);
		statusMap.put("5.27", 340);
		statusMap.put("5.28", 330);
		statusMap.put("5.29", 310);
		statusMap.put("5.31", 350);
		statusMap.put("5.32", 340);
		statusMap.put("5.33", 310);

	}

	

	
	
}
