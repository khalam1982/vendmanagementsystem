package com.centrica.vms.ws.service;

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.Constants;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.MPxNNotFoundException;
import com.centrica.vms.exception.MSNNotFoundException;
import com.centrica.vms.exception.VMSInvalidDateException;
import com.centrica.vms.exception.VMSInvalidPPKeyException;
import com.centrica.vms.exception.VMSInvalidTransactionIdException;
import com.centrica.vms.model.PPKeyTransaction;
import com.centrica.vms.model.PPKeyTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.service.PPKeySchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.headend.ack.ppkey.model.DeliveryPPKeyReceipt;
import com.centrica.vms.ws.headend.ack.ppkey.model.DeliveryPPKeyReceiptResponse;
import com.centrica.vms.ws.headend.ack.ppkey.model.PPKeyStatusCode;
import com.centrica.vms.ws.headend.ack.model.DeliveryStatusCode;
import com.centrica.vms.ws.headend.ack.service.AcknowledgePPKeyDelivery;
import com.centrica.vms.ws.headend.ack.service.AcknowledgePPKeyDeliveryResponse;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.PremiseDetails;
import com.centrica.vms.ws.ppk.service.BusinessProcessingFaultCode;
import com.centrica.vms.ws.ppk.service.BusinessProcessingFaultException0;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyRequest;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyRequestMessage;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyResponse;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyResponseMessage;
import com.centrica.vms.ws.service.helper.PPKeyServiceHelper;
import com.centrica.vms.ws.service.mapper.PPKeyUpdateMapper;
import com.centrica.vms.ws.service.validator.PPKeyServiceValidator;

/**
 * PPKeyServiceImpl
 * 
 * Class to handle PP Key Update
 * 
 * @author chackram
 */
public class PPKeyServiceImpl implements PPKeyService {

	private final Logger logger = ESAPI.getLogger(PPKeyServiceImpl.class);

	private final PPKeySchedulerServiceImpl ppkSchedulerService;
	private final PPKeyServiceHelper svchelper;
	private final PPKeyUpdateMapper mapper;
	private final PPKeyServiceValidator validator;
	private final WSTransactionDAO transDao;
	private final VMSUtils vmsUtils;

	/**
	 * Constructor
	 */
	public PPKeyServiceImpl() {
		ppkSchedulerService = new PPKeySchedulerServiceImpl();
		svchelper = new PPKeyServiceHelper();
		mapper = new PPKeyUpdateMapper();
		validator = new PPKeyServiceValidator();
		transDao = new WSTransactionDAO();
		vmsUtils = new VMSUtils();
	}

	/**
	 * Constructor
	 */
	public PPKeyServiceImpl(final PPKeySchedulerServiceImpl ppkSchedulerService, final PPKeyServiceHelper svchelper, final PPKeyUpdateMapper mapper,
			final PPKeyServiceValidator validator, final WSTransactionDAO transDao, 
			final VMSUtils vmsUtils) {
		this.ppkSchedulerService = ppkSchedulerService;
		this.svchelper = svchelper;
		this.mapper = mapper;
		this.validator = validator;
		this.transDao = transDao;
		this.vmsUtils = vmsUtils;
	}

