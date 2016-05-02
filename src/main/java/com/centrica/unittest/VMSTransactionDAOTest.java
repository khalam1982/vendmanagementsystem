/**
 * VMSTransactionDAOTest.java
 * Purpose: Unit test class for VMS transaction DAO implementation
 *
 * @author ramasap1
 */
package com.centrica.unittest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import junit.framework.TestCase;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.FunctionDetails;
import com.centrica.vms.model.GroupDetails;
import com.centrica.vms.model.UserCredentials;
import com.centrica.vms.model.UserDetails;
import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.DAO.SchedulerTransactionDAO;
import com.centrica.vms.scheduler.model.VendTxnSchedulerDetails;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;

/**
 * Unit test method for VMS Transaction DAO implementations 
 * @see TestCase
 */
public class VMSTransactionDAOTest extends TestCase {
	
	private Logger logger = ESAPI.getLogger(this.getClass());
	private static final String VMS_USERNAME_VALUE="system";
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		 super.setUp();
		 PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("properties/log4j.config").getPath());
	     logger = ESAPI.getLogger(getClass().getName());
	     logger.info(Logger.EVENT_UNSPECIFIED,"log configuration is Successful");
	}
	
	
	/**
	 * Method used to test the hibernate insert operation on the vend transaction table
	 */
	public void testInsert_VendDTxn(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testInsert_VendDTxn method");
		String transactionID = this.generateTransactionID();
		int status = 125;
		VendTxnWSDetails vendTxnWSDetails = prepareVendTransactionDetails(transactionID,status);
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		try{
			vmsTransactionDAO.insert(vendTxnWSDetails);
		}catch(DBException ex){
			assertTrue(false);
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testInsert_VendDTxn method");
	}
	
	/**
	 * Method used to test the hibernate update operation on the vend transaction table using vend transaction object
	 */
	public void testUpdate_VendTxn(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testUpdate_VendTxn method");
		String transactionID = this.generateTransactionID();
		int status = 125;
		VendTxnWSDetails vendTxnWSDetails = prepareVendTransactionDetails(transactionID,status);
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		WSTransactionDAO wsTransactionDAO = new WSTransactionDAO();
		try{
			vmsTransactionDAO.insert(vendTxnWSDetails);
			vendTxnWSDetails = wsTransactionDAO.getVendTxnWSDetails(transactionID);
			vendTxnWSDetails.setUpdatedBy("second run");
			Set<VendTxnStatus> txnStatusDetails = vendTxnWSDetails.getTxnStatusDetails();
			VendTxnStatus txnStatusDetail = new VendTxnStatus();
			txnStatusDetail.setStatus(new Integer(Status.SC_100.getStatus()));
			txnStatusDetail.setUpdatedTimeStamp(new Date());
			txnStatusDetails.add(txnStatusDetail);
			vendTxnWSDetails.setTxnStatusDetails(txnStatusDetails);
			vmsTransactionDAO.update(vendTxnWSDetails);
			
		}catch(DBException ex){
			assertTrue(false);
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testUpdate_VendTxn method");
	}
	
	/**
	 * Method used to test the hibernate update operation on the vend transaction table using the vend scheduler object
	 */
	public void testUpdate_VendSTxn(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testUpdate_VendSTxn method");
		String transactionID = this.generateTransactionID();
		int status = 125;
		VendTxnWSDetails vendTxnWSDetails = prepareVendTransactionDetails(transactionID,status);
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		SchedulerTransactionDAO schedulerTransactionDAO = new SchedulerTransactionDAO();
		try{
			vmsTransactionDAO.insert(vendTxnWSDetails);
			VendTxnSchedulerDetails VendTxnSchedulerDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
			VendTxnSchedulerDetails.setUpdatedBy("second run");
			Set<VendTxnStatus> txnStatusDetails = VendTxnSchedulerDetails.getTxnStatusDetails();
			VendTxnStatus txnStatusDetail = new VendTxnStatus();
			txnStatusDetail.setStatus(new Integer(Status.SC_100.getStatus()));
			txnStatusDetail.setUpdatedTimeStamp(new Date());
			txnStatusDetails.add(txnStatusDetail);
			VendTxnSchedulerDetails.setTxnStatusDetails(txnStatusDetails);
			vmsTransactionDAO.update(VendTxnSchedulerDetails);
			
		}catch(DBException ex){
			assertTrue(false);
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testUpdate_VendSTxn method");
	}
	
	
	/**
	 * Method used to test the hibernate update twice on the vend transaction table using the vend scheduler object
	 */
	public void testUpdate_VendSTxn_2(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testUpdate_VendSTxn_2 method");
		String transactionID = this.generateTransactionID();
		int status = 125;
		VendTxnWSDetails vendTxnWSDetails = prepareVendTransactionDetails(transactionID,status);
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		SchedulerTransactionDAO schedulerTransactionDAO = new SchedulerTransactionDAO();
		try{
			vmsTransactionDAO.insert(vendTxnWSDetails);
			VendTxnSchedulerDetails VendTxnSchedulerDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
			VendTxnSchedulerDetails.setUpdatedBy("second run");
			Set<VendTxnStatus> txnStatusDetails = VendTxnSchedulerDetails.getTxnStatusDetails();
			VendTxnStatus txnStatusDetail = new VendTxnStatus();
			txnStatusDetail.setStatus(new Integer(Status.SC_100.getStatus()));
			txnStatusDetail.setUpdatedTimeStamp(new Date());
			txnStatusDetails.add(txnStatusDetail);
			VendTxnSchedulerDetails.setTxnStatusDetails(txnStatusDetails);
			vmsTransactionDAO.update(VendTxnSchedulerDetails);
			VendTxnSchedulerDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
			VendTxnSchedulerDetails.setUpdatedBy("third run");
			txnStatusDetails = VendTxnSchedulerDetails.getTxnStatusDetails();
			txnStatusDetail = new VendTxnStatus();
			txnStatusDetail.setStatus(new Integer(Status.SC_300.getStatus()));
			txnStatusDetail.setUpdatedTimeStamp(new Date());
			txnStatusDetails.add(txnStatusDetail);
			VendTxnSchedulerDetails.setTxnStatusDetails(txnStatusDetails);
			vmsTransactionDAO.update(VendTxnSchedulerDetails);
			
		}catch(DBException ex){
			assertTrue(false);
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testUpdate_VendSTxn_2 method");
	}
	
	
	/**
	 * Method to get the unsent vend codes count for Phase 2
	 * @param 
	 * @return 
	 */
	public void testGetResendVendCodesPhase2Count(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetResendVendCodesPhase2Count method");
		String transactionID = this.generateTransactionID();
		int status = Status.SC_125.getStatus(); //Please check this value in vms properties file 
		VendTxnWSDetails vendTxnWSDetails = prepareVendTransactionDetails(transactionID,status);
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		try{
			vmsTransactionDAO.insert(vendTxnWSDetails);
			Integer unsentMessages = vmsTransactionDAO.getResendTransactionCount(DeviceTypeEnum.PH2B.getDeviceType());
			assertNotSame(0,unsentMessages);
			logger.info(Logger.EVENT_UNSPECIFIED,"unsentMessages size is " + unsentMessages);
		}catch(DBException ex){
			assertTrue(false);
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetResendVendCodesPhase2Count method");
	}
	
	/**
	 * Method to get the unsent vend codes count for Phase 3
	 * @param 
	 * @return 
	 */
	public void testGetResendVendCodesPhase3Count(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetResendVendCodesPhase3Count method");
		String transactionID = this.generateTransactionID();
		int status = Status.SC_125.getStatus(); //Please check this value in vms properties file 
		VendTxnWSDetails vendTxnWSDetails = prepareVendTransactionDetails(transactionID,status);
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		try{
			vmsTransactionDAO.insert(vendTxnWSDetails);
			Integer unsentMessages = vmsTransactionDAO.getResendTransactionCount(DeviceTypeEnum.PH3.getDeviceType());
			assertNotSame(0,unsentMessages);
			logger.info(Logger.EVENT_UNSPECIFIED,"unsentMessages size is " + unsentMessages);
		}catch(DBException ex){
			assertTrue(false);
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetResendVendCodesPhase3Count method");
	}
	
	/**
	 * Method to get the unsent vend codes count for Adjustment
	 * @param 
	 * @return 
	 */
	public void testGetResendVendCodesAdjustmentCount(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetResendVendCodesAdjustmentCount method");
		String transactionID = this.generateTransactionID();
		int status = Status.SC_125.getStatus(); //Please check this value in vms properties file 
		VendTxnWSDetails vendTxnWSDetails = prepareVendTransactionDetails(transactionID,status);
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		try{
			vmsTransactionDAO.insert(vendTxnWSDetails);
			Integer unsentMessages = vmsTransactionDAO.getResendTransactionCount(DeviceTypeEnum.CIM.getDeviceType());
			assertNotSame(0,unsentMessages);
			logger.info(Logger.EVENT_UNSPECIFIED,"unsentMessages size is " + unsentMessages);
		}catch(DBException ex){
			assertTrue(false);
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetResendVendCodesAdjustmentCount method");
	}
	
	/**
	 * Method to generate the transaction id
	 * @param
	 * @return String
	 */
	private String generateTransactionID(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering generateTransactionID method");
		Random random = new Random();
		int transactionID = random.nextInt(9999);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving generateTransactionID method");
		return new Integer(transactionID).toString();
	}
	
	/**
	 * Method that prepares the vend transaction details
	 * @param String
	 * @param int
	 * @return VendTxnWSDetails
	 */
	private VendTxnWSDetails prepareVendTransactionDetails(String primaryKey,int status) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareVendTransactionDetails method");
		VendTxnWSDetails vendTransactionDetails = new VendTxnWSDetails();
		vendTransactionDetails.setCreditValue("10");
		vendTransactionDetails.setHoldingPeriod(60l);
		vendTransactionDetails.setSource("5-99");
		vendTransactionDetails.setPan("pan");
		vendTransactionDetails.setPpKey("11112222111122221111222211112222");
		vendTransactionDetails.setOriginalTransactionID("0");
		VendTxnStatus txnStatusDetail = new VendTxnStatus();
		txnStatusDetail.setStatus(new Integer(Status.SC_120.getStatus()));
		txnStatusDetail.setUpdatedTimeStamp(new Date());
		
		Set<VendTxnStatus> txnStatusDetails = new HashSet<VendTxnStatus>();
		txnStatusDetails.add(txnStatusDetail);
		txnStatusDetail = new VendTxnStatus();
		txnStatusDetail.setStatus(new Integer(Status.SC_200.getStatus()));
		txnStatusDetail.setUpdatedTimeStamp(new Date());
		txnStatusDetails.add(txnStatusDetail);
		vendTransactionDetails.setTxnStatusDetails(txnStatusDetails);
		vendTransactionDetails.setTransactionID(primaryKey);
		vendTransactionDetails.setTransactionType(TransactionType.ElectricPurchase.toString());
		vendTransactionDetails.setVendCode("vendcode");
		vendTransactionDetails.setActualTimeStamp(new Date());
		vendTransactionDetails.setCreatedTimeStamp(new Date());
		vendTransactionDetails.setVendcodeTimeStamp(new Date());
		vendTransactionDetails.setUpdatedBy(VMS_USERNAME_VALUE);
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareVendTransactionDetails method");
		return vendTransactionDetails;
	}
	
	/**
	 * Test case to check for get vend history method;
	 * Transaction for the given PAN number should exist.
	 * @param 
	 * @return
	 */
	public void testGetVendHistory(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetVendHistory method");
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		String pan = "1000000000000000001";
		ArrayList<VendHistoryDetails> vendHistoryDetails = vmsTransactionDAO.getVendHistory(pan);
		if(vendHistoryDetails!=null && vendHistoryDetails.size()>0){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetVendHistory method");
	}
	
	/**
	 * Test case to check for get vend history method;
	 * Given user should exist.
	 * @param
	 * @return
	 */
	public void testGetUserDetails(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetUserDetails method");
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		String userName = "superuser";
		ArrayList<UserDetails> userDetails = vmsTransactionDAO.getUserDetails(userName);
		if(userDetails!=null && userDetails.size()>0){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetUserDetails method");
	}
	
	/**
	 * Test case to check for get vend history method;
	 * Given power user should exist for that group id.
	 * @param
	 * @return
	 */
	public void testGetPowerUserDetails(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetPowerUserDetails method");
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		String groupID = "G2";
		ArrayList<UserDetails> userDetails = vmsTransactionDAO.getPowerUserDetails(groupID);
		if(userDetails!=null && userDetails.size()>0){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetPowerUserDetails method");
	}
	
	/**
	 * Test case to check for get vend history method;
	 * Given user should exist for that group id.
	 * @param
	 * @return
	 */
	public void testGetGroupUsers(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetGroupUsers method");
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		String groupID = "G2";
		ArrayList<UserDetails> userDetails = vmsTransactionDAO.getGroupUsers(groupID);
		if(userDetails!=null && userDetails.size()>0){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetGroupUsers method");
	}
	
	/**
	 * Test case to check for get vend history method;
	 * Groups should exist.
	 * @param
	 * @return
	 */
	public void testGetGroupDetails(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetGroupDetails method");
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		String groupID = "G1";
		ArrayList<GroupDetails> groupDetails = vmsTransactionDAO.getGroupDetails(groupID);
		if(groupDetails!=null && groupDetails.size()>0){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetGroupDetails method");
	}
	
	/**
	 * Test case to check for get vend history method;
	 * Given function should exist.
	 * @param
	 * @return
	 */
	public void testGetFunctionDetails(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetFunctionDetails method");
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		Collection<String> functions = new ArrayList<String>(); 
		functions.add("00");
		functions.add("01");
		ArrayList<FunctionDetails> functionDetails = vmsTransactionDAO.getFunctionDetails(functions);
		if(functionDetails!=null && functionDetails.size()>0){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetFunctionDetails method");
	}
	
	/**
	 * Test case to check for get vend history method;
	 * Given user should exist.
	 * @param
	 * @return
	 */
	public void testGetUserCrendentials(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetUserCrendentials method");
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		String userName = "superuser";
		ArrayList<UserCredentials> userCredentials = vmsTransactionDAO.getUserCrendentials(userName);
		if(userCredentials!=null && userCredentials.size()>0){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetUserCrendentials method");
	}
	
	/*
	 * Method for preparing the VMS Messaging system details
	 * @param
	 * @return
	 */
	/*private VMSMessagingSystem prepareVMSMessagingSystemDetails() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareVMSMessagingSystemDetails method");
		VMSMessagingSystem messagingSystem = new VMSMessagingSystem();
		messagingSystem.setMessageData(message);
		messagingSystem.setDeviceTypeID(DeviceTypeEnum.PH2B.getDeviceType());
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareVMSMessagingSystemDetails method");
		return messagingSystem;
	}*/
}
