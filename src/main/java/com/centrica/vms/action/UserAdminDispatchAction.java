/**
 * UserAdminDispatchAction.java
 * Purpose: Class to create user admin dispatch action
 *
 * @author ramasap1
 */
package com.centrica.vms.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.delegate.VMSWebActionDelegate;
import com.centrica.vms.exception.UserOperationException;
import com.centrica.vms.exception.UsrAlreadyExistException;
import com.centrica.vms.exception.UsrisaPowerUsrException;
import com.centrica.vms.form.UserAdminForm;
import com.centrica.vms.model.FunctionDetails;
import com.centrica.vms.model.UserCredentials;
import com.centrica.vms.model.UserDetails;
import com.centrica.vms.model.UserFunctionDetails;
import com.centrica.vms.model.UserDetails.Indicator;
/**
 * Methods to create user admin dispatch action
 * @see VMSDispatchAction
 */
public class UserAdminDispatchAction extends VMSDispatchAction {
	
	private Logger logger = ESAPI.getLogger(this.getClass());
	VMSWebActionDelegate  vmsWebActionDelegate = new VMSWebActionDelegate();
	
	/**
	 * Method used to forward the request to the groupPage
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
		logger.debug(Logger.EVENT_SUCCESS,"Entering the groupPage method ");
		String loggedUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedUser);
		UserAdminForm userAdminForm = (UserAdminForm)form;
		String groupID = (String)request.getSession(false).getAttribute("GROUPID");
		userAdminForm.setVmsGroup(groupID);
		ActionForward actionForward =  mapping.findForward("searchPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the groupPage method: "+loggedUser);
		return actionForward;
	}
	
	
	/**
	 * Method used to get the power user of that group
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward userDetailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the detailPage method ");
		String loggedUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedUser);
		UserAdminForm userAdminForm = (UserAdminForm)form;
		ActionForward actionForward =  mapping.findForward("detailPage");
		String userName = userAdminForm.getUserName();
		String mainUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"User Name " + userName);
		logger.info(Logger.EVENT_UNSPECIFIED,"Group ID " + userAdminForm.getVmsGroup());
		 try{
			 if(userName.equals(mainUser)){
				throw new UsrAlreadyExistException();
			 }
			 UserDetails userDetails = vmsWebActionDelegate.getUser(userName,userAdminForm.getVmsGroup());
			 populateUserAdminForm(request, userAdminForm, userDetails);
		 }catch(UsrAlreadyExistException ex){
			 logger.error(Logger.EVENT_FAILURE," UsrAlreadyExistException is thrown ");
			 String errorMessage = "error.usrindiffgroup";
			 actionForward = handleDetailPageException(mapping, request,errorMessage);
		 }catch(UsrisaPowerUsrException ex){
			 logger.error(Logger.EVENT_FAILURE," UsrisaPowerUsrException is thrown ");
			 String errorMessage = "error.usrisapwrusr";
			 actionForward = handleDetailPageException(mapping, request,errorMessage);
		 }
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the detailPage method :"+loggedUser);
		return actionForward;
	}


	/**
	 * Method used to populate the user admin form
	 * @param HttpServletRequest
	 * @param UserAdminForm
	 * @param UserDetails
	 * @return
	 */
	private void populateUserAdminForm(HttpServletRequest request,
			UserAdminForm userAdminForm, UserDetails userDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering populateUserAdminForm method");
		ArrayList<FunctionDetails> availableFunctionDetails = (ArrayList<FunctionDetails>)request.getSession(false).getAttribute("USERFUNCTIONS");
		if(userDetails!=null){
			populateDetailPage(userAdminForm, userDetails,availableFunctionDetails);
			userAdminForm.setOperationType("UPDATE");
			userDetails = null;
		}else{
			
			/*for(FunctionDetails availableFunctions:availableFunctionDetails){
				if(availableFunctions.getFunctionCode().equals("07")){
					availableFunctionDetails.remove(availableFunctions);
					break;
				}
			}*/
			userAdminForm.setAvailableFunctionDetails(availableFunctionDetails);
			userAdminForm.setOperationType("SAVE");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving populateUserAdminForm method");
	}


	/**
	 * Method to handle the exception thrown at the detail page request
	 * @param ActionMapping
	 * @param HttpServletRequest
	 * @param String - errorMessage
	 * @return ActionForward
	 */
	private ActionForward handleDetailPageException(ActionMapping mapping,
			HttpServletRequest request, String errorMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the handleDetailPageException method ");
		ActionForward actionForward;
		ActionMessages messages = new ActionMessages(); 
		messages.add("app.message", new ActionMessage(errorMessage,true));
		saveErrors(request, messages);
		actionForward =  mapping.findForward("searchPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the handleDetailPageException method ");
		return actionForward;
	}


	/**
	 * Method used to populate the details page
	 * @param UserAdminForm
	 * @param UserDetails
	 * @param ArrayList<FunctionDetails>
	 * @return
	 */
	private void populateDetailPage(UserAdminForm userAdminForm,
			UserDetails userDetails,
			ArrayList<FunctionDetails> availableFunctionDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateDetailPage method ");
		userAdminForm.setUserName(userDetails.getUserName());
		// CEN_3 start
		//userAdminForm.setPassword(userDetails.getPassword());
		// CEN_3 end
		userAdminForm.setLockIND(userDetails.getLockIND());
		userAdminForm.setLanID(userDetails.getLanID());
		if(userDetails.getPasswordDate()!=null){
			userAdminForm.setPasswordExpired(new VMSUtils().isPasswordExpired(userDetails.getPasswordDate()));
		}
		populateFunctionDetails(userAdminForm, userDetails,availableFunctionDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateDetailPage method ");
	}


	/**
	 * Method used to populate the function details
	 * @param UserAdminForm
	 * @param UserDetails
	 * @param ArrayList<FunctionDetails>
	 * @return
	 */
	private void populateFunctionDetails(UserAdminForm userAdminForm,UserDetails userDetails,ArrayList<FunctionDetails> availableFunctionDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateFunctionDetails method ");
		Iterator<UserFunctionDetails> iterator= userDetails.getUserFunctionDetails().iterator();
		ArrayList<FunctionDetails> selectedFunctionDetails = new ArrayList<FunctionDetails>();
		ArrayList<FunctionDetails> unSelectedFunctionDetails = new ArrayList<FunctionDetails>();
		FunctionDetails functionDetails = null;
		while(iterator.hasNext()){
			functionDetails = iterator.next().getFunctionDetails();
			selectedFunctionDetails.add(functionDetails);
		}
		populateUnSelectedFunctions(availableFunctionDetails,selectedFunctionDetails, unSelectedFunctionDetails);
		userAdminForm.setAvailableFunctionDetails(unSelectedFunctionDetails);
		userAdminForm.setSelectedFunctionDetails(selectedFunctionDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateFunctionDetails method ");
	}


	/**
	 * Method used to populate the unselected functions
	 * @param ArrayList<FunctionDetails>
	 * @param ArrayList<FunctionDetails>
	 * @param ArrayList<FunctionDetails>
	 * @return
	 */
	private void populateUnSelectedFunctions(ArrayList<FunctionDetails> availableFunctionDetails,ArrayList<FunctionDetails> selectedFunctionDetails,
			ArrayList<FunctionDetails> unSelectedFunctionDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateUnSelectedFunctions method ");
		for(FunctionDetails availableFunctions :availableFunctionDetails){
			boolean isPresent = false;
			for(FunctionDetails functions:selectedFunctionDetails){
				if(functions.getFunctionCode().equals(availableFunctions.getFunctionCode())){
					isPresent=true;
					break;
				}
			}
			System.out.println("code" +availableFunctions.getFunctionCode());
			/*if(!isPresent && !availableFunctions.getFunctionCode().equals("07")){
				unSelectedFunctionDetails.add(availableFunctions);
			}*/
			if(!isPresent){
				unSelectedFunctionDetails.add(availableFunctions);
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateUnSelectedFunctions method ");
	}
	
	/**
	 * Method used to update the power user details
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
		String loggedUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedUser);
		UserAdminForm userAdminForm = (UserAdminForm)form;
		ActionForward actionForward;
		String userName = userAdminForm.getUserName();
		logger.info(Logger.EVENT_UNSPECIFIED,"User Name " + userName);
		String vmsGroup = userAdminForm.getVmsGroup();
		logger.info(Logger.EVENT_UNSPECIFIED,"VMS Group" + vmsGroup);
		String powerUserLanID = (String)request.getSession(false).getAttribute("LANID");
		// CEN_3 start
		UserCredentials userCredentials = vmsWebActionDelegate.getUserCredentials(loggedUser); //Defect ID: 1386
		if(userCredentials.getPassword().equals(userAdminForm.getAdminPassword())){
			processUpdateRequest(request, userAdminForm, userName, vmsGroup,powerUserLanID);
			actionForward =  mapping.findForward("confirmPage");
		}else{
			saveIncorrectLoginMsg(mapping, request);
			setFunctionDetails(request, userAdminForm);
			actionForward =  mapping.findForward("detailPage");
		}
		// CEN_3 end
		
		
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the update method: " +loggedUser);
		return actionForward;
	}

	// CEN_3 start
	/**
	 * Method to process the update request
	 * @param HttpServletRequest
	 * @param UserAdminForm
	 * @param String - userName
	 * @param String - vmsGroup
	 * @param String - powerUserLanID
	 * @return
	 * @throws UsrAlreadyExistException
	 * @throws UsrisaPowerUsrException
	 */
	private void processUpdateRequest(HttpServletRequest request,UserAdminForm userAdminForm, String userName, String vmsGroup,String powerUserLanID) throws UsrAlreadyExistException,
			UsrisaPowerUsrException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the processUpdateRequest method ");
		UserDetails userDetails = vmsWebActionDelegate.getUser(userName,userAdminForm.getVmsGroup());
		ArrayList<FunctionDetails> vmsFunctionDetails = vmsWebActionDelegate.getFunctionDetails(vmsGroup);
		populateUserDetails_Update(userAdminForm, powerUserLanID,userDetails,vmsFunctionDetails,vmsGroup);
		try{
			Boolean status = vmsWebActionDelegate.updateUserDetails(userDetails,false);
			logger.info(Logger.EVENT_UNSPECIFIED,"status " + status);
			populateMessage(request,status);
		}catch(UserOperationException ex){
			logger.error(Logger.EVENT_FAILURE,"UserOperationException is thrown");
			populateMessage(request,false);
		}
		userDetails = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the processUpdateRequest method ");
	}

	
	/**
	 * Method used to save the log admin password error message 
	 * @param ActionMapping
	 * @param HttpServletRequest
	 * @return
	 */
	private void  saveIncorrectLoginMsg(ActionMapping mapping,
			HttpServletRequest request) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  saveIncorrectLoginMsg method ");
		logger.info(Logger.EVENT_UNSPECIFIED,"Incorrect admin password");
		ActionMessages messages = new ActionMessages();
		messages.add("app.password", new ActionMessage("error.adminpassword.incorrect"));
		saveErrors(request, messages);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  saveIncorrectLoginMsg method ");
	}
	
	/**
	 * Method to set the function details.
	 * @param HttpServletRequest
	 * @param UserAdminForm
	 * @return
	 */
	private void setFunctionDetails(HttpServletRequest request,
			UserAdminForm userAdminForm) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the setFunctionDetails method ");
		userAdminForm.setSelectedFunctionDetails((ArrayList<FunctionDetails>)request.getSession(false).getAttribute("selectedFunctionDetails"));
		userAdminForm.setAvailableFunctionDetails((ArrayList<FunctionDetails>)request.getSession(false).getAttribute("availableFunctionDetails"));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the setFunctionDetails method ");
	}
	
	// CEN_3 end
	/**
	 * Method used to add the new power user
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
		String loggedInUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedInUser);
		UserAdminForm userAdminForm = (UserAdminForm)form;
		String superUserLanID = (String)request.getSession(false).getAttribute("LANID");
		String vmsGroup = userAdminForm.getVmsGroup();
		// CEN_3 start
		ActionForward actionForward;
		UserCredentials userCredentials = vmsWebActionDelegate.getUserCredentials(loggedInUser);
		if(userCredentials.getPassword().equals(userAdminForm.getAdminPassword())){
			processSaveRequest(request, userAdminForm, superUserLanID, vmsGroup);
			actionForward =  mapping.findForward("confirmPage");
		}else{
			saveIncorrectLoginMsg(mapping, request);
			setFunctionDetails(request, userAdminForm);
			actionForward =  mapping.findForward("detailPage");
		}
		// CEN_3 end
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the save method :"+loggedInUser);
		return actionForward;
		
	}

	// CEN_3 start

	/**
	 * Method to process the save request
	 * @param HttpServletRequest
	 * @param UserAdminForm
	 * @param String - superUserLanID
	 * @param String - vmsGroup
	 * @return
	 */
	private void processSaveRequest(HttpServletRequest request,
			UserAdminForm userAdminForm, String superUserLanID, String vmsGroup) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processSaveRequest method ");
		UserDetails userDetails = populateUserDetails(userAdminForm,superUserLanID, vmsGroup);
		Boolean status = vmsWebActionDelegate.saveUserDetails(userDetails);
		populateMessage(request,status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processSaveRequest method ");
	}

	// CEN_3 end
	/**
	 * Method used to delete the user 
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the delete method ");
		String loggedUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedUser);
		UserAdminForm userAdminForm = (UserAdminForm)form;
		// CEN_3 start
		ActionForward actionForward;
		String userName = userAdminForm.getUserName();
		String vmsGroup = userAdminForm.getVmsGroup();
		logger.info(Logger.EVENT_UNSPECIFIED,"User Name,VMS Group " + userName+","+vmsGroup);
		UserCredentials userCredentials = vmsWebActionDelegate.getUserCredentials(loggedUser);
		if(userCredentials.getPassword().equals(userAdminForm.getAdminPassword())){
			processDeleteRequest(request, userAdminForm, userName);
			actionForward =  mapping.findForward("confirmPage");
		}else{
			saveIncorrectLoginMsg(mapping, request);
			setFunctionDetails(request, userAdminForm);
			actionForward =  mapping.findForward("detailPage");
		}
		// CEN_3 end
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the delete method "+loggedUser);
		return actionForward;
	}


	// CEN_3 start
	/**
	 * Method to process the delete request
	 * @param HttpServletRequest
	 * @param UserAdminForm
	 * @param String - userName
	 * @return
	 * @throws UsrAlreadyExistException
	 * @throws UsrisaPowerUsrException
	 */
	private void processDeleteRequest(HttpServletRequest request,
			UserAdminForm userAdminForm, String userName)
			throws UsrAlreadyExistException, UsrisaPowerUsrException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processDeleteRequest method ");
		UserDetails userDetails = vmsWebActionDelegate.getUser(userName,userAdminForm.getVmsGroup());
		try{
			Boolean status = vmsWebActionDelegate.deleteUserDetails(userDetails,false);
			logger.info(Logger.EVENT_UNSPECIFIED,"status " + status);
			populateMessage(request,status);
		}catch(UserOperationException ex){
			populateMessage(request,false);
			logger.error(Logger.EVENT_FAILURE,"UserOperationException is thrown");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processDeleteRequest method ");
	}
	
	// CEN_3 end
	
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
	
	/**
	 * Method used to populate the new user details
	 * @param UserAdminForm
	 * @param String - superUserLanID
	 * @param String - vmsGroup
	 * @return
	 */
	private UserDetails populateUserDetails(
			UserAdminForm userAdminForm, String superUserLanID,
			String vmsGroup) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateUserDetails method ");
		UserDetails userDetails = new UserDetails();
		populateUserBasicDetails(userAdminForm, superUserLanID,userDetails);
		Set<UserFunctionDetails> userFunctionDetails = new HashSet<UserFunctionDetails>();
		String userAccessFunctions[] =  userAdminForm.getUserFunctionCodes().split("\\$");
		ArrayList<String> newFunctions = new ArrayList<String>();
		for(String functionCode:userAccessFunctions  ){
			newFunctions.add(functionCode);
		}
		new VMSUtils().addNewFunctions(vmsGroup, userFunctionDetails, newFunctions);
		userDetails.setLanID(userAdminForm.getLanID());
		userDetails.setUserFunctionDetails(userFunctionDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateUserDetails method ");
		return userDetails;
	}


	/**
	 * Method used to populate the basic user details
	 * @param UserAdminForm
	 * @param String - superUserLanID
	 * @param UserDetails
	 */
	private void populateUserBasicDetails(
			UserAdminForm userAdminForm, String superUserLanID,
			UserDetails userDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateUserBasicDetails method ");
		userDetails.setUserName(userAdminForm.getUserName());
		// CEN_3 start
		userDetails.setPassword(userAdminForm.getUserPassword());
		// CEN_3 end
		userDetails.setPasswordDate(new Date());
		userDetails.setCreatedBy(superUserLanID);
		userDetails.setCreatedTimestamp(new Date());
		userDetails.setGroupID(userAdminForm.getVmsGroup());
		userDetails.setPowerIND(Indicator.N.getIndicator());
		userDetails.setRetryCount(0);
		userDetails.setSuperIND(Indicator.N.getIndicator());
		userDetails.setUpdatedBy(superUserLanID);
		userDetails.setUpdatedTimestamp(new Date());
		populateLockIND(userAdminForm, userDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateUserBasicDetails method ");
	}
	/**
	 *  Method used to populate the user details to update them
	 * @param UserAdminForm
	 * @param String - powerUserLanID
	 * @param UserDetails
	 * @param ArrayList<FunctionDetails>
	 * @param String - vmsGroup
	 * @return
	 */
	private void populateUserDetails_Update(UserAdminForm userAdminForm, String powerUserLanID,UserDetails userDetails,ArrayList<FunctionDetails> vmsFunctionDetails,String vmsGroup) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateUserDetails_Update method ");
		userDetails.setUserName(userAdminForm.getUserName());
		populatePassword(userAdminForm, userDetails);
		populateLockIND(userAdminForm, userDetails);
		userDetails.setLanID(userAdminForm.getLanID());
		populatePassword(userAdminForm, userDetails);
		userDetails.setRetryCount(0);
		userDetails.setUpdatedBy(powerUserLanID);
		userDetails.setUpdatedTimestamp(new Date());
		prepareFunctionDetails(userAdminForm, userDetails,vmsFunctionDetails,vmsGroup);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateUserDetails_Update method ");
	}


	/**
	 * Method used to prepare the function details
	 * @param UserAdminForm
	 * @param UserDetails
	 * @param ArrayList<FunctionDetails>
	 * @param String - vmsGroup
	 * @return
	 */
	private void prepareFunctionDetails(UserAdminForm userAdminForm,UserDetails userDetails,ArrayList<FunctionDetails> vmsFunctionDetails,String vmsGroup) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the prepareFunctionDetails method ");
		VMSUtils vmsUtils = new VMSUtils();
	    Set<UserFunctionDetails> userFunctionDetails = userDetails.getUserFunctionDetails();
		String userAccessFunctions[] =  userAdminForm.getUserFunctionCodes().split("\\$");
		ArrayList<String> newFunctions = new ArrayList<String>();
		ArrayList<String> oldFunctions = new ArrayList<String>();
		vmsUtils.getUnselectedFunctions(userFunctionDetails, userAccessFunctions,oldFunctions);
		vmsUtils.removeUnselectedFunctions(userFunctionDetails, oldFunctions);
		vmsUtils.getNewFunctions(userFunctionDetails, userAccessFunctions, newFunctions);
		vmsUtils.addNewFunctions(vmsGroup, userFunctionDetails, newFunctions);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the prepareFunctionDetails method ");
	}


	

	/**
	 * Method used to populate the user lock indicator
	 * @param UserAdminForm
	 * @param UserDetails
	 * @return
	 */
	private void populateLockIND(UserAdminForm userAdminForm,
			UserDetails userDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateLockIND method ");
		if(userAdminForm.getLockIND()==Indicator.Y.getIndicator()){
			userDetails.setLockIND(Indicator.Y.getIndicator());
		}else{
			userDetails.setLockIND(Indicator.N.getIndicator());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateLockIND method ");
	}


	/**
	 * Method used to populate the password if it has been modified
	 * @param UserAdminForm
	 * @param UserDetails
	 * @return
	 */
	private void populatePassword(UserAdminForm userAdminForm,
			UserDetails userDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populatePassword method ");
		// CEN_3 start
		if(!userAdminForm.getUserPassword().equals("")&&!userDetails.getPassword().equals(userAdminForm.getUserPassword())){
			userDetails.setPasswordDate(new Date());
			userDetails.setPassword(userAdminForm.getUserPassword());
		}
		// CEN_3 end
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populatePassword method ");
	}

}
