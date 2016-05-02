/**
 * VMSVHService.java
 * This file implements the vend history service
 * @author ramasap1
 * @version 1.0
 */
package com.centrica.vms.ws.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn;
import smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn;
import smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Details;
import smartmetervendhistory.enterprise.uk.co.britishgas.VendHistoryResponseMessage;
import smartmetervendhistory.enterprise.uk.co.britishgas.VoidTxn_Details;
import vhcommon.enterprise.uk.co.britishgas.StringType;

import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.vh.ws.sap.service.GetVendHistory;
import com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse;

public class VMSVHService {
	
	private VMSTransactionDAO vmsTransactionDAO;
	
	/**
	 * Constructor
	 */
	public VMSVHService() {
		vmsTransactionDAO = new VMSTransactionDAO();
	}

	/**
	 * Constructor
	 * @param vmsTransactionDAO - VMSTransactionDAO
	 */
	public void setVMSTransactionDAO(final VMSTransactionDAO vmsTransactionDAO) {
		this.vmsTransactionDAO = vmsTransactionDAO;
	}
	
	private Logger logger = ESAPI.getLogger(getClass().getName());


	/**
	 * Method used to get the vend history
	 * @param vendHistoryRequest
	 * @return
	 */
	public GetVendHistoryResponse getVendHistory(GetVendHistory vendHistoryRequest){
     logger.debug(Logger.EVENT_SUCCESS,"Entering getVendHistory method");
   	 String pan = vendHistoryRequest.getVendHistoryRequest().getPan().toString();
   	 logger.info(Logger.EVENT_UNSPECIFIED,"pan : "+ pan);
	 int noOfTxns = new Integer(vendHistoryRequest.getVendHistoryRequest().getNoOfTxns().toString()).intValue();
	 logger.info(Logger.EVENT_UNSPECIFIED,"No Of Txns : "+ noOfTxns);
	 if(vendHistoryRequest.getVendHistoryRequest().getValidFrom()!=null && vendHistoryRequest.getVendHistoryRequest().getValidFrom().getDateType()!=null){
		logger.info(Logger.EVENT_UNSPECIFIED,"Valid From :"+ vendHistoryRequest.getVendHistoryRequest().getValidFrom().getDateType().toString());
	 }
	 if(vendHistoryRequest.getVendHistoryRequest().getValidTo()!=null && vendHistoryRequest.getVendHistoryRequest().getValidTo().getDateType()!=null){
		 logger.info(Logger.EVENT_UNSPECIFIED,"Valid to :"+ vendHistoryRequest.getVendHistoryRequest().getValidTo().getDateType().toString());
 	 }
	 GetVendHistoryResponse getVendHistoryResponse = new GetVendHistoryResponse();
	 VendHistoryResponseMessage vendHistoryResponseMessage = new VendHistoryResponseMessage();
	 StringType stringType = new StringType();
	 stringType.setStringType(pan);
	 vendHistoryResponseMessage.setPan(stringType);
	 try{
    	Purchase_Txn[] purchase_Txn = preparePurchaseTxn(pan,noOfTxns);
    	Adjustment_Txn[] adjustment_Txn = prepareAdjustmentTxn(pan,noOfTxns);
    	if(purchase_Txn!=null && purchase_Txn.length!=0){
    		vendHistoryResponseMessage.setCreditPurchase(purchase_Txn);
    	}else{
 	    	logger.info(Logger.EVENT_UNSPECIFIED,"purchase transactions is null  or empty " + purchase_Txn);
 	    }
    	if(adjustment_Txn!=null && adjustment_Txn.length!=0){
    		vendHistoryResponseMessage.setCreditAdjustment(adjustment_Txn);
    	}else{
 	    	logger.info(Logger.EVENT_UNSPECIFIED,"adjustment transactions is null  or empty " + adjustment_Txn);
 	    }
	 }catch(Exception ex){
		 logger.error(Logger.EVENT_FAILURE,"Exception : "+ ex.getMessage());
	 }
	 getVendHistoryResponse.setVendHistoryResponse(vendHistoryResponseMessage);
	 logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendHistory method");
     return getVendHistoryResponse;
   }
	
