package com.centrica.unittest;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.easymock.EasyMock;
import org.junit.Test;

import com.centrica.unittest.service.VMSTestDataProvider;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.model.PPKeyTransaction;
import com.centrica.vms.model.PPKeyTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.service.PPKeySchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.headend.ack.ppkey.model.DeliveryPPKeyReceipt;
import com.centrica.vms.ws.headend.ack.ppkey.model.PPKeyStatusCode;
import com.centrica.vms.ws.headend.ack.service.AcknowledgePPKeyDelivery;
import com.centrica.vms.ws.headend.ack.service.AcknowledgePPKeyDeliveryResponse;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.PremiseDetails;
import com.centrica.vms.ws.ppk.service.BusinessProcessingFaultCode;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyRequest;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyRequestMessage;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyResponse;
import com.centrica.vms.ws.service.PPKeyService;
import com.centrica.vms.ws.service.PPKeyServiceImpl;
import com.centrica.vms.ws.service.helper.PPKeyServiceHelper;
import com.centrica.vms.ws.service.mapper.PPKeyUpdateMapper;
import com.centrica.vms.ws.service.validator.PPKeyServiceValidator;

/**
 * Test class to test PPKeyServiceImpl
 * This unit test class tests the functionality of PPKeyServiceImpl class.
 * 
 * Methods Tested
 * 
 * UpdatePPKeyResponse updatePPKey(final UpdatePPKeyRequest ppkRequest)
 */
public class PPKeyServiceImplTest {

	private final PPKeySchedulerServiceImpl ppkSchedulerService = EasyMock.createMock(PPKeySchedulerServiceImpl.class);
	private final PPKeyUpdateMapper mapper = new PPKeyUpdateMapper();
	private final PPKeyServiceValidator validator = new PPKeyServiceValidator();
	private final WSTransactionDAO transDao = EasyMock.createMock(WSTransactionDAO.class);
	private final VMSUtils vmsUtils = new VMSUtils();
	private final PPKeyServiceHelper svchelper = new PPKeyServiceHelper(ppkSchedulerService, transDao, vmsUtils);

	private final PPKeyService ppKeyService = new PPKeyServiceImpl(ppkSchedulerService, svchelper, mapper, validator, transDao, vmsUtils);

	/**
	 * Method to test Update PP Key
	 * Input - Null Transaction Id
	 * @throws Exception
	 */
	@Test
	public void updatePPKeyEmptyTransactionId() throws Exception {

		final UpdatePPKeyRequest request = new UpdatePPKeyRequest();
		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();

		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(null);
		request.setPPKeyRequest(requestMsg);

		EasyMock.expect(transDao.insert((PPKeyTransaction)EasyMock.anyObject())).andReturn(true);

		EasyMock.replay(transDao);

		final UpdatePPKeyResponse respone = ppKeyService.updatePPKey(request);

		EasyMock.verify(transDao);

		assertEquals(BusinessProcessingFaultCode.value7.getValue(), respone.getPPKeyResponse().getStatusCode());

	}

	/**
	 * Method to test Update PP Key
	 * Input - Invalid Transaction Id
	 * @throws Exception
	 */
	@Test
	public void updatePPKeyInvalidTransactionId() throws Exception {

		final UpdatePPKeyRequest request = new UpdatePPKeyRequest();
		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();

		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier("1234567890123455676899605694685968");
		request.setPPKeyRequest(requestMsg);

		EasyMock.expect(transDao.insert((PPKeyTransaction)EasyMock.anyObject())).andReturn(true);

		EasyMock.replay(transDao);

		final UpdatePPKeyResponse respone = ppKeyService.updatePPKey(request);

		EasyMock.verify(transDao);

		assertEquals(BusinessProcessingFaultCode.value7.getValue(), respone.getPPKeyResponse().getStatusCode());

	}

	/**
	 * Method to test Update PP Key
	 * Input - Null MSN
	 * @throws Exception
	 */
	@Test
	public void updatePPKeyEmptyMSN() throws Exception {

		final UpdatePPKeyRequest request = new UpdatePPKeyRequest();
		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();

		requestMsg.setMsn(null);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		request.setPPKeyRequest(requestMsg);

		EasyMock.expect(transDao.insert((PPKeyTransaction)EasyMock.anyObject())).andReturn(true);

		EasyMock.replay(transDao);

		final UpdatePPKeyResponse respone = ppKeyService.updatePPKey(request);

		EasyMock.verify(transDao);

		assertEquals(BusinessProcessingFaultCode.value2.getValue(), respone.getPPKeyResponse().getStatusCode());

	}

