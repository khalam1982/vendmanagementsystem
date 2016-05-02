/**
 * PersistenceSessionFactory.java
 * Purpose: Factory class for Persistence session
 *
 * @author ramasap1
 */
package com.centrica.vms.factory;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Factory methods for Persistence session
 */
public class PersistenceSessionFactory {
	
		/** The single instance of hibernate SessionFactory */ 
		private static org.hibernate.SessionFactory sessionFactory;
		private Logger logger = ESAPI.getLogger(this.getClass());
		
		/**	 * disable constructor to guarantee a single instance	 */	
		private PersistenceSessionFactory() {	}
		/**
		 * GET the static instance of the class
		 * @param
		 * @return SessionFactory
		 */
		public static SessionFactory getInstance() {
				if(sessionFactory==null){
					sessionFactory = new Configuration().configure("/properties/hibernate.cfg.xml").buildSessionFactory(); 
				}
				return sessionFactory;
		}
			
	    /** 
	     * Returns a session from the session context. If there is no session in the context it opens a session,   
		 * stores it in the context and returns it.	 
		 * This factory is intended to be used with a hibernate.cfg.xml	 
		 * including the following property 
	     * <property name="current_session_context_class">thread</property> 
	     * This would return the current open session or if this does not exist, 
	     * will create a new session
	     * @param
	     * @return Session
	     */	
			
	    public Session getCurrentSession() {
	    	logger.info(Logger.EVENT_UNSPECIFIED,"In the getCurrentSession method");
				return sessionFactory.getCurrentSession();	
		}  
			
	    /**
	     * closes the session factory
	     * @param 
	     * @return
	     */	
		public static void close(){		
			if (sessionFactory != null){
				sessionFactory.close();		
			}
				sessionFactory = null;
	     }
}
