/**
 * SAPCommonService.java
 * Purpose: SAP common service
 * @author nagarajk
 */
package com.centrica.vms.scheduler.external.service;

import java.util.Calendar;

import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;

import smartmeterprocessing.trilliant.uk.co.britishgas.QueryHeadendMastershipDetailsOutServiceStub.HLUParameters_type0;
import smartmeterprocessing.trilliant.uk.co.britishgas.QueryHeadendMastershipDetailsOutServiceStub.QueryHeadendMastershipRequest;
import smartmeterprocessing.trilliant.uk.co.britishgas.QueryHeadendMastershipDetailsOutServiceStub.QueryHeadendMastershipRequest_MT;
import smartmeterprocessing.trilliant.uk.co.britishgas.QueryHeadendMastershipDetailsOutServiceStub.QueryHeadendMastershipResponse_MT;
import smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentRequest;
import smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentRequestMessage;
import smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponse;
import smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponseMessage;

import com.centrica.vms.common.DeviceTypeConstants;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.model.LookUpParam;
import com.centrica.vms.model.PPKeyTransaction;
import com.centrica.vms.model.ServiceId;
import com.centrica.vms.scheduler.external.model.QueryServiceDetails;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.model.VendAckJobDetails;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.VendTxnWSDetails;
/**
 * Methods for Head end common service
 */
public class SAPCommonService {
	
	private static final String SERVICE_CONSUMER = "VMS";
	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	/**
	 * @param vendAcknowlegmentRequest
	 * @param response
	 * @return
	 */
	public final int processApplyVendAcknowledgementResponse(
			VendAcknowlegmentRequest vendAcknowlegmentRequest,
			VendAcknowlegmentResponse response) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processApplyVendAcknowledgementResponse method");
		int status = 0;
		VendAcknowlegmentResponseMessage message = response.getVendAcknowlegmentResponse();
		if (vendAcknowlegmentRequest.getVendAcknowlegmentRequest().getTransactionId().equals(
				response.getVendAcknowlegmentResponse().getTransactionId())) {
			logger.info(Logger.EVENT_UNSPECIFIED,"matching transaction id");
			status = message.getReasonCode().intValue();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processApplyVendAcknowledgementResponse method");
		return status;
	}


	/**
	 * @param jobDetails
	 * @return
	 */
	public final VendAcknowlegmentRequest sendVendAcknowledgementRequest(VendAckJobDetails jobDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendVendAcknowledgementRequest method");
		VendAcknowlegmentRequest vendAcknowlegmentRequest = new VendAcknowlegmentRequest();
		VendAcknowlegmentRequestMessage request = new VendAcknowlegmentRequestMessage();
		request.setTransactionId(jobDetails.getTransactionID());
		request.setPAN(jobDetails.getPan());
		request.setVendCode(jobDetails.getVendCode());
		request.setCreditValue(jobDetails.getCreditValue());
		Calendar cal = Calendar.getInstance();
		cal.setTime(jobDetails.getTimestamp());
		request.setDate(cal);
		request.setMSN(jobDetails.getMsn());
		request.setStatus(String.valueOf(jobDetails.getStatus()));
		vendAcknowlegmentRequest.setVendAcknowlegmentRequest(request);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVendAcknowledgementRequest method");
		return vendAcknowlegmentRequest;
	}


