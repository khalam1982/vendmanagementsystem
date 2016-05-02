/**
 * SchedulerTestServlet.java
 * Purpose: Unit test class for scheduler implementation
 *
 * @author ramasap1
 */
package com.centrica.unittest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;


/**
 * Unit test method for scheduler implementations 
 * @see HttpServlet
 * @see Logger
 */
@SuppressWarnings("serial")
public class SchedulerTestServlet extends HttpServlet {

	private Logger logger = ESAPI.getLogger(this.getClass());
	private static final String VMS_USERNAME_VALUE="system";
	
	@Override
	/*
	 * The Servlet GET method
	 * @param  HttpServletRequest
	 * @param HttpServletResponse
	 * @return 
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ServletInputStream servletInputStream = req.getInputStream();
		logger.info(Logger.EVENT_UNSPECIFIED,"In do get method");
		String transactionID;
		String holdingPeriod;
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
			if(array.length==3){
				method  = array[0];
				transactionID = array[1];
				holdingPeriod = array[2];
				if(method.equals("schedule")){
					invokeScheduleService(transactionID,holdingPeriod);
				}else if(method.equals("scheduleHEPhase2bUtility")){
					invokeScheduleHEPhase2bUtility(transactionID,holdingPeriod);
				}else if(method.equals("scheduleHEPhase3Utility")){
					invokeScheduleHEPhase3Utility(transactionID,holdingPeriod);
				} else if (method.equals("scheduleAdjust")) {
					invokeScheduleCIMUtility(transactionID, holdingPeriod);
				}else{
					invokeUnScheduleService(transactionID,holdingPeriod);
				}
				
			}else{
				logger.info(Logger.EVENT_UNSPECIFIED,"Array length is not expected");
			}
			
		}catch(IOException ex){
			logger.error(Logger.EVENT_FAILURE,"IO Exception : " + ex.getMessage());
		}catch (Throwable t){
			logger.error(Logger.EVENT_FAILURE,"Caught throwable in SchedulerTestServlet"+t.getMessage());
		}
		
	}

	/**
	 * @param transactionID
	 * @param holdingPeriod
	 */
	private void invokeScheduleCIMUtility(String transactionID,
			String holdingPeriod) {
		VMSSchedulerServiceImpl vmsSchedulerServiceImpl = new VMSSchedulerServiceImpl();
		int retryCount=0;
		Boolean status = false;
		try {
			status = vmsSchedulerServiceImpl.scheduleAdjustJob(retryCount, transactionID, Long.getLong(holdingPeriod), 
					"mpxn", "1000", Calendar.getInstance().getTime(), false);
		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"DBException::" + e.getMessage());
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"Schedule Status : " + status);
	}

	@Override
	/*
	 * The Servlet POST method
	 * @param  HttpServletRequest
	 * @param HttpServletResponse
	 * @return 
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.info(Logger.EVENT_UNSPECIFIED,"In do post method");
		doGet(req, resp);
	}

	/**
	 * Method to schedule the job request
	 * @param String
	 * @param String
	 * @return 
	 */
	private void invokeScheduleService(String transactionID,String holdingPeriod) {
		logger.info(Logger.EVENT_UNSPECIFIED,"In invokeScheduleService");
		WSTransactionDAO wstransactionDAO = new WSTransactionDAO();
		VendTxnWSDetails vendTransactionDetails = prepareVendTransactionDetails(transactionID,
				Long.valueOf(holdingPeriod),Status.SC_115.getStatus());
		try{
			wstransactionDAO.insert(vendTransactionDetails);
		} catch (DBException e) {
			 logger.error(Logger.EVENT_FAILURE,"DB Exception");
		}
		VMSSchedulerServiceImpl vmsSchedulerServiceImpl = new VMSSchedulerServiceImpl();
		Boolean status = false;
		try{
			int retryCount=0;
			status = vmsSchedulerServiceImpl.scheduleJob(retryCount,transactionID,
					new Long(holdingPeriod), "pan", "vendcode",
					TransactionType.ElectricPurchase.toString(),"1000",new Date(),DeviceTypeEnum.PH2B.getDeviceType(), false);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		
		logger.info(Logger.EVENT_UNSPECIFIED,"Schedule Status : " + status);

	}
	
	/**
	 * Method that prepares the vend transaction details
	 * @param String
	 * @param Long
	 * @param int
	 * @return VendTxnWSDetails
	 */
	private VendTxnWSDetails prepareVendTransactionDetails(String primaryKey,Long holdingPeriod,int status) {
		VendTxnWSDetails vendTransactionDetails = new VendTxnWSDetails();
		vendTransactionDetails.setCreditValue("10");
		vendTransactionDetails.setHoldingPeriod(holdingPeriod);
		vendTransactionDetails.setSource("5-99");
		vendTransactionDetails.setPan("pan");
		vendTransactionDetails.setPpKey("11112222111122221111222211112222");
		vendTransactionDetails.setOriginalTransactionID("0");
		Set<VendTxnStatus> txnStatusDetails = vendTransactionDetails.getTxnStatusDetails();
		VendTxnStatus txnStatusDetail = new VendTxnStatus();
		txnStatusDetail.setStatus(new Integer(Status.SC_100.getStatus()));
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
		return vendTransactionDetails;
	}
	
	/**
	 * Method to test the get unsent transactions 
	 * @param
	 * @return int
	 */
	public int getUnsentVendCount(){
		int count = 0;
		int maxRecord = new Integer(new VMSUtils().loadProperties().getProperty("UTILITY_MAXCOUNT")).intValue();
		ArrayList<VMSMessagingSystem> list = new SchedulerTransactionDAO().getUnsentMessages(maxRecord, DeviceTypeEnum.PH2B.getDeviceType());
		if(list!=null){
			count = list.size();
			logger.info(Logger.EVENT_UNSPECIFIED,"List size is"+count);
		}
		return count;
	}
	
	/**
	 * Method to unschedule the job request
	 * @param String
	 * @param String
	 * @return 
	 */
	private void invokeUnScheduleService(String transactionID,String holdingPeriod){
		logger.info(Logger.EVENT_UNSPECIFIED,"In invokeUnScheduleService");
		WSTransactionDAO wstransactionDAO = new WSTransactionDAO();
		VendTxnWSDetails vendTransactionDetails = prepareVendTransactionDetails(transactionID,
				Long.valueOf(holdingPeriod),Status.SC_115.getStatus());
		try{
			wstransactionDAO.insert(vendTransactionDetails);
		} catch (DBException e) {
			 logger.error(Logger.EVENT_FAILURE,"DB Exception");
		}
		VMSSchedulerServiceImpl vmsSchedulerServiceImpl = new VMSSchedulerServiceImpl();
		Boolean status = false;
		try{
			int retryCount=0;
			    status = vmsSchedulerServiceImpl.scheduleJob(retryCount,transactionID,
					new Long(holdingPeriod), "pan", "vendcode",
					TransactionType.ElectricPurchase.toString(),"1000",new Date(),DeviceTypeEnum.PH2B.getDeviceType(), false);
			    logger.info(Logger.EVENT_UNSPECIFIED,"Schedule Status : " + status);
			    status = vmsSchedulerServiceImpl.unScheduleJob(transactionID,Status.SC_135);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"UnSchedule Status : " +status);
	}

	/**
	 * Method to invoke the schedule HE utility for Phase 2b
	 * @param String
	 * @param String
	 * @return 
	 */
	private void invokeScheduleHEPhase2bUtility(String count,String username){
		logger.info(Logger.EVENT_UNSPECIFIED,"In invokeScheduleHEPhase2bUtility");
		VMSSchedulerServiceImpl vmsSchedulerServiceImpl = new VMSSchedulerServiceImpl();
		int status = vmsSchedulerServiceImpl.scheduleHEUtility(count, username, DeviceTypeEnum.PH2B.getDeviceType());
		logger.info(Logger.EVENT_UNSPECIFIED,"status is "+status);
		logger.info(Logger.EVENT_UNSPECIFIED,"In invokeScheduleHEPhase2bUtility");
		
	}
	
	/**
	 * Method to invoke the schedule HE utility for Phase 3
	 * @param String
	 * @param String
	 * @return 
	 */
	private void invokeScheduleHEPhase3Utility(String count,String username){
		logger.info(Logger.EVENT_UNSPECIFIED,"In invokeScheduleHEPhase3Utility");
		VMSSchedulerServiceImpl vmsSchedulerServiceImpl = new VMSSchedulerServiceImpl();
		int status = vmsSchedulerServiceImpl.scheduleHEUtility(count, username, DeviceTypeEnum.PH3.getDeviceType());
		logger.info(Logger.EVENT_UNSPECIFIED,"status is "+status);
		logger.info(Logger.EVENT_UNSPECIFIED,"In invokeScheduleHEPhase3Utility");
		
	}
}
