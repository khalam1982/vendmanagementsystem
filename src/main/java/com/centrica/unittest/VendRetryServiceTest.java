package com.centrica.unittest;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.Date;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.centrica.vms.common.DeviceTypeConstants;
import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.external.model.VendRetryConfig;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceipt;
import com.centrica.vms.ws.headend.ack.model.ResponseCode;
import com.centrica.vms.ws.model.VendRetryDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.service.VendRetryService;

public class VendRetryServiceTest {

	private static final String TRANSACTION_ID = "TST000000000001";
	private VendRetryService vendRetryService;
	private VendTxnWSDetails vendTxnWSDetails;
	private DeliveryReceipt deliveryReceipt;
	private WSTransactionDAO wsDAO = createMock(WSTransactionDAO.class);
	private VMSSchedulerServiceImpl schedulerService = createMock(VMSSchedulerServiceImpl.class);
	private VMSUtils utils = createMock(VMSUtils.class);
	private VendRetryDetails vendRetryDetails;
	private Integer existingStatus = Status.SC_200.getStatus();

	@Before
	public void init() throws Exception {
		vendRetryService = new VendRetryService(wsDAO, schedulerService, utils);
		vendTxnWSDetails = mockVendTxnWSDetails();
		deliveryReceipt = mockDeliveryReceipt();
		mockVendRetryDetails();
	}

	private void mockConfig() throws NamingException {
		expect(utils.getVendServiceDetails(DeviceTypeConstants.VEND_RETRY)).andReturn(mockVendRetryConfig());
		replay(utils);
	}

	private VendRetryDetails mockVendRetryDetails() {
		vendRetryDetails = new VendRetryDetails();
		vendRetryDetails.setRetryCount(2);
		vendRetryDetails.setTransactionId(TRANSACTION_ID);
		return vendRetryDetails;
	}

	private VendRetryConfig mockVendRetryConfig() {
		VendRetryConfig config = new VendRetryConfig();
		config.setRetries("3");
		config.setEligibleStatus("350");
		config.setRetryPeriod("60,60,60");
		return config;
	}

	private DeliveryReceipt mockDeliveryReceipt() {
		DeliveryReceipt receipt = new DeliveryReceipt();
		receipt.setDeliveryStatus(ResponseCode.value7);
		return receipt;
	}

	private VendTxnWSDetails mockVendTxnWSDetails() {
		VendTxnWSDetails vendTxnWSDetails = new VendTxnWSDetails();
		vendTxnWSDetails.setTransactionID(TRANSACTION_ID);
		vendTxnWSDetails.setActualTimeStamp(new Date());
		vendTxnWSDetails.setPan("12345");
		vendTxnWSDetails.setCreditValue("1000");
		vendTxnWSDetails.setVendCode("12345");
		vendTxnWSDetails.setTransactionType("ELECTRIC_PURCHASE");
		return vendTxnWSDetails;
	}

	@Test
	public void checkIfRetryIsNotExecutedForIneligibleStatus() throws Exception {
		mockConfig();
		deliveryReceipt.setDeliveryStatus(ResponseCode.value1);
		assertFalse(vendRetryService.retryVend(vendTxnWSDetails, deliveryReceipt, existingStatus));
	}
	
	@Test
	public void checkIfNoRetryForExistingStatusOf180() throws Exception {
		mockConfig();
		deliveryReceipt.setDeliveryStatus(ResponseCode.value1);
		existingStatus = Status.SC_180.getStatus();
		assertFalse(vendRetryService.retryVend(vendTxnWSDetails, deliveryReceipt, existingStatus));
	}

	@Test
	public void checkIfRetryIsNotExecutedForDBErrorInFetchingVendRetry() throws Exception {
		mockConfig();
		expect(wsDAO.getVendRetryCount(vendTxnWSDetails.getTransactionID())).andThrow(new DBException());
		replay(wsDAO);
		assertFalse(vendRetryService.retryVend(vendTxnWSDetails, deliveryReceipt, existingStatus));
	}

	@Test
	public void checkIfRetryIsNotExecutedIfMaxRetriesExceeded() throws Exception {
		mockConfig();
		vendRetryDetails.setRetryCount(3);
		expect(wsDAO.getVendRetryCount(vendTxnWSDetails.getTransactionID())).andReturn(vendRetryDetails);
		replay(wsDAO);
		assertFalse(vendRetryService.retryVend(vendTxnWSDetails, deliveryReceipt, existingStatus));
	}

	@Test
	public void checkIfRetryIsNotExecutedForDBErrorInStoringVendRetry() throws Exception {
		mockConfig();
		expect(wsDAO.getVendRetryCount(vendTxnWSDetails.getTransactionID())).andReturn(vendRetryDetails);
		expect(wsDAO.insertOrUpdate(vendRetryDetails)).andThrow(new DBException());
		replay(wsDAO);
		assertFalse(vendRetryService.retryVend(vendTxnWSDetails, deliveryReceipt, existingStatus));
	}

	@Test
	public void checkIfRetryIsNotExecutedForErrorInStoringVendRetry() throws Exception {
		mockConfig();
		expect(wsDAO.getVendRetryCount(vendTxnWSDetails.getTransactionID())).andReturn(vendRetryDetails);
		expect(wsDAO.insertOrUpdate(vendRetryDetails)).andReturn(false);
		replay(wsDAO);
		assertFalse(vendRetryService.retryVend(vendTxnWSDetails, deliveryReceipt, existingStatus));
	}

