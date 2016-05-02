 package com.centrica.vms.ws.transaction.query;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.ws.service.VMSVHServiceData;

    /**
     *  VendTransactionQueryServiceSkeleton java skeleton for the axisService
     */
    public class VendTransactionQuery{
    	private static final String SUCCESS_STATUS = "Success";
		private static final String CURRENCY_TYPE = "GBP";
		private Logger logger = ESAPI.getLogger(getClass().getName());
    	
        public List<VendTransaction> getPurchaseTransactionsForGivenPAN(String pan,int noOfTxns, Calendar startTimeStamp, Calendar endTimeStamp) {
     		logger.debug(Logger.EVENT_SUCCESS,"Entering getPurchaseTransactionsForGivenPAN method");
     		List<VendTransaction> vendTxnList = new ArrayList<VendTransaction>();
     		VendTransactionQueryData queryData = new VendTransactionQueryData();
     		VMSVHServiceData vmsVHServiceData = new VMSVHServiceData();
     		Collection<String> transactionTypes = vmsVHServiceData.getPurchaseTxnTypes();
     		List<VendHistoryDetails> vendHistoryDetails= null;
     		vendHistoryDetails = queryData.getVendPurchaseTransactionsFromCacheOrDB(pan, transactionTypes,noOfTxns,startTimeStamp,endTimeStamp);     		
     		if(vendHistoryDetails!=null && !vendHistoryDetails.isEmpty()){
     			Iterator<VendHistoryDetails> iteratorVHDetails = vendHistoryDetails.iterator();
     			VendHistoryDetails vendHistoryDetail;
     			VendTransaction vendTransaction = null;
     			while(iteratorVHDetails.hasNext()){
     				vendHistoryDetail = iteratorVHDetails.next();
     				vendTransaction = populateVendTransaction(vendHistoryDetail);	
     				vendTxnList.add(vendTransaction);
     			}
     		}else{
      	    	logger.info(Logger.EVENT_UNSPECIFIED,"Vend history details is null  or empty " + vendHistoryDetails);
      	    }
     		logger.debug(Logger.EVENT_SUCCESS,"Leaving getPurchaseTransactionsForGivenPAN method");
     		return vendTxnList;
     	}

		private VendTransaction populateVendTransaction(VendHistoryDetails vendHistoryDetail) {
			VendTransaction vendTransaction = new VendTransaction();
			VendCode vendCode = new VendCode();
			vendCode.setVendCode(vendHistoryDetail.getVendCode());
			vendTransaction.setVendCode(vendCode);
				
			 Channel source = new Channel();
        	 source.setChannel(vendHistoryDetail.getSourceDetails().getSourceDescription());
        	 vendTransaction.setChannel(source);
        	 
        	 BG_Amount amount = new BG_Amount();
        	 
        	 final String vendAmt = getVendAmount(vendHistoryDetail.getCreditValue());

        	 BigDecimal value = new BigDecimal(vendAmt);
        	 amount.setBG_Amount(value);
        	 VendAmount vendAmount = new VendAmount();
        	 BG_CurrencyAmount curAmount = new BG_CurrencyAmount();
        	 curAmount.setAmount(amount);
        	 curAmount.setCurrencyType(CURRENCY_TYPE);
        	 vendAmount.setVendAmount(curAmount);
        	 vendTransaction.setVendAmount(vendAmount);
        	 
        	 VendDateTimeStamp timestamp = new VendDateTimeStamp();
        	 Calendar calendar = Calendar.getInstance();
        	 calendar.setTime(vendHistoryDetail.getCreatedTimeStamp());
        	 timestamp.setVendDateTimeStamp(calendar);
        	 vendTransaction.setVendDateTimeStamp(timestamp);
        	
        	 Status status = new Status();
        	 status.setStatus(SUCCESS_STATUS);
        	 vendTransaction.setStatus(status);
        	 
        	 return vendTransaction;
			
		}

		/**
		 * Returns Vend Amount in the format - 0.00
		 * @param vendAmt of type String
		 * @return String
		 */
		private String getVendAmount(String vendAmt) {

			if( vendAmt != null && !vendAmt.isEmpty() ) {
				try {
					double vendValue = Integer.parseInt(vendAmt)/100;
					NumberFormat numFmt = NumberFormat.getInstance();
					numFmt.setMinimumFractionDigits(2);
					vendAmt = numFmt.format(vendValue);
				} catch(NumberFormatException e ) {
					/** Do Nothing **/
				}
			}
			return vendAmt;

		}

    }
