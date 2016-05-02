/**
 * 
 */
package com.centrica.vms.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.centrica.vms.factory.PersistenceSessionFactory;
import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.model.StatusDetails;
import com.centrica.vms.model.VendReportDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;

/**
 * @author nagarajk
 *
 */
public class VendReportTransactionDAO extends VMSTransactionDAO {
	
	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SourceDetails> getVendSourceDetails() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering method getVendSourceDetails");
		ArrayList<SourceDetails> sourceLst = null;
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.SourceDetails";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setReadOnly(true);
			session.clear();
			sourceLst = (ArrayList<SourceDetails>)query.list();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving method getVendSourceDetails");
		return sourceLst;
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<StatusDetails> getVendStatusDetails() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering method getVendStatusDetails");
		ArrayList<StatusDetails> statusLst = null;
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		try {
			String SQL_STRING = "FROM com.centrica.vms.model.StatusDetails";
			session.beginTransaction();
			Query query = session.createQuery(SQL_STRING);
			query.setReadOnly(true);
			session.clear();
			statusLst = (ArrayList<StatusDetails>)query.list();
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving method getVendStatusDetails");
		return statusLst;
	}
	
	/**
	 * @param startDateRange
	 * @param endDateRange
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<VendReportDetails> getReportData(Date startDateRange,Date endDateRange, String source) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getReportData method");
		Collection<String> transactionTypes = new ArrayList<String>();
		transactionTypes.add(TransactionType.Void.getTransactionType());
		transactionTypes.add(TransactionType.Reversal.getTransactionType());
		Session session = PersistenceSessionFactory.getInstance().getCurrentSession();
		session.beginTransaction();
		ArrayList<VendReportDetails> result = null;
		try {
			Criteria crit = session.createCriteria(VendReportDetails.class);
			crit.add(Restrictions.between("createdTimeStamp", startDateRange, endDateRange));
			crit.add(Restrictions.not(Restrictions.in("transactionType", transactionTypes)));
			if (source != null && !source.isEmpty()) {
				SourceDetails sourceDetails = new SourceDetails();
				sourceDetails.setSource(source);
				crit.add(Restrictions.eq("sourceDetails", sourceDetails));
			}
			result = (ArrayList<VendReportDetails>)crit.list();
		}catch(Exception ex){
			logger.error(Logger.EVENT_FAILURE,"Exception "+ex.getMessage());
		}finally{
			logger.info(Logger.EVENT_UNSPECIFIED,"Reached finally block ");
			rollbackTransaction(session.getTransaction());
			closeSession(session);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getReportData method");
		return result;
	}
}
