package com.centrica.unittest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.centrica.vms.ws.transaction.query.VendTransaction;
import com.centrica.vms.ws.transaction.query.VendTransactionQuery;

public class VendTransactionsQueryTest extends TestCase {

private Logger logger = null;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		 super.setUp();
		 PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("properties/log4j.config").getPath());
	     logger = ESAPI.getLogger(getClass().getName());
	     logger.info(Logger.EVENT_UNSPECIFIED,"log configuration is Successful");
	}
	
	public void testPurchaseTxn(){
		VendTransactionQuery query = new VendTransactionQuery();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {
			Date startdate = dateFormatter.parse("13-03-2013 01:10:10");
		    Date endDate = dateFormatter.parse("13-03-2014 01:10:10");

		    Calendar calendarStartDate = Calendar.getInstance();
		    calendarStartDate.setTime(startdate);
		    Calendar calendarEndDate = Calendar.getInstance();
		    calendarEndDate.setTime(endDate);
		    
		    List<VendTransaction> list = query.getPurchaseTransactionsForGivenPAN("9826170633605553362", 2, calendarStartDate, calendarEndDate);
		    for(VendTransaction txn:list){
		    	System.out.println("\n"+txn.getVendDateTimeStamp().getVendDateTimeStamp().getTime()+","+txn.getVendCode()+","+txn.getVendAmount());
		    }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"ParseException::" + e.getMessage());
		}
	}
}
