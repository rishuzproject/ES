package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.BudgetFormDje;
import com.elecnor.ecosystem.bean.BudgetFormEquip;
import com.elecnor.ecosystem.bean.BudgetFormIndirect;
import com.elecnor.ecosystem.bean.BudgetFormLabor;
import com.elecnor.ecosystem.bean.BudgetFormMat;
import com.elecnor.ecosystem.bean.BudgetFormProjectAdmin;
import com.elecnor.ecosystem.bean.BudgetFormSubContractor;
import com.elecnor.ecosystem.dao.RfcBudgetFormDAO;

@Repository
public class RfcBudgetFormServiceDAOImpl implements RfcBudgetFormDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void addMaterialFormData(BudgetFormMat materialBudgetBean)
			throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.merge(materialBudgetBean);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}

	@Override
	@Transactional
	public void addSubContractorFormData(
			BudgetFormSubContractor subContractorBudgetBean) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.merge(subContractorBudgetBean);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}

	@Override
	@Transactional
	public void addDJEFormData(BudgetFormDje djeBudgetBean) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.merge(djeBudgetBean);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}

	@Override
	@Transactional
	public void addEquipFormData(BudgetFormEquip equipBudgetBean)
			throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.merge(equipBudgetBean);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}

	@Override
	@Transactional
	public void addIndirectFormData(BudgetFormIndirect indirectBudgetBean)
			throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.merge(indirectBudgetBean);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}

	@Override
	@Transactional
	public void addProjectAdminFormData(BudgetFormProjectAdmin projectAdminBudgetBean) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.merge(projectAdminBudgetBean);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}

	@Override
	@Transactional
	public void addLaborFormData(BudgetFormLabor laborBudgetBean)
			throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.merge(laborBudgetBean);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<BudgetFormMat> getMaterialBudgetData(String rfcLogId) throws Exception {
		Session hybSes = sessionFactory.getCurrentSession();
		Query query = hybSes.createQuery("from BudgetFormMat where RFC_LOG_ID = :rfcLogId");
		query.setParameter("rfcLogId", rfcLogId);
		ArrayList<BudgetFormMat> allMaterialBudgetDetails = (ArrayList<BudgetFormMat>) query.list();
		return allMaterialBudgetDetails;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<BudgetFormSubContractor> getSubContractorBudgetData(String rfcLogId) throws Exception {
		Session hybSes = sessionFactory.getCurrentSession();
		Query query = hybSes.createQuery("from BudgetFormSubContractor where RFC_LOG_ID = :rfcLogId");
		query.setParameter("rfcLogId", rfcLogId);
		ArrayList<BudgetFormSubContractor> allSubContractorBudgetDetails = (ArrayList<BudgetFormSubContractor>) query.list();
		return allSubContractorBudgetDetails;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<BudgetFormLabor> getLaborBudgetData(String rfcLogId) throws Exception {
		Session hybSes = sessionFactory.getCurrentSession();
		Query query = hybSes.createQuery("from BudgetFormLabor where RFC_LOG_ID = :rfcLogId");
		query.setParameter("rfcLogId", rfcLogId);
		ArrayList<BudgetFormLabor> allLaborBudgetDetails = (ArrayList<BudgetFormLabor>) query.list();
		return allLaborBudgetDetails;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<BudgetFormEquip> getEquipBudgetData(String rfcLogId) throws Exception {
		Session hybSes = sessionFactory.getCurrentSession();
		Query query = hybSes.createQuery("from BudgetFormEquip where RFC_LOG_ID = :rfcLogId");
		query.setParameter("rfcLogId", rfcLogId);
		ArrayList<BudgetFormEquip> allEquipBudgetDetails = (ArrayList<BudgetFormEquip>) query.list();
		return allEquipBudgetDetails;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<BudgetFormDje> getDirectJobBudgetData(String rfcLogId) throws Exception {
		Session hybSes = sessionFactory.getCurrentSession();
		Query query = hybSes.createQuery("from BudgetFormDje where RFC_LOG_ID = :rfcLogId");
		query.setParameter("rfcLogId", rfcLogId);
		ArrayList<BudgetFormDje> allDirectJobBudgetDetails = (ArrayList<BudgetFormDje>) query.list();
		return allDirectJobBudgetDetails;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<BudgetFormIndirect> getIndirectBudgetData(String rfcLogId) throws Exception {
		Session hybSes = sessionFactory.getCurrentSession();
		Query query = hybSes.createQuery("from BudgetFormIndirect where RFC_LOG_ID = :rfcLogId");
		query.setParameter("rfcLogId", rfcLogId);
		ArrayList<BudgetFormIndirect> allIndirectBudgetDetails = (ArrayList<BudgetFormIndirect>) query.list();
		return allIndirectBudgetDetails;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<BudgetFormProjectAdmin> getProjAdminBudgetData(String rfcLogId) throws Exception {
		Session hybSes = sessionFactory.getCurrentSession();
		Query query = hybSes.createQuery("from BudgetFormProjectAdmin where RFC_LOG_ID = :rfcLogId");
		query.setParameter("rfcLogId", rfcLogId);
		ArrayList<BudgetFormProjectAdmin> allProjAdminBudgetDetails = (ArrayList<BudgetFormProjectAdmin>) query.list();
		return allProjAdminBudgetDetails;
	}

	
}
