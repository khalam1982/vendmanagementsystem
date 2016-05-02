/**
 * HeadendService.java
 * Purpose: Head end service
 * @author ramasap1
 */
package com.centrica.vms.scheduler.external.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.axis2.transport.http.HttpTransportProperties.Authenticator;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;

import trilliantamiservice.AMIInterfaceImplServiceStub;
import trilliantamiservice.AdjustCredit11;
import trilliantamiservice.AdjustCreditResponseE;

import com.centrica.vms.common.DeviceTypeConstants;
import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.HESLookUpFailedException;
import com.centrica.vms.model.LookUpParam;
import com.centrica.vms.model.ServiceId;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.model.JobDetails;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.ApplyVendCodeRequest;
import com.trilliantnetworks.unity.ws.ApplyVendCodeResponse;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.LoginResponse;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;
import com.trilliantnetworks.unity.ws.UnityServiceStub;
import com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyRequest;
import com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyResponse;

/**
 * Methods for Head end service
 * @see IHeadendService
 */
public class HeadendService implements IHeadendService {

	private Logger logger = ESAPI.getLogger(getClass().getName());
	private UnityServiceStub unityStub = null;
	private AMIInterfaceImplServiceStub amiServiceStub = null;
	private final HeadendCommonService headendCommonService;
	private final VMSUtils vmsUtils;
	
	/**
	 * Constructor
	 */
	public HeadendService() {
		headendCommonService = new HeadendCommonService();
		vmsUtils = new VMSUtils();
	}
	
