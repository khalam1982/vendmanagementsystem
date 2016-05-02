package com.centrica.unittest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;

import org.easymock.EasyMock;
import org.junit.Test;

import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;

import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.CreditIDCompKey;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;
import com.centrica.vms.ws.sap.service.CreateVendResponse;
import com.centrica.vms.ws.service.VMSCommonPIService;

/**
 * Test class to test VMSCommonPIService
 * This unit test class mock all the dependent classes calls
 * and test the functionality of VMSCommonPIService class.
 * 
 * Methods Tested
 * 
 * prepareFaultResponse(VendOutcomeCode_type1 faultCode, String errorMessage, CreateVendResponse paymentResponse)
 * rollBackCreditID(String transactionType, String msn, Boolean status)
 * setTransactionDetails(TransactionType transactionType, String pan, String vendCode, String source, Long holdingPeriod, 
 * String originalTransactionID, String transactionID, String creditValue, String currencyType, Status status, String paymentKey, 
 * Date actualTimeStamp,Date vendcodeTimeStamp,String msn, String createdBy, String updatedBy)
 * getCreditValue(String creditValue)
 * getVendTxnStatus(Iterator<VendTxnStatus> itr)
 * setVendTxnStatus(VendTxnWSDetails vendTransactionDetails, WSTransactionDAO wsTransactionDAO, int status)
 * getHoldingPeriod(Long holdingPeriod, String source, WSTransactionDAO wstransactionDAO)
 * getCreditID(TransactionType transactionType, String msn, WSTransactionDAO wstransactionDAO)
 * getPaymentResponse(String vendCode, CreateVendResponse paymentResponse, Boolean status)
 */
public class VMSCommonPIServiceSmallTest {

	private static final String CLASS_NAME = "com.centrica.vms.ws.service.VMSCommonPIService";

	final WSTransactionDAO wsTransDao = EasyMock.createMock(WSTransactionDAO.class);

	private final VMSCommonPIService piService = new VMSCommonPIService(wsTransDao);

	/**
	 * Method to test Prepare Fault Response
	 * @throws Exception
	 */
	@Test
	public void prepareFaultResponse() {

		final CreateVendResponse paymentResponse = new CreateVendResponse();
		final CreateVendResponse response = piService.prepareFaultResponse(VendOutcomeCode_type1.value1, "ERROR", paymentResponse);

		assertEquals(VendOutcomeCode_type1.value1, response.getPaymentResponse().getVendOutcome().getVendOutcomeCode());
		assertNotNull(response.getPaymentResponse().getLog());

	}

	/**
	 * Method to test RollBack CreditID
	 * @throws Exception
	 */
	@Test
	public void rollBackCreditID() throws Exception {

		EasyMock.expect(wsTransDao.rollBackCreditValue((CreditIDCompKey) EasyMock.anyObject())).andReturn(1);
		EasyMock.replay(wsTransDao);

		piService.rollBackCreditID(TransactionType.ElectricPurchase.toString(), VMSTestDataProvider.MSN, true);

		EasyMock.verify(wsTransDao);

	}

	/**
	 * Method to test Set Transaction Details
	 * @throws Exception
	 */
	@Test
	public void setTransactionDetails() throws Exception {

		final VendTxnWSDetails vendTxnDetails = piService.setTransactionDetails(TransactionType.ElectricPurchase , VMSTestDataProvider.PAN, VMSTestDataProvider.MSN, 
				VMSTestDataProvider.SOURCE, new Long(0), VMSTestDataProvider.ORIG_TRANS_ID, VMSTestDataProvider.TRANSACTION_ID, VMSTestDataProvider.CREDIT_VALUE, 
				"GBP", Status.SC_100, VMSTestDataProvider.PP_KEY, new Date(),	new Date(), VMSTestDataProvider.MSN, VMSTestDataProvider.TEST, VMSTestDataProvider.TEST);

		assertEquals(VMSTestDataProvider.TEST, vendTxnDetails.getCreatedBy());
		assertEquals(VMSTestDataProvider.CREDIT_VALUE, vendTxnDetails.getCreditValue());
		assertEquals(VMSTestDataProvider.MSN, vendTxnDetails.getVendCode());
		assertEquals(VMSTestDataProvider.MSN, vendTxnDetails.getMsn());
		assertEquals(VMSTestDataProvider.ORIG_TRANS_ID, vendTxnDetails.getOriginalTransactionID());
		assertEquals(VMSTestDataProvider.PAN, vendTxnDetails.getPan());
		assertEquals(VMSTestDataProvider.PP_KEY, vendTxnDetails.getPpKey());
		assertEquals(VMSTestDataProvider.SOURCE, vendTxnDetails.getSource());
		assertEquals(VMSTestDataProvider.TRANSACTION_ID, vendTxnDetails.getTransactionID());

	}

