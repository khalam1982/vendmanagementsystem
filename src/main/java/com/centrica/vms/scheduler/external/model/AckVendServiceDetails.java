/**
 * AckVendServiceDetails.java
 * Purpose: ACK vend service details
 * @author nagarajk
 */
package com.centrica.vms.scheduler.external.model;
/**
 * Method for ACK vend service details
 */
public class AckVendServiceDetails {

	private int ackMinWaitPeriod;
	private int ackMaxWaitPeriod;
	private int ackMaxDelay;
	private String ackJobTime;
	
	/**
	 * @return the ackJobTime
	 */
	public String getAckJobTime() {
		return ackJobTime;
	}

	/**
	 * @param ackJobTime the ackJobTime to set
	 */
	public void setAckJobTime(String ackJobTime) {
		this.ackJobTime = ackJobTime;
	}

	/**
	 * @return the ackMaxDelay
	 */
	public int getAckMaxDelay() {
		return ackMaxDelay;
	}

	/**
	 * @param ackMaxDelay the ackMaxDelay to set
	 */
	public void setAckMaxDelay(int ackMaxDelay) {
		this.ackMaxDelay = ackMaxDelay;
	}


	
	/**
	 * @return the ackMaxWaitPeriod
	 */
	public int getAckMaxWaitPeriod() {
		return ackMaxWaitPeriod;
	}

	/**
	 * @param ackMaxWaitPeriod the ackMaxWaitPeriod to set
	 */
	public void setAckMaxWaitPeriod(int ackMaxWaitPeriod) {
		this.ackMaxWaitPeriod = ackMaxWaitPeriod;
	}


	
	/**
	 * @return the ackMinWaitPeriod
	 */
	public int getAckMinWaitPeriod() {
		return ackMinWaitPeriod;
	}

	/**
	 * @param ackMinWaitPeriod the ackMinWaitPeriod to set
	 */
	public void setAckMinWaitPeriod(int ackMinWaitPeriod) {
		this.ackMinWaitPeriod = ackMinWaitPeriod;
	}
}
