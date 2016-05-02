/**
 * HEPhase3UtilityDispatchAction.java
 * Purpose: Class to create HEAD END VMS utility dispatch action
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

import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.delegate.VMSWebActionDelegate;
import com.centrica.vms.form.HEUtilityRequestForm;
import com.centrica.vms.form.UtilityRequestForm;

/**
 * Methods to create HEAD END VMS utility dispatch action
 * @see VMSUtilityDispatchAction
 */
public class HEPhase3UtilityDispatchAction extends VMSUtilityDispatchAction {
	
	private Logger logger = ESAPI.getLogger(this.getClass());
	VMSWebActionDelegate vmsWebActionDelegate = new VMSWebActionDelegate();
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
		ActionForward actionForward = null;
		actionForward =  heDetailRequest(mapping,form,userName);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the requestPage method : " + userName);
		return actionForward;
	} 

	/**
	 * Method to forward the request to the headend utility detail page with the count
	 * @param ActionMapping
	 * @param ActionForm
	 * @param String
	 * @return ActionForward
	 */
	private ActionForward heDetailRequest(ActionMapping mapping, ActionForm form,String userName){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the heDetailRequest method: ");
		UtilityRequestForm utilityRequestForm = (UtilityRequestForm)form;
		Integer count = vmsWebActionDelegate.getResendTransactionCount(userName, 1);
		utilityRequestForm.setCount(count.toString());
		ActionForward actionForward =  mapping.findForward("hePhase3Request");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the heDetailRequest method : ");
		return actionForward;
	}
	
	/**
	 * Method to forward the request to the confirmation page with the messages.
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward heConfirmPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.debug(Logger.EVENT_SUCCESS,"Entering the heConfirmPage method: "+userName);
		HEUtilityRequestForm heUtilityRequestForm = (HEUtilityRequestForm)form;
		Integer processStatus = vmsWebActionDelegate.scheduleHEUtility(heUtilityRequestForm,
				DeviceTypeEnum.PH3.getDeviceType());
		heUtilityRequestForm.setProcessStatus(processStatus.toString());
		ActionForward actionForward =  mapping.findForward("heConfirmPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the heConfirmPage method : " + userName);
		return actionForward;
	}
}
