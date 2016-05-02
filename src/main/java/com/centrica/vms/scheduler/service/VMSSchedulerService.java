/**
 * VMSSchedulerService.java
 * Purpose: VMS Scheduler service
 * @author ramasap1
 */
package com.centrica.vms.scheduler.service;

import java.util.Date;

import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VendTransaction.Status;



/**
 * Methods for VMS Scheduler service
 */
public interface VMSSchedulerService {
	
	/**
	 * Method to accept the schedule job request
	 * @param int retryCount
	 * @param String transactionID
	 * @param Long holdingPeriod
	 * @param String pan
	 * @param String vendCode
	 * @param String paymentType
	 * @param String creditValue
	 * @param Date timestamp
	 * @return Boolean status (true/false)
	 * @throws DBException
	 */
	public Boolean scheduleJob(int retryCount,String transactionID,Long holdingPeriod,String pan,String vendCode,
			String paymentType,String creditValue,Date timestamp, Integer deviceType, Boolean isReschedule) throws DBException;
	
	/**
	 * @param retryCount
	 * @param transactionID
	 * @param holdingPeriod
	 * @param pan
	 * @param paymentType
	 * @param creditValue
	 * @param timestamp
	 * @param deviceType
	 * @param isReschedule
	 * @return
	 * @throws DBException
	 */
	public Boolean scheduleAdjustJob(int retryCount,String transactionID,Long holdingPeriod,
				String paymentType,String creditValue,Date timestamp, Boolean isReschedule) throws DBException;
	
	/**
	 * Method to accept the schedule vend history job request
	 * @param int retryCount
	 * @param String transactionID
	 * @param Long retryInterval
	 * @param Date updatedTimestamp
	 * @param int status code 
	 * @return Boolean status (true/false)
	 * @throws DBException
	 */
/*	public Boolean scheduleUpdateJob(int retryCount, String transactionID, Long retryInterval,
			Date updatedTiemstamp, int status) throws DBException;*/
	
	/**
	 * Method to accept the unschedule job request
	 * @param String transactionID
	 * @param Status
	 * @return Boolean
	 * @throws DBException
	 */
	public Boolean unScheduleJob(String transactionID,Status status) throws DBException;
	
	/**
	 * Method to accept the unschedule job request
	 * @param String transactionID
	 * @return Boolean
	 * @throws DBException
	 */
	
	public Boolean unScheduleJob(String transactionID) throws DBException;
	
	/**
	 * Method to schedule the Headend Utility
	 * @param String count
	 * @param String userName
	 * @return Integer
	 */
	public Integer scheduleHEUtility(String count,String userName, int deviceType);

	/**
	 * Method to schedule the Headend CIM Utility
	 * @param String count
	 * @param String userName
	 * @return Integer
	 */
	public Integer scheduleCIMUtility(String count,String userName);
	
/*	/**
	 * Method to schedule the SAP PI Utility
	 * @param String count
	 * @param String userName
	 * @return Integer
	 *//*
	public Integer scheduleSAPPIUtility(String count,String userName);*/
	public Boolean scheduleVendAcknowledgeSAPJob(int retryCount,String transactionID,Long holdingPeriod,String pan,String msn,String vendCode,
			String creditValue,Date timestamp, Integer status, Boolean isReschedule);
}