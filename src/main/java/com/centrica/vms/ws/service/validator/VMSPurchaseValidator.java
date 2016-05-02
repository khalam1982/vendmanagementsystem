package com.centrica.vms.ws.service.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.VMSMaxVendRequestException;
import com.centrica.vms.exception.VMSVendSuspendedException;
import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;

/**
 * VMSPurchaseValidator - Validates Payment Request
 */
public class VMSPurchaseValidator {

	private Logger logger = ESAPI.getLogger(getClass().getName());

	private static final String DATE_FORMAT = "dd/MM/yyyy";

	private final WSTransactionDAO wsTransDao;

	/**
	 * Constructor
	 */
	public VMSPurchaseValidator() {
		wsTransDao = new WSTransactionDAO();
	}

	/**
	 * Validates vend request for the given PAN.
	 * Gets maximum allowed vend transaction request for the given source
	 * and checks the number of transactions happened for the given PAN
	 * and for the given Vend Request Date. If number of request exceeds 
	 * for the given source and for the given transaction type, exception is thrown.
	 * 
	 * @param source of type String
	 * @param panNo of type String
	 * @param transType of type String
	 * @return boolean
	 * @throws VMSMaxVendRequestException
	 */
	public boolean validatePurchaseRequestForMaxVend(final String source, final String panNo, final TransactionType transType) throws VMSMaxVendRequestException {

		boolean isValid = true;
		try {
			final SourceDetails sourceDetails = wsTransDao.getSourceDetails(source);
			if( sourceDetails != null && sourceDetails.getMaxVend() != null && sourceDetails.getMaxVend() != -1 ) {
				final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
				final String reqDate = dateFormat.format(new Date());
				final int totalTransactions = wsTransDao.fetchTotalPurchaseTransactions(panNo, reqDate, transType.getTransactionType());
				if( totalTransactions >= sourceDetails.getMaxVend() ) {
					isValid = false;
					logger.info(Logger.EVENT_UNSPECIFIED,"Already enough number of transactions are raised, cant allow another online payment request");
					throw new VMSMaxVendRequestException();
				}
			}
		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception thrown " + e.getMessage());
		}
		return isValid;

	}

	/**
	 * Checks for Vend Suspension for the given Meter.
	 * If the Meter is suspended for vending, 
	 * the Vend Transaction Status is set to 1. 
	 * 
	 * @param meterDetails - MeterDetails
	 * @return boolean
	 * @throws VMSVendSuspendedException
	 */
	public boolean checkVendSuspensionStatus(final MeterDetails meterDetails) throws VMSVendSuspendedException {

		boolean isValid = true;
		if( meterDetails.getVendTxnStatus() != null && meterDetails.getVendTxnStatus() == 1 ) {
			isValid = false;
			logger.info(Logger.EVENT_UNSPECIFIED,"Vend Suspended for the Meter, cant vend now.");
			throw new VMSVendSuspendedException();
		}
		return isValid;

	}

}
