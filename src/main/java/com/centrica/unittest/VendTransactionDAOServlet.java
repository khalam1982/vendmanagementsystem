/**
 * VendTransactionDAOServlet.java
 * Purpose: Unit test class for Vend DAO transactions
 *
 * @author ramasap1
 */
package com.centrica.unittest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.DAO.SchedulerTransactionDAO;
import com.centrica.vms.scheduler.model.VendTxnSchedulerDetails;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;

/**
 * Unit test methods for Vend DAO transactions
 * @see HttpServlet
 * 
 */
@SuppressWarnings("serial")
public class VendTransactionDAOServlet extends HttpServlet {

	private SchedulerTransactionDAO schedulerTransactionDAO = new SchedulerTransactionDAO();
	private WSTransactionDAO wstransactionDAO = new WSTransactionDAO();
	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() {
	}

	@Override
	/* The servlet GET method
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws ServletException
	 * @throws IOException
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
				if(method.equals("triggerName")){
					getTriggerName();
				}else if (method.equals("updateStatus")) {
					updateVendTransactionStatus();
				}else if(method.equals("updateVendTransaction")){
					updateVendTransaction();
				}else if(method.equals("getStatus")){
					getSchedulerVendTransactionStatus();
				}else if(method.equals("getUnsentVCCount")){
					getUnsentTransaction();
				}else if(method.equals("getUnsentVendCount")){
					getUnsentVendCount();
				} else if (method.equals("getVendTransactionStatus")) {
					getVendTransactionStatus();
				} else if (method.equals("getUnsentAdjustCount")) {
					getVoidAdjustTransactionCount();
				}
			}else{
				logger.info(Logger.EVENT_UNSPECIFIED,"Array length is not expected");
			}
			
		}catch(IOException ex){
			logger.error(Logger.EVENT_FAILURE,"IO Exception : " + ex.getMessage());
		}catch (Throwable t){
			logger.error(Logger.EVENT_FAILURE,"Caught throwable in VendTransactionDAOServlet"+t.getMessage());
		}
	}
	
	
	/**
	 * 
	 */
	private void getVoidAdjustTransactionCount() {
		int maxRecord = new Integer(new VMSUtils().loadProperties().getProperty("UTILITY_MAXCOUNT")).intValue();
		String statusCodes = new VMSUtils().loadProperties().getProperty("HEUTILITY_STATUSCODES");
		ArrayList<VendTxnWSDetails> list = schedulerTransactionDAO.getUnsentTransactions(maxRecord,statusCodes);
		if(list!=null){
			logger.info(Logger.EVENT_UNSPECIFIED,"List size is"+list.size());
			//assertTrue(true);
		}else{
			//assertTrue(false);
		}
		
		
	}

