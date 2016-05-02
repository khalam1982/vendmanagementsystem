package com.centrica.vms.scheduler.DAO;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.factory.PersistenceSessionFactory;
import com.centrica.vms.scheduler.model.PPKeyTxnScheduler;

/**
 * PPKeySchedulerDAO
 * 
 * DAO Class to handle Database Transactions
 * 
 * @author chackram
 */
public class PPKeySchedulerDAO extends VMSTransactionDAO {

	private final Logger logger = ESAPI.getLogger(PPKeySchedulerDAO.class);

	/**
	 * Gets PP Key Transaction Scheduler Details from Database
	 * 
	 * @param transactionID of type Strinf
	 * @return PPKeyTxnScheduler
	 * @throws DBException
	 */
	public PPKeyTxnScheduler getPPKeyTxnSchedulerDetails(final String transactionID) throws DBException {

		final Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		Transaction tx = null;
		PPKeyTxnScheduler ppKeyTxnSdlr;
		try{
			tx = session.beginTransaction();
			ppKeyTxnSdlr = (PPKeyTxnScheduler)session.get(PPKeyTxnScheduler.class, transactionID);
		} 
		catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			ppKeyTxnSdlr = null;
			throw new DBException();
		} finally {
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(tx);
		}
		return ppKeyTxnSdlr;

	}

}
