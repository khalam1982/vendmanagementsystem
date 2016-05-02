package com.centrica.vms.ws.transaction.query;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.scheduler.model.VendTransactionsCache;

public class VendTransactionQueryData {

	private Logger logger = ESAPI.getLogger(getClass().getName());
	private static final int STATUS_VEND_SUCCESSFUL = 100;

	public List<VendHistoryDetails> getVendPurchaseTransactionsFromDB(
			String pan, Collection<String> transactionTypes, int txnCount,
			Calendar startTimeStamp, Calendar endTimeStamp) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getVendPurchaseTransactionsFromDB");
		List<VendHistoryDetails> tempList = null;
		List<VendHistoryDetails> list = new ArrayList<VendHistoryDetails>();
		try {
			VMSTransactionDAO txnDAO = new VMSTransactionDAO();
			if (startTimeStamp == null && endTimeStamp == null) {
				tempList = txnDAO.getVendHistory_Purchase(pan, transactionTypes,-1);
			} else {
				tempList = txnDAO.getVendPurchaseTransactionsForGivenDateRange(pan,
						transactionTypes, -1, startTimeStamp,
						endTimeStamp);
			}
		} catch (Exception e) {
			logger.error(Logger.EVENT_FAILURE,"Exception found at getVendPurchaseTransactions: "
					+ e.getMessage());
		}
		//Filter for successful transaction count
		for(VendHistoryDetails v : tempList ){
			if(checkVendStatus(v)){
				list.add(v);
				if(list.size()==txnCount) break;
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getVendPurchaseTransactionsFromDB");
		return list;
	}

	private List<VendHistoryDetails> filterTransactionsByDateAndCount(
			List<VendHistoryDetails> vendHistoryDetailsList, int txnCount,
			Calendar startTimeStamp, Calendar endTimeStamp) {
		List<VendHistoryDetails> vendHistoryDetailsListTemp = new ArrayList<VendHistoryDetails>();
		try {
			for (VendHistoryDetails v : vendHistoryDetailsList) {				
				Date createdDateStamp = v.getCreatedTimeStamp();
				boolean isDateWithinInRangeFlag = true;
				if (startTimeStamp != null && endTimeStamp != null) {
					isDateWithinInRangeFlag = isDateWithinGivenRange(
							startTimeStamp, endTimeStamp, createdDateStamp);
				}
				if (isDateWithinInRangeFlag && checkVendStatus(v)) {
					vendHistoryDetailsListTemp.add(v);
					// txnCount is -1 by defaults
					if(txnCount != -1 && vendHistoryDetailsListTemp.size()== txnCount){
						break;
					}
				}
			}
		} catch (ParseException e) {
			logger
					.info(Logger.EVENT_UNSPECIFIED,"Error while parsing VendHistoryDetails in filterTransactionsByDateAndCount "
							+ e.getMessage());
		}
		return vendHistoryDetailsListTemp;
	}
	private Boolean checkVendStatus(VendHistoryDetails vendHistoryDetail) {
		Boolean successful = false;
		Set<VendTxnStatus> vendStatusSet =vendHistoryDetail.getTxnStatusDetails();
		for(VendTxnStatus v : vendStatusSet){
			if(v.getStatus().intValue()==STATUS_VEND_SUCCESSFUL){
				successful = true;
			}
		}
		return successful;
	}

	private boolean isDateWithinGivenRange(Calendar startTimeStamp,
			Calendar endTimeStamp, Date createdDateStamp) throws ParseException {

		VendTransactionUtil util = new VendTransactionUtil();
		Date formattedStartDate = util.getFormattedDate(startTimeStamp);
		Date formattedEndDate = util.getFormattedDate(endTimeStamp);
		Calendar createdDateTimeStamp = Calendar.getInstance();
		createdDateTimeStamp.setTime(createdDateStamp);
		Date formattedCreatedDate = util.getFormattedDate(createdDateTimeStamp);
		if ((formattedCreatedDate.after(formattedStartDate) || formattedCreatedDate
				.equals(formattedStartDate))
				&& (formattedCreatedDate.before(formattedEndDate) || formattedCreatedDate
						.equals(formattedEndDate))) {
			return true;
		}
		return false;
	}
	public List<VendHistoryDetails> getVendPurchaseTransactionsFromCacheOrDB(
			String pan, Collection<String> transactionTypes, int txnCount,
			Calendar startTimeStamp, Calendar endTimeStamp) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getVendPurchaseTransactionsFromCacheOrDB");
		VMSUtils vmsutils = new VMSUtils();
		Boolean caching_enabled = new Boolean(vmsutils.loadProperties().getProperty("CACHING_ENABLED"));
		List<VendHistoryDetails> vendHistoryDetailsList = null;
		if (caching_enabled) {
			logger.info(Logger.EVENT_UNSPECIFIED,"Caching is enabled");
			List<VendHistoryDetails> vendHistoryDetailsListTemp = null;
			VendTransactionsCache cache = VendTransactionsCache.getInstance();
			ConcurrentHashMap<String, LinkedList<VendHistoryDetails>> map = cache.getTransactionsCache();
			if(!map.isEmpty()){
				logger.info(Logger.EVENT_UNSPECIFIED,"Fetching transactions from cache");
				vendHistoryDetailsListTemp = map.get(pan);
				vendHistoryDetailsList = filterTransactionsByDateAndCount(
						vendHistoryDetailsListTemp, txnCount, startTimeStamp,
						endTimeStamp);

			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,"Cache Map is null. Fetch transactions from DB");
				vendHistoryDetailsList = getVendPurchaseTransactionsFromDB(pan,
						transactionTypes, txnCount, startTimeStamp,
						endTimeStamp);
			}
		} else {
			logger.info(Logger.EVENT_UNSPECIFIED,"Caching is disabled. Fetching transactions from DB");
			vendHistoryDetailsList = getVendPurchaseTransactionsFromDB(pan,
					transactionTypes, txnCount, startTimeStamp, endTimeStamp);
		}

		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getVendPurchaseTransactionsFromCacheOrDB");
		return vendHistoryDetailsList;
	}

}
