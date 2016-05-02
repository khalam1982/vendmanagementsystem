/**
 * ChangePwdDispatchAction.java
 * Purpose: Class to create change password dispatch action
 *
 * @author ramasap1
 */
package com.centrica.vms.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.centrica.vms.delegate.VMSWebActionDelegate;
import com.centrica.vms.form.ChangePasswordForm;
import com.centrica.vms.model.UserCredentials;
/**
 * Methods to create change password dispatch action
 * @see VMSDispatchAction
 */
public class ChangePwdDispatchAction extends VMSDispatchAction {

	private Logger logger = ESAPI.getLogger(getClass());
	
	/**
	 * Method used to forward the request to the password page.
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward passwordPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the passwordPage method ");
		ChangePasswordForm changePasswordForm = (ChangePasswordForm)form;
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		ActionForward actionForward =  mapping.findForward("passwordPage");
		changePasswordForm.setUserName(userName);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the passwordPage method");
		return actionForward;
	}
	
	/**
	 * Method used to update the request
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.debug(Logger.EVENT_SUCCESS,"Entering the update method ");
		ChangePasswordForm changePasswordForm = (ChangePasswordForm)form;
		String userName = changePasswordForm.getUserName();
		String oldPassword = changePasswordForm.getOldPassword();
		ActionForward actionForward;
		VMSWebActionDelegate vmsWebActionDelegate = new VMSWebActionDelegate();
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		UserCredentials userCredentials = vmsWebActionDelegate.getUserCredentials(userName);
		String currentPassword = userCredentials.getPassword();
		if(oldPassword.equals(currentPassword)){
			setUserCredentialDetails(changePasswordForm, userCredentials);
			Boolean status = vmsWebActionDelegate.updateUserCredentials(userCredentials);
			populateMessage(request, status);
			actionForward =  mapping.findForward("confirmPage");
		}else{
			saveActionErrors(request);
			actionForward =  mapping.findForward("passwordPage");
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the update method : "+userName);
		return actionForward;
	}

	/**
	 * Method to set the user credential details
	 * @param ChangePasswordForm
	 * @param UserCredentials
	 * @return 
	 */
	private void setUserCredentialDetails(ChangePasswordForm changePasswordForm,
			UserCredentials userCredentials) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setUserCredentialDetails method ");
		userCredentials.setPassword(changePasswordForm.getNewPassword());
		userCredentials.setPasswordDate(new Date());
		userCredentials.setUpdatedTimestamp(new Date());
		userCredentials.setUpdatedBy(changePasswordForm.getUserName());
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setUserCredentialDetails method ");
	}

	/**
	 * Method to save the action errors
	 * @param HttpServletRequest
	 * @return 
	 */
	private void saveActionErrors(HttpServletRequest request) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering saveActionErrors method ");
		ActionErrors errors = new ActionErrors();
		errors.add("app.password",new ActionMessage("app.oldpassword.incorrect"));
		ActionMessages messages = new ActionMessages(); 
		messages.add("app.password", new ActionMessage("app.oldpassword.incorrect",true));
		saveErrors(request, messages);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving saveActionErrors method ");
	}
	
	/**
	 * Method used to populate the relevant message
	 * @param HttpServletRequest
	 * @param Boolean
	 * @return
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
