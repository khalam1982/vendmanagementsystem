/**
 * 
 */
package com.centrica.vms.ws.model;

import com.centrica.vms.model.VendTransaction;


/**
 * @author ramasap1
 *
 */
public class VendTxnWSDetails extends VendTransaction{
	
	
	private String vendCode;
	    
	private String transactionType;
	
	private String creditValue;
	
	private String pan;
	
	private String source;
	
	private String msn;
	
	private String triggerName;
	
	private String topUpSource;
	
	private String headend;
	
	public String getTopUpSource() {
		return topUpSource;
	}

	public void setTopUpSource(String topUpSource) {
		this.topUpSource = topUpSource;
	}
	
	public String getHeadend() {
		return headend;
	}

	public void setHeadend(String headend) {
		this.headend = headend;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getHoldingPeriod() {
		return holdingPeriod;
	}

	public void setHoldingPeriod(Long holdingPeriod) {
		this.holdingPeriod = holdingPeriod;
	}

	private Long holdingPeriod;
	
	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}



	public String getPpKey() {
		return ppKey;
	}



	public void setPpKey(String ppKey) {
		this.ppKey = ppKey;
	}

	private String ppKey;
	


	public String getCreditValue() {
		return creditValue;
	}



	public void setCreditValue(String creditValue) {
		this.creditValue = creditValue;
	}


	/**
	 * @return the vendCode
	 */
	public String getVendCode() {
		return vendCode;
	}



	/**
	 * @param vendCode the vendCode to set
	 */
	public void setVendCode(String vendCode) {
		this.vendCode = vendCode;
	}

	/**
	 * @return the vendType
	 */
	public String getTransactionType() {
		return transactionType;
	}



	/**
	 * @param vendType the vendType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	/**
	 * @return the triggerName
	 */
	public String getTriggerName() {
		return triggerName;
	}

	/**
	 * @param triggerName the triggerName to set
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

   /**
    * Transaction Types 
    * @author ramasap1
    *
    */
   public enum TransactionType{
	   
	   GPositiveAdjustment("GPositiveAdjustment"),GNegativeAdjustment("GNegativeAdjustment"),GasPurchase("GasPurchase"),ENegativeAdjustment("ENegativeAdjustment"),
	   EPositiveAdjustment("EPositiveAdjustment"),ElectricPurchase("ElectricPurchase"),Reversal("Reversal"),
	   VoidENegativeAdjustment("VoidENegativeAdjustment"),VoidGNegativeAdjustment("VoidGNegativeAdjustment"),VoidReversal("VoidReversal"),
	   Void("Void");
	   
	   private String transactionType;
	   
	   private TransactionType(String transactionType){
		   this.transactionType = transactionType;
	   }
	   
	   public String getTransactionType(){
		   return this.transactionType;
	   }
   }

}
