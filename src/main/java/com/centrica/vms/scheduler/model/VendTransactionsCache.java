package com.centrica.vms.scheduler.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.ws.service.VMSVHServiceData;

public class VendTransactionsCache {
	private Logger logger = ESAPI.getLogger(getClass().getName());
	private ConcurrentHashMap<String, LinkedList<VendHistoryDetails>> cache = new ConcurrentHashMap<String, LinkedList<VendHistoryDetails>>();
	private static VendTransactionsCache instance = null;

	public static VendTransactionsCache getInstance() {
		synchronized (VendTransactionsCache.class) {
			if (instance == null) {
				instance = new VendTransactionsCache();
			}
			return instance;
		}
	}
	private VendTransactionsCache(){		
	}

	public ConcurrentHashMap<String, LinkedList<VendHistoryDetails>> getTransactionsCache() {
		return cache;
	}

	public void refreshCache() {
		logger.info(Logger.EVENT_UNSPECIFIED,"Refreshing VendTransactionsCache");
		synchronized (VendTransactionsCache.class) {
			cache = loadTransactionsToCache();
		}
	}

	private ConcurrentHashMap<String, LinkedList<VendHistoryDetails>> loadTransactionsToCache() {
		ConcurrentHashMap<String, LinkedList<VendHistoryDetails>> cacheMap = new ConcurrentHashMap<String, LinkedList<VendHistoryDetails>>();
		try {

			logger.debug(Logger.EVENT_SUCCESS,"Entering loadTransactionsToCache method ");
			List<VendHistoryDetails> list = null;
			VMSVHServiceData vmsVHServiceData = new VMSVHServiceData();
			Collection<String> transactionTypes = vmsVHServiceData
					.getPurchaseTxnTypes();
			VMSTransactionDAO txnDAO = new VMSTransactionDAO();
			list = txnDAO.getAllVendPurchaseTransactions(transactionTypes);
			String pan = null;
			for (VendHistoryDetails v : list) {
				pan = v.getPan();
				if (pan != null) {
					addValueToCache(cacheMap, pan, v);
				}
			}
			// Print all values stored in ConcurrentHashMap instance
			for (Entry<String, LinkedList<VendHistoryDetails>> e : cacheMap.entrySet()) {
				logger.info(Logger.EVENT_UNSPECIFIED,e.getKey() + "=" + printValues(e.getValue()));
			}

			logger.debug(Logger.EVENT_SUCCESS,"Leaving loadTransactionsToCache method ");
		} catch (Exception e) {
			logger.error(Logger.EVENT_FAILURE,"Exception caught in loadTransactionsToCache method "
					+ e.getMessage());
		}
		return cacheMap;
	}

	private String printValues(LinkedList<VendHistoryDetails> values) {
		// TODO Auto-generated method stub
		String combinedValues = "";
		for (VendHistoryDetails v : values) {
			combinedValues = combinedValues.concat("" + v.getPan() + ":"
					+ v.getTransactionID() + ",");
		}
		return combinedValues;
	}

	void addValueToCache(
			ConcurrentHashMap<String, LinkedList<VendHistoryDetails>> cacheMap,
			String key, VendHistoryDetails value) {
		if (!cacheMap.containsKey(key)) {
			cacheMap.put(key, new LinkedList<VendHistoryDetails>());
		}
		cacheMap.get(key).add(value);
	}
}
