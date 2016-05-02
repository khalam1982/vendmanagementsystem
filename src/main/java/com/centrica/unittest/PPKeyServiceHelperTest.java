package com.centrica.unittest;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Test;

import com.centrica.unittest.service.VMSTestDataProvider;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.MSNNotFoundException;
import com.centrica.vms.model.PPKeyTransaction;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.service.PPKeySchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.service.helper.PPKeyServiceHelper;


/**
 * Test class to test PPKeyServiceHelper
 * This unit test class tests the functionality of PPKeyServiceHelperTest class.
 * 
 * Methods Tested
 * 
 * updateMeterDetails(final Status transStatus, final PPKeyTransaction transaction)
 * sendAcknowledgementToSAP(final PPKeyTransaction transaction, final Status headEndstatus)
 */
public class PPKeyServiceHelperTest {

	private final PPKeySchedulerServiceImpl ppkSchedulerService = EasyMock.createMock(PPKeySchedulerServiceImpl.class);
	private final WSTransactionDAO transDao = EasyMock.createMock(WSTransactionDAO.class);
	private final VMSUtils vmsUtils = new VMSUtils();

	private final PPKeyServiceHelper ppKeySvcHlpr = new PPKeyServiceHelper(ppkSchedulerService, transDao, vmsUtils);

	/**
	 * Method to test Update Meter Key
	 * Input - Invalid MSN
	 * @throws Exception
	 */
	@Test(expected = MSNNotFoundException.class)
	public void updateMeterDetailsInvalidMSN() throws Exception {

		final PPKeyTransaction transaction = new PPKeyTransaction();
		transaction.setMsn(VMSTestDataProvider.MSN);

		EasyMock.expect(transDao.getMeterDetails(VMSTestDataProvider.MSN)).andThrow(new MSNNotFoundException());

		EasyMock.replay(transDao);

		ppKeySvcHlpr.updateMeterDetails(Status.SC_100, transaction);
		EasyMock.verify(transDao);

	}

	/**
	 * Method to test Meter PP Key
	 * Input - Null Status
	 * @throws Exception
	 */
	@Test
	public void updateMeterDetailsNullStatus() throws Exception {

		final PPKeyTransaction transaction = new PPKeyTransaction();
		transaction.setMsn(VMSTestDataProvider.MSN);

		final MeterDetails meterDetails = VMSTestDataProvider.constructMeterDetails();
		meterDetails.setVendTxnStatus(1);

		ppKeySvcHlpr.updateMeterDetails(null, transaction);

		assertEquals(1, meterDetails.getVendTxnStatus());

	}

	/**
	 * Method to test Update Meter Key
	 * Input - Negative Status
	 * @throws Exception
	 */
	@Test
	public void updateMeterDetailsNegativeStatus() throws Exception {

		final PPKeyTransaction transaction = new PPKeyTransaction();
		transaction.setMsn(VMSTestDataProvider.MSN);

		final MeterDetails meterDetails = VMSTestDataProvider.constructMeterDetails();
		EasyMock.expect(transDao.getMeterDetails(VMSTestDataProvider.MSN)).andReturn(meterDetails);
		EasyMock.expect(transDao.update(meterDetails)).andReturn(true);

		EasyMock.replay(transDao);

		ppKeySvcHlpr.updateMeterDetails(Status.SC_600, transaction);
		EasyMock.verify(transDao);

		assertEquals(0, meterDetails.getVendTxnStatus());
		assertEquals(VMSTestDataProvider.PP_KEY, meterDetails.getPrepayKey());

	}

	/**
	 * Method to test Update Meter Key
	 * Positive Scenario
	 * @throws Exception
	 */
	@Test
	public void updateMeterDetails() throws Exception {

		final PPKeyTransaction transaction = new PPKeyTransaction();
		transaction.setMsn(VMSTestDataProvider.MSN);

		final MeterDetails meterDetails = VMSTestDataProvider.constructMeterDetails();
		EasyMock.expect(transDao.getMeterDetails(VMSTestDataProvider.MSN)).andReturn(meterDetails);
		EasyMock.expect(transDao.update(meterDetails)).andReturn(true);

		EasyMock.replay(transDao);

		ppKeySvcHlpr.updateMeterDetails(Status.SC_100, transaction);
		EasyMock.verify(transDao);

		assertEquals(0, meterDetails.getVendTxnStatus());
		assertEquals(transaction.getPpKey(), meterDetails.getPrepayKey());

	}

	/**
	 * Method to test Send Acknowledgement To SAP
	 * Input - Negative Status
	 * @throws Exception 
	 * @throws Exception
	 */
	@Test
	public void sendAcknowledgementToSAPNegativeStatus() throws Exception {

		final PPKeyTransaction transaction = new PPKeyTransaction();

		EasyMock.expect(ppkSchedulerService.scheduleACKJobtoSAP(0, transaction.getTransactionId(), 0L, 
				transaction.getMsn(), transaction.getMpxn(), Status.SC_300.getStatus(), new Date(), false)).andReturn(true);

		EasyMock.replay(transDao);

		ppKeySvcHlpr.sendAcknowledgementToSAP(transaction, Status.SC_310);

		EasyMock.verify(transDao);

	}

	/**
	 * Method to test Send Acknowledgement To SAP
	 * Positive Scenario
	 * @throws Exception 
	 */
	@Test
	public void sendAcknowledgementToSAP() throws Exception {

		final PPKeyTransaction transaction = new PPKeyTransaction();

		EasyMock.expect(ppkSchedulerService.scheduleACKJobtoSAP(0, transaction.getTransactionId(), 0L, 
				transaction.getMsn(), transaction.getMpxn(), Status.SC_200.getStatus(), new Date(), false)).andReturn(true);

		EasyMock.replay(transDao);

		ppKeySvcHlpr.sendAcknowledgementToSAP(transaction, Status.SC_100);

		EasyMock.verify(transDao);

	}

}
