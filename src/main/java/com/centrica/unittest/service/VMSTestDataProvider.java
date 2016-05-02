package com.centrica.unittest.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import meter.enterprise.uk.co.britishgas.MeteringAssetBasic;

import org.apache.axis2.databinding.types.Token;

import smartmeterprocessing.enterprise.uk.co.britishgas.CreateVendResponseMessage;
import smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendCode;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcome;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestTimestamp;
import smartmeterprocessing.enterprise.uk.co.britishgas.VoidVendRequestMessage;
import smartmeterprocessing.enterprise.uk.co.britishgas.VoidVendRequestResponseMessage;
import smartmetervendhistory.enterprise.uk.co.britishgas.VendHistoryRequestMessage;
import vhcommon.enterprise.uk.co.britishgas.IntegerType;
import vhcommon.enterprise.uk.co.britishgas.StringType;

import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.form.VendRequestDetailsForm;
import com.centrica.vms.form.VoidRequestDetailsForm;
import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.model.UserDetails;
import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.vh.ws.sap.service.GetVendHistory;
import com.centrica.vms.ws.headend.ack.model.DeliveryReceiptResponse;
import com.centrica.vms.ws.headend.ack.model.DeliveryStatusCode;
import com.centrica.vms.ws.model.CustomerDetails;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.PremiseDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;
import com.centrica.vms.ws.sap.service.CreateVendResponse;
import com.centrica.vms.ws.sap.service.VoidVend;
import com.centrica.vms.ws.sap.service.VoidVendResponse;
import common.enterprise.uk.co.britishgas.BG_Identifier;
import common.enterprise.uk.co.britishgas.BG_MessageHeader;

public class VMSTestDataProvider {

	public static final String PAN = "123PSN57676577";
	public static final String TRANSACTION_ID = "T1234567890";
	public static final String TEST = "Test";
	public static final String MSN = "MSN345678890";
	public static final String MSN_DB = "3705852841";

	public static final String MPXN = "1065247789046";
	public static final String PP_KEY = "PP56578234MN5656MNS46565";
	public static final String CREDIT_VALUE = "1234";
	public static final String SOURCE_DESC = "Description";
	public static final String SOURCE = "PayPoint";
	public static final String SOURCE_ONE = "1-23456789";
	public static final String ORIG_TRANS_ID = "T22234567890";
	public static final String PAY_CHANNEL = "Online";
	public static final String STATUS = "200";
	public static final String VEND_DESC = "Vend Successful";
	public static final String VEND_CODE = "ISP1234567890";

	/**
	 * Constructs Meter Details
	 * @return MeterDetails
	 */
	public static MeterDetails constructMeterDetails() {

		final MeterDetails reqMeterDetails = new MeterDetails();
		reqMeterDetails.setDeviceTypeID(DeviceTypeEnum.PH3.getDeviceType());
		reqMeterDetails.setFuelTypeID(123);
		reqMeterDetails.setMsn(MSN);
		reqMeterDetails.setPan(PAN);
		reqMeterDetails.setPrepayKey(PP_KEY);
		return reqMeterDetails;

	}

	/**
	 * Constructs Vend History Details
	 * @return VendHistoryDetails
	 */
	public static VendHistoryDetails constructVendHistoryDetails() {

		final VendHistoryDetails historyDetails = new VendHistoryDetails();
		historyDetails.setCreditValue(CREDIT_VALUE);
		historyDetails.setHoldingPeriod(10l);
		historyDetails.setVendCode(VEND_CODE);
		historyDetails.setMsn(MSN);
		historyDetails.setOriginalTransactionID(ORIG_TRANS_ID);
		historyDetails.setPan(PAN);
		historyDetails.setSourceDetails(constructSourceDetails());
		historyDetails.setActualTimeStamp(new Date());
		historyDetails.setTransactionID(TRANSACTION_ID);
		historyDetails.setTransactionType(PAY_CHANNEL);
		historyDetails.setVendcodeTimeStamp(new Date());
		historyDetails.setCreatedTimeStamp(new Date());
		return historyDetails;

	}

	/**
	 * Constructs Source Details
	 * @return SourceDetails
	 */
	public static SourceDetails constructSourceDetails() {

		final SourceDetails source = new SourceDetails();
		source.setHoldingPeriod(10l);
		source.setSource(SOURCE);
		source.setSourceDescription(SOURCE_DESC);
		return source;

	}

	/**
	 * Constructs Vend Request Details Form
	 * @return VendRequestDetailsForm
	 */
	public static VendRequestDetailsForm constructVendRequestDetailsForm() {

		final VendRequestDetailsForm reqVendRequestDetailsForm = new VendRequestDetailsForm();
		reqVendRequestDetailsForm.setCreditType("M");
		reqVendRequestDetailsForm.setCreditValue(CREDIT_VALUE);
		reqVendRequestDetailsForm.setDateTime(new Date());
		reqVendRequestDetailsForm.setPan(PAN);
		reqVendRequestDetailsForm.setPayChannel(PAY_CHANNEL);
		reqVendRequestDetailsForm.setVendCode(VEND_CODE);
		reqVendRequestDetailsForm.setTransactionID(TRANSACTION_ID);
		reqVendRequestDetailsForm.setHoldingPeriod("23");
		return reqVendRequestDetailsForm;

	}

