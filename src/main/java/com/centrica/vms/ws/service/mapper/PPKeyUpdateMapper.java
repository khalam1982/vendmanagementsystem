package com.centrica.vms.ws.service.mapper;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.Constants;
import com.centrica.vms.model.PPKeyTransaction;
import com.centrica.vms.model.PPKeyTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.model.PPKeyTxnScheduler;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyRequestMessage;

/**
 * PPKeyUpdateMapper
 * 
 * Class to Map PP Key Update Objects
 * 
 * @author chackram
 */
public class PPKeyUpdateMapper {

	private final Logger logger = ESAPI.getLogger(PPKeyUpdateMapper.class);

	/**
	 * Sets PP Key Transaction Status with the given Status Code
	 * 
	 * @param ppKeyTxnSdlr - PPKeyTxnScheduler
	 * @param statusCode - Status 
	 * @param triggerName of type String
	 * @param transactionId of type String
	 */
	public void setPPKTransStatus(final PPKeyTxnScheduler ppKeyTxnSdlr, final Status statusCode, final String triggerName, final String transactionId) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyUpdateMapper:setPPKTransStatus method");
		final Set<PPKeyTxnStatus> txnStatusDetails = ppKeyTxnSdlr.getStatusDetails();
		setPPKeyTransactionStatus(statusCode, txnStatusDetails);
		ppKeyTxnSdlr.setTriggerName(triggerName);
		ppKeyTxnSdlr.setTransactionId(transactionId);
		ppKeyTxnSdlr.setUpdatedBy(Constants.SYSTEM);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyUpdateMapper:setPPKTransStatus method");

	}

	/**
	 * Sets PP Key Transaction Status
	 * 
	 * @param statusCode - Status
	 * @param txnStatusDetails - Set<PPKeyTxnStatus>
	 */
	public void setPPKeyTransactionStatus(final Status statusCode, final Set<PPKeyTxnStatus> txnStatusDetails) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyUpdateMapper:setPPKeyTransactionStatus method");
		final PPKeyTxnStatus status = new PPKeyTxnStatus();
		status.setStatus(statusCode.getStatus());
		status.setUpdatedDate(new Date());
		txnStatusDetails.add(status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyUpdateMapper:setPPKeyTransactionStatus method");

	}

	/**
	 * Updates the PPKey Transaction status
	 * 
	 * @param txnStatusDetails - Set<PPKeyTxnStatus>
	 */
	public void updateTimestamp(final PPKeyTxnScheduler ppKeyTxnSdlr) {
		
		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyUpdateMapper:updateTimestamp method");
		Iterator<PPKeyTxnStatus> itr =  ppKeyTxnSdlr.getStatusDetails().iterator();
		if (itr.hasNext()) { 
			PPKeyTxnStatus ppKeyTxnStatus = itr.next();
			ppKeyTxnStatus.setUpdatedDate(new Date());
			ppKeyTxnSdlr.setUpdatedBy(Constants.SYSTEM);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyUpdateMapper:updateTimestamp method");
		
	}

	/**
	 * Maps PPKeyTransaction from UpdatePPKeyRequestMessage
	 * 
	 * @param request - UpdatePPKeyRequestMessage
	 * @param statusCode - Status
	 * @return PPKeyTransaction
	 */
	public PPKeyTransaction mapPPKeyTransactionRequest(final UpdatePPKeyRequestMessage request, final Status statusCode) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyUpdateMapper:mapPPKeyTransactionRequest method");
		final PPKeyTransaction ppkTrans = new PPKeyTransaction();
		ppkTrans.setTransactionId(request.getPPKeyRequestIdentifier());
		ppkTrans.setCreatedBy(Constants.SYSTEM);
		if( request.getPPKeyRequestDateTime() != null ) {
			ppkTrans.setRequestDate(request.getPPKeyRequestDateTime().getTime());
		}
		ppkTrans.setMpxn(request.getMpxn());
		ppkTrans.setMsn(request.getMsn());
		ppkTrans.setPpKey(request.getPPKey());
		ppkTrans.setUpdatedBy(Constants.SYSTEM);
		ppkTrans.setUpdatedDate(new Date());

		final Set<PPKeyTxnStatus> txnStatusDetails = new HashSet<PPKeyTxnStatus>();
		setPPKeyTransactionStatus(statusCode, txnStatusDetails);
		ppkTrans.setStatusDetails(txnStatusDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyUpdateMapper:mapPPKeyTransactionRequest method");
		return ppkTrans;

	}

}
