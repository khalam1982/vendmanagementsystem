/**
 * HeadendApplyVendService.java
 * Purpose: Head End apply vend service
 * @author ramasap1
 */
package com.centrica.vms.scheduler.external.service;

import java.rmi.RemoteException;

import javax.naming.NamingException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;

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
/**
 * Method for Head End apply vend service
 * @see HeadendCommonService
 */
public class HeadendApplyVendService extends HeadendCommonService{
	
	private Logger logger = ESAPI.getLogger(getClass().getName());
	/*
	 * Method to send the vend code
	 * @param JobDetails
	 * @return int
	 */
	public int sendVendCode(JobDetails jobDetails){
		int status = 0;
		VendServiceDetails vendServiceDetails = null;
		try{
			if (jobDetails.getDeviceType() == DeviceTypeEnum.PH2B.getDeviceType()) {
				vendServiceDetails = (VendServiceDetails) new VMSUtils().getVendServiceDetails(DeviceTypeConstants.DEVICE_TYPE_PHASE_2B);	
			} else {
				vendServiceDetails = (VendServiceDetails) new VMSUtils().getVendServiceDetails(DeviceTypeConstants.DEVICE_TYPE_PHASE_3);
			
				vendServiceDetails = new SAPService().lookUp(LookUpParam.PCN, jobDetails.getPan(), ServiceId.APPLY_VEND_CODE, vendServiceDetails, jobDetails.getTransactionID());
			}
			
			String url=vendServiceDetails.getUrl();
			logger.info(Logger.EVENT_UNSPECIFIED,"Headend URL "+url);
			UnityServiceStub unityServiceStub = prepareUnityServiceStub(vendServiceDetails, url);
			LoginResponse loginResponse = loginService(unityServiceStub,vendServiceDetails);
			unityServiceStub = prepareSessionHeader(loginResponse,unityServiceStub);
			status = sendApplyVendCodeRequest(jobDetails, unityServiceStub);
			logoutService(unityServiceStub,loginResponse.getResult().getSessionId());
		}catch(LoginFault ex){
			logger.error(Logger.EVENT_FAILURE,"Login Fault Exception"+ex.getMessage());
		}catch(AccessDeniedFault ex){
			logger.error(Logger.EVENT_FAILURE,"Access denied Fault Exception"+ex.getMessage());
		}catch(UnexpectedErrorFault ex){
			logger.error(Logger.EVENT_FAILURE,"Access denied Fault Exception"+ex.getMessage());
		}catch(ADBException ex){
			logger.error(Logger.EVENT_FAILURE,"ABD Exception"+ex.getMessage());
		}catch(AxisFault ex){
			logger.error(Logger.EVENT_FAILURE,"Axis Fault Exception"+ex.getMessage());
			status = 408; //Request failed exception
		}catch(RemoteException ex){
			logger.error(Logger.EVENT_FAILURE,"Remote Exception"+ex.getMessage());
		}catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"Naming Exception"+ex.getMessage()); 
		} catch (HESLookUpFailedException e) {
			logger.error(Logger.EVENT_FAILURE,"HESLookUpFailedException"+e.getMessage());
			status = 408;
		}
		return status;
	}
	
	/**
	 * Method used to prepare and send the apply vend code request to the headend 
	 * @param JobDetails
	 * @param UnityServiceStub
	 * @return int
	 * @throws RemoteException
	 * @throws UnexpectedErrorFault
	 * @throws AccessDeniedFault
	 * @throws LoginFault
	 */
	private int sendApplyVendCodeRequest(JobDetails jobDetails,
			UnityServiceStub unityServiceStub) throws RemoteException,
			UnexpectedErrorFault, AccessDeniedFault, LoginFault {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendApplyVendCodeRequest method");
		int status=0;
		ApplyVendCodeRequest applyVendCodeRequest = prepareApplyVendCodeRequest(jobDetails);
		ApplyVendCodeResponse  applyVendCodeResponse = unityServiceStub.applyVendCode(applyVendCodeRequest);
		status = processApplyVendCodeResponse(applyVendCodeResponse,applyVendCodeRequest);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendApplyVendCodeRequest method");
		return status;
	}
}
