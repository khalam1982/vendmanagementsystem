/**
 * PowerUserAdminDispatchAction.java
 * Purpose: Class to create power user admin dispatch action
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
import com.centrica.vms.form.PowerUserAdminForm;
import com.centrica.vms.model.FunctionDetails;
import com.centrica.vms.model.GroupDetails;
import com.centrica.vms.model.UserCredentials;
import com.centrica.vms.model.UserDetails;
import com.centrica.vms.model.UserFunctionDetails;
import com.centrica.vms.model.UserDetails.Indicator;
/**
 * Methods to create power user admin dispatch action
 * @see VMSDispatchAction
 */
public class PowerUserAdminDispatchAction extends VMSDispatchAction {
	
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
	public ActionForward groupPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the groupPage method ");
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		PowerUserAdminForm powerUserAdminForm = (PowerUserAdminForm)form;
		ActionForward actionForward =  mapping.findForward("groupPage");
		String groupID = (String)request.getSession(false).getAttribute("GROUPID");
		logger.info(Logger.EVENT_UNSPECIFIED,"GroupID is " + groupID);
		ArrayList<GroupDetails> groupDetails = vmsWebActionDelegate.getGroupDetails(groupID);
		powerUserAdminForm.setGroupDetails(groupDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the groupPage method: "+userName);
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
	public ActionForward detailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the detailPage method ");
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		PowerUserAdminForm powerUserAdminForm = (PowerUserAdminForm)form;
		ActionForward actionForward =  mapping.findForward("detailPage");
		String vmsGroup = powerUserAdminForm.getVmsGroup();
		logger.info(Logger.EVENT_UNSPECIFIED,"VMS Group" + vmsGroup);
		UserDetails userDetails = vmsWebActionDelegate.getUserDetails(vmsGroup);
		ArrayList<FunctionDetails> availableFunctionDetails = new ArrayList<FunctionDetails>(); 
		if(!vmsGroup.equals("G4")){
			availableFunctionDetails = vmsWebActionDelegate.getFunctionDetails(vmsGroup);
		}
		if(userDetails!=null){
			populateDetailPage(powerUserAdminForm, userDetails,availableFunctionDetails);
			powerUserAdminForm.setOperationType("UPDATE");
			userDetails = null;
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"availableFunctionDetails " + availableFunctionDetails.size());
			powerUserAdminForm.setAvailableFunctionDetails(availableFunctionDetails);
			powerUserAdminForm.setOperationType("SAVE");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the detailPage method: "+userName);
		return actionForward;
	}


