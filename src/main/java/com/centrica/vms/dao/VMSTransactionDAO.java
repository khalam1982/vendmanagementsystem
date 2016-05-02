/**
 * VMSTransactionDAO.java
 * Purpose: VMS Transaction DAO class
 *
 * @author ramasap1
 */
package com.centrica.vms.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.centrica.vms.exception.DBException;
import com.centrica.vms.factory.PersistenceSessionFactory;
import com.centrica.vms.model.FunctionDetails;
import com.centrica.vms.model.GroupDetails;
import com.centrica.vms.model.UserCredentials;
import com.centrica.vms.model.UserDetails;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.ws.model.VendRetryDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.transaction.query.VendTransactionUtil;

/**
 * DAO methods for VMS Transactions
 */
public class VMSTransactionDAO {

	private Logger logger = ESAPI.getLogger(VMSTransactionDAO.class);
	
	/**
	 * Method to insert the record in the table
	 * @param Object - object
	 * @return Boolean
	 */
	public Boolean insert(Object object) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering insert method");
		Boolean status = Boolean.FALSE;
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		Transaction tx = null;
		try{
		tx = session.beginTransaction();
		session.save(object);
		tx.commit();
		status = Boolean.TRUE;
		}catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			rollbackTransaction(tx);
			throw new DBException();
		} finally {
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving insert method");
		return status;
	}

	
	/**
	 * Method to insert or update the record in the table
	 * @param Object - object
	 * @return Boolean
	 */
	public Boolean insertOrUpdate(Object object) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering insertOrUpdate method");
		Boolean status = Boolean.FALSE;
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		Transaction tx = null;
		try{
		tx = session.beginTransaction();
		session.saveOrUpdate(object);
		tx.commit();
		status = Boolean.TRUE;
		}catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			rollbackTransaction(tx);
			throw new DBException();
		} finally {
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving insertOrUpdate method");
		return status;
	}
	
	/**
	 * Method to update the record in the table
	 * @param object
	 * @return Boolean
	 * @throws StaleObjectStateException
	 * @throws DBException
	 */
	public Boolean update(Object object) throws StaleObjectStateException,DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering update method");
		Boolean status = Boolean.FALSE;
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		Transaction tx = null;
		try{
		tx = session.beginTransaction();
		session.update(object);
		tx.commit(); 
		status = Boolean.TRUE;
		}catch(StaleObjectStateException ex){
			logger.error(Logger.EVENT_FAILURE,"StaleObjectStateException " + ex.getMessage());
			throw ex;
		}catch (RuntimeException e) {
		
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			throw new DBException();
		} finally {
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving update method");
		return status;
	}
	
	/**
	 * Method to delete the record in the table
	 * @param Object - object 
	 * @return Boolean
	 * @throws StaleObjectStateException
	 * @throws DBException
	 */
	public Boolean delete(Object object) throws StaleObjectStateException,DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering delete method");
		Boolean status = Boolean.FALSE;
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		Transaction tx = null;
		try{
		tx = session.beginTransaction();
		session.delete(object);
		status = Boolean.TRUE;
		tx.commit();    
		}catch(StaleObjectStateException ex){
			logger.error(Logger.EVENT_FAILURE,"StaleObjectStateException " + ex.getMessage());
			rollbackTransaction(tx);
			throw ex;
		}catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			rollbackTransaction(tx);
			throw new DBException();
		} finally {
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving delete method");
		return status;
	}
	
	/**
	 * Method to retrieve the list of vend transaction for that pan
	 * @param String - pan
	 * @return ArrayList<VendHistoryDetails>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<VendHistoryDetails> getVendHistory(String pan){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getVendHistory");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		ArrayList<VendHistoryDetails> list = null;
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.VendHistoryDetails WHERE PAN=:panNumber AND VENDCODE IS NOT NULL ORDER BY CREATED_TIMESTAMP DESC";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("panNumber", pan);
			query.setReadOnly(true);
			query.setMaxResults(45);
			session.clear();
			list = (ArrayList<VendHistoryDetails>)query.list();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getVendHistory");
		return list;
	}
	 
	
	/**
	 * Method to retrieve the list of purchase vend transactions for the given pan
	 * @param pan
	 * @param transactionTypes
	 * @param txnCount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<VendHistoryDetails> getVendHistory_Purchase(String pan,Collection<String> transactionTypes,int txnCount){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getVendHistory_Purchase");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		ArrayList<VendHistoryDetails> list = null;
		logger.info(Logger.EVENT_UNSPECIFIED,"transactionTypes : " + transactionTypes.iterator().next());
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.VendHistoryDetails WHERE PAN=:panNumber AND TRANSACTION_TYPE IN (:transactionType) ORDER BY CREATED_TIMESTAMP DESC";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("panNumber", pan);
			query.setParameterList("transactionType", transactionTypes);
			query.setReadOnly(true);
			if(txnCount != -1)
				query.setMaxResults(txnCount);
			session.clear();
			list = (ArrayList<VendHistoryDetails>)query.list();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getVendHistory_Purchase");
		return list;
	}

	/**
	 * Method to retrieve the list of adjustment vend transactions for the given pan
	 * @param pan
	 * @param transactionTypes
	 * @param txnCount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<VendHistoryDetails> getVendHistory_Adjustment(String pan,Collection<String> transactionTypes,int txnCount){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getVendHistory_Adjustment");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		ArrayList<VendHistoryDetails> list = null;
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.VendHistoryDetails WHERE PAN=:panNumber AND TRANSACTION_TYPE IN (:transactionType) ORDER BY CREATED_TIMESTAMP DESC";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("panNumber", pan);
			query.setParameterList("transactionType", transactionTypes);
			query.setReadOnly(true);
			query.setMaxResults(txnCount);
			session.clear();
			list = (ArrayList<VendHistoryDetails>)query.list();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getVendHistory_Adjustment");
		return list;
	}
	
	/**
	 * Method to retrieve the list of void transactions for the given pan
	 * @param transactionID
	 * @param transactionTypes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<VendHistoryDetails> getVendHistory_Void(String transactionID,Collection<String> transactionTypes){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getVendHistory_Void");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		ArrayList<VendHistoryDetails> list = null;
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.VendHistoryDetails WHERE ORIGINAL_TRANSACTION_ID=:txnID AND TRANSACTION_TYPE IN (:transactionType) ORDER BY CREATED_TIMESTAMP DESC";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("txnID", transactionID);
			query.setParameterList("transactionType", transactionTypes);
			query.setReadOnly(true);
			query.setMaxResults(1);
			session.clear();
			list = (ArrayList<VendHistoryDetails>)query.list();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getVendHistory_Void");
		return list;
	}
	
	
	/**
	 * Method to retrieve the User Details
	 * @param String - user name
	 * @return ArrayList<UserDetails>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<UserDetails> getUserDetails(String userName){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getUserDetails");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession(); 
		ArrayList<UserDetails> list = null;
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.UserDetails WHERE USERNAME=:user";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("user", userName);
			list = (ArrayList<UserDetails>)query.list();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		//session.getTransaction().rollback();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getUserDetails");
		return list;
	}

	
	/**
	 *  Method to retrieve the power User Detail for the given group id
	 * @param String - group ID
	 * @return ArrayList<UserDetails>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<UserDetails> getPowerUserDetails(String groupID){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getPowerUserDetails");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		ArrayList<UserDetails> list = null;
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.UserDetails WHERE POWER_IND='Y' AND GROUP_ID=:group";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("group", groupID);
			list = (ArrayList<UserDetails>)query.list();
			//session.getTransaction().rollback();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getPowerUserDetails");
		return list;
	}
	
	/**
	 * Method to retrieve the  User Detail for the given group id
	 * @param String - group ID
	 * @return ArrayList<UserDetails>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<UserDetails> getGroupUsers(String groupID){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getGroupUsers");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		ArrayList<UserDetails> list = null;
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.UserDetails WHERE POWER_IND='N' AND GROUP_ID=:group";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("group", groupID);
			list = (ArrayList<UserDetails>)query.list();
		//	session.getTransaction().rollback();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getGroupUsers");
		return list;
	}
	
	/**
	 * Method to get the group details expect the group id passed
	 * @param String - group ID
	 * @return ArrayList<GroupDetails>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<GroupDetails> getGroupDetails(String groupID){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getGroupDetails");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession(); 
		ArrayList<GroupDetails> list = null;
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.GroupDetails WHERE GROUP_ID !=:group";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("group", groupID);
			list = (ArrayList<GroupDetails>)query.list();
			//session.getTransaction().rollback();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getGroupDetails");
		return list;
	}
    //CEN_1 start
	/**
	 * Method used to retrieve the list of functions
	 * @param Collection<String> - functions
	 * @return ArrayList<FunctionDetails>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<FunctionDetails> getFunctionDetails(Collection<String> functions){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getFunctionDetails");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession(); 
		ArrayList<FunctionDetails> list = null;
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.FunctionDetails WHERE FUNCTION_CODE IN (:function)";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameterList("function", functions);
			list = (ArrayList<FunctionDetails>)query.list();
			//session.getTransaction().rollback();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getFunctionDetails");
		return list;
	}
	//CEN_1 end
	/**
	 * Method used to retrieve the user credentials for the user
	 * @param String - userName
	 * @return ArrayList<UserCredentials>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<UserCredentials> getUserCrendentials(String userName){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getUserCrendentials");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		ArrayList<UserCredentials> list  = null;
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.UserCredentials WHERE USERNAME=:user";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("user", userName);
			list = (ArrayList<UserCredentials>)query.list();
			//session.getTransaction().rollback();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getUserCrendentials");
		return list;
	}

	
	/**
	 * Method used to get the count of vendcodes to resend of the given status
	 * @param String type
	 * @return Integer
	 */
	@SuppressWarnings("unchecked")
	public Integer getResendTransactionCount(Integer type){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getResendTransactionCount");
		Integer count = 0;
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		session.beginTransaction();
		ArrayList<Integer> result = null;
		try {
			result = (ArrayList<Integer>) session.createCriteria(VMSMessagingSystem.class).add(
				Restrictions.eq("deviceTypeID", type)).setProjection(Projections.rowCount()).list();	
		}catch(Exception ex){
			logger.error(Logger.EVENT_FAILURE,"Exception "+ex.getMessage());
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		if(result!=null && result.size()!=0){
			count = result.get(0);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getResendTransactionCount");
		return count;
	}
	
	/**
	 * @return
	 */
	public ArrayList<VendTxnWSDetails> getVendTransactionDetails(int min, int max) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendTransactionDetails method");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		session.beginTransaction();
		ArrayList<VendTxnWSDetails> result = null;
		Calendar minCalendar = Calendar.getInstance();
		Calendar maxCalendar = Calendar.getInstance();
		min = min / 1440;
		max = max / 1440;
		minCalendar.add(Calendar.DAY_OF_MONTH, -min); // TODO: 48 hours before the current date
		minCalendar.add(Calendar.HOUR, -1); // TODO: 48 hours before the current date 
		Date eDate = minCalendar.getTime();
		maxCalendar.add(Calendar.DAY_OF_MONTH, -max); // TODO: 120 hours before the current date
		Date sDate = maxCalendar.getTime();
		try {
			Criteria crit = session.createCriteria(VendTxnWSDetails.class);
			crit.add(Restrictions.and(Restrictions.between("createdTimeStamp", sDate, eDate), 
					Restrictions.not(Restrictions.isNull("vendCode")))); 
			result = (ArrayList<VendTxnWSDetails>) crit.list();	
		}catch(Exception ex){
			logger.error(Logger.EVENT_FAILURE,"Exception "+ex.getMessage());
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendTransactionDetails method");
		return result;
	}
	
	/**
	 * Method to roll back the transaction
	 * @param Transaction
	 * @return
	 */
	protected void rollbackTransaction(Transaction tx){
		logger.debug(Logger.EVENT_SUCCESS,"Entering rollbackTransaction method");
		if (tx != null && tx.isActive()) {
			try {
				tx.rollback();
			} catch (HibernateException e1) {
				logger.error(Logger.EVENT_FAILURE,"HibernateException " + e1.getMessage());
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving rollbackTransaction method");
	}
	@SuppressWarnings("unchecked")
	public ArrayList<VendHistoryDetails> getVendPurchaseTransactionsForGivenDateRange(String pan,
			Collection<String> transactionTypes, int txnCount,
			Calendar startDate, Calendar endDate){ 
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getVendPurchaseTransactionsForGivenDateRange");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		ArrayList<VendHistoryDetails> list = null;
		VendTransactionUtil util = new VendTransactionUtil();
		Date startDateWithoutTime = util.getFormattedDate(startDate);
		Date endDateWithoutTime = util.getFormattedDate(endDate);
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.VendHistoryDetails WHERE PAN=:panNumber AND TRANSACTION_TYPE IN (:transactionType)"
				+ "AND to_date(to_char(CREATED_TIMESTAMP, 'yyyy-MM-dd'), 'yyyy-MM-dd') BETWEEN :startDate AND :endDate";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("panNumber", pan);
			query.setParameterList("transactionType", transactionTypes);
			query.setParameter("startDate", startDateWithoutTime);
			query.setParameter("endDate", endDateWithoutTime);
			query.setReadOnly(true);
			if(txnCount != -1)
				query.setMaxResults(txnCount);
			session.clear();
			list = (ArrayList<VendHistoryDetails>) query.list();
		}catch(RuntimeException e){
    		try{
    			rollbackTransaction(session.getTransaction());
    		}catch(RuntimeException rbe){
    			logger.error(Logger.EVENT_FAILURE,"Couldn’t roll back transaction", rbe);
    		}
    		throw e;
    	}finally{
    		logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block");   
    		closeSession(session);
    	}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getVendPurchaseTransactionsForGivenDateRange");
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<VendHistoryDetails> getAllVendPurchaseTransactions(Collection<String> transactionTypes){ 
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getAllVendPurchaseTransactions");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		ArrayList<VendHistoryDetails> list = null;
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.VendHistoryDetails WHERE TRANSACTION_TYPE IN (:transactionType) ORDER BY CREATED_TIMESTAMP DESC";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameterList("transactionType", transactionTypes);
			query.setReadOnly(true);
			session.clear();
			list = (ArrayList<VendHistoryDetails>) query.list();
		}catch(RuntimeException e){
    		try{
    			rollbackTransaction(session.getTransaction());
    		}catch(RuntimeException rbe){
    			logger.error(Logger.EVENT_FAILURE,"Couldn’t roll back transaction", rbe);
    		}
    		throw e;
    	}finally{
    		logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block");    	
    		closeSession(session);
    	}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getAllVendPurchaseTransactions");
		return list;
	}
	
	public VendRetryDetails getVendRetryCount(String transactionId) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendRetryStatus method");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		Transaction tx = null;
		VendRetryDetails vendRetryDetails;
		try {
			tx = session.beginTransaction();
			vendRetryDetails = (VendRetryDetails) session.get(VendRetryDetails.class, transactionId);
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			vendRetryDetails = null;
			throw new DBException();
		} finally{
			logger.debug(Logger.EVENT_SUCCESS,"Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getPPKeyTxnDetails method");
		return vendRetryDetails;
	}
	
	protected void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
	
}
