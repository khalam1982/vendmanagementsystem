package com.centrica.unittest;

import java.math.BigInteger;

import junit.framework.TestCase;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.log4j.PropertyConfigurator;

import smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn;
import smartmetervendhistory.enterprise.uk.co.britishgas.VendHistoryRequestMessage;
import vhcommon.enterprise.uk.co.britishgas.IntegerType;
import vhcommon.enterprise.uk.co.britishgas.StringType;

import com.centrica.vms.vh.ws.sap.service.GetVendHistory;
import com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse;
import com.centrica.vms.ws.service.VMSVHService;

public class VMSVHServiceTest extends TestCase {
	
private Logger logger = null;
	
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
	 * Method used to get the vend history
	 */
	public void tesGetVendHistory(){
		VMSVHService vmsVHService = new VMSVHService();
		VendHistoryRequestMessage vendHistoryRequest = new VendHistoryRequestMessage();
		StringType stringType = new StringType();
		stringType.setStringType("9826170733605597953");
		vendHistoryRequest.setPan(stringType);
		IntegerType integerType = new IntegerType();
		integerType.setIntegerType(new BigInteger("1"));
		vendHistoryRequest.setNoOfTxns(integerType);
		GetVendHistory getVendHistory = new GetVendHistory();
		getVendHistory.setVendHistoryRequest(vendHistoryRequest);
		GetVendHistoryResponse getVendHistoryResponse = vmsVHService.getVendHistory(getVendHistory);
		Purchase_Txn[] purchase_Txn =  getVendHistoryResponse.getVendHistoryResponse().getCreditPurchase();
		if(purchase_Txn!=null && purchase_Txn.length!=0){
			int txnLength = purchase_Txn.length; 
			int txnNo = 0;
			while(txnLength!=0){
				txnLength--;
				logger.info(Logger.EVENT_UNSPECIFIED,"Transaction ID:" + purchase_Txn[txnNo].getTxnDetails().getTransactionID());
				logger.info(Logger.EVENT_UNSPECIFIED,"Transaction ID:" + purchase_Txn[txnNo].getTxnDetails().getTxnOutcome()[0].getStatus().getStatusCode());
				txnNo++;
			}
			
		}
		
	} 
	
	/**
	 * Method used to get the vend history with the masked vend code
	 */
	public void testGetVendHistory_mask(){
		VMSVHService vmsVHService = new VMSVHService();
		VendHistoryRequestMessage vendHistoryRequest = new VendHistoryRequestMessage();
		StringType stringType = new StringType();
		stringType.setStringType("9000000000000000003");
		vendHistoryRequest.setPan(stringType);
		IntegerType integerType = new IntegerType();
		integerType.setIntegerType(new BigInteger("1"));
		vendHistoryRequest.setNoOfTxns(integerType);
		GetVendHistory getVendHistory = new GetVendHistory();
		getVendHistory.setVendHistoryRequest(vendHistoryRequest);
		GetVendHistoryResponse getVendHistoryResponse = vmsVHService.getVendHistory(getVendHistory);
		Purchase_Txn[] purchase_Txn =  getVendHistoryResponse.getVendHistoryResponse().getCreditPurchase();
		if(purchase_Txn!=null && purchase_Txn.length!=0){
			int txnLength = purchase_Txn.length; 
			int txnNo = 0;
			while(txnLength!=0){
				txnLength--;
				logger.info(Logger.EVENT_UNSPECIFIED,"Transaction ID:" + purchase_Txn[txnNo].getTxnDetails().getTransactionID());
				logger.info(Logger.EVENT_UNSPECIFIED,"Transaction ID:" + purchase_Txn[txnNo].getTxnDetails().getTxnOutcome()[0].getStatus().getStatusCode());
				logger.info(Logger.EVENT_UNSPECIFIED,"Vend Code:" + purchase_Txn[txnNo].getTxnDetails().getVendCode().getStringType().toString());
				txnNo++;
			}
			
		}
		
	} 

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
