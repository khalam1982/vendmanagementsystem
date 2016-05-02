package com.centrica.unittest.service;

import java.util.ArrayList;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Test;

import smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn;

import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.vh.ws.sap.service.GetVendHistory;
import com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse;
import com.centrica.vms.ws.service.VMSVHService;

/**
 * Test class to test VMSVHService
 * This unit test class mock all the dependent classes calls
 * and test the functionality of VMSVHService class.
 * 
 * Methods Tested
 * 
 * getVendHistory(GetVendHistory vendHistoryRequest)
 */
public class VMSVHServiceSmallTest {

	/**
	 * Method used to test get vend history
	 * @throws Exception
	 */
	@Test
	public void getVendHistory() throws Exception {

		final VMSVHService vmsVHService = new VMSVHService();

		final GetVendHistory getVendHistory = VMSTestDataProvider.constructVendHistoryRequestMessage();

		final ArrayList<VendHistoryDetails> vendHistoryList = new ArrayList<VendHistoryDetails>();
		final VendHistoryDetails historyDetails = VMSTestDataProvider.constructVendHistoryDetails();
		vendHistoryList.add(historyDetails);

		final VMSTransactionDAO transDao = EasyMock.createMock(VMSTransactionDAO.class);
		vmsVHService.setVMSTransactionDAO(transDao);

		EasyMock.expect(transDao.getVendHistory_Purchase(VMSTestDataProvider.PAN, VMSTestDataProvider.constructTransactionList(), 1)).andReturn(vendHistoryList);
		EasyMock.expect(transDao.getVendHistory_Adjustment(VMSTestDataProvider.PAN, VMSTestDataProvider.constructTransactionTypeList(), 1)).andReturn(vendHistoryList);
		EasyMock.expect(transDao.getVendHistory_Void(VMSTestDataProvider.TRANSACTION_ID, VMSTestDataProvider.constructTransactionsList())).andReturn(vendHistoryList);
		EasyMock.replay(transDao);

		final GetVendHistoryResponse vendHistoryResponse = vmsVHService.getVendHistory(getVendHistory);
		EasyMock.verify(transDao);

		final Purchase_Txn[] purchase_Txn =  vendHistoryResponse.getVendHistoryResponse().getCreditPurchase();
		for( int index = 0; index < purchase_Txn.length; index++ ) {
			Assert.assertEquals(historyDetails.getTransactionID(), purchase_Txn[index].getTxnDetails().getTransactionID().getStringType());
			Assert.assertEquals(historyDetails.getTransactionType(), purchase_Txn[index].getTxnDetails().getTransactionType().getStringType());
		}

	}

}
