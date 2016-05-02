package com.centrica.unittest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Test;

import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcome;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;

import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.dao.VendReportTransactionDAO;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.form.AckRequestDetailsForm;
import com.centrica.vms.form.AdjustmentRequestDetailsForm;
import com.centrica.vms.form.VendRequestDetailsForm;
import com.centrica.vms.form.VoidRequestDetailsForm;
import com.centrica.vms.model.FunctionDetails;
import com.centrica.vms.model.GroupDetails;
import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.model.StatusDetails;
import com.centrica.vms.model.UserCredentials;
import com.centrica.vms.model.UserDetails;
import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.service.VMSWebActionService;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceipt;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceiptResponse;
import com.centrica.vms.ws.headend.ack.model.ResponseCode;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.sap.service.CreateVend;
import com.centrica.vms.ws.sap.service.CreateVendResponse;
import com.centrica.vms.ws.sap.service.VMSSAPService;
import com.centrica.vms.ws.sap.service.VoidVend;
import com.centrica.vms.ws.sap.service.VoidVendResponse;
import com.centrica.vms.ws.service.VMSServiceImpl;

/**
 * Test class to test VMSWebActionService
 * This unit test class mock all the dependent classes calls
 * and test the functionality of VMSWebActionService class.
 * 
 * Methods Tested
 * 
 * getMeterDetails(String pan)
 * saveMeterDetails(MeterDetails meterDetails)
 * updateMeterDetails(MeterDetails meterDetails)
 * deleteMeterDetails(MeterDetails meterDetails)
 * getVendHistory(String pan)
 * sendVendRequest(VendRequestDetailsForm vendRequestDetailsForm)
 * sendVoidRequest(VoidRequestDetailsForm voidRequestDetailsForm)
 * sendAckRequest(AckRequestDetailsForm ackRequestDetailsForm)
 * sendVendReportRequest(VendReportRequestForm vendReportRequestForm, OutputStream out)
 * getUserDetails(String userName, String password)
 * getGroupDetails(String groupID)
 * getUserDetails(String groupID)
 * getFunctionDetails(String groupID)
 * updateUserDetails(UserDetails userDetails,Boolean isPowerUser)
 * getUser(String userName,String groupID)
 * getResendTransactionCount(String userName, Integer type)
 * saveUserDetails(UserDetails userDetails)
 * deleteUserDetails(UserDetails userDetails,Boolean isPowerUser)
 * getUserCredentials(String userName)
 * updateUserCredentials(UserCredentials userCredentials)
 * scheduleHEUtility(HEUtilityRequestForm heUtilityRequestForm, int deviceType)
 * sendAdjustmentRequest(AdjustmentRequestDetailsForm adjustmentRequestDetailsForm)
 * getVendSourceList()
 * getVendStatusList()
 */
public class VMSWebActionServiceSmallTest {

	private final WSTransactionDAO wsTransDao = EasyMock.createMock(WSTransactionDAO.class);
	private final VMSTransactionDAO vmsTransDao = EasyMock.createMock(VMSTransactionDAO.class);
	private final VendReportTransactionDAO reportTransDao = EasyMock.createMock(VendReportTransactionDAO.class);
	private final VMSSAPService vmssapService = EasyMock.createMock(VMSServiceImpl.class);
	private final VMSSchedulerServiceImpl vmsSchedulerService = EasyMock.createMock(VMSSchedulerServiceImpl.class);
	private final VMSServiceImpl vmsService = EasyMock.createMock(VMSServiceImpl.class);

	private final VMSWebActionService webActionService = new VMSWebActionService(wsTransDao, vmsTransDao, reportTransDao, 
			vmssapService, vmsSchedulerService, vmsService);

	/**
	 * Method to test Get Meter Details
	 * @throws Exception
	 */
	@Test
	public void getMeterDetails() throws Exception {

		final MeterDetails reqMeterDetails = VMSTestDataProvider.constructMeterDetails();
		EasyMock.expect(wsTransDao.getMeterDetails(VMSTestDataProvider.PAN)).andReturn(reqMeterDetails);
		EasyMock.replay(wsTransDao);

		final MeterDetails resMeterDetails = webActionService.getMeterDetails(VMSTestDataProvider.PAN);
		EasyMock.verify(wsTransDao);

		assertEquals(reqMeterDetails.getDeviceTypeID(), resMeterDetails.getDeviceTypeID());
		assertEquals(reqMeterDetails.getFuelTypeID(), resMeterDetails.getFuelTypeID());
		assertEquals(reqMeterDetails.getMsn(), resMeterDetails.getMsn());
		assertEquals(reqMeterDetails.getPan(), resMeterDetails.getPan());
		assertEquals(reqMeterDetails.getPrepayKey(), resMeterDetails.getPrepayKey());

	}

