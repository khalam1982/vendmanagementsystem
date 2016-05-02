/**
 * MeterDispatchAction.java
 * Purpose: Class to create Meter dispatch action
 *
 * @author ramasap1
 */
package com.centrica.vms.action;

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

import com.centrica.vms.common.FuelTypeEnum;
import com.centrica.vms.delegate.VMSWebActionDelegate;
import com.centrica.vms.form.MeterDetailsForm;
import com.centrica.vms.ws.model.MeterDetails;

/**
 * Methods to create Meter dispatch action
 * @see VMSDispatchAction
 */
public class MeterDispatchAction extends VMSDispatchAction {

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
		((MeterDetailsForm)form).setMsn("");
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
		MeterDetailsForm  meterDetailsForm = (MeterDetailsForm)form;
		MeterDetails meterDetails = vmswebActionDelegate.getMeterDetails(meterDetailsForm.getMsn());
		if(meterDetails!=null){
			if (meterDetails.getFuelTypeID() == FuelTypeEnum.ELECTRIC.getFuelType()) {
				meterDetailsForm.setFuelType("ELECTRIC");	
			} else {
				meterDetailsForm.setFuelType("GAS");
			}
				
			
			meterDetailsForm.setOperationType("UPDATE");
		}else{
			meterDetailsForm.setFuelType("");
			meterDetailsForm.setOperationType("SAVE");
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
		logger.info(Logger.EVENT_UNSPECIFIED,"User name"+userName);
		MeterDetails meterDetails = getMeterDetails(form, userName);
		//MeterDetailsForm meterDetailsForm = (MeterDetailsForm)form;
		Boolean status = vmswebActionDelegate.saveMeterDetails(meterDetails);
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
		MeterDetails meterDetails = getMeterDetails(form, userName);
		//MeterDetailsForm meterDetailsForm = (MeterDetailsForm)form;
		Boolean status = vmswebActionDelegate.updateMeterDetails(meterDetails);
		populateMessage(request,status);
		ActionForward actionForward =  mapping.findForward("confirmPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the update method :" +userName);
		return actionForward;
	}
	
	/**
	 * Method to delete the meter details
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
		MeterDetails meterDetails = getMeterDetails(form, userName);
		//MeterDetailsForm meterDetailsForm = (MeterDetailsForm)form;
		ActionForward actionForward =  mapping.findForward("confirmPage");
		Boolean status = vmswebActionDelegate.deleteMeterDetails(meterDetails);
		populateMessage(request,status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the delete method: "+userName);
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
		((MeterDetailsForm)form).setMsn("");
		ActionForward actionForward =  mapping.findForward("searchPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the cancel method: "+userName);
		return actionForward;
	}

	/**
	 * Method to prepare the meter details from action form
	 * @param ActionForm
	 * @return MeterDetails
	 */
	private MeterDetails getMeterDetails(ActionForm form, String username) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getMeterDetails method");
		MeterDetailsForm  meterDetailsForm = (MeterDetailsForm)form;
		MeterDetails meterDetails = new MeterDetails();
		if (meterDetailsForm.getFuelType().equals("ELECTRIC")) {
			meterDetails.setFuelTypeID(FuelTypeEnum.ELECTRIC.getFuelType());
		} else {
			meterDetails.setFuelTypeID(FuelTypeEnum.GAS.getFuelType());
		}
		meterDetails.setMsn(meterDetailsForm.getMsn());
		meterDetails.setLastUpdatedByID(username);
		meterDetails.setLastUpdatedDate(Calendar.getInstance().getTime());
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getMeterDetails method");
		return meterDetails;
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
