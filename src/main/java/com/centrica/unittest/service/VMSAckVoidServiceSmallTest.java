package com.centrica.unittest.service;

import static org.junit.Assert.assertFalse;

import java.lang.reflect.Method;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Test;

import com.centrica.vms.common.ManuTypeEnum;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.CustomerDetails;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;
import com.centrica.vms.ws.service.VMSAckVoidService;
import com.centrica.vms.ws.service.VMSLGGeneratePhase2B;

/**
 * Test class to test VMSAckVoidService
 * This unit test class mock all the dependent classes calls
 * and test the functionality of VMSAckVoidService class.
 * 
 * Methods Tested
 * 
 * processNegtiveAdjustment(VendTxnWSDetails vendTransactionDetails, String transactionID)
 * processGasNegtiveAdjustment(String creditValue, String paymentKeyHex,
			String transactionID, String transactionType, String pan, String msn, String source)
 */
public class VMSAckVoidServiceSmallTest {

	private static final String CLASS_NAME = "com.centrica.vms.ws.service.VMSAckVoidService";

	private final WSTransactionDAO wsTransDao = EasyMock.createMock(WSTransactionDAO.class);
	private final VMSLGGeneratePhase2B paymentServicePh2 = EasyMock.createMock(VMSLGGeneratePhase2B.class);
	private final VMSSchedulerServiceImpl vmsSchdulerService = EasyMock.createMock(VMSSchedulerServiceImpl.class);
	private final VMSAckVoidService vmsAckVoidService = new VMSAckVoidService(wsTransDao, paymentServicePh2, vmsSchdulerService);

	/**
	 * Method to test Process Negative Adjustment
	 * @throws Exception
	 */
	@Test
	public void processNegtiveAdjustment() throws Exception {

		final Class<?> vmsAckVoidSrvcClass = Class.forName(CLASS_NAME);
		final VendTxnWSDetails vendTxDetails = VMSTestDataProvider.constructVendTxnWSDetails(Status.SC_200.getStatus());
		vendTxDetails.setTransactionType(TransactionType.ElectricPurchase.getTransactionType());

		final MeterDetails meterDetails = VMSTestDataProvider.constructMeterDetails();
		meterDetails.setManuTypeID(ManuTypeEnum.LG.getManuType());

		EasyMock.expect(wsTransDao.getMeterDetails(VMSTestDataProvider.MSN)).andReturn(meterDetails);

		final CustomerDetails customer = VMSTestDataProvider.constructCustomerDetails();
		EasyMock.expect(wsTransDao.getCustomerDetails(VMSTestDataProvider.PAN)).andReturn(customer);

		EasyMock.expect(wsTransDao.getVendTxnWSDetails(VMSTestDataProvider.TRANSACTION_ID)).andReturn(vendTxDetails);

		EasyMock.expect(wsTransDao.update(vendTxDetails)).andReturn(true);

		EasyMock.expect(vmsSchdulerService.scheduleAdjustJob(EasyMock.anyInt(), EasyMock.anyString(), (Long)EasyMock.anyObject(), 
				EasyMock.anyString(), EasyMock.anyString(), (Date) EasyMock.anyObject(), EasyMock.anyBoolean())).andReturn(true);

		EasyMock.replay(wsTransDao);
		EasyMock.replay(vmsSchdulerService);

		final Method method = vmsAckVoidSrvcClass.getDeclaredMethod("processNegtiveAdjustment", VendTxnWSDetails.class, String.class);
		method.setAccessible(true);
		final Boolean status = (Boolean) method.invoke(vmsAckVoidService, vendTxDetails, VMSTestDataProvider.TRANSACTION_ID);

		EasyMock.verify(wsTransDao);
		EasyMock.verify(vmsSchdulerService);

		assertFalse(status);

	}

	/**
	 * Method to test Process Gas Negative Adjustment
	 * @throws Exception
	 */
	@Test
	public void processGasNegtiveAdjustment() throws Exception {

		final VendTxnWSDetails vendTxDetails = VMSTestDataProvider.constructVendTxnWSDetails(Status.SC_200.getStatus());
		vendTxDetails.setTransactionType(TransactionType.ElectricPurchase.getTransactionType());

		final MeterDetails meterDetails = VMSTestDataProvider.constructMeterDetails();
		meterDetails.setManuTypeID(ManuTypeEnum.LG.getManuType());

		EasyMock.expect(wsTransDao.getMeterDetails(VMSTestDataProvider.MSN)).andReturn(meterDetails);

		final CustomerDetails customer = VMSTestDataProvider.constructCustomerDetails();
		EasyMock.expect(wsTransDao.getCustomerDetails(VMSTestDataProvider.PAN)).andReturn(customer);

		EasyMock.expect(wsTransDao.getVendTxnWSDetails(VMSTestDataProvider.TRANSACTION_ID)).andReturn(vendTxDetails);

		EasyMock.expect(wsTransDao.update(vendTxDetails)).andReturn(true);

		EasyMock.expect(vmsSchdulerService.scheduleAdjustJob(EasyMock.anyInt(), EasyMock.anyString(), (Long)EasyMock.anyObject(), 
				EasyMock.anyString(), EasyMock.anyString(), (Date) EasyMock.anyObject(), EasyMock.anyBoolean())).andReturn(true);

		EasyMock.replay(wsTransDao);
		EasyMock.replay(vmsSchdulerService);

		final Class<?> vmsAckVoidSrvcClass = Class.forName(CLASS_NAME);
		final Method method = vmsAckVoidSrvcClass.getDeclaredMethod("processGasNegAdjust", String.class, String.class, String.class, String.class, 
				String.class, String.class, String.class);
		method.setAccessible(true);
		final Boolean status = (Boolean) method.invoke(vmsAckVoidService, VMSTestDataProvider.CREDIT_VALUE, VMSTestDataProvider.PP_KEY,
				VMSTestDataProvider.TRANSACTION_ID, TransactionType.GasPurchase.getTransactionType(), VMSTestDataProvider.PAN, 
				VMSTestDataProvider.MSN, VMSTestDataProvider.SOURCE);

		EasyMock.verify(wsTransDao);
		EasyMock.verify(vmsSchdulerService);

		assertFalse(status);

	}

}