	/**
	 * Method to test Update PP Key
	 * Input - Invalid MSN
	 * @throws Exception
	 */
	@Test
	public void updatePPKeyInvalidMSN() throws Exception {

		final UpdatePPKeyRequest request = new UpdatePPKeyRequest();
		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();

		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		request.setPPKeyRequest(requestMsg);

		EasyMock.expect(transDao.getMeterDetails(VMSTestDataProvider.MSN)).andReturn(null);

		EasyMock.expect(transDao.insert((PPKeyTransaction)EasyMock.anyObject())).andReturn(true);

		EasyMock.replay(transDao);

		final UpdatePPKeyResponse respone = ppKeyService.updatePPKey(request);

		EasyMock.verify(transDao);

		assertEquals(BusinessProcessingFaultCode.value2.getValue(), respone.getPPKeyResponse().getStatusCode());

	}

	/**
	 * Method to test Update PP Key
	 * Input - Invalid MPXN
	 * @throws Exception
	 */
	@Test
	public void updatePPKeyInvalidMPXN() throws Exception {

		final UpdatePPKeyRequest request = new UpdatePPKeyRequest();
		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();

		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		request.setPPKeyRequest(requestMsg);

		final MeterDetails meterDetails = VMSTestDataProvider.constructMeterDetails();

		EasyMock.expect(transDao.getMeterDetails(VMSTestDataProvider.MSN)).andReturn(meterDetails);

		EasyMock.expect(transDao.getPremiseDetailsByMSN(VMSTestDataProvider.MSN)).andReturn(null);

		EasyMock.expect(transDao.insert((PPKeyTransaction)EasyMock.anyObject())).andReturn(true);

		EasyMock.replay(transDao);

		final UpdatePPKeyResponse respone = ppKeyService.updatePPKey(request);

		EasyMock.verify(transDao);

		assertEquals(BusinessProcessingFaultCode.value3.getValue(), respone.getPPKeyResponse().getStatusCode());

	}

	/**
	 * Method to test Update PP Key
	 * Input - Null PP Key
	 * @throws Exception
	 */
	@Test
	public void updatePPKeyEmptyPPKey() throws Exception {

		final UpdatePPKeyRequest request = new UpdatePPKeyRequest();
		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();

		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(null);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		request.setPPKeyRequest(requestMsg);

		EasyMock.expect(transDao.insert((PPKeyTransaction)EasyMock.anyObject())).andReturn(true);

		EasyMock.replay(transDao);

		final UpdatePPKeyResponse respone = ppKeyService.updatePPKey(request);

		EasyMock.verify(transDao);

		assertEquals(BusinessProcessingFaultCode.value4.getValue(), respone.getPPKeyResponse().getStatusCode());

	}

	/**
	 * Method to test Update PP Key
	 * Input - Invalid PP Key
	 * @throws Exception
	 */
	@Test
	public void updatePPKeyInvalidPPKey() throws Exception {

		final UpdatePPKeyRequest request = new UpdatePPKeyRequest();
		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();

		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(null);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		request.setPPKeyRequest(requestMsg);

		EasyMock.expect(transDao.insert((PPKeyTransaction)EasyMock.anyObject())).andReturn(true);

		EasyMock.replay(transDao);

		final UpdatePPKeyResponse respone = ppKeyService.updatePPKey(request);

		EasyMock.verify(transDao);

		assertEquals(BusinessProcessingFaultCode.value4.getValue(), respone.getPPKeyResponse().getStatusCode());

	}

	/**
	 * Method to test Update PP Key
	 * Input - Null Request Date
	 * @throws Exception
	 */
	@Test
	public void updatePPKeyEmptyRequestDate() throws Exception {

		final UpdatePPKeyRequest request = new UpdatePPKeyRequest();
		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();

		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(null);
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		request.setPPKeyRequest(requestMsg);

		EasyMock.expect(transDao.insert((PPKeyTransaction)EasyMock.anyObject())).andReturn(true);

		EasyMock.replay(transDao);

		final UpdatePPKeyResponse respone = ppKeyService.updatePPKey(request);

		EasyMock.verify(transDao);

		assertEquals(BusinessProcessingFaultCode.value6.getValue(), respone.getPPKeyResponse().getStatusCode());

	}

