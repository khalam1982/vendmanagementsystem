/**
 * VMSLoginAction.java
 * Purpose: Class to create VMS login action
 *
 * @author ramasap1
 */
package com.centrica.vms.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.delegate.VMSWebActionDelegate;
import com.centrica.vms.exception.UsrLockedException;
import com.centrica.vms.exception.UsrPwdExpiredException;
import com.centrica.vms.form.VMSLoginForm;
import com.centrica.vms.model.FunctionDetails;
import com.centrica.vms.model.UserDetails;
import com.centrica.vms.model.UserFunctionDetails;

/**
 * Methods to create VMS login action
 * @see VMSDispatchAction
 */
public class VMSLoginAction extends VMSDispatchAction {
	
	private Logger logger = ESAPI.getLogger(this.getClass());
	
	/**
	 * Method to display the login page
	 * @param ActionMapping
	 * @param ActionForm
	 * @param ActionForm
	 * @param HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		logger.debug(Logger.EVENT_SUCCESS,"Entering login method");
		ActionForward forward = mapping.findForward("loginPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving login method");
		return forward;
		
	}
	
	/**
	 * Method to login to the system.
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward loginSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		logger.debug(Logger.EVENT_SUCCESS,"Entering loginSubmit method");
		VMSLoginForm  vmsLoginForm = (VMSLoginForm)form;
		ActionForward forward = null;
		String errorMessage;
		try{
			UserDetails userDetails = new VMSWebActionDelegate().getUserDetails(vmsLoginForm.getUserName(), vmsLoginForm.getPassword());
			if(userDetails!= null){
				prepareSessionDetails(request, userDetails);
				userDetails = null;
				forward = mapping.findForward("mainPage");
			}else{
				errorMessage = "error.userdetails.wrong";
				logger.info(Logger.EVENT_UNSPECIFIED,"Credentials are wrong");
				forward = setLoginError(mapping, request,errorMessage);
			}
		}catch(UsrLockedException ex){
			errorMessage = "error.usraccountlck.wrong";
			forward = setLoginError(mapping, request,errorMessage);
		}catch(UsrPwdExpiredException ex){
			errorMessage = "error.usrpwdexpired.wrong";
			forward = setLoginError(mapping, request,errorMessage);
			
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving loginSubmit method");
		return forward;
	}

	/**
	 * Method to set the login error
	 * @param ActionMapping
	 * @param HttpServletRequest
	 * @param String - errorMessage
	 * @return ActionForward
	 */
	private ActionForward setLoginError(ActionMapping mapping,HttpServletRequest request,String errorMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setLoginError method");
		ActionForward forward;
		forward = mapping.findForward("loginPage");
		ActionMessages errors = new ActionMessages();
		errors.add("loginerror", new ActionMessage(errorMessage));
		saveMessages(request, errors);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setLoginError method");
		return forward;
	}
	
	/**
	 * Method to prepate the session details
	 * @param HttpServletRequest
	 * @param UserDetails
	 * @return
	 */
	private void prepareSessionDetails(HttpServletRequest request,
			UserDetails userDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareSessionDetails method");
		HttpSession session = request.getSession(true);
		session.setAttribute("LANID", userDetails.getLanID());
		session.setAttribute("GROUPID", userDetails.getGroupID());
		session.setAttribute("USERNAME",userDetails.getUserName());
		session.setAttribute("REMINDER",userDetails.getPassDueDate());
		
		Iterator<UserFunctionDetails> userFunctionDetails = userDetails.getUserFunctionDetails().iterator();
		ArrayList<FunctionDetails> functionDetails = new ArrayList<FunctionDetails>();
		while(userFunctionDetails.hasNext()){
			functionDetails.add(userFunctionDetails.next().getFunctionDetails());
		}
		session.setAttribute("USERFUNCTIONS", functionDetails);
		Properties properties = new VMSUtils().loadProperties();
		session.setMaxInactiveInterval(new Integer(properties.getProperty("SESSION_TIMEOUT")));
		session.setAttribute("RELEASE_VERSION",properties.getProperty("RELEASE_VERSION"));
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareSessionDetails method");
	}
	
	/**
	 * Method to logout of the system
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward logOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		logger.debug(Logger.EVENT_SUCCESS,"Entering logOut method");
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		ActionForward forward = mapping.findForward("logoffPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving logOut method");
		return forward;
		
	}

}
