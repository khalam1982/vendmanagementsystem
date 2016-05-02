package com.centrica.unittest;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.centrica.unittest.service.VMSTestDataProvider;
import com.centrica.vms.exception.MSNNotFoundException;
import com.centrica.vms.exception.VMSInvalidDateException;
import com.centrica.vms.exception.VMSInvalidPPKeyException;
import com.centrica.vms.exception.VMSInvalidTransactionIdException;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyRequestMessage;
import com.centrica.vms.ws.service.validator.PPKeyServiceValidator;

/**
 * Test class to test PPKeyServiceValidator
 * This unit test class tests the functionality of PPKeyServiceValidator class.
 * 
 * Methods Tested
 * 
 * validateUpdatePPKeyRequest(final UpdatePPKeyRequestMessage request)
 */
public class PPKeyServiceValidatorTest {

	private final PPKeyServiceValidator validator = new PPKeyServiceValidator();

	/**
	 * Method to test Validate Update PP Key Request
	 * Input - Null Transaction Id
	 * @throws Exception
	 */
	@Test(expected = VMSInvalidTransactionIdException.class)
	public void validateUpdatePPKeyRequestEmptyTransactionId() throws Exception {

		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();
		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(null);
		validator.validateUpdatePPKeyRequest(requestMsg);

	}

	/**
	 * Method to test Validate Update PP Key Request
	 * Input - Invalid Transaction Id
	 * @throws Exception
	 */
	@Test(expected = VMSInvalidTransactionIdException.class)
	public void validateUpdatePPKeyRequestInvalidTransactionId() throws Exception {

		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();
		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier("1234567890123455676899605694685968");
		validator.validateUpdatePPKeyRequest(requestMsg);

	}

	/**
	 * Method to test Validate Update PP Key Request
	 * Input - Null MSN
	 * @throws Exception
	 */
	@Test(expected = MSNNotFoundException.class)
	public void validateUpdatePPKeyRequestEmptyMSN() throws Exception {

		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();
		requestMsg.setMsn(null);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		validator.validateUpdatePPKeyRequest(requestMsg);

	}

	/**
	 * Method to test Validate Update PP Key Request
	 * Input - Invalid MSN
	 * @throws Exception
	 */
	@Test
	public void validateUpdatePPKeyRequestInvalidMSN() throws Exception {

		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();
		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		validator.validateUpdatePPKeyRequest(requestMsg);

	}

	/**
	 * Method to test Validate Update PP Key Request
	 * Input - Invalid MPXN
	 * @throws Exception
	 */
	@Test
	public void validateUpdatePPKeyRequestInvalidMPXN() throws Exception {

		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();
		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		validator.validateUpdatePPKeyRequest(requestMsg);

	}

	/**
	 * Method to test Validate Update PP Key Request
	 * Input - Null PP Key
	 * @throws Exception
	 */
	@Test(expected = VMSInvalidPPKeyException.class)
	public void validateUpdatePPKeyRequestEmptyPPKey() throws Exception {

		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();
		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(null);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		validator.validateUpdatePPKeyRequest(requestMsg);

	}

	/**
	 * Method to test Validate Update PP Key Request
	 * Input - Invalid PP Key
	 * @throws Exception
	 */
	@Test(expected = VMSInvalidPPKeyException.class)
	public void validateUpdatePPKeyRequestInvalidPPKey() throws Exception {

		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();
		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(null);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		validator.validateUpdatePPKeyRequest(requestMsg);

	}

	/**
	 * Method to test Validate Update PP Key Request
	 * Input - Null Request Date
	 * @throws Exception
	 */
	@Test(expected = VMSInvalidDateException.class)
	public void validateUpdatePPKeyRequestEmptyRequestDate() throws Exception {

		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();
		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(null);
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		validator.validateUpdatePPKeyRequest(requestMsg);

	}

	/**
	 * Method to test Validate Update PP Key Request
	 * Input - Future Request Date
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test(expected = VMSInvalidDateException.class)
	public void validateUpdatePPKeyRequestInvalidRequestDate() throws Exception {

		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();
		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		final Calendar cal = Calendar.getInstance();
		final Date date = cal.getTime();
		date.setDate(date.getDate() + 1);
		cal.setTime(date);
		requestMsg.setPPKeyRequestDateTime(cal);
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		validator.validateUpdatePPKeyRequest(requestMsg);

	}

	/**
	 * Method to test Validate Update PP Key Request
	 * Positive Scenario
	 * @throws Exception
	 */
	@Test
	public void validateUpdatePPKeyRequest() throws Exception {

		final UpdatePPKeyRequestMessage requestMsg = new UpdatePPKeyRequestMessage();
		requestMsg.setMsn(VMSTestDataProvider.MSN);
		requestMsg.setPPKey(VMSTestDataProvider.PP_KEY);
		requestMsg.setPPKeyRequestDateTime(Calendar.getInstance());
		requestMsg.setPPKeyRequestIdentifier(VMSTestDataProvider.TRANSACTION_ID);
		validator.validateUpdatePPKeyRequest(requestMsg);

	}

}