	@Test
	public void checkIfFalseIsReturnedIfRescheduleFails() throws Exception {
		mockConfig();
		expect(wsDAO.getVendRetryCount(vendTxnWSDetails.getTransactionID())).andReturn(vendRetryDetails);
		expect(wsDAO.insertOrUpdate(vendRetryDetails)).andReturn(true);
		replay(wsDAO);
		expect(
				schedulerService.scheduleJob(0, vendTxnWSDetails.getTransactionID(), 60l, vendTxnWSDetails.getPan(), vendTxnWSDetails
						.getVendCode(), vendTxnWSDetails.getTransactionType(), vendTxnWSDetails.getCreditValue(), vendTxnWSDetails
						.getActualTimeStamp(), DeviceTypeEnum.PH3.getDeviceType(), false)).andReturn(false);
		replay(schedulerService);
		assertFalse(vendRetryService.retryVend(vendTxnWSDetails, deliveryReceipt, existingStatus));
	}

	@Test
	public void checkIfFalseIsReturnedIfRescheduleThrowsException() throws Exception {
		mockConfig();
		expect(wsDAO.getVendRetryCount(vendTxnWSDetails.getTransactionID())).andReturn(vendRetryDetails);
		expect(wsDAO.insertOrUpdate(vendRetryDetails)).andReturn(true);
		replay(wsDAO);
		expect(
				schedulerService.scheduleJob(0, vendTxnWSDetails.getTransactionID(), 60l, vendTxnWSDetails.getPan(), vendTxnWSDetails
						.getVendCode(), vendTxnWSDetails.getTransactionType(), vendTxnWSDetails.getCreditValue(), vendTxnWSDetails
						.getActualTimeStamp(), DeviceTypeEnum.PH3.getDeviceType(), false)).andThrow(new DBException());
		replay(schedulerService);
		assertFalse(vendRetryService.retryVend(vendTxnWSDetails, deliveryReceipt, existingStatus));
	}

	@Test
	public void checkIfTrueIsReturnedIfRescheduleIsSuccessful() throws Exception {
		mockConfig();
		expect(wsDAO.getVendRetryCount(vendTxnWSDetails.getTransactionID())).andReturn(vendRetryDetails);
		expect(wsDAO.insertOrUpdate(vendRetryDetails)).andReturn(true);
		replay(wsDAO);
		expect(
				schedulerService.scheduleJob(0, vendTxnWSDetails.getTransactionID(), 60l, vendTxnWSDetails.getPan(), vendTxnWSDetails
						.getVendCode(), vendTxnWSDetails.getTransactionType(), vendTxnWSDetails.getCreditValue(), vendTxnWSDetails
						.getActualTimeStamp(), DeviceTypeEnum.PH3.getDeviceType(), false)).andReturn(true);
		replay(schedulerService);
		assertTrue(vendRetryService.retryVend(vendTxnWSDetails, deliveryReceipt, existingStatus));
	}

	@Test
	public void checkIfDefaultConfigForErrorInGettingFromContext() throws Exception {
		expect(utils.getVendServiceDetails(DeviceTypeConstants.VEND_RETRY)).andThrow(new NamingException());
		replay(utils);
		expect(wsDAO.getVendRetryCount(vendTxnWSDetails.getTransactionID())).andReturn(vendRetryDetails);
		expect(wsDAO.insertOrUpdate(vendRetryDetails)).andReturn(true);
		replay(wsDAO);
		expect(
				schedulerService.scheduleJob(0, vendTxnWSDetails.getTransactionID(), 1800l, vendTxnWSDetails.getPan(), vendTxnWSDetails
						.getVendCode(), vendTxnWSDetails.getTransactionType(), vendTxnWSDetails.getCreditValue(), vendTxnWSDetails
						.getActualTimeStamp(), DeviceTypeEnum.PH3.getDeviceType(), false)).andReturn(true);
		replay(schedulerService);
		assertTrue(vendRetryService.retryVend(vendTxnWSDetails, deliveryReceipt, existingStatus));
	}

	@Test
	public void checkIfDefaultRetryDetailsIsUsedIfNotPresent() throws Exception {
		expect(utils.getVendServiceDetails(DeviceTypeConstants.VEND_RETRY)).andThrow(new NamingException());
		replay(utils);
		vendRetryDetails.setRetryCount(1);
		expect(wsDAO.getVendRetryCount(vendTxnWSDetails.getTransactionID())).andReturn(null);
		expect(wsDAO.insertOrUpdate(vendRetryDetails)).andReturn(true);
		replay(wsDAO);
		expect(
				schedulerService.scheduleJob(0, vendTxnWSDetails.getTransactionID(), 900l, vendTxnWSDetails.getPan(), vendTxnWSDetails
						.getVendCode(), vendTxnWSDetails.getTransactionType(), vendTxnWSDetails.getCreditValue(), vendTxnWSDetails
						.getActualTimeStamp(), DeviceTypeEnum.PH3.getDeviceType(), false)).andReturn(true);
		replay(schedulerService);
		assertTrue(vendRetryService.retryVend(vendTxnWSDetails, deliveryReceipt, existingStatus));
	}
	
	
}