	public QueryHeadendMastershipRequest_MT buildLookUpServiceRequest(
			LookUpParam paramName, String paramValue, ServiceId serviceId) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering buildLookUpServiceRequest method");
		QueryHeadendMastershipRequest_MT request = new QueryHeadendMastershipRequest_MT();
		QueryHeadendMastershipRequest queryHeadendMastershipRequest = new QueryHeadendMastershipRequest();
		HLUParameters_type0 hluParam = new HLUParameters_type0();
		hluParam.setConsumer(SERVICE_CONSUMER);
		hluParam.setParameterName(paramName.name());
		hluParam.setParameterValue(paramValue);
		hluParam.setServiceId(serviceId.getServiceCode());
		queryHeadendMastershipRequest.setHLUParameters(hluParam);
		request.setQueryHeadendMastershipRequest_MT(queryHeadendMastershipRequest);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving buildLookUpServiceRequest method");
		return request;
	}


	public void mapLookUpResponse(QueryHeadendMastershipResponse_MT queryHeadendMastershipDetailsOut, ServiceId serviceId, VendServiceDetails details) throws NamingException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering mapLookUpResponse method");
		String provider = queryHeadendMastershipDetailsOut.getQueryHeadendMastershipResponse_MT().getProvider();
		if (serviceId.isAmi()) {
			logger.error(Logger.EVENT_SUCCESS,"AMI Server Mapping -- In progress");
			QueryServiceDetails queryServiceDetails = queryPhase3DeviceDetails(provider);
			details.setUrl(buildURL(details, queryServiceDetails.getHostNameAmi(), queryServiceDetails.getPortNumberAmi()));
			details.setUserName(queryServiceDetails.getUsernameAmi());
			details.setPassword(queryServiceDetails.getPasswordAmi());
			details.setHesLabel(queryServiceDetails.getLabel());
		} else {
			logger.error(Logger.EVENT_SUCCESS,"Unity Server Mapping -- In progress");
			QueryServiceDetails queryServiceDetails = queryPhase3DeviceDetails(provider);
			details.setUrl(buildURL(details, queryServiceDetails.getHostName(), queryServiceDetails.getPortNumber()));
			details.setUserName(queryServiceDetails.getUsername());
			details.setPassword(queryServiceDetails.getPassword());
			details.setHesLabel(queryServiceDetails.getLabel());
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving mapLookUpResponse method");
	}


	private String buildURL(VendServiceDetails vendServiceDetails, String hostName, String portNumber) {
		String url = vendServiceDetails.getUrl();
		url = StringUtils.replace(url,"HES_IP", hostName);
		url = StringUtils.replace(url, "HES_PORT", portNumber);
		return url;
	}
	
	private QueryServiceDetails queryPhase3DeviceDetails(String providerName) throws NamingException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering queryPhase3DeviceDetails method");
		VMSUtils vmsUtils = new VMSUtils();
		QueryServiceDetails queryServiceDetails = (QueryServiceDetails) vmsUtils.getVendServiceDetails(DeviceTypeConstants.QUERY_SERVICE);
		String[] hostNames =  queryServiceDetails.getHostName().split(queryServiceDetails.getSeparator());
		String[] portNumbers =  queryServiceDetails.getPortNumber().split(queryServiceDetails.getSeparator());
		String[] usernames = queryServiceDetails.getUsername().split(queryServiceDetails.getSeparator());
		String[] passwords = queryServiceDetails.getPassword().split(queryServiceDetails.getSeparator());
		String[] timeouts = queryServiceDetails.getTimeout().split(queryServiceDetails.getSeparator());
		String[] hostNamesAmi =  queryServiceDetails.getHostNameAmi().split(queryServiceDetails.getSeparator());
		String[] portNumbersAmi =  queryServiceDetails.getPortNumberAmi().split(queryServiceDetails.getSeparator());
		String[] usernamesAmi = queryServiceDetails.getUsernameAmi().split(queryServiceDetails.getSeparator());
		String[] passwordsAmi = queryServiceDetails.getPasswordAmi().split(queryServiceDetails.getSeparator());
		String[] labels = queryServiceDetails.getLabel().split(queryServiceDetails.getSeparator());
		for (int index = 0; index < hostNames.length; index++) {
			QueryServiceDetails queryServiceDetail = new QueryServiceDetails();
				queryServiceDetail.setHostName(hostNames[index]);
				queryServiceDetail.setPortNumber(portNumbers[index]);
				queryServiceDetail.setUsername(usernames[index]);
				queryServiceDetail.setPassword(passwords[index]);
				queryServiceDetail.setTimeout(timeouts[index]);
				queryServiceDetail.setHostNameAmi(hostNamesAmi[index]);
				queryServiceDetail.setPortNumberAmi(portNumbersAmi[index]);
				queryServiceDetail.setUsernameAmi(usernamesAmi[index]);
				queryServiceDetail.setPasswordAmi(passwordsAmi[index]);
				queryServiceDetail.setLabel(labels[index]);
				if (providerMatch(providerName, queryServiceDetail)) {
					logger.debug(Logger.EVENT_SUCCESS,"Leaving queryPhase3DeviceDetails method");
					logger.error(Logger.EVENT_SUCCESS,"Successfully mapped a HES in VMS: " + queryServiceDetail.toString());
					return queryServiceDetail;
				}
			}
		
		logger.error(Logger.EVENT_SUCCESS,"Failed to map a HES in VMS context");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving queryPhase3DeviceDetails method");
		return new QueryServiceDetails();
	}
	
		
		private boolean providerMatch(String providerName,
			QueryServiceDetails queryServiceDetail) {
		return providerName.equalsIgnoreCase(queryServiceDetail.getLabel());
	}

	public void storeHeadEnd(String transactionId, String hesLabel,
			ServiceId serviceId) {
		logger.debug(Logger.EVENT_SUCCESS, "Entering storeHeadEnd method");
		try {
			WSTransactionDAO dao = new WSTransactionDAO();
			if (serviceId == ServiceId.UPDATE_PPKEY) {
				PPKeyTransaction ppKeyTxnDetails = dao
						.getPPKeyTxnDetails(transactionId);
				if (ppKeyTxnDetails != null && isUpdateRequired(ppKeyTxnDetails.getHeadend(), hesLabel)) {
					ppKeyTxnDetails.setHeadend(hesLabel);
					dao.update(ppKeyTxnDetails);
				}
			} else {
				VendTxnWSDetails vendTxnWSDetails = dao
						.getVendTxnWSDetails(transactionId);
				if (vendTxnWSDetails != null && isUpdateRequired(vendTxnWSDetails.getHeadend(), hesLabel)) {
					vendTxnWSDetails.setHeadend(hesLabel);
					dao.update(vendTxnWSDetails);
				}
			}

		} catch (Exception e) {
			logger.error(Logger.EVENT_FAILURE,
					"Error occurred while attempting to store HES details in DB: "
							+ e.getMessage());
		}

		logger.debug(Logger.EVENT_SUCCESS, "Leaving storeHeadEnd method");
	}

		private boolean isUpdateRequired(String headendFromDB, String hesLabel) {
			return StringUtils.isBlank(headendFromDB) || !StringUtils.equalsIgnoreCase(headendFromDB, hesLabel);
		}
	
}
