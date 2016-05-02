/**
 * VMSVHServiceData.java
 * Vend history details data preparation
 * @author ramasap1
 * @version 1.0
 */

package com.centrica.vms.ws.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn;
import smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Type;
import smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Details;
import smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome;
import vhcommon.enterprise.uk.co.britishgas.DateType;
import vhcommon.enterprise.uk.co.britishgas.IntegerType;
import vhcommon.enterprise.uk.co.britishgas.SourceType;
import vhcommon.enterprise.uk.co.britishgas.StatusType;
import vhcommon.enterprise.uk.co.britishgas.StringType;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.model.StatusDetails;
import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;

public class VMSVHServiceData {
	
	private static final String DEFAULT_HOLDING_PERIOD = "0";
	private static final String EMPTY_VEND_CODE = "---------";
	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	/**
     * Method used to set the credit type
     * @param adjustment_Txn
     * @param vendHistoryDetail
     * @param txnNo
     */
	public void setCreditType(Adjustment_Txn[] adjustment_Txn,
			VendHistoryDetails vendHistoryDetail, int txnNo) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setCreditType method");
		String transactionType = vendHistoryDetail.getTransactionType();
		transactionType = transactionType.toUpperCase();
		if(transactionType.contains(new VMSUtils().loadProperties().getProperty("CREDIT_TYPE"))){
			adjustment_Txn[txnNo].setCreditType(Adjustment_Type.POSITIVE);
		}else{
			adjustment_Txn[txnNo].setCreditType(Adjustment_Type.NEGATIVE);
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setCreditType method");
	}
	
	/**
	 * Method used to set the actual timestamp
	 * @param vendHistoryDetail
	 * @param txn_Details
	 */
	public void setActualTimestamp(VendHistoryDetails vendHistoryDetail,
		Txn_Details txn_Details) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setActualTimestamp method");
		DateType dateType = new DateType();
		Calendar calender = Calendar.getInstance();
		if (vendHistoryDetail.getActualTimeStamp() != null) {
			calender.setTime(vendHistoryDetail.getActualTimeStamp());
		} 
		dateType.setDateType(calender);
		txn_Details.setActual_timestamp(dateType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setActualTimestamp method");
	}

	/**
	 * Method used to set the vend code timestamp
	 * @param vendHistoryDetail
	 * @param txn_Details
	 */
	public void setVendcodeTimestamp(VendHistoryDetails vendHistoryDetail,
		Txn_Details txn_Details) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendcodeTimestamp method");
		DateType dateType = new DateType();
		Calendar calender = Calendar.getInstance();
		if (vendHistoryDetail.getVendcodeTimeStamp() != null) {
			calender.setTime(vendHistoryDetail.getVendcodeTimeStamp());
		} else if (vendHistoryDetail.getActualTimeStamp() != null) {
			calender.setTime(vendHistoryDetail.getActualTimeStamp());
		} 
		dateType.setDateType(calender);
		txn_Details.setVendcode_timestamp(dateType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendcodeTimestamp method");
	}

	/**
	 * Method used to set the created timestamp
	 * @param vendHistoryDetail
	 * @param txn_Details
	 */
	public void setCreatedTimestamp(VendHistoryDetails vendHistoryDetail,
		Txn_Details txn_Details) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setCreatedTimestamp method");
		DateType dateType = new DateType();
		Calendar calender = Calendar.getInstance();
		if (vendHistoryDetail.getCreatedTimeStamp() != null) {
			calender.setTime(vendHistoryDetail.getCreatedTimeStamp());
		} else if (vendHistoryDetail.getActualTimeStamp() != null) {
			calender.setTime(vendHistoryDetail.getActualTimeStamp());
		} 
		dateType.setDateType(calender);
		txn_Details.setCreated_timestamp(dateType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setCreatedTimestamp method");
	}


	/**
	 * Method used to set the source type
	 * @param vendHistoryDetail
	 * @param txn_Details
	 */
	public void setSourceType(VendHistoryDetails vendHistoryDetail,
		Txn_Details txn_Details) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setSourceType method");
		StringType stringType;
		SourceType sourceType = new SourceType();
		stringType = new StringType();
		stringType.setStringType(vendHistoryDetail.getSourceDetails().getSource());
		sourceType.setSourceCode(stringType);
		stringType = new StringType();
		stringType.setStringType(vendHistoryDetail.getSourceDetails().getSourceDescription());
		sourceType.setSourceDescription(stringType);
		txn_Details.setSourceDetails(sourceType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setSourceType method");
	}

