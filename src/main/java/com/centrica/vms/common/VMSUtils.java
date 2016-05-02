/**
 * VMSUtils.java
 * Purpose: Utility class
 *
 * @author ramasap1, nagarajk
 */
package com.centrica.vms.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.axis2.databinding.types.Token;
import org.apache.commons.lang.StringUtils;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import smartmeterprocessing.enterprise.uk.co.britishgas.VendCreditType_type1;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;
import LG_PAYMENT_CODE.PaymentCode.eCreditType;

import com.centrica.vms.model.UserFunctionDetails;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.ws.model.CreditIDDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;

import common.enterprise.uk.co.britishgas.BG_Log;
import common.enterprise.uk.co.britishgas.BG_LogItem;
import common.enterprise.uk.co.britishgas.BG_LogItemCategoryCode;
import common.enterprise.uk.co.britishgas.BG_LogItemNote;
import common.enterprise.uk.co.britishgas.BG_LogItemSeverityCode;
import common.enterprise.uk.co.britishgas.BG_LogItemTypeID;
import common.enterprise.uk.co.britishgas.BG_ProcessingResultCode;
/**
 * Utility methods for other classes
 */
public class VMSUtils {

	private Logger logger = ESAPI.getLogger(getClass().getName());

	/**
	 * Method to get the type of credit
	 * 
	 * @param VendCreditType_type1
	 * @return eCreditType
	 */
	public eCreditType getTypeOfCredit(VendCreditType_type1 creditType) {

		eCreditType typeOfCredit;
		logger.debug(Logger.EVENT_SUCCESS,"Entering getTypeOfCredit method");
		if (creditType.equals(VendCreditType_type1.ADJUSTMENT)) {
			typeOfCredit = eCreditType.Adjustment;
		} else {
			typeOfCredit = eCreditType.Purchase;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getTypeOfCredit method");
		return typeOfCredit;
	}

	/**
	 * Method to get the VMS status code
	 * @param int - status
	 * @return int
	 */
	public int getVMSStatus(int status){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVMSStatus method");
		logger.info(Logger.EVENT_UNSPECIFIED,"Status is " + status);
		switch(status){
		case 10:
			return Status.SC_10.getStatus();
		case 15:
			return Status.SC_15.getStatus();
		case 20:
			return Status.SC_20.getStatus();
		case 25:
			return Status.SC_25.getStatus();
		case 30:
			return Status.SC_30.getStatus();
		case 35:
			return Status.SC_35.getStatus();
		case 40:
			return Status.SC_40.getStatus();
		case 45:
			return Status.SC_45.getStatus();
		case 50:
			return Status.SC_50.getStatus();
		case 55:
			return Status.SC_55.getStatus();
		case 60:
			return Status.SC_60.getStatus();
		case 65:
			return Status.SC_65.getStatus();
		case 70:
			return Status.SC_70.getStatus();
		case 75:
			return Status.SC_75.getStatus();
		case 90:
			return Status.SC_90.getStatus();
		case 95:
			return Status.SC_95.getStatus();
		case 96:
			return Status.SC_96.getStatus();
		case 100:
			return Status.SC_100.getStatus();
		case 105:
			return Status.SC_105.getStatus();
		case 106:
			return Status.SC_106.getStatus();
		case 107:
			return Status.SC_107.getStatus();
		case 108:
			return Status.SC_108.getStatus();
		case 109:
			return Status.SC_109.getStatus();
		case 110:
			return Status.SC_110.getStatus();
		case 115:
			return Status.SC_115.getStatus();
		case 120:
			return Status.SC_120.getStatus();
		case 125:
			return Status.SC_125.getStatus();
		case 130:
			return Status.SC_130.getStatus();
		case 135:
			return Status.SC_135.getStatus();
		case 140:
			return Status.SC_140.getStatus();
		case 145:
			return Status.SC_145.getStatus();
		case 150:
			return Status.SC_150.getStatus();
		case 155:
			return Status.SC_155.getStatus();
		case 160:
			return Status.SC_160.getStatus();
		case 165:
			return Status.SC_165.getStatus();
		case 170:
			return Status.SC_170.getStatus();
		case 180:
			return Status.SC_180.getStatus();
		case 200:
			return Status.SC_200.getStatus();
		case 300:
			return Status.SC_300.getStatus();
		case 310:
			return Status.SC_310.getStatus();
		case 320:
			return Status.SC_320.getStatus();
		case 330:
			return Status.SC_330.getStatus();
		case 340:
			return Status.SC_340.getStatus();
		case 350:
			return Status.SC_350.getStatus();
		case 360:
			return Status.SC_360.getStatus();
		case 370:
			return Status.SC_370.getStatus();
		case 380:
			return Status.SC_380.getStatus();
		case 390:
			return Status.SC_390.getStatus();
		case 80:
			return Status.SC_80.getStatus();
		case 151:
			return Status.SC_151.getStatus();
		case 152:
			return Status.SC_152.getStatus();
		case 153:
			return Status.SC_153.getStatus();
		case 154:
			return Status.SC_154.getStatus();
		case 400:
			return Status.SC_400.getStatus();
		case 410:
			return Status.SC_410.getStatus();
		case 510:
			return Status.SC_515.getStatus();
		case 520:
			return Status.SC_520.getStatus();
		case 570:
			return Status.SC_570.getStatus();
		case 600:
			return Status.SC_600.getStatus();
		default:
			return Status.SC_125.getStatus(); // Unsent to headend 
		}
	}
	
	/**
	 * Method to load the properties
	 * @param
	 * @return Properties
	 */
	public Properties loadProperties() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering load properties method");
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("properties/VMSDetails.properties");
			properties.load(inputStream);
		} catch (IOException ex) {
			logger.error(Logger.EVENT_FAILURE,"IOException is thrown" + ex.getMessage());	
			properties.clear();				
		}
		if(inputStream != null){
			try {
				inputStream.close();
			} catch (IOException e) {
				logger.error(Logger.EVENT_FAILURE,"IOException thrown while releasing Input Stream" + e.getMessage());	
			}
		}


