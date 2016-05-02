/**
 * SAPService.java
 * Purpose: SAP service for handling VMS to SAP requests
 * @author nagarajk
 */
package com.centrica.vms.scheduler.external.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.axis2.transport.http.HttpTransportProperties.Authenticator;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;

import smartmeterprocessing.trilliant.uk.co.britishgas.QueryHeadendMastershipDetailsOutServiceStub;
import smartmeterprocessing.trilliant.uk.co.britishgas.QueryHeadendMastershipDetailsOutServiceStub.QueryHeadendMastershipRequest_MT;
import smartmeterprocessing.trilliant.uk.co.britishgas.QueryHeadendMastershipDetailsOutServiceStub.QueryHeadendMastershipResponse_MT;
import smartmeterprocessing.vend.uk.co.britishgas.SmartMeterProcessingActionVendAcknowledgment_OutServiceStub;
import smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentRequest;
import smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponse;

import com.centrica.vms.common.DeviceTypeConstants;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.HESLookUpFailedException;
import com.centrica.vms.model.LookUpParam;
import com.centrica.vms.model.LookUpServiceDetails;
import com.centrica.vms.model.ServiceId;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.scheduler.external.model.VendAckSAPServiceDetails;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.model.VendAckJobDetails;
import com.centrica.vms.scheduler.service.VMSSchedulerService;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;

/**
 * Methods for Head end service
 * @see IHeadendService
 */
public class SAPService implements ISAPService {

	private static final String VEND_ACK_SAP_SERVICE = "VendAckSAPService";
	private Logger logger = ESAPI.getLogger(getClass().getName());
	private SmartMeterProcessingActionVendAcknowledgment_OutServiceStub ackOutStub = null;
	private final SAPCommonService sapCommonService = new SAPCommonService();

	
	/**
	 * @param transactionID
	 * @throws DBException
	 */
	public void sendAcknowledgementToSAP(final String transactionID) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendAcknowledgementToSAP method");
		VMSSchedulerService vmsSchedulerService = new VMSSchedulerServiceImpl();
		WSTransactionDAO wsTransactionDAO = new WSTransactionDAO();
		VendTxnWSDetails vendTxnWSDetails = wsTransactionDAO.getVendTxnWSDetails(transactionID);
		VendTxnStatus vendTxnStatus = null;
		Iterator<VendTxnStatus> iterator = vendTxnWSDetails.getTxnStatusDetails().iterator();
		
		//TODO: check this logic is correct
		if (iterator != null) {
			while(iterator.hasNext()) {
				VendTxnStatus status = (VendTxnStatus) iterator.next();
				if (vendTxnStatus == null || status.getUpdatedTimeStamp().after(vendTxnStatus.getUpdatedTimeStamp())) {
					vendTxnStatus = status;
				}
			}
		}
		