	/**
	 * Method to test Save Meter Details
	 * @throws Exception
	 */
	@Test
	public void saveMeterDetails() throws Exception {

		final MeterDetails reqMeterDetails = VMSTestDataProvider.constructMeterDetails();
		EasyMock.expect(vmsTransDao.insert(reqMeterDetails)).andReturn(true);
		EasyMock.replay(vmsTransDao);

		final boolean status = webActionService.saveMeterDetails(reqMeterDetails);
		EasyMock.verify(vmsTransDao);

		assertTrue(status);

	}

	/**
	 * Method to test Save Meter Details
	 * @throws Exception
	 */
	@Test
	public void saveMeterDetailsException() throws Exception {

		final MeterDetails reqMeterDetails = VMSTestDataProvider.constructMeterDetails();
		EasyMock.expect(vmsTransDao.insert(reqMeterDetails)).andReturn(false).andThrow(new DBException()).times(2);
		EasyMock.replay(vmsTransDao);

		final boolean status = webActionService.saveMeterDetails(reqMeterDetails);
		assertFalse(status);

	}

	/**
	 * Method to test Update Meter Details
	 * @throws Exception
	 */
	@Test
	public void updateMeterDetails() throws Exception {

		final MeterDetails reqMeterDetails = VMSTestDataProvider.constructMeterDetails();
		EasyMock.expect(vmsTransDao.update(reqMeterDetails)).andReturn(true);
		EasyMock.replay(vmsTransDao);

		final boolean status = webActionService.updateMeterDetails(reqMeterDetails);
		EasyMock.verify(vmsTransDao);

		assertTrue(status);

	}

	/**
	 * Method to test Update Meter Details
	 * @throws Exception
	 */
	@Test
	public void deleteMeterDetails() throws Exception {

		final MeterDetails reqMeterDetails = VMSTestDataProvider.constructMeterDetails();
		EasyMock.expect(vmsTransDao.delete(reqMeterDetails)).andReturn(true);
		EasyMock.replay(vmsTransDao);

		final boolean status = webActionService.deleteMeterDetails(reqMeterDetails);
		EasyMock.verify(vmsTransDao);

		assertTrue(status);

	}

	/**
	 * Method to test Get Vend History
	 * @throws Exception
	 */
	@Test
	public void getVendHistory() throws Exception {

		final ArrayList<VendHistoryDetails> vendHistoryList = new ArrayList<VendHistoryDetails>();
		final VendHistoryDetails historyDetails = VMSTestDataProvider.constructVendHistoryDetails();
		vendHistoryList.add(historyDetails);

		EasyMock.expect(vmsTransDao.getVendHistory(VMSTestDataProvider.PAN)).andReturn(vendHistoryList);
		EasyMock.replay(vmsTransDao);

		final ArrayList<VendHistoryDetails> resHistoryList = webActionService.getVendHistory(VMSTestDataProvider.PAN);
		EasyMock.verify(vmsTransDao);

		for( VendHistoryDetails history : resHistoryList ) {
			assertEquals(historyDetails.getTransactionID(), history.getTransactionID());
			assertEquals(historyDetails.getTransactionType(), history.getTransactionType());
		}

	}