	/**
	 * Method to test Update PP Key
	 * Input - Future Request Date
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void updatePPKeyInvalidRequestDate() throws Exception {

		final UpdatePPKeyRequest request = new UpdatePPKeyRequest();
		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();

		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		final Calendar cal = Calendar.getInstance();
		final Date date = cal.getTime();
		date.setDate(date.getDate() + 1);
		cal.setTime(date);
		requestMsg.setPPKeyRequestDateTime(cal);
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		request.setPPKeyRequest(requestMsg);

		EasyMock.expect(transDao.insert((PPKeyTransaction)EasyMock.anyObject())).andReturn(true);

		EasyMock.replay(transDao);

		final UpdatePPKeyResponse respone = ppKeyService.updatePPKey(request);

		EasyMock.verify(transDao);

		assertEquals(BusinessProcessingFaultCode.value6.getValue(), respone.getPPKeyResponse().getStatusCode());

	}

	/**
	 * Method to test Update PP Key - Positive Scenario
	 * @throws Exception
	 */
	@Test
	public void updatePPKey() throws Exception {

		final UpdatePPKeyRequest request = new UpdatePPKeyRequest();
		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();

		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		request.setPPKeyRequest(requestMsg);

		final MeterDetails meterDetails = VMSTestDataProvider.constructMeterDetails();

		EasyMock.expect(transDao.getMeterDetails(VMSTestDataProvider.MSN)).andReturn(meterDetails);

		final PremiseDetails premiseDetails = VMSTestDataProvider.constructPremiseDetails();
		EasyMock.expect(transDao.getPremiseDetailsByMSN(VMSTestDataProvider.MSN)).andReturn(premiseDetails);

		EasyMock.expect(transDao.insert((PPKeyTransaction)EasyMock.anyObject())).andReturn(true);

		EasyMock.expect(ppkSchedulerService.schedulePPKeyUpdatetoHeadEnd(EasyMock.anyInt(), EasyMock.anyString(), EasyMock.anyLong(), 
				EasyMock.anyString(), EasyMock.anyString(), (Date)EasyMock.anyObject(), EasyMock.anyBoolean())).andReturn(true);

		EasyMock.expect(transDao.update(meterDetails)).andReturn(true);

		EasyMock.expect(ppkSchedulerService.schedulePPKeyWatchJob(EasyMock.anyString(), EasyMock.anyLong(), 
				EasyMock.anyString(), (Date)EasyMock.anyObject(), EasyMock.anyBoolean())).andReturn(true);

		EasyMock.replay(transDao);
		EasyMock.replay(ppkSchedulerService);

		final UpdatePPKeyResponse respone = ppKeyService.updatePPKey(request);
		EasyMock.verify(transDao);
		EasyMock.verify(ppkSchedulerService);

		assertEquals(Status.SC_200.getStatus(), Integer.parseInt(respone.getPPKeyResponse().getStatusCode()));

	}

	/**
	 * Method to test Acknowledge PP Key - Positive Scenario
	 * @throws Exception
	 */
	@Test
	public void acknowledgePPKeyDelivery() throws Exception {

		final AcknowledgePPKeyDelivery acknowledgePPKeyDelivery = new AcknowledgePPKeyDelivery();
		final DeliveryPPKeyReceipt receipt = new DeliveryPPKeyReceipt();
		receipt.setDeliveryStatus(PPKeyStatusCode.value1);
		receipt.setTransactionID(VMSTestDataProvider.TRANSACTION_ID);
		acknowledgePPKeyDelivery.setDeliveryPPKeyReceipt(receipt);

		final MeterDetails meterDetails = VMSTestDataProvider.constructMeterDetails();
		final PPKeyTransaction transaction = new PPKeyTransaction();
		final Set<PPKeyTxnStatus> statusSet = new HashSet<PPKeyTxnStatus>();
		final PPKeyTxnStatus ppKeyTxnStatus = new PPKeyTxnStatus();
		ppKeyTxnStatus.setStatus(Status.SC_200.getStatus());
		statusSet.add(ppKeyTxnStatus);
		transaction.setStatusDetails(statusSet);
		transaction.setMsn(VMSTestDataProvider.MSN);

		EasyMock.expect(transDao.getPPKeyTxnDetails(VMSTestDataProvider.TRANSACTION_ID)).andReturn(transaction);

		//EasyMock.expect(ppkSchedulerService.unschedulePPKeyUpdatetoHeadEnd(VMSTestDataProvider.TRANSACTION_ID)).andReturn(true);

		EasyMock.expect(transDao.update(transaction)).andReturn(true);

		EasyMock.expect(transDao.getMeterDetails(VMSTestDataProvider.MSN)).andReturn(meterDetails);

		EasyMock.expect(transDao.update(meterDetails)).andReturn(true);

		EasyMock.expect(ppkSchedulerService.unschedulePPKeyWatchJob(VMSTestDataProvider.TRANSACTION_ID)).andReturn(true);

		EasyMock.expect(ppkSchedulerService.scheduleACKJobtoSAP(EasyMock.anyInt(), EasyMock.anyString(), EasyMock.anyLong(), 
				EasyMock.anyString(), EasyMock.anyString(), EasyMock.anyInt(), (Date)EasyMock.anyObject(), EasyMock.anyBoolean())).andReturn(true);

		EasyMock.replay(transDao);
		EasyMock.replay(ppkSchedulerService);

		final AcknowledgePPKeyDeliveryResponse respone = ppKeyService.acknowledgePPKeyDelivery(acknowledgePPKeyDelivery);

		EasyMock.verify(transDao);
		EasyMock.verify(ppkSchedulerService);

		assertEquals(Status.SC_200.getStatus(), respone.getDeliveryPPKeyReceiptResponse().getStatus().getValue());

	}

