package com.centrica.unittest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.easymock.EasyMock;
import org.junit.Test;

import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;

import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.sap.service.VoidVend;
import com.centrica.vms.ws.sap.service.VoidVendResponse;
import com.centrica.vms.ws.service.VMSVoidService;

/**
 * Test class to test VMSVoidService
 * This unit test class mock all the dependent classes calls
 * and test the functionality of VMSVoidService class.
 * 
 * Methods Tested
 * 
 * handleVoidRequest(VoidVend voidRequest)
 * processVoidRequest(String transactionID,String originalTransactionID, 
 * VendTxnWSDetails vendTransactionDetails, Calendar timestamp,String pan)
 */
public class VMSVoidServiceSmallTest {

	private final WSTransactionDAO wsTransDao = EasyMock.createMock(WSTransactionDAO.class);
	private final VMSSchedulerServiceImpl vmsSchedulerService = EasyMock.createMock(VMSSchedulerServiceImpl.class);

	private final VMSVoidService vmsVoidService = new VMSVoidService(vmsSchedulerService, wsTransDao);

	/**
	 * Method to test Handle void request
	 * @throws Exception
	 */
	@Test
	public void handleVoidRequest() throws Exception {

		final VoidVend voidRequest = VMSTestDataProvider.constructVoidRequest();

		final VendTxnWSDetails vendTxDetails = VMSTestDataProvider.constructVendTxnWSDetails(Status.SC_200.getStatus());

		final MeterDetails meterDetails = VMSTestDataProvider.constructMeterDetails();

		EasyMock.expect(wsTransDao.getVendTxnWSDetails(VMSTestDataProvider.TRANSACTION_ID)).andReturn(vendTxDetails).times(2);

		EasyMock.expect(wsTransDao.getVendTxnWSDetails("BG_" + VMSTestDataProvider.TRANSACTION_ID)).andReturn(vendTxDetails).times(2);

		EasyMock.expect(wsTransDao.getMeterDetails(VMSTestDataProvider.MSN)).andReturn(meterDetails);

		EasyMock.expect(wsTransDao.update(vendTxDetails)).andReturn(true);

		EasyMock.expect(vmsSchedulerService.scheduleJob(0, VMSTestDataProvider.TRANSACTION_ID, 0l, VMSTestDataProvider.PAN, 
				vendTxDetails.getVendCode(), vendTxDetails.getTransactionType(), vendTxDetails.getCreditValue(), vendTxDetails.getVendcodeTimeStamp(), 
				meterDetails.getDeviceTypeID(), true)).andReturn(true);

		EasyMock.replay(wsTransDao);
		EasyMock.replay(vmsSchedulerService);

		final VoidVendResponse vendResponse = vmsVoidService.handleVoidRequest(voidRequest);
		EasyMock.verify(wsTransDao);
		EasyMock.verify(vmsSchedulerService);

		assertEquals(vendResponse.getVoidTxnResponse().getVendOutcome().getVendOutcomeCode(), VendOutcomeCode_type1.value1);

	}

	/**
	 * Method to test process void request
	 * @throws Exception
	 */
	@Test
	public void processVoidRequest() throws Exception {

		final VendTxnWSDetails vendTxDetails = VMSTestDataProvider.constructVendTxnWSDetails(Status.SC_200.getStatus());

		final MeterDetails meterDetails = VMSTestDataProvider.constructMeterDetails();

		EasyMock.expect(wsTransDao.getVendTxnWSDetails(VMSTestDataProvider.TRANSACTION_ID)).andReturn(vendTxDetails).times(3);

		EasyMock.expect(wsTransDao.getMeterDetails(VMSTestDataProvider.MSN)).andReturn(meterDetails);

		EasyMock.expect(wsTransDao.update(vendTxDetails)).andReturn(true);

		EasyMock.expect(vmsSchedulerService.scheduleJob(0, VMSTestDataProvider.TRANSACTION_ID, 0l, VMSTestDataProvider.PAN, 
				vendTxDetails.getVendCode(), vendTxDetails.getTransactionType(), vendTxDetails.getCreditValue(), vendTxDetails.getVendcodeTimeStamp(), 
				meterDetails.getDeviceTypeID(), true)).andReturn(true);

		EasyMock.replay(wsTransDao);
		EasyMock.replay(vmsSchedulerService);

		assertTrue(vmsVoidService.processVoidRequest(VMSTestDataProvider.TRANSACTION_ID, VMSTestDataProvider.TRANSACTION_ID, 
				vendTxDetails, Calendar.getInstance(), VMSTestDataProvider.PAN));

		EasyMock.verify(wsTransDao);
		EasyMock.verify(vmsSchedulerService);

	}

}
