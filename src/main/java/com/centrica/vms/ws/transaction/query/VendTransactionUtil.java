package com.centrica.vms.ws.transaction.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

public class VendTransactionUtil {

	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	public Date getFormattedDate(Calendar calObject){
		Date formattedDate = null;
		try {
		if(calObject!=null && calObject.getTime()!=null){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormatter.format(calObject.getTime());		
		formattedDate = dateFormatter.parse(dateString);
		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"Exception while parsing date "+e.getMessage());
		}
		return formattedDate;		
	}
}