	/**
	 * Method to test Send Vend Request
	 * @throws Exception
	 */
	@Test
	public void sendVendRequest() throws Exception {

		final CreateVendResponse paymentResponse = VMSTestDataProvider.constructCreateVendResponse();
		final VendRequestDetailsForm reqVendRequestDetailsForm = VMSTestDataProvider.constructVendRequestDetailsForm();

		EasyMock.expect(vmssapService.generatePaymentCode(EasyMock.anyObject(CreateVend.class))).andReturn(paymentResponse);
		EasyMock.replay(vmssapService);

		VendRequestDetailsForm resVendRequestDetailsForm = webActionService.sendVendRequest(reqVendRequestDetailsForm);
		EasyMock.verify(vmssapService);

		assertEquals(reqVendRequestDetailsForm.getCreditType(), resVendRequestDetailsForm.getCreditType());
		assertEquals(reqVendRequestDetailsForm.getPan(), resVendRequestDetailsForm.getPan());
		assertEquals(reqVendRequestDetailsForm.getPayChannel(), resVendRequestDetailsForm.getPayChannel());
		assertEquals(reqVendRequestDetailsForm.getStatusCode(), resVendRequestDetailsForm.getStatusCode());
		assertEquals(reqVendRequestDetailsForm.getTransactionID(), resVendRequestDetailsForm.getTransactionID());

	}

	/**
	 * Method to test Send Void Request
	 * @throws Exception
	 */
	@Test
	public void sendVoidRequest() throws Exception {

		final VoidRequestDetailsForm reqDetailsForm = VMSTestDataProvider.constructVoidRequestDetailsForm();
		final VendOutcome outCme = VMSTestDataProvider.constructVendOutcome();
		final VoidVendResponse response = VMSTestDataProvider.constructVoidVendResponse(outCme);

		EasyMock.expect(vmssapService.voidPayment(EasyMock.anyObject(VoidVend.class))).andReturn(response);
		EasyMock.replay(vmssapService);

		final VoidRequestDetailsForm resDetailsForm = webActionService.sendVoidRequest(reqDetailsForm);
		EasyMock.verify(vmssapService);

		assertEquals(reqDetailsForm.getPan(), resDetailsForm.getPan());
		assertEquals(reqDetailsForm.getTransactionID(), resDetailsForm.getTransactionID());
		assertEquals(VendOutcomeCode_type1.value1.getValue(), resDetailsForm.getStatusCode());
		assertEquals(outCme.getVendOutcomeDescription(), resDetailsForm.getDescription());

	}

	/**
	 * Method to test Send Ack Request
	 * @throws Exception
	 */
	@Test
	public void sendAckRequest() throws Exception {

		final AckRequestDetailsForm reqDetailsForm = new AckRequestDetailsForm();
		reqDetailsForm.setTransactionID(VMSTestDataProvider.TRANSACTION_ID);
		final DeliveryReceiptResponse delivRecptResponse = VMSTestDataProvider.constructDeliveryReceiptResponse();

		EasyMock.expect(vmsService.acknowledgePaymentDelivery(EasyMock.anyObject(DeliveryReceipt.class))).andReturn(delivRecptResponse);
		EasyMock.replay(vmsService);

		final AckRequestDetailsForm resDetailsForm = webActionService.sendAckRequest(reqDetailsForm);
		EasyMock.verify(vmsService);

		assertEquals(ResponseCode.value1.getValue(), Integer.parseInt(resDetailsForm.getStatusCode()));

	}

	/**
	 * Method to test Get User Details
	 * @throws Exception
	 */
	@Test
	public void getUserDetails() throws Exception {

		final ArrayList<UserDetails> userList = VMSTestDataProvider.constructUserDetailsList();
		EasyMock.expect(vmsTransDao.getUserDetails(VMSTestDataProvider.TEST)).andReturn(userList);
		EasyMock.replay(vmsTransDao);

		final UserDetails userDetails = webActionService.getUserDetails(VMSTestDataProvider.TEST, VMSTestDataProvider.TEST);
		EasyMock.verify(vmsTransDao);

		assertEquals(0, userDetails.getPassDueDate());

	}

	/**
	 * Method to test Get Group Details
	 * @throws Exception
	 */
	@Test
	public void getGroupDetails() throws Exception {

		final ArrayList<GroupDetails> groupInpList = new ArrayList<GroupDetails>();

		EasyMock.expect(vmsTransDao.getGroupDetails(VMSTestDataProvider.TEST)).andReturn(groupInpList);
		EasyMock.replay(vmsTransDao);

		final ArrayList<GroupDetails> groupList = webActionService.getGroupDetails(VMSTestDataProvider.TEST);
		EasyMock.verify(vmsTransDao);

		assertEquals(groupInpList, groupList);

	}

