/**
 * Log4jInitializerServlet.java
 * Purpose: Log4J initialization servlet
 * @author ramasap1
 */
package com.centrica.vms.servlet;



import javax.servlet.http.HttpServlet;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 * Methods for Log4J initialization servlet
 * @see HttpServlet
 */
public class Log4jInitializerServlet extends HttpServlet {
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() {
	    String prefix =  getServletContext().getRealPath("/");
	    String file = getInitParameter("log4j-init-file");
	    // if the log4j-init-file is not set, then no point in trying
	    if(file != null) {
	      PropertyConfigurator.configure(prefix+file);
	      Logger logger = ESAPI.getLogger(getClass().getName());
	      logger.info(Logger.EVENT_UNSPECIFIED,"log configuration is Successful");
	    }
	  }

}
