package com.centrica.unittest.service;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.context.MessageContext;
import org.easymock.EasyMock;
import org.junit.Test;

import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.headend.auth.AuthenticationService;

/**
 * Test class to test AuthenticationService
 * This unit test class mock all the dependent classes calls
 * and test the functionality of AuthenticationService class.
 * 
 * Methods Tested
 * 
 * authenticateRequest(MessageContext messageContext)
 * validateUser(String userName,String password)
 */
public class AuthenticationServiceSmallTest {

	private static final String PASSWORD = "password";
	private static final String USER_NAME = "userName";
	private static final String LOGIN = "login";

	private final WSTransactionDAO wsTransDao = EasyMock.createMock(WSTransactionDAO.class);

	private final AuthenticationService authService = new AuthenticationService(wsTransDao);

	/**
	 * Method to test Authenticate Request
	 * @throws Exception
	 */
	@Test
	public void authenticateRequest() throws Exception {

		final MessageContext msgContext = constructMessageContext();

		EasyMock.expect(wsTransDao.getUserStatus(VMSTestDataProvider.TEST, VMSTestDataProvider.TEST)).andReturn(true);
		EasyMock.replay(wsTransDao);

		authService.authenticateRequest(msgContext);

		EasyMock.verify(wsTransDao);

	}

	/**
	 * Method to test Validate User
	 * @throws Exception
	 */
	public void validateUser() throws Exception {

		EasyMock.expect(wsTransDao.getUserStatus(VMSTestDataProvider.TEST, VMSTestDataProvider.TEST)).andReturn(true);
		EasyMock.replay(wsTransDao);
		authService.validateUser(VMSTestDataProvider.TEST, VMSTestDataProvider.TEST);
		EasyMock.verify(wsTransDao);


	}

	/**
	 * Constructs Message Context
	 * 
	 * @return MessageContext
	 * @throws Exception
	 */
	private MessageContext constructMessageContext() throws Exception {

		final MessageContext msgContext = new MessageContext();
		final SOAPEnvelope envelope = OMAbstractFactory.getSOAP11Factory().getDefaultEnvelope();
		final OMFactory fac = OMAbstractFactory.getOMFactory();
		final OMElement header =  fac.createOMElement(LOGIN, null);
		final OMElement userDetails = fac.createOMElement("userdetails", null);
		final OMElement userName = fac.createOMElement(USER_NAME, null);
		userName.addChild(fac.createOMText(userName, VMSTestDataProvider.TEST));
		final OMElement password = fac.createOMElement(PASSWORD, null);
		password.addChild(fac.createOMText(password, VMSTestDataProvider.TEST));
		userDetails.addChild(userName);
		userDetails.addChild(password);
		header.addChild(userDetails);
		envelope.getHeader().addChild(header);
		msgContext.setEnvelope(envelope);
		return msgContext;

	}

}