	/**
	 * Method to test Acknowledge PP Key - Negative Scenario
	 * @throws Exception
	 */
	@Test
	public void acknowledgePPKeyDeliveryNegativeStatus() throws Exception {

		final AcknowledgePPKeyDelivery acknowledgePPKeyDelivery = new AcknowledgePPKeyDelivery();
		final DeliveryPPKeyReceipt receipt = new DeliveryPPKeyReceipt();
		receipt.setDeliveryStatus(PPKeyStatusCode.value2);
		receipt.setTransactionID(VMSTestDataProvider.TRANSACTION_ID);
		acknowledgePPKeyDelivery.setDeliveryPPKeyReceipt(receipt);

		final MeterDetails meterDetails = VMSTestDataProvider.constructMeterDetails();
		final PPKeyTransaction transaction = new PPKeyTransaction();
		final Set<PPKeyTxnStatus> statusSet = new HashSet<PPKeyTxnStatus>();
		final PPKeyTxnStatus ppKeyTxnStatus = new PPKeyTxnStatus();
		ppKeyTxnStatus.setStatus(Status.SC_570.getStatus());
		statusSet.add(ppKeyTxnStatus);
		transaction.setStatusDetails(statusSet);
		transaction.setMsn(VMSTestDataProvider.MSN);
		transaction.setTransactionId(VMSTestDataProvider.TRANSACTION_ID);

		EasyMock.expect(transDao.getPPKeyTxnDetails(VMSTestDataProvider.TRANSACTION_ID)).andReturn(transaction);

		EasyMock.expect(ppkSchedulerService.unschedulePPKeyUpdatetoHeadEnd(VMSTestDataProvider.TRANSACTION_ID)).andReturn(true);

		EasyMock.expect(transDao.update(transaction)).andReturn(true);

		EasyMock.expect(transDao.getMeterDetails(VMSTestDataProvider.MSN)).andReturn(meterDetails);

		EasyMock.expect(transDao.update(meterDetails)).andReturn(true);

		EasyMock.expect(ppkSchedulerService.unschedulePPKeyWatchJob(VMSTestDataProvider.TRANSACTION_ID)).andReturn(true);

		EasyMock.expect(ppkSchedulerService.scheduleACKJobtoSAP(EasyMock.anyInt(), EasyMock.anyString(), EasyMock.anyLong(), 
				EasyMock.anyString(), EasyMock.anyString(), EasyMock.anyInt(), (Date)EasyMock.anyObject(), EasyMock.anyBoolean())).andReturn(true);

		EasyMock.replay(transDao);
		EasyMock.replay(ppkSchedulerService);

		final AcknowledgePPKeyDeliveryResponse respone = ppKeyService.acknowledgePPKeyDelivery(acknowledgePPKeyDelivery);

		EasyMock.verify(transDao);
		EasyMock.verify(ppkSchedulerService);

		assertEquals(Status.SC_200.getStatus(), respone.getDeliveryPPKeyReceiptResponse().getStatus().getValue());

	}

}
