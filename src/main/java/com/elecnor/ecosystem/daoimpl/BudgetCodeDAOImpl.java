package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.BudgetCode;
import com.elecnor.ecosystem.bean.BudgetFormDje;
import com.elecnor.ecosystem.bean.BudgetFormEquip;
import com.elecnor.ecosystem.bean.BudgetFormIndirect;
import com.elecnor.ecosystem.bean.BudgetFormLabor;
import com.elecnor.ecosystem.bean.BudgetFormMat;
import com.elecnor.ecosystem.bean.BudgetFormProjectAdmin;
import com.elecnor.ecosystem.bean.BudgetFormSubContractor;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.BudgetCodeDAO;
import com.elecnor.ecosystem.helper.BudgetFormHelper;

@Repository
public class BudgetCodeDAOImpl implements BudgetCodeDAO {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<BudgetCode> getAllBudgetCodeDetails() throws Exception {
		ArrayList<BudgetCode> allBudgetCodesDetails = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from BudgetCode where STATUS = 'ACTIVE'");
			allBudgetCodesDetails = (ArrayList<BudgetCode>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return allBudgetCodesDetails;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<BudgetCode> getAllBudgetCodeDetails(Long DomainDetail) throws Exception {
		ArrayList<BudgetCode> allBudgetCodesDetails = null;
		try {
			Session hybSes = sessionFactory.getCurrentSession();
			Query query = hybSes.createQuery("from BudgetCode where status = :status AND DOMAIN_ID = :domaindetail");
			query.setParameter("status", "ACTIVE");
			query.setParameter("domaindetail", DomainDetail);
			allBudgetCodesDetails = (ArrayList<BudgetCode>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return allBudgetCodesDetails;
	}

	@Transactional
	@Override
	public String setBudgetCodeDelete(Long activityId, UserDetail userDetails) throws Exception {
		String result = null;
		try {
			Session hybSes = sessionFactory.getCurrentSession();
			Query query = hybSes
					.createQuery("update BudgetCode set status = :status,UPDATED_BY=:updBy, UPDATED_DATE=:updDate where activityId = :activityid");
			query.setParameter("status", "INACTIVE");
			query.setParameter("updBy", userDetails.getUserId());
			query.setParameter("updDate", new Date());
			query.setParameter("activityid", activityId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Transactional
	@Override
	public String setSaveBudgetCode(BudgetCode budgetCodeDetail) throws Exception {
		String result = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			hibSes.merge(budgetCodeDetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public int checkActivityNumberisExist(long activityId, long domainId, Integer activityNumber) throws Exception {
		int isExist = 0;
		BudgetFormHelper budgetFormHelper = new BudgetFormHelper();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session
					.createQuery("from BudgetCode where ACTIVITY_NUMBER=:activityNumber and DOMAIN_ID=:domainId and STATUS!='INACTIVE' and ACTIVITY_ID != :activityId");
			query.setParameter("activityNumber", activityNumber);
			query.setParameter("domainId", domainId);
			query.setParameter("activityId", activityId);
			ArrayList<BudgetCode> projList = (ArrayList<BudgetCode>) query.list();
			isExist = budgetFormHelper.checkIfActivityNumberExists(projList);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return isExist;
	}

	@Override
	@Transactional
	public String setUpdateBudgetCode(BudgetCode budgetCodeDetail) throws Exception {
		String result = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"update BudgetCode set COST_TYPE=:costType,ACTIVITY_DESCRIPTION=:activityDesc,"
							+ "ACTIVITY_NUMBER=:activityNumber,DOMAIN_ID=:domainId,STATUS=:status,"
							+ "UPDATED_BY=:updatedBy,UPDATED_DATE =:updatedDate where ACTIVITY_ID=:activityId");
			query.setParameter("activityId", budgetCodeDetail.getActivityId());
			query.setParameter("costType", budgetCodeDetail.getCostType());
			query.setParameter("activityDesc", budgetCodeDetail.getActivityDescription());
			query.setParameter("activityNumber", budgetCodeDetail.getActivityNumber());
			query.setParameter("domainId", budgetCodeDetail.getDomainDetail());
			query.setParameter("status", budgetCodeDetail.getStatus());
			query.setParameter("updatedBy", budgetCodeDetail.getUpdatedBy());
			query.setParameter("updatedDate", budgetCodeDetail.getUpdatedDate());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<BudgetCode> getActivityList(String costType) throws Exception {

		List<BudgetCode> list = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from BudgetCode where COST_TYPE = :costType");
			query.setParameter("costType", costType);
			list = query.list();
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return list;
	}

	@Override
	@Transactional
	public void addMaterialFormData(BudgetFormMat materialBudgetBean) throws Exception {
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
	public void addSubContractorFormData(BudgetFormSubContractor subContractorBudgetBean) throws Exception {
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
	public void addEquipFormData(BudgetFormEquip equipBudgetBean) throws Exception {
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
	public void addIndirectFormData(BudgetFormIndirect indirectBudgetBean) throws Exception {
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
	public void addLaborFormData(BudgetFormLabor laborBudgetBean) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.merge(laborBudgetBean);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}

	@Override
	@Transactional
	public String updateMaterialFormDataBasedOnRfcIdAndActivityNumber(BudgetFormMat materialBudgetBean)
			throws Exception {
		String exception = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("UPDATE BudgetFormMat set "
					+ "MATERIAL = :material , QUOTED_ITEMS = :quotedItems , " + "UPDATED_BY = :updateBy "
					+ "WHERE RFC_LOG_ID = :rfcLogId AND ACTIVITY_NUMBER = :activityNo");

			query.setParameter("activityNo", materialBudgetBean.getActivityNumber());
			query.setParameter("material", materialBudgetBean.getMaterial());
			query.setParameter("quotedItems", materialBudgetBean.getQuotedItems());
			query.setParameter("updateBy", materialBudgetBean.getUpdatedByUserDetail());
			query.setParameter("rfcLogId", materialBudgetBean.getRfcLog().getsNo());

			query.executeUpdate();
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return exception;
	}

	@Override
	@Transactional
	public void updateLaborFormDataRfcIdAndActivityNumber(BudgetFormLabor laborBudgetBean) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("UPDATE BudgetFormLabor SET " + "HOURS = :hours, "
					+ "UPDATED_BY = :updateBy " + "WHERE RFC_LOG_ID = :rfcLogId AND ACTIVITY_NUMBER = :activityNo");

			query.setParameter("activityNo", laborBudgetBean.getActivityNumber());
			query.setParameter("hours", laborBudgetBean.getHours());
			query.setParameter("updateBy", laborBudgetBean.getUpdatedByUserDetail());
			query.setParameter("rfcLogId", laborBudgetBean.getRfcLog().getsNo());
			query.executeUpdate();
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}

	}

	@Override
	@Transactional
	public String getBelcoMatDescription(Integer belcoMatCode) throws Exception {

		String belcoMatDesc = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("select activityDescription from BudgetFormMat where ACTIVITY_NUMBER = :belcoMatCode");
			query.setParameter("belcoMatCode", belcoMatCode);
			belcoMatDesc = (String) query.list().get(0);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return belcoMatDesc;
	}
	
}
