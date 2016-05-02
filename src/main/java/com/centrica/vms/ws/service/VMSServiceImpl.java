/**
 * VMSServiceImpl.java
 * Purpose :  Class invoked from the web service skeleton class
 * @author ramasap1
 * @version 1.0
 */
package com.centrica.vms.ws.service;
 


import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import smartmeterprocessing.enterprise.uk.co.britishgas.VendCreditType_type1;

import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.EPaymentKeyInvalidException;
import com.centrica.vms.exception.EPaymentKeyOutOfRangeException;
import com.centrica.vms.exception.LGLicenceException;
import com.centrica.vms.exception.UnknownBreakupException;
import com.centrica.vms.exception.VMSAppException;
import com.centrica.vms.exception.VMSException;
import com.centrica.vms.exception.VMSInvalidPANException;
import com.centrica.vms.vh.ws.sap.service.GetVendHistory;
import com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceipt;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceiptResponse;
import com.centrica.vms.ws.headend.ack.service.VMSHeadendService;
import com.centrica.vms.ws.pi.service.AsynActivationandDeactivation;
import com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDelivery;
import com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDeliveryResponse;
import com.centrica.vms.ws.sap.service.CreateVend;
import com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDelivery;
import com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDeliveryResponse;import com.centrica.vms.ws.pi.service.AsynActivationandDeactivation;import com.centrica.vms.ws.sap.service.CreateVend;
import com.centrica.vms.ws.sap.service.CreateVendResponse;
import com.centrica.vms.ws.sap.service.ReverseVend;
import com.centrica.vms.ws.sap.service.ReverseVendResponse;
import com.centrica.vms.ws.sap.service.VMSSAPService;
import com.centrica.vms.ws.sap.service.VoidVend;
import com.centrica.vms.ws.sap.service.VoidVendResponse;


public class VMSServiceImpl implements VMSSAPService, VMSHeadendService {

	private Logger logger = ESAPI.getLogger(getClass().getName());
 
	private String createdBy = "system";
	
