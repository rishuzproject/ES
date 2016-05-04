package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.ApplicationInvoice;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ApplicationInvoiceDAO;

@Repository
public class ApplicationInvoiceDAOImpl implements ApplicationInvoiceDAO {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<ApplicationInvoice> getAllInvoiceDetails(long signUpDomainId) throws Exception {
		
		ArrayList<ApplicationInvoice> allInvoiceDetails = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from ApplicationInvoice where STATUS = 'ACTIVE' and DOMAIN_ID =:signUpDomainId");
			query.setParameter("signUpDomainId", signUpDomainId);
			allInvoiceDetails = (ArrayList<ApplicationInvoice>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return allInvoiceDetails;
	}

	@Override
	@Transactional
	public ApplicationInvoice setSaveInvoice(ApplicationInvoice applicationInvoiceDetail) throws Exception {
		ApplicationInvoice result = null;
		try {
			Session hibses = sessionFactory.getCurrentSession();
			result = (ApplicationInvoice) hibses.merge(applicationInvoiceDetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public String setUpdateInvoice(ApplicationInvoice applicationInvoiceDetail) throws Exception {
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update ApplicationInvoice set "
									+ "DOMAIN_ID = :domainId,INVOICE_GENERATED_DATE = :invoiceGeneratedDate, INVOICE_DUE_DATE = :invoiceDueDate,"
									+ "UPDATED_BY = :updatedBy, UPDATED_DATE = :updatedDate, IS_LATE_FEES_APPLICABLE = :isLateFee, IS_INTEREST_APPLICABLE = :isInterest,"
									+ "ADDITIONAL_CHARGES = :additionalCharges,INVOICE_TYPE = :invoiceType where INVOICE_ID = :invoiceId");
			query.setParameter("invoiceId", applicationInvoiceDetail.getInvoiceId());
			query.setParameter("domainId", applicationInvoiceDetail.getDomainDetail());
			query.setParameter("invoiceGeneratedDate", applicationInvoiceDetail.getInvoiceGeneratedDate());
			query.setParameter("invoiceDueDate", applicationInvoiceDetail.getInvoiceDueDate());
			query.setParameter("updatedBy", applicationInvoiceDetail.getUpdatedBy());
			query.setParameter("updatedDate", applicationInvoiceDetail.getUpdatedDate());
			query.setParameter("isLateFee", applicationInvoiceDetail.isLateFeesApplicable());
			query.setParameter("isInterest", applicationInvoiceDetail.isInterestApplicable());
			query.setParameter("additionalCharges", applicationInvoiceDetail.getAdditionalCharges());
			query.setParameter("invoiceType", applicationInvoiceDetail.getInvoiceType());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public String setApplicationInvoiceToDelete(Long invoiceId, UserDetail userDetails) throws Exception {
		String result = null;
		try {
			Session hibses = sessionFactory.getCurrentSession();
			Query query = hibses
					.createQuery("update ApplicationInvoice set STATUS = :status,UPDATED_BY=:updBy, UPDATED_DATE=:updDate where INVOICE_ID = :invoiceId");
			query.setParameter("invoiceId", invoiceId);
			query.setParameter("updBy", userDetails.getUserId());
			query.setParameter("updDate", new Date());
			query.setParameter("status", "INACTIVE");
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public String setApplicationInvoiceToCancel(Long invoiceId, UserDetail userDetails) throws Exception {
		String result = null;
		try {
			Session hibses = sessionFactory.getCurrentSession();
			Query query = hibses
					.createQuery("update ApplicationInvoice set INVOICE_TYPE = :invoiceType,UPDATED_BY=:updBy, UPDATED_DATE=:updDate where INVOICE_ID = :invoiceId");
			query.setParameter("invoiceId", invoiceId);
			query.setParameter("updBy", userDetails.getUserId());
			query.setParameter("updDate", new Date());
			query.setParameter("invoiceType", "Cancelled");
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<ApplicationInvoice> getInvoiceDetails(long domainId) throws Exception {
		ArrayList<ApplicationInvoice> allInvoiceDetails = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from ApplicationInvoice where STATUS = 'ACTIVE' and DOMAIN_ID=:domainId");
			query.setParameter("domainId", domainId);
			allInvoiceDetails = (ArrayList<ApplicationInvoice>) query.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return allInvoiceDetails;
	}

}
