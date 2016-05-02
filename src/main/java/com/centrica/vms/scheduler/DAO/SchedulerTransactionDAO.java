/**
 * SchedulerTransactionDAO.java
 * Purpose: Scheduler transaction DAO
 * @author ramasap1
 */
package com.centrica.vms.scheduler.DAO;

import java.util.ArrayList;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.factory.PersistenceSessionFactory;
import com.centrica.vms.model.UnsendVCTransaction;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.scheduler.model.VendTxnSchedulerDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;
/**
 * Methods for Scheduler transaction DAO
 * @see VMSTransactionDAO
 */
public class SchedulerTransactionDAO extends VMSTransactionDAO {
	
	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	

	/**
	 * Method to get the vend transaction scheduler details
	 * @param java.lang.String - transactionID
	 * @return VendTxnSchedulerDetails
	 * @throws DBException
	 */
	public VendTxnSchedulerDetails getVendTxnSchedulerDetails(String transactionID)throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendTxnSchedulerDetails method");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		Transaction tx = null;
		VendTxnSchedulerDetails vendTxnSchedulerDetails;
		try{
			tx = session.beginTransaction();
			vendTxnSchedulerDetails = (VendTxnSchedulerDetails)session.get(VendTxnSchedulerDetails.class, transactionID);
		} 
		catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			vendTxnSchedulerDetails = null;
			throw new DBException();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendTxnSchedulerDetails method");
		return vendTxnSchedulerDetails;
	}
	
	/**
	 * Method used to get the list of transactions in the given statuses
	 * @param int - max record
	 * @param java.lang.String - status code
	 * @return ArrayList<VendTxnWSDetails>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<VendTxnWSDetails> getUnsentTransactions(int maxRecord,String statusCodes){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getUnsentTransactions method");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		logger.info(Logger.EVENT_UNSPECIFIED,"maxRecord "+maxRecord);
		ArrayList<VendTxnWSDetails> list = null;
		try {
			String SQL_STRING = "FROM com.centrica.vms.ws.model.VendTxnWSDetails WHERE STATUS IN("+statusCodes+") ORDER BY UPDATED_TIMESTAMP ASC";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("statuses", statusCodes);
			query.setMaxResults(maxRecord);
			list = (ArrayList<VendTxnWSDetails>)query.list();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}	
		logger.debug(Logger.EVENT_SUCCESS,"Entering getUnsentTransactions method");
		return list;
	}
	
	/**
	 * Method used to get the list of all SOAP messages
	 * @param int - max record
	 * @param java.lang.String - type 
	 * @return ArrayList<VMSMessagingSystem>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<VMSMessagingSystem> getUnsentMessages(int maxRecord, int deviceTypeID){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getUnsentMessages method");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		System.out.println("type of device : " + deviceTypeID);
		logger.info(Logger.EVENT_UNSPECIFIED,"maxRecord "+maxRecord);
		ArrayList<VMSMessagingSystem> list = new ArrayList<VMSMessagingSystem>();
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.VMSMessagingSystem WHERE DEVICE_TYPE_ID='"+deviceTypeID+"'";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING); 
			
			list = (ArrayList<VMSMessagingSystem>) query.list();
			/* 
			 * The below condition is required before setting the max results value.
			 * Otherwise Hibernate will reset the list to empty
			 */
			if (list.size() > maxRecord) { 
				query.setMaxResults(maxRecord);
			}
		} finally {
			closeSession(session);
		}
		//session.getTransaction().rollback();	
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getUnsentMessages method");
		return list;
	}
	
	/**
	 * Method used to get the count of vendcodes to resend of the given status
	 * @param ArrayList<Integer>
	 * @return ArrayList<Integer>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getResendVendCodesCount(ArrayList<Integer> statuses){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getResendVendCodesCount method");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		ArrayList<Integer> result = null;
		session.beginTransaction();
		try{
			Criteria criteria = session.createCriteria(UnsendVCTransaction.class).add(Restrictions.in("status", statuses));
			criteria.setProjection(Projections.rowCount());
			result = (ArrayList<Integer>)criteria.list(); 
			session.getTransaction().rollback();
		}catch(Exception ex){
			logger.error(Logger.EVENT_FAILURE,"Exception "+ex.getMessage());
			session.getTransaction().rollback();
		} finally {
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getResendVendCodesCount method");
		return result;
	}
}
