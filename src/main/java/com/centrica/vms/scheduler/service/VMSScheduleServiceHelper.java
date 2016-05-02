package com.centrica.vms.scheduler.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.Scheduler;


/**
 * Helper class for VMSScheduleService
 */
public class VMSScheduleServiceHelper {

	private final Logger logger = ESAPI.getLogger(getClass().getName());

	/**
	 * Gets Scheduler instance from context and returns it to the caller
	 * 
	 * @param ctxtName of type String
	 * @return Scheduler
	 * @throws NamingException
	 */
	public Scheduler getScheduler(final String ctxtName) throws NamingException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering getScheduler method ");
		Context context = new InitialContext();
		Scheduler scheduler = (Scheduler)context.lookup(ctxtName);
		context.close();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the getScheduler method");
		return scheduler;

	}

}
