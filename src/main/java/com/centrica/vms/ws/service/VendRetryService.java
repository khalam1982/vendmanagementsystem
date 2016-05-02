package com.centrica.vms.ws.service;

import javax.naming.NamingException;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.DeviceTypeConstants;
import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.external.model.VendRetryConfig;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceipt;
import com.centrica.vms.ws.model.VendRetryDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;

/**
 * Service Class that provides ability to schedule a vend retry if any error
 * related to network issue between Headend and Comms Hub is received by VMS as
 * a negative acknowledgement
 * 
 */
public class VendRetryService {

	private static final String DEFAULT_ELIGIBLE_STATUS = "350";

	private static final String RETRY_CONFIG_SEPARATOR = ",";

	private static final int INITIAL_RETRY_COUNT = 0;

	private static final String DEFAULT_RETRY_PERIOD = "900,900,1800,14400";

	private static final String DEFAULT_MAX_RETRIES = "4";

	private Logger logger = ESAPI.getLogger(getClass().getName());

	private final WSTransactionDAO wsTransactionDAO;
	private final VMSSchedulerServiceImpl vmsSchdulerService;
	private final VMSUtils vmsUtils;

	public VendRetryService() {
		this(new WSTransactionDAO(), new VMSSchedulerServiceImpl(), new VMSUtils());
	}

	/**
	 * Constructor created for the purpose of injecting dependencies
	 * 
	 * @param wsTransactionDAO
	 * @param vmsSchdulerService
	 * @param vmsUtils
	 */
	public VendRetryService(WSTransactionDAO wsTransactionDAO, VMSSchedulerServiceImpl vmsSchdulerService, VMSUtils vmsUtils) {
		this.wsTransactionDAO = wsTransactionDAO;
		this.vmsSchdulerService = vmsSchdulerService;
		this.vmsUtils = vmsUtils;

	}

	/**
	 * Method that validates the status in negative acknowledgement and attempts
	 * to retry sending the vend to HES after a certain wait time if the
	 * attempts have not exceeded permissible limits.
	 * 
	 * @param vendTxnWSDetails
	 *            - transactionDetails of the original transaction
	 * @param deliveryReceipt
	 *            - NACK response from HES
	 * @return - boolean - true if vend code is rescheduled, false if not.
	 */
	public boolean retryVend(VendTxnWSDetails vendTxnWSDetails, DeliveryReceipt deliveryReceipt, Integer existingStatus) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering retryVend method");

		boolean retry = false;
		// 1. Check if the status is in the list of statuses that could be
		// retried. If not no retries needed.
		VendRetryConfig retryConfig = getConfig();
		if (isRetriable(deliveryReceipt, retryConfig, existingStatus)) {

			// 2. Fetch/initialize retryCount values from VEND_RETRY table in DB
			VendRetryDetails vendRetryDetails = getVendRetryDetails(vendTxnWSDetails);
			// If any DBException in above call, null is returned in which case
			// we should resort to existing process
			if (vendRetryDetails == null)
				return retry;

			// 3. Fetch retryPeriod and maxRetryCount from config
			int retryCount = vendRetryDetails.getRetryCount();
			int maxRetryCount = Integer.parseInt(retryConfig.getRetries());

			// 4. Check if retryCount exceeds maximum retries. If so, then no
			// more retries needed.
			// Set to only "less than" below as the retry count is not yet
			// incremented.
			if (retryCount < maxRetryCount) {

				// 5. Parse the "wait period" for the next retry from config
				Long retryPeriod = Long.parseLong(retryConfig.getRetryPeriod().split(RETRY_CONFIG_SEPARATOR)[retryCount]);

				// 6. Increment retrycount & store the new retryCount value
				vendRetryDetails.setRetryCount(++retryCount);
				retry = updateNewRetryCount(vendRetryDetails);

				// 7. Reschedule VendCodeJob to run after retryPeriod.
				if (retry) {
					retry = rescheduleVendCode(vendTxnWSDetails, retryPeriod);
				}
			}
		}