	/**
	 * Constructs Create Vend Response
	 * @return CreateVendResponse
	 */
	public static CreateVendResponse constructCreateVendResponse() {

		final CreateVendResponse paymentResponse = new CreateVendResponse();
		final CreateVendResponseMessage vendResMsg = new CreateVendResponseMessage();
		final VendCode vendCode = new VendCode();
		vendCode.setVendCode(VEND_CODE);
		vendResMsg.setVendCode(vendCode);
		vendResMsg.setVendOutcome(constructVendOutcome());
		paymentResponse.setPaymentResponse(vendResMsg);
		return paymentResponse;

	}

	/**
	 * Constructs VendOutcome Details
	 * @return VendOutcome
	 */
	public static VendOutcome constructVendOutcome() {

		final VendOutcome outCme = new VendOutcome();
		outCme.setVendOutcomeCode(VendOutcomeCode_type1.value1);
		outCme.setVendOutcomeDescription(VEND_DESC);
		return outCme;

	}

	/**
	 * Constructs VoidVendResponse Details
	 * @return VoidVendResponse
	 */
	public static VoidVendResponse constructVoidVendResponse(final VendOutcome outCme) {

		final VoidVendResponse response = new VoidVendResponse();
		final VoidVendRequestResponseMessage vendRespMsg = new VoidVendRequestResponseMessage();
		vendRespMsg.setVendOutcome(outCme);
		response.setVoidTxnResponse(vendRespMsg);
		return response;

	}

	/**
	 * Constructs Void Request Details Form
	 * @return VoidRequestDetailsForm
	 */
	public static VoidRequestDetailsForm constructVoidRequestDetailsForm() {

		final VoidRequestDetailsForm reqDetailsForm = new VoidRequestDetailsForm();
		reqDetailsForm.setPan(PAN);
		reqDetailsForm.setDateTime(new Date());
		reqDetailsForm.setStatusCode(STATUS);
		reqDetailsForm.setTransactionID(TRANSACTION_ID);
		reqDetailsForm.setOriginalTransactionID(ORIG_TRANS_ID);
		return reqDetailsForm;

	}

	/**
	 * Constructs Delivery Receipt Response
	 * @return DeliveryReceiptResponse
	 */
	public static DeliveryReceiptResponse constructDeliveryReceiptResponse() {

		final DeliveryReceiptResponse delivRecptResponse = new DeliveryReceiptResponse();
		delivRecptResponse.setDeliveryStatus(DeliveryStatusCode.value1);
		delivRecptResponse.setTransactionID(TRANSACTION_ID);
		return delivRecptResponse;

	}

