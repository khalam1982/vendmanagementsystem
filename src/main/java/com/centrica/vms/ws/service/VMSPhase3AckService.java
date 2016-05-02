package com.centrica.vms.ws.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.StatusAckCode;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.EPaymentKeyInvalidException;
import com.centrica.vms.exception.EPaymentKeyOutOfRangeException;
import com.centrica.vms.exception.LGLicenceException;
import com.centrica.vms.exception.UnknownBreakupException;
import com.centrica.vms.exception.VMSAppException;
import com.centrica.vms.exception.VMSInvalidPANException;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.external.service.SAPService;
import com.centrica.vms.scheduler.service.VMSSchedulerService;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceipt;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceiptResponse;
import com.centrica.vms.ws.headend.ack.model.DeliveryStatusCode;
import com.centrica.vms.ws.headend.ack.model.MeterSource;
import com.centrica.vms.ws.headend.ack.model.ResponseCode;
import com.centrica.vms.ws.model.VendTxnWSDetails;

public class VMSPhase3AckService extends VMSAckVoidService {
	
	private Logger logger = ESAPI.getLogger(getClass().getName());
	private static final String VMS_USERNAME_VALUE="system";
	
	public DeliveryReceiptResponse handleAckRequest(DeliveryReceipt deliveryReceipt) throws EPaymentKeyInvalidException, 
		EPaymentKeyOutOfRangeException, UnknownBreakupException, VMSInvalidPANException, LGLicenceException, VMSAppException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering handleAckRequest method");
		DeliveryReceiptResponse deliveryReceiptResponse = new DeliveryReceiptResponse();
		String transactionID = deliveryReceipt.getTransactionID();
		ResponseCode deliveryStatus = null;
		deliveryStatus = deliveryReceipt.getDeliveryStatus();
		String msn = deliveryReceipt.getMsn();
		String vendcode = deliveryReceipt.getVendCode();
		Calendar timestamp = deliveryReceipt.getTimestamp();
		logger.info(Logger.EVENT_UNSPECIFIED,"Request details"
				+ new Object[] { transactionID, deliveryStatus });
		try {
			// Get the transaction ID first based on MSN and Vend code
			if (transactionID.equals("000000000000000000")) {
				transactionID = wsTransactionDAO.getVendTxnWSDetails(msn, vendcode);
			}
			VendTxnWSDetails vendTxnWSDetails = wsTransactionDAO.getVendTxnWSDetails(transactionID);
			Integer existingStatus = getExistingStatus(vendTxnWSDetails);
			if (!new VendRetryService().retryVend(vendTxnWSDetails, deliveryReceipt, existingStatus)) {
				updateDeliveryStatus(transactionID, msn, vendcode, timestamp,
						deliveryStatus, deliveryReceipt.getMeterSource());
					// Call SAP acknowledgement only if there wasn't already a
					// success ACK received by VMS.
					if (existingStatus != Status.SC_100.getStatus()) {
						new SAPService().sendAcknowledgementToSAP(transactionID);
					}
			}
			deliveryReceiptResponse = prepareDeliveryResponse(transactionID, StatusAckCode.ACCEPTED.getStatusCode());
		} catch (DBException ex) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown");
			deliveryReceiptResponse = prepareDeliveryResponse(transactionID, StatusAckCode.INVALID.getStatusCode());
		}
		transactionID = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving handleAckRequest method ");
		return deliveryReceiptResponse;
	}



	private Integer getExistingStatus(VendTxnWSDetails vendTxnWSDetails) {
		Integer status = 0;
		if(vendTxnWSDetails!=null){
			Iterator<VendTxnStatus> itr = vendTxnWSDetails.getTxnStatusDetails().iterator();
			status = getVendTxnStatus(itr);
		}
		return status;
	}
	
	

	/**
	 *  Method to update the delivery status
	 * @param transactionID
	 * @param headendStatus
	 * @param meterSource 
	 * @throws DBException
	 * @throws EPaymentKeyInvalidException 
	 * @throws EPaymentKeyOutOfRangeException 
	 * @throws UnknownBreakupException 
	 * @throws VMSInvalidPANException 
	 * @throws LGLicenceException 
	 */
	private void updateDeliveryStatus(String transactionID, String msn, String vendcode, Calendar timestamp,
			ResponseCode headendStatus, MeterSource meterSource) throws DBException, EPaymentKeyInvalidException, 
			EPaymentKeyOutOfRangeException, UnknownBreakupException, VMSInvalidPANException, LGLicenceException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateDeliveryStatus method");
		Integer status = null;
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id : "+ transactionID);
		VendTxnWSDetails vendTxnWSDetails = wsTransactionDAO.getVendTxnWSDetails(transactionID);
		
		if(vendTxnWSDetails!=null){
			Iterator<VendTxnStatus> itr = vendTxnWSDetails.getTxnStatusDetails().iterator();
			status = getVendTxnStatus(itr);

			//Update top up source if meter source is not empty.
			if (meterSource != null) {
				vendTxnWSDetails.setTopUpSource(getSourceString(meterSource));
			} else {
				vendTxnWSDetails.setTopUpSource("OTA");
			}
			/*
			 * Update the acknowledgement status when the previous status is
			 * sent to head end or vend code retry was in progress (in case of reversal)
			 */ 
			if(status==Status.SC_200.getStatus() || status==Status.SC_170.getStatus() || status == Status.SC_120.getStatus() || presumedEnded(status)){
				if(status==Status.SC_170.getStatus() || status == Status.SC_120.getStatus()){
					// Acknowledgement received when request is in retry, remove job from the scheduler
					logger.info(Logger.EVENT_UNSPECIFIED,"Unschedule job from the scheduler with no status update");
					VMSSchedulerService vmsschedulerService = new VMSSchedulerServiceImpl();
					Boolean unSchedulestatus = vmsschedulerService.unScheduleJob(transactionID);	
					logger.info(Logger.EVENT_UNSPECIFIED,"UnSchedule status is -"+unSchedulestatus);
				}
				if (headendStatus.getValue() == StatusAckCode.ACCEPTED.getStatusCode()) {
					status = Status.SC_100.getStatus();	
				} else if (headendStatus.getValue() == StatusAckCode.INVALID.getStatusCode()) {
					status = Status.SC_110.getStatus();	
				} else if (headendStatus.getValue() == StatusAckCode.ERROR.getStatusCode()) {
					status = Status.SC_105.getStatus();
				} else if (headendStatus.getValue() == StatusAckCode.DUPLICATE.getStatusCode()) {
					status = Status.SC_106.getStatus();
				} else if (headendStatus.getValue() == StatusAckCode.KEYPAD_LOCK.getStatusCode()) {
					status = Status.SC_107.getStatus();
				} else if (headendStatus.getValue() == StatusAckCode.MAX_CREDIT.getStatusCode()) {
					status = Status.SC_108.getStatus();
				} else if (headendStatus.getValue() == StatusAckCode.UNKNOWN.getStatusCode()) {
					status = Status.SC_109.getStatus();
				}
				updateVendTxnStatus(vendTxnWSDetails, status);
			
		} else if (status == Status.SC_180.getStatus()) { // ACK received for Voided Vend
				logger.info(Logger.EVENT_UNSPECIFIED,"ACK received for Voided Vend");
				status = Status.SC_190.getStatus();
				updateVendTxnStatus(vendTxnWSDetails, status);
				//Trigger the Vend acknowledgement to SAP to ACK received for voided vend
				//new SAPService().sendAcknowledgementToSAP(transactionID);
				//Commented the above line as this is a duplicate call. Please refer the caller method of this method.
				// Trigger Negative Adjustment
				try {
					logger.info(Logger.EVENT_UNSPECIFIED,"Original Transaction ID : " + transactionID);
					if (headendStatus.getValue() != StatusAckCode.UNKNOWN.getStatusCode()) { 
						//Don't do adjustment if the original vend failed due to network issue
						processNegtiveAdjustment(vendTxnWSDetails,
								wsTransactionDAO.getVoidTxnID(transactionID));
					}
				} catch (EPaymentKeyInvalidException e) {
					logger.error(Logger.EVENT_FAILURE,"EPaymentKeyInvalidException::" + e.getMessage());
					throw e;
				} catch (EPaymentKeyOutOfRangeException e) {
					logger.error(Logger.EVENT_FAILURE,"EPaymentKeyOutOfRangeException::" + e.getMessage());
					throw e;
				} catch (UnknownBreakupException e) {
					logger.error(Logger.EVENT_FAILURE,"UnknownBreakupException::" + e.getMessage());
					throw e;
				} catch (VMSInvalidPANException e) {
					logger.error(Logger.EVENT_FAILURE,"VMSInvalidPANException::" + e.getMessage());
					throw e;
				} catch (LGLicenceException e) {
					logger.error(Logger.EVENT_FAILURE,"LGLicenceException::" + e.getMessage());
					throw e;
				} catch (VMSAppException e) {
					logger.error(Logger.EVENT_FAILURE,"VMSAppException::" + e.getMessage());
					throw e;
				}
			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id "+ transactionID +"is voided");
			}
		}
			logger.info(Logger.EVENT_UNSPECIFIED,"Transaction status" + status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateDeliveryStatus method");
	}



	private String getSourceString(MeterSource meterSource) {
		int source = meterSource.getValue();
		switch (source) {
		case 1:
			return "IHD";
		case 2:
			return "OTA";
		case 3:
			return "METER";
		default:
			return "OTA"; //Default to OTA if source is not received from Headend.
		}
	}
	
	private boolean presumedEnded(Integer status) {
		if (presumedAck(status) || presumedFailedAtHES(status)) {
			return true;
		}
		return false;
	}



	private boolean presumedFailedAtHES(Integer status) {
	/* 300 - HEADEND_UNHANDLED ERROR
	 * 310 - HEADEND_DEVICE DOES N0T EXIST
	 * 320 - HEADEND_MULTIPLE DEVICE EXIST
	 * 330 - HEADEND_DEVICE NOT ACTIVE
	 * 340 - HEADEND_DEVICE NOT CONNECTED
	 * 350 - HEADEND_DEVICE NOT IN PREPAYMENT MODE
	 * 370 - COMMUNICATION FAILURE IN HEADEND
	 * 125 - UNSENT TO HEADEND
	 * 
	 * One of the above errors mean that the initial "applyVendCode" synchronous call or its 
	 * equivalent has failed at headend, but an ACK is received possibly because the customer applied it manually
	 * 
	 */
		
		if (status == Status.SC_125.getStatus()
				|| status == Status.SC_300.getStatus()
				|| status == Status.SC_310.getStatus()
				|| status == Status.SC_320.getStatus()
				|| status == Status.SC_330.getStatus()
				|| status == Status.SC_340.getStatus()
				|| status == Status.SC_350.getStatus()
				|| status == Status.SC_370.getStatus()) {
			return true;
		}
		return false;
	}



	private boolean presumedAck(Integer status) {
		/* 100 - HEADEND_RECEIVED BY METER
		 * 105 - REJECTED ERROR
		 * 106 - REJECTED DUPLICATE TOP UP
		 * 107 - REJECTED KEYPAD LOCK
		 * 108 - REJECTED MAX CREDIT REACHED
		 * 109 - REJECTED UNKNOWN
		 * 110 - HEADEND_NOT RECEIVED BY METER
		 * 190 - ACK RECEIVED FOR VOIDED VEND
		 * 
		 * One of the above codes mean that the current ACK is not the first ACK for the vend. 
		 * 100 means the previous ACK was a success. Other codes mean the previous ACK was a failure.
		 * 
		 * Possible reasons could be because the meter was R2S2 or because a manual vend was attempted on meter
		 * 
		 */
		
		if (status == Status.SC_100.getStatus()
				|| status == Status.SC_105.getStatus()
				|| status == Status.SC_106.getStatus()
				|| status == Status.SC_107.getStatus()
				|| status == Status.SC_108.getStatus()
				|| status == Status.SC_109.getStatus()
				|| status == Status.SC_110.getStatus()
				|| status == Status.SC_190.getStatus()) {
			return true;
		}
		return false;
	}



	/**
	 * Method to prepare the delivery response
	 * @param transactionID
	 * @param statusCode
	 * @return
	 */
	private DeliveryReceiptResponse prepareDeliveryResponse(String transactionID, int statusCode) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareDeliveryResponse method");
		DeliveryReceiptResponse deliveryReceiptResponse = new DeliveryReceiptResponse();
		deliveryReceiptResponse.setTransactionID(transactionID);
		if (statusCode == DeliveryStatusCode.value1.getValue()) {
			deliveryReceiptResponse.setDeliveryStatus(DeliveryStatusCode.value1);
		} else {
			deliveryReceiptResponse.setDeliveryStatus(DeliveryStatusCode.value2);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareDeliveryResponse method");
		return deliveryReceiptResponse;
	}


	/**
	 * @param itr
	 * @return
	 */
	protected Integer getVendTxnStatus(Iterator<VendTxnStatus> itr) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendTxnStatus method");
		Integer vendTransactionStatus = 0;
		VendTxnStatus vendTxnStatus = null;
		if (itr != null) {
			if (itr.hasNext()) {
				vendTxnStatus = itr.next();
				vendTransactionStatus = vendTxnStatus.getStatus();
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendTxnStatus method");
		return vendTransactionStatus;
	}
	
	/**
	 * @param vendTxnWSDetails
	 * @param status
	 * @throws DBException
	 */
	private void updateVendTxnStatus(VendTxnWSDetails vendTxnWSDetails, int status) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateVendTxnStatus method ");
		VendTxnStatus vendTxnStatus = new VendTxnStatus();
		Set<VendTxnStatus> txnStatusDetails = vendTxnWSDetails.getTxnStatusDetails();
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend Transaction Status : " + status);
		vendTxnStatus.setStatus(status);
		vendTxnStatus.setUpdatedTimeStamp(new Date());
		vendTxnWSDetails.setUpdatedBy(VMS_USERNAME_VALUE);
		txnStatusDetails.add(vendTxnStatus);
		vendTxnWSDetails.setTxnStatusDetails(txnStatusDetails);
		wsTransactionDAO.update(vendTxnWSDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateVendTxnStatus method ");
	}

}
