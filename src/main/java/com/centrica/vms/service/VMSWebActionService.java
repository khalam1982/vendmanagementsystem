/**
 * VMSWebActionService.java
 * Purpose: VMS Web Action service class
 * @author ramasap1
 */
package com.centrica.vms.service;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import jxl.write.WriteException;

import org.apache.axis2.databinding.types.Token;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.hibernate.StaleObjectStateException;

import smartmeterprocessing.enterprise.uk.co.britishgas.CreateVendMessage;
import smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendAmount;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendCreditType_type1;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestTimestamp;
import smartmeterprocessing.enterprise.uk.co.britishgas.VoidVendRequestMessage;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.dao.VendReportTransactionDAO;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.UserOperationException;
import com.centrica.vms.exception.UsrAlreadyExistException;
import com.centrica.vms.exception.UsrLockedException;
import com.centrica.vms.exception.UsrPwdExpiredException;
import com.centrica.vms.exception.UsrisaPowerUsrException;
import com.centrica.vms.form.AckRequestDetailsForm;
import com.centrica.vms.form.AdjustmentRequestDetailsForm;
import com.centrica.vms.form.HEUtilityRequestForm;
import com.centrica.vms.form.VendReportRequestForm;
import com.centrica.vms.form.VendRequestDetailsForm;
import com.centrica.vms.form.VoidRequestDetailsForm;
import com.centrica.vms.model.FunctionDetails;
import com.centrica.vms.model.GroupDetails;
import com.centrica.vms.model.ReportGeneratorDetails;
import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.model.StatusDetails;
import com.centrica.vms.model.UserCredentials;
import com.centrica.vms.model.UserDetails;
import com.centrica.vms.model.UserFunctionDetails;
import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.model.UserDetails.Indicator;
import com.centrica.vms.scheduler.service.VMSSchedulerService;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceipt;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceiptResponse;
import com.centrica.vms.ws.headend.ack.model.DeliveryStatusCode;
import com.centrica.vms.ws.headend.ack.model.ResponseCode;
import com.centrica.vms.ws.model.CustomerDetails;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.PremiseDetails;
import com.centrica.vms.ws.sap.service.CreateVend;
import com.centrica.vms.ws.sap.service.CreateVendResponse;
import com.centrica.vms.ws.sap.service.VMSSAPService;
import com.centrica.vms.ws.sap.service.VoidVend;
import com.centrica.vms.ws.sap.service.VoidVendResponse;
import com.centrica.vms.ws.service.ReportGeneratorService;
import com.centrica.vms.ws.service.VMSServiceImpl;
import common.enterprise.uk.co.britishgas.BG_Amount;
import common.enterprise.uk.co.britishgas.BG_CurrencyAmount;
import common.enterprise.uk.co.britishgas.BG_Identifier;
import common.enterprise.uk.co.britishgas.BG_MessageHeader;
/**
 * Methods for VMS Web Action service
 * @see IVMSWebActionService
 */
public class VMSWebActionService implements IVMSWebActionService {

	
	private Logger logger = ESAPI.getLogger(this.getClass());
	
	private final WSTransactionDAO wsTransactionDAO;
	private final VMSTransactionDAO vmsTransactionDAO;
	private final VendReportTransactionDAO vendReportTransactionDAO;
	private final VMSSAPService vmssapService;
	private final VMSSchedulerService vmsSchedulerService;
	private final VMSServiceImpl vmsService;
	
	/**
	 * Constructor
	 */
	public VMSWebActionService() {
		wsTransactionDAO = new WSTransactionDAO();
		vmsTransactionDAO = new VMSTransactionDAO();
		vendReportTransactionDAO = new VendReportTransactionDAO();
		vmssapService = new VMSServiceImpl();
		vmsSchedulerService = new VMSSchedulerServiceImpl();
		vmsService = new VMSServiceImpl();
	}
	
