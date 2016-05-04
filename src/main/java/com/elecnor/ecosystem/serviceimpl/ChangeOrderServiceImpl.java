package com.elecnor.ecosystem.serviceimpl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.bean.RfcLogTracking;
import com.elecnor.ecosystem.bean.RfcSubCostTypeBean;
import com.elecnor.ecosystem.bean.RfcTakeoffSheet;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ChangeOrderDAO;
import com.elecnor.ecosystem.daoimpl.ChangeOrderDAOImpl;
import com.elecnor.ecosystem.service.BudgetCodeService;
import com.elecnor.ecosystem.service.ChangeOrderService;
import com.elecnor.ecosystem.service.ExcelSheetService;
import com.elecnor.ecosystem.util.PropertyFileReader;
import com.elecnor.ecosystem.util.Utility;
import com.file.handler.service.FileUploadService;

@Service
public class ChangeOrderServiceImpl implements ChangeOrderService {

	static Logger logger = Logger.getLogger(ChangeOrderDAOImpl.class);
	@Autowired
	ChangeOrderDAO changeOrderDAO;
	@Autowired
	BudgetCodeService budgetCodeService;
	@Autowired
	ExcelSheetService excelSheetService;
	@Autowired
	FileUploadService fileUploadService;

	@Override
	public RfcLog addRfcService(RfcLog log, UserDetail loginUserProfileBean, JobDetail selectedProjectBean, HttpSession session, HttpServletRequest request) throws Exception {
		logger.info(" ---- Inside class ChangeOrderServiceImpl : Start of addRfcService(RfcLog log, UserDetail loginUserProfileBean, JobDetail selectedProjectBean, HttpSession session, HttpServletRequest request) method ---- \n");
		Utility util = new Utility();
		String s = request.getParameter("sNoHidden");
		if (s != null && !s.equalsIgnoreCase("")) {
			// In Case Of Update
			log.setsNo(Integer.parseInt(s));
			log.setUpdatedBy(loginUserProfileBean.getFirstName());
			log.setOrigDate(util.stringToDate(request.getParameter("origDate"), "dd-MMM-yyyy"));
			log.setApprovedDate(util.stringToDate(request.getParameter("approvedDate"), "MM-dd-yyyy"));
		} else {
			log.setApprovedDate(util.stringToDate(request.getParameter("approvedDate"), "MM-dd-yyyy"));
			// In Case Of Insert
			log.setsNo(0);
			log.setUpdatedBy(null);
			// Setting Original Date- Date when RFC is generated.Cannot Be
			// changed @Ankur 4.8.14
			log.setOrigDate(new Date());
			// Setting Original Reference No. , It is 4 digit alphanumeric
			// number -- "0001" for external && "I0001" for internal.It is
			// project specific, ie.for every project It will start with
			// 0001.
			String query = "SELECT originationReferenceNumber FROM RfcLog  where " + "JOB_ID=" + selectedProjectBean.getJobId();
			if (log.getRfcType().equalsIgnoreCase("External")) {
				query += " and rfcType='External' order by sNo desc";
			} else {
				query += " and rfcType='Internal' order by sNo desc";
			}
			// Getting Last SNO Entered By the user
			String lastOriginalReferenceNumber = null;
			try {
				lastOriginalReferenceNumber = changeOrderDAO.getLastOrinalReferenceNumberBasedOnType(query);
				lastOriginalReferenceNumber = lastOriginalReferenceNumber.replace("I", "");

				int newRefNumber = Integer.parseInt(lastOriginalReferenceNumber);
				newRefNumber += 1;
				lastOriginalReferenceNumber = (newRefNumber + "").length() >= 4 ? (newRefNumber + "") : get0AddedAtBegining((newRefNumber + ""));
			} catch (Exception e) {
				if (log.getRfcType().equalsIgnoreCase("External")) {
					lastOriginalReferenceNumber = "0001";
				} else {
					lastOriginalReferenceNumber = "I0001";
				}
			}
			log.setOriginationReferenceNumber(lastOriginalReferenceNumber);
		}

		String factor = request.getParameter("factor");
		if (factor == null || factor.trim().length() == 0) {
			log.setFactor(100.0);
		}
		factor = request.getParameter("materialFactor");
		if (factor == null || factor.trim().length() == 0) {
			log.setMaterialFactor(100.0);
		}
		factor = request.getParameter("laborFactor");
		if (factor == null || factor.trim().length() == 0) {
			log.setLaborFactor(100.0);
		}
		factor = request.getParameter("subcontractCostFactor");
		if (factor == null || factor.trim().length() == 0) {
			log.setSubcontractorFactor(100.0);
		}

		if (request.getParameterValues("rfcMapping") != null && request.getParameterValues("rfcMapping").length != 0) {
			log.setRfcMappingDB(Arrays.toString(request.getParameterValues("rfcMapping")));
		} else {
			log.setRfcMappingDB(null);
		}

		request.setAttribute("paneSelected", request.getParameter("add"));
		// log.setLastStatUpDate(request.getParameter("lastStatUpDate"));
		log.setJobDetail(selectedProjectBean);
		log.setSubmittedBy(loginUserProfileBean.getFirstName());
		log.setSubmittedDate(new Date());

		RfcLog rfcHistoryBean = null;
		if (s != null && !s.equalsIgnoreCase("")) {
			// Sending Email In Case of Status Changed(Both RFC Status ANd
			// Custome Approval Status)
			rfcHistoryBean = changeOrderDAO.getRfcDetails(Integer.parseInt(s));
			// Checking old RFC Customer Approval Status with the updated one
			// In case of first time cannot save history
			if (rfcHistoryBean != null) {
				if (rfcHistoryBean.getRfcUserResponseStatus() != null && log.getRfcUserResponseStatus() != null
						&& !rfcHistoryBean.getRfcUserResponseStatus().equalsIgnoreCase(log.getRfcUserResponseStatus())) {
					// In Case status Do Not Match, Sending Email to
					// Supervisor
					// and
					// Manager.
					String emailBody = "Hi, </br>Customer Approval Status of RFC : " + log.getRfcDesc() + " of Project : " + selectedProjectBean.getJobName() + " : "
							+ selectedProjectBean.getJobNumber() + " has been changed to " + log.getRfcUserResponseStatus() + ".</br> Its previous status was : "
							+ rfcHistoryBean.getRfcUserResponseStatus();
					// Thread thread = new Thread(new
					// EmailThread(selectedProjectBean.getManager().getEmailId(),
					// selectedProjectBean
					// .getSupervisor().getEmailId(), "RFC :" +
					// log.getRfcDesc() +
					// " RFC Customer Approval Status Change Notification",
					// emailBody, false, null));
					// thread.start();
				}
				// Checking old RFC Status with the updated one
				if (rfcHistoryBean.getRfcStatus() != null && log.getRfcStatus() != null && !rfcHistoryBean.getRfcStatus().equalsIgnoreCase(log.getRfcStatus())) {
					// In Case status Do Not Match, Sending Email to
					// Supervisor
					// and
					// Manager.
					String emailBody = "Hi, </br>Status of RFC : " + log.getRfcDesc() + " of Project : " + selectedProjectBean.getJobName() + " : " + selectedProjectBean.getJobNumber()
							+ " has been changed to " + log.getRfcStatus() + ".</br> Its previous status was : " + rfcHistoryBean.getRfcStatus();
					// Thread thread = new Thread(new EmailThread(
					// selectedProjectBean.getManager()
					// .getEmailId(), selectedProjectBean
					// .getSupervisor().getEmailId(),
					// "RFC :" + log.getRfcDesc()
					// + " Status Change Notification", emailBody,
					// false, null));
					// thread.start();
				}

				// Need to Save the history once RFC rfcUserResponseStatus
				// status is
				// Customer For Approval/Approved/Rejected
				if (log.getRfcUserResponseStatus() != null
						&& (log.getRfcUserResponseStatus().equalsIgnoreCase("Send For Customer Approval") || log.getRfcUserResponseStatus().equalsIgnoreCase("Approved")
								|| log.getRfcUserResponseStatus().equalsIgnoreCase("Rejected") || log.getRfcUserResponseStatus().equalsIgnoreCase("Resend For Customer Approval"))) {

					// Setting values for RFC Tracking object.

					RfcLogTracking rfcTrackingObject = new RfcLogTracking();
					rfcTrackingObject.setsNoTracking(rfcHistoryBean.getsNo());
					rfcTrackingObject.setProjectId(rfcHistoryBean.getJobDetail().getJobId().intValue());
					rfcTrackingObject.setRfcLog(log);
					rfcTrackingObject.setApproved(rfcHistoryBean.getApproved());
					rfcTrackingObject.setQuoted(rfcHistoryBean.getQuoted());
					rfcTrackingObject.setRfcStatus(rfcHistoryBean.getRfcStatus());
					rfcTrackingObject.setRfcDesc(rfcHistoryBean.getRfcDesc());
					rfcTrackingObject.setContingency(rfcHistoryBean.getContingency());
					rfcTrackingObject.setCustRefNo(rfcHistoryBean.getCustRefNo());
					rfcTrackingObject.setOriginationReferenceNumber(rfcHistoryBean.getOriginationReferenceNumber());
					rfcTrackingObject.setDirJobCost(rfcHistoryBean.getDirJobCost());
					rfcTrackingObject.setEquip(rfcHistoryBean.getEquip());
					rfcTrackingObject.setExpApproval(rfcHistoryBean.getExpApproval());
					rfcTrackingObject.setFactor(rfcHistoryBean.getFactor());
					rfcTrackingObject.setIndirCost(rfcHistoryBean.getIndirCost());
					rfcTrackingObject.setLaborFactor(rfcHistoryBean.getLaborFactor());
					rfcTrackingObject.setLabr(rfcHistoryBean.getLabr());
					rfcTrackingObject.setLabrHr(rfcHistoryBean.getLabrHr());
					rfcTrackingObject.setUpdatedBy(rfcHistoryBean.getUpdatedBy());
					rfcTrackingObject.setMaterial(rfcHistoryBean.getMaterial());
					rfcTrackingObject.setMaterialFactor(rfcHistoryBean.getMaterialFactor());
					rfcTrackingObject.setOwnedEquipment(rfcHistoryBean.getOwnedEquipment());
					rfcTrackingObject.setProjAdmin(rfcHistoryBean.getProjAdmin());
					rfcTrackingObject.setRfcType(rfcHistoryBean.getRfcType());
					rfcTrackingObject.setSubContract(rfcHistoryBean.getSubContract());
					rfcTrackingObject.setSubcontractorFactor(rfcHistoryBean.getSubcontractorFactor());
					rfcTrackingObject.setSubmittedDate(new Date());
					rfcTrackingObject.setSubmittedBy(rfcHistoryBean.getSubmittedBy());
					changeOrderDAO.insertRfcTracking(rfcTrackingObject);
				}
			}
		}
		log.setDeleted(false);
		// Saving Updated Rfc Bean, History Logged
		RfcLog rfcBudgetFormBean = changeOrderDAO.insertOrUpdateRfcLogList(log);

		// Reading Upload Form Starts
		try {
			// MultipartRequestWrapper wrapper = (MultipartRequestWrapper)
			// request;
			// HttpServletRequest excelUploadRequest = wrapper.getRequest();
			// Process the FormFile
			if (log.getMyFile().getOriginalFilename() != "") {
				try {
					MultipartFile myFile = log.getMyFile();
					String fileName = myFile.getOriginalFilename();
					int projectId = selectedProjectBean.getJobId().intValue();
					String location = PropertyFileReader.getInstance().getStringProperty("uploadfilefolder") + String.valueOf(projectId) + "/" + fileName;
					log.setRfcFileLocation(location);

					log.setRfcFileName(log.getMyFile().getOriginalFilename());
					List<RfcSubCostTypeBean> list = uploadrfcLogFile(selectedProjectBean, loginUserProfileBean, log, rfcBudgetFormBean);
					log.setMyFile(myFile);
					log.setRfcSubCostTypeBeans(list);
					uploadRfcTakeOffFile(log, loginUserProfileBean, myFile,selectedProjectBean);
					// for(RfcSubCostTypeBean subCost : list){
					// System.out.println("values are:::"+subCost.getQuantity());
					// }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.info(" ---- Exception occurred while Adding/ Updating Change Order ---- \n" + e.getCause());
			e.printStackTrace();
		}
		// Reading Upload Form Ends
		if (!(s != null && !s.equalsIgnoreCase(""))) {
			budgetCodeService.AddBudgetForms(rfcBudgetFormBean, session);
		}
		logger.info(" ---- Inside class ChangeOrderServiceImpl : End of addRfcService(RfcLog log, UserDetail loginUserProfileBean, JobDetail selectedProjectBean, HttpSession session, HttpServletRequest request) method ---- \n");
		return rfcBudgetFormBean;
	}

	private String get0AddedAtBegining(String referenceNumber) {
		for (int i = 0; i <= (5 - referenceNumber.length()); i++) {
			referenceNumber = "0" + referenceNumber;
		}
		return referenceNumber;
	}

	private void uploadRfcTakeOffFile(RfcLog updatedRfcBean, UserDetail loginUserProfileBean, MultipartFile myFile,JobDetail selectedProjectBean) throws IOException {
		logger.info(" ---- Inside class ChangeOrderServiceImpl : uploadRfcTakeOffFile(RfcLog updatedRfcBean, UserDetail loginUserProfileBean, MultipartFile myFile) method ---- \n");
		try {
			if (myFile != null) {
				
				
				
				/*rfcTakeoffsheet.setFileName(myFile.getOriginalFilename());*/
				
				/*rfcTakeoffsheet.setSubmittedDate(new Date());*/
			
				/*rfcTakeoffsheet.setContents(myFile.getBytes());*/
				Integer fileId=fileUploadService.uploadFile(myFile.getBytes(), myFile.getOriginalFilename(),myFile.getOriginalFilename().substring(myFile.getOriginalFilename().lastIndexOf(".")));
				RfcTakeoffSheet rfcTakeoffsheet = new RfcTakeoffSheet();
				rfcTakeoffsheet.setSubmittedBy(loginUserProfileBean);
				rfcTakeoffsheet.setRfcLogSno(updatedRfcBean);
				rfcTakeoffsheet.setAssociatedDocument(fileId);
				rfcTakeoffsheet.setAssociatedJob(selectedProjectBean);
				changeOrderDAO.addRfcTakeSheet(rfcTakeoffsheet);
			}
		} catch (Exception e) {
			logger.info(" ---- Inside class ChangeOrderServiceImpl : Exception occurred while uploading RFC Take off sheet ---- \n");
			e.printStackTrace();
		}

	}

	private List<RfcSubCostTypeBean> uploadrfcLogFile(JobDetail selectedProjectBean, UserDetail loginUserProfileBean, RfcLog log, RfcLog updatedRfcBean) {
		logger.info(" ---- Inside class ChangeOrderServiceImpl : uploadrfcLogFile(JobDetail selectedProjectBean, UserDetail loginUserProfileBean, RfcLog log, RfcLog updatedRfcBean) method ---- \n");
		List<RfcSubCostTypeBean> list = null;

		MultipartFile fileUploaded = log.getMyFile();
		File file = new File(fileUploaded.getOriginalFilename());

		OutputStream os = null;
		try {
			if (file != null && file.getName() != "") {
				os = new FileOutputStream(file);
			}

			InputStream is = new BufferedInputStream(fileUploaded.getInputStream());
			int count;
			byte buf[] = new byte[4096];

			while ((count = is.read(buf)) > -1) {
				os.write(buf, 0, count);
			}
			is.close();
			os.close();
			String exp = excelSheetService.updateBudgetCosts(file, selectedProjectBean, loginUserProfileBean, updatedRfcBean);
		} catch (FileNotFoundException e) {
			logger.info(" ---- Inside class ChangeOrderServiceImpl : Exception occurred while uploading RFC Take off sheet in method uploadrfcLogFile() ---- \n");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * This method is to get the Change Order List based on JobId. Only used for
	 * Change Order Expose
	 * 
	 * @param request
	 *            , userId & userRole
	 * @return List<JobDetail>
	 */
	@Override
	public List<RfcLog> getChangeOrderListByJobId(String jobIdStr) throws Exception {
		logger.info(" ---- Inside class ChangeOrderServiceImpl : getChangeOrderListByJobId(String jobIdStr) method ---- \n");
		return changeOrderDAO.getChangeOrderListByJobId(jobIdStr);
	}
}
