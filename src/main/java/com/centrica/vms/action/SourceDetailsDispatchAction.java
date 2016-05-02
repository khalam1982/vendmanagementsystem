package com.centrica.vms.action;

import java.util.List;

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
import com.centrica.vms.form.SourceDetailsForm;
import com.centrica.vms.model.SourceDetails;

/**
 * SourceDetailsDispatchAction - Action class which handles 
 * request from Source Details Page
 */
public class SourceDetailsDispatchAction extends VMSDispatchAction {

	private final Logger logger = ESAPI.getLogger(this.getClass());

	private static final String SHOW_SOURCE = "showSource";
	private static final String SOURCE_LIST = "sourceDetList";
	private static final String MESSAGE = "message";
	private static final String MSG_SOURCE_UPDATE = "app.source.update";
	private static final String ERROR = "error";
	private static final String ERR_UPDATE_FAILED = "error.source.updatefailed";

	private final VMSWebActionDelegate actionDelegate = new VMSWebActionDelegate();

	/**
	 * Process Show flow. Displays Source Details
	 * 
	 * @param mapping - ActionMapping
	 * @param form - ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward processShow(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, 
			final HttpServletResponse response)	throws Exception {

		logger.debug(Logger.EVENT_SUCCESS,"Entering the processShow method ");
		final List<SourceDetails> sourceList = actionDelegate.getVendSourceList();
		request.getSession().setAttribute(SOURCE_LIST, sourceList);
		final ActionForward actionForward =  mapping.findForward(SHOW_SOURCE);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the processShow method ");
		return actionForward;

	}

	/**
	 * Process Submit flow. Updates Source Details to database
	 * 
	 * @param mapping - ActionMapping
	 * @param form - ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward processSubmit(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, 
			final HttpServletResponse response)	throws Exception {

		logger.debug(Logger.EVENT_SUCCESS,"Entering the processSubmit method ");
		try {
			final SourceDetailsForm sourceDtlsFrm = (SourceDetailsForm)form;
			SourceDetails source = null;
			final List<SourceDetails> sourceList = (List<SourceDetails>) request.getSession().getAttribute(SOURCE_LIST);
			for( SourceDetails sourceDet : sourceList) {
				if( sourceDtlsFrm.getSelectedSource().equalsIgnoreCase(sourceDet.getSource()) ) {
					source = sourceDet;
					break;
				}
			}
			
			if(source != null){
				
				source.setMaxVend(new Integer(sourceDtlsFrm.getVendLimit()));
			}
			
			final boolean isSuccess = actionDelegate.updateSourceDetails(source);
			if( isSuccess ) {
				final ActionMessages messages = new ActionMessages();
				final ActionMessage msg = new ActionMessage(MSG_SOURCE_UPDATE);
				messages.add(MESSAGE, msg);
				saveMessages(request, messages);
			} else {
				setError(request);
			}
		} catch(Exception e) {
			logger.error(Logger.EVENT_FAILURE,"Exception occured when submitting source details " + e.getMessage());
			setError(request);
		}
		final ActionForward actionForward = mapping.findForward(SHOW_SOURCE);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the processSubmit method ");
		return actionForward;

	}

	/**
	 * Source Select Show flow. Displays Vend limit Details
	 * 
	 * @param mapping - ActionMapping
	 * @param form - ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return ActionForward
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward onSourceSelect(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, 
			final HttpServletResponse response)	throws Exception {

		logger.debug(Logger.EVENT_SUCCESS,"Entering the onSourceSelect method ");
		final SourceDetailsForm sourceDtlsFrm = (SourceDetailsForm)form;

		String maxVend = "";
		String selectedSrc = sourceDtlsFrm.getSelectedSource();
		if( selectedSrc != null && !("-1").equalsIgnoreCase(selectedSrc) ) {
			final List<SourceDetails> sourceList = (List<SourceDetails>) request.getSession().getAttribute(SOURCE_LIST);
			for( SourceDetails sourceDet : sourceList) {
				if( selectedSrc.equalsIgnoreCase(sourceDet.getSource()) ) {
					if( sourceDet.getMaxVend() != null ) {
						maxVend = sourceDet.getMaxVend().toString();
					}
					break;
				}
			}
		}
		sourceDtlsFrm.setVendLimit(maxVend);
		final ActionForward actionForward =  mapping.findForward(SHOW_SOURCE);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the onSourceSelect method ");
		return actionForward;

	}

	/**
	 * Sets Error
	 * 
	 * @param request - HttpServletRequest
	 */
	@SuppressWarnings("deprecation")
	private void setError(final HttpServletRequest request) {

		final ActionErrors errors = new ActionErrors();
		errors.add(ERROR, new ActionMessage(ERR_UPDATE_FAILED));
		saveErrors(request, errors);

	}

}
