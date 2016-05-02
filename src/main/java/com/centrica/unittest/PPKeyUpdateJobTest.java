package com.centrica.unittest;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;

import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.axiom.soap.SOAPEnvelope;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;

import com.centrica.vms.common.DeviceTypeConstants;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.model.PPKeyTxnStatus;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.DAO.PPKeySchedulerDAO;
import com.centrica.vms.scheduler.external.delegate.HeadendDelegate;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.job.PPKeyUpdateJob;
import com.centrica.vms.scheduler.model.JobDetails;
import com.centrica.vms.scheduler.model.PPKeyTxnScheduler;
import com.centrica.vms.scheduler.service.PPKeySchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.service.helper.PPKeyServiceHelper;
import com.centrica.vms.ws.service.mapper.PPKeyUpdateMapper;

public class PPKeyUpdateJobTest extends EasyMockSupport{

	private HeadendDelegate headEndDelegate = createMock(HeadendDelegate.class);
	private PPKeySchedulerDAO schedulerTransactionDAO = createMock(PPKeySchedulerDAO.class);
	private VMSUtils util = createMock(VMSUtils.class);
	private PPKeyUpdateMapper mapper = createMock(PPKeyUpdateMapper.class);
	private PPKeySchedulerServiceImpl ppKeySchedulerServiceImpl = createMock(PPKeySchedulerServiceImpl.class);
	private WSTransactionDAO wsTransactionDAO = createMock(WSTransactionDAO.class);
	private JobExecutionContext jobExecutionContext = createMock(JobExecutionContext.class);
	private VMSTransactionDAO vmsTransactionDAO = createMock(VMSTransactionDAO.class);
	private final PPKeyServiceHelper svchelper = createMock(PPKeyServiceHelper.class);;
	private PPKeyUpdateJob ppKeyUpdateJob;
	
	private JobDataMap jobMap = new JobDataMap();
	private PPKeyTxnScheduler dummyTxnScheduler;
	
	@Before
	public void init() throws Exception {
		ppKeyUpdateJob = new PPKeyUpdateJob(headEndDelegate, schedulerTransactionDAO, util, svchelper, mapper, ppKeySchedulerServiceImpl, wsTransactionDAO, vmsTransactionDAO);
		buildJobMap();
		Trigger trigger = createMock(Trigger.class);
		expect(jobExecutionContext.getTrigger()).andReturn(trigger).anyTimes();
		expect(trigger.getJobDataMap()).andReturn(jobMap).anyTimes();
		dummyTxnScheduler = new PPKeyTxnScheduler();
		dummyTxnScheduler.setTriggerName("PPK_TRIGGER");
		expect(schedulerTransactionDAO.getPPKeyTxnSchedulerDetails("PPK12345")).andReturn(dummyTxnScheduler).anyTimes();
		expect(util.loadProperties()).andReturn(initProps()).anyTimes();
		//
		expect(schedulerTransactionDAO.update(dummyTxnScheduler)).andReturn(true).anyTimes();
		
	}

	private VendServiceDetails initContextParams() {
		VendServiceDetails details = new VendServiceDetails();
		details.setNoofretries(5);
		details.setRetryPeriod("300,300,300,300,300");
		return details;
	}

	private Properties initProps() {
		Properties properties = new Properties();
		properties.put("JOB_TRANSACTIONID", "TransactionID");
		properties.put("JOB_MSN", "Msn");
		properties.put("JOB_PPKEY", "PPKey");
		properties.put("PPK_JOB_TIMESTAMP", "PPKUpdtTimestamp");
		properties.put("JOB_RETRY_COUNT", "VendStatusRetryCount");
		properties.put("JOB_STATUS", "Status");
		return properties;
	}
	
	private void buildJobMap() {
		jobMap.put("TransactionID", "PPK12345");
		jobMap.put("Msn", "E6S02220171456");
		jobMap.put("PPKey", "12345");
		jobMap.put("PPKUpdtTimestamp", new Date());
		jobMap.put("VendStatusRetryCount", 0);
		jobMap.put("Status", 0);
	}

