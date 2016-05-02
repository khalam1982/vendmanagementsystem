/**
 * 
 */
package com.centrica.vms.scheduler.job;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.centrica.vms.scheduler.model.VendTransactionsCache;

/**
 * @author koodlys
 *
 */
public class VendTransactionsLoadCacheJob implements Job {
	
	private Logger logger = ESAPI.getLogger(this.getClass());
	private VendTransactionsCache vendCache = null;
	
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext arg0) throws JobExecutionException { 
		logger.debug(Logger.EVENT_SUCCESS,"Entering VendTransactionsLoadCacheJob execute method");
		try {
				vendCache = VendTransactionsCache.getInstance();	
				vendCache.refreshCache();
				logger.info(Logger.EVENT_UNSPECIFIED,"Cache Job next firetime-"+arg0.getNextFireTime());			
	        } catch (Exception e) {
	        	logger.error(Logger.EVENT_FAILURE,"Exception in  VendTransactionsLoadCacheJob "+e.getStackTrace());
	        	throw new JobExecutionException();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving VendTransactionsLoadCacheJob execute method");
	}		
	
}
