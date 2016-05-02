/**
 * PremiseDispatchAction.java
 * Purpose: Class to create Premise dispatch action
 *
 * @author nagarajk
 */
package com.centrica.vms.action;

import java.text.ParseException;
import java.util.Calendar;

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
import com.centrica.vms.form.PremiseDetailsForm;
import com.centrica.vms.ws.model.PremiseDetails;

/**
 * Methods to create Premise dispatch action
 * @see VMSDispatchAction
 */
public class PremiseDispatchAction extends VMSDispatchAction {

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
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		((PremiseDetailsForm)form).setMpxn("");
		ActionForward actionForward =  mapping.findForward("searchPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the searchPage method : "+userName);
		return actionForward;
	}
	
	/**
	 * Method to retrieve the meter details
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
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		PremiseDetailsForm  premiseDetailsForm = (PremiseDetailsForm)form;
		PremiseDetails premiseDetails = vmswebActionDelegate.getPremiseDetails(premiseDetailsForm.getMpxn());
		if(premiseDetails!=null){
			premiseDetailsForm.setMsn(premiseDetails.getMSN());
			premiseDetailsForm.setOperationType("UPDATE");
		}else{
			premiseDetailsForm.setMsn("");
			premiseDetailsForm.setOperationType("SAVE");
		}
		ActionForward actionForward =  mapping.findForward("detailPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the detailPage method :"+userName);
		return actionForward;
	}
	
	/**
	 * Method to save the meter details
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the save method ");
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		Boolean status = false;
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		try{
			PremiseDetails premiseDetails = getPremiseDetails(form, userName);
			//MeterDetailsForm meterDetailsForm = (MeterDetailsForm)form;
			status = vmswebActionDelegate.savePremiseDetails(premiseDetails);
		}catch(ParseException ex){
			logger.error(Logger.EVENT_FAILURE,"ParseException is thrown" + ex.getMessage());
			throw ex;
		}
		populateMessage(request,status);
		ActionForward actionForward =  mapping.findForward("confirmPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the save method : "+userName);
		return actionForward;
	}

	/**
	 * Method to update the meter details
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the update method ");
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		Boolean status = false;
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		try{
			PremiseDetails premiseDetails = getPremiseDetails(form, userName);
			status = vmswebActionDelegate.updatePremiseDetails(premiseDetails);
		}catch(ParseException ex){
			logger.error(Logger.EVENT_FAILURE,"ParseException is thrown" + ex.getMessage());
			throw ex;
		}
		populateMessage(request,status);
		ActionForward actionForward =  mapping.findForward("confirmPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the update method :" +userName);
		return actionForward;
	}
	
	/**
	 * Method to handle the cancel request
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
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		((PremiseDetailsForm)form).setMpxn("");
		ActionForward actionForward =  mapping.findForward("searchPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the cancel method: "+userName);
		return actionForward;
	}

	/**
	 * Method to prepare the customer details from action form
	 * @param ActionForm
	 * @return CustomerDetails
	 */
	private PremiseDetails getPremiseDetails(ActionForm form, String username) throws ParseException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering getCustomerDetails method");
		PremiseDetailsForm  premiseDetailsForm = (PremiseDetailsForm)form;
		PremiseDetails premiseDetails = new PremiseDetails();
		premiseDetails.setMpxn(premiseDetailsForm.getMpxn());
		premiseDetails.setMSN(premiseDetailsForm.getMsn());
		premiseDetails.setUpdatedBy(username);
		premiseDetails.setUpdatedTimestamp(Calendar.getInstance().getTime());
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getCustomerDetails method");
		return premiseDetails;
	}

	/**
	 * Method used to populate the relevant message
	 * @param HttpServletRequest
	 * @param Boolean
	 */
	private void populateMessage(HttpServletRequest request,Boolean status) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering populateMessage method");
		logger.info(Logger.EVENT_UNSPECIFIED,"status " + status);
		ActionMessages messages = new ActionMessages(); 
		if(status){
			messages.add("app.message", new ActionMessage("app.usroperationsuccess",true));
		}else{
			messages.add("app.message", new ActionMessage("app.usroperationfailed",true));
		}
		saveMessages(request, messages);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving populateMessage method");
	}
}
