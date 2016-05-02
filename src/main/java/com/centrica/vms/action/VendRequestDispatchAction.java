/**
 * VendRequestDispatchAction.java
 * Purpose: Class to create vend request dispatch action
 *
 * @author ramasap1
 */
package com.centrica.vms.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.centrica.vms.delegate.VMSWebActionDelegate;
import com.centrica.vms.form.VendRequestDetailsForm;

/**
 * Methods to create vend request dispatch action
 * @see VMSDispatchAction
 */
public class VendRequestDispatchAction extends VMSDispatchAction {

	private Logger logger = ESAPI.getLogger(this.getClass());
	VMSWebActionDelegate vmswebActionDelegate = new VMSWebActionDelegate();
	/**
	 * Method to display the request page
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward requestPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the requestPage method ");
		String loggedUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedUser);
		ActionForward actionForward =  mapping.findForward("requestPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the requestPage method : "+loggedUser);
		return actionForward;
	}
	
	/**
	 * Method to view the confirm page
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the submit method ");
		String loggedUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedUser);
		VendRequestDetailsForm  vendRequestDetailsForm = (VendRequestDetailsForm)form;
		vendRequestDetailsForm.setDateTime(new Date());
		vendRequestDetailsForm.setCreditType("PURCHASE");
		ActionForward actionForward =  mapping.findForward("confirmPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the submit method : "+loggedUser);
		return actionForward;
	}
	
	
	/**
	 * Method to view the confirm page
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward confirm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the confirm method ");
		String loggedUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedUser);
		VendRequestDetailsForm  vendRequestDetailsForm = (VendRequestDetailsForm)form;
		VMSWebActionDelegate vmswebActionDelegate = new VMSWebActionDelegate();
		vendRequestDetailsForm = vmswebActionDelegate.sendVendRequest(vendRequestDetailsForm);
		ActionForward actionForward =  mapping.findForward("responsePage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the confirm method : "+loggedUser);
		return actionForward;
	}
	
	/**
	 * Method to perform the cancel operation
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the cancel method ");
		String loggedUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedUser);
		VendRequestDetailsForm  vendRequestDetailsForm = (VendRequestDetailsForm)form;
		vendRequestDetailsForm.clear();
		ActionForward actionForward =  mapping.findForward("requestPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the cancel method :"+loggedUser);
		return actionForward;
	} 
}