	/**
	 * Constructor
	 * @param headendCommonService - HeadendCommonService
	 * @param vmsUtils - VMSUtils
	 */
	public HeadendService(final HeadendCommonService headendCommonService, final VMSUtils vmsUtils) {
		this.headendCommonService = headendCommonService;
		this.vmsUtils = vmsUtils;
	}

	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.scheduler.external.service.IHeadendService#sendVendCode(com.centrica.vms.scheduler.model.JobDetails)
	 */
	@Override
	public int sendVendCode(JobDetails jobDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendVendCode method");
		int status = 0;
		
		VendServiceDetails vendServiceDetails = null;
		try {
			if (jobDetails.getDeviceType() == DeviceTypeEnum.PH2B.getDeviceType()) {
				vendServiceDetails = (VendServiceDetails) vmsUtils.getVendServiceDetails(DeviceTypeConstants.DEVICE_TYPE_PHASE_2B);
			} else {
				vendServiceDetails = (VendServiceDetails) vmsUtils.getVendServiceDetails(DeviceTypeConstants.DEVICE_TYPE_PHASE_3);
				
				vendServiceDetails = new SAPService().lookUp(LookUpParam.PCN, jobDetails.getPan(), ServiceId.APPLY_VEND_CODE, vendServiceDetails, jobDetails.getTransactionID());
				
			}
			String url=vendServiceDetails.getUrl();
			logger.info(Logger.EVENT_UNSPECIFIED,"Headend URL "+url);
			unityStub = headendCommonService.prepareUnityServiceStub(vendServiceDetails, url);
			LoginResponse loginResponse = headendCommonService.loginService(unityStub,vendServiceDetails);
			unityStub = headendCommonService.prepareSessionHeader(loginResponse,unityStub);
			status = sendApplyVendCodeRequest(jobDetails);
			headendCommonService.logoutService(unityStub,loginResponse.getResult().getSessionId());
		}catch(LoginFault ex){
			logger.error(Logger.EVENT_FAILURE,"Login Fault Exception"+ex.getMessage());
			status = 408; // Incident no : INC02861846
		}catch(AccessDeniedFault ex){
			logger.error(Logger.EVENT_FAILURE,"Access denied Fault Exception"+ex.getMessage());
			status = 408; // Incident no : INC02861846
		}catch(UnexpectedErrorFault ex){
			logger.error(Logger.EVENT_FAILURE,"Access denied Fault Exception"+ex.getMessage());
			status = 408; // Incident no : INC02861846
		}catch(ADBException ex){
			logger.error(Logger.EVENT_FAILURE,"ABD Exception"+ex.getMessage());
			status = 408; // Incident no : INC02861846
		}catch(AxisFault ex){
			logger.error(Logger.EVENT_FAILURE,"Axis Fault Exception"+ex.getMessage());
			status = 408; //Request failed exception
		}catch(RemoteException ex){
			logger.error(Logger.EVENT_FAILURE,"Remote Exception"+ex.getMessage());
			status = 408; // Incident no : INC02861846
		}catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"Naming Exception"+ex.getMessage());
			status = 408; // Incident no : INC02861846
		} catch (HESLookUpFailedException e) {
			logger.error(Logger.EVENT_FAILURE,"HESLookUpFailedException"+e.getMessage());
			status = 408;
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVendCode method");
		return status;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.scheduler.external.service.IHeadendService#sendPPKey(com.centrica.vms.scheduler.model.JobDetails)
	 */
	@Override
	public int sendPPKey(JobDetails jobDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendPPKey method");
		int status = 0;
		
		try {
			VendServiceDetails vendServiceDetails = (VendServiceDetails) vmsUtils.getVendServiceDetails(DeviceTypeConstants.PPKKEY_HES_UPDATE);
			
			vendServiceDetails = new SAPService().lookUp(LookUpParam.SN, jobDetails.getMsn(), ServiceId.UPDATE_PPKEY, vendServiceDetails, jobDetails.getTransactionID());
			
			String url=vendServiceDetails.getUrl();
			logger.error(Logger.EVENT_FAILURE,"Headend PPKey Update URL "+url);
			//Login
			unityStub = headendCommonService.prepareUnityServiceStub(vendServiceDetails, url);
			LoginResponse loginResponse = headendCommonService.loginService(unityStub,vendServiceDetails);
			unityStub = headendCommonService.prepareSessionHeader(loginResponse,unityStub);
			//Send the PPKey
			status = sendUpdatePPKeyRequest(jobDetails);
			//Logout
			headendCommonService.logoutService(unityStub,loginResponse.getResult().getSessionId());
			
		}catch(LoginFault ex){
			logger.error(Logger.EVENT_FAILURE,"Login Fault Exception"+ex.getMessage());
			status = 408; 
		}catch(AccessDeniedFault ex){
			logger.error(Logger.EVENT_FAILURE,"Access denied Fault Exception"+ex.getMessage());
			status = 408; 
		}catch(UnexpectedErrorFault ex){
			logger.error(Logger.EVENT_FAILURE,"Access denied Fault Exception"+ex.getMessage());
			status = 408; 
		}catch(ADBException ex){
			logger.error(Logger.EVENT_FAILURE,"ABD Exception"+ex.getMessage());
			status = 408; 
		}catch(AxisFault ex){
			logger.error(Logger.EVENT_FAILURE,"Axis Fault Exception"+ex.getMessage());
			status = 408; 
		}catch(RemoteException ex){
			logger.error(Logger.EVENT_FAILURE,"Remote Exception"+ex.getMessage());
			status = 408; 
		}catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"Naming Exception"+ex.getMessage());
			status = 408; 
		} catch (HESLookUpFailedException e) {
			logger.error(Logger.EVENT_FAILURE,"HESLookUpFailedException"+e.getMessage());
			status = 408; 
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendPPKey method");
		return status;
	}
	
	

	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.scheduler.external.service.IHeadendService#sendVendCode(com.centrica.vms.scheduler.model.JobDetails)
	 */
	@Override
	public int sendAdjustCredit(JobDetails jobDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendAdjustCredit method");
		int status = 0;
		VendServiceDetails vendServiceDetails = null;
		try {
			vendServiceDetails = (VendServiceDetails) new VMSUtils().
				getVendServiceDetails(DeviceTypeConstants.DEVICE_TYPE_CIM_PHASE_3);
			
			vendServiceDetails = new SAPService().lookUp(LookUpParam.MPXN, jobDetails.getMpxn(), ServiceId.CREDIT_ADJUSTMENT, vendServiceDetails, jobDetails.getTransactionID());
			
			String url=vendServiceDetails.getUrl();
			logger.info(Logger.EVENT_UNSPECIFIED,"Headend URL "+url);
			HttpTransportProperties.Authenticator basicAuthentication = new HttpTransportProperties.Authenticator();
			basicAuthentication.setUsername(vendServiceDetails.getUserName());
			basicAuthentication.setPassword(vendServiceDetails.getPassword());
			List<String> authSchemes = new ArrayList<String>();
			authSchemes.add(Authenticator.BASIC);
			basicAuthentication.setAuthSchemes(authSchemes);
			amiServiceStub = new AMIInterfaceImplServiceStub(url);
			amiServiceStub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, basicAuthentication);
			amiServiceStub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, "false");
			status = sendApplyAdjustCreditRequest(jobDetails);
		}catch(LoginFault ex){
			logger.error(Logger.EVENT_FAILURE,"Login Fault Exception"+ex.getMessage());
			status = 408; // Incident no : INC02861846
		}catch(AccessDeniedFault ex){
			logger.error(Logger.EVENT_FAILURE,"Access denied Fault Exception"+ex.getMessage());
			status = 408; // Incident no : INC02861846
		}catch(UnexpectedErrorFault ex){
			logger.error(Logger.EVENT_FAILURE,"Access denied Fault Exception"+ex.getMessage());
			status = 408; // Incident no : INC02861846
		}catch(AxisFault ex){
			logger.error(Logger.EVENT_FAILURE,"Axis Fault Exception"+ex.getMessage());
			status = 408; //Request failed exception
		}catch(RemoteException ex){
			logger.error(Logger.EVENT_FAILURE,"Remote Exception"+ex.getMessage());
			status = 408; // Incident no : INC02861846
		}catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"Naming Exception"+ex.getMessage());
			status = 408; // Incident no : INC02861846
		} catch (HESLookUpFailedException e) {
			logger.error(Logger.EVENT_FAILURE,"HESLookUpFailedException"+e.getMessage());
			status = 408; 
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendAdjustCredit method");
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
	public void prepareSOAPEnvelope(JobDetails jobDetails) 
		throws LoginFault, AccessDeniedFault, UnexpectedErrorFault, AxisFault, RemoteException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareSOAPEnvelope method");
		ApplyVendCodeRequest applyVendCodeRequest = headendCommonService.prepareApplyVendCodeRequest(jobDetails);
		unityStub.applyVendCode(applyVendCodeRequest);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareSOAPEnvelope method");
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
	public void preparePPKeySOAPEnvelope(JobDetails jobDetails) 
			throws LoginFault, AccessDeniedFault, UnexpectedErrorFault, AxisFault, RemoteException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering preparePPKeySOAPEnvelope method");
		UpdatePrepaymentKeyRequest updatePrepaymentKeyRequest = headendCommonService.prepareUpdatePPKeyRequest(jobDetails);
		unityStub.updatePrepaymentKey(updatePrepaymentKeyRequest);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving preparePPKeySOAPEnvelope method");
	}
	
	/**
	 * Method to get the SOAP Envelope for re-sending it through an utility
	 * @param 
	 * @return org.apache.axiom.soap.SOAPEnvelope
	 */
	public SOAPEnvelope getSOAPEnvelope() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getSOAPEnvelope method");
		SOAPEnvelope env = null;
		if (unityStub != null) {
			env = unityStub._getServiceClient().getLastOperationContext().
				getMessageContexts().get("Out").getEnvelope();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getSOAPEnvelope method");
		return env;
	}
	
	/**
	 * Method to get the SOAP Envelope (new CIM based) for re-sending it through an utility
	 * @param 
	 * @return org.apache.axiom.soap.SOAPEnvelope
	 */
	public SOAPEnvelope getAdjustSOAPEnvelope() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getAdjustSOAPEnvelope method");
		SOAPEnvelope env = null;
		if (amiServiceStub != null) {
			env = amiServiceStub._getServiceClient().getLastOperationContext().
				getMessageContexts().get("Out").getEnvelope();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getAdjustSOAPEnvelope method");
		return env;
	}
	
	/**
	 * Method used to prepare and send the apply vend code request to the headend 
	 * @param JobDetails
	 * @return int
	 * @throws RemoteException
	 * @throws UnexpectedErrorFault
	 * @throws AccessDeniedFault
	 * @throws LoginFault
	 */
	private int sendApplyVendCodeRequest(JobDetails jobDetails) throws RemoteException,
			UnexpectedErrorFault, AccessDeniedFault, LoginFault {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendApplyVendCodeRequest method");
		int status=0;
		ApplyVendCodeRequest applyVendCodeRequest = headendCommonService.prepareApplyVendCodeRequest(jobDetails);
		ApplyVendCodeResponse  applyVendCodeResponse = unityStub.applyVendCode(applyVendCodeRequest);
		status = headendCommonService.processApplyVendCodeResponse(applyVendCodeResponse,applyVendCodeRequest);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendApplyVendCodeRequest method");
		return status;
	}
	
	/**
	 * Prepare and sends the PrepaymentKey to Headend. Captures the maps the response
	 * @param jobDetails
	 * @return status
	 * @throws RemoteException
	 * @throws UnexpectedErrorFault
	 * @throws AccessDeniedFault
	 * @throws LoginFault
	 */
	private int sendUpdatePPKeyRequest(JobDetails jobDetails) throws RemoteException,
	UnexpectedErrorFault, AccessDeniedFault, LoginFault {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendUpdatePPKeyRequest method");
		UpdatePrepaymentKeyRequest updatePrepaymentKeyRequest = headendCommonService.prepareUpdatePPKeyRequest(jobDetails);
		UpdatePrepaymentKeyResponse  updatePrepaymentKeyResponse = unityStub.updatePrepaymentKey(updatePrepaymentKeyRequest);
		int status = headendCommonService.processUpdatePrepaymentKeyResponse(updatePrepaymentKeyRequest,updatePrepaymentKeyResponse);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendUpdatePPKeyRequest method");
		return status;
	}
	
	/**
	 * Method used to prepare and send the apply adjust credit request to the headend 
	 * @param JobDetails
	 * @return int
	 * @throws RemoteException
	 * @throws UnexpectedErrorFault
	 * @throws AccessDeniedFault
	 * @throws LoginFault
	 */
	private int sendApplyAdjustCreditRequest(JobDetails jobDetails) throws RemoteException,
			UnexpectedErrorFault, AccessDeniedFault, LoginFault {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendApplyAdjustCreditRequest method");
		int status=0;
		HeadendCommonService headendCommonService = new HeadendCommonService();
		AdjustCredit11 adjustCredit14 = headendCommonService.prepareApplyAdjustCreditRequest(jobDetails);
		AdjustCreditResponseE  adjustCreditResponse = amiServiceStub.adjustCredit(adjustCredit14);
		status = headendCommonService.processApplyAdjustCreditResponse(adjustCreditResponse,adjustCredit14);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendApplyAdjustCreditRequest method");
		return status;
	}

	
}