	/**
	 * Constructor
	 * @param wstransactionDAO - WSTransactionDAO
	 * @param vmsTransactionDAO - VMSTransactionDAO
	 * @param vendReportTransactionDAO - VendReportTransactionDAO
	 * @param vmssapService - VMSSAPService
	 * @param vmsSchedulerService - VMSSchedulerService
	 * @param vmsService - VMSServiceImpl
	 */
	public VMSWebActionService(final WSTransactionDAO wstransactionDAO, final VMSTransactionDAO vmsTransactionDAO, 
			final VendReportTransactionDAO vendReportTransactionDAO, final VMSSAPService vmssapService, 
			final VMSSchedulerService vmsSchedulerService, final VMSServiceImpl vmsService) {
		this.wsTransactionDAO = wstransactionDAO;
		this.vmsTransactionDAO = vmsTransactionDAO;
		this.vendReportTransactionDAO = vendReportTransactionDAO;
		this.vmssapService = vmssapService;
		this.vmsSchedulerService = vmsSchedulerService;
		this.vmsService = vmsService;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#getMeterDetails(java.lang.String)
	 */
	public MeterDetails getMeterDetails(String msn) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getMeterDetails method ");
		logger.info(Logger.EVENT_UNSPECIFIED,"MSN :" +msn);
		MeterDetails meterDetails = null;
		try{
			meterDetails = wsTransactionDAO.getMeterDetails(msn);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		
		logger.info(Logger.EVENT_UNSPECIFIED,"meterDetails:" +meterDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getMeterDetails method ");
		return meterDetails;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#getCustomerDetails(java.lang.String)
	 */
	public CustomerDetails getCustomerDetails(String pan) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getCustomerDetails method ");
		logger.info(Logger.EVENT_UNSPECIFIED,"pan :" +pan);
		CustomerDetails customerDetails = null;
		try{
			customerDetails = wsTransactionDAO.getCustomerDetails(pan);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		
		logger.info(Logger.EVENT_UNSPECIFIED,"customerDetails:" +customerDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getCustomerDetails method ");
		return customerDetails;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#getPremiseDetails(java.lang.String)
	 */
	public PremiseDetails getPremiseDetails(String mpxn) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getPremiseDetails method ");
		logger.info(Logger.EVENT_UNSPECIFIED,"mpxn :" + mpxn);
		PremiseDetails premiseDetails = null;
		try{
			premiseDetails = wsTransactionDAO.getPremiseDetails(mpxn);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		
		logger.info(Logger.EVENT_UNSPECIFIED,"premiseDetails:" +premiseDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getPremiseDetails method ");
		return premiseDetails;
	}

	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#saveMeterDetails(com.centrica.vms.ws.model.MeterDetails)
	 */
	public Boolean saveMeterDetails(MeterDetails meterDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering saveMeterDetails method ");
		
		meterDetails.setPan(meterDetails.getPan().toUpperCase());
		Boolean status = false;
		try{
			status = vmsTransactionDAO.insert(meterDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"status:" +status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving saveMeterDetails method ");
		return status;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#saveCustomerDetails(com.centrica.vms.ws.model.CustomerDetails)
	 */
	public Boolean saveCustomerDetails(CustomerDetails customerDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering saveCustomerDetails method ");
		customerDetails.setPan(customerDetails.getPan().toUpperCase());
		Boolean status = false;
		try{
			status = vmsTransactionDAO.insert(customerDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"status:" +status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving saveCustomerDetails method ");
		return status;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#savePremiseDetails(com.centrica.vms.ws.model.PremiseDetails)
	 */
	public Boolean savePremiseDetails(PremiseDetails premiseDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering savePremiseDetails method ");
		VMSTransactionDAO vmstransactionDAO = new VMSTransactionDAO();
		premiseDetails.setMpxn(premiseDetails.getMpxn().toUpperCase());
		Boolean status = false;
		try{
			status = vmstransactionDAO.insert(premiseDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"status:" +status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving savePremiseDetails method ");
		return status;
	}

	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#updateMeterDetails(com.centrica.vms.ws.model.MeterDetails)
	 */
	public Boolean updateMeterDetails(MeterDetails meterDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateMeterDetails method ");
		Boolean status = false;
		try{
			status = vmsTransactionDAO.update(meterDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"status:" +status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateMeterDetails method ");
		return status;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#updateMeterDetails(com.centrica.vms.ws.model.CustomerDetails)
	 */
	public Boolean updateCustomerDetails(CustomerDetails customerDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateCustomerDetails method ");
		VMSTransactionDAO vmstransactionDAO = new VMSTransactionDAO();
		Boolean status = false;
		try{
			status = vmstransactionDAO.update(customerDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"status:" +status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateCustomerDetails method ");
		return status;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#updatePremiseDetails(com.centrica.vms.ws.model.PremiseDetails)
	 */
	public Boolean updatePremiseDetails(PremiseDetails premiseDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updatePremiseDetails method ");
		VMSTransactionDAO vmstransactionDAO = new VMSTransactionDAO();
		Boolean status = false;
		try{
			status = vmstransactionDAO.update(premiseDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"status:" +status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updatePremiseDetails method ");
		return status;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#deleteMeterDetails(com.centrica.vms.ws.model.MeterDetails)
	 */
	public Boolean deleteMeterDetails(MeterDetails meterDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering deleteMeterDetails method ");
		Boolean status = false;
		try{
			status = vmsTransactionDAO.delete(meterDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"status:" +status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving deleteMeterDetails method ");
		return status;
	}

	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#getVendHistory(java.lang.String)
	 */
	public ArrayList<VendHistoryDetails> getVendHistory(String pan){ 
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendHistory method ");
		logger.info(Logger.EVENT_UNSPECIFIED,"pan :" +pan);
		ArrayList<VendHistoryDetails> list = vmsTransactionDAO.getVendHistory(pan);
		logger.info(Logger.EVENT_UNSPECIFIED,"list:" +list);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendHistory method ");
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#sendVendRequest(com.centrica.vms.form.VendRequestDetailsForm)
	 */
	public VendRequestDetailsForm sendVendRequest(
			VendRequestDetailsForm vendRequestDetailsForm) { 
		logger.debug(Logger.EVENT_SUCCESS,"Entering  sendVendRequest method");
		Random random = new Random();
		String transactionID = "VMS"+ random.nextInt(999)+random.nextInt(999)+random.nextInt(999);
		vendRequestDetailsForm.setTransactionID(transactionID);
		
		CreateVend paymentRequest = preparePaymentRequest(vendRequestDetailsForm);
		CreateVendResponse paymentResponse = vmssapService.generatePaymentCode(paymentRequest);
		populatePaymentResponse(vendRequestDetailsForm, transactionID,
				paymentResponse);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVendRequest method");
		return vendRequestDetailsForm;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#sendVoidRequest(com.centrica.vms.form.VoidRequestDetailsForm)
	 */
	public VoidRequestDetailsForm sendVoidRequest(
			VoidRequestDetailsForm voidRequestDetailsForm) { 
		logger.debug(Logger.EVENT_SUCCESS,"Entering  sendVoidRequest method");
		VoidVend voidRequest = new VoidVend();
		String transactionID = voidRequestDetailsForm.getTransactionID();
		String originalTxnID = voidRequestDetailsForm.getOriginalTransactionID();
		String panNumber = voidRequestDetailsForm.getPan();
		VoidVendRequestMessage voidVendRequestMessage = new VoidVendRequestMessage();
		BG_MessageHeader messageHeader = new BG_MessageHeader();
		messageHeader.setServiceConsumer("0");
		voidVendRequestMessage.setMessageHeader(messageHeader);
		setVendRequestKeyVoid(voidVendRequestMessage, transactionID);
		setVendOrgRequestKeyVoid(voidVendRequestMessage,originalTxnID);
		setVendRequestTimestampVoid(voidVendRequestMessage);
		setVoidVendRequestPan(panNumber, voidVendRequestMessage);
		voidRequest.setVoidTxnRequest(voidVendRequestMessage);
		VoidVendResponse paymentResponse = vmssapService.voidPayment(voidRequest);
		populateVoidResponse(voidRequestDetailsForm, transactionID,
				paymentResponse);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVoidRequest method");
		return voidRequestDetailsForm;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#sendAckRequest(com.centrica.vms.form.AckRequestDetailsForm)
	 */
	public AckRequestDetailsForm sendAckRequest(
			AckRequestDetailsForm ackRequestDetailsForm) { 
		logger.debug(Logger.EVENT_SUCCESS,"Entering  sendAckRequest method");
		String transactionID = ackRequestDetailsForm.getTransactionID();
		String transactionDesc = ackRequestDetailsForm.getTransactionDesc();
		DeliveryReceipt deliveryReceipt = new DeliveryReceipt();
		deliveryReceipt.setDeliveryStatus(ResponseCode.value1);
		deliveryReceipt.setDescription(transactionDesc);
		deliveryReceipt.setTransactionID(transactionID);
		DeliveryReceiptResponse deliveryReceiptResponse = vmsService.acknowledgePaymentDelivery(deliveryReceipt);
		
		populateAckResponse(ackRequestDetailsForm, transactionID,
				deliveryReceiptResponse);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendAckRequest method");
		return ackRequestDetailsForm;
	}
	
	private void populateAckResponse(
			AckRequestDetailsForm ackRequestDetailsForm, String transactionID,
			DeliveryReceiptResponse deliveryReceiptResponse) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering populateAckResponse method");
		ackRequestDetailsForm.setStatusCode(deliveryReceiptResponse.getDeliveryStatus().toString());
		if (deliveryReceiptResponse.getDeliveryStatus() == DeliveryStatusCode.value1) {
			ackRequestDetailsForm.setDescription("ACK SUCCESSFULL");
		} else {
			ackRequestDetailsForm.setDescription("ACK UNSUCCESSFULL");
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving populateAckResponse method");
		
	}

	/**
	 * @param voidRequestDetailsForm
	 * @param transactionID
	 * @param paymentResponse
	 */
	private void populateVoidResponse(
			VoidRequestDetailsForm voidRequestDetailsForm,
			String transactionID, VoidVendResponse paymentResponse) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering populateVoidResponse method");
		voidRequestDetailsForm.setDescription(paymentResponse.getVoidTxnResponse().getVendOutcome().getVendOutcomeDescription());
		voidRequestDetailsForm.setStatusCode(paymentResponse.getVoidTxnResponse().getVendOutcome().getVendOutcomeCode().toString());
		voidRequestDetailsForm.setTransactionID(transactionID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving populateVoidResponse method");
	}
	/**
	 * Method to set the vend request key for void
	 * @param voidVendRequestMessage
	 * @param transactionID
	 * @return
	 */
	private void setVendRequestKeyVoid(VoidVendRequestMessage voidVendRequestMessage, String transactionID) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendRequestKeyVoid method");
		VendRequestKey vendRequestKey = new VendRequestKey();
		BG_Identifier identifier = new BG_Identifier();
		Token token = new Token();
		token.setValue(transactionID);
		identifier.setBG_IdentifierContent(token);
		vendRequestKey.setVendRequestIdentifier(identifier);
		voidVendRequestMessage.setVendRequestKey(vendRequestKey);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendRequestKeyVoid method");
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
	 * Method to set request pan in the void request
	 * @param String
	 * @param VoidVendRequestMessage
	 * @return
	 */
	private void setVoidVendRequestPan(String pan,
			VoidVendRequestMessage voidVendRequestMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  setVoidVendRequestPan method");
		PanNumber panNumber = new PanNumber();
		panNumber.setPanNumber(pan);
		voidVendRequestMessage.setVendRequestPAN(panNumber);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVoidVendRequestPan method");
	}
	
	/* (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#sendVendReportRequest(com.centrica.vms.form.VendReportRequestForm)
	 */
	public VendReportRequestForm sendVendReportRequest(VendReportRequestForm vendReportRequestForm, OutputStream out) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  sendVendReportRequest method");
		ReportGeneratorDetails reportGeneratorDetails = new ReportGeneratorDetails();
		try {
			Date startDate = getDateByValue(vendReportRequestForm.getStartDate(), vendReportRequestForm.getMenuStartHour(),
				vendReportRequestForm.getMenuStartMinute(),  vendReportRequestForm.getMenuStartMerid());
			Date endDate = getDateByValue(vendReportRequestForm.getEndDate(), vendReportRequestForm.getMenuEndHour(),
					vendReportRequestForm.getMenuEndMinute(), vendReportRequestForm.getMenuEndMerid());
			
			logger.info(Logger.EVENT_UNSPECIFIED,"Start date : " + startDate);
			logger.info(Logger.EVENT_UNSPECIFIED,"End date : " + endDate);
			logger.info(Logger.EVENT_UNSPECIFIED,"Source type : " + vendReportRequestForm.getSelectedSource());
			logger.info(Logger.EVENT_UNSPECIFIED,"Status type : " + vendReportRequestForm.getSelectedStatus());
			
			reportGeneratorDetails.setStartDate(startDate);
			reportGeneratorDetails.setEndDate(endDate);
			reportGeneratorDetails.setSource(vendReportRequestForm.getSelectedSource());
			reportGeneratorDetails.setStatus(vendReportRequestForm.getSelectedStatus());
			new ReportGeneratorService(reportGeneratorDetails).invoke(out);
		} catch (ParseException ex) {
			logger.error(Logger.EVENT_FAILURE,"ParseException : " + ex.getMessage());
		} catch (WriteException ex) {
			logger.error(Logger.EVENT_FAILURE,"WriteException : " + ex.getMessage());
		} catch (IOException ex) {	
			logger.error(Logger.EVENT_FAILURE,"IOException : " + ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  sendVendReportRequest method");
		return vendReportRequestForm;
		
	}
	
	/**
	 * @param date
	 * @param hour
	 * @param minute
	 * @param meridian
	 * @return
	 * @throws ParseException
	 */
	private Date getDateByValue(String date, String hour, String minute, String meridian) throws ParseException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  getDateByValue method");
		Date value = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Integer hVal = getHourOfDay(hour, meridian);
		try {
			Date sDate = dateFormat.parse(date);
			Calendar sCal = Calendar.getInstance();
			sCal.setTime(sDate);
			sCal.set(Calendar.HOUR_OF_DAY, hVal);
			sCal.set(Calendar.MINUTE, new Integer(minute));
			value = sCal.getTime();
		} catch (ParseException ex) {
			throw ex;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  getDateByValue method");
		return value;
	}
	
	/**
	 * @param hour
	 * @param meridian
	 * @return
	 */
	private Integer getHourOfDay(String hour, String meridian) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  getHourOfDay method");
		Integer value = new Integer(hour);
		if (meridian.equals("2")) { // AM
			if (value != 12) // exclude noon
				value = value + 12;
		} else {
			if (value == 12) // for a 24 hour clock
				value = 0;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  getHourOfDay method");
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#getUserDetails(java.lang.String, java.lang.String)
	 */
	public UserDetails getUserDetails(String userName, String password)throws UsrPwdExpiredException,UsrLockedException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  getUserDetails method");
		UserDetails userDetail = null;
		ArrayList<UserDetails> userDetails = vmsTransactionDAO.getUserDetails(userName);
		if(userDetails!=null && userDetails.size()>0){
			 userDetail = userDetails.get(0);
			 userDetail = validateUserDetails(password, userDetail);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getUserDetails method");
		return userDetail;
	}

	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#getGroupDetails(java.lang.String)
	 */
	public ArrayList<GroupDetails> getGroupDetails(String groupID){
		logger.debug(Logger.EVENT_SUCCESS,"Entering  getGroupDetails method");
		ArrayList<GroupDetails> groupDetails = vmsTransactionDAO.getGroupDetails(groupID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  getGroupDetails method");
		return groupDetails;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#getUserDetails(java.lang.String)
	 */
	public UserDetails getUserDetails(String groupID){
		logger.debug(Logger.EVENT_SUCCESS,"Entering  getUserDetails method");
		ArrayList<UserDetails> userDetails = vmsTransactionDAO.getPowerUserDetails(groupID);
		UserDetails userDetail = null;
		if(userDetails!=null && userDetails.size()>0){
			userDetail = userDetails.get(0);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  getUserDetails method");
		return userDetail;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#getFunctionDetails(java.lang.String)
	 */
	public ArrayList<FunctionDetails> getFunctionDetails(String groupID){
		logger.debug(Logger.EVENT_SUCCESS,"Entering  getFunctionDetails method");
		String functionGroup = "Function_"+groupID;
		Collection<String> groupCollection = new VMSUtils().getGroupFunctions(functionGroup);
		ArrayList<FunctionDetails> functionDetails = vmsTransactionDAO.getFunctionDetails(groupCollection);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  getFunctionDetails method");
		return functionDetails;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#updateUserDetails(com.centrica.vms.model.UserDetails, java.lang.Boolean)
	 */
	public Boolean updateUserDetails(UserDetails userDetails,Boolean isPowerUser) throws UserOperationException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering  updateUserDetails method");
		Boolean status = Boolean.FALSE;
		try{
			if(isPowerUser){
				updateChildUserDetails(userDetails);
			}
			status = vmsTransactionDAO.update(userDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException :"+ex.getMessage());
		}catch(StaleObjectStateException ex){
			logger.error(Logger.EVENT_FAILURE,"StaleObjectStateException :"+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  updateUserDetails method");
		return status;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#getUser(java.lang.String, java.lang.String)
	 */
	public UserDetails getUser(String userName,String groupID) throws UsrAlreadyExistException,UsrisaPowerUsrException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering  getUser method");
		ArrayList<UserDetails> userDetails = vmsTransactionDAO.getUserDetails(userName);
		UserDetails userDetail = null;
		if(userDetails!=null && userDetails.size()>0){
			userDetail = userDetails.get(0);
			if(!userDetail.getGroupID().equals(groupID)){
				throw new UsrAlreadyExistException();
			}
			if(userDetail.getPowerIND()== Indicator.Y.getIndicator()){
				throw new UsrisaPowerUsrException();
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  getUser method");
		return userDetail;
	}
    
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#getResendVCCount(java.lang.String, java.lang.String)
	 */
	public Integer getResendTransactionCount(String userName, Integer type){
		logger.debug(Logger.EVENT_SUCCESS,"Entering  getResendTransactionCount method : " + userName);
		Integer count = vmsTransactionDAO.getResendTransactionCount(type);
		logger.info(Logger.EVENT_UNSPECIFIED,type + "count : " + count);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  getResendTransactionCount method");
		return count;
	}
	
	/**
	 * Method used to update the child user details.
	 * @param userDetails
	 * @param vmsTransactionDAO
	 * @throws DBException
	 */
	private void updateChildUserDetails(UserDetails userDetails) throws DBException,UserOperationException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  updateChildUserDetails method");
		ArrayList<UserDetails> groupUsers = vmsTransactionDAO.getGroupUsers(userDetails.getGroupID());
		for(UserDetails user:groupUsers){
			user.setUpdatedBy(userDetails.getUpdatedBy());
			user.setUpdatedTimestamp(new Date());
			Set<UserFunctionDetails> userFunctionDetails = user.getUserFunctionDetails();
			Set<UserFunctionDetails> mainUserFunctionDetails = userDetails.getUserFunctionDetails();
			updateChildUserFunctionDetails(userFunctionDetails,mainUserFunctionDetails);
			Boolean childStatus = vmsTransactionDAO.update(user);
			if(!childStatus){
				throw new UserOperationException();
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  updateChildUserDetails method");
	}

	/**
	 * Method used to update the child user function details
	 * @param userFunctionDetails
	 * @param mainUserFunctionDetails
	 */
	private void updateChildUserFunctionDetails(Set<UserFunctionDetails> userFunctionDetails,Set<UserFunctionDetails> mainUserFunctionDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  updateChildUserFunctionDetails method");
		int size = userFunctionDetails.size();
		for(int i=0;i<size;i++){
			for(UserFunctionDetails userFunction:userFunctionDetails){
				boolean isPresent = false;
				 for(UserFunctionDetails mainUserFunction:mainUserFunctionDetails){
					if(mainUserFunction.getFunctionDetails().getFunctionCode().equals(userFunction.getFunctionDetails().getFunctionCode())){
						isPresent=true;
						break;
					}	
				}
				if(!isPresent){
					userFunctionDetails.remove(userFunction);
					break;
				}	
			}
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  updateChildUserFunctionDetails method");
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#saveUserDetails(com.centrica.vms.model.UserDetails)
	 */
	public Boolean saveUserDetails(UserDetails userDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering  saveUserDetails method");
		Boolean status = Boolean.FALSE;
		try{
			status = vmsTransactionDAO.insert(userDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException :"+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  saveUserDetails method");
		return status;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#deleteUserDetails(com.centrica.vms.model.UserDetails, java.lang.Boolean)
	 */
	public Boolean deleteUserDetails(UserDetails userDetails,Boolean isPowerUser) throws UserOperationException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering  deleteUserDetails method");
		Boolean status = Boolean.FALSE;
		try{
			if(isPowerUser){
				ArrayList<UserDetails> groupUsers = vmsTransactionDAO.getGroupUsers(userDetails.getGroupID());
				for(UserDetails user:groupUsers){
					Boolean childStatus = vmsTransactionDAO.delete(user);
					if(!childStatus){
						throw new UserOperationException();
					}
				}
			}
			status = vmsTransactionDAO.delete(userDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException :"+ex.getMessage());
		}catch(StaleObjectStateException ex){
			logger.error(Logger.EVENT_FAILURE,"StaleObjectStateException :"+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  deleteUserDetails method");
		return status;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#getUserCredentials(java.lang.String)
	 */
	public UserCredentials getUserCredentials(String userName){
		logger.debug(Logger.EVENT_SUCCESS,"Entering  getUserCredentials method");
		ArrayList<UserCredentials> userCrendentials = vmsTransactionDAO.getUserCrendentials(userName);
		UserCredentials userCredentials = new UserCredentials();
		if(userCrendentials!=null && userCrendentials.size()>0){
			userCredentials = userCrendentials.get(0);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  getUserCredentials method");
		return userCredentials;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#updateUserCredentials(com.centrica.vms.model.UserCredentials)
	 */
	public Boolean updateUserCredentials(UserCredentials userCredentials){
		logger.debug(Logger.EVENT_SUCCESS,"Entering  updateUserCredentials method");
		Boolean status = false;
		try{
			status = vmsTransactionDAO.update(userCredentials);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  updateUserCredentials method");
		return status;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#scheduleHEUtility(com.centrica.vms.form.HEUtilityRequestForm)
	 */
	public Integer scheduleHEUtility(HEUtilityRequestForm heUtilityRequestForm, int deviceType){
		logger.debug(Logger.EVENT_SUCCESS,"Entering  scheduleHEUtility method");
		Integer processStatusCode = vmsSchedulerService.scheduleHEUtility(heUtilityRequestForm.getCount(),
				heUtilityRequestForm.getUserName(), deviceType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  scheduleHEUtility method");
		return processStatusCode;
	}
	
	
	/* (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#scheduleCIMUtility(com.centrica.vms.form.HEUtilityRequestForm)
	 */
	public Integer scheduleCIMUtility(HEUtilityRequestForm heUtilityRequestForm){
		logger.debug(Logger.EVENT_SUCCESS,"Entering  scheduleCIMUtility method");
		Integer processStatusCode = vmsSchedulerService.scheduleCIMUtility(heUtilityRequestForm.getCount(),
				heUtilityRequestForm.getUserName());
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  scheduleCIMUtility method");
		return processStatusCode;
	}
	
	/* Drop 2 - starts here */
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.service.IVMSWebActionService#sendVendRequest(com.centrica.vms.form.VendRequestDetailsForm)
	 */
	public AdjustmentRequestDetailsForm sendAdjustmentRequest(
			AdjustmentRequestDetailsForm adjustmentRequestDetailsForm) { 
		logger.debug(Logger.EVENT_SUCCESS,"Entering  sendAdjustmentRequest method");
		Random random = new Random();
		String transactionID = "VMS"+ random.nextInt(999)+random.nextInt(999)+random.nextInt(999);
		adjustmentRequestDetailsForm.setTransactionID(transactionID);
		CreateVend paymentRequest = preparePaymentRequest((VendRequestDetailsForm)adjustmentRequestDetailsForm);
		CreateVendResponse paymentResponse = vmssapService.generatePaymentCode(paymentRequest);
		populatePaymentResponse((VendRequestDetailsForm)adjustmentRequestDetailsForm, transactionID,
				paymentResponse);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendAdjustmentRequest method");
		return adjustmentRequestDetailsForm;
	}
	/* Drop 2 - ends here */

	
	/**
	 * Method used to validate the user details
	 * @param password
	 * @param vmsTransactionDAO
	 * @param userDetail
	 * @return
	 * @throws UsrPwdExpiredException
	 * @throws UsrLockedException
	 */
	private UserDetails validateUserDetails(String password, UserDetails userDetail)throws UsrPwdExpiredException,UsrLockedException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  validateUserDetails method");
		if(userDetail.getLockIND()==Indicator.N.getIndicator() && password.equals(userDetail.getPassword())){
			logger.info(Logger.EVENT_UNSPECIFIED,"Login details are correct and the user is not locked");
			VMSUtils vmsUtils = new VMSUtils();
			if((vmsUtils.isPasswordExpired(userDetail.getPasswordDate()))){
				logger.info(Logger.EVENT_UNSPECIFIED,"Validity of the password got expired");
				userDetail = null;
				throw new UsrPwdExpiredException();
			}else if(userDetail.getRetryCount()>0){
				userDetail = updateUserCount(userDetail);
			}
			userDetail.setPassDueDate(vmsUtils.passwordDueDate(userDetail.getPasswordDate()));
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"Login details either incorrect or user is locked");
			if(userDetail.getLockIND()==Indicator.N.getIndicator()){
				updateUserDetails(userDetail);
			}else if(userDetail.getLockIND()==Indicator.Y.getIndicator()){
				throw new UsrLockedException();
			}
			userDetail=null;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Entering  validateUserDetails method");
		return userDetail;
	}

	/**
	 * Method to update the user details
	 * @param vmsTransactionDAO
	 * @param userDetail
	 */
	private void updateUserDetails(UserDetails userDetail) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateUserDetails method");
		logger.info(Logger.EVENT_UNSPECIFIED,"updateUserDetails " + userDetail.getRetryCount() );
		if(userDetail.getRetryCount()>=2){
			userDetail.setLockIND(Indicator.Y.getIndicator());
		}
		userDetail.setRetryCount((userDetail.getRetryCount())+1);
		userDetail.setUpdatedTimestamp(new Date());
		userDetail.setUpdatedBy(userDetail.getLanID());
		try{
			vmsTransactionDAO.update(userDetail);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown "+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateUserDetails method");
	}
	
	/**
	 * Method to update the user count
	 * @param vmsTransactionDAO
	 * @param userDetail
	 */
	private UserDetails updateUserCount(UserDetails userDetail){
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateUserCount method");
		userDetail.setRetryCount(0);
		userDetail.setUpdatedTimestamp(new Date());
		userDetail.setUpdatedBy(userDetail.getLanID());
		try{
			 vmsTransactionDAO.update(userDetail);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown "+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateUserCount method");
		return userDetail;
		
	}
	/**
	 * Method used to populate the payment response
	 * @param vendRequestDetailsForm
	 * @param transactionID
	 * @param paymentResponse
	 */
	private void populatePaymentResponse(
			VendRequestDetailsForm vendRequestDetailsForm,
			String transactionID, CreateVendResponse paymentResponse) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering populatePaymentResponse method");
		vendRequestDetailsForm.setDescription(paymentResponse.getPaymentResponse().getVendOutcome().getVendOutcomeDescription());
		vendRequestDetailsForm.setStatusCode(paymentResponse.getPaymentResponse().getVendOutcome().getVendOutcomeCode().getValue());
		vendRequestDetailsForm.setTransactionID(transactionID);
		if(paymentResponse.getPaymentResponse().getVendCode()!=null){
			vendRequestDetailsForm.setVendCode(paymentResponse.getPaymentResponse().getVendCode().getVendCode());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving populatePaymentResponse method");
	}

	/**
	 * Method used to prepare payment request
	 * @param vendRequestDetailsForm
	 * @return
	 */
	private CreateVend preparePaymentRequest(
			VendRequestDetailsForm vendRequestDetailsForm) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering preparePaymentRequest method");
		CreateVend paymentRequest = new CreateVend();
		CreateVendMessage createVendMessage = createVendMessage(vendRequestDetailsForm);
		paymentRequest.setPaymentRequest(createVendMessage);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving preparePaymentRequest method");
		return paymentRequest;
	}

	/**
	 * Method used to create the vend message
	 * @param vendRequestDetailsForm
	 * @return
	 */
	private CreateVendMessage createVendMessage(
			VendRequestDetailsForm vendRequestDetailsForm) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering createVendMessage method");
		CreateVendMessage createVendMessage = new CreateVendMessage();
		setVendRequestAdditional(vendRequestDetailsForm, createVendMessage);
		PanNumber panNumber = new PanNumber();
		panNumber.setPanNumber(vendRequestDetailsForm.getPan());
		createVendMessage.setPanNumber(panNumber);
		setVendAmount(vendRequestDetailsForm, createVendMessage);
		setVendRequestKey(vendRequestDetailsForm, createVendMessage);
		VendRequestTimestamp vendRequestTimestamp = new VendRequestTimestamp();
		setVendRequestTimestamp(vendRequestDetailsForm, createVendMessage,
				vendRequestTimestamp);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createVendMessage method");
		return createVendMessage;
	}

	/**
	 * Method used to set the vend request timestamp
	 * @param vendRequestDetailsForm
	 * @param createVendMessage
	 * @param vendRequestTimestamp
	 */
	private void setVendRequestTimestamp(
			VendRequestDetailsForm vendRequestDetailsForm,
			CreateVendMessage createVendMessage,
			VendRequestTimestamp vendRequestTimestamp) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  setVendRequestTimestamp method");
		Calendar timeStamp= Calendar.getInstance();
		timeStamp.setTime(vendRequestDetailsForm.getDateTime());
		try {
			new VMSUtils().adjustTimeToBST(timeStamp);
		} catch (java.io.IOException ex) {
			logger.error(Logger.EVENT_FAILURE,"IO Exception caught");
		}
		vendRequestTimestamp.setVendRequestDateTime(timeStamp);
		createVendMessage.setVendRequestTimestamp(vendRequestTimestamp);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendRequestTimestamp method");
	}

	/**
	 * Method used to set the vend transaction id
	 * @param vendRequestDetailsForm
	 * @param createVendMessage
	 */
	private void setVendRequestKey(
			VendRequestDetailsForm vendRequestDetailsForm,
			CreateVendMessage createVendMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendRequestKey method");
		VendRequestKey vendRequestKey = new VendRequestKey();
		BG_Identifier identifier = new BG_Identifier();
		Token token = new Token(); 
		token.setValue(vendRequestDetailsForm.getTransactionID()); //Need to modify
		identifier.setBG_IdentifierContent(token);
		vendRequestKey.setVendRequestIdentifier(identifier);
		createVendMessage.setVendRequestKey(vendRequestKey);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendRequestKey method");
	}

	/**
	 * Method used to set the vend amount
	 * @param vendRequestDetailsForm
	 * @param createVendMessage
	 */
	private void setVendAmount(VendRequestDetailsForm vendRequestDetailsForm,
			CreateVendMessage createVendMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendAmount method");
		VendAmount vendAmount = new VendAmount();
		BG_CurrencyAmount currencyAmount = new BG_CurrencyAmount();
		BG_Amount amount = new BG_Amount();
		int creditValue = 0;
		if(vendRequestDetailsForm instanceof AdjustmentRequestDetailsForm){
			creditValue = (int)((new Float(vendRequestDetailsForm.getCreditValue()).floatValue())*100);
		}else{
			creditValue = (new Float(vendRequestDetailsForm.getCreditValue()).intValue())*100;
		}
		amount.setBG_Amount(new BigDecimal(creditValue)); //Amount entered in pounds converted into pence
		currencyAmount.setAmount(amount);
		vendAmount.setVendAmount(currencyAmount);
		createVendMessage.setVendAmount(vendAmount);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendAmount method");
	}

	/**
	 * Method used to set the additional data on the vend request
	 * @param vendRequestDetailsForm
	 * @param createVendMessage
	 */
	private void setVendRequestAdditional(
			VendRequestDetailsForm vendRequestDetailsForm,
			CreateVendMessage createVendMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendRequestAdditional method");
		VendRequestAdditional vendRequestAdditional = new VendRequestAdditional();
		if(vendRequestDetailsForm instanceof AdjustmentRequestDetailsForm){
			vendRequestAdditional.setVendCreditType(VendCreditType_type1.ADJUSTMENT);
			vendRequestAdditional.setVendSource("1-8");
		}else{
			vendRequestAdditional.setVendCreditType(VendCreditType_type1.PURCHASE);
			vendRequestAdditional.setVendSource("1-2");
		}
		vendRequestAdditional.setVendHoldingPeriod(vendRequestDetailsForm.getHoldingPeriod());
		createVendMessage.setVendAdditional(vendRequestAdditional);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendRequestAdditional method");
	}
	
	/**
	 * @return
	 */
	public ArrayList<SourceDetails> getVendSourceList() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendSourceList method");
		ArrayList<SourceDetails> sourceLst = null;
		sourceLst = vendReportTransactionDAO.getVendSourceDetails();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendSourceList method");
		return sourceLst;
	}
	
	/**
	 * @return
	 */
	public ArrayList<StatusDetails> getVendStatusList() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendStatusList method");
		ArrayList<StatusDetails> statusLst = null;
		
		statusLst = vendReportTransactionDAO.getVendStatusDetails();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendStatusList method");
		return statusLst;
	}
	
	/**
	 * Updates Source Details
	 * 
	 * @param sourceDetails - SourceDetails
	 * @return Boolean
	 */
	public Boolean updateSourceDetails(SourceDetails sourceDetails) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering  updateSourceDetails method");
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		Boolean status = false;
		try{
			status = vmsTransactionDAO.update(sourceDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  updateSourceDetails method");
		return status;

	}

}