	/**
	 * Updates PP Key
	 * Schedules Trigger to send PP Key to HeadEnd
	 * 
	 * @param ppkRequest - UpdatePPKeyRequest
	 * @return UpdatePPKeyResponse
	 * @throws BusinessProcessingFaultException0
	 */
	@Override
	public UpdatePPKeyResponse updatePPKey(final UpdatePPKeyRequest ppkRequest) throws BusinessProcessingFaultException0 {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyServiceImpl:updatePPKey method");
		final UpdatePPKeyResponse response = new UpdatePPKeyResponse();
		final UpdatePPKeyRequestMessage request = ppkRequest.getPPKeyRequest();

		try {
			/** Validate Request **/
			validator.validateUpdatePPKeyRequest(request);

			final MeterDetails meterDetails = transDao.getMeterDetails(request.getMsn());
			if( meterDetails == null || meterDetails.getMsn() == null ) {
				throw new MSNNotFoundException();
			}

			final PremiseDetails premiseDetails = fetchPremiseDetails(request);
			if( premiseDetails == null || premiseDetails.getMpxn() == null ) {
				throw new MPxNNotFoundException();
			}

			final PPKeyTransaction ppkTrans = mapper.mapPPKeyTransactionRequest(request, Status.SC_515);
			/** Insert PP key Update transaction to DB **/
			transDao.insert(ppkTrans);

			final Properties properties = vmsUtils.loadProperties();
			final long scheduleTime = Long.parseLong(properties.getProperty(Constants.PPK_HE_SCHEDULE_TIME));

			/** Schedule job to send it to HES **/
			ppkSchedulerService.schedulePPKeyUpdatetoHeadEnd(0, request.getPPKeyRequestIdentifier(), scheduleTime, request.getMsn(),
					request.getPPKey(), new Date(), false);

			/** Suspend Vend transaction **/
			meterDetails.setVendTxnStatus(1);
			transDao.update(meterDetails);

			final long watchTime = Long.parseLong(properties.getProperty(Constants.PPK_WATCH_SCHEDULE_TIME));
			/** Add a trigger to resume after 30 minutes, in case acknowledgement was not received **/
			ppkSchedulerService.schedulePPKeyWatchJob(request.getPPKeyRequestIdentifier(), watchTime, request.getMsn(), new Date(), false);

			/** Prepare SOAP Response with Success Message**/
			setResponse(response, String.valueOf(Status.SC_200.getStatus()), Constants.SUCCESS);
		} catch (VMSInvalidTransactionIdException e) {
			logger.error(Logger.EVENT_FAILURE,"Invalid Transaction Id" + e.getMessage());
			updateFailedTrans(request, Status.SC_80);
			setResponse(response, BusinessProcessingFaultCode.value7.getValue(), "Invalid Transaction Id");
		} catch (MSNNotFoundException e) {
			logger.error(Logger.EVENT_FAILURE,"MSN Not Found" + e.getMessage());
			updateFailedTrans(request, Status.SC_60);
			setResponse(response, BusinessProcessingFaultCode.value2.getValue(), "MSN Not Found");
		} catch (MPxNNotFoundException e) {
			logger.error(Logger.EVENT_FAILURE,"MPXN Not Found" + e.getMessage());
			updateFailedTrans(request, Status.SC_65);
			setResponse(response, BusinessProcessingFaultCode.value3.getValue(), "MPXN Not Found");
		} catch (VMSInvalidPPKeyException e) {
			logger.error(Logger.EVENT_FAILURE,"Invalid PPKey" + e.getMessage());
			updateFailedTrans(request, Status.SC_10);
			setResponse(response, BusinessProcessingFaultCode.value4.getValue(), "Invalid PPKey");
		} catch (VMSInvalidDateException e) {
			logger.error(Logger.EVENT_FAILURE,"Invalid Request Date" + e.getMessage());
			updateFailedTrans(request, Status.SC_95);
			setResponse(response, BusinessProcessingFaultCode.value6.getValue(), "Invalid Request Date");
		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception" + e.getMessage());
			updateFailedTrans(request, Status.SC_20);
			setResponse(response, BusinessProcessingFaultCode.value5.getValue(), "DB Exception");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyServiceImpl:updatePPKey method");
		return response;

	}

	/**
	 * Acknowledges PP Key Delivery from HE
	 * 
	 * @param acknowledgePPKeyDelivery
	 * @return AcknowledgePPKeyDeliveryResponse
	 */
	@Override
	public AcknowledgePPKeyDeliveryResponse acknowledgePPKeyDelivery(final AcknowledgePPKeyDelivery acknowledgePPKeyDelivery) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyServiceImpl:acknowledgePPKeyDelivery method");
		final AcknowledgePPKeyDeliveryResponse response = new AcknowledgePPKeyDeliveryResponse();

		final DeliveryPPKeyReceipt receipt = acknowledgePPKeyDelivery.getDeliveryPPKeyReceipt();
		final String transactionId = receipt.getTransactionID();
		final PPKeyStatusCode deliveryStatus = receipt.getDeliveryStatus();
		PPKeyStatusCode returnStatus = PPKeyStatusCode.value1;
		logger.info(Logger.EVENT_UNSPECIFIED,"Acknowledge PP Key Delivery Status " +  transactionId + deliveryStatus.getValue() );

		try {
			final PPKeyTransaction transaction = transDao.getPPKeyTxnDetails(transactionId);

			/** Check PP Key Transaction Status in VMS and update HE Status to DB **/
			final Status headEndstatus = checkAndUpdateStatus(deliveryStatus, transaction);

			/** Update Meter Details - PP Key, Vend Suspend only if it is successful in HeadEnd. 
			 *  Update PP Key in case of Cancelled Status as well  **/
			svchelper.updateMeterDetails(headEndstatus, transaction);

			/** Un schedule Watch Job **/
			ppkSchedulerService.unschedulePPKeyWatchJob(transactionId);

			/** Schedule SAP Acknowledgement Job **/
			svchelper.sendAcknowledgementToSAP(transaction, headEndstatus);
		} catch (DBException e) {
			returnStatus = PPKeyStatusCode.value2;
			logger.error(Logger.EVENT_FAILURE,"DB Exception" + e.getMessage());
		} catch (Exception e) {
			returnStatus = PPKeyStatusCode.value2;
			logger.error(Logger.EVENT_FAILURE,"Exception" + e.getMessage());
		}
		final DeliveryPPKeyReceiptResponse receiptRsp = new DeliveryPPKeyReceiptResponse();
		receiptRsp.setStatus(returnStatus);
		receiptRsp.setTransactionID(transactionId);
		response.setDeliveryPPKeyReceiptResponse(receiptRsp);

		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyServiceImpl:acknowledgePPKeyDelivery method");
		return response;

	}

