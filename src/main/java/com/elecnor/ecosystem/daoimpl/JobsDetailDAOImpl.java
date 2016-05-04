/**
 * 
 */
package com.elecnor.ecosystem.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.dao.JobsDetailDAO;
import com.elecnor.ecosystem.querybuilder.JobsDetailQueryBuilder;
import com.elecnor.ecosystem.util.ConstantUtil;

/**
 * @author Ashutosh
 */
@Repository
public class JobsDetailDAOImpl implements JobsDetailDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	JobsDetailQueryBuilder queryBuilder;

	static Logger logger = Logger.getLogger(JobsDetailDAOImpl.class);

	public ArrayList<JobDetail> getAggregateReportListByJobID(String queryParm) throws Exception {
		ArrayList<JobDetail> alluserDetails = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(queryParm);
			alluserDetails = (ArrayList<JobDetail>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return alluserDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<JobDetail> getActiveJobDetails(Long domainId) throws Exception {
		ArrayList<JobDetail> jobList = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from JobDetail where DOMAIN_ID =:domainId and STATUS=:status");
			query.setParameter("domainId", domainId);
			query.setParameter("status", "ACTIVE");
			jobList = (ArrayList<JobDetail>) query.list();

		} catch (Exception e) {
			logger.info(" ---- Exception occurred while getting List for all Jobs---- \n" + e.getCause());
			e.printStackTrace();
			throw e;
		}
		return jobList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<JobDetail> getPendingJobDetails(Long domainId) throws Exception {
		ArrayList<JobDetail> jobList = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from JobDetail where DOMAIN_ID =:domainId and STATUS=:status");
			query.setParameter("domainId", domainId);
			query.setParameter("status", "PENDING");
			jobList = (ArrayList<JobDetail>) query.list();

		} catch (Exception e) {
			logger.info(" ---- Exception occurred while getting List for all Jobs---- \n" + e.getCause());
			e.printStackTrace();
			throw e;
		}
		return jobList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<JobDetail> getAllJobDetails(Long domainId) throws Exception {
		ArrayList<JobDetail> jobList = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from JobDetail where DOMAIN_ID =:domainId and STATUS IN ('ACTIVE','PENDING')");
			query.setParameter("domainId", domainId);
			jobList = (ArrayList<JobDetail>) query.list();

		} catch (Exception e) {
			logger.info(" ---- Exception occurred while getting List for all Jobs---- \n" + e.getCause());
			e.printStackTrace();
			throw e;
		}
		return jobList;
	}

	@Override
	@Transactional
	public JobDetail saveJob(JobDetail jobDetail) throws Exception {
		JobDetail jobDetailBean = null;
		String ex = null;

		try {
			Session session = sessionFactory.getCurrentSession();
			jobDetailBean = (JobDetail) session.merge(jobDetail);
		} catch (Exception e) {
			jobDetailBean = null;
			logger.info(" ---- Exception occurred while saving or updating Job ---- \n" + e.getCause());
			e.printStackTrace();
			ex = e.getMessage();
			throw e;
		}
		return jobDetailBean;
	}

	@Override
	@Transactional
	public String deleteJob(long jobId) {
		// TODO Auto-generated method stub
		String str = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(" update JobDetail set STATUS=:status where JOB_ID=:jobId");
			query.setParameter("jobId", jobId);
			query.setParameter("status", "INACTIVE");
			query.executeUpdate();
		} catch (HibernateException e) {
			str = e.getMessage();
			e.printStackTrace();
		}

		return str;
	}

	@Override
	@Transactional
	public JobDetail getJobById(long jobId) {
		// TODO Auto-generated method stub
		JobDetail jobDetail = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from JobDetail  where JOB_ID=:jobId");
			query.setParameter("jobId", jobId);
			jobDetail = (JobDetail) query.list().get(0);

		} catch (HibernateException e) {
			jobDetail = null;
			e.printStackTrace();
		}
		return jobDetail;

	}

	@Override
	@Transactional
	public String updateJob(JobDetail jobDetail, Long jobCustomer, Long jobType, Long jobForeman, Long jobManager, Long jobSupervisor, Long jobExecutive, Long jobPurchasingAgent, Long vendorsList,
			Integer jobDepartmentType, Long contractor, String status, DomainDetail domailDetail, String startDate, String endDate, String CODate, String extendedDate, String Description,
			long updatedBy, String sovType, Long jobAccountant, Long jobWarehouseManager, Long jobSuperindent, String jobEstimator) throws Exception {
		// TODO Auto-generated method stub

		String ex = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"update JobDetail set CUSTOMER_ID=:jobCustomer,PROJECT_TYPE=:jobType,ESTIMATOR=:jobEstimator,SUPERINDENT=:jobSuperindent,WAREHOUSE_MANAGER=:jobWarehouseManager,FOREMAN=:jobForeman,ACCOUNTANT=:jobAccountant,MANAGER=:jobManager,SUPERVISOR=:jobSupervisor,EXECUTIVE=:jobExecutive,PURCHASING_AGENT=:jobPurchasingAgent,VENDOR_ID=:vendorsList,CONTRACTOR_ID=:contractor,STATUS=:status,START_DATE=:startDate,END_DATE=:endDate,EXTENDED_DATE=:extendedDate,CO_DATE=:CODate,DESCRIPTION=:Description,UPDATED_BY=:updatedBy,JOB_NUMBER=:jobNumber,JOB_NAME=:jobName,BID_PRICE=:bidPrice,ORIGINAL_CONTRACT_VALUE=:originalContrVal,REPORT_MARGIN=:reportMargin,PERFORMANCE_TARGET_MARGIN=:perfTestMargin,LABOR_BONUS_TARGET_OVER_BUDGET=:laborBonusTarg,AUTO_APPROVAL_LIMIT=:autoApprovalLimit,COLLECTION_DAY_OUT=:collectionDayOut,DIRECT_JOB_DAY_OUT=:directJobDayOut,INDIRECT_DAY_OUT=:indirectDayOut,LABOR_DAY_OUT=:laborDayOut, MATERIAL_DAY_OUT=:materialDayOut,OWNED_EQUIPMENT_DAY_OUT=:ownedEquipmentDayOut,PROJ_ADMIN_DAY_OUT=:projAdminDayOut, RENTAL_EQUIPMENT_DAY_OUT=:rentalEquipmentDayOut,RETENTION_DAY_OUT=:retentionDayOut,RETENTION_PERCENT=:retentionPercent,SUBCONTRACTOR_DAY_OUT=:subcontractorDayOut,UPDATED_DATE=:updatedDate,SOV_TYPE=:sovType,"
								+ "STATUS=:status,ACTIVATION_VALIDITY_TIMEPERIOD=:activationValidityTimePeriod,BID_NUMBER=:bidNumber,ARCHITECT_ID=:architectId,INSURANCE_SENT_DATE=:insuranceSentDate,PERFORMANCE_SENT_DATE=:performanceSentDate,CPN_SENT_DATE=:cpnSentDate,IS_CERTIFIED_PAYROLL=:isCertifiedPayroll,NOTICE_RECEIVED_DATE=:noticeReceivedDate,OWNER_CONTROLLED_INSURANCE_PROG=:ownerControlledInsuranceProg,"
								+ "TYPE_OF_CONTRACT=:typeOfContract,OTHER_TYPE_OF_CONTRACT=:otherTypeOfContract,CONTRACT_AMOUNT=:contractAmount,CONTRACT_NUMBER=:contractNumber,BID_BUGET_MATERIAL_COST=:bidBudgetMaterialCost,BID_BUDGET_SUBCONTRACTORS_COST=:bidBudgetSubcontractorsCost,BID_BUDGET_DIRECT_JOB_COSTS=:bidBudgetDirectJobCosts,"
								+ "BID_BUDGDET_RENTAL_EQUIPMENT_COSTS=:bidBudgetRentalEquipmentCosts,BID_BUDGET_OWNED_EQUIPMENTS_COSTS=:bidBudgetOwnedEquipmentsCosts,BID_BUDGET_PROJECT_ADMINISTRATION_COSTS=:bidBudgetProjectAdministrationCost,BID_BUDGET_LABOR_COST=:bidBudgetLaborCost,BID_BUDGET_INDIRECT_EXPENSES=:bidBudgetIndirectExpenses,"
								+ "OPERATIONS_BUDGET_MATERIAL_COSTS=:operationsBudgetMaterialCosts,OPERATIONS_COST_SUBCONTRACTORS_COST=:operationsSubcontractorsCosts,OPERATIONS_BUDGET_DIRECT_JOB_COST=:operationsBudgetDirectJobCost,OPERATIONS_RENTAL_EQUIPMENT_COST=:operationsBudgetRentalEquipmentCost,OPERATIONS_OWNED_EQUIPMENT_COST=:operationsOwnedEquipmentCost,"
								+ "OPERATIONS_PROJECT_ADMINISTRATION_COST=:operationsProjectAdministrationCost,OPERATIONS_INDIRECT_EXPENSES=:operationsIndirectExpenses,OPERATIONS_LABOR_COST=:operationsBudgetLaborCost  where JOB_ID=:jobId");
		if (jobCustomer == 0) {
			query.setParameter("jobCustomer", null);
		} else {
			query.setParameter("jobCustomer", jobCustomer);
		}
		if (jobType == 0) {
			query.setParameter("jobType", null);
		} else {
			query.setParameter("jobType", jobType);
		}
		if (jobEstimator == "") {
			query.setParameter("jobEstimator", null);
		} else {
			query.setParameter("jobEstimator", jobEstimator);
		}
		if (jobSuperindent == 0) {
			query.setParameter("jobSuperindent", null);
		} else {
			query.setParameter("jobSuperindent", jobSuperindent);
		}
		if (jobWarehouseManager == 0) {
			query.setParameter("jobWarehouseManager", null);
		} else {
			query.setParameter("jobWarehouseManager", jobWarehouseManager);
		}

		if (jobAccountant == 0) {
			query.setParameter("jobAccountant", null);
		} else {
			query.setParameter("jobAccountant", jobAccountant);
		}
		if (jobManager == 0) {
			query.setParameter("jobManager", null);
		} else {
			query.setParameter("jobManager", jobManager);
		}
		if (jobForeman == 0) {
			query.setParameter("jobForeman", null);
		} else {
			query.setParameter("jobForeman", jobForeman);
		}
		if (jobSupervisor == 0) {
			query.setParameter("jobSupervisor", null);
		} else {
			query.setParameter("jobSupervisor", jobSupervisor);
		}
		if (jobExecutive == 0) {
			query.setParameter("jobExecutive", null);
		} else {
			query.setParameter("jobExecutive", jobExecutive);
		}
		if (jobPurchasingAgent == 0) {
			query.setParameter("jobPurchasingAgent", null);
		} else {
			query.setParameter("jobPurchasingAgent", jobPurchasingAgent);
		}
		/*
		 * if (jobDepartmentType == 0) { query.setParameter("departmentList",
		 * null); } else { query.setParameter("departmentList",
		 * jobDepartmentType); }
		 */
		if (vendorsList == 0) {
			query.setParameter("vendorsList", null);
		} else {
			query.setParameter("vendorsList", vendorsList);
		}
		if (contractor == 0) {
			query.setParameter("contractor", null);
		} else {
			query.setParameter("contractor", contractor);
		}
		query.setParameter("status", ConstantUtil.ACTIVE);
		// query.setParameter("domainId", domailDetail.getDomainId());
		if (startDate == null || startDate.equals("")) {
			query.setParameter("startDate", null);
		} else {
			System.out.println(simpleDateFormat.format(simpleDateFormat.parse(startDate)));
			query.setParameter("startDate", simpleDateFormat.parse(startDate));
		}
		if (endDate == null || endDate.equals("")) {
			query.setParameter("endDate", null);
		} else {
			query.setParameter("endDate", simpleDateFormat.parse(endDate));
		}
		if (extendedDate == null || extendedDate.equals("")) {
			query.setParameter("extendedDate", null);
		} else {
			query.setParameter("extendedDate", simpleDateFormat.parse(extendedDate));
		}
		if (CODate == null || CODate.equals("")) {
			query.setParameter("CODate", null);
		} else {
			query.setParameter("CODate", simpleDateFormat.parse(CODate));
		}
		if (sovType == null || sovType.equals("")) {
			query.setParameter("sovType", null);
		} else {
			query.setParameter("sovType", sovType);
		}
		query.setParameter("Description", Description);
		query.setParameter("updatedBy", updatedBy);
		query.setParameter("jobNumber", jobDetail.getJobNumber());
		query.setParameter("jobName", jobDetail.getJobName());
		// query.setParameter("jobAddress", jobDetail.getJobAddress());
		query.setParameter("jobId", jobDetail.getJobId());

		query.setParameter("bidPrice", jobDetail.getBidPrice());
		query.setParameter("originalContrVal", jobDetail.getOriginalContractValue());
		query.setParameter("reportMargin", jobDetail.getReportMargin());
		query.setParameter("perfTestMargin", jobDetail.getPerformanceTargetMargin());
		query.setParameter("laborBonusTarg", jobDetail.getLaborBonusTargetOverBudget());
		query.setParameter("autoApprovalLimit", jobDetail.getAutoApprovalLimit());
		query.setParameter("collectionDayOut", jobDetail.getCollectionDayOut());
		query.setParameter("retentionDayOut", jobDetail.getRetentionDayOut());
		query.setParameter("retentionPercent", jobDetail.getRetentionPercent());
		query.setParameter("materialDayOut", jobDetail.getMaterialDayOut());
		query.setParameter("subcontractorDayOut", jobDetail.getSubcontractorDayOut());
		query.setParameter("directJobDayOut", jobDetail.getDirectJobDayOut());
		query.setParameter("rentalEquipmentDayOut", jobDetail.getRentalEquipmentDayOut());
		query.setParameter("ownedEquipmentDayOut", jobDetail.getOwnedEquipmentDayOut());
		query.setParameter("projAdminDayOut", jobDetail.getProjAdminDayOut());
		query.setParameter("laborDayOut", jobDetail.getLaborDayOut());
		query.setParameter("indirectDayOut", jobDetail.getIndirectDayOut());
		query.setParameter("updatedDate", new Date());

		query.setParameter("operationsBudgetLaborCost", jobDetail.getOperationsBudgetLaborCost());
		query.setParameter("operationsIndirectExpenses", jobDetail.getOperationsIndirectExpenses());
		query.setParameter("operationsProjectAdministrationCost", jobDetail.getOperationsProjectAdministrationCost());
		query.setParameter("operationsOwnedEquipmentCost", jobDetail.getOperationsOwnedEquipmentCost());
		query.setParameter("operationsBudgetRentalEquipmentCost", jobDetail.getOperationsBudgetRentalEquipmentCost());
		query.setParameter("operationsBudgetDirectJobCost", jobDetail.getOperationsBudgetDirectJobCost());
		query.setParameter("operationsSubcontractorsCosts", jobDetail.getOperationsSubcontractorsCosts());
		query.setParameter("operationsBudgetMaterialCosts", jobDetail.getOperationsBudgetMaterialCosts());
		query.setParameter("bidBudgetIndirectExpenses", jobDetail.getBidBudgetIndirectExpenses());
		query.setParameter("bidBudgetLaborCost", jobDetail.getBidBudgetLaborCost());
		query.setParameter("bidBudgetProjectAdministrationCost", jobDetail.getBidBudgetProjectAdministrationCost());
		query.setParameter("bidBudgetOwnedEquipmentsCosts", jobDetail.getBidBudgetOwnedEquipmentsCosts());
		query.setParameter("bidBudgetRentalEquipmentCosts", jobDetail.getBidBudgetRentalEquipmentCosts());
		query.setParameter("bidBudgetDirectJobCosts", jobDetail.getBidBudgetDirectJobCosts());
		query.setParameter("bidBudgetSubcontractorsCost", jobDetail.getBidBudgetSubcontractorsCost());
		query.setParameter("bidBudgetMaterialCost", jobDetail.getBidBudgetMaterialCost());
		query.setParameter("contractNumber", jobDetail.getContractNumber());
		query.setParameter("contractAmount", jobDetail.getContractAmount());
		query.setParameter("otherTypeOfContract", jobDetail.getOtherTypeOfContract());
		query.setParameter("typeOfContract", jobDetail.getTypeOfContract());
		query.setParameter("ownerControlledInsuranceProg", jobDetail.getOwnerControlledInsuranceProg());
		query.setParameter("noticeReceivedDate", jobDetail.getNoticeReceivedDate());
		query.setParameter("isCertifiedPayroll", jobDetail.getIsCertifiedPayroll());
		query.setParameter("cpnSentDate", jobDetail.getCpnSentDate());
		query.setParameter("performanceSentDate", jobDetail.getPerformanceSentDate());
		query.setParameter("insuranceSentDate", jobDetail.getInsuranceSentDate());
		query.setParameter("architectId", jobDetail.getArchitectId());
		query.setParameter("bidNumber", jobDetail.getBidNumber());
		query.setParameter("activationValidityTimePeriod", jobDetail.getActivationValidityTimePeriod());

		query.executeUpdate();
		return jobDetail.getJobId().toString();
	}

	@Override
	@Transactional
	public JobDetail getJobBean(long jobId) throws Exception {
		JobDetail exposeJobBean = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from JobDetail where JOB_ID=:jobId");
			query.setParameter("jobId", jobId);
			exposeJobBean = (JobDetail) query.list().get(0);
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return exposeJobBean;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public boolean isJobNumExixsting(String jobNumber) {

		boolean isExist = false;
		ArrayList<JobDetail> jobList = null;
		try {
			jobList = new ArrayList<JobDetail>();
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from JobDetail where JOB_NUMBER=:jobNumber  and STATUS=:status");
			query.setParameter("jobNumber", jobNumber);
			query.setParameter("status", "ACTIVE");
			jobList = (ArrayList<JobDetail>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jobList != null && !jobList.isEmpty()) {
			isExist = true;
		}
		return isExist;
	}

	@Override
	@Transactional
	public void deleteJobDatailsWhenExceptionInAPICall(long jobId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("delete from JobDetail where JOB_ID=:jobId");
			query.setParameter("jobId", jobId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void duplicateProjectRfc(long originalJobId, long duplicateJobId) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session
					.createQuery("INSERT INTO RfcLogBean(projectId, rfcDesc, custRefNo, status, rfcType, origDate, lastStatUpDate, quoted, factor, approved, expApproval, material, subContract, dirJobCost, equip, projAdmin, labrHr, labr, indirCost, contingency, rfcMappingDB, deleted, submittedBy, submittedDate, updatedBy, materialFactor, laborFactor, ownedEquipment, rfcNotes, subcontractCostFactor) SELECT  "
							+ duplicateJobId
							+ ", rfcDesc, custRefNo, status, rfcType, origDate, lastStatUpDate, quoted, factor, approved, expApproval, material, subContract, dirJobCost, equip, projAdmin, labrHr, labr, indirCost, contingency, rfcMappingDB, deleted, submittedBy, submittedDate, updatedBy, materialFactor, laborFactor, ownedEquipment, rfcNotes, subcontractCostFactor FROM RfcLogBean WHERE PROJECT_ID =:projectId");
			query.setParameter("projectId", originalJobId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<String> getJobNamesByDomainId(long domainId) throws Exception {
		ArrayList<String> jobNames = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("select jobName from JobDetail where DOMAIN_ID=:domainId and STATUS=:status");
			query.setParameter("domainId", domainId);
			query.setParameter("status", ConstantUtil.ACTIVE);
			jobNames = (ArrayList<String>) query.list();
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return jobNames;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<JobDetail> getJobDetailsInvolvingUser(Long userId, Long domainId) throws Exception {

		ArrayList<JobDetail> jobList = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("select * from JOB_DETAILS where STATUS='ACTIVE'");
			((SQLQuery) query).addEntity(JobDetail.class);
			jobList = (ArrayList<JobDetail>) query.list();

		} catch (Exception e) {
			logger.info(" ---- Exception occurred while getting List for all Jobs---- \n" + e.getCause());
			e.printStackTrace();
			throw e;
		}
		return jobList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<JobDetail> getActiveJobListByUserRoleAndID(String userId, String userRole) throws Exception {
		StringBuilder queryStr = new StringBuilder("from JobDetail ");
		// For the time being Admin role is given to all the other users except
		// Supervisor, Manager and Foreman. This will be changed based on
		// further requirements by client. @Ashutosh
		if (userRole.equalsIgnoreCase("ADMIN") || userRole.equalsIgnoreCase("EXECUTIVE") || userRole.equalsIgnoreCase("PURCHASING AGENT") || userRole.equalsIgnoreCase("ACCOUNTANT")
				|| userRole.equalsIgnoreCase("Report&Track")) {
			queryStr.append("WHERE ");
		} else if (userRole.equalsIgnoreCase("SUPERVISOR")) {
			queryStr.append("WHERE SUPERVISOR=" + userId + " AND ");
		} else if (userRole.equalsIgnoreCase("MANAGER")) {
			queryStr.append("WHERE MANAGER=" + userId + " AND ");
		} else {
			queryStr.append("WHERE FOREMAN=" + userId + " AND ");
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryBuilder.appendStatusActiveClause(queryStr));

		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<JobDetail> getInActiveJobListByUserRoleAndID(String userId, String userRole) throws Exception {
		StringBuilder queryStr = new StringBuilder("from JobDetail ");
		// For the time being Admin role is given to all the other users except
		// Supervisor, Manager and Foreman. This will be changed based on
		// further requirements by client. @Ashutosh
		if (userRole.equalsIgnoreCase("ADMIN") || userRole.equalsIgnoreCase("EXECUTIVE") || userRole.equalsIgnoreCase("PURCHASING AGENT") || userRole.equalsIgnoreCase("ACCOUNTANT")
				|| userRole.equalsIgnoreCase("Report&Track")) {
			queryStr.append("WHERE ");
		} else if (userRole.equalsIgnoreCase("SUPERVISOR")) {
			queryStr.append("WHERE SUPERVISOR=" + userId + " AND ");
		} else if (userRole.equalsIgnoreCase("MANAGER")) {
			queryStr.append("WHERE MANAGER=" + userId + " AND ");
		} else {
			queryStr.append("WHERE FOREMAN=" + userId + " AND ");
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryBuilder.appendStatusInActiveClause(queryStr));

		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<JobDetail> getClosedJobListByUserRoleAndID(String userId, String userRole) throws Exception {
		StringBuilder queryStr = new StringBuilder("from JobDetail ");
		// For the time being Admin role is given to all the other users except
		// Supervisor, Manager and Foreman. This will be changed based on
		// further requirements by client. @Ashutosh
		if (userRole.equalsIgnoreCase("ADMIN") || userRole.equalsIgnoreCase("EXECUTIVE") || userRole.equalsIgnoreCase("PURCHASING AGENT") || userRole.equalsIgnoreCase("ACCOUNTANT")
				|| userRole.equalsIgnoreCase("Report&Track")) {
			queryStr.append("WHERE ");
		} else if (userRole.equalsIgnoreCase("SUPERVISOR")) {
			queryStr.append("WHERE SUPERVISOR=" + userId + " AND ");
		} else if (userRole.equalsIgnoreCase("MANAGER")) {
			queryStr.append("WHERE MANAGER=" + userId + " AND ");
		} else {
			queryStr.append("WHERE FOREMAN=" + userId + " AND ");
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryBuilder.appendStatusClosedClause(queryStr));

		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<JobDetail> getDuplicateJobListByUserRoleAndID(String userId, String userRole) throws Exception {
		StringBuilder queryStr = new StringBuilder("from JobDetail ");
		// For the time being Admin role is given to all the other users except
		// Supervisor, Manager and Foreman. This will be changed based on
		// further requirements by client. @Ashutosh
		if (userRole.equalsIgnoreCase("ADMIN") || userRole.equalsIgnoreCase("EXECUTIVE") || userRole.equalsIgnoreCase("PURCHASING AGENT") || userRole.equalsIgnoreCase("ACCOUNTANT")
				|| userRole.equalsIgnoreCase("Report&Track")) {
			queryStr.append("WHERE ");
		} else if (userRole.equalsIgnoreCase("SUPERVISOR")) {
			queryStr.append("WHERE SUPERVISOR=" + userId + " AND ");
		} else if (userRole.equalsIgnoreCase("MANAGER")) {
			queryStr.append("WHERE MANAGER=" + userId + " AND ");
		} else {
			queryStr.append("WHERE FOREMAN=" + userId + " AND ");
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryBuilder.appendStatusDupicateClause(queryStr));

		return query.list();
	}

	@Override
	@Transactional
	public JobDetail getJobDetailByJobId(String jobIdStr) throws Exception {
		String queryStr = new String("from JobDetail where JOB_ID = :jobId");
		JobDetail resultObject = null;

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryStr);
		query.setParameter("jobId", jobIdStr);

		if (query.list() != null && query.list().size() > 0)
			resultObject = (JobDetail) query.list().get(0);

		return resultObject;
	}

	// Not needed now as there is no two way communication from MPR but it might
	// be used in future @Ashutosh
	@Override
	public int updateDaysOutDataForProject(JobDetail jobDetailDaysOut) throws Exception {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"update JobDetail set CUSTOMER_ID=:jobCustomer,JOB_ADDRESS=:jobAddress,PROJECT_TYPE=:jobType,ESTIMATOR=:jobEstimator,SUPERINDENT=:jobSuperindent,WAREHOUSE_MANAGER=:jobWarehouseManager,FOREMAN=:jobForeman,ACCOUNTANT=:jobAccountant,MANAGER=:jobManager,SUPERVISOR=:jobSupervisor,EXECUTIVE=:jobExecutive,PURCHASING_AGENT=:jobPurchasingAgent,VENDOR_ID=:vendorsList,CONTRACTOR_ID=:contractor,STATUS=:status,START_DATE=:startDate,END_DATE=:endDate,EXTENDED_DATE=:extendedDate,CO_DATE=:CODate,DESCRIPTION=:Description,UPDATED_BY=:updatedBy,JOB_NUMBER=:jobNumber,JOB_NAME=:jobName,BID_PRICE=:bidPrice,ORIGINAL_CONTRACT_VALUE=:originalContrVal,REPORT_MARGIN=:reportMargin,PERFORMANCE_TARGET_MARGIN=:perfTestMargin,LABOR_BONUS_TARGET_OVER_BUDGET=:laborBonusTarg,AUTO_APPROVAL_LIMIT=:autoApprovalLimit,COLLECTION_DAY_OUT=:collectionDayOut,DIRECT_JOB_DAY_OUT=:directJobDayOut,INDIRECT_DAY_OUT=:indirectDayOut,LABOR_DAY_OUT=:laborDayOut, MATERIAL_DAY_OUT=:materialDayOut,OWNED_EQUIPMENT_DAY_OUT=:ownedEquipmentDayOut,PROJ_ADMIN_DAY_OUT=:projAdminDayOut, RENTAL_EQUIPMENT_DAY_OUT=:rentalEquipmentDayOut,RETENTION_DAY_OUT=:retentionDayOut,RETENTION_PERCENT=:retentionPercent,SUBCONTRACTOR_DAY_OUT=:subcontractorDayOut,UPDATED_DATE=:updatedDate,SOV_TYPE=:sovType,"
								+ "STATUS=:status,ACTIVATION_VALIDITY_TIMEPERIOD=:activationValidityTimePeriod,BID_NUMBER=:bidNumber,ARCHITECT_ID=:architectId,INSURANCE_SENT_DATE=:insuranceSentDate,PERFORMANCE_SENT_DATE=:performanceSentDate,CPN_SENT_DATE=:cpnSentDate,IS_CERTIFIED_PAYROLL=:isCertifiedPayroll,NOTICE_RECEIVED_DATE=:noticeReceivedDate,OWNER_CONTROLLED_INSURANCE_PROG=:ownerControlledInsuranceProg,"
								+ "TYPE_OF_CONTRACT=:typeOfContract,OTHER_TYPE_OF_CONTRACT=:otherTypeOfContract,CONTRACT_AMOUNT=:contractAmount,CONTRACT_NUMBER=:contractNumber,BID_BUGET_MATERIAL_COST=:bidBudgetMaterialCost,BID_BUDGET_SUBCONTRACTORS_COST=:bidBudgetSubcontractorsCost,BID_BUDGET_DIRECT_JOB_COSTS=:bidBudgetDirectJobCosts,"
								+ "BID_BUDGDET_RENTAL_EQUIPMENT_COSTS=:bidBudgetRentalEquipmentCosts,BID_BUDGET_OWNED_EQUIPMENTS_COSTS=:bidBudgetOwnedEquipmentsCosts,BID_BUDGET_PROJECT_ADMINISTRATION_COSTS=:bidBudgetProjectAdministrationCost,BID_BUDGET_LABOR_COST=:bidBudgetLaborCost,BID_BUDGET_INDIRECT_EXPENSES=:bidBudgetIndirectExpenses,"
								+ "OPERATIONS_BUDGET_MATERIAL_COSTS=:operationsBudgetMaterialCosts,OPERATIONS_COST_SUBCONTRACTORS_COST=:operationsSubcontractorsCosts,OPERATIONS_BUDGET_DIRECT_JOB_COST=:operationsBudgetDirectJobCost,OPERATIONS_RENTAL_EQUIPMENT_COST=:operationsBudgetRentalEquipmentCost,OPERATIONS_OWNED_EQUIPMENT_COST=:operationsOwnedEquipmentCost,"
								+ "OPERATIONS_PROJECT_ADMINISTRATION_COST=:operationsProjectAdministrationCost,OPERATIONS_INDIRECT_EXPENSES=:operationsIndirectExpenses,OPERATIONS_LABOR_COST=:operationsBudgetLaborCost  where JOB_ID=:jobId");

		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public boolean isJobNumExixsting(String jobId, String jobNumber) {
		// TODO Auto-generated method stub
		boolean isExist = false;
		ArrayList<JobDetail> jobList = null;
		try {
			jobList = new ArrayList<JobDetail>();
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from JobDetail where JOB_NUMBER=:jobNumber and JOB_ID!=:jobId  and STATUS=:status");
			query.setParameter("jobNumber", jobNumber);
			query.setParameter("jobId", jobId);
			query.setParameter("status", "ACTIVE");
			jobList = (ArrayList<JobDetail>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jobList != null && !jobList.isEmpty()) {
			isExist = true;
		}
		return isExist;

	}

}
