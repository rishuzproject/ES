package com.elecnor.ecosystem.job.processor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;

import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.email.utility.bean.EmailNotificationBean;
import com.email.utility.executor.EmailTaskExecutor;

/**
 * 
 * @author Vaibhav This processor will setup the Job in all downstream
 *         application (Subscribed for that Domain)
 */
// @Component
public class JobSetupProcessor {

	private TaskExecutor taskExecutor;

	@Autowired
	MPRTaskExecutor mprTaskExecutor;

	@Autowired
	BudgetFormsExecuter budgetFormExecuter;

	@Autowired
	EmailTaskExecutor emailHandlerThread;

	public class JobSetupProcessorThread implements Runnable {

		private JobDetail jobDetail;
		/*
		 * private String operationType; private TaskExecutor taskExecutor;
		 */
		private UserDetail userDetail;
		private boolean isMPRsubscribed = true;
		private String startDate, endDate, oldStartDt, oldEndDt, submittedBy = "";
		private Long projectid = 0l;
		private boolean isUpdate = false;
		private Map<String, Boolean> appStatusMap = null;
		private String emailTo = "";

		boolean isSuccess = false;

		@Value("${dev.mode.to.email}")
		private String devEmailID;

		/**
		 * @param jobDetail
		 * @param operationType
		 */
		public JobSetupProcessorThread(JobDetail jobDetail) {
			super();
			this.jobDetail = jobDetail;
		}

		public JobSetupProcessorThread(String startDate, String endDate, String oldStartDt, String oldEndDt, boolean isUpdate, Long projectid, JobDetail jobDetail, UserDetail userDetail,
				String emailTo) {
			super();
			this.startDate = startDate;
			this.endDate = endDate;
			this.oldStartDt = oldStartDt;
			this.oldEndDt = oldEndDt;
			this.isUpdate = isUpdate;
			this.projectid = projectid;
			if (null != userDetail && null != userDetail.getFirstName())
				this.submittedBy = userDetail.getFirstName();
			this.jobDetail = jobDetail;
			this.userDetail = userDetail;
			this.emailTo = emailTo;
		}

		public void run() {
			appStatusMap = new HashMap<>();
			// First add budget form in ecosystem
			try {
				if (null != jobDetail)
					budgetFormExecuter.addBudgetForms(jobDetail, userDetail);
				// appStatusMap.put(ConstantUtil.APP_NAME_ECOSYSTEM,
				// budgetFormExecuter.addBudgetForms(jobDetail, userDetail));
			} catch (Exception e) {
				e.printStackTrace();
				// appStatusMap.put(ConstantUtil.APP_NAME_ECOSYSTEM, false);
				String subject = ConstantUtil.EXCEPTION_EMAIL_SUBJECT_BUDGET_FORMS;
				String content = e.getMessage();
				sendNotification(subject, content, devEmailID, userDetail.getEmailId(), userDetail.getUserId());
			}

			// Rest of the down stream application process
			// MPR
			if (isMPRsubscribed) {
				if (!startDate.trim().equals("") && !endDate.trim().equals("")) {
					try {
						appStatusMap.put(ConstantUtil.APP_NAME_MPR, mprTaskExecutor.ExecuteTaskForNewJob(startDate, endDate, oldStartDt, oldEndDt, isUpdate, projectid, submittedBy));
					} catch (Exception e) {
						e.printStackTrace();
						appStatusMap.put(ConstantUtil.APP_NAME_MPR, false);
						String subject = ConstantUtil.EXCEPTION_EMAIL_SUBJECT_CASHFLOW;
						String content = e.getMessage();
						sendNotification(subject, content, devEmailID, userDetail.getEmailId(), userDetail.getUserId());
					}
				}
			}

			// Send notification email to user after all applications are set
			if (!appStatusMap.isEmpty())
				createAppStatusMailforUser(appStatusMap, emailTo, userDetail.getEmailId(), userDetail.getUserId(), jobDetail);
		}

	}

	public JobSetupProcessor() {
	}

