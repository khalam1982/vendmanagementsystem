/**
 * WSTransactionDAO.java
 * Purpose: WS Transaction DAO class
 * @author ramasap1
 */
package com.centrica.vms.ws.DAO;

import java.util.ArrayList;
import java.util.Date;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.MPxNNotFoundException;
import com.centrica.vms.exception.MSNNotFoundException;
import com.centrica.vms.exception.PanNotFoundException;
import com.centrica.vms.factory.PersistenceSessionFactory;
import com.centrica.vms.model.PPKeyTransaction;
import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.model.UserDetails;
import com.centrica.vms.ws.model.CreditIDCompKey;
import com.centrica.vms.ws.model.CreditIDDetails;
import com.centrica.vms.ws.model.CustomerDetails;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.PremiseDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;
/**
 * Methods for WS Transaction DAO
 * @see VMSTransactionDAO
 */
public class WSTransactionDAO extends VMSTransactionDAO {

	private Logger logger = ESAPI.getLogger(getClass().getName());

	/** 
	 * Method to get the vend transaction details
	 * @param transactionID
	 * @return
	 * @throws DBException
	 */
	public VendTxnWSDetails getVendTxnWSDetails(String transactionID) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendTxnWSDetails method");
		Session session = PersistenceSessionFactory.getInstance()
				.getCurrentSession();
		Transaction tx = null;
		VendTxnWSDetails vendTxnWSDetails;
		try {
			tx = session.beginTransaction();
			vendTxnWSDetails = (VendTxnWSDetails) session.get(
					VendTxnWSDetails.class, transactionID);
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			vendTxnWSDetails = null;
			throw new DBException();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendTxnWSDetails method");
		return vendTxnWSDetails;
	}
	
	/**
	 * @param msn
	 * @param vendcode
	 * @return
	 * @throws DBException
	 */
	public String getVendTxnWSDetails(String msn, String vendcode)throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendTxnWSDetails method");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		session.beginTransaction();
		ArrayList<VendTxnWSDetails> result = null;
		String transactionID = null;
		try {
			Criteria crit = session.createCriteria(VendTxnWSDetails.class);
			crit.add(Restrictions.eq("msn", msn));
			crit.add(Restrictions.eq("vendCode", vendcode));
			result = (ArrayList<VendTxnWSDetails>) crit.list();
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			throw new DBException();
		} finally {
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}