	@Test
	public void checkForStatusUpdateForSuccessCase() throws Exception {
		mapper.setPPKTransStatus(anyObject(PPKeyTxnScheduler.class), anyObject(Status.class), anyString(), anyString());
		mapper.setPPKTransStatus(anyObject(PPKeyTxnScheduler.class), anyObject(Status.class), anyString(), anyString());
		expect(headEndDelegate.sendPPKey(anyObject(JobDetails.class))).andReturn(200).times(1);
		replayAll();
		ppKeyUpdateJob.execute(jobExecutionContext);
		verifyAll();
		
	}

	@Test
	public void checkForRetryCase() throws Exception {
		jobMap.put("VendStatusRetryCount", 1);
		expect(util.getVendServiceDetails(DeviceTypeConstants.PPKKEY_HES_UPDATE)).andReturn(initContextParams());
		mapper.updateTimestamp(dummyTxnScheduler);
		expect(headEndDelegate.sendPPKey(anyObject(JobDetails.class))).andReturn(408).times(1);
		expect(wsTransactionDAO.getPPKeyTxnDetails("PPK12345")).andReturn(dummyTxnScheduler).times(1);
		expect(ppKeySchedulerServiceImpl.schedulePPKeyUpdatetoHeadEnd(2, "PPK12345", 300L, "E6S02220171456", "12345", (Date)jobMap.get("PPKUpdtTimestamp"), true)).andReturn(true);
		dummyTxnScheduler.setStatusDetails(statusSet(570));
		replayAll();
		ppKeyUpdateJob.execute(jobExecutionContext);
		verifyAll();
		
	}

	@Test
	public void checkForAllRetriesFailedCase() throws Exception {
		jobMap.put("VendStatusRetryCount", 5);
		expect(util.getVendServiceDetails(DeviceTypeConstants.PPKKEY_HES_UPDATE)).andReturn(initContextParams());
		expect(headEndDelegate.sendPPKey(anyObject(JobDetails.class))).andReturn(408).times(1);
		mapper.setPPKTransStatus(eq(dummyTxnScheduler), anyObject(Status.class), anyString(), anyString());
		headEndDelegate.preparePPKeySOAPEnvelope(anyObject(JobDetails.class));
		mapper.updateTimestamp(dummyTxnScheduler);
		SOAPEnvelope envelope = createMock(SOAPEnvelope.class);
		expect(headEndDelegate.getSOAPEnvelope()).andReturn(envelope);
		dummyTxnScheduler.setStatusDetails(statusSet(570));
		expect(vmsTransactionDAO.insert(anyObject(VMSMessagingSystem.class))).andReturn(true);
		replayAll();
		ppKeyUpdateJob.execute(jobExecutionContext);
		verifyAll();
		
	}

	@Test
	public void checkForAlreadyAcknowledgedCase() throws Exception {
		jobMap.put("VendStatusRetryCount", 1);
		expect(util.getVendServiceDetails(DeviceTypeConstants.PPKKEY_HES_UPDATE)).andReturn(initContextParams());
		expect(headEndDelegate.sendPPKey(anyObject(JobDetails.class))).andReturn(408).times(1);
		mapper.updateTimestamp(dummyTxnScheduler);
		expect(wsTransactionDAO.getPPKeyTxnDetails("PPK12345")).andReturn(dummyTxnScheduler).times(1);
		dummyTxnScheduler.setStatusDetails(statusSet(100));
		replayAll();
		ppKeyUpdateJob.execute(jobExecutionContext);
		verifyAll();
		
	}

	private Set<PPKeyTxnStatus> statusSet(int statusVal) {
		Set<PPKeyTxnStatus> statusDetails = new HashSet<PPKeyTxnStatus>();
		PPKeyTxnStatus status = new PPKeyTxnStatus();
		status.setStatus(statusVal);
		statusDetails.add(status);
		return statusDetails;
	}


}
