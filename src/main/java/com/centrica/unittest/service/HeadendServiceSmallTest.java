package com.centrica.unittest.service;

import static org.junit.Assert.assertEquals;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.client.ServiceClient;
import org.easymock.EasyMock;
import org.junit.Test;

import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.external.service.HeadendCommonService;
import com.centrica.vms.scheduler.external.service.HeadendService;
import com.centrica.vms.scheduler.model.JobDetails;
import com.trilliantnetworks.unity.ws.ApplyVendCodeRequest;
import com.trilliantnetworks.unity.ws.ApplyVendCodeResponse;
import com.trilliantnetworks.unity.ws.LoginResponse;
import com.trilliantnetworks.unity.ws.UnityServiceStub;
import com.trilliantnetworks.unity.ws.result.LoginResult;

/**
 * Test class to test HeadendService
 * This unit test class mock all the dependent classes calls
 * and test the functionality of HeadendService class.
 * 
 * Methods Tested
 * 
 * sendVendCode(JobDetails jobDetails)
 * prepareSOAPEnvelope(JobDetails jobDetails)
 * getSOAPEnvelope()
 */
public class HeadendServiceSmallTest {

	private static final String HTTP_URL = "http://www.localhost.com";
	private static final String SESSION_ID = "123334545445";

	private final HeadendCommonService heCmnService = EasyMock.createMock(HeadendCommonService.class);
	private final VMSUtils vmsUtils = EasyMock.createMock(VMSUtils.class);
	private final VendServiceDetails vendSrvc = EasyMock.createMock(VendServiceDetails.class);
	private final UnityServiceStub unityServiceStub = EasyMock.createMock(UnityServiceStub.class);
	private final HeadendService headEndService = new HeadendService(heCmnService, vmsUtils);

	/**
	 * Method to test Send Vend Code
	 * @throws Exception
	 */
	@Test
	public void sendVendCode() throws Exception {

		final JobDetails jobDetails = new JobDetails();
		jobDetails.setDeviceType(DeviceTypeEnum.PH3.getDeviceType());

		EasyMock.expect(vmsUtils.getVendServiceDetails(EasyMock.anyString())).andReturn(vendSrvc);

		EasyMock.expect(vendSrvc.getUrl()).andReturn(HTTP_URL);

		EasyMock.expect(heCmnService.prepareUnityServiceStub(vendSrvc, HTTP_URL)).andReturn(unityServiceStub);

		final LoginResponse loginRsp = new LoginResponse();
		final LoginResult result = new LoginResult();
		result.setSessionId(SESSION_ID);
		loginRsp.setResult(result);

		EasyMock.expect(heCmnService.loginService(unityServiceStub, vendSrvc)).andReturn(loginRsp);

		EasyMock.expect(heCmnService.prepareSessionHeader(loginRsp, unityServiceStub)).andReturn(unityServiceStub);

		final ApplyVendCodeRequest applyVendCodeRequest = new ApplyVendCodeRequest();
		EasyMock.expect(heCmnService.prepareApplyVendCodeRequest(jobDetails)).andReturn(applyVendCodeRequest);

		final ApplyVendCodeResponse applyVendCodeResponse = new ApplyVendCodeResponse();
		EasyMock.expect(unityServiceStub.applyVendCode(applyVendCodeRequest)).andReturn(applyVendCodeResponse);

		EasyMock.expect(heCmnService.processApplyVendCodeResponse(applyVendCodeResponse, applyVendCodeRequest)).andReturn(1);

		heCmnService.logoutService(unityServiceStub, SESSION_ID);
		EasyMock.expectLastCall();

		EasyMock.replay(vmsUtils);
		EasyMock.replay(heCmnService);
		EasyMock.replay(vendSrvc);
		EasyMock.replay(unityServiceStub);

		final int status = headEndService.sendVendCode(jobDetails);

		EasyMock.verify(vmsUtils);
		EasyMock.verify(heCmnService);
		EasyMock.verify(vendSrvc);
		EasyMock.verify(unityServiceStub);

		assertEquals(1, status);

	}

	/**
	 * Method to test Prepare SOAP Envelope
	 * @throws Exception 
	 */
	@Test(expected = NullPointerException.class)
	public void prepareSOAPEnvelope() throws Exception {

		final JobDetails jobDetails = new JobDetails();

		final ApplyVendCodeRequest applyVendCodeRequest = new ApplyVendCodeRequest();
		EasyMock.expect(heCmnService.prepareApplyVendCodeRequest(jobDetails)).andReturn(applyVendCodeRequest);

		final ApplyVendCodeResponse applyVendCodeResponse = new ApplyVendCodeResponse();
		EasyMock.expect(unityServiceStub.applyVendCode(applyVendCodeRequest)).andReturn(applyVendCodeResponse);

		EasyMock.replay(heCmnService);
		EasyMock.replay(unityServiceStub);
		headEndService.prepareSOAPEnvelope(jobDetails);

		EasyMock.verify(heCmnService);
		EasyMock.verify(unityServiceStub);

	}

	/**
	 * Method to test Send Vend Code
	 * @throws Exception 
	 */
	@Test(expected = NullPointerException.class)
	public void getSOAPEnvelope() throws Exception {

		final SOAPEnvelope env =  EasyMock.createMock(SOAPEnvelope.class);

		final ServiceClient svcClient =  EasyMock.createMock(ServiceClient.class);

		unityServiceStub._setServiceClient(svcClient);

		EasyMock.expect(unityServiceStub._getServiceClient().getLastOperationContext().getMessageContexts().get("Out").getEnvelope()).andReturn(env);

		EasyMock.replay(heCmnService);
		EasyMock.replay(unityServiceStub);

		headEndService.getSOAPEnvelope();

		EasyMock.verify(heCmnService);
		EasyMock.verify(unityServiceStub);

	}

}