	private String updatedBy = "system";
	
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.centrica.vms.ws.sap.service.VMSSAPService#generatePaymentCode(com
	 * .centrica.vms.ws.sap.service.CreateVend)
	 */
	public CreateVendResponse generatePaymentCode(CreateVend createRequest) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering generatePaymentCode method");
		CreateVendResponse createVendResponse = new CreateVendResponse();
		IVMSPaymentService paymentType;
		try {
			VendCreditType_type1 creditType = createRequest.getPaymentRequest().getVendAdditional().getVendCreditType();
			if(creditType == VendCreditType_type1.PURCHASE){
				paymentType = new VMSPurchasePaymentService();
			}else{
				paymentType = new VMSAdjPaymentService();
			}
			createVendResponse = paymentType.handlePaymentRequest(createRequest,this.createdBy,this.updatedBy);
		} catch(VMSException ex){
			logger.error(Logger.EVENT_FAILURE,"VMS Exception is thrown" + ex);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving generatePaymentCode method ");
		return createVendResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.centrica.vms.ws.sap.service.VMSSAPService#reversePayment(com.centrica
	 * .vms.ws.sap.service.ReverseVend)
	 */
	public ReverseVendResponse reversePayment(ReverseVend reverseVend) { 
		logger.debug(Logger.EVENT_SUCCESS,"Entering reversePayment method");
		VMSReversalServiceFactory vmsReversalServiceFactory = new VMSReversalServiceFactory();
		VMSReversalService vmsReversalService = vmsReversalServiceFactory.fetchReversalService(reverseVend.getReverseTxnRequest().getOriginalVendRequestKey().getVendRequestIdentifier().toString());
		if (vmsReversalService == null && vmsReversalServiceFactory.getReversalFaultResponse() != null) {
			return vmsReversalServiceFactory.getReversalFaultResponse();
		}
		ReverseVendResponse reverseTxnResponse = vmsReversalService.handleReversalRequest(reverseVend);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving reversePayment method");
		return reverseTxnResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.centrica.vms.ws.sap.service.VMSSAPService#voidPayment(com.centrica
	 * .vms.ws.sap.service.VoidPayment)
	 */
	public VoidVendResponse voidPayment(VoidVend voidRequest) { 
		logger.debug(Logger.EVENT_SUCCESS,"Entering voidPayment method");
		VMSVoidService vmsVoidService = new VMSVoidService();
		VoidVendResponse voidVendResponse = vmsVoidService.handleVoidRequest(voidRequest);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving voidPayment method");
		return voidVendResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.centrica.vms.ws.headend.service.VMSHeadendService#
	 * acknowledgePaymentDelivery
	 * (com.centrica.vms.ws.headend.model.DeliveryReceipt)
	 */
	@Override
	public DeliveryReceiptResponse acknowledgePaymentDelivery(DeliveryReceipt deliveryReceipt) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering acknowledgePaymentDelivery method");
		VMSPhase3AckService vmsAckService = new VMSPhase3AckService();
		DeliveryReceiptResponse deliveryReceiptResponse = null;
		try {
			deliveryReceiptResponse = vmsAckService.handleAckRequest(deliveryReceipt);
		} catch (EPaymentKeyInvalidException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"EPaymentKeyInvalidException::" + e.getMessage());
		} catch (EPaymentKeyOutOfRangeException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"EPaymentKeyOutOfRangeException::" + e.getMessage());
		} catch (UnknownBreakupException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"UnknownBreakupException::" + e.getMessage());
		} catch (VMSInvalidPANException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"VMSInvalidPANException::" + e.getMessage());
		} catch (LGLicenceException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"LGLicenceException::" + e.getMessage());
		} catch (VMSAppException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"VMSAppException::" + e.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving acknowledgePaymentDelivery method ");
		return deliveryReceiptResponse;
	}
	
	

	
	
	/* (non-Javadoc)
	 * @see com.centrica.vms.ws.sap.service.VMSSAPService#acknowledgeAdjustResponse(com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDelivery)
	 */
	public AcknowledgePaymentDeliveryResponse acknowledgeAdjustResponse(AcknowledgePaymentDelivery acknowledgePaymentDelivery0) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering acknowledgeAdjustResponse method ");
		AcknowledgePaymentDeliveryResponse acknowledgePaymentDeliveryResponse = null;
		VMSAdjustmentAckService vmsAdjustmentAckService = new VMSAdjustmentAckService();
		try {
			acknowledgePaymentDeliveryResponse = vmsAdjustmentAckService.handleAckRequest(acknowledgePaymentDelivery0);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"DBEception:" + e.getMessage());
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving acknowledgeAdjustResponse method ");
		return acknowledgePaymentDeliveryResponse;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.ws.sap.service.VMSSAPService#getVendHistory(com.centrica.vms.vh.ws.sap.service.GetVendHistory)
	 */
	@Override
	public GetVendHistoryResponse getVendHistory(GetVendHistory vendHistoryRequest) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendHistory method");
		VMSVHService vmsVHService = new VMSVHService();
		GetVendHistoryResponse getVendHistoryResponse = vmsVHService.getVendHistory(vendHistoryRequest);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendHistory method ");
		return getVendHistoryResponse;
	}
	
	/* (non-Javadoc)
	 * @see com.centrica.vms.ws.sap.service.VMSSAPService#handleSCNRegistrationService(com.centrica.vms.scn.ws.sap.service.AsynActivationandDeactivation)
	 */
	@Override
	public void handleSCNRegistrationService(AsynActivationandDeactivation asynActivationandDeactivation) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering handleSCNRegistrationService method");
		VMSSCNService vmsSCNService = new VMSSCNService();
		vmsSCNService.handleSCNRegistration(asynActivationandDeactivation);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving handleSCNRegistrationService method ");
	}
}
