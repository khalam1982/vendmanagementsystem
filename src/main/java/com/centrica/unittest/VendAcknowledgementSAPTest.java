package com.centrica.unittest;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import junit.framework.TestCase;

import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.external.service.SAPService;
import com.centrica.vms.scheduler.model.VendAckJobDetails;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.VendTxnWSDetails;


public class VendAcknowledgementSAPTest extends TestCase  {

	private Logger logger = ESAPI.getLogger(getClass().getName());
	private VendAckJobDetails jobDetails = new VendAckJobDetails();
		
	public void testVendSAPAck(){
		prepareVendAckJobDetails();
		SAPService sapService = new SAPService();
		sapService.sendVendAcknowledgement(jobDetails);	
		testUpdateVendTxnStatus(jobDetails.getTransactionID());
		
	}
	
	public void testUpdateVendTxnStatus(String txnId){
		try {
		System.out.print("Entering testUpdateVendTxnStatus");
		WSTransactionDAO wsTransactionDAO = new WSTransactionDAO();		
		VendTxnWSDetails vendTransactionDetails = wsTransactionDAO.getVendTxnWSDetails(txnId);
		Set<VendTxnStatus> txnStatusDetails = vendTransactionDetails.getTxnStatusDetails();
		VendTxnStatus vendTxnStatus = new VendTxnStatus();
		vendTxnStatus.setStatus(Status.SC_101.getStatus());
		vendTxnStatus.setUpdatedTimeStamp(new Date());	
		txnStatusDetails.add(vendTxnStatus);
		vendTransactionDetails.setTxnStatusDetails(txnStatusDetails); 
		wsTransactionDAO.update(vendTransactionDetails);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"DBException::" + e.getMessage());
		}
	 }		
	
	private void prepareVendAckJobDetails() {
		System.out.println("Entering prepareVendAckJobDetails method");
		String transactionID = "MAN00000000019";
		String pan = "9826170633929987247";
		String vendCode = "73123039003643301604";
		String creditValue = "1100";
		Calendar date = Calendar.getInstance();
		Date timestamp = date.getTime();
		Integer retryCount = 1;
		Integer status = 200;
		String msn = "S13N153968";
		jobDetails.setTransactionID(transactionID);
		jobDetails.setPan(pan);
		jobDetails.setVendCode(vendCode);
		jobDetails.setCreditValue(creditValue);
		jobDetails.setTimestamp(timestamp);
		jobDetails.setRetryCount(retryCount);
		jobDetails.setStatus(status);
		jobDetails.setMsn(msn);
		System.out.println("Vend Ack Job details"+new Object[]{transactionID,pan,vendCode,status,timestamp});
		System.out.println("Leaving prepareVendAckJobDetails method");			
	}	
}