	/**
	 * Method to test Get User Details By Group Id
	 * @throws Exception
	 */
	@Test
	public void getUserDetailsByGroupId() throws Exception {

		final ArrayList<UserDetails> userList = VMSTestDataProvider.constructUserDetailsList();

		EasyMock.expect(vmsTransDao.getPowerUserDetails(VMSTestDataProvider.TEST)).andReturn(userList);
		EasyMock.replay(vmsTransDao);

		final UserDetails userDetails = webActionService.getUserDetails(VMSTestDataProvider.TEST);
		EasyMock.verify(vmsTransDao);

		assertEquals(0, userDetails.getPassDueDate());

	}

	/**
	 * Method to test Get Function Details
	 * @throws Exception
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void getFunctionDetails() throws Exception {

		final ArrayList<FunctionDetails> functionInpList = new ArrayList<FunctionDetails>();

		EasyMock.expect(vmsTransDao.getFunctionDetails((Collection<String>)EasyMock.anyObject())).andReturn(functionInpList);
		EasyMock.replay(vmsTransDao);

		final ArrayList<FunctionDetails> functionList = webActionService.getFunctionDetails(VMSTestDataProvider.TEST);
		EasyMock.verify(vmsTransDao);

		assertEquals(functionInpList, functionList);

	}

	/**
	 * Method to test Update User Details
	 * @throws Exception
	 */
	@Test
	public void updateUserDetails() throws Exception {

		final UserDetails user = VMSTestDataProvider.constructUserDetails();
		EasyMock.expect(vmsTransDao.update(user)).andReturn(true);
		EasyMock.replay(vmsTransDao);

		final boolean status = webActionService.updateUserDetails(user, false);
		EasyMock.verify(vmsTransDao);

		assertTrue(status);

	}

	/**
	 * Method to test Update User Details
	 * @throws Exception
	 */
	@Test
	public void updateUserDetailsException() throws Exception {

		final UserDetails user = VMSTestDataProvider.constructUserDetails();
		EasyMock.expect(vmsTransDao.update(user)).andReturn(false);
		EasyMock.replay(vmsTransDao);

		final boolean status = webActionService.updateUserDetails(user, false);
		EasyMock.verify(vmsTransDao);

		assertFalse(status);

	}

	/**
	 * Method to test Get User Details By Name and GroupId
	 * @throws Exception
	 */
	@Test
	public void getUserByNameAndGroupId() throws Exception {

		final ArrayList<UserDetails> userList = VMSTestDataProvider.constructUserDetailsList();
		EasyMock.expect(vmsTransDao.getUserDetails(VMSTestDataProvider.TEST)).andReturn(userList);
		EasyMock.replay(vmsTransDao);

		final UserDetails userDetails = webActionService.getUser(VMSTestDataProvider.TEST, "1");
		EasyMock.verify(vmsTransDao);

		assertEquals(userList.get(0).getUserName(), userDetails.getUserName());
		assertEquals(userList.get(0).getPassword(), userDetails.getPassword());

	}

	/**
	 * Method to test Save User Details
	 * @throws Exception
	 */
	@Test
	public void saveUserDetails() throws Exception {

		final UserDetails user = VMSTestDataProvider.constructUserDetails();
		EasyMock.expect(vmsTransDao.insert(user)).andReturn(true);
		EasyMock.replay(vmsTransDao);

		final boolean status = webActionService.saveUserDetails(user);
		EasyMock.verify(vmsTransDao);

		assertTrue(status);

	}

	/**
	 * Method to test Delete User Details
	 * @throws Exception
	 */
	@Test
	public void deleteUserDetails() throws Exception {

		final UserDetails user = VMSTestDataProvider.constructUserDetails();
		EasyMock.expect(vmsTransDao.delete(user)).andReturn(true);
		EasyMock.replay(vmsTransDao);

		final boolean status = webActionService.deleteUserDetails(user, false);
		EasyMock.verify(vmsTransDao);

		assertTrue(status);

	}

