/**
 * VendTransaction.java
 * Purpose: Vend transaction
 * @author ramasap1
 */
package com.centrica.vms.model;
 
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.centrica.vms.model.VendTxnStatus;

/**
 * Methods for vend transaction
 */
public class VendTransaction {

	
	private String transactionID;
	
	private String originalTransactionID;

	private Date createdTimeStamp;
	
	private Date actualTimeStamp;
	
	private String updatedBy = null;
	
	private String createdBy = null;
	

	
	private Set<VendTxnStatus> txnStatusDetails = null;
	
	/*
	 * Method to get the actual time stamp
	 * @param
	 * @return java.util.Date
	 */
	public Date getActualTimeStamp() {
		return actualTimeStamp;
	}

	/*
	 * Method to set the actual time stamp
	 * @param java.util.Date - actual time stamp
	 * @return
	 */
	public void setActualTimeStamp(Date actualTimeStamp) {
		this.actualTimeStamp = actualTimeStamp;
	}

	/*
	 * Method to get the vend code time stamp
	 * @param
	 * @return java.util.Date 
	 */
	public Date getVendcodeTimeStamp() {
		return vendcodeTimeStamp;
	}

	/*
	 * Method to set the vend code time stamp
	 * @param java.util.Date - vend code time stamp
	 * @return
	 */
	public void setVendcodeTimeStamp(Date vendcodeTimeStamp) {
		this.vendcodeTimeStamp = vendcodeTimeStamp;
	}


	private Date vendcodeTimeStamp;
	
	private Integer version;

	/*
	 * Method to get the version
	 * @param
	 * @return java.lang.Integer
	 */
	public Integer getVersion() {
		return version;
	}

	/*
	 * Method to set the version
	 * @param java.lang.Integer
	 * @return
	 */
	private void setVersion(Integer version) {
		this.version = version;
	}

	/*
	 * Method to get the original transaction ID
	 * @param java.lang.String
	 * @return
	 */
	public String getOriginalTransactionID() {
		return originalTransactionID;
	}

	/*
	 * Method to set the original transaction ID
	 * @param java.lang.String - original transaction ID
	 * @return
	 */
	public void setOriginalTransactionID(String originalTransactionID) {
		this.originalTransactionID = originalTransactionID;
	}

	/**
	 * Method to get the transaction ID
	 * @param
	 * @return java.lang.String - the transactionID
	 */
	public String getTransactionID() {
		return transactionID;
	}

	/**
	 * Method to set the transaction ID
	 * @param java.lang.String - the transactionID to set
	 * @return
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	
	
	
	/**
	 * Method to get the created time stamp
	 * @param
	 * @return java.util.Date - the createdTimeStamp
	 */
	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	/**
	 * Method to set the created time stamp
	 * @param java.util.Date - createdTimeStamp
	 * @return
	 */
	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}


	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Method used to get the transaction status details
	 * @return
	 */
	public Set<VendTxnStatus> getTxnStatusDetails() {
		return txnStatusDetails;
	}

	/**
	 * Method used to set the transaction status details
	 * @param txnStatusDetails
	 */
	public void setTxnStatusDetails(Set<VendTxnStatus> txnStatusDetails) {
		this.txnStatusDetails = txnStatusDetails;
	}
	
	/**
	 * Get the latest status code for the vend transaction
	 *  
	 * @return
	 */
	public String getStatusDesc() {
		StatusDetails statusDetails = null;
		String statusDesc = null;
		Iterator<VendTxnStatus> iterator = this.txnStatusDetails.iterator();
		if(iterator!=null){
			if(iterator.hasNext()){
				VendTxnStatus vendTxnStatus = iterator.next();
				statusDetails = vendTxnStatus.getStatusDetails();
			}
		}
		if (statusDetails != null) {
			statusDesc = statusDetails.getStatusDesc();
		} 
		iterator = null;
		statusDetails = null;
		return statusDesc;
	}
	
	/**
	 * @return
	 */
	public Integer getStatusCode() {
		StatusDetails statusDetails = null;
		Integer statusCode = null;
		Iterator<VendTxnStatus> iterator = this.txnStatusDetails.iterator();
		if(iterator!=null){
			if(iterator.hasNext()){
				VendTxnStatus vendTxnStatus = iterator.next();
				statusDetails = vendTxnStatus.getStatusDetails();
			}
		}
		if (statusDetails != null) {
			statusCode = statusDetails.getStatusCode();
		} 
		iterator = null;
		statusDetails = null;
		return statusCode;
	}
	
	public enum Status{
		
	     SC_10(10), SC_15(15), SC_20(20), SC_25(25), SC_30(30), SC_35(35), SC_40(40), SC_45(45), SC_50(50), SC_55(55), SC_60(60), 
	     SC_65(65), SC_70(70), SC_75(75), SC_90(90), SC_95(95), SC_96(96), SC_100(100),SC_101(101),SC_102(102),SC_105(105), SC_106(106), SC_107(107), SC_108(108), SC_109(109), 
	     SC_110(110),SC_115(115),SC_120(120),SC_125(125),SC_130(130),
	     SC_135(135),SC_140(140),SC_145(145),SC_150(150),SC_155(155),SC_160(160),SC_165(165),SC_170(170),SC_180(180),
	     SC_190(190),SC_200(200),SC_300(300),SC_310(310),SC_320(320),SC_330(330),SC_340(340),SC_350(350),SC_360(360),SC_370(370),SC_380(380),
	     SC_390(390),SC_400(400),SC_410(410), SC_80(80), SC_151(151), SC_152(152),SC_153(153),SC_154(154),
	     SC_515(515), SC_520(520), SC_570(570), SC_600(600);

		 private int status;
		   
		 private Status(int status){
		   this.status = status;
		 }
		 public int getStatus(){
		   return this.status;
		 }
		 
		 public static Status getStatus(int status) {
			 for (Status s : Status.values()) {
				 if (s.getStatus() == status) {
					 return s;
				 }
			 }
			 return null;
		 }
		 
		 
	   }
	
}
