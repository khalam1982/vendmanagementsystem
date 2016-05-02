package com.centrica.vms.ws.service;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import smartmeterprocessing.enterprise.uk.co.britishgas.ReverseVendResponseMessage;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcome;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.sap.service.ReverseVendResponse;
import common.enterprise.uk.co.britishgas.BG_Log;

public class VMSReversalServiceFactory {

	private static final String DATABASE_EXCEPTION = "DATABASE EXCEPTION";
	
	private static final String REVERSAL_REJECTED = "REVERSAL REJECTED";

	private Logger logger = ESAPI.getLogger(getClass().getName());

	private ReverseVendResponse reversalFaultResponse;
	
	private final WSTransactionDAO wsTransactionDAO;
	
	/**
	 * Constructor
	 */
	public VMSReversalServiceFactory() {
		wsTransactionDAO = new WSTransactionDAO();
	}
	
	/**
	 * Constructor
	 * @param wsTransactionDAO - WSTransactionDAO
	 */
	public VMSReversalServiceFactory(final WSTransactionDAO wsTransactionDAO) {
		this.wsTransactionDAO = wsTransactionDAO;
	}
	
	public VMSReversalService fetchReversalService(String originalVendRequestKey) {
		try {
			VendTxnWSDetails vendTransactionDetails = wsTransactionDAO.getVendTxnWSDetails(originalVendRequestKey);
			if (VMSUtils.internalChannel(vendTransactionDetails.getSource())) {
				return new VMSInternalChannelReversalService();
			}
		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown");
			setReversalFaultResponse(prepareReversalFaultResponse(VendOutcomeCode_type1.value4, DATABASE_EXCEPTION, new ReverseVendResponse()));
			return null;

		}
		return new VMSReversalService();
	}

	protected ReverseVendResponse prepareReversalFaultResponse(VendOutcomeCode_type1 faultCode, String errorMessage, ReverseVendResponse paymentResponse) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareReversalFaultResponse method");
		logger.info(Logger.EVENT_UNSPECIFIED,"errorMessage " + errorMessage);
		VMSUtils vmsUtils = new VMSUtils();
		ReverseVendResponseMessage reverseVendResponseMessage = new ReverseVendResponseMessage();
		VendOutcome vendOutcome = new VendOutcome();
		vendOutcome.setVendOutcomeCode(faultCode);
		vendOutcome.setVendOutcomeDescription(REVERSAL_REJECTED);
		reverseVendResponseMessage.setVendOutcome(vendOutcome);
		BG_Log log = vmsUtils.getBGLogForFault(faultCode, errorMessage);
		reverseVendResponseMessage.setLog(log);
		paymentResponse.setReverseTxnResponse(reverseVendResponseMessage);
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareReversalFaultResponse method");
		return paymentResponse;
	}

	public void setReversalFaultResponse(ReverseVendResponse reversalFaultResponse) {
		this.reversalFaultResponse = reversalFaultResponse;
	}

	public ReverseVendResponse getReversalFaultResponse() {
		return reversalFaultResponse;
	}

}
