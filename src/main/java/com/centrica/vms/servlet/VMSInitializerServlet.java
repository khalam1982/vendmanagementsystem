/**
 * VMSInitializerServlet.java
 * Purpose: VMS initialization servlet
 * @author ramasap1
 */
package com.centrica.vms.servlet;

import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

/**
 * Methods for VMS initialization servlet
 * @see HttpServlet
 */
public class VMSInitializerServlet extends HttpServlet {
	
	private Logger logger = ESAPI.getLogger(getClass().getName());
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config); 
		logger.debug(Logger.EVENT_SUCCESS,"Entering the init method");
		setDefaultTimeZone(config);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the init method");
	}

	/*
	 * Method to set the default date and time zone
	 * @param config
	 * @return
	 */
	private void setDefaultTimeZone(ServletConfig config) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the setDefaultTimeZone method");
		String timeZone = (String)config.getInitParameter("TIMEZONE");
		TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the setDefaultTimeZone method : "+ new Date());
	}

}