	public JobSetupProcessor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	/*
	 * public void SetUpJobInDownstreamApps(JobDetail jobDetail, String
	 * operationType) { taskExecutor.execute(new
	 * JobSetupProcessorThread(jobDetail, operationType)); }
	 */
	public void setUpJobInDownstreamApps(String startDate, String endDate, String oldStartDt, String oldEndDt, boolean isUpdate, Long jobId, JobDetail jobDetailBean, UserDetail userDetail,
			String emailTo) {
		try {
			taskExecutor.execute(new JobSetupProcessorThread(startDate, endDate, oldStartDt, oldEndDt, isUpdate, jobId, jobDetailBean, userDetail, emailTo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Notify user that the job has been added in ecosystem (SEND EMAIL)
	public void sendNotification(String subject, String content, String emailTo, String emailFrom, Long submittedBy) {
		EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
		emailNotificationBean.setEmailTo(emailTo);
		emailNotificationBean.setEmailSubject(subject);
		emailNotificationBean.setEmailContent(content);
		emailNotificationBean.setEmailFrom(emailFrom);
		emailNotificationBean.setSubmittedBy(submittedBy);
		emailNotificationBean.setSubmittedDate(new Date());
		emailHandlerThread.executeEmailTask(emailNotificationBean);
	}

	// Create comulative Email for user with application status
	public void createAppStatusMailforUser(Map<String, Boolean> appStatusMap, String emailTo, String emailFrom, Long submittedBy, JobDetail jobDetails) {
		StringBuilder succeededApps = new StringBuilder();
		StringBuilder failedApps = new StringBuilder();
		StringBuilder content = new StringBuilder();

		if (appStatusMap.containsKey(ConstantUtil.APP_NAME_ECOSYSTEM)) {
			if (appStatusMap.get(ConstantUtil.APP_NAME_ECOSYSTEM)) {
				succeededApps.append(ConstantUtil.APP_NAME_ECOSYSTEM).append(",");
			} else {
				failedApps.append(ConstantUtil.APP_NAME_ECOSYSTEM).append(",");
			}
		}
		if (appStatusMap.containsKey(ConstantUtil.APP_NAME_MPR)) {
			if (appStatusMap.get(ConstantUtil.APP_NAME_MPR)) {
				succeededApps.append(ConstantUtil.APP_NAME_MPR).append(",");
			} else {
				failedApps.append(ConstantUtil.APP_NAME_MPR).append(",");
			}
		}

		// Case 1: When all application setup successfully
		if (succeededApps.length() > 1 && failedApps.length() == 0) {
			succeededApps.deleteCharAt(succeededApps.lastIndexOf(","));
			content.append(" Hello, <br>      Requested Job has been setup successfully and ready to use. Please access Ecosystem for more details.");
			content.append("<br>      Below are the details for requested Job.");
			if (null != jobDetails.getJobName() && !jobDetails.getJobName().trim().equalsIgnoreCase(""))
				content.append(" <br>Job  Name :-" + jobDetails.getJobName());
			if (null != jobDetails.getJobNumber() && !jobDetails.getJobNumber().trim().equalsIgnoreCase(""))
				content.append(" <br>Job # :-" + jobDetails.getJobNumber());
			if (null != jobDetails.getBidNumber() && !jobDetails.getBidNumber().trim().equalsIgnoreCase(""))
				content.append(" <br>Bid # :- " + jobDetails.getBidNumber());
			if (null != jobDetails.getContractAmount() && jobDetails.getContractAmount() != 0)
				content.append(" <br>Contract Value :-" + jobDetails.getContractAmount());
			if (null != jobDetails.getEstimator() && !jobDetails.getEstimator().trim().equalsIgnoreCase(""))
				content.append(" <br>Estimator :- " + jobDetails.getEstimator());
		}

		// Case 2: When some of the application setup successfully and some had
		// errors
		if (succeededApps.length() > 1 && failedApps.length() > 1) {
			succeededApps.deleteCharAt(succeededApps.lastIndexOf(","));
			failedApps.deleteCharAt(failedApps.lastIndexOf(","));
			content.append(" Hello, <br> Requested Job has been setup successfully in Ecosystem : <b>").append(succeededApps).append("</b>");
			content.append(". Please access Ecosystem for more details.");
			content.append(" <br> Error occurred while setting up Job in  : <b>").append(failedApps).append("</b>");
			content.append(" <br> Development team has been notified about the error. Team will update you once they resolve the issues.");
		}

		// Case 3: When all application failed
		if (succeededApps.length() == 0 && failedApps.length() > 1) {
			failedApps.deleteCharAt(failedApps.lastIndexOf(","));
			content.append(" Error occurred while setting up Job ");
			if (null != jobDetails.getJobName() && !jobDetails.getJobName().trim().equalsIgnoreCase(""))
				content.append("- " + jobDetails.getJobName());
			content.append(" , Development team is been notified about the error. ");
			content.append("<br> Team will update you once they resolve the issues.");
			content.append("<br> (You can also report an issue for development team to track the progress)");

		}

		sendNotification("Belco-Elecnor notification", content.toString(), emailTo, emailFrom, submittedBy);
	}

}
