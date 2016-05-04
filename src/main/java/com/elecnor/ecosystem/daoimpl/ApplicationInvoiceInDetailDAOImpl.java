package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.ApplicationInvoicePlanMapping;
import com.elecnor.ecosystem.dao.ApplicationInvoiceInDetailDAO;

@Repository
public class ApplicationInvoiceInDetailDAOImpl implements ApplicationInvoiceInDetailDAO {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ApplicationInvoicePlanMapping> getAllResults(long invoiceId) throws Exception {
		ArrayList<ApplicationInvoicePlanMapping> allResult = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from ApplicationInvoicePlanMapping where INVOICE_ID =:invoiceId");
			query.setParameter("invoiceId", invoiceId);
			allResult = (ArrayList<ApplicationInvoicePlanMapping>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return allResult;
	}

	@Override
	@Transactional
	public String addPlansToInvoice(ApplicationInvoicePlanMapping applicationInvoicePlanMapping) {

		String result = null;
		try {
			Session hibses = sessionFactory.getCurrentSession();
			hibses.merge(applicationInvoicePlanMapping);
		} catch (Exception e) {
			e.printStackTrace();
			//throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ApplicationInvoicePlanMapping> getAllResults() throws Exception {
		ArrayList<ApplicationInvoicePlanMapping> allResult = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from ApplicationInvoicePlanMapping");
			allResult = (ArrayList<ApplicationInvoicePlanMapping>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return allResult;
	}
}
