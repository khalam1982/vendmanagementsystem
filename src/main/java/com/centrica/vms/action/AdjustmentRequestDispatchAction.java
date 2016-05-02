/**
 * AdjustmentRequestDispatchAction.java
 * Purpose: Class to create adjustment request dispatch action
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
import com.centrica.vms.form.AdjustmentRequestDetailsForm;


/**
 * Methods to create adjustment request dispatch action
 * @see VMSDispatchAction
 */
public class AdjustmentRequestDispatchAction extends VMSDispatchAction {

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
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.debug(Logger.EVENT_SUCCESS,"Entering the requestPage method:" + userName);
		ActionForward actionForward =  mapping.findForward("requestPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the searchPage method : " + userName);
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
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.debug(Logger.EVENT_SUCCESS,"Entering the submit method :"+userName);
		AdjustmentRequestDetailsForm  adjustmentHistoryDetailsForm = (AdjustmentRequestDetailsForm)form;
		adjustmentHistoryDetailsForm.setDateTime(new Date());
		adjustmentHistoryDetailsForm.setCreditType("ADJUSTMENT");
		ActionForward actionForward =  mapping.findForward("confirmPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the submit method");
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
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.debug(Logger.EVENT_SUCCESS,"Entering the confirm method "+userName);
		AdjustmentRequestDetailsForm  adjustmentHistoryDetailsForm = (AdjustmentRequestDetailsForm)form;
		VMSWebActionDelegate vmswebActionDelegate = new VMSWebActionDelegate();
		adjustmentHistoryDetailsForm = vmswebActionDelegate.sendAdjustmentRequest(adjustmentHistoryDetailsForm);
		ActionForward actionForward =  mapping.findForward("responsePage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the confirm method" + userName);
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
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.debug(Logger.EVENT_SUCCESS,"Entering the cancel method " +userName);
		AdjustmentRequestDetailsForm  adjustmentHistoryDetailsForm = (AdjustmentRequestDetailsForm)form;
		adjustmentHistoryDetailsForm.clear();
		ActionForward actionForward =  mapping.findForward("requestPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the cancel method" + userName);
		return actionForward;
	} 
}
