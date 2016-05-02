/**
 * cacheVendServiceDetails.java
 * Purpose: cache vend service details
 * @author nagarajk
 */
package com.centrica.vms.scheduler.external.model;
/**
 * Method for cache vend service details
 */
public class VendTransactionCacheDetails {

//	private int cacheMinWaitPeriod;
//	private int cacheMaxWaitPeriod;
//	private int cacheMaxDelay;
	private String cacheJobTime;
	
	/**
	 * @return the cacheJobTime
	 */
	public String getCacheJobTime() {
		return cacheJobTime;
	}

	/**
	 * @param cacheJobTime the cacheJobTime to set
	 */
	public void setCacheJobTime(String cacheJobTime) {
		this.cacheJobTime = cacheJobTime;
	}	
}
