package com.centrica.unittest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.Trigger;

import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.DAO.SchedulerTransactionDAO;
import com.centrica.vms.scheduler.model.VendTxnSchedulerDetails;
import com.centrica.vms.scheduler.service.VMSScheduleServiceHelper;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;

/**
 * Test class to test VMSSchedulerServiceImpl
 * This unit test class mock all the dependent classes calls
 * and test the functionality of VMSSchedulerServiceImpl class.
 * 
 * scheduleJob(int retryCount,String transactionID,Long holdingPeriod,String pan,String vendCode,
 * String paymentType,String creditValue,Date timestamp, Integer deviceType, Boolean isReschedule)
 * unScheduleJob(String transactionID,Status txnStatus)
 * scheduleHEUtility(String count,String userName, int deviceType)
 * scheduleSAPPIUtility(String count,String userName)
 */
public class VMSSchedulerServiceImplSmallTest {

	private static final String QUARTZ_SCHEDULER = "QuartzScheduler";

	private final SchedulerTransactionDAO schedulerTransactionDAO = EasyMock.createMock(SchedulerTransactionDAO.class);
	private final VMSScheduleServiceHelper helper = EasyMock.createMock(VMSScheduleServiceHelper.class);
	private final Scheduler scheduler = EasyMock.createMock(Scheduler.class);

	private final VMSSchedulerServiceImpl scheduleService = new VMSSchedulerServiceImpl(schedulerTransactionDAO, helper);


	/**
	 * Method to test Schedule Job
	 * @throws Exception
	 */
	@Test
	public void scheduleJob() throws Exception {

		final VendTxnSchedulerDetails vendTransactionDetails = new VendTxnSchedulerDetails();
		vendTransactionDetails.setTransactionID(VMSTestDataProvider.TRANSACTION_ID);

		EasyMock.expect(helper.getScheduler(QUARTZ_SCHEDULER)).andReturn(scheduler);

		EasyMock.expect(scheduler.isStarted()).andReturn(true);

		EasyMock.expect(schedulerTransactionDAO.getVendTxnSchedulerDetails(VMSTestDataProvider.TRANSACTION_ID)).andReturn(vendTransactionDetails);
		EasyMock.expect(schedulerTransactionDAO.update(vendTransactionDetails)).andReturn(true);

		EasyMock.expect(scheduler.scheduleJob((Trigger) EasyMock.anyObject())).andReturn(new Date());

		EasyMock.expect(scheduler.getTriggerState(EasyMock.anyString(), EasyMock.anyString())).andReturn(1);

		EasyMock.replay(schedulerTransactionDAO);
		EasyMock.replay(helper);
		EasyMock.replay(scheduler);

		final boolean status = scheduleService.scheduleJob(1, VMSTestDataProvider.TRANSACTION_ID, 0L, VMSTestDataProvider.PAN, VMSTestDataProvider.VEND_CODE, 
				VMSTestDataProvider.PAY_CHANNEL, VMSTestDataProvider.CREDIT_VALUE, new Date(), 1, false);

		EasyMock.verify(schedulerTransactionDAO);
		EasyMock.verify(helper);
		EasyMock.verify(scheduler);

		assertTrue(status);

	}

	/**
	 * Method to test Unschedule Job
	 * @throws Exception
	 */
	@Test
	public void unscheduleJob() throws Exception {

		final VendTxnSchedulerDetails vendTransactionDetails = new VendTxnSchedulerDetails();
		vendTransactionDetails.setTransactionID(VMSTestDataProvider.TRANSACTION_ID);
		vendTransactionDetails.setTriggerName("Trigger12");
		vendTransactionDetails.setTxnStatusDetails(VMSTestDataProvider.constructVendTxnStatus(Status.SC_200.getStatus()));

		EasyMock.expect(helper.getScheduler(QUARTZ_SCHEDULER)).andReturn(scheduler);

		EasyMock.expect(scheduler.isStarted()).andReturn(true);

		EasyMock.expect(schedulerTransactionDAO.getVendTxnSchedulerDetails(VMSTestDataProvider.TRANSACTION_ID)).andReturn(vendTransactionDetails);

		EasyMock.expect(scheduler.unscheduleJob(EasyMock.anyString(), EasyMock.anyString())).andReturn(true);

		EasyMock.expect(schedulerTransactionDAO.update(vendTransactionDetails)).andReturn(true);

		EasyMock.replay(schedulerTransactionDAO);
		EasyMock.replay(helper);
		EasyMock.replay(scheduler);

		final boolean status = scheduleService.unScheduleJob(VMSTestDataProvider.TRANSACTION_ID, Status.SC_200);

		EasyMock.verify(schedulerTransactionDAO);
		EasyMock.verify(helper);
		EasyMock.verify(scheduler);

		assertTrue(status);

	}

	/**
	 * Method to test Schedule HE Utility
	 * @throws Exception
	 */
	@Test
	public void scheduleHEUtility() throws Exception {

		EasyMock.expect(helper.getScheduler(QUARTZ_SCHEDULER)).andReturn(scheduler);

		EasyMock.expect(scheduler.isStarted()).andReturn(true);

		EasyMock.expect(scheduler.getTriggerState(EasyMock.anyString(), EasyMock.anyString())).andReturn(-1).times(2);

		EasyMock.expect(scheduler.scheduleJob((Trigger) EasyMock.anyObject())).andReturn(new Date());

		EasyMock.replay(helper);
		EasyMock.replay(scheduler);

		final Integer status = scheduleService.scheduleHEUtility("1", VMSTestDataProvider.TEST, 1);

		EasyMock.verify(helper);
		EasyMock.verify(scheduler);

		assertEquals(-1, status);

	}

	/**
	 * Method to test Schedule SAP Utility
	 * @throws Exception
	 */
	@Test
	public void scheduleSAPUtility() throws Exception {

		EasyMock.expect(helper.getScheduler(QUARTZ_SCHEDULER)).andReturn(scheduler);

		EasyMock.expect(scheduler.isStarted()).andReturn(true);

		EasyMock.expect(scheduler.getTriggerState(EasyMock.anyString(), EasyMock.anyString())).andReturn(-1).times(2);

		EasyMock.expect(scheduler.scheduleJob((Trigger) EasyMock.anyObject())).andReturn(new Date());

		EasyMock.replay(helper);
		EasyMock.replay(scheduler);

		final Integer status = scheduleService.scheduleHEUtility("1", VMSTestDataProvider.TEST, 1);

		EasyMock.verify(helper);
		EasyMock.verify(scheduler);

		assertEquals(-1, status);

	}

}