	/**
	 * Method used to prepare the purchase transactions
	 * @param pan
	 * @param noOfTxns
	 * @return
	 */
	private Purchase_Txn[] preparePurchaseTxn(String pan,int noOfTxns) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering preparePurchaseTxn method");
		Purchase_Txn[] purchase_Txn = null;
		
		VMSVHServiceData vmsVHServiceData = new VMSVHServiceData();
		Collection<String> transactionTypes = vmsVHServiceData.getPurchaseTxnTypes();
		ArrayList<VendHistoryDetails> vendHistoryDetails = vmsTransactionDAO.getVendHistory_Purchase(pan, transactionTypes,noOfTxns);
		if(vendHistoryDetails!=null && vendHistoryDetails.size()!=0){
			Iterator<VendHistoryDetails> iteratorVHDetails = vendHistoryDetails.iterator();
			purchase_Txn = new Purchase_Txn[vendHistoryDetails.size()];
			VendHistoryDetails vendHistoryDetail;
			int txnNo = 0;
			while(iteratorVHDetails.hasNext()){
				vendHistoryDetail = iteratorVHDetails.next();
				if (isValidVendCode(vendHistoryDetail)) {
					purchase_Txn[txnNo] = new Purchase_Txn();
		 			Txn_Details txn_Details = new Txn_Details();
		 			String transactionID = vendHistoryDetail.getTransactionID();
		 			setTxnDetails(vendHistoryDetail, txn_Details, transactionID,vmsVHServiceData);
					VoidTxn_Details voidTxn_Details = prepareVoidTxn(pan,transactionID);
					if(voidTxn_Details!=null){
						purchase_Txn[txnNo].setVoidTxnDetails(voidTxn_Details);
					}else{
			 	    	logger.info(Logger.EVENT_UNSPECIFIED,"void transaction details is null " + voidTxn_Details);
			 	    }
		 			purchase_Txn[txnNo].setTxnDetails(txn_Details);
		 			txnNo++;
				}
			}
		}else{
 	    	logger.info(Logger.EVENT_UNSPECIFIED,"Vend history details is null  or empty " + vendHistoryDetails);
 	    }
		logger.debug(Logger.EVENT_SUCCESS,"Leaving preparePurchaseTxn method");
		return purchase_Txn;
	}

	private boolean isValidVendCode(VendHistoryDetails vendHistoryDetail) {
		return vendHistoryDetail != null && StringUtils.isNotBlank(vendHistoryDetail.getVendCode()) && !vendHistoryDetail.getVendCode().startsWith("--");
	}

	/**
	 * Method used to set the transaction details
	 * @param vendHistoryDetail
	 * @param txn_Details
	 * @param transactionID
	 * @param vmsVHServiceData
	 */
	private void setTxnDetails(VendHistoryDetails vendHistoryDetail,
		Txn_Details txn_Details, String transactionID,VMSVHServiceData vmsVHServiceData) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setTxnDetails method");
		vmsVHServiceData.setCreditValue(vendHistoryDetail, txn_Details);
		vmsVHServiceData.setTransactionID(transactionID, txn_Details);
		vmsVHServiceData.setVendCode(vendHistoryDetail, txn_Details);
		vmsVHServiceData.setTransactionStatuses(txn_Details,vendHistoryDetail);
		vmsVHServiceData.setTxnType(vendHistoryDetail, txn_Details);
		vmsVHServiceData.setMsn(vendHistoryDetail, txn_Details);
		vmsVHServiceData.setHoldingPeriod(vendHistoryDetail, txn_Details);
		vmsVHServiceData.setSourceType(vendHistoryDetail, txn_Details);
		vmsVHServiceData.setActualTimestamp(vendHistoryDetail, txn_Details);
		vmsVHServiceData.setVendcodeTimestamp(vendHistoryDetail, txn_Details);
		vmsVHServiceData.setCreatedTimestamp(vendHistoryDetail, txn_Details);
		vmsVHServiceData.setTopUpSource(vendHistoryDetail, txn_Details);
		logger.debug(Logger.EVENT_SUCCESS,"Entering setTxnDetails method");
	}

	 /**
	  * Method used to prepare the adjustment transaction
	  * @param pan
	  * @param noOfTxns
	  * @return
	  */
    private Adjustment_Txn[] prepareAdjustmentTxn(String pan,int noOfTxns) {
   	 logger.debug(Logger.EVENT_SUCCESS,"Entering prepareAdjustmentTxn method");
   	 Adjustment_Txn[] adjustment_Txn = new Adjustment_Txn[noOfTxns];
     VMSVHServiceData vmsVHServiceData = new VMSVHServiceData();
		 Collection<String> transactionTypes = new VMSVHServiceData().getAdjustmentTxnType();
		 ArrayList<VendHistoryDetails> vendHistoryDetails = vmsTransactionDAO.getVendHistory_Adjustment(pan, transactionTypes, noOfTxns);
		 if(vendHistoryDetails!=null && vendHistoryDetails.size()!=0){
				Iterator<VendHistoryDetails> iteratorVHDetails = vendHistoryDetails.iterator();
				adjustment_Txn = new Adjustment_Txn[vendHistoryDetails.size()];
				VendHistoryDetails vendHistoryDetail;
				int txnNo = 0;
				while(iteratorVHDetails.hasNext()){
					vendHistoryDetail = iteratorVHDetails.next();
					adjustment_Txn[txnNo] = new Adjustment_Txn();
		 			Txn_Details txn_Details = new Txn_Details();
		 			String transactionID = vendHistoryDetail.getTransactionID();
		 			setTxnDetails(vendHistoryDetail, txn_Details, transactionID,vmsVHServiceData);
		 			new VMSVHServiceData().setCreditType(adjustment_Txn, vendHistoryDetail, txnNo);
		 			adjustment_Txn[txnNo].setTxnDetails(txn_Details);
		 			txnNo++;
				}
			}else{
	 	    	logger.info(Logger.EVENT_UNSPECIFIED,"Vend history details is null  or empty " + vendHistoryDetails);
	 	    }
   	 logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareAdjustmentTxn method");
   	 return adjustment_Txn;
   	 
    }


    
    /**
	 * Method used to prepare the void transaction
	 * @param pan
	 * @param originalTransactionID
	 * @return
	 */
	 private VoidTxn_Details prepareVoidTxn(String pan,String originalTransactionID) {
    	 logger.debug(Logger.EVENT_SUCCESS,"Entering prepareVoidTxn method");
		 VoidTxn_Details voidTxn_Details = null;
		 VMSVHServiceData vmsVHServiceData = new VMSVHServiceData();
		 Collection<String> transactionTypes = new VMSVHServiceData().getVoidTxnType();
		 ArrayList<VendHistoryDetails> vendHistoryDetails = vmsTransactionDAO.getVendHistory_Void(originalTransactionID, transactionTypes);
		 if(vendHistoryDetails!=null && vendHistoryDetails.size()!=0){
			 voidTxn_Details = new VoidTxn_Details();
			 VendHistoryDetails vendHistoryDetail = vendHistoryDetails.get(0);
			 Txn_Details txn_Details = new Txn_Details();
			 setTxnDetails(vendHistoryDetail, txn_Details, vendHistoryDetail.getTransactionID(),vmsVHServiceData);
			 voidTxn_Details.setTxnDetails(txn_Details);
		 }else{
 	    	logger.info(Logger.EVENT_UNSPECIFIED,"Vend history details is null or empty " + vendHistoryDetails);
 	    }
		 logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareVoidTxn method");
		 return voidTxn_Details;
	}
	
}