	/**
	 * Checks and Updates Status to Database
	 * 
	 * @param status - PPKeyStatusCode
	 * @param transaction - PPKeyTransaction
	 * @return Status
	 * @throws DBException
	 */
	private Status checkAndUpdateStatus(final PPKeyStatusCode status, final PPKeyTransaction transaction) throws DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyServiceImpl:checkAndUpdateStatus method");
		Status headEndstatus = null;
		final Iterator<PPKeyTxnStatus> statusItr = transaction.getStatusDetails().iterator();
		final int lastStatus = statusItr != null && statusItr.hasNext() ? statusItr.next().getStatus() : 0;

		logger.info(Logger.EVENT_UNSPECIFIED,"VMS Last Status : " + lastStatus);
		/** Acknowledgement received after sent to Head End or in retry or the transaction is cancelled **/
		if( Status.SC_200.getStatus() == lastStatus || Status.SC_570.getStatus() == lastStatus || Status.SC_125.getStatus() == lastStatus 
				||  Status.SC_600.getStatus() == lastStatus  ) {
			if( Status.SC_570.getStatus() == lastStatus ) {
				/** Acknowledgement received when request is in retry, Remove job from the scheduler **/
				logger.info(Logger.EVENT_UNSPECIFIED,"Unscheduling PP Key Update Job as acknowledgement received from Head End");
				final boolean unSchedulestatus = ppkSchedulerService.unschedulePPKeyUpdatetoHeadEnd(transaction.getTransactionId());	
				logger.info(Logger.EVENT_UNSPECIFIED,"UnSchedule status is - " + unSchedulestatus);
			}
			headEndstatus = DeliveryStatusCode.value1.getValue() == status.getValue() ? Status.SC_100 : Status.SC_110;
			mapper.setPPKeyTransactionStatus(headEndstatus, transaction.getStatusDetails());
			transDao.update(transaction);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyServiceImpl:checkAndUpdateStatus method");
		return headEndstatus;

	}

	/**
	 * Fetches Premise Details from Database
	 * 
	 * @param request - UpdatePPKeyRequestMessage
	 * @return PremiseDetails
	 * @throws DBException
	 * @throws MPxNNotFoundException
	 */
	private PremiseDetails fetchPremiseDetails(final UpdatePPKeyRequestMessage request) throws DBException,
	MPxNNotFoundException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyServiceImpl:fetchPremiseDetails method");
		PremiseDetails premiseDetails = null;
		if( request.getMpxn() != null && !request.getMpxn().isEmpty() ) {
			premiseDetails = transDao.getPremiseDetails(request.getMpxn());
		} else {
			premiseDetails = transDao.getPremiseDetailsByMSN(request.getMsn());
			if( premiseDetails != null ) {
				request.setMpxn(premiseDetails.getMpxn());
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyServiceImpl:fetchPremiseDetails method");
		return premiseDetails;

	}

	/**
	 * Updates Failed Transaction Status to Database
	 * 
	 * @param request - UpdatePPKeyRequestMessage
	 * @param statusCode - Status
	 */
	private void updateFailedTrans(final UpdatePPKeyRequestMessage request, final Status statusCode) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyServiceImpl:updateFailedTrans method");
		/** Update Transaction Status before throwing error. **/
		final PPKeyTransaction ppkTrans = mapper.mapPPKeyTransactionRequest(request, statusCode);
		try {
			transDao.insert(ppkTrans);
		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception when inserting Failed Transaction Details " + e.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyServiceImpl:updateFailedTrans method");

	}

	/**
	 * Forms Response
	 * 
	 * @param response - UpdatePPKeyResponse
	 * @param status of type String
	 * @param msg of type String
	 */
	private void setResponse(final UpdatePPKeyResponse response, final String status, final String msg) {

		final UpdatePPKeyResponseMessage responseMsg = new UpdatePPKeyResponseMessage();
		responseMsg.setErrorDescription(msg);
		responseMsg.setStatusCode(status);
		response.setPPKeyResponse(responseMsg);

	}

}
