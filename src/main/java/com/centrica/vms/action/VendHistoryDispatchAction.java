/**
 * VendHistoryDispatchAction.java
 * Purpose: Class to create vend history dispatch action
 *
 * @author nagarajk
 */
package com.centrica.vms.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.centrica.vms.delegate.VMSWebActionDelegate;
import com.centrica.vms.form.VendHistoryDetailsForm;
import com.centrica.vms.model.VendHistoryDetails;

/**
 * Methods to create vend history dispatch action
 * @see VMSDispatchAction
 */
public class VendHistoryDispatchAction extends VMSDispatchAction {

	private Logger logger = ESAPI.getLogger(this.getClass());
	VMSWebActionDelegate vmswebActionDelegate = new VMSWebActionDelegate();
	/**
	 * Method to display the search page
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward searchPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the searchPage method ");
		String loggedUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedUser);
		ActionForward actionForward =  mapping.findForward("searchPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the searchPage method:"+loggedUser);
		return actionForward;
	}
	
	/**
	 * Method to retrieve the vend history for the pan
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward detailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the detailPage method ");
		String loggedUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedUser);
		VendHistoryDetailsForm  vendHistoryDetailsForm = (VendHistoryDetailsForm)form;
		ArrayList<VendHistoryDetails> vendHistoryList = vmswebActionDelegate.getVendHistory(vendHistoryDetailsForm.getPan());
		logger.info(Logger.EVENT_UNSPECIFIED,"vendHistoryList size is " + vendHistoryList.size());
		if(vendHistoryList.size()==0){
			ActionMessages messages = new ActionMessages(); 
			messages.add("app.message", new ActionMessage("app.vndhis.message",true));
			saveMessages(request, messages);
		}
		vendHistoryDetailsForm.setVendHistoryDetails(vendHistoryList);
		ActionForward actionForward =  mapping.findForward("detailPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the detailPage method:"+loggedUser);
		return actionForward;
	}
}
