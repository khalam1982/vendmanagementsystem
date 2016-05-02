package com.centrica.unittest.service;

import static org.junit.Assert.assertFalse;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.easymock.EasyMock;
import org.junit.Test;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.scheduler.DAO.SchedulerTransactionDAO;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.external.service.HeadendCommonService;
import com.centrica.vms.scheduler.model.UtilityJobDetails;
import com.centrica.vms.scheduler.service.HEMessagePhase3Service;
import com.trilliantnetworks.unity.ws.LoginResponse;
import com.trilliantnetworks.unity.ws.UnityServiceStub;
import com.trilliantnetworks.unity.ws.result.LoginResult;

/**
 * Test class to test HEMessagePhase3Service
 * This unit test class mock all the dependent classes calls
 * and test the functionality of HEMessagePhase3Service class.
 * 
 * Methods Tested
 * 
 * processUnsentMessages(UtilityJobDetails utilityJobDetails)
 */
public class HEMessagePhase3ServiceTest {

	private static final String HTTP_URL = "http://www.localhost.com";
	private static final String UTILITY_MAXCOUNT = "UTILITY_MAXCOUNT";
	private static final String COUNT = "20";
	private static final String SESSION_ID = "123334545445";

	/**
	 * Method to test Process Unsent Messages
	 * @throws Exception
	 */
	@Test
	public void processUnsentMessages() throws Exception {

		final SchedulerTransactionDAO schedulerTransDAO = EasyMock.createMock(SchedulerTransactionDAO.class);
		final HeadendCommonService heCmnService = EasyMock.createMock(HeadendCommonService.class);
		final VMSUtils vmsUtils = EasyMock.createMock(VMSUtils.class);

		final URL url = new URL(HTTP_URL);
		final HEMessagePhase3Service hePh3Service = new HEMessagePhase3Service(url, ESAPI.getLogger(getClass().getName()), 
				vmsUtils, schedulerTransDAO, heCmnService);

		final ArrayList<VMSMessagingSystem> vmsMsgSystemList = new ArrayList<VMSMessagingSystem>();

		final Properties properties = new Properties();
		properties.put(UTILITY_MAXCOUNT, COUNT);

		EasyMock.expect(vmsUtils.loadProperties()).andReturn(properties);

		EasyMock.expect(vmsUtils.removeChar(EasyMock.anyString(), EasyMock.anyChar())).andReturn("123345656567676878");

		EasyMock.expect(schedulerTransDAO.getUnsentMessages(20, 1)).andReturn(vmsMsgSystemList);

		final VendServiceDetails vendSrvc = EasyMock.createMock(VendServiceDetails.class);

		EasyMock.expect(vmsUtils.getVendServiceDetails(EasyMock.anyString())).andReturn(vendSrvc);

		final UnityServiceStub unityServiceStub = EasyMock.createMock(UnityServiceStub.class);
		EasyMock.expect(heCmnService.prepareUnityServiceStub(vendSrvc, url.toString())).andReturn(unityServiceStub);

		final LoginResponse loginRsp = new LoginResponse();
		final LoginResult result = new LoginResult();
		result.setSessionId(SESSION_ID);
		loginRsp.setResult(result);
		EasyMock.expect(heCmnService.loginService(unityServiceStub, vendSrvc)).andReturn(loginRsp);
		heCmnService.logoutService(unityServiceStub, SESSION_ID);
		EasyMock.expectLastCall();

		EasyMock.replay(schedulerTransDAO);
		EasyMock.replay(vmsUtils);
		EasyMock.replay(heCmnService);

		final UtilityJobDetails utilityJobDetails = new UtilityJobDetails();
		utilityJobDetails.setCount(COUNT);

		assertFalse(hePh3Service.processUnsentMessages(utilityJobDetails));

		EasyMock.verify(schedulerTransDAO);
		EasyMock.verify(vmsUtils);
		EasyMock.verify(heCmnService);

	}

}