		if (vendTxnStatus != null) {
			vmsSchedulerService.scheduleVendAcknowledgeSAPJob(
					0, transactionID, new Long(0), vendTxnWSDetails.getPan(), vendTxnWSDetails.getMsn(), 
					vendTxnWSDetails.getVendCode(), vendTxnWSDetails.getCreditValue(), 
					vendTxnStatus.getUpdatedTimeStamp(), vendTxnStatus.getStatus(), Boolean.FALSE);	
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendAcknowledgementToSAP method");
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.scheduler.external.service.IHeadendService#sendVendCode(com.centrica.vms.scheduler.model.JobDetails)
	 */
	@Override
	public int sendVendAcknowledgement(VendAckJobDetails jobDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendVendAcknowledgement method");
		int status = 0;
		
		VendAcknowlegmentResponse response = null;
		VendAckSAPServiceDetails vendAckSAPService;
		
		try {
			vendAckSAPService = (VendAckSAPServiceDetails) new VMSUtils().
				getVendServiceDetails(VEND_ACK_SAP_SERVICE);	
			ackOutStub = new SmartMeterProcessingActionVendAcknowledgment_OutServiceStub(
					vendAckSAPService.getUrl());
			HttpTransportProperties.Authenticator basicAuthentication = 
				new HttpTransportProperties.Authenticator();
			basicAuthentication.setUsername(vendAckSAPService.getUserName());
			basicAuthentication.setPassword(vendAckSAPService.getPassword());
			List<String> authSchemes = new ArrayList<String>();
			authSchemes.add(Authenticator.BASIC);
			basicAuthentication.setAuthSchemes(authSchemes);
			ackOutStub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, basicAuthentication);
			ackOutStub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, "false");
			VendAcknowlegmentRequest vendAcknowlegmentRequest = 
				sapCommonService.sendVendAcknowledgementRequest(jobDetails);
			response = ackOutStub
					.VMS_VendAcknowledgment_Sync_Out(vendAcknowlegmentRequest);
			status = sapCommonService.processApplyVendAcknowledgementResponse(
					vendAcknowlegmentRequest, response);
		} catch (NamingException ex) {
			logger.error(Logger.EVENT_FAILURE,"Naming Exception" + ex.getMessage());
			status = 408;
		} catch (AxisFault ex) {
			logger.error(Logger.EVENT_FAILURE,"Axis Fault Exception" + ex.getMessage());
			status = 408; // Request failed exception
		} catch (RemoteException ex) {
			logger.error(Logger.EVENT_FAILURE,"Remote Exception" + ex.getMessage());			
			status = 408; 
		}		
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVendAcknowledgement method");
		return status;
	}


	/**
	 * Method to prepare the SOAP Envelope for re-sending it through an utility
	 * @param JobDetails
	 * @return
	 * @throws LoginFault
	 * @throws AccessDeniedFault
	 * @throws UnexpectedErrorFault
	 * @throws AxisFault
	 * @throws RemoteException
	 */
	public void prepareSOAPEnvelope(VendAckJobDetails jobDetails) 
		throws LoginFault, AccessDeniedFault, UnexpectedErrorFault, AxisFault, RemoteException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareSOAPEnvelope method");
		VendAcknowlegmentRequest vendAcknowlegmentRequest = 
			sapCommonService.sendVendAcknowledgementRequest(jobDetails);
		ackOutStub.VMS_VendAcknowledgment_Sync_Out(vendAcknowlegmentRequest);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareSOAPEnvelope method");
	}
	
	/**
	 * Method to get the SOAP Envelope for re-sending it through an utility
	 * @param 
	 * @return org.apache.axiom.soap.SOAPEnvelope
	 */
	public SOAPEnvelope getSOAPEnvelope() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getSOAPEnvelope method");
		SOAPEnvelope env = null;
		if (ackOutStub != null) {
			env = ackOutStub._getServiceClient().getLastOperationContext().
				getMessageContexts().get("Out").getEnvelope();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getSOAPEnvelope method");
		return env;
	}
	
	public VendServiceDetails lookUp(LookUpParam paramName, String paramValue, ServiceId serviceId, VendServiceDetails details, String transactionId) throws HESLookUpFailedException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering lookUp method");
		VMSUtils utils = new VMSUtils();
		VendServiceDetails vendServiceDetails = utils.clone(details);
		try {
			LookUpServiceDetails lookUpServiceDetails = (LookUpServiceDetails) utils.getVendServiceDetails(DeviceTypeConstants.LOOKUP_SERVICE);
			
			QueryHeadendMastershipDetailsOutServiceStub lookUpServiceStub = new QueryHeadendMastershipDetailsOutServiceStub(lookUpServiceDetails.getUrl());
			HttpTransportProperties.Authenticator basicAuthentication = 
					new HttpTransportProperties.Authenticator();
				basicAuthentication.setUsername(lookUpServiceDetails.getUsername());
				basicAuthentication.setPassword(lookUpServiceDetails.getPassword());
				List<String> authSchemes = new ArrayList<String>();
				authSchemes.add(Authenticator.BASIC);
				basicAuthentication.setAuthSchemes(authSchemes);
				lookUpServiceStub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, basicAuthentication);
				lookUpServiceStub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, "false");
				lookUpServiceStub._getServiceClient().getOptions().setTimeOutInMilliSeconds(Long.parseLong(lookUpServiceDetails.getTimeout()));
				QueryHeadendMastershipRequest_MT queryHeadendMastershipRequest_MT0 = sapCommonService.buildLookUpServiceRequest(paramName, paramValue, serviceId);
				QueryHeadendMastershipResponse_MT queryHeadendMastershipDetailsOut = lookUpServiceStub.QueryHeadendMastershipDetailsOut(queryHeadendMastershipRequest_MT0);
				sapCommonService.mapLookUpResponse(queryHeadendMastershipDetailsOut, serviceId, vendServiceDetails);
				sapCommonService.storeHeadEnd(transactionId, vendServiceDetails.getHesLabel(), serviceId);
				
		} catch (AxisFault e) {
			logger.error(Logger.EVENT_FAILURE,"Axis Fault" + e.getMessage());
			throw new HESLookUpFailedException();
		} catch (NamingException e) {
			logger.error(Logger.EVENT_FAILURE,"Naming Exception" + e.getMessage());
			throw new HESLookUpFailedException();
		} catch (Exception e) {
			logger.error(Logger.EVENT_FAILURE,"Exception" + e.getMessage());	
			throw new HESLookUpFailedException();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving lookUp method");
		return vendServiceDetails;
	}
	
}
