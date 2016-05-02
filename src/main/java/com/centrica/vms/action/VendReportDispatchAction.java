/**
 * VendReportDispatchAction.java
 * Purpose: Class to create vend history dispatch action
 *
 * @author nagarajk
 */
package com.centrica.vms.action;


import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.centrica.vms.delegate.VMSWebActionDelegate;
import com.centrica.vms.form.VendReportRequestForm;
import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.model.StatusDetails;

/**
 * Methods to create vend history dispatch action
 * @see VMSDispatchAction
 */
public class VendReportDispatchAction extends VMSDispatchAction {

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
	public ActionForward reportPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the reportPage method ");
		String loggedUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedUser);
		
		VendReportMenuBean menuBean = new VendReportMenuBean();
		HttpSession session = request.getSession();
		
	    List<String> menuHourList = new ArrayList<String>();
	    menuHourList = menuBean.menuHourList();
        session.setAttribute( "menuStartHourList", menuHourList);
        session.setAttribute( "menuEndHourList", menuHourList);

        List<String> menuMinuteList = new ArrayList<String>();
	    menuMinuteList = menuBean.menuMinuteList();
        session.setAttribute( "menuStartMinuteList", menuMinuteList);
        session.setAttribute( "menuEndMinuteList", menuMinuteList);
        
        List<String> menuMeridList = new ArrayList<String>();
        menuMeridList = menuBean.menuMeridList();
        session.setAttribute( "menuStartMeridList", menuMeridList);
        session.setAttribute( "menuEndMeridList", menuMeridList);
        
        List<SourceDetails> sourceLst = vmswebActionDelegate.getVendSourceList();
        Map<String, String> selectedSourceList = new HashMap<String, String>();
        selectedSourceList = menuBean.setSelectedSourceDetails(sourceLst);
        session.setAttribute( "selectedSourceDetails", selectedSourceList);
        
        List<StatusDetails> statusLst = vmswebActionDelegate.getVendStatusList();
        Map<Integer, String> selectedStatusMap = new HashMap<Integer, String>();
        selectedStatusMap = menuBean.setSelectedStatusDetails(statusLst);
        session.setAttribute( "selectedStatusDetails", selectedStatusMap);
        
		ActionForward actionForward =  mapping.findForward("reportPage");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the reportPage method:"+loggedUser);
		return actionForward;
	}
	
	/**
	 * Method to view the submit page
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
		logger.debug(Logger.EVENT_SUCCESS,"Entering the submit method ");
		String loggedUser = (String)request.getSession(false).getAttribute("USERNAME");
		logger.info(Logger.EVENT_UNSPECIFIED,"Logged in User name" +loggedUser);
		VendReportRequestForm  vendReportRequestForm = (VendReportRequestForm)form;
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "inline;filename="+"Vend_Report.xls");
		// Write the output
		OutputStream out = response.getOutputStream();
		vendReportRequestForm = vmswebActionDelegate.sendVendReportRequest(vendReportRequestForm, out);
		logger.info(Logger.EVENT_UNSPECIFIED,"REPORT SUCCESSFULLY GENERATED");
		out.flush();
		out.close();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the submit method : "+loggedUser);
		return null;
	}
	
}
