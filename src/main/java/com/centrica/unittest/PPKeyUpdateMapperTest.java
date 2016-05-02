package com.centrica.unittest;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.centrica.unittest.service.VMSTestDataProvider;
import com.centrica.vms.model.PPKeyTransaction;
import com.centrica.vms.model.PPKeyTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.model.PPKeyTxnScheduler;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyRequestMessage;
import com.centrica.vms.ws.service.mapper.PPKeyUpdateMapper;


/**
 * Test class to test PPKeyUpdateMapper
 * This unit test class tests the functionality of PPKeyUpdateMapper class.
 * 
 * Methods Tested
 * 
 * setPPKTransStatus(final PPKeyTxnScheduler ppKeyTxnSdlr, final Status statusCode, final String triggerName, final String transactionId)
 * setPPKeyTransactionStatus(final Status statusCode, final Set<PPKeyTxnStatus> txnStatusDetails)
 * PPKeyTransaction mapPPKeyTransactionRequest(final UpdatePPKeyRequestMessage request, final Status statusCode)
 */
public class PPKeyUpdateMapperTest {

	private final PPKeyUpdateMapper mapper = new PPKeyUpdateMapper();


	/**
	 * Method to test setPPKTransStatus
	 * @throws Exception
	 */
	@Test
	public void setPPKTransStatus() throws Exception {

		final PPKeyTxnScheduler ppKeyTxnSdlr = new PPKeyTxnScheduler();
		ppKeyTxnSdlr.setStatusDetails(new HashSet<PPKeyTxnStatus>());

		mapper.setPPKTransStatus(ppKeyTxnSdlr, Status.SC_100, VMSTestDataProvider.TEST, VMSTestDataProvider.TRANSACTION_ID);

		assertEquals(Status.SC_100.getStatus(), ppKeyTxnSdlr.getStatusDetails().iterator().next().getStatus());
		assertEquals(VMSTestDataProvider.TEST, ppKeyTxnSdlr.getTriggerName());
		assertEquals(VMSTestDataProvider.TRANSACTION_ID, ppKeyTxnSdlr.getTransactionId());

	}

	/**
	 * Method to test setPPKeyTransactionStatus
	 * @throws Exception
	 */
	@Test
	public void setPPKeyTransactionStatus() throws Exception {

		final Set<PPKeyTxnStatus> txnStatusDetails = new HashSet<PPKeyTxnStatus>();
		mapper.setPPKeyTransactionStatus(Status.SC_100, txnStatusDetails);
		assertEquals(Status.SC_100.getStatus(), txnStatusDetails.iterator().next().getStatus());

	}

	/**
	 * Method to test mapPPKeyTransactionRequest
	 * @throws Exception
	 */
	@Test
	public void mapPPKeyTransactionRequest() throws Exception {

		final UpdatePPKeyRequestMessage request = new UpdatePPKeyRequestMessage();
		request.setMpxn(VMSTestDataProvider.MPXN);
		request.setMsn(VMSTestDataProvider.MSN);
		request.setPPKey(VMSTestDataProvider.PP_KEY);
		request.setPPKeyRequestDateTime(Calendar.getInstance());
		request.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		final PPKeyTransaction ppkTrans = mapper.mapPPKeyTransactionRequest(request, Status.SC_515);

		assertEquals(request.getMpxn(), ppkTrans.getMpxn());
		assertEquals(request.getMsn(), ppkTrans.getMsn());
		assertEquals(request.getPPKey(), ppkTrans.getPpKey());
		assertEquals(request.getPPKeyRequestDateTime().getTime(), ppkTrans.getRequestDate());
		assertEquals(request.getPPKeyRequestIdentifier(), ppkTrans.getTransactionId());

	}

}