	/**
	 *  Test case to get the trigger name
	 *  @param
	 *  @return
	 */
	public void getTriggerName(){
		String transactionID = generatePrimaryKey();
		VendTxnSchedulerDetails vendTxnSchedulerDetails = new VendTxnSchedulerDetails();
		VendTxnWSDetails vendTransactionDetails = prepareVendTransactionDetails(transactionID);
		try{
			schedulerTransactionDAO.insert(vendTransactionDetails);
			vendTxnSchedulerDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
			vendTxnSchedulerDetails.setTransactionID(transactionID);
			vendTxnSchedulerDetails.setTriggerName("test");
			schedulerTransactionDAO.update(vendTxnSchedulerDetails);
			vendTxnSchedulerDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
			String triggerName = vendTxnSchedulerDetails.getTriggerName();
			//assertNotNull(triggerName);
			logger.info(Logger.EVENT_UNSPECIFIED,"trigger name" + triggerName);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		
		
	}
	
	/**
	 * Test case to update the status of the transaction
	 * @param
	 * @return
	 */
	public void updateVendTransactionStatus(){
		String transactionID = generatePrimaryKey();
		VendTxnWSDetails vendTransactionDetails = prepareVendTransactionDetails(transactionID);
		try{
			schedulerTransactionDAO.insert(vendTransactionDetails);
			int status = Status.SC_200.getStatus();
			VendTxnSchedulerDetails vendTxnSchedulerDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
			Set<VendTxnStatus> txnStatusDetails = vendTransactionDetails.getTxnStatusDetails();
			VendTxnStatus txnStatusDetail = new VendTxnStatus();
			txnStatusDetail.setStatus(new Integer(status));
			txnStatusDetail.setUpdatedTimeStamp(new Date());
			txnStatusDetails.add(txnStatusDetail);
			vendTransactionDetails.setTxnStatusDetails(txnStatusDetails);
			Boolean updateStatus = schedulerTransactionDAO.update(vendTxnSchedulerDetails);
			//assertTrue(updateStatus);
			logger.info(Logger.EVENT_UNSPECIFIED,"Updated status : " + updateStatus);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		
		
	}
	
	/**
	 * Test case to update the vend transaction
	 * @param
	 * @return
	 */
	public void updateVendTransaction(){
		String transactionID = generatePrimaryKey();
		VendTxnWSDetails vendTxnWSDetails = prepareVendTransactionDetails(transactionID);
		try{
			schedulerTransactionDAO.insert(vendTxnWSDetails);
			VendTxnSchedulerDetails vendTransactionDetails = new VendTxnSchedulerDetails();
			vendTransactionDetails.setTransactionID(vendTxnWSDetails.getTransactionID());
			Set<VendTxnStatus> txnStatusDetails = vendTransactionDetails.getTxnStatusDetails();
			VendTxnStatus txnStatusDetail = new VendTxnStatus();
			txnStatusDetail.setStatus(new Integer(Status.SC_120.getStatus()));
			txnStatusDetail.setUpdatedTimeStamp(new Date());
			txnStatusDetails.add(txnStatusDetail);
			vendTransactionDetails.setTxnStatusDetails(txnStatusDetails);
			vendTransactionDetails.setTriggerName("test");
			schedulerTransactionDAO.update(vendTransactionDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		
		logger.info(Logger.EVENT_UNSPECIFIED,"in the test update vend transaction method");
		
	}
	
	/**
	 * Test case to get the status
	 * @param
	 * @return
	 */
	public void getSchedulerVendTransactionStatus(){
		String transactionID = generatePrimaryKey();
		VendTxnWSDetails vendTxnWSDetails = prepareVendTransactionDetails(transactionID);
		try{
			schedulerTransactionDAO.insert(vendTxnWSDetails);
			VendTxnSchedulerDetails vendTxnSchedulerDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
			int status = vendTxnSchedulerDetails.getTxnStatusDetails().iterator().next().getStatus();
			//assertNotNull(status);
			logger.info(Logger.EVENT_UNSPECIFIED,"Status is:" + status);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		
	}
	
	

	/**
	 * Test case to get the status
	 * @param
	 * @return
	 */
	public void getVendTransactionStatus(){
		String primaryKey = generatePrimaryKey();
		VendTxnWSDetails vendTransactionDetails = prepareVendTransactionDetails(primaryKey);
		try{
			wstransactionDAO.insert(vendTransactionDetails);
			VendTxnWSDetails vendTxnWSDetails = wstransactionDAO.getVendTxnWSDetails(vendTransactionDetails.getTransactionID());
			logger.info(Logger.EVENT_UNSPECIFIED,"Status is "+vendTxnWSDetails.getTxnStatusDetails().iterator().next().getStatus());
			if(vendTxnWSDetails.getTxnStatusDetails().iterator().next().getStatus()==vendTransactionDetails.getTxnStatusDetails().iterator().next().getStatus()){
				//assertTrue(true);
			}else{
				//assertTrue(false);
			}
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
	}
	
	/**
	 * Method to test the get unsent transactions 
	 * @param
	 * @return
	 */
	public void getUnsentTransaction(){
		int maxRecord = new Integer(new VMSUtils().loadProperties().getProperty("UTILITY_MAXCOUNT")).intValue();
		String statusCodes = new VMSUtils().loadProperties().getProperty("HEUTILITY_STATUSCODES");
		ArrayList<VendTxnWSDetails> list = schedulerTransactionDAO.getUnsentTransactions(maxRecord,statusCodes);
		if(list!=null){
			logger.info(Logger.EVENT_UNSPECIFIED,"List size is"+list.size());
			//assertTrue(true);
		}else{
			//assertTrue(false);
		}
		
	}
	
	/**
	 * Method to test the get unsent vend code transactions
	 * @param 
	 * @return 
	 */
	public void getUnsentVendCount(){
		int maxRecord = new Integer(new VMSUtils().loadProperties().getProperty("UTILITY_MAXCOUNT")).intValue();
		ArrayList<VMSMessagingSystem> list = schedulerTransactionDAO.getUnsentMessages(maxRecord, DeviceTypeEnum.PH2B.getDeviceType());
		if(list!=null){
			logger.info(Logger.EVENT_UNSPECIFIED,"List size is"+list.size());
			//assertTrue(true);
		}else{
			//assertTrue(false);
		}
		
	}
	
	/**
	 * Method to generate the primary key 
	 * @param
	 * @return String
	 */
	private String generatePrimaryKey(){
		Random random = new Random();
		int primaryKey = random.nextInt(99999);
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id is " + primaryKey);
		String key = new Integer(primaryKey).toString();
		return key;
	}
	
	
	/**
	 * Method that prepares the vend transaction details
	 * @param String
	 * @return VendTxnWSDetails
	 */
	private VendTxnWSDetails prepareVendTransactionDetails(String primaryKey) {
		VendTxnWSDetails vendTransactionDetails = new VendTxnWSDetails();
		vendTransactionDetails.setCreditValue("10");
		vendTransactionDetails.setHoldingPeriod(100l);
		vendTransactionDetails.setSource("5-99");
		vendTransactionDetails.setPan("test");
		vendTransactionDetails.setPpKey("11112222111122221111222211112222");
		vendTransactionDetails.setOriginalTransactionID("0");
		Set<VendTxnStatus> txnStatusDetails = vendTransactionDetails.getTxnStatusDetails();
		VendTxnStatus txnStatusDetail = new VendTxnStatus();
		txnStatusDetail.setStatus(new Integer(Status.SC_115.getStatus()));
		txnStatusDetail.setUpdatedTimeStamp(new Date());
		txnStatusDetails.add(txnStatusDetail);
		vendTransactionDetails.setTxnStatusDetails(txnStatusDetails);
		vendTransactionDetails.setTransactionID(primaryKey);
		vendTransactionDetails.setTransactionType(TransactionType.GasPurchase.toString());
		vendTransactionDetails.setVendCode("vend");
		return vendTransactionDetails;
	}

}