		if (result != null && result.size() > 0) {
			logger.info(Logger.EVENT_UNSPECIFIED,"size : " + result.size());
			VendTxnWSDetails vendTxnWSDetails = result.get(0);
			transactionID = vendTxnWSDetails.getTransactionID();
		}
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendTxnWSDetails method");
		return transactionID;
	}

	/** 
	 * Method to get the void transaction ID for the original transaction.
	 * @param originalTransactionID
	 * @return
	 * @throws DBException
	 */
	@SuppressWarnings("unchecked")
	public String getVoidTxnID(String originalTransactionID) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVoidTxnID method");
		Session session = PersistenceSessionFactory.getInstance()
				.getCurrentSession();
		session.beginTransaction();
		ArrayList<VendTxnWSDetails> result = null;
		String voidTransactionID = null;
		try {
			Criteria crit = session.createCriteria(VendTxnWSDetails.class);
			crit.add(Restrictions.eq("originalTransactionID", originalTransactionID));
			crit.add(Restrictions.eq("transactionType", "Void"));
			result = (ArrayList<VendTxnWSDetails>) crit.list();
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			throw new DBException();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
			session = null;
		}
		
		if (result != null && result.size() > 0) {
			logger.info(Logger.EVENT_UNSPECIFIED,"size : " + result.size());
			VendTxnWSDetails vendTxnWSDetails = result.get(0);
			voidTransactionID = vendTxnWSDetails.getTransactionID();
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVoidTxnID method");
		return voidTransactionID;
	}
	
	/**
	 * Method to get the credit id details
	 * @param creditIDCompKey
	 * @return
	 * @throws DBException
	 */
	public int getCreditIDDetails(CreditIDCompKey creditIDCompKey) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering getCreditIDDetails method");
		CreditIDDetails creditIDDetails = null;
		Session session = PersistenceSessionFactory.getInstance()
				.getCurrentSession();
		Transaction tx = null;
		int creditID = -1;
		try {
			tx = session.beginTransaction();
			creditIDDetails = (CreditIDDetails) session.get(
					CreditIDDetails.class, creditIDCompKey);
			creditID = processCreditID(creditIDDetails, creditIDCompKey);
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			creditIDDetails = null;
			throw new DBException();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getCreditIDDetails method");
		return creditID;
	}

	/**
	 * Method to process the credit id.
	 * @param creditIDDetails
	 * @param creditIDCompKey
	 * @return
	 * @throws DBException
	 */
	private int processCreditID(CreditIDDetails creditIDDetails,
			CreditIDCompKey creditIDCompKey) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering processCreditID method");
		int creditID;
		if (creditIDDetails == null) {
			creditID = -1;
			creditIDDetails = new CreditIDDetails();
		} else {
			creditID = creditIDDetails.getCreditID();
		}
		creditID = new VMSUtils().processCreditIDValue(creditIDCompKey.getTransactionType(),
				creditID);
		creditIDDetails.setCreditIDCompKey(creditIDCompKey);
		creditIDDetails.setCreditID(creditID);
		this.insert(creditIDDetails);
		logger.info(Logger.EVENT_UNSPECIFIED,"Credit ID is " + creditID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processCreditID method");
		return creditID;
	}

	/**
	 * Method used to get meter details for given msn
	 * @param msn
	 * @return
	 * @throws DBException
	 * @throws MSNNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public MeterDetails getMeterDetails(String msn) throws DBException,MSNNotFoundException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering getMeterDetails method");
		Session session = PersistenceSessionFactory.getInstance()
				.getCurrentSession();
		Transaction tx = null;
		MeterDetails meterDetails = null;
		String SQL_STRING = "FROM com.centrica.vms.ws.model.MeterDetails WHERE MSN=?";
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setString(0, msn);
			ArrayList<MeterDetails> list = (ArrayList<MeterDetails>)query.list();
			if(list!=null && list.size()>0){
				meterDetails = list.get(0);
			}else{
				throw new MSNNotFoundException();
			}
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			meterDetails = null;
			throw new DBException();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getMeterDetails method");
		return meterDetails;
	}
	
	/**
	 * Method used to get customer details for given msn
	 * @param pan
	 * @return
	 * @throws DBException
	 * @throws MSNNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public CustomerDetails getCustomerDetails(String pan) throws DBException,PanNotFoundException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering getCustomerDetails method");
		Session session = PersistenceSessionFactory.getInstance()
				.getCurrentSession();
		Transaction tx = null;
		CustomerDetails customerDetails = null;
		String SQL_STRING = "FROM com.centrica.vms.ws.model.CustomerDetails WHERE PAN=?";
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setString(0, pan);
			ArrayList<CustomerDetails> list = (ArrayList<CustomerDetails>)query.list();
			if(list!=null && list.size()>0){
				customerDetails = list.get(0);
			}else{
				throw new PanNotFoundException();
			}
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			customerDetails = null;
			throw new DBException();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getCustomerDetails method");
		return customerDetails;
	}

	/**
	 * Method used to get premise details for given MPxN
	 * @param pan
	 * @return
	 * @throws DBException
	 * @throws MPxNNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public PremiseDetails getPremiseDetails(String mpxn) throws DBException,MPxNNotFoundException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering getPremiseDetails method");
		Session session = PersistenceSessionFactory.getInstance()
				.getCurrentSession();
		Transaction tx = null;
		PremiseDetails premiseDetails = null;
		String SQL_STRING = "FROM com.centrica.vms.ws.model.PremiseDetails WHERE MPXN=?";
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setString(0, mpxn);
			ArrayList<PremiseDetails> list = (ArrayList<PremiseDetails>)query.list();
			if(list!=null && list.size()>0){
				premiseDetails = list.get(0);
			}else{
				throw new MPxNNotFoundException();
			}
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			premiseDetails = null;
			throw new DBException();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getPremiseDetails method");
		return premiseDetails;
	}
	
	/**
	 * Method to retrieve the source details
	 * 
	 * @param source
	 * @return
	 */
	public SourceDetails getSourceDetails(String source) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering getSourceDetails method");
		Session session = PersistenceSessionFactory.getInstance()
				.getCurrentSession();
		Transaction tx = null;
		SourceDetails sourceDetails;
		try {
			tx = session.beginTransaction();
			sourceDetails = (SourceDetails) session.get(SourceDetails.class,
					source);
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			sourceDetails = null;
			throw new DBException();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getSourceDetails method");
		return sourceDetails;
	}

	/**
	 * Method to decrement the credit value for the pan
	 * @param creditIDCompKey
	 * @return
	 */
	public int rollBackCreditValue(CreditIDCompKey creditIDCompKey) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering getCreditIDDetails method");
		CreditIDDetails creditIDDetails = null;
		Session session = PersistenceSessionFactory.getInstance()
				.getCurrentSession();
		Transaction tx = null;
		int creditID = -1;
		try {
			tx = session.beginTransaction();
			creditIDDetails = (CreditIDDetails) session.get(
					CreditIDDetails.class, creditIDCompKey);
			processRollBackCreditValue(creditIDDetails);
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			creditIDDetails = null;
			throw new DBException();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getCreditIDDetails method");
		return creditID;
	}
	
	/**
	 * Method to get the latest vend timestamp for the gas purchase transaction
	 * @param msn
	 * @param creditValue
	 * @param day
	 * @param month
	 * @param year
	 * @param hour
	 * @return
	 */
	public Date getVendTimeStamp(String msn,String creditValue,int day,int month,int year,int hour,TransactionType transactionType){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getVendTimeStamp");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		String SQL_STRING = "SELECT max(vendcodeTimeStamp) FROM com.centrica.vms.ws.model.VendTxnWSDetails WHERE MSN= :msnNumber AND CREDIT_VALUE =:vendAmount " +
				"AND ((DAY(VENDCODE_TIMESTAMP)> :vendDay AND MONTH(VENDCODE_TIMESTAMP)>= :vendMonth AND YEAR(VENDCODE_TIMESTAMP)>= :vendYear " +
					" )OR (DAY(VENDCODE_TIMESTAMP)= :vendDay AND MONTH(VENDCODE_TIMESTAMP)= :vendMonth AND YEAR(VENDCODE_TIMESTAMP)= :vendYear " +
							"AND HOUR(VENDCODE_TIMESTAMP)>= :vendHour )) AND TRANSACTION_TYPE = '"+transactionType.getTransactionType()+"' ";
		Date vendcodeTimeStamp = null;
		try{
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("msnNumber", msn);
			query.setParameter("vendAmount", creditValue);
			query.setParameter("vendDay", day);
			query.setParameter("vendMonth", month);
			query.setParameter("vendYear", year);
			query.setParameter("vendHour", hour);
			query.setReadOnly(true);
			session.clear();
			ArrayList<Date> list = (ArrayList<Date>)query.list();
		
			if(list!=null && list.size()>0){
				vendcodeTimeStamp = list.get(0);
			}
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}	
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getVendTimeStamp");
		return vendcodeTimeStamp; 
	}

	/**
	 * Method to validate the user status
	 * @param userName
	 * @param password
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Boolean getUserStatus(String userName,String password)throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering the method getUserStatus");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession(); 
		String SQL_STRING = "FROM com.centrica.vms.model.UserDetails WHERE USERNAME= :user AND PASSWORD=:pass AND GROUP_ID='G4'";
		Boolean userStatus = Boolean.FALSE;
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setParameter("user", userName);
			query.setParameter("pass", password);
			ArrayList<UserDetails> list = (ArrayList<UserDetails>)query.list();
			if(list!=null && list.size()>0){
				userStatus = Boolean.TRUE;
			}
			tx.rollback();
		}catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			rollbackTransaction(tx);
			throw new DBException();
		} finally {
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method getUserStatus");
		return userStatus;
	}

	/**
	 * Method to fetch the total number of purchase transactions for the given date
	 * 
	 * @param pan of type String
	 * @param date of type String
	 * @param transType of type String
	 * @return integer
	 */
	public int fetchTotalPurchaseTransactions(final String pan, final String date, final String transType)throws DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering the method fetchTotalPurchaseTransactions");
		int result = 0;
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession(); 
		final String SQL_STRING = "FROM com.centrica.vms.ws.model.VendTxnWSDetails WHERE PAN= :pan AND TO_CHAR(CREATED_TIMESTAMP, 'DD/MM/YYYY') =:date " +
		"AND TRANSACTION_TYPE =:transType";

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			final Query query = session.createQuery(SQL_STRING);
			query.setParameter("pan", pan);
			query.setParameter("date", date);
			query.setParameter("transType", transType);
			result = query.list() != null && query.list().size() > 0 ?  query.list().size() : 0; 
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			rollbackTransaction(tx);
			throw new DBException();
		} finally {
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the method fetchTotalPurchaseTransactions");
		return result;

	}

	/**
	 * Method to get the decremented value and update them
	 * @param creditIDDetails
	 * @throws DBException
	 */
	private void processRollBackCreditValue(CreditIDDetails creditIDDetails)
			throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processRollBackCreditValue method");
		creditIDDetails =  new VMSUtils().decrementCreditIDValue(creditIDDetails);
		this.update(creditIDDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processRollBackCreditValue method");
	}
	
	/**
	 * Method used to get premise details for given MSN
	 * @param pan
	 * @return
	 * @throws DBException
	 * @throws MPxNNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public PremiseDetails getPremiseDetailsByMSN(final String msn) throws DBException,MPxNNotFoundException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering getPremiseDetailsByMSN method");
		Session session = PersistenceSessionFactory.getInstance()
		.getCurrentSession();
		Transaction tx = null;
		PremiseDetails premiseDetails = null;
		String SQL_STRING = "FROM com.centrica.vms.ws.model.PremiseDetails WHERE MSN=?";
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setString(0, msn);
			ArrayList<PremiseDetails> list = (ArrayList<PremiseDetails>)query.list();
			if(list!=null && list.size()>0){
				premiseDetails = list.get(0);
			}else{
				throw new MPxNNotFoundException();
			}
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			premiseDetails = null;
			throw new DBException();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getPremiseDetailsByMSN method");
		return premiseDetails;

	}

	/** 
	 * Fetches PP Key transaction details
	 * 
	 * @param transactionId
	 * @return getPPKeyTxnDetails
	 * @throws DBException
	 */
	public PPKeyTransaction getPPKeyTxnDetails(final String transactionId) throws DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering getPPKeyTxnDetails method");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		Transaction tx = null;
		PPKeyTransaction ppKeyTxnDetails;
		try {
			tx = session.beginTransaction();
			ppKeyTxnDetails = (PPKeyTransaction) session.get(PPKeyTransaction.class, transactionId);
		} catch (RuntimeException e) {
			logger.error(Logger.EVENT_FAILURE,"RuntimeException " + e.getMessage());
			ppKeyTxnDetails = null;
			throw new DBException();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getPPKeyTxnDetails method");
		return ppKeyTxnDetails;
	}

	/*@SuppressWarnings("unchecked")
	public boolean getRetryEligibility(int status) throws DBException {
		logger.info("Entering getRetryEligibility method");
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		Transaction tx = null;
		String sql = "SELECT COUNT(1) FROM VEND_RETRY_ELIGIBLITY WHERE STATUS_CODE=:statusCode";
		boolean retriable = false;
		try {
			tx = session.beginTransaction();
			Query query = session.createSQLQuery(sql);
			query.setParameter("statusCode", status);
			List<BigDecimal> list = (ArrayList<BigDecimal>)query.list();
			retriable = list!=null && !list.isEmpty() && list.get(0) != null && list.get(0).intValue()>0;
		} catch (RuntimeException e) {
			logger.info("RuntimeException " + e.getMessage());
			throw new DBException();
		} finally{
			logger.info("Reached finally block ");
			rollbackTransaction(tx);
			closeSession(session);
		}
		tx = null;
		session = null;
		logger.info("Leaving getRetryEligibility method");
		return retriable;
	}*/

	

}
	
