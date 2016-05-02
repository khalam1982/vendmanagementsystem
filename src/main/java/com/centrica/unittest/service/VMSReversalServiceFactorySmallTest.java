package com.centrica.unittest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Test;

import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.sap.service.ReverseVendResponse;
import com.centrica.vms.ws.service.VMSInternalChannelReversalService;
import com.centrica.vms.ws.service.VMSReversalService;
import com.centrica.vms.ws.service.VMSReversalServiceFactory;

/**
 * Test class to test VMSReversalServiceFactory
 * This unit test class mock all the dependent classes calls
 * and test the functionality of VMSReversalServiceFactory class.
 * 
 * Methods Tested
 * 
 * fetchReversalService(String originalVendRequestKey)
 * prepareReversalFaultResponse(VendOutcomeCode_type1 faultCode, String errorMessage, 
 * ReverseVendResponse paymentResponse)
 */
public class VMSReversalServiceFactorySmallTest {

	private final WSTransactionDAO wsTransDao = EasyMock.createMock(WSTransactionDAO.class);

	private final VMSReversalServiceFactory vmsRvrslServiceFctry = new VMSReversalServiceFactory(wsTransDao);

	/**
	 * Method to test Fetch Reversal Service
	 * @throws Exception
	 */
	@Test
	public void fetchReversalService() throws Exception {

		final VendTxnWSDetails vendTxDetails = VMSTestDataProvider.constructVendTxnWSDetails(Status.SC_200.getStatus());
		vendTxDetails.setSource(VMSTestDataProvider.SOURCE_ONE);

		EasyMock.expect(wsTransDao.getVendTxnWSDetails(VMSTestDataProvider.TRANSACTION_ID)).andReturn(vendTxDetails);
		EasyMock.replay(wsTransDao);

		final VMSReversalService rvrslService = vmsRvrslServiceFctry.fetchReversalService(VMSTestDataProvider.TRANSACTION_ID);

		EasyMock.verify(wsTransDao);

		assertTrue(rvrslService instanceof VMSInternalChannelReversalService);

	}

	/**
	 * Method to test Fetch Reversal Service
	 * @throws Exception
	 */
	@Test
	public void fetchReversalServiceVMSRvrslSrvc() throws Exception {

		final VendTxnWSDetails vendTxDetails = VMSTestDataProvider.constructVendTxnWSDetails(Status.SC_200.getStatus());
		vendTxDetails.setSource(VMSTestDataProvider.SOURCE);
		EasyMock.expect(wsTransDao.getVendTxnWSDetails(VMSTestDataProvider.TRANSACTION_ID)).andReturn(vendTxDetails);
		EasyMock.replay(wsTransDao);

		final VMSReversalService rvrslService = vmsRvrslServiceFctry.fetchReversalService(VMSTestDataProvider.TRANSACTION_ID);

		EasyMock.verify(wsTransDao);

		assertTrue(rvrslService instanceof VMSReversalService);

	}

	/**
	 * Method to test Fetch Reversal Service
	 */
	@Test
	public void fetchReversalServiceNeg() throws Exception {

		EasyMock.expect(wsTransDao.getVendTxnWSDetails(VMSTestDataProvider.TRANSACTION_ID)).andThrow(new DBException());
		EasyMock.replay(wsTransDao);

		final VMSReversalService rvrslService = vmsRvrslServiceFctry.fetchReversalService(VMSTestDataProvider.TRANSACTION_ID);

		EasyMock.verify(wsTransDao);

		assertNull(rvrslService);

	}

	/**
	 * Method to Set and Get Reversal Fault Response
	 */
	@Test
	public void setAndGetReversalFaultResponse() throws Exception {

		final ReverseVendResponse rvrsVendRspReq = new ReverseVendResponse();
		vmsRvrslServiceFctry.setReversalFaultResponse(rvrsVendRspReq);

		final ReverseVendResponse rvrsVendRsp = vmsRvrslServiceFctry.getReversalFaultResponse();

		assertEquals(rvrsVendRspReq, rvrsVendRsp);

	}

}