	/**
	 * Method used to populate the details page
	 * @param PowerUserAdminForm
	 * @param UserDetails
	 * @param ArrayList<FunctionDetails>
	 * @return
	 */
	private void populateDetailPage(PowerUserAdminForm powerUserAdminForm,
			UserDetails userDetails,
			ArrayList<FunctionDetails> availableFunctionDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateDetailPage method ");
		powerUserAdminForm.setUserName(userDetails.getUserName());// CEN_3 start
		powerUserAdminForm.setLockIND(userDetails.getLockIND());// CEN_3 end
		powerUserAdminForm.setLanID(userDetails.getLanID());
		if(userDetails.getPasswordDate()!=null){
			powerUserAdminForm.setPasswordExpired(new VMSUtils().isPasswordExpired(userDetails.getPasswordDate()));
		}
		populateFunctionDetails(powerUserAdminForm, userDetails,availableFunctionDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateDetailPage method ");
	}


	/**
	 * Method used to populate the function details
	 * @param PowerUserAdminForm
	 * @param UserDetails
	 * @param ArrayList<FunctionDetails>
	 * @return
	 */
	private void populateFunctionDetails(PowerUserAdminForm powerUserAdminForm,UserDetails userDetails,ArrayList<FunctionDetails> availableFunctionDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateFunctionDetails method ");
		Iterator<UserFunctionDetails> iterator= userDetails.getUserFunctionDetails().iterator();
		ArrayList<FunctionDetails> selectedFunctionDetails = new ArrayList<FunctionDetails>();
		ArrayList<FunctionDetails> functions = new ArrayList<FunctionDetails>();
		FunctionDetails functionDetails = null;
		while(iterator.hasNext()){
			functionDetails = iterator.next().getFunctionDetails();
			functionDetails.getFunctionCode();
			selectedFunctionDetails.add(functionDetails);
		}
		populateAvailableFunctions(availableFunctionDetails,selectedFunctionDetails, functions);
		powerUserAdminForm.setAvailableFunctionDetails(functions);
		powerUserAdminForm.setSelectedFunctionDetails(selectedFunctionDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateFunctionDetails method ");
	}


	/**
	 * Method used to populate the available functions to select
	 * @param ArrayList<FunctionDetails>
	 * @param ArrayList<FunctionDetails>
	 * @param ArrayList<FunctionDetails>
	 * @return
	 */
	private void populateAvailableFunctions(ArrayList<FunctionDetails> availableFunctionDetails,ArrayList<FunctionDetails> selectedFunctionDetails,
			ArrayList<FunctionDetails> functions) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateAvailableFunctions method ");
		for(FunctionDetails availableDetails:availableFunctionDetails){
			boolean isPresent = false;
			for(FunctionDetails selectedDetails:selectedFunctionDetails){
				if(selectedDetails.getFunctionCode().equals(availableDetails.getFunctionCode())){
					isPresent = true;
					break;
				}
			}
			if(!isPresent){
				functions.add(availableDetails);
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateAvailableFunctions method ");
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
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		PowerUserAdminForm powerUserAdminForm = (PowerUserAdminForm)form;
		ActionForward actionForward;
		String vmsGroup = powerUserAdminForm.getVmsGroup();
		logger.info(Logger.EVENT_UNSPECIFIED,"VMS Group" + vmsGroup);
		String superUserLanID = (String)request.getSession(false).getAttribute("LANID");
		// CEN_3 start
		UserCredentials userCredentials = vmsWebActionDelegate.getUserCredentials(userName);
		if(userCredentials.getPassword().equals(powerUserAdminForm.getAdminPassword())){
			processUpdateRequest(request, powerUserAdminForm, vmsGroup,superUserLanID);
			actionForward =  mapping.findForward("confirmPage");
		}else{
			saveIncorrectLoginMsg(mapping, request);
			setFunctionDetails(request, powerUserAdminForm);
			actionForward =  mapping.findForward("detailPage");
		}
		// CEN_3 end
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the update method : "+userName);
		return actionForward;
	}

	// CEN_3 start
	/**
	 * Method to set the function details.
	 * @param HttpServletRequest
	 * @param PowerUserAdminForm
	 * @return
	 */
	private void setFunctionDetails(HttpServletRequest request,
			PowerUserAdminForm powerUserAdminForm) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the setFunctionDetails method ");
		powerUserAdminForm.setSelectedFunctionDetails((ArrayList<FunctionDetails>)request.getSession(false).getAttribute("selectedFunctionDetails"));
		powerUserAdminForm.setAvailableFunctionDetails((ArrayList<FunctionDetails>)request.getSession(false).getAttribute("availableFunctionDetails"));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the setFunctionDetails method ");
	}
	
	// CEN_3 end
	/**
	 * Method to populate and update the power user.
	 * @param HttpServletRequest
	 * @param PowerUserAdminForm
	 * @param String - vmsGroup
	 * @param String - superUserLanID
	 * @return
	 */
	private void processUpdateRequest(HttpServletRequest request,
			PowerUserAdminForm powerUserAdminForm, String vmsGroup,
			String superUserLanID) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processUpdateRequest method");
		UserDetails userDetails = vmsWebActionDelegate.getUserDetails(vmsGroup);
		populateUserDetails_Update(powerUserAdminForm, superUserLanID,userDetails,vmsGroup);
		try{
			Boolean status = vmsWebActionDelegate.updateUserDetails(userDetails,true);
			populateMessage(request,status);
		}catch(UserOperationException ex){
			logger.error(Logger.EVENT_FAILURE,"child user UserOperationException is thrown");
			populateMessage(request,false);
		}
		userDetails = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processUpdateRequest method");
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
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		PowerUserAdminForm powerUserAdminForm = (PowerUserAdminForm)form;
		String superUserLanID = (String)request.getSession(false).getAttribute("LANID");
		String vmsGroup = powerUserAdminForm.getVmsGroup();
		ActionForward actionForward;
		// CEN_3 start
		UserCredentials userCredentials = vmsWebActionDelegate.getUserCredentials(userName);
		if(userCredentials.getPassword().equals(powerUserAdminForm.getAdminPassword())){
			processSaveRequest(request, powerUserAdminForm, superUserLanID,vmsGroup);
			actionForward =  mapping.findForward("confirmPage");
		}else{
			saveIncorrectLoginMsg(mapping, request);
			setFunctionDetails(request, powerUserAdminForm);
			actionForward =  mapping.findForward("detailPage");
		}
		// CEN_3 end
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the save method :"+userName);
		return actionForward;
		
	}

	// CEN_3 start

	/**
	 * Method to populate and save power user
	 * @param HttpServletRequest
	 * @param PowerUserAdminForm
	 * @param String - superUserLanID
	 * @param String - vmsGroup
	 * @return
	 */
	private void processSaveRequest(HttpServletRequest request,
			PowerUserAdminForm powerUserAdminForm, String superUserLanID,
			String vmsGroup) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering  processSaveRequest method ");
		UserDetails userDetails = populateUserDetails(powerUserAdminForm,superUserLanID, vmsGroup);
		Boolean status = vmsWebActionDelegate.saveUserDetails(userDetails);
		populateMessage(request,status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving  processSaveRequest method ");
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
		String userName = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		PowerUserAdminForm powerUserAdminForm = (PowerUserAdminForm)form;
		ActionForward actionForward =  mapping.findForward("confirmPage");
		String vmsGroup = powerUserAdminForm.getVmsGroup();
		logger.info(Logger.EVENT_UNSPECIFIED,"VMS Group" + vmsGroup);
		// CEN_3 start
		UserCredentials userCredentials = vmsWebActionDelegate.getUserCredentials(userName);
		if(userCredentials.getPassword().equals(powerUserAdminForm.getAdminPassword())){
			processDeleteRequest(request, vmsGroup);
			actionForward =  mapping.findForward("confirmPage");
		}else{
			saveIncorrectLoginMsg(mapping, request);
			setFunctionDetails(request, powerUserAdminForm);
			actionForward =  mapping.findForward("detailPage");
		}
		// CEN_3 end
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the delete method :"+userName);
		return actionForward;
	}

	// CEN_3 start
	/**
	 * Method to process the delete request.
	 * @param HttpServletRequest
	 * @param String - vmsGroup
	 * @return
	 */
	private void processDeleteRequest(HttpServletRequest request,String vmsGroup) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the processDeleteRequest method ");
		UserDetails userDetails = vmsWebActionDelegate.getUserDetails(vmsGroup);
		try{
			Boolean status = vmsWebActionDelegate.deleteUserDetails(userDetails,true);
			populateMessage(request,status);
		}catch(UserOperationException ex){
			logger.error(Logger.EVENT_FAILURE,"child user UserOperationException is thrown");
			populateMessage(request,false);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the processDeleteRequest method ");
	}
	
	// CEN_3 end
	/**
	 * Method used to populate the new user details
	 * @param PowerUserAdminForm
	 * @param String - superUserLanID
	 * @param String - vmsGroup
	 * @return UserDetails
	 */
	private UserDetails populateUserDetails(
			PowerUserAdminForm powerUserAdminForm, String superUserLanID,
			String vmsGroup) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateUserDetails method ");
		UserDetails userDetails = new UserDetails();
		populateUserBasicDetails(powerUserAdminForm, superUserLanID,userDetails);
		if(vmsGroup.equals("G4")){
			userDetails.setLanID(powerUserAdminForm.getVmsGroupName());
		}else{
			Set<UserFunctionDetails> userFunctionDetails = new HashSet<UserFunctionDetails>();
			String userAccessFunctions[] =  powerUserAdminForm.getUserFunctionCodes().split("\\$");
			ArrayList<String> newFunctions = new ArrayList<String>();
			for(String functionCode:userAccessFunctions  ){
				newFunctions.add(functionCode);
			}
			new VMSUtils().addNewFunctions(vmsGroup, userFunctionDetails, newFunctions);
			userDetails.setLanID(powerUserAdminForm.getLanID());
			userDetails.setUserFunctionDetails(userFunctionDetails);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateUserDetails method ");
		return userDetails;
	}


	/**
	 * Method used to populate the basic user details
	 * @param PowerUserAdminForm
	 * @param String - superUserLanID
	 * @param UserDetails
	 * @return
	 */
	private void populateUserBasicDetails(
			PowerUserAdminForm powerUserAdminForm, String superUserLanID,
			UserDetails userDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateUserBasicDetails method ");
		userDetails.setUserName(powerUserAdminForm.getUserName());
		userDetails.setPassword(powerUserAdminForm.getUserPassword());
		userDetails.setPasswordDate(new Date());
		userDetails.setCreatedBy(superUserLanID);
		userDetails.setCreatedTimestamp(new Date());
		userDetails.setGroupID(powerUserAdminForm.getVmsGroup());
		userDetails.setPowerIND(Indicator.Y.getIndicator());
		userDetails.setRetryCount(0);
		userDetails.setSuperIND(Indicator.N.getIndicator());
		userDetails.setUpdatedBy(superUserLanID);
		userDetails.setUpdatedTimestamp(new Date());
		populateLockIND(powerUserAdminForm, userDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateUserBasicDetails method ");
	}
	/**
	 *  Method used to populate the user details to update them
	 * @param PowerUserAdminForm
	 * @param String - superUserLanID
	 * @param UserDetails
	 * @param String - vmsGroup
	 * @return 
	 */
	private void populateUserDetails_Update(PowerUserAdminForm powerUserAdminForm, String superUserLanID,UserDetails userDetails,String vmsGroup) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateUserDetails_Update method ");
		userDetails.setUserName(powerUserAdminForm.getUserName());
		populatePassword(powerUserAdminForm, userDetails);
		if(!powerUserAdminForm.getVmsGroup().equals("G4")){
			populateLockIND(powerUserAdminForm, userDetails);
			userDetails.setLanID(powerUserAdminForm.getLanID());
			populatePassword(powerUserAdminForm, userDetails);
			userDetails.setRetryCount(0);
			userDetails.setUpdatedBy(superUserLanID);
			userDetails.setUpdatedTimestamp(new Date());
			prepareFunctionDetails(powerUserAdminForm, userDetails,vmsGroup);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateUserDetails_Update method ");
	}


	/**
	 * Method used to prepare the function details
	 * @param PowerUserAdminForm
	 * @param UserDetails
	 * @param String - vmsGroup
	 * @return 
	 */
	private void prepareFunctionDetails(PowerUserAdminForm powerUserAdminForm,UserDetails userDetails,String vmsGroup) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the prepareFunctionDetails method ");
		VMSUtils vmsUtils = new VMSUtils();
	    Set<UserFunctionDetails> userFunctionDetails = userDetails.getUserFunctionDetails();
		String userAccessFunctions[] =  powerUserAdminForm.getUserFunctionCodes().split("\\$");
		ArrayList<String> newFunctions = new ArrayList<String>();
		ArrayList<String> oldFunctions = new ArrayList<String>();
		vmsUtils.getUnselectedFunctions(userFunctionDetails, userAccessFunctions,oldFunctions);
		vmsUtils.removeUnselectedFunctions(userFunctionDetails, oldFunctions);
		vmsUtils.getNewFunctions(userFunctionDetails, userAccessFunctions, newFunctions);
		vmsUtils.addNewFunctions(vmsGroup, userFunctionDetails, newFunctions);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the prepareFunctionDetails method ");
	}


	/**
	 * Method used to add the new functions assigned to the user 
	 * @param vmsGroup
	 * @param userFunctionDetails
	 * @param newFunctions
	 */
	/*private void addNewFunctions(String vmsGroup,
			Set<UserFunctionDetails> userFunctionDetails,
			ArrayList<String> newFunctions) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the addNewFunctions method ");
		for(String functionCode:newFunctions){
			if(functionCode!=null && !functionCode.equals("")){
				UserFunctionDetails userFunctionDetail = new UserFunctionDetails();
			    userFunctionDetail.setGroupID(vmsGroup);
				userFunctionDetail.setFunctionCode(functionCode);
			    userFunctionDetails.add(userFunctionDetail);
			}
				  
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the addNewFunctions method ");
	}*/


	/**
	 * Method used to get the list of newly assigned functions.
	 * @param userFunctionDetails
	 * @param userAccessFunctions
	 * @param newFunctions
	 */
	/*private void getNewFunctions(Set<UserFunctionDetails> userFunctionDetails,
			String[] userAccessFunctions, ArrayList<String> newFunctions) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the getNewFunctions method ");
		for(String functionCode:userAccessFunctions){
			boolean isPresent = false;
			for(UserFunctionDetails userFunctionDetail:userFunctionDetails){
				if(userFunctionDetail.getFunctionDetails().getFunctionCode().equals(functionCode)){
					isPresent = true;
					break;
				}
			}
			if(!isPresent){
				newFunctions.add(functionCode);
			}
				
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the getNewFunctions method ");
	}*/


	/**
	 * Method used to remove the unselected functions from the existing user's functions
	 * @param userFunctionDetails
	 * @param oldFunctions
	 */
	/*private void removeUnselectedFunctions(
			Set<UserFunctionDetails> userFunctionDetails,
			ArrayList<String> oldFunctions) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the removeUnselectedFunctions method ");
		for(String functionCode:oldFunctions){
			for(UserFunctionDetails userFunctionDetail:userFunctionDetails){
				if(userFunctionDetail.getFunctionDetails().getFunctionCode().equals(functionCode)){
					userFunctionDetails.remove(userFunctionDetail);
					break;
				}
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the removeUnselectedFunctions method ");
	}*/


	/**
	 * Method used to get the list of unselected functions from the existing user functions
	 * @param userFunctionDetails
	 * @param userAccessFunctions
	 * @param oldFunctions
	 */
	/*private void getUnselectedFunctions(
			Set<UserFunctionDetails> userFunctionDetails,
			String[] userAccessFunctions, ArrayList<String> oldFunctions) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the getUnselectedFunctions method ");
		for(UserFunctionDetails userFunctionDetail:userFunctionDetails){
			boolean isSelected = false;
			for(String functionCode:userAccessFunctions){
				if(userFunctionDetail.getFunctionDetails().getFunctionCode().equals(functionCode)){
					isSelected = true;
					break;
				}
			}
			if(!isSelected){
					oldFunctions.add(userFunctionDetail.getFunctionDetails().getFunctionCode());
			}
				
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the getUnselectedFunctions method ");
	}*/

	/**
	 * Method used to populate the user lock indicator
	 * @param PowerUserAdminForm
	 * @param UserDetails
	 * @return
	 */
	private void populateLockIND(PowerUserAdminForm powerUserAdminForm,
			UserDetails userDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populateLockIND method ");
		if(powerUserAdminForm.getLockIND()==Indicator.Y.getIndicator()){
			userDetails.setLockIND(Indicator.Y.getIndicator());
		}else{
			userDetails.setLockIND(Indicator.N.getIndicator());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populateLockIND method ");
	}


	/**
	 * Method used to populate the password if it has been modified
	 * @param PowerUserAdminForm
	 * @param UserDetails
	 * @return
	 */
	private void populatePassword(PowerUserAdminForm powerUserAdminForm,
			UserDetails userDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the populatePassword method ");
		if(!powerUserAdminForm.getUserPassword().equals("")&&!userDetails.getPassword().equals(powerUserAdminForm.getUserPassword())){
			userDetails.setPasswordDate(new Date());
			userDetails.setPassword(powerUserAdminForm.getUserPassword());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the populatePassword method ");
	}

}
