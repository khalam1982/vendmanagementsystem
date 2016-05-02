package com.centrica.unittest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Set;

import org.easymock.EasyMock;
import org.junit.Test;

import smartmeterprocessing.enterprise.uk.co.britishgas.ReverseVendMessage;
import smartmeterprocessing.enterprise.uk.co.britishgas.ReverseVendResponseMessage;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;

import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;
import com.centrica.vms.ws.sap.service.ReverseVend;
import com.centrica.vms.ws.sap.service.ReverseVendResponse;
import com.centrica.vms.ws.service.VMSReversalService;
import common.enterprise.uk.co.britishgas.BG_MessageHeader;

/**
 * Test class to test VMSReversalService
 * This unit test class mock all the dependent classes calls
 * and test the functionality of VMSReversalService class.
 * 
 * Methods Tested
 * 
 * handleReversalRequest(ReverseVend reverseVend)
 * fetchTransactionDetails(String originalTransactionID)
 * processReversePayment(String originalTransactionID, String transactionID,
 * String transactionType, Set<VendTxnStatus> vendTxnStatus, String msn, Calendar timestamp)
 * prepareReverseTxnResponse(String originalTransactionID, String transactionID, Boolean status, ReverseVendResponse reverseTxnResponse)
 */
public class VMSReversalServiceSmallTest {

	private static final String CLASS_NAME = "com.centrica.vms.ws.service.VMSReversalService";
	private final String ACCEPTED = "ACCEPTED";

	private final WSTransactionDAO wsTransDao = EasyMock.createMock(WSTransactionDAO.class);
	private final VMSSchedulerServiceImpl vmsSchedulerService = EasyMock.createMock(VMSSchedulerServiceImpl.class);

	private final VMSReversalService reversalService = new VMSReversalService(wsTransDao, vmsSchedulerService);

	/**
	 * Method to test Handle void request
	 * @throws Exception
	 */
	@Test
	public void handleVoidRequest() throws Exception {

		final ReverseVend reverseVend = constructReverseVend();

		final VendTxnWSDetails vendTxDetails = VMSTestDataProvider.constructVendTxnWSDetails(Status.SC_120.getStatus());
		vendTxDetails.setTransactionType(TransactionType.ElectricPurchase.getTransactionType());

		EasyMock.expect(wsTransDao.getVendTxnWSDetails(VMSTestDataProvider.TRANSACTION_ID)).andReturn(vendTxDetails);

		EasyMock.expect(wsTransDao.insert(EasyMock.anyObject())).andReturn(true);

		EasyMock.expect(vmsSchedulerService.unScheduleJob(VMSTestDataProvider.TRANSACTION_ID, Status.SC_130)).andReturn(true);

		EasyMock.replay(wsTransDao);
		EasyMock.replay(vmsSchedulerService);

		final ReverseVendResponse reverseVendRsp = reversalService.handleReversalRequest(reverseVend);

		EasyMock.verify(wsTransDao);
		EasyMock.verify(vmsSchedulerService);

		assertEquals(VendOutcomeCode_type1.value1, reverseVendRsp.getReverseTxnResponse().getVendOutcome().getVendOutcomeCode());
		assertEquals(ACCEPTED, reverseVendRsp.getReverseTxnResponse().getVendOutcome().getVendOutcomeDescription());

	}

	/**
	 * Method to test Fetch Transaction Details
	 */
	public void fetchTransactionDetails() throws Exception {

		final VendTxnWSDetails vendTxDetails = VMSTestDataProvider.constructVendTxnWSDetails(Status.SC_120.getStatus());

		EasyMock.expect(wsTransDao.getVendTxnWSDetails(VMSTestDataProvider.TRANSACTION_ID)).andReturn(vendTxDetails);
		EasyMock.replay(wsTransDao);

		final VendTxnWSDetails resVendTxDetails = reversalService.fetchTransactionDetails(VMSTestDataProvider.TRANSACTION_ID);

		EasyMock.verify(wsTransDao);

		assertEquals(vendTxDetails, resVendTxDetails);

	}

	/**
	 * Method to test Process Reverse Payment
	 */
	@Test
	public void processReversePayment() throws Exception {

		final ReverseVendResponse vendRspns = new ReverseVendResponse();
		final ReverseVendResponseMessage msg = new ReverseVendResponseMessage();
		msg.setVendOutcome(VMSTestDataProvider.constructVendOutcome());
		vendRspns.setReverseTxnResponse(msg);

		EasyMock.expect(vmsSchedulerService.unScheduleJob(VMSTestDataProvider.ORIG_TRANS_ID, Status.SC_130)).andReturn(true);
		EasyMock.replay(vmsSchedulerService);

		final Class<?> reversalSrvcClass = Class.forName(CLASS_NAME);
		final Method method = reversalSrvcClass.getDeclaredMethod("processReversePayment", String.class, String.class, String.class, 
				Set.class, String.class, Calendar.class);
		method.setAccessible(true);
		final ReverseVendResponse response = (ReverseVendResponse) method.invoke(reversalService, VMSTestDataProvider.ORIG_TRANS_ID, 
				VMSTestDataProvider.TRANSACTION_ID, VMSTestDataProvider.TEST, VMSTestDataProvider.constructVendTxnStatus(Status.SC_120.getStatus()), 
				VMSTestDataProvider.MSN, null);

		EasyMock.verify(vmsSchedulerService);

		assertNotNull(response.getReverseTxnResponse().getVendOutcome());

	}

	/**
	 * Method to test Prepare Reverse TxnResponse
	 * @throws Exception
	 */
	@Test
	public void prepareReverseTxnResponse() throws Exception {

		final ReverseVendResponse vendRspns = new ReverseVendResponse();
		final ReverseVendResponseMessage msg = new ReverseVendResponseMessage();
		msg.setVendOutcome(VMSTestDataProvider.constructVendOutcome());
		vendRspns.setReverseTxnResponse(msg);

		EasyMock.expect(wsTransDao.insert(EasyMock.anyObject())).andReturn(true);
		EasyMock.replay(wsTransDao);

		final Class<?> reversalSrvcClass = Class.forName(CLASS_NAME);
		final Method method = reversalSrvcClass.getDeclaredMethod("prepareReverseTxnResponse", String.class, String.class, Boolean.class, ReverseVendResponse.class);
		method.setAccessible(true);
		final ReverseVendResponse response = (ReverseVendResponse) method.invoke(reversalService, VMSTestDataProvider.ORIG_TRANS_ID, 
				VMSTestDataProvider.TRANSACTION_ID, true,vendRspns);

		EasyMock.verify(wsTransDao);

		assertNotNull(response.getReverseTxnResponse().getVendOutcome());

	}

	/**
	 * Constructs Reverse Vend
	 * @return ReverseVend
	 */
	private ReverseVend constructReverseVend() {

		final ReverseVend reverseVend = new ReverseVend();
		final ReverseVendMessage rvrsVendMsg = new ReverseVendMessage();
		final BG_MessageHeader msgHdr = new BG_MessageHeader();
		rvrsVendMsg.setMessageHeader(msgHdr);
		rvrsVendMsg.setOriginalVendRequestKey(VMSTestDataProvider.constructVendRequestKey());
		rvrsVendMsg.setVendRequestKey(VMSTestDataProvider.constructVendRequestKey());
		rvrsVendMsg.setVendRequestTimeStamp(VMSTestDataProvider.constructVendRequestTimeStamp());			
		reverseVend.setReverseTxnRequest(rvrsVendMsg);
		return reverseVend;

	}

}