	/**
	 * Method used to set the holding pen period
	 * @param vendHistoryDetail
	 * @param txn_Details
	 */
	public void setHoldingPeriod(VendHistoryDetails vendHistoryDetail,Txn_Details txn_Details) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setHoldingPeriod method");
		StringType stringType = new StringType();
		if (vendHistoryDetail.getHoldingPeriod() != null ) {
			stringType.setStringType(new Long(vendHistoryDetail.getHoldingPeriod()).toString());
		} else {
			stringType.setStringType(DEFAULT_HOLDING_PERIOD);
		}
		txn_Details.setHoldingPeriod(stringType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setHoldingPeriod method");
	}

	/**
	 * Method used to set the MSN
	 * @param vendHistoryDetail
	 * @param txn_Details
	 */
	public void setMsn(VendHistoryDetails vendHistoryDetail,Txn_Details txn_Details) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setMsn method");
		StringType stringType = new StringType();
		stringType.setStringType(vendHistoryDetail.getMsn());
		txn_Details.setMsn(stringType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setMsn method");
	}

	/**
	 * Method used to set the transaction type
	 * @param vendHistoryDetail
	 * @param txn_Details
	 */
	public void setTxnType(VendHistoryDetails vendHistoryDetail,Txn_Details txn_Details) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setTxnType method");
		StringType stringType = new StringType();
		stringType.setStringType(vendHistoryDetail.getTransactionType());
		txn_Details.setTransactionType(stringType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setTxnType method");
	}

	/**
	 * Method used to set the vend code
	 * @param vendHistoryDetail
	 * @param txn_Details
	 */
	public void setVendCode(VendHistoryDetails vendHistoryDetail,Txn_Details txn_Details) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendCode method");
		StringType stringType = new StringType();
		String vendCode = getVendCode(vendHistoryDetail);
		logger.info(Logger.EVENT_UNSPECIFIED,"VendCode " + vendCode);
		if(vendHistoryDetail.getTxnStatusDetails()!=null && (vendHistoryDetail.getTransactionType().equals(TransactionType.ElectricPurchase.getTransactionType()) || vendHistoryDetail.getTransactionType().equals(TransactionType.GasPurchase.getTransactionType())) ){
			Iterator<VendTxnStatus> iterator = vendHistoryDetail.getTxnStatusDetails().iterator();
			while(iterator.hasNext()){
				StatusDetails statusDetails = (StatusDetails)iterator.next().getStatusDetails();
				if(statusDetails.getStatusCode()==Status.SC_140.getStatus() || statusDetails.getStatusCode()==Status.SC_145.getStatus()|| statusDetails.getStatusCode()==Status.SC_180.getStatus() || statusDetails.getStatusCode()==Status.SC_130.getStatus()){
					int size = vendCode.length();
					String maskVendCode = "";
					for(int i=0;i<vendCode.length()-4;i++){
						maskVendCode = maskVendCode+"X";
					}
					vendCode=maskVendCode+vendCode.substring(size-4,size);
					logger.info(Logger.EVENT_UNSPECIFIED,"Masked vendCode " + vendCode);
				}
			}
		}
		stringType.setStringType(vendCode);
		txn_Details.setVendCode(stringType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendCode method");
	}

	private String getVendCode(VendHistoryDetails vendHistoryDetail) {
		String vendCode = vendHistoryDetail.getVendCode();
		return StringUtils.isNotBlank(vendCode) ? vendCode : EMPTY_VEND_CODE;
	}

	/**
	 * Method used to set the transaction id
	 * @param transactionID
	 * @param txn_Details
	 */
	public void setTransactionID(String transactionID,Txn_Details txn_Details) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setTransactionID method");
		StringType stringType = new StringType();
		stringType.setStringType(transactionID);
		txn_Details.setTransactionID(stringType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setTransactionID method");
	}

	/**
	 * Method used to set the credit value
	 * @param vendHistoryDetail
	 * @param txn_Details
	 */
	public void setCreditValue(VendHistoryDetails vendHistoryDetail,Txn_Details txn_Details) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setCreditValue method");
		StringType stringType = new StringType();
		stringType.setStringType(vendHistoryDetail.getCreditValue());
		txn_Details.setCredit_value(stringType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setCreditValue method");
	}

