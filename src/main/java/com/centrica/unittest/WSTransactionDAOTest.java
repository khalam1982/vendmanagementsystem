/**
 * WSTransactionDAOTest.java
 * Purpose: Unit test class for web service transaction DAO classes
 *
 * @author nagarajk
 */
package com.centrica.unittest;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import junit.framework.TestCase;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.model.StatusDetails;
import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.CreditIDCompKey;
import com.centrica.vms.ws.model.CustomerDetails;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.PremiseDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;

/**
 * Unit test method for web service transaction DAO classes
 * @see TestCase
 */
public class WSTransactionDAOTest extends TestCase {

	private WSTransactionDAO wstransactionDAO;
	private Logger logger;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		 super.setUp();
		 wstransactionDAO = new WSTransactionDAO();
		 PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("properties/log4j.config").getPath());
	     logger = ESAPI.getLogger(getClass().getName());
	     logger.info(Logger.EVENT_UNSPECIFIED,"log configuration is Successful");
	}
	
	/**
	 * Method used to test the get vend history details method
	 */
	public void testGetVendHistory_2(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetVendHistory_2 method");
		ArrayList<VendHistoryDetails> vendHistoryDetails = wstransactionDAO.getVendHistory("9826170633605561530");
		VendHistoryDetails vendHistoryDetail = vendHistoryDetails.get(0);
		Iterator<VendTxnStatus> iterator = vendHistoryDetail.getTxnStatusDetails().iterator();
		logger.info(Logger.EVENT_UNSPECIFIED,"vend code " + vendHistoryDetail.getVendCode());
		logger.info(Logger.EVENT_UNSPECIFIED,"credit value" + vendHistoryDetail.getCreditValue());
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction ID" + vendHistoryDetail.getTransactionID());
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction type" + vendHistoryDetail.getTransactionType());
		logger.info(Logger.EVENT_UNSPECIFIED,"created timestamp" + vendHistoryDetail.getCreatedTimeStamp());
		logger.info(Logger.EVENT_UNSPECIFIED,"actual timestamp" + vendHistoryDetail.getActualTimeStamp());
		logger.info(Logger.EVENT_UNSPECIFIED,"vendcode timestmap" + vendHistoryDetail.getVendTimeStamp());
		logger.info(Logger.EVENT_UNSPECIFIED,"source" + vendHistoryDetail.getSourceDetails().getSource());
		logger.info(Logger.EVENT_UNSPECIFIED,"source description " + vendHistoryDetail.getSourceDetails().getSourceDescription());
		logger.info(Logger.EVENT_UNSPECIFIED,"meter serial number " + vendHistoryDetail.getMsn());
		logger.info(Logger.EVENT_UNSPECIFIED,"Holding period " + vendHistoryDetail.getHoldingPeriod());
		if(iterator!=null){
			while(iterator.hasNext()){
				VendTxnStatus vendTxnStatus = iterator.next();
				logger.info(Logger.EVENT_UNSPECIFIED,""+vendTxnStatus.getUpdatedTimeStamp());
				StatusDetails statusDetails = vendTxnStatus.getStatusDetails();
				if(statusDetails!=null){
					logger.info(Logger.EVENT_UNSPECIFIED,"Status Code : "+statusDetails.getStatusCode());
					logger.info(Logger.EVENT_UNSPECIFIED,"Status Description : "+statusDetails.getStatusDesc());
				}else{
					logger.info(Logger.EVENT_UNSPECIFIED,"statusDetails object is null");
				}
				
			}
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"iterator object is null");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetVendHistory_2 method");
	}
	
	/**
	 * Method used to test the get vend history details method for the purchase types
	 */
	public void testGetVendHistory_purchase(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetVendHistory_purchase method");
		String pan="9826170633605561530";
		Collection<String> transactionTypes = new ArrayList<String>();
		transactionTypes.add(TransactionType.ElectricPurchase.getTransactionType());
		transactionTypes.add(TransactionType.GasPurchase.getTransactionType());
	    int txnCount = 5;
		ArrayList<VendHistoryDetails> vendHistoryDetails = wstransactionDAO.getVendHistory_Purchase(pan, transactionTypes,txnCount);
		VendHistoryDetails vendHistoryDetail = vendHistoryDetails.get(0);
		Iterator<VendTxnStatus> iterator = vendHistoryDetail.getTxnStatusDetails().iterator();
		logger.info(Logger.EVENT_UNSPECIFIED,"vend code " + vendHistoryDetail.getVendCode());
		logger.info(Logger.EVENT_UNSPECIFIED,"credit value" + vendHistoryDetail.getCreditValue());
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction ID" + vendHistoryDetail.getTransactionID());
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction type" + vendHistoryDetail.getTransactionType());
		logger.info(Logger.EVENT_UNSPECIFIED,"created timestamp" + vendHistoryDetail.getCreatedTimeStamp());
		logger.info(Logger.EVENT_UNSPECIFIED,"actual timestamp" + vendHistoryDetail.getActualTimeStamp());
		logger.info(Logger.EVENT_UNSPECIFIED,"vendcode timestmap" + vendHistoryDetail.getVendTimeStamp());
		logger.info(Logger.EVENT_UNSPECIFIED,"source" + vendHistoryDetail.getSourceDetails().getSource());
		logger.info(Logger.EVENT_UNSPECIFIED,"source description " + vendHistoryDetail.getSourceDetails().getSourceDescription());
		logger.info(Logger.EVENT_UNSPECIFIED,"meter serial number " + vendHistoryDetail.getMsn());
		logger.info(Logger.EVENT_UNSPECIFIED,"Holding period " + vendHistoryDetail.getHoldingPeriod());
		if(iterator!=null){
			while(iterator.hasNext()){
				VendTxnStatus vendTxnStatus = iterator.next();
				logger.info(Logger.EVENT_UNSPECIFIED,""+vendTxnStatus.getUpdatedTimeStamp());
				StatusDetails statusDetails = vendTxnStatus.getStatusDetails();
				if(statusDetails!=null){
					logger.info(Logger.EVENT_UNSPECIFIED,"Status Code : "+statusDetails.getStatusCode());
					logger.info(Logger.EVENT_UNSPECIFIED,"Status Description : "+statusDetails.getStatusDesc());
				}else{
					logger.info(Logger.EVENT_UNSPECIFIED,"statusDetails object is null");
				}
				
			}
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"iterator object is null");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetVendHistory_purchase method");
	}
	
	/**
	 * Method used to test the get vend history details method for the adjustment types
	 */
	public void testGetVendHistory_adjustment(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetVendHistory_adjustment method");
		String pan="9826170633605561530";
		Collection<String> transactionTypes = new ArrayList<String>();
		transactionTypes.add(TransactionType.ENegativeAdjustment.getTransactionType());
		transactionTypes.add(TransactionType.EPositiveAdjustment.getTransactionType());
		transactionTypes.add(TransactionType.GNegativeAdjustment.getTransactionType());
		transactionTypes.add(TransactionType.GPositiveAdjustment.getTransactionType());
	    int txnCount = 5;
		ArrayList<VendHistoryDetails> vendHistoryDetails = wstransactionDAO.getVendHistory_Adjustment(pan, transactionTypes, txnCount);
		VendHistoryDetails vendHistoryDetail = vendHistoryDetails.get(0);
		Iterator<VendTxnStatus> iterator = vendHistoryDetail.getTxnStatusDetails().iterator();
		logger.info(Logger.EVENT_UNSPECIFIED,"vend code " + vendHistoryDetail.getVendCode());
		logger.info(Logger.EVENT_UNSPECIFIED,"credit value" + vendHistoryDetail.getCreditValue());
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction ID" + vendHistoryDetail.getTransactionID());
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction type" + vendHistoryDetail.getTransactionType());
		logger.info(Logger.EVENT_UNSPECIFIED,"created timestamp" + vendHistoryDetail.getCreatedTimeStamp());
		logger.info(Logger.EVENT_UNSPECIFIED,"actual timestamp" + vendHistoryDetail.getActualTimeStamp());
		logger.info(Logger.EVENT_UNSPECIFIED,"vendcode timestmap" + vendHistoryDetail.getVendTimeStamp());
		logger.info(Logger.EVENT_UNSPECIFIED,"source" + vendHistoryDetail.getSourceDetails().getSource());
		logger.info(Logger.EVENT_UNSPECIFIED,"source description " + vendHistoryDetail.getSourceDetails().getSourceDescription());
		logger.info(Logger.EVENT_UNSPECIFIED,"meter serial number " + vendHistoryDetail.getMsn());
		logger.info(Logger.EVENT_UNSPECIFIED,"Holding period " + vendHistoryDetail.getHoldingPeriod());
		if(iterator!=null){
			while(iterator.hasNext()){
				VendTxnStatus vendTxnStatus = iterator.next();
				logger.info(Logger.EVENT_UNSPECIFIED,""+vendTxnStatus.getUpdatedTimeStamp());
				StatusDetails statusDetails = vendTxnStatus.getStatusDetails();
				if(statusDetails!=null){
					logger.info(Logger.EVENT_UNSPECIFIED,"Status Code : "+statusDetails.getStatusCode());
					logger.info(Logger.EVENT_UNSPECIFIED,"Status Description : "+statusDetails.getStatusDesc());
				}else{
					logger.info(Logger.EVENT_UNSPECIFIED,"statusDetails object is null");
				}
				
			}
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"iterator object is null");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetVendHistory_adjustment method");
	}
	
	/**
	 * Method used to test the get vend history details method for the adjustment types
	 */
	public void testGetVendHistory_void(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetVendHistory_void method");
		String transactionID="4238";
		Collection<String> transactionTypes = new ArrayList<String>();
		transactionTypes.add(TransactionType.VoidENegativeAdjustment.getTransactionType());
		transactionTypes.add(TransactionType.VoidGNegativeAdjustment.getTransactionType());
		ArrayList<VendHistoryDetails> vendHistoryDetails = wstransactionDAO.getVendHistory_Void(transactionID, transactionTypes);
		if(vendHistoryDetails.size()!=0){
			VendHistoryDetails vendHistoryDetail = vendHistoryDetails.get(0);
			Iterator<VendTxnStatus> iterator = vendHistoryDetail.getTxnStatusDetails().iterator();
			logger.info(Logger.EVENT_UNSPECIFIED,"vend code " + vendHistoryDetail.getVendCode());
			logger.info(Logger.EVENT_UNSPECIFIED,"credit value" + vendHistoryDetail.getCreditValue());
			logger.info(Logger.EVENT_UNSPECIFIED,"Transaction ID" + vendHistoryDetail.getTransactionID());
			logger.info(Logger.EVENT_UNSPECIFIED,"Transaction type" + vendHistoryDetail.getTransactionType());
			logger.info(Logger.EVENT_UNSPECIFIED,"created timestamp" + vendHistoryDetail.getCreatedTimeStamp());
			logger.info(Logger.EVENT_UNSPECIFIED,"actual timestamp" + vendHistoryDetail.getActualTimeStamp());
			logger.info(Logger.EVENT_UNSPECIFIED,"vendcode timestmap" + vendHistoryDetail.getVendTimeStamp());
			logger.info(Logger.EVENT_UNSPECIFIED,"source" + vendHistoryDetail.getSourceDetails().getSource());
			logger.info(Logger.EVENT_UNSPECIFIED,"source description " + vendHistoryDetail.getSourceDetails().getSourceDescription());
			logger.info(Logger.EVENT_UNSPECIFIED,"meter serial number " + vendHistoryDetail.getMsn());
			logger.info(Logger.EVENT_UNSPECIFIED,"Holding period " + vendHistoryDetail.getHoldingPeriod());
			if(iterator!=null){
				while(iterator.hasNext()){
					VendTxnStatus vendTxnStatus = iterator.next();
					logger.info(Logger.EVENT_UNSPECIFIED,""+vendTxnStatus.getUpdatedTimeStamp());
					StatusDetails statusDetails = vendTxnStatus.getStatusDetails();
					if(statusDetails!=null){
						logger.info(Logger.EVENT_UNSPECIFIED,"Status Code : "+statusDetails.getStatusCode());
						logger.info(Logger.EVENT_UNSPECIFIED,"Status Description : "+statusDetails.getStatusDesc());
					}else{
						logger.info(Logger.EVENT_UNSPECIFIED,"statusDetails object is null");
					}
					
				}
			}else{
				logger.info(Logger.EVENT_UNSPECIFIED,"iterator object is null");
			}
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"No records found");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetVendHistory_void method");
	}
	
	/**
	 * test case to test the get credit id method for purchase
	 * @param
	 * @return
	 */
	public void testGetCreditID_Purchase(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetCreditID_Purchase method");
		String creditType = TransactionType.ElectricPurchase.toString();
		String msn = "1411308150000";
		CreditIDCompKey creditIDCompKey = new CreditIDCompKey();
		creditIDCompKey.setMsn(msn);
		creditIDCompKey.setTransactionType(creditType);
		try{
			int creditID= wstransactionDAO.getCreditIDDetails(creditIDCompKey);
			assertNotNull(creditID);
			logger.info(Logger.EVENT_UNSPECIFIED,"Credit id is"+creditID);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetCreditID_Purchase method");
		
	}
	
	/**
	 * test case to test the get credit id method for adjustment
	 * @param
	 * @return
	 */
	public void testGetCreditID_Adjustment(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetCreditID_Adjustment method");
		String creditType = TransactionType.VoidENegativeAdjustment.toString();
		String msn = "1411308150000";
		CreditIDCompKey creditIDCompKey = new CreditIDCompKey();
		creditIDCompKey.setMsn(msn);
		creditIDCompKey.setTransactionType(creditType);
		try{
			int creditID= wstransactionDAO.getCreditIDDetails(creditIDCompKey);
			logger.info(Logger.EVENT_UNSPECIFIED,"Credit id is"+creditID);
			assertNotNull(creditID);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetCreditID_Adjustment method");
	}
	
	
	/**
	 * Test case to test the update delivery status method
	 * @param
	 * @return
	 */
	public void testUpdateDeliveryStatus(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testUpdateDeliveryStatus method");
		String primaryKey = generatePrimaryKey();
		VendTxnWSDetails vendTransactionDetails = prepareVendTransactionDetails(primaryKey);
		try{
			wstransactionDAO.insert(vendTransactionDetails);
			VendTxnStatus txnStatusDetail = new VendTxnStatus();
			txnStatusDetail.setStatus(new Integer(Status.SC_100.getStatus()));
			txnStatusDetail.setUpdatedTimeStamp(new Date());
			Set<VendTxnStatus> txnStatusDetails = new HashSet<VendTxnStatus>();
			txnStatusDetails.add(txnStatusDetail);
			vendTransactionDetails.setTxnStatusDetails(txnStatusDetails);
			Boolean updateStatus = wstransactionDAO.update(vendTransactionDetails);
			assertTrue(updateStatus);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testUpdateDeliveryStatus method");
	}
	
	/**
	 * Test case to test the get vend transaction status method
	 * @param
	 * @return
	 */
	public void testGetVendTransactionStatus() throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetVendTransactionStatus method");
		String methodName = "getVendTransactionStatus";
		sendRequest(methodName);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetVendTransactionStatus method");
	}
	
	/**
	 * Test case to test get holding period method
	 * @param
	 * @return
	 */
	public void testGetHoldingPeriod(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetHoldingPeriod method");
		String source = "5-99"; // Source should be available in the table
		try{
			SourceDetails sourceDetails = wstransactionDAO.getSourceDetails(source);
			logger.info(Logger.EVENT_UNSPECIFIED,"holdingPeriod is "+sourceDetails.getHoldingPeriod());
			assertNotNull(sourceDetails.getHoldingPeriod());
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetHoldingPeriod method");
		
	}
	
	/**
	 * Test case to test the get user status method
	 * @param
	 * @return
	 */
	public void testGetUserStatus(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetUserStatus method");
		String userName = "user1";
		String password = "user1";
		try{
			Boolean userStatus = wstransactionDAO.getUserStatus(userName,password);
			assertTrue(userStatus);
			logger.info(Logger.EVENT_UNSPECIFIED,"User status" + userStatus);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetUserStatus method");
		
	}
	
	/**
	 * Test case to test insert vend transaction details method
	 * @param
	 * @return
	 */
	public void testInsertVendTransactionDetails(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testInsertVendTransactionDetails method");
		String primaryKey = generatePrimaryKey();
		VendTxnWSDetails vendTransactionDetails = prepareVendTransactionDetails(primaryKey);
		try{
			Boolean status = wstransactionDAO.insert(vendTransactionDetails);
			assertTrue(status);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testInsertVendTransactionDetails method");
	}

	/**
	 * Method to generate the primary key 
	 * @param
	 * @return String
	 */
	private String generatePrimaryKey(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering generatePrimaryKey method");
		Random random = new Random();
		int primaryKey = random.nextInt(99999);
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id is " + primaryKey);
		String key = new Integer(primaryKey).toString();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving generatePrimaryKey method");
		return key;
	}
	
	/**
	 * Test case to test the get vend transaction details method
	 * @param
	 * @return 
	 */
	public void testGetVendTransactionDetails(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetVendTransactionDetails method");
		String primaryKey = generatePrimaryKey();
		VendTxnWSDetails vendTransactionDetails = prepareVendTransactionDetails(primaryKey);
		try{
			wstransactionDAO.insert(vendTransactionDetails);
			VendTxnWSDetails vendTransactionDetailsResponse = wstransactionDAO.getVendTxnWSDetails(primaryKey);
			logger.info(Logger.EVENT_UNSPECIFIED,"pan : "+vendTransactionDetails.getPan());
			logger.info(Logger.EVENT_UNSPECIFIED,"transaction type : "+vendTransactionDetails.getTransactionType());
			logger.info(Logger.EVENT_UNSPECIFIED,"holding period : "+vendTransactionDetails.getHoldingPeriod());
			logger.info(Logger.EVENT_UNSPECIFIED,"credit value : "+vendTransactionDetails.getCreditValue());
			if(vendTransactionDetails.getPan().equals(vendTransactionDetailsResponse.getPan())){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
			if(vendTransactionDetails.getTransactionType().equals(vendTransactionDetailsResponse.getTransactionType())){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
			if(vendTransactionDetails.getHoldingPeriod().equals(vendTransactionDetailsResponse.getHoldingPeriod())){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
			if(vendTransactionDetails.getCreditValue().equals(vendTransactionDetailsResponse.getCreditValue())){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetVendTransactionDetails method");
	}
	
	/**
	 * test case for getting the vend transaction details for void request
	 * @param
	 * @return
	 */
	public void testGetVendTxnDetailsForVoid(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetVendTxnDetailsForVoid method");
		String originaltransaction = generatePrimaryKey();
		VendTxnWSDetails vendTransactionDetails1 = prepareVendTransactionDetails(originaltransaction);
		try{
			wstransactionDAO.insert(vendTransactionDetails1);
			String transactionID = generatePrimaryKey();
			VendTxnWSDetails vendTransactionDetails2 = prepareVendTransactionDetails(transactionID);
			vendTransactionDetails2.setOriginalTransactionID(originaltransaction);
			wstransactionDAO.insert(vendTransactionDetails2);
			VendTxnWSDetails vendTransactionDetails3 = wstransactionDAO.getVendTxnWSDetails(originaltransaction);
			if(vendTransactionDetails1.getPan().equals(vendTransactionDetails3.getPan())){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
			if(vendTransactionDetails1.getTransactionType().equals(vendTransactionDetails3.getTransactionType())){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
			if(vendTransactionDetails1.getHoldingPeriod().equals(vendTransactionDetails3.getHoldingPeriod())){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
			if(vendTransactionDetails1.getCreditValue().equals(vendTransactionDetails3.getCreditValue())){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
			
			logger.info(Logger.EVENT_UNSPECIFIED,"pan : "+vendTransactionDetails3.getPan());
			logger.info(Logger.EVENT_UNSPECIFIED,"transaction type : "+vendTransactionDetails3.getTransactionType());
			logger.info(Logger.EVENT_UNSPECIFIED,"holding period : "+vendTransactionDetails3.getHoldingPeriod());
			logger.info(Logger.EVENT_UNSPECIFIED,"credit value : "+vendTransactionDetails3.getCreditValue());
			logger.info(Logger.EVENT_UNSPECIFIED,"pp key : "+vendTransactionDetails3.getPpKey());
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetVendTxnDetailsForVoid method");
		
	}
	
	/**
	 * Method that prepares the vend transaction details
	 * @param String
	 * @return VendTxnWSDetails
	 */
	private VendTxnWSDetails prepareVendTransactionDetails(String primaryKey) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareVendTransactionDetails method");
		VendTxnWSDetails vendTransactionDetails = new VendTxnWSDetails();
		vendTransactionDetails.setCreditValue("10");
		vendTransactionDetails.setHoldingPeriod(100l);
		vendTransactionDetails.setSource("0-0");
		vendTransactionDetails.setPan("test");
		vendTransactionDetails.setMsn("msn");
		vendTransactionDetails.setPpKey("11112222111122221111222211112222");
		vendTransactionDetails.setOriginalTransactionID("0");
		VendTxnStatus txnStatusDetail = new VendTxnStatus();
		txnStatusDetail.setStatus(new Integer(Status.SC_115.getStatus()));
		txnStatusDetail.setUpdatedTimeStamp(new Date());
		Set<VendTxnStatus> txnStatusDetails = new HashSet<VendTxnStatus>();
		txnStatusDetails.add(txnStatusDetail);
		vendTransactionDetails.setTxnStatusDetails(txnStatusDetails);
		vendTransactionDetails.setTransactionID(primaryKey);
		vendTransactionDetails.setTransactionType(TransactionType.GasPurchase.toString());
		vendTransactionDetails.setVendCode("vend");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareVendTransactionDetails method");
		return vendTransactionDetails;
	}

	/**
	 * Make sure the entry for the pan is there in the table
	 * @param
	 * @return
	 */
	public void testGetCustomerDetails_Electric(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetCustomerDetails_Electric method");
		String pan = "9826170633605553362";
		try{
			CustomerDetails customerDetails = wstransactionDAO.getCustomerDetails(pan);
			assertNotNull(customerDetails.getPan());
			logger.info(Logger.EVENT_UNSPECIFIED,"MPxN "+customerDetails.getMpxn());
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetCustomerDetails_Electric method");
	}
	
	/**
	 * Make sure the entry for the MSN is there in the table
	 * @param
	 * @return
	 */
	public void testGetMeterDetails_Electric(){
		String msn = "Z12N019606";
		try{
			MeterDetails meterDetails = wstransactionDAO.getMeterDetails(msn);
			assertNotNull(meterDetails.getPrepayKey());
			logger.info(Logger.EVENT_UNSPECIFIED,"PP KEY "+meterDetails.getPrepayKey());
			logger.info(Logger.EVENT_UNSPECIFIED,"FUEL TYPE "+meterDetails.getFuelTypeID());
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		
	}
	
	/**
	 * Make sure the entry for the MPxN is there in the table
	 * @param
	 * @return
	 */
	public void testGetPremiseDetails_Electric(){
		String mpxn = "1610013181712";
		try{
			PremiseDetails premiseDetails = wstransactionDAO.getPremiseDetails(mpxn);
			assertNotNull(premiseDetails.getMSN());
			logger.info(Logger.EVENT_UNSPECIFIED,"MSN "+premiseDetails.getMSN());
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
	}
	
	/**
	 * Make sure the entry for the pan is there in the table
	 * @param
	 * @return
	 */
	public void testGetCustomerDetails_Gas(){
		String pan = "9826170633605597864";
		try{
			CustomerDetails customerDetails = wstransactionDAO.getCustomerDetails(pan);
			assertNotNull(customerDetails.getMpxn());
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		
	}
	
	/**
	 * Make sure the entry for the MSN is there in the table
	 * @param
	 * @return
	 */
	public void testGetMeterDetails_Gas(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetMeterDetails_Gas method");
		String msn = "E6S00469231255";
		try{
			MeterDetails meterDetails = wstransactionDAO.getMeterDetails(msn);
			assertNotNull(meterDetails.getPrepayKey());
			logger.info(Logger.EVENT_UNSPECIFIED,"FUEL TYPE "+meterDetails.getFuelTypeID());
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetMeterDetails_Gas method");
	}
 	
	/**
	 * Make sure the entry for the MPxN is there in the table
	 * @param
	 * @return
	 */
	public void testGetPremiseDetails_Gas(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetPremiseDetails_Gas method");
		String mpxn = "1411308150000";
		try{
			PremiseDetails premiseDetails = wstransactionDAO.getPremiseDetails(mpxn);
			assertNotNull(premiseDetails.getMSN());
			logger.info(Logger.EVENT_UNSPECIFIED,"MSN "+premiseDetails.getMSN());
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetPremiseDetails_Gas method");
	}
	
	/**
	 * Test the method to retrieve the list of vend history objects
	 * @param
	 * @return
	 */
	public void testGetVendHistory(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetVendHistory method");
		String transactionID = generatePrimaryKey();
		VendTxnWSDetails vendTransactionDetails = prepareVendTransactionDetails(transactionID);
		try{
			wstransactionDAO.insert(vendTransactionDetails);
			VMSTransactionDAO vmstransactionDAO = new VMSTransactionDAO();
			ArrayList<VendHistoryDetails> arrayList = vmstransactionDAO.getVendHistory(vendTransactionDetails.getPan());
			if(arrayList!=null && arrayList.size()!=0){
				assertTrue(true);
				for(VendHistoryDetails vendHistoryDetails:arrayList){
					logger.info(Logger.EVENT_UNSPECIFIED,"transactionID " + vendHistoryDetails.getTransactionID());
					logger.info(Logger.EVENT_UNSPECIFIED,"value " + vendHistoryDetails.getCreditValue());
				}
			}else{
				assertTrue(false);
			}
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown ");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetVendHistory method");
		
	}
	
	/* 
	 * Test case for get the vend timestamp
	 * @param
	 * @return
	 */
	public void testGetVendTimeStamp(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering testGetVendTimeStamp method");
		//String pan="1234567890123456789";
		//wstransactionDAO.getVendTimeStamp(pan, creditValue, actualTimestamp);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving testGetVendTimeStamp method");
	}
	
	/**
	 * Method to send the request
	 * @param String
	 * @return 
	 */
	private void sendRequest(String methodName) throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendRequest method");
		HttpURLConnection httpURLConnection = null;
		OutputStreamWriter wr = null;
		String message = methodName;
		try{
			URL u = new URL ("http://localhost:8080/VendManagementSystem/VendTransactionDAOTest");
			httpURLConnection = (HttpURLConnection)u.openConnection();
			if(httpURLConnection != null){
				httpURLConnection.setRequestMethod("GET");
				httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
				httpURLConnection.setRequestProperty( "Content-Length", new Integer (message.length()).toString());
				httpURLConnection.setDoOutput(true);
				wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
				if(wr != null){
					wr.write(message);
					wr.flush();
				}			
				if(HttpURLConnection.HTTP_OK ==httpURLConnection.getResponseCode() ){
					assertTrue(true);
				}else{
					assertTrue(false);
				}
			}		
		}catch(IOException ex){
		   logger.error(Logger.EVENT_FAILURE,"IO Exception");
		} finally {
			if(httpURLConnection != null) httpURLConnection.disconnect();
			if(wr != null) wr.close();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendRequest method");
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		wstransactionDAO = null;
		super.tearDown();
	}

}
