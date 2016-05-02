/**
 * VendTransactionQueryServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
package com.centrica.vms.ws.transaction.query;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.VMSInvalidPANException;

/**
 * VendTransactionQueryServiceSkeleton java skeleton for the axisService
 */
public class VendTransactionQueryServiceSkeleton {

	private Logger logger = ESAPI.getLogger(getClass().getName());

	/**
	 * Auto generated method signature
	 * 
	 * @param getTransactions
	 * @throws BusinessProcessingFaultException0
	 *             :
	 */

	public com.centrica.vms.ws.transaction.query.GetTransactionsResponse GetTransactions(
			com.centrica.vms.ws.transaction.query.GetTransactions getTransactions)
			throws BusinessProcessingFaultException0 {
		GetTransactionsResponse response = new GetTransactionsResponse();
		try {

			GetTransactionsResponseMessage responseMessage = new GetTransactionsResponseMessage();
			logger.debug(Logger.EVENT_SUCCESS,"Entering VendTransactionQuery GetTransactions method");
			GetTransactionsRequestMessage requestMessage = getTransactions
					.getTransactionRequest();
			List<VendTransaction> vendTxnList = null;
			if (requestMessage != null) {
				vendTxnList = getPurchaseTransactions(requestMessage,
						vendTxnList);
			} else {
				BusinessProcessingFaultException0 exception = getCustomException(
						BusinessProcessingFaultCode.MISSING_QUERY_PARAMATERS,
						"Query parameters are missing");
				throw exception;
			}
			responseMessage.setVendTransaction(vendTxnList
					.toArray(new VendTransaction[vendTxnList.size()]));
			response.setTransactionResponse(responseMessage);
			logger.debug(Logger.EVENT_SUCCESS,"Leaving VendTransactionQuery GetTransactions method");

		} catch (BusinessProcessingFaultException0 ex) {
			logger.error(Logger.EVENT_FAILURE,"Business processing exception in VendTransactionQuery GetTransactions : " + ex.getMessage());
			throw ex;
		} catch (Exception e) {
			logger.error(Logger.EVENT_FAILURE,"Exception in VendTransactionQuery GetTransactions: " + e.getMessage());
			BusinessProcessingFaultException0 exception = getCustomException(
					BusinessProcessingFaultCode.UNKNOWN_ERROR,
					"Unknown exception thrown for given parameters");
			throw exception;

		}
		return response;
	}

	private BusinessProcessingFaultException0 getCustomException(
			BusinessProcessingFaultCode businessProcessingFaultCode,
			String customMessage) {
		BusinessProcessingFaultException0 exception = new BusinessProcessingFaultException0();
		BusinessProcessingFault faultResponse = new BusinessProcessingFault();
		faultResponse.setExceptionCode(businessProcessingFaultCode);
		faultResponse.setExceptionMessage(customMessage);
		exception.setFaultMessage(faultResponse);
		return exception;
	}

	private List<VendTransaction> getPurchaseTransactions(
			GetTransactionsRequestMessage requestMessage,
			List<VendTransaction> vendTxnList)
			throws BusinessProcessingFaultException0 {

		try {
			logger.debug(Logger.EVENT_SUCCESS,"Entering getPurchaseTransactions method");
			VendTransactionQuery vendTransactionQuery = new VendTransactionQuery();
			PanNumber pan = requestMessage.getPanNumber();
			NumberOfTransactions noTxns = requestMessage
					.getNumberOfTransactions();
			TransactionStartTimestamp startTimeStamp = requestMessage
					.getTransactionStartTimestamp();
			TransactionEndTimestamp endTimeStamp = requestMessage
					.getTransactionEndTimestamp();
			if (checkIfPanIsNullOrEmpty(pan)
					&& (noTxns != null || (startTimeStamp != null && endTimeStamp != null))) {
				// startTimeStamp and endTimeStamp are optional paramaters
				if ((startTimeStamp == null && endTimeStamp == null)
						|| validDates(startTimeStamp, endTimeStamp)) {
					String panVal = pan.getPanNumber();
					if (!isValidPan(panVal)) {
						throw new VMSInvalidPANException();
					}

					String numberOfTxns = noTxns.getNumberOfTransactions();
					// Number of transactions is an optional paramater
					if (numberOfTxns == null) {
						numberOfTxns = "-1";
					}
					Calendar startTimeStampOpt = startTimeStamp == null ? null
							: startTimeStamp.getTransactionStartTimestamp();
					Calendar endTimeStampOpt = endTimeStamp == null ? null
							: endTimeStamp.getTransactionEndTimestamp();
					vendTxnList = vendTransactionQuery
							.getPurchaseTransactionsForGivenPAN(panVal, Integer
									.parseInt(numberOfTxns), startTimeStampOpt,
									endTimeStampOpt);
				} else {
					BusinessProcessingFaultException0 exception = getCustomException(
							BusinessProcessingFaultCode.INVALID_PARAMETER_FORMAT,
							"Please check the start and end dates. End date can't be today's date");
					throw exception;

				}
			} else {
				BusinessProcessingFaultException0 exception = getCustomException(
						BusinessProcessingFaultCode.MISSING_QUERY_PARAMATERS,
						"Query parameters are missing");
				throw exception;
			}

		} catch (VMSInvalidPANException e) {
			BusinessProcessingFaultException0 exception = getCustomException(
					BusinessProcessingFaultCode.INVALID_PAN,
					"Please check the PAN");
			throw exception;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getPurchaseTransactions method");
		return vendTxnList;
	}

	private boolean isValidPan(String panVal) throws VMSInvalidPANException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering method isValidPan method");
		VMSUtils vmsUtils = new VMSUtils();
		Boolean isValidPan = Boolean.FALSE;
		if (!vmsUtils.isValidPanNumber(panVal)) {
			logger.info(Logger.EVENT_UNSPECIFIED,"Invalid PAN ");
			throw new VMSInvalidPANException();
		} else {
			isValidPan = Boolean.TRUE;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving isValidPan method ");
		return isValidPan;
	}

	private boolean checkIfPanIsNullOrEmpty(PanNumber pan) {
		return pan != null && pan.getPanNumber() != null
				&& !pan.getPanNumber().isEmpty();
	}

	private boolean validDates(TransactionStartTimestamp startTimeStamp,
			TransactionEndTimestamp endTimeStamp) {
		try {
			VendTransactionUtil util = new VendTransactionUtil();
			Date formattedStartDate = util.getFormattedDate(startTimeStamp
					.getTransactionStartTimestamp());
			Date formattedEndDate = util.getFormattedDate(endTimeStamp
					.getTransactionEndTimestamp());
			Date formattedActualDate = util.getFormattedDate(Calendar
					.getInstance());

			// check if start date and end dates are before today's date and
			// start date is before or equal end date
			if (formattedStartDate.before(formattedActualDate)
					&& formattedEndDate.before(formattedActualDate)
					&& (formattedStartDate.before(formattedEndDate) || formattedStartDate
							.equals(formattedEndDate))) {
				return true;
			}
		} catch (Exception e) {
			logger.error(Logger.EVENT_FAILURE,"Exception while validating dates " + e.getMessage());
		}
		return false;

	}
}