	/**
	 * Method used to get the purchase transaction types
	 * @return
	 */
	public Collection<String> getPurchaseTxnTypes() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getPurchaseTxnTypes method");
		Collection<String> transactionTypes = new ArrayList<String>();
		transactionTypes.add(TransactionType.ElectricPurchase.getTransactionType());
		transactionTypes.add(TransactionType.GasPurchase.getTransactionType());
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getPurchaseTxnTypes method");
		return transactionTypes;
	}

	/**
	 * Method used to get the void transaction types
	 * @return
	 */
	public Collection<String> getVoidTxnType() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVoidTxnType method");
		Collection<String> transactionTypes = new ArrayList<String>();
		transactionTypes.add(TransactionType.VoidENegativeAdjustment.getTransactionType());
		transactionTypes.add(TransactionType.VoidGNegativeAdjustment.getTransactionType());
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVoidTxnType method");
		return transactionTypes;
	}

	/**
	 * Method used to get the adjustment transaction type
	 * @return
	 */
	public Collection<String> getAdjustmentTxnType() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getAdjustmentTxnType method");
		Collection<String> transactionTypes = new ArrayList<String>();
		transactionTypes.add(TransactionType.ENegativeAdjustment.getTransactionType());
		transactionTypes.add(TransactionType.EPositiveAdjustment.getTransactionType());
		transactionTypes.add(TransactionType.GNegativeAdjustment.getTransactionType());
		transactionTypes.add(TransactionType.GPositiveAdjustment.getTransactionType());
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getAdjustmentTxnType method");
		return transactionTypes;
	}
  
 
	/**
	 * Method used to set the transaction statuses
	 * @param txn_Details
	 * @return
	 */
	public void setTransactionStatuses(Txn_Details txn_Details,VendHistoryDetails vendHistoryDetail) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setTransactionStatuses method");
	    Set<VendTxnStatus> txnStatus = vendHistoryDetail.getTxnStatusDetails();
	    VendTxnStatus vendTxnStatus;
	    StatusType statusType;
	    if(txnStatus !=null && !txnStatus.isEmpty())
	    {
	    	Iterator<VendTxnStatus>  vendTxnStatuses = txnStatus.iterator();
	    	Txn_Outcome[] txn_Outcome = new Txn_Outcome[txnStatus.size()];
	    	int txnNo=0;
	    	while(vendTxnStatuses.hasNext()){
	    		statusType = new StatusType();
	    		vendTxnStatus = vendTxnStatuses.next();
	    		setStatusCode(vendTxnStatus,txn_Outcome, txnNo,statusType);
	    	 	setStatusDescription(vendTxnStatus, statusType);
	    	 	txn_Outcome[txnNo].setStatus(statusType);
	    	 	setUpdatedTimestamp(vendTxnStatus, txn_Outcome, txnNo);
	    	 	txnNo++;
	    	}
	    	txn_Details.setTxnOutcome(txn_Outcome);
	    }else{
	    	logger.info(Logger.EVENT_UNSPECIFIED,"Transaction status details is null or empty " + txnStatus);
	    }
	    
	 	logger.debug(Logger.EVENT_SUCCESS,"Leaving setTransactionStatuses method");
 	}

	/**
	 * Method used to set the status description
	 * @param vendTxnStatus
	 * @param statusType
	 */
	public void setStatusDescription(VendTxnStatus vendTxnStatus,
		StatusType statusType) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setStatusDescription method");
		StringType stringType;
		stringType = new StringType();
		stringType.setStringType(vendTxnStatus.getStatusDetails().getStatusDesc());
		statusType.setStatusDescription(stringType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setStatusDescription method");
	}

	/**
	 *  Method used to set the status code
	 * @param vendTxnStatus
	 * @param txn_Outcome
	 * @param txnNo
	 * @param statusType
	 * @return
	 */
	public StatusType setStatusCode(VendTxnStatus vendTxnStatus,
		Txn_Outcome[] txn_Outcome, int txnNo,StatusType statusType) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setStatusCode method");
		IntegerType integerType;
		txn_Outcome[txnNo] = new Txn_Outcome();
		integerType = new IntegerType();
		integerType.setIntegerType(new BigInteger(new Integer(vendTxnStatus.getStatusDetails().getStatusCode()).toString()));
		statusType.setStatusCode(integerType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setStatusCode method");
		return statusType;
	}

	/**
	 * Method used to set the updated timestamp.
	 * @param vendTxnStatus
	 * @param txn_Outcome
	 * @param txnNo
	 */
	public void setUpdatedTimestamp(VendTxnStatus vendTxnStatus,
		Txn_Outcome[] txn_Outcome, int txnNo) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setUpdatedTimestamp method");
		DateType dateType = new DateType();
		Calendar calender = Calendar.getInstance();
		calender.setTime(vendTxnStatus.getUpdatedTimeStamp());
		dateType.setDateType(calender);
		txn_Outcome[txnNo].setUpdated_timestamp(dateType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setUpdatedTimestamp method");
		}

	/**
	 * Method used to set the updated timestamp.
	 * @param vendTxnStatus
	 * @param txn_Outcome
	 * @param txnNo
	 */
	public void setTopUpSource(VendHistoryDetails vendHistoryDetail,
			Txn_Details txn_Details) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setTopUpSource method");
		if (StringUtils.isNotBlank(vendHistoryDetail.getTopUpSource())) {
			StringType stringType = new StringType();
			stringType.setStringType(vendHistoryDetail.getTopUpSource());
			txn_Details.setTopUpSource(stringType);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setTopUpSource method");
		
	}
}