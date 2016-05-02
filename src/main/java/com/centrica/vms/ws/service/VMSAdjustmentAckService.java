package com.centrica.vms.ws.service;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.StatusAckCode;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.VMSInvalidPANException;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.sap.adjustment.model.DeliveryReceiptResponse;
import com.centrica.vms.ws.sap.adjustment.model.DeliveryStatusCode;
import com.centrica.vms.ws.sap.adjustment.model.ResponseCode;
import com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDelivery;
import com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDeliveryResponse;

public class VMSAdjustmentAckService {
	
	private Logger logger = ESAPI.getLogger(getClass().getName());
	private static final String VMS_USERNAME_VALUE="system";
	private WSTransactionDAO wsTransactionDAO = new WSTransactionDAO();
	
	public AcknowledgePaymentDeliveryResponse handleAckRequest(AcknowledgePaymentDelivery acknowledgePaymentDelivery) 
		throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering handleAckRequest method");
		AcknowledgePaymentDeliveryResponse acknowledgePaymentDeliveryResponse = new AcknowledgePaymentDeliveryResponse();
		String transactionID = acknowledgePaymentDelivery.getDeliveryReceipt().getTransactionID();
		ResponseCode deliveryStatus = null;
		deliveryStatus = acknowledgePaymentDelivery.getDeliveryReceipt().getDeliveryStatus();
		logger.info(Logger.EVENT_UNSPECIFIED,"Request details"
				+ new Object[] { transactionID, deliveryStatus });
		try {
			updateDeliveryStatus(transactionID, deliveryStatus);
			acknowledgePaymentDeliveryResponse = prepareDeliveryResponse(transactionID, StatusAckCode.ACCEPTED.getStatusCode());
		} catch (DBException ex) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown");
			acknowledgePaymentDeliveryResponse = prepareDeliveryResponse(transactionID, StatusAckCode.INVALID.getStatusCode());
		}
		transactionID = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving handleAckRequest method ");
		return acknowledgePaymentDeliveryResponse;
	}
	
	/**
	 *  Method to update the delivery status
	 * @param transactionID
	 * @param headendStatus
	 * @throws DBException
	 * @throws VMSInvalidPANException 
	 */
	private void updateDeliveryStatus(String transactionID,
			ResponseCode adjustmentStatus) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateDeliveryStatus method");
		Integer status = null;
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id : "+ transactionID);
		VendTxnWSDetails vendTxnWSDetails = wsTransactionDAO.getVendTxnWSDetails(transactionID);
		
		if(vendTxnWSDetails!=null){
			Iterator<VendTxnStatus> itr = vendTxnWSDetails.getTxnStatusDetails().iterator();
			status = getVendTxnStatus(itr);
			/*
			 * Update the acknowledgement status when the previous status is
			 * sent to head end or vend code retry was in progress (in case of reversal)
			 */ 
			if(status==Status.SC_200.getStatus() || status==Status.SC_170.getStatus()){
				if (adjustmentStatus.getResponseCode() == StatusAckCode.ACCEPTED.getStatusCode()) {
					status = Status.SC_100.getStatus();	
				} else if (adjustmentStatus.getResponseCode() == StatusAckCode.INVALID.getStatusCode()) {
					status = Status.SC_110.getStatus();	
				} else if (adjustmentStatus.getResponseCode() == StatusAckCode.ERROR.getStatusCode()) {
					status = Status.SC_105.getStatus();
				} else if (adjustmentStatus.getResponseCode() == StatusAckCode.DUPLICATE.getStatusCode()) {
					status = Status.SC_106.getStatus();
				} else if (adjustmentStatus.getResponseCode() == StatusAckCode.KEYPAD_LOCK.getStatusCode()) {
					status = Status.SC_107.getStatus();
				} else if (adjustmentStatus.getResponseCode() == StatusAckCode.MAX_CREDIT.getStatusCode()) {
					status = Status.SC_108.getStatus();
				} else if (adjustmentStatus.getResponseCode() == StatusAckCode.UNKNOWN.getStatusCode()) {
					status = Status.SC_109.getStatus();
				} else {
					
				}
			}
			updateVendTxnStatus(vendTxnWSDetails, status);
		} else {
			logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id "+ transactionID +"is voided");
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction status" + status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateDeliveryStatus method");
	}
	
	/**
	 * Method to prepare the delivery response
	 * @param transactionID
	 * @param statusCode
	 * @return
	 */
	private AcknowledgePaymentDeliveryResponse prepareDeliveryResponse(String transactionID, int statusCode) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareDeliveryResponse method");
		AcknowledgePaymentDeliveryResponse acknowledgePaymentDeliveryResponse = new AcknowledgePaymentDeliveryResponse();
		DeliveryReceiptResponse deliveryReceiptResponse = new DeliveryReceiptResponse();
		deliveryReceiptResponse.setTransactionID(transactionID);
		DeliveryStatusCode deliveryStatusCode = new DeliveryStatusCode();
		deliveryStatusCode.setDeliveryStatusCode(statusCode);
		deliveryReceiptResponse.setDeliveryStatus(deliveryStatusCode);
		acknowledgePaymentDeliveryResponse.setDeliveryReceiptResponse(deliveryReceiptResponse);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareDeliveryResponse method");
		return acknowledgePaymentDeliveryResponse;
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