		logger.debug(Logger.EVENT_SUCCESS,"Leaving retryVend method");
		return retry;
	}

	/*
	 * private boolean isRetriable(DeliveryReceipt deliveryReceipt) {
	 * logger.info("Entering isRetriable method"); boolean retriable = false;
	 * try { retriable =
	 * wsTransactionDAO.getRetryEligibility(deliveryReceipt.getDeliveryStatus
	 * ().getValue()); } catch (DBException e) { logger
	 * .error("DBException in trying to get retry eligility. Skipping retry process"
	 * ); } logger.info("Leaving isRetriable method"); return retriable; }
	 */

	private boolean isRetriable(DeliveryReceipt deliveryReceipt, VendRetryConfig retryConfig, Integer existingStatus) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering isRetriable method");
		boolean retriable = false;
		if (existingStatus == Status.SC_180.getStatus()) {
			return false; //If this is already assumed to be a void-ed vend then we don't need to retry.
		}
		String[] eligibleStatuses = retryConfig.getEligibleStatus().split(RETRY_CONFIG_SEPARATOR);
		for (String status : eligibleStatuses) {
			if (Integer.parseInt(status) == deliveryReceipt.getDeliveryStatus().getValue()) {
				retriable = true;
				break;
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving isRetriable method");
		return retriable;
	}

	private boolean updateNewRetryCount(VendRetryDetails vendRetryDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateNewRetryCount method");
		try {
			return wsTransactionDAO.insertOrUpdate(vendRetryDetails);

		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"DBException in trying to insert/update vendRetryDetails");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateNewRetryCount method");
		return false;
	}

	private boolean rescheduleVendCode(VendTxnWSDetails vendTxnWSDetails, Long retryPeriod) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering rescheduleVendCode method");
		boolean reschedule = false;
		try {
			/*
			 * Most values fetched from vendTxnWSDetails. The values not present
			 * are retryCount - set to zero as this means the existing internal
			 * retry (for VMS-HES connectivity issues) retryPeriod - the period
			 * of wait time derived from config timestamp - set to actual
			 * timestamp deviceType - set to Phase 3 rescheduleFlag - Refers to
			 * internal retries and has to be set as false here.
			 */
			reschedule = vmsSchdulerService.scheduleJob(0, vendTxnWSDetails.getTransactionID(), retryPeriod, vendTxnWSDetails.getPan(),
					vendTxnWSDetails.getVendCode(), vendTxnWSDetails.getTransactionType(), vendTxnWSDetails.getCreditValue(),
					vendTxnWSDetails.getActualTimeStamp(), DeviceTypeEnum.PH3.getDeviceType(), false);
		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"DBException when trying to reschedule Job: " + e.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving rescheduleVendCode method");
		return reschedule;
	}

	private VendRetryConfig getConfig() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getConfig method");
		VendRetryConfig retryConfig = null;
		try {
			retryConfig = (VendRetryConfig) vmsUtils.getVendServiceDetails(DeviceTypeConstants.VEND_RETRY);
		} catch (NamingException e) {
			logger.error(Logger.EVENT_FAILURE,"Exception when retrieving config: " + e);
			retryConfig = defaultConfig();
			logger.error(Logger.EVENT_FAILURE,"Using default config values");
		}

		logger.error(Logger.EVENT_FAILURE,"Config values " + retryConfig.toString());

		logger.debug(Logger.EVENT_SUCCESS,"Leaving getConfig method");
		return retryConfig;
	}

	private VendRetryConfig defaultConfig() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering defaultConfig method");

		VendRetryConfig config = new VendRetryConfig();
		config.setRetries(DEFAULT_MAX_RETRIES);
		config.setRetryPeriod(DEFAULT_RETRY_PERIOD);
		config.setEligibleStatus(DEFAULT_ELIGIBLE_STATUS);

		logger.debug(Logger.EVENT_SUCCESS,"Leaving defaultConfig method");
		return config;
	}

	private VendRetryDetails getVendRetryDetails(VendTxnWSDetails vendTxnWSDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendRetryDetails method");
		VendRetryDetails vendRetryDetails = null;
		try {
			vendRetryDetails = wsTransactionDAO.getVendRetryCount(vendTxnWSDetails.getTransactionID());
			// If no entry is present null is returned in which case a new
			// object is created with retryCount 0.
			if (vendRetryDetails == null) {
				logger.debug(Logger.EVENT_FAILURE,"Empty Vend Retry Details from database. Using default vend retry details");
				vendRetryDetails = initVendRetryDetails(vendTxnWSDetails);
			}
			logger.error(Logger.EVENT_FAILURE,"VendRetryDetails from DB: " + vendRetryDetails.toString());
		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"Exception when retrieving retryCount " + e.getMessage());
		}

		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendRetryDetails method");
		return vendRetryDetails;
	}

	private VendRetryDetails initVendRetryDetails(VendTxnWSDetails vendTxnWSDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering initVendRetryDetails method");

		VendRetryDetails vendRetryDetails = new VendRetryDetails();
		vendRetryDetails.setRetryCount(INITIAL_RETRY_COUNT);
		vendRetryDetails.setTransactionId(vendTxnWSDetails.getTransactionID());

		logger.debug(Logger.EVENT_SUCCESS,"Leaving initVendRetryDetails method");
		return vendRetryDetails;
	}

}
