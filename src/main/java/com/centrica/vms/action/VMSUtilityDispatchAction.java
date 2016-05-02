/**
 * VMSUtilityDispatchAction.java
 * Purpose: Class to create VMS utility dispatch action
 *
 * @author ramasap1
 */
package com.centrica.vms.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.centrica.vms.delegate.VMSWebActionDelegate;
import com.centrica.vms.form.UtilityRequestForm;
/**
 * Methods to create VMS utility dispatch action
 * @see VMSDispatchAction
 */
public class VMSUtilityDispatchAction extends VMSDispatchAction {
	
	private Logger logger = ESAPI.getLogger(this.getClass());
	VMSWebActionDelegate vmsWebActionDelegate = new VMSWebActionDelegate();
	private static String HEREQUEST_PH2 = "01";
	private static String HEREQUEST_PH3 = "02";
	private static String HEREQUEST_CIM = "03";
	
	/**
	 * Method to access the utility request type page
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward requestTypePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.debug(Logger.EVENT_SUCCESS,"Entering the requestTypePage method:" + userName);
		UtilityRequestForm utilityRequestForm = (UtilityRequestForm)form;
		utilityRequestForm.setUserName(userName);
		ActionForward actionForward =  mapping.findForward("requestType");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the requestTypePage method : " + userName);
		return actionForward;
	}	
	
	/**
	 * Method to forward the request to the relevant utility page
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
		logger.debug(Logger.EVENT_SUCCESS,"Entering the requestPage method: "+userName);
		UtilityRequestForm utilityRequestForm = (UtilityRequestForm)form;
		String requestType = utilityRequestForm.getRequestType();
		ActionForward actionForward = null;
		if(requestType.equals(HEREQUEST_PH2)){
			actionForward =   mapping.findForward("hePhase2Request");
		} else if (requestType.equals(HEREQUEST_PH3)) {
			actionForward =   mapping.findForward("hePhase3Request");
		} else if (requestType.equals(HEREQUEST_CIM)) {
			actionForward =   mapping.findForward("heCIMRequest");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the requestPage method : " + userName);
		return actionForward;
	}
}