	/**
	 * Constructs User Details
	 * @return UserDetails
	 */
	public static UserDetails constructUserDetails() {

		final UserDetails user = new UserDetails();
		user.setLanID("34565677");
		user.setGroupID("1");
		user.setLockIND('N');

		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 45);
		user.setPasswordDate(cal.getTime());
		user.setPassword(TEST);
		user.setUserName(TEST);
		return user;

	}

	/**
	 * Constructs User Details List
	 * @return ArrayList<UserDetails>
	 */
	public static ArrayList<UserDetails> constructUserDetailsList() {

		final ArrayList<UserDetails> userList = new ArrayList<UserDetails>();
		UserDetails user = constructUserDetails();
		userList.add(user);
		return userList;

	}

	/**
	 * Constructs Vend History Request Message
	 * @return GetVendHistory
	 */
	public static GetVendHistory constructVendHistoryRequestMessage() {

		final VendHistoryRequestMessage vendHistoryRequest = new VendHistoryRequestMessage();
		final StringType stringType = new StringType();
		stringType.setStringType(PAN);
		vendHistoryRequest.setPan(stringType);

		final IntegerType integerType = new IntegerType();
		integerType.setIntegerType(new BigInteger("1"));
		vendHistoryRequest.setNoOfTxns(integerType);
		final GetVendHistory getVendHistory = new GetVendHistory();
		getVendHistory.setVendHistoryRequest(vendHistoryRequest);
		return getVendHistory;

	}

	/**
	 * Constructs Transaction List
	 * @return Collection<String>
	 */
	public static Collection<String> constructTransactionList() {

		final Collection<String> transactionTypes = new ArrayList<String>();
		transactionTypes.add(TransactionType.ElectricPurchase.getTransactionType());
		transactionTypes.add(TransactionType.GasPurchase.getTransactionType());
		return transactionTypes;

	}

	/**
	 * Constructs Transaction List
	 * @return Collection<String>
	 */
	public static Collection<String> constructTransactionsList() {

		final Collection<String> transactions = new ArrayList<String>();
		transactions.add(TransactionType.VoidENegativeAdjustment.getTransactionType());
		transactions.add(TransactionType.VoidGNegativeAdjustment.getTransactionType());
		return transactions;

	}

	/**
	 * Constructs Transaction Type List
	 * @return Collection<String>
	 */
	public static Collection<String> constructTransactionTypeList() {

		final Collection<String> transactionTypeList = new ArrayList<String>();
		transactionTypeList.add(TransactionType.ENegativeAdjustment.getTransactionType());
		transactionTypeList.add(TransactionType.EPositiveAdjustment.getTransactionType());
		transactionTypeList.add(TransactionType.GNegativeAdjustment.getTransactionType());
		transactionTypeList.add(TransactionType.GPositiveAdjustment.getTransactionType());
		return transactionTypeList;

	}

	/**
	 * Constructs Void Vend
	 * @return VoidVend
	 */
	public static VoidVend constructVoidRequest() {

		final VoidVend voidRequest = new VoidVend();

		VoidVendRequestMessage vendReqMsg = new VoidVendRequestMessage();

		final BG_MessageHeader msgHdr = new BG_MessageHeader();
		vendReqMsg.setMessageHeader(msgHdr);

		vendReqMsg.setVendRequestKey(constructVendRequestKey());

		vendReqMsg.setOriginalVendRequestKey(constructVendRequestKey());

		final MeteringAssetBasic meter = new MeteringAssetBasic();
		meter.setManufacturerSerialNumber(VMSTestDataProvider.MSN);
		meter.setMeteringAssetEquipmentNumber("M1234567890");
		meter.setSapMeteringAssetSerialNumber("S1234567890");
		vendReqMsg.setVendRequestMSN(meter);

		final PanNumber panNo = new PanNumber();
		panNo.setPanNumber(VMSTestDataProvider.PAN);
		vendReqMsg.setVendRequestPAN(panNo);

		vendReqMsg.setVendRequestTimeStamp(constructVendRequestTimeStamp());

		voidRequest.setVoidTxnRequest(vendReqMsg);
		return voidRequest;
	}

	/**
	 * Constructs VendTxnWSDetails
	 * @return VendTxnWSDetails
	 */
	public static VendTxnWSDetails constructVendTxnWSDetails(final int statusCode) {

		final VendTxnWSDetails vendTxDetails = new VendTxnWSDetails();
		final Set<VendTxnStatus> statusSet = constructVendTxnStatus(statusCode);
		vendTxDetails.setTxnStatusDetails(statusSet);
		vendTxDetails.setOriginalTransactionID(VMSTestDataProvider.TRANSACTION_ID);
		vendTxDetails.setMsn(VMSTestDataProvider.MSN);
		vendTxDetails.setPan(VMSTestDataProvider.PAN);
		vendTxDetails.setTransactionType(TransactionType.Reversal.getTransactionType());
		vendTxDetails.setCreditValue(CREDIT_VALUE);
		return vendTxDetails;

	}

	/**
	 * Constructs VendTxnStatus Set
	 * @param statusCode
	 * @return Set<VendTxnStatus>
	 */
	public static Set<VendTxnStatus> constructVendTxnStatus(final int statusCode) {

		final Set<VendTxnStatus> statusSet = new HashSet<VendTxnStatus>();
		final VendTxnStatus status = new VendTxnStatus();
		status.setStatus(statusCode);
		statusSet.add(status);
		return statusSet;

	}

	/**
	 * Constructs Vend Request Key
	 * @return VendRequestKey
	 */
	public static VendRequestKey constructVendRequestKey() {

		final VendRequestKey origReqKey = new VendRequestKey();
		final BG_Identifier origIdentifier = new BG_Identifier();
		final Token tkn = new Token();
		tkn.setValue(VMSTestDataProvider.TRANSACTION_ID);
		origIdentifier.setBG_IdentifierContent(tkn);
		origReqKey.setVendRequestIdentifier(origIdentifier);
		return origReqKey;

	}

	/**
	 * Constructs Vend Request Time Stamp
	 * @return VendRequestTimestamp
	 */
	public static VendRequestTimestamp constructVendRequestTimeStamp() {

		final VendRequestTimestamp tmsStamp = new VendRequestTimestamp();
		tmsStamp.setVendRequestDateTime(Calendar.getInstance());
		return tmsStamp;

	}

	/**
	 * Constructs Customer Details
	 * @return CustomerDetails
	 */
	@SuppressWarnings("deprecation")
	public static CustomerDetails constructCustomerDetails() {

		final CustomerDetails customer = new CustomerDetails();
		customer.setPan(VMSTestDataProvider.PAN);
		customer.setMpxn(VMSTestDataProvider.MSN);
		customer.setValidFrom(new Date(112, 01, 01));
		customer.setValidTo(new Date(199, 01, 01));
		return customer;

	}
	
	/**
	 * Constructs Premise Details
	 * @return PremiseDetails
	 */
	public static PremiseDetails constructPremiseDetails() {

		final PremiseDetails premiseDetails = new PremiseDetails();
		premiseDetails.setMpxn(MPXN);
		premiseDetails.setMSN(MSN);
		return premiseDetails;

	}

}
