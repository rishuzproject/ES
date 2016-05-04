package com.elecnor.ecosystem.job.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elecnor.ecosystem.bean.BudgetCode;
import com.elecnor.ecosystem.bean.BudgetFormDje;
import com.elecnor.ecosystem.bean.BudgetFormEquip;
import com.elecnor.ecosystem.bean.BudgetFormIndirect;
import com.elecnor.ecosystem.bean.BudgetFormLabor;
import com.elecnor.ecosystem.bean.BudgetFormMat;
import com.elecnor.ecosystem.bean.BudgetFormProjectAdmin;
import com.elecnor.ecosystem.bean.BudgetFormSubContractor;
import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.BudgetCodeDAO;

@Component
public class BudgetFormsExecuter {

	@Autowired
	BudgetCodeDAO budgetCodeDao;

	public boolean addBudgetForms(JobDetail jobDetailBean, UserDetail submittedByUser) throws Exception {

		// START MATERIAL FORM //
		try {
			List<BudgetCode> activityList = budgetCodeDao.getActivityList("MAT");
			BudgetFormMat matBudgetBean = new BudgetFormMat();

			for (BudgetCode activityBean : activityList) {
				matBudgetBean.setActivityDescription(activityBean.getActivityDescription());
				matBudgetBean.setActivityNumber(activityBean.getActivityNumber());
				matBudgetBean.setMaterial(0.0);
				matBudgetBean.setMiscMat(0.0);
				matBudgetBean.setJobDetail(jobDetailBean);
				matBudgetBean.setQuotedItems(0.0);
				matBudgetBean.setSalesTax(0.0);
				matBudgetBean.setSubmittedByUserDetail(submittedByUser);
				matBudgetBean.setSubmittedDate(new java.util.Date());

				budgetCodeDao.addMaterialFormData(matBudgetBean);
			}
			// END MATERIAL FORM //

			// START SUB-CONTRACTOR FORM //
			activityList = budgetCodeDao.getActivityList("SUB");
			BudgetFormSubContractor subBudgetBean = new BudgetFormSubContractor();

			for (int loop = 2; loop <= 2; loop++) {
				for (BudgetCode activityBean : activityList) {

					subBudgetBean.setActivityDescription(activityBean.getActivityDescription());
					subBudgetBean.setActivityNumber(activityBean.getActivityNumber());
					subBudgetBean.setJobDetail(jobDetailBean);
					subBudgetBean.setQuotedItems(0.0);
					subBudgetBean.setSubmittedByUserDetail(submittedByUser);
					subBudgetBean.setSubmittedDate(new java.util.Date());
					budgetCodeDao.addSubContractorFormData(subBudgetBean);

				}
			}
			// END SUB-CONTRACTOR FORM //

			// START DJE FORM //
			activityList = budgetCodeDao.getActivityList("DJE");
			BudgetFormDje djeBudgetBean = new BudgetFormDje();

			for (BudgetCode activityBean : activityList) {

				djeBudgetBean.setActivityDescription(activityBean.getActivityDescription());
				djeBudgetBean.setActivityNumber(activityBean.getActivityNumber());
				djeBudgetBean.setMaterial(0.0);
				djeBudgetBean.setJobDetail(jobDetailBean);
				djeBudgetBean.setSubmittedByUserDetail(submittedByUser);
				djeBudgetBean.setSubmittedDate(new java.util.Date());

				budgetCodeDao.addDJEFormData(djeBudgetBean);
			}
			// END DJE FORM //

			// START EQUIP FORM //
			activityList = budgetCodeDao.getActivityList("EQUIP");
			BudgetFormEquip equipBudgetBean = new BudgetFormEquip();

			for (BudgetCode activityBean : activityList) {

				equipBudgetBean.setActivityDescription(activityBean.getActivityDescription());
				equipBudgetBean.setActivityNumber(activityBean.getActivityNumber());
				equipBudgetBean.setMaterial(0.0);
				equipBudgetBean.setJobDetail(jobDetailBean);
				equipBudgetBean.setSubmittedByUserDetail(submittedByUser);
				equipBudgetBean.setSubmittedDate(new java.util.Date());

				budgetCodeDao.addEquipFormData(equipBudgetBean);
			}
			// END EQUIP FORM //

			// START INDIRECT FORM //
			activityList = budgetCodeDao.getActivityList("INDIRECT");
			BudgetFormIndirect indirectBudgetBean = new BudgetFormIndirect();

			for (BudgetCode activityBean : activityList) {

				indirectBudgetBean.setActivityDescription(activityBean.getActivityDescription());
				indirectBudgetBean.setActivityNumber(activityBean.getActivityNumber());
				indirectBudgetBean.setMaterial(0.0);
				indirectBudgetBean.setJobDetail(jobDetailBean);
				indirectBudgetBean.setSubmittedByUserDetail(submittedByUser);
				indirectBudgetBean.setSubmittedDate(new java.util.Date());

				budgetCodeDao.addIndirectFormData(indirectBudgetBean);
			}
			// END INDIRECT FORM //

			// START PROJECT ADMIN FORM //
			activityList = budgetCodeDao.getActivityList("PROJECT ADMIN");
			BudgetFormProjectAdmin projectAdminBudgetBean = new BudgetFormProjectAdmin();

			for (BudgetCode activityBean : activityList) {

				projectAdminBudgetBean.setActivityDescription(activityBean.getActivityDescription());
				projectAdminBudgetBean.setActivityNumber(activityBean.getActivityNumber());
				projectAdminBudgetBean.setHours(0.0);
				projectAdminBudgetBean.setRate(0.0);
				projectAdminBudgetBean.setJobDetail(jobDetailBean);
				projectAdminBudgetBean.setSubmittedByUserDetail(submittedByUser);
				projectAdminBudgetBean.setSubmittedDate(new java.util.Date());

				budgetCodeDao.addProjectAdminFormData(projectAdminBudgetBean);
			}
			// END PROJECT ADMIN FORM //

			// START LABOR FORM //
			activityList = budgetCodeDao.getActivityList("LABOR");
			BudgetFormLabor laborBudgetBean = new BudgetFormLabor();

			for (int loop = 1; loop <= 3; loop++) {
				for (BudgetCode activityBean : activityList) {

					laborBudgetBean.setActivityDescription(activityBean.getActivityDescription());
					laborBudgetBean.setActivityNumber(activityBean.getActivityNumber());
					laborBudgetBean.setHours(0.0);
					laborBudgetBean.setBurden(0.0);
					laborBudgetBean.setFringes(0.0);
					laborBudgetBean.setLabWBdn(0.0);
					laborBudgetBean.setLabWoBdn(0.0);
					laborBudgetBean.setJobDetail(jobDetailBean);
					laborBudgetBean.setSubmittedByUserDetail(submittedByUser);
					laborBudgetBean.setSubmittedDate(new java.util.Date());

					budgetCodeDao.addLaborFormData(laborBudgetBean);
				}
			}
		} catch (Exception e) {
			return false;
		}
		// END LABOR FORM //
		return true;
	}

}