	/**
	 * Method to test Get Credit Value
	 * @throws Exception
	 */
	@Test
	public void getCreditValue() throws Exception {

		final String creditValue = piService.getCreditValue("-" + VMSTestDataProvider.CREDIT_VALUE);
		assertEquals(VMSTestDataProvider.CREDIT_VALUE, creditValue);

	}

	/**
	 * Method to test Get VendTxn Status
	 * @throws Exception
	 */
	@Test
	public void getVendTxnStatus() throws Exception {

		final Class<?> piServiceClass = Class.forName(CLASS_NAME);
		final Method method = piServiceClass.getDeclaredMethod("getVendTxnStatus", Iterator.class);
		method.setAccessible(true);
		final Integer status = (Integer) method.invoke(piService, VMSTestDataProvider.constructVendTxnStatus(Status.SC_200.getStatus()).iterator());

		assertEquals(Status.SC_200.getStatus(), status);

	}

	/**
	 * Method to test Set VendTxn Status
	 */
	@Test
	public void setVendTxnStatus() throws Exception {

		final VendTxnWSDetails vendTransactionDetails = VMSTestDataProvider.constructVendTxnWSDetails(Status.SC_200.getStatus());

		EasyMock.expect(wsTransDao.update(vendTransactionDetails)).andReturn(true);
		EasyMock.replay(wsTransDao);

		final Class<?> piServiceClass = Class.forName(CLASS_NAME);
		final Method method = piServiceClass.getDeclaredMethod("setVendTxnStatus", VendTxnWSDetails.class, WSTransactionDAO.class, int.class);
		method.setAccessible(true);
		method.invoke(piService, vendTransactionDetails, wsTransDao, Status.SC_200.getStatus());

		EasyMock.verify(wsTransDao);

	}

	/**
	 * Method to test Get Holding Period
	 * @throws Exception
	 */
	@Test
	public void getHoldingPeriod() throws Exception {

		final SourceDetails source = VMSTestDataProvider.constructSourceDetails();

		EasyMock.expect(wsTransDao.getSourceDetails(VMSTestDataProvider.SOURCE)).andReturn(VMSTestDataProvider.constructSourceDetails());
		EasyMock.replay(wsTransDao);

		final Class<?> piServiceClass = Class.forName(CLASS_NAME);
		final Method method = piServiceClass.getDeclaredMethod("getHoldingPeriod", Long.class, String.class, WSTransactionDAO.class);
		method.setAccessible(true);

		final Long holdingPeriod = (Long) method.invoke(piService, new Long(-1), VMSTestDataProvider.SOURCE, wsTransDao);
		assertEquals(source.getHoldingPeriod(), holdingPeriod);

		EasyMock.verify(wsTransDao);

	}

	/**
	 * Method to test Get Holding Period
	 * @throws Exception
	 */
	@Test
	public void getCreditID() throws Exception {

		EasyMock.expect(wsTransDao.getCreditIDDetails((CreditIDCompKey) EasyMock.anyObject())).andReturn(1);
		EasyMock.replay(wsTransDao);

		final Class<?> piServiceClass = Class.forName(CLASS_NAME);
		final Method method = piServiceClass.getDeclaredMethod("getCreditID", TransactionType.class, String.class, WSTransactionDAO.class);
		method.setAccessible(true);

		final Integer creditId = (Integer) method.invoke(piService, TransactionType.ElectricPurchase, VMSTestDataProvider.MSN, wsTransDao);
		assertEquals(1, creditId);

		EasyMock.verify(wsTransDao);

	}

	/**
	 * Method to test Get Payment Response
	 * @throws Exception
	 */
	@Test
	public void getPaymentResponse() throws Exception {

		final Class<?> piServiceClass = Class.forName(CLASS_NAME);
		final Method method = piServiceClass.getDeclaredMethod("getPaymentResponse", String.class, CreateVendResponse.class, Boolean.class);
		method.setAccessible(true);

		final CreateVendResponse vendResponseMessage = (CreateVendResponse) method.invoke(piService, VMSTestDataProvider.VEND_CODE, 
				VMSTestDataProvider.constructCreateVendResponse(), true);

		assertEquals(VMSTestDataProvider.VEND_CODE, vendResponseMessage.getPaymentResponse().getVendCode().getVendCode());

	}

}