	/**
	 * Method to test Get User Credentials
	 * @throws Exception
	 */
	@Test
	public void getUserCredentials() throws Exception {

		final ArrayList<UserCredentials> userList = new ArrayList<UserCredentials>();
		final UserCredentials user = new UserCredentials();
		user.setUserID(1233456778);
		user.setUserName(VMSTestDataProvider.TEST);
		userList.add(user);

		EasyMock.expect(vmsTransDao.getUserCrendentials(VMSTestDataProvider.TEST)).andReturn(userList);
		EasyMock.replay(vmsTransDao);

		final UserCredentials userCredntl = webActionService.getUserCredentials(VMSTestDataProvider.TEST);
		EasyMock.verify(vmsTransDao);

		assertEquals(user.getUserID(), userCredntl.getUserID());
		assertEquals(user.getUserName(), userCredntl.getUserName());

	}

	/**
	 * Method to test Update User Credentials
	 * @throws Exception
	 */
	@Test
	public void updateUserCredentials() throws Exception {

		final UserCredentials userCredentials = new UserCredentials();
		userCredentials.setUserID(1233456778);
		userCredentials.setUserName(VMSTestDataProvider.TEST);

		EasyMock.expect(vmsTransDao.update(userCredentials)).andReturn(true);
		EasyMock.replay(vmsTransDao);

		final boolean status = webActionService.updateUserCredentials(userCredentials);
		EasyMock.verify(vmsTransDao);

		assertTrue(status);

	}

	/**
	 * Method to test Get Resend Transaction Count
	 * @throws Exception
	 */
	@Test
	public void getResendTransactionCount() throws Exception {

		EasyMock.expect(vmsTransDao.getResendTransactionCount(1)).andReturn(1);
		EasyMock.replay(vmsTransDao);

		Integer result = webActionService.getResendTransactionCount(VMSTestDataProvider.TEST, 1);
		EasyMock.verify(vmsTransDao);

		assertEquals(1, result.intValue());

	}

	/**
	 * Method to test Get Vend Source List
	 * @throws Exception
	 */
	@Test
	public void getVendSourceList() throws Exception {

		final ArrayList<SourceDetails> sourceLst = new ArrayList<SourceDetails>();
		sourceLst.add(VMSTestDataProvider.constructSourceDetails());
		EasyMock.expect(reportTransDao.getVendSourceDetails()).andReturn(sourceLst);
		EasyMock.replay(reportTransDao);

		final ArrayList<SourceDetails> resSourceLst = webActionService.getVendSourceList();
		EasyMock.verify(reportTransDao);

		assertEquals(sourceLst, resSourceLst);

	}

	/**
	 * Method to test Get Vend Status List
	 * @throws Exception
	 */
	@Test
	public void getVendStatusList() throws Exception {

		final ArrayList<StatusDetails> statusLst = new ArrayList<StatusDetails>();
		final StatusDetails status = new StatusDetails();
		status.setStatusCode(200);
		status.setStatusDesc("Successful");
		statusLst.add(status);

		EasyMock.expect(reportTransDao.getVendStatusDetails()).andReturn(statusLst);
		EasyMock.replay(reportTransDao);

		final ArrayList<StatusDetails> resStatusLst = webActionService.getVendStatusList();
		EasyMock.verify(reportTransDao);

		assertEquals(statusLst, resStatusLst);

	}

	/**
	 * Method to test Send Adjustment Request
	 * @throws Exception
	 */
	@Test
	public void sendAdjustmentRequest() throws Exception {

		final AdjustmentRequestDetailsForm adjustmntFrm = new AdjustmentRequestDetailsForm();
		adjustmntFrm.setCreditType("M");
		adjustmntFrm.setCreditValue(VMSTestDataProvider.CREDIT_VALUE);
		adjustmntFrm.setDateTime(new Date());
		adjustmntFrm.setPan(VMSTestDataProvider.PAN);
		adjustmntFrm.setPayChannel(VMSTestDataProvider.PAY_CHANNEL);
		adjustmntFrm.setTransactionID(VMSTestDataProvider.TRANSACTION_ID);
		adjustmntFrm.setHoldingPeriod("23");

		final CreateVendResponse paymentResponse = VMSTestDataProvider.constructCreateVendResponse();

		EasyMock.expect(vmssapService.generatePaymentCode((CreateVend) EasyMock.anyObject())).andReturn(paymentResponse);
		EasyMock.replay(vmssapService);

		webActionService.sendAdjustmentRequest(adjustmntFrm);
		EasyMock.verify(vmssapService);

		assertEquals(paymentResponse.getPaymentResponse().getVendCode().getVendCode(), adjustmntFrm.getVendCode());

	}

}