		logger.debug(Logger.EVENT_SUCCESS,"Leaving load properties method");
		return properties;
	}

	/**
	 * Method converts the String to hexastring
	 * 
	 * @param char[] - base
	 * @return String
	 */
	public String stringToHexString(char[] base) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering stringToHexString method");
		StringBuffer buffer = new StringBuffer();
		int intValue;
		for (int x = 0; x < base.length; x++) {
			intValue = base[x];
			String hexString = Integer.toHexString(intValue);
			hexString = adjustBytes(2,hexString);
			logger.info(Logger.EVENT_UNSPECIFIED,"Char is:" + base[x] + "hex is" + hexString);
			buffer.append(hexString);
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"String equivalent is" + buffer.toString());
		logger.debug(Logger.EVENT_SUCCESS,"Entering stringToHexString method");
		return buffer.toString();
	}
	
	
    /**
     * Method to adjust the hex string bits
     * @param int - noOfBytes
     * @param String - hexString
     * @return String
     */
	private String adjustBytes(int noOfBytes, String hexString) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering adjustBytes method");
		while (hexString.length() < noOfBytes) {
			hexString = "0" + hexString;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving adjustBytes method");
		return hexString;
	}

	/**
	 * Method to create the BG log
	 * 
	 * @param VendOutcomeCode_type1
	 * @param String - message
	 * @return BG_Log
	 */
	public BG_Log getBGLog(VendOutcomeCode_type1 outcomeCode, String message) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getBGLog method");
		BG_Log log = new BG_Log();
		String processResultCode = this.loadProperties().getProperty("LOG_PRC");
		String maximumLogItemSeverityCode = this.loadProperties().getProperty("LOG_MLISC");
		String logItemSeverityCode = this.loadProperties().getProperty("LOG_LISC");
		String logItemCategoryCode = this.loadProperties().getProperty("LOG_LICC");
		log.setBusinessDocumentProcessingResultCode(getProcessingResultCode(processResultCode));
		log.setMaximumLogItemSeverityCode(getMaximumLogItemSeverityCode(maximumLogItemSeverityCode));
		log.setItem(getLogItem(outcomeCode, message, logItemSeverityCode,logItemCategoryCode));

		logger.debug(Logger.EVENT_SUCCESS,"Leaving getBGLog method");
		return log;
	}

	/**
	 * Method to create the BG log
	 * 
	 * @param VendOutcomeCode_type1
	 * @param String - errorMessage
	 * @return BG_Log
	 */
	public BG_Log getBGLogForFault(VendOutcomeCode_type1 faultCode,
			String errorMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getBGLogForFault method");
		BG_Log log = new BG_Log();
		String processResultCode = this.loadProperties().getProperty("FLOG_PRC");
		String maximumLogItemSeverityCode = this.loadProperties().getProperty("FLOG_MLISC");
		String logItemSeverityCode = this.loadProperties().getProperty("FLOG_LISC");
		String logItemCategoryCode = this.loadProperties().getProperty("FLOG_LICC");
		log
				.setBusinessDocumentProcessingResultCode(getProcessingResultCode(processResultCode));
		log
				.setMaximumLogItemSeverityCode(getMaximumLogItemSeverityCode(maximumLogItemSeverityCode));
		log.setItem(getLogItem(faultCode, errorMessage, logItemSeverityCode,
				logItemCategoryCode));

		logger.debug(Logger.EVENT_SUCCESS,"Leaving getBGLogForFault method");
		return log;
	}

	/**
	 * Method to get the log item
	 * 
	 * @param VendOutcomeCode_type1
	 * @param String - message
	 * @param String - logItemSeverityCode
	 * @param String - logItemCategoryCode
	 * @return BG_LogItem
	 */
	private BG_LogItem[] getLogItem(VendOutcomeCode_type1 outComeCode,
			String message, String logItemSeverityCode,
			String logItemCategoryCode) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getLogItem method");
		BG_LogItem[] logItem = new BG_LogItem[1];
		logItem[0] = new BG_LogItem();
		logItem[0].setNote(getLogNote(message));
		logItem[0].setSeverityCode(getLogItemSeverityCode(logItemSeverityCode));
		logItem[0].setCategoryCode(getLogItemCategoryCode(logItemCategoryCode));
		logItem[0].setTypeID(getLogItemTypeID(outComeCode));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getLogItem method");
		return logItem;
	}

	/**
	 * Method to get the log note
	 * 
	 * @param String - errorMessage
	 * @return BG_LogItemNote
	 */
	private BG_LogItemNote getLogNote(String errorMessage) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getLogNote method");
		BG_LogItemNote logNote = new BG_LogItemNote();
		logNote.setBG_LogItemNote(errorMessage);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getLogNote method");
		return logNote;
	}

	/**
	 * method to get the log item type id
	 * 
	 * @param VendOutcomeCode_type1
	 * @return BG_LogItemTypeID
	 */
	private BG_LogItemTypeID getLogItemTypeID(VendOutcomeCode_type1 faultCode) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getLogItemTypeID  method");
		BG_LogItemTypeID typeID = new BG_LogItemTypeID();
		typeID.setBG_LogItemTypeID(getToken(faultCode.getValue()));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getLogItemTypeID  method");
		return typeID;
	}

	/**
	 * Method to get the log item severity code
	 * 
	 * @param String - logItemSeverityCode
	 * @return BG_LogItemSeverityCode
	 */
	private BG_LogItemSeverityCode getLogItemSeverityCode(
			String logItemSeverityCode) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getLogItemSeverityCode  method");
		BG_LogItemSeverityCode logSeverityCode = new BG_LogItemSeverityCode();
		logSeverityCode
				.setBG_LogItemSeverityCode(getToken(logItemSeverityCode));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getLogItemSeverityCode  method");
		return logSeverityCode;
	}

	/**
	 * Method to get the log item category code
	 * 
	 * @param String - logItemCategoryCode
	 * @return BG_LogItemCategoryCode
	 */
	private BG_LogItemCategoryCode getLogItemCategoryCode(
			String logItemCategoryCode) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getLogItemCategoryCode  method");
		BG_LogItemCategoryCode categoryCode = new BG_LogItemCategoryCode();
		categoryCode.setBG_LogItemCategoryCode(getToken(logItemCategoryCode));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getLogItemCategoryCode  method");
		return categoryCode;
	}

	/**
	 * Method to get the maximum log item severity code
	 * 
	 * @param String - maximumLogItemSeverityCode
	 * @return BG_LogItemSeverityCode
	 */
	private BG_LogItemSeverityCode getMaximumLogItemSeverityCode(
			String maximumLogItemSeverityCode) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getMaximumLogItemSeverityCode  method");
		BG_LogItemSeverityCode severity = new BG_LogItemSeverityCode();
		severity
				.setBG_LogItemSeverityCode(getToken(maximumLogItemSeverityCode));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getMaximumLogItemSeverityCode  method");
		return severity;
	}

	/**
	 * Method to get the processing result code
	 * 
	 * @param String - processResultCode
	 * @return BG_ProcessingResultCode
	 */
	private BG_ProcessingResultCode getProcessingResultCode(
			String processResultCode) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getProcessingResultCode  method");
		BG_ProcessingResultCode resultCode = new BG_ProcessingResultCode();
		resultCode.setBG_ProcessingResultCode(getToken(processResultCode));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getProcessingResultCode  method");
		return resultCode;
	}

	/**
	 * Method to get the token
	 * 
	 * @param String - tokenValue
	 * @return Token
	 */
	private Token getToken(String tokenValue) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getToken method");
		Token resultToken = new Token();
		resultToken.setValue(tokenValue);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getToken method");
		return resultToken;
	}

	/**
	 * Method to get the schedule job run time
	 * 
	 * @param Long - holdingPeriod
	 * @return Date
	 */
	public Date getScheduleTime(Long holdingPeriod) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getScheduleTime method ");
		Date currentDate = new Date();
		logger.info(Logger.EVENT_UNSPECIFIED,"Current Date is : " + currentDate);
		Long delayedTime = currentDate.getTime() + (holdingPeriod * 1000);
		Calendar calender = new GregorianCalendar();
		calender.setTimeInMillis(delayedTime);
		logger.info(Logger.EVENT_UNSPECIFIED,"Scheduled time is " + calender.getTime());
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getScheduleTime method ");
		return calender.getTime();
	}
	
	/**
	 * Method to get the timestamp used to generate the vend code
	 * @param Date - vendTimeStamp
	 * @param Date - actualTimeStamp
	 * @return Date
	 */
	public Date getVendTimeStamp(Date vendTimeStamp,Date actualTimeStamp){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendTimeStamp method ");
		logger.info(Logger.EVENT_UNSPECIFIED,"vend TimeStamp : " + vendTimeStamp);
		if(vendTimeStamp!=null){
			Long delayedTime = vendTimeStamp.getTime() + new Integer(this.loadProperties().getProperty("GAS_INCREMENTVALUE")); //Increment by an hour
			//Calendar calender = new GregorianCalendar();
			Calendar calender = Calendar.getInstance();
			calender.setTimeInMillis(delayedTime);
			vendTimeStamp = calender.getTime();
			
		}else{
			vendTimeStamp = actualTimeStamp;
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend code timestamp is " + vendTimeStamp);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getScheduleTime method ");
		return vendTimeStamp;
	}
	/**
	 * Method to process the credit id value
	 * 
	 * @param String - transactionType
	 * @param int - creditNo
	 * @return int
	 */
	public int processCreditIDValue(String transactionType, int creditNo) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processCreditIDValue method");
		if (transactionType.equals(TransactionType.ElectricPurchase.toString())) {
			if (creditNo >= new Integer(this.loadProperties().getProperty("PUR_CREDITMAXLIMIT"))) {
				creditNo = 0;
			} else {
				creditNo++;
			}
		} else {
			if (creditNo >= new Integer(this.loadProperties().getProperty("ADJ_CREDITMAXLIMIT"))) {
				creditNo = 0;
			} else {
				creditNo++;
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processCreditIDValue method");
		return creditNo;
	}
	
	/**
	 *  Method to decrement the credit id value
	 * @param CreditIDDetails
	 * @return CreditIDDetails
	 */
	public CreditIDDetails decrementCreditIDValue(CreditIDDetails creditIDDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering decrementCreditIDValue method");
		String transactionType = creditIDDetails.getCreditIDCompKey().getTransactionType();
		int creditNo = creditIDDetails.getCreditID();
		if (transactionType.equals(TransactionType.ElectricPurchase.toString())) {
			if (creditNo == 0) {
				creditNo = new Integer(this.loadProperties().getProperty("PUR_CREDITMAXLIMIT"));
			} else {
				creditNo--;
			}
		} else {
			if (creditNo == 0) {
				creditNo = new Integer(this.loadProperties().getProperty("ADJ_CREDITMAXLIMIT"));
			} else {
				creditNo--;
			}
		}
		creditIDDetails.setCreditID(creditNo);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving decrementCreditIDValue method");
		return creditIDDetails;
	}
	
	/**
	 * Method used to validate the password
	 * @param Date - passwordDate
	 * @return Boolean
	 */
	public Boolean isPasswordExpired(Date passwordDate){
		logger.debug(Logger.EVENT_SUCCESS,"Entering method isPasswordExpired");
		Properties property = this.loadProperties();
		if(getPasswordDueDate(passwordDate)>(new Integer(property.getProperty("PASS_VALIDITY")))){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Method used to get the password due date
	 * @param Date - passwordDate
	 * @return int
	 */
	public int passwordDueDate(Date passwordDate){
		logger.debug(Logger.EVENT_SUCCESS,"Entering method passwordDueDate");
		Properties property = this.loadProperties();
		int dueDate = getPasswordDueDate(passwordDate);
		if(dueDate>new Integer(property.getProperty("PASS_DUEDATE"))){
			dueDate = new Integer(property.getProperty("PASS_VALIDITY"))-dueDate+1;
		}else{
			dueDate = 0;
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"due date is " + dueDate);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving method passwordDueDate");
		return dueDate;
	}
	
	/**
	 * Method used to get the password due date
	 * @param Date - passwordDate
	 * @return int
	 */
	private int getPasswordDueDate(Date passwordDate){
		logger.debug(Logger.EVENT_SUCCESS,"Entering method getPasswordDueDate");
		int dueDays = new Long(((new Date().getTime())/(1000*60*60*24))-((passwordDate.getTime())/(1000*60*60*24))).intValue();
		logger.info(Logger.EVENT_UNSPECIFIED,"Due date is:"+dueDays);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving method getPasswordDueDate");
		return dueDays;
		
	}
	
	/**
	 * Method used to add the new functions assigned to the user 
	 * @param String-  vmsGroup
	 * @param Set<UserFunctionDetails> - userFunctionDetails
	 * @param ArrayList<String> - newFunctions
	 * @return 
	 */
	public void addNewFunctions(String vmsGroup,
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
	}


	/**
	 * Method used to get the list of newly assigned functions.
	 * @param Set<UserFunctionDetails> - userFunctionDetails
	 * @param String - userAccessFunctions
	 * @param ArrayList<String> - newFunctions
	 * @return
	 */
	public void getNewFunctions(Set<UserFunctionDetails> userFunctionDetails,
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
	}


	/**
	 * Method used to remove the unselected functions from the existing user's functions
	 * @param Set<UserFunctionDetails> - userFunctionDetails
	 * @param ArrayList<String> - oldFunctions
	 * @return
	 */
	public void removeUnselectedFunctions(
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
	}


	/**
	 * Method used to get the list of unselected functions from the existing user functions
	 * @param Set<UserFunctionDetails> - userFunctionDetails
	 * @param String - userAccessFunctions
	 * @param ArrayList<String> - oldFunctions
	 * @return 
	 */
	public void getUnselectedFunctions(
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
	}
	
	/**
	 * Method used to check the password if there are any consecutive characters
	 * @param String - password
	 * @return Boolean
	 */
	public Boolean isConsecutivePassword(String password){
		logger.debug(Logger.EVENT_SUCCESS,"Entering isConsecutivePassword method");
		int j =0;
		int size = password.length();
		for(int i=0; i<size; i++)
	       {
			    j++;
	           if(j!=size&&password.charAt(i) == password.charAt(j))
	             {
	                	return true;
	             }
	      }
		logger.debug(Logger.EVENT_SUCCESS,"Leaving isConsecutivePassword method");
		return false;
	}
	/**
	 * Method used to check whether the password at least special character
	 * @param String - password
	 * @return Boolean
	 */
	public Boolean hasSpecialCharacter(String password){
		logger.debug(Logger.EVENT_SUCCESS,"Entering hasSpecialCharacter method");
		String specialCharacter = "!\"£$%^&*()-_+={}[]:;@'~#<,>.?/\\|";
        for(int i=0; i<specialCharacter.length(); i++)
        {
                if(password.indexOf(specialCharacter.charAt(i)) >= 0 )
                {
                	return true;
                }
        }
        logger.debug(Logger.EVENT_SUCCESS,"Leaving hasSpecialCharacter method");
        return false;
	}

	/**
	 * Method used to check whether the password has only numbers
	 * @param String - password
	 * @return Boolean
	 */
	public Boolean allNumeric(String password){
		String numbers = "1234567890";
		logger.debug(Logger.EVENT_SUCCESS,"Entering allNumeric method");
		int num=0;
        for(int i=0; i<password.length(); i++)
        {
                if(numbers.indexOf(password.charAt(i)) >= 0 )
                {
                	num++;
                }
        }
        if(password.length()==num){
        	return true;
        }
        logger.debug(Logger.EVENT_SUCCESS,"Leaving allNumeric method");
        return false;
        
	}
	
	/**
	 * Method used to check whether the password has special characters
	 * @param String - password
	 * @return Boolean 
	 */
	public Boolean allSpecialCharacters(String password){
		String specialCharacter = "!\"£$%^&*()-_+={}[]:;@'~#<,>.?/\\|";
		logger.debug(Logger.EVENT_SUCCESS,"Entering allSpecialCharacters method");
		int num=0;
        for(int i=0; i<password.length(); i++)
        {
                if(specialCharacter.indexOf(password.charAt(i)) >= 0 )
                {
                	num++;
                }
                
        }
        if(password.length()==num){
        	return true;
        }
        logger.debug(Logger.EVENT_SUCCESS,"Leaving allSpecialCharacters method");
        return false;
        
	}
	
	/**
	 * Method used to check whether the password has at least one number
	 * @param String - password
	 * @return Boolean
	 */
	public Boolean hasNumeric(String password){
		String characters = "1234567890";
		logger.debug(Logger.EVENT_SUCCESS,"Entering hasNumeric method");
        for(int i=0; i<characters.length(); i++)
        {
                if(password.indexOf(characters.charAt(i)) >= 0 )
                {
                	return true;
                }
        }
        logger.debug(Logger.EVENT_SUCCESS,"Leaving hasNumeric method");
        return false;
	}
	
	/**
	 * Method to validate the holding period
	 * @param Long - holdingPeriod
	 * @return boolean - TRUE/FALSE
	 */
	public boolean isValidHoldingPeriod(Long holdingPeriod) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering isValidHoldingPeriod method");
		if (holdingPeriod == null || holdingPeriod.intValue() != 0) {
			logger.info(Logger.EVENT_UNSPECIFIED,"Holding period should be zero for Adjustment");
			return false;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving isValidHoldingPeriod method");
		return true;
	}
	
	/**
	 * Method to validate the source
	 * @param String - source
	 * @return boolean - TRUE/FALSE
	 */
	public boolean isValidSource(String source) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering isValidSource method");
		if (source == null || source.equals("")) {
			logger.info(Logger.EVENT_UNSPECIFIED,"Source should not be empty for Adjustment");
			return false;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving isValidSource method");
		return true;
	}
	
	/**
	 * Method to validate the transaction ID length for internal channels.
	 * @param transactionId
	 * @return
	 */
	public boolean isValidTransactionId(String transactionId) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering isValidTransactionId method");
		boolean valid = true;
		if (StringUtils.isBlank(transactionId) || transactionId.length() > 15) {
			logger.info(Logger.EVENT_UNSPECIFIED,"TransactionId cannot be empty & length should be within 15 characters");
			valid = false;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Entering isValidTransactionId method");
		return valid;
	}
	
	public static boolean internalChannel(String source) {
		return source.startsWith("1-") ? true : false;
	}

	/**
	 * Method to validate the pan number (empty, length and digits check
	 * @param String - pan
	 * @return boolean
	 */
	
	public boolean isValidPanNumber(String pan) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering isValidPanNumber method");
		boolean isValidPan = true;
		if (pan == null || pan.length() != 19) {
			logger.info(Logger.EVENT_UNSPECIFIED,"PAN number is invalid");
			isValidPan = false;
		}
		for (int i=0;i<pan.length();i++)
		{
			if ((int)pan.charAt(i)<48 || (int)pan.charAt(i)>57) {
				logger.info(Logger.EVENT_UNSPECIFIED,"PAN number should be digits");
				isValidPan = false;
				break;
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving isValidPanNumber method");
		return isValidPan;
	}
	/**
	 * Method to validate the MSN (empty, length)
	 * @param String - msn
	 * @return boolean
	 */
	
	public boolean isValidMSN(String msn) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering isValidPanNumber method");
		boolean isValidMSN = true;
		if (msn == null || msn.length() ==0) {
			logger.info(Logger.EVENT_UNSPECIFIED,"MSN  is invalid");
			isValidMSN = false;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving isValidPanNumber method");
		return isValidMSN;
	}
	
	/**
	 * Method used to get the transaction type
	 * @param VendCreditType_type1
	 * @param int - value
	 * @param Boolean - isElectric
	 * @return TransactionType
	 */
	public TransactionType getTransactionType(VendCreditType_type1 creditType,int value,Boolean isElectric) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getTransactionType method");
		TransactionType transactionType;
		if (isElectric && creditType == VendCreditType_type1.ADJUSTMENT && value < 0) {
			transactionType = TransactionType.ENegativeAdjustment;
		}else if (isElectric && creditType == VendCreditType_type1.ADJUSTMENT && value >= 0) {
			transactionType = TransactionType.EPositiveAdjustment;
		}else if(isElectric){
			transactionType = TransactionType.ElectricPurchase;
		}else if (!isElectric && creditType == VendCreditType_type1.ADJUSTMENT && value < 0) {
			transactionType = TransactionType.GNegativeAdjustment;
		}else if (!isElectric && creditType == VendCreditType_type1.ADJUSTMENT && value >= 0) {
			transactionType = TransactionType.GPositiveAdjustment;
		}else{
			transactionType = TransactionType.GasPurchase;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getTransactionType method");
		return transactionType;
	}
	//CEN_1 start
	/**
	 * Method used to get the SAP PI or HEAD END details from the JNDI resource
	 * @param String - service
	 * @return Object
	 */
	public Object getVendServiceDetails(String service)throws NamingException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendServiceDetails method");
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		Object obj = envCtx.lookup("bean/" + service);
		envCtx.close();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendServiceDetails method");
		return obj;
	}

	
	/**
	 * Method used to remove a specified character from the given string
	 * @param String
	 * @param character
	 * @return String
	 */
	public String removeChar(String s, char c) {
    	logger.debug(Logger.EVENT_SUCCESS,"Entering removeChar method");
        String r = "";
        for (int i = 0; i < s.length(); i ++) {
           if (s.charAt(i) != c) r += s.charAt(i);
        }
        logger.debug(Logger.EVENT_SUCCESS,"Leaving removeChar method");
        return r;
    }
	
	/**
	 * Method used to convert the string value to array
	 * @param String - codes
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> stringToArray(String codes){
		logger.debug(Logger.EVENT_SUCCESS,"Entering stringToArray method");
		String array[] = codes.split(",");
		ArrayList<Integer> codeList = new ArrayList<Integer>();
		for(String code : array){
			code.trim();
			code = code.substring(1, code.length()-1);
			code.trim();
			codeList.add(new Integer(code));
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving stringToArray method");
		return codeList;
	}
	
	/**
	 * Method to get the list of group functions
	 * @param String - functionGroup
	 * @return Collection<String>
	 */
	public Collection<String> getGroupFunctions(String functionGroup){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getGroupFunctions method");
		String functions = new VMSUtils().loadProperties().getProperty(functionGroup);
		Collection<String> groupCollection = new ArrayList<String>();
		if(functions!=null && !functions.equals("")){
			String groupFunctions[] = functions.split(",");
			for(String groupFunction:groupFunctions){
				groupCollection.add(groupFunction);
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getGroupFunctions method");
		return groupCollection;
	}
	
	/* 
	 * Method to retrieve the run the Unix "date +%Z" command
	 * @param 
	 * @return String
	 */
    private String getServerHour() throws IOException {
    	logger.debug(Logger.EVENT_SUCCESS,"Entering getServerHour method");
        String s = null;

        // run the Unix "date +%k" command
        // using the Runtime exec method:
        Process p = Runtime.getRuntime().exec("date +%k");

        java.io.BufferedReader stdInput = new java.io.BufferedReader(new
             java.io.InputStreamReader(p.getInputStream()));

        // read the output from the command
        s = stdInput.readLine();
        logger.debug(Logger.EVENT_SUCCESS,"Leaving getServerHour method");
        return s;
    }

	
	/* 
	 * Method to adjust the time to UTC time.
	 * @param Calendar
	 * @return
	 */
    public void adjustTimeToUTC(Calendar timestamp) throws IOException {
    	logger.debug(Logger.EVENT_SUCCESS,"Entering adjustTimeToUTC method");
    	logger.info(Logger.EVENT_UNSPECIFIED,"Before Conversion : " + timestamp.get(Calendar.HOUR_OF_DAY));
    	int hrValue = 0;
    	long sec = 3600 * 1000;
    	String hour = getServerHour().trim();
    	
    	if (!hour.equals("")) {
    		hrValue = Integer.parseInt(hour);
    	}
    	
    	if (hrValue != timestamp.get(Calendar.HOUR_OF_DAY)) {
    		timestamp.setTime(new Date(timestamp.getTimeInMillis() + sec));
    	}
    	logger.info(Logger.EVENT_UNSPECIFIED,"After Conversion : " + timestamp.get(Calendar.HOUR_OF_DAY));
    	logger.debug(Logger.EVENT_SUCCESS,"Leaving adjustTimeToUTC method");
    }
    
    /**
     * @param timestamp
     * @return Integer
     * @throws IOException
     */
    public Integer offsetTimeToUTC(Calendar timestamp) throws IOException {
    	logger.debug(Logger.EVENT_SUCCESS,"Entering offsetTimeToUTC method");
    	int hrValue = 0;
    	String hour = getServerHour().trim();
    	if (!hour.equals("")) {
    		hrValue = Integer.parseInt(hour);
    		if (hrValue == timestamp.get(Calendar.HOUR_OF_DAY)) {
        		//deduct one hour for UTC only when the time zone is in BST
        		hrValue--;
        	}
    	} else {
    		hrValue = timestamp.get(Calendar.HOUR_OF_DAY);
    	}
    	logger.debug(Logger.EVENT_SUCCESS,"Leaving offsetTimeToUTC method");
    	return hrValue;
    }
    
    /* 
	 * Method to adjust the time to BST time
	 * @param Calendar
	 * @return
	 */
    public void adjustTimeToBST(Calendar timestamp) throws IOException {
    	logger.debug(Logger.EVENT_SUCCESS,"Entering adjustTimeToBST method");
    	int hrValue = 0;
    	String hour = getServerHour().trim();
    	
    	if (!hour.equals("")) {
    		hrValue = Integer.parseInt(hour);
    	}
    	
    	long sec = 3600 * 1000;

    	if (hrValue != timestamp.get(Calendar.HOUR_OF_DAY)) {
    		timestamp.setTime(new Date(timestamp.getTimeInMillis() - sec));
    	}
    	logger.debug(Logger.EVENT_SUCCESS,"Leaving adjustTimeToBST method");
    }
	//CEN_1 end

	/**
	 * Method used to get only the date part
	 * @param date
	 * @return
	 */
	public Calendar truncateDate(Calendar date) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering truncateDate method");
		Calendar returnDate = Calendar.getInstance();
		
		int dd = date.get(Calendar.DATE);
		int mmm = date.get(Calendar.MONTH);
		int yyyy = date.get(Calendar.YEAR);
		
		returnDate.set(Calendar.DATE, dd);
		returnDate.set(Calendar.MONTH, mmm);
		returnDate.set(Calendar.YEAR, yyyy);
		returnDate.set(Calendar.HOUR, 0);
		returnDate.set(Calendar.MINUTE, 0);
		returnDate.set(Calendar.SECOND, 0);
		returnDate.set(Calendar.MILLISECOND, 0);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving truncateDate method");
		return returnDate;
	}
	
	public VendServiceDetails clone(VendServiceDetails originalDetails) {
		VendServiceDetails vendServiceDetails = new VendServiceDetails();
		vendServiceDetails.setUrl(originalDetails.getUrl());
		vendServiceDetails.setUserName(originalDetails.getUserName());
		vendServiceDetails.setPassword(originalDetails.getPassword());
		vendServiceDetails.setPdTimeout(originalDetails.getPdTimeout());
		vendServiceDetails.setRetryPeriod(originalDetails.getRetryPeriod());
		vendServiceDetails.setTimeout(originalDetails.getTimeout());
		vendServiceDetails.setNoofretries(originalDetails.getNoofretries());
		vendServiceDetails.setAckJobTime(originalDetails.getAckJobTime());
		vendServiceDetails.setAckMaxDelay(originalDetails.getAckMaxDelay());
		vendServiceDetails.setAckMaxWaitPeriod(originalDetails.getAckMaxWaitPeriod());
		vendServiceDetails.setAckMinWaitPeriod(originalDetails.getAckMinWaitPeriod());
		return vendServiceDetails;
	}
}
