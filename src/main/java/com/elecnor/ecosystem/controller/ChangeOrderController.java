package com.elecnor.ecosystem.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.bean.RfcLogTracking;
import com.elecnor.ecosystem.bean.RfcTakeoffSheet;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ChangeOrderDAO;
import com.elecnor.ecosystem.dao.JobsDetailDAO;
import com.elecnor.ecosystem.service.ChangeOrderService;
import com.elecnor.ecosystem.util.Utility;
import com.file.handler.bean.FileUploadBean;
import com.file.handler.service.FileUploadService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author Vaibhav
 * @Vaibhav Removing Navigation API to Navigation Controller.
 */

@Controller
public class ChangeOrderController {

	@Autowired
	ChangeOrderService changeOrderService;
	@Autowired
	ChangeOrderDAO changeOrderDAO;
	@Autowired
	JobsDetailDAO jobsDao;

	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	Utility util;

	/*
	 * @RequestMapping("/changeOrder") public String getChangeOrder() { return
	 * "changeOrderProcess"; }
	 * 
	 * @ModelAttribute(value = "/rfcFormBean") public RfcLog getRfcFormBean() {
	 * return new RfcLog(); }
	 */

	@RequestMapping(value = "/totalCalculations")
	@ResponseBody
	public String totalCalculations(HttpServletRequest request, HttpSession session) {
		List<Object> fullList = new ArrayList<Object>();
		try {
			List<RfcLog> rfcList = changeOrderDAO.getAllRfcLogs();
			if (request.getParameter("chartType").equalsIgnoreCase("total")) {
				double totalCost = 0;
				double grossProfit = 0;
				for (RfcLog rl : rfcList) {
					System.out.println(rl.getCalBasedOn());
					System.out.println(rl.getTotalCost());
					System.out.println(rl.getGrossProfit());
					totalCost += rl.getTotalCost();
					grossProfit += rl.getGrossProfit();
				}
				Map<String, Object> dataMap2 = new HashMap<String, Object>();
				Map<String, Object> dataMap3 = new HashMap<String, Object>();

				dataMap2.put("planName", "Total Cost");
				dataMap2.put("price", totalCost);
				fullList.add(dataMap2);
				dataMap3.put("planName", "Total Profit");
				dataMap3.put("price", grossProfit);
				fullList.add(dataMap3);
				return util.getJsonResult(fullList);
			} else {
				double totalQ = 0;
				double totalA = 0;

				for (RfcLog rl : rfcList) {
					System.out.println(rl.getQuoted());
					totalQ += rl.getQuoted();
					System.out.println(rl.getApproved());
					totalA += rl.getApproved();
				}
				Map<String, Object> dataMap = new HashMap<String, Object>();
				Map<String, Object> dataMap1 = new HashMap<String, Object>();

				dataMap.put("planName", "Quoted");
				dataMap.put("price", totalQ);
				fullList.add(dataMap);
				dataMap1.put("planName", "Approved");
				dataMap1.put("price", totalA);
				fullList.add(dataMap1);
				return util.getJsonResult(fullList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/totalCalculationsForProject")
	@ResponseBody
	public String totalCalculationsForProject(HttpServletRequest request, HttpSession session) {
		List<Object> fullListForProject = new ArrayList<Object>();
		try {
//			List<RfcLog> rfcLogsForProject = changeOrderDAO.getAllRfcLogList(Integer.parseInt(request.getParameter("jobId")));
			List<RfcLog> rfcList = changeOrderDAO.getAllActiveRfcLogListForProject(Integer.parseInt(request.getParameter("jobId")));
			if (request.getParameter("chartTypeForProject").equalsIgnoreCase("QA")) {
				double totalQ = 0;
				double totalA = 0;
				for (RfcLog rl : rfcList) {
					System.out.println(rl.getQuoted());
					totalQ += rl.getQuoted();
					System.out.println(rl.getApproved());
					totalA += rl.getApproved();
				}
				Map<String, Object> dataMap = new HashMap<String, Object>();
				Map<String, Object> dataMap1 = new HashMap<String, Object>();
				dataMap.put("planName", "Quoted");
				dataMap.put("price", totalQ);
				fullListForProject.add(dataMap);
				dataMap1.put("planName", "Approved");
				dataMap1.put("price", totalA);
				fullListForProject.add(dataMap1);
				return util.getJsonResult(fullListForProject);
			} else {
				double totalCost = 0;
				double grossProfit = 0;
				for (RfcLog rl : rfcList) {
					System.out.println(rl.getCalBasedOn());
					System.out.println(rl.getTotalCost());
					System.out.println(rl.getGrossProfit());
					totalCost += rl.getTotalCost();
					grossProfit += rl.getGrossProfit();
				}
				Map<String, Object> dataMap2 = new HashMap<String, Object>();
				Map<String, Object> dataMap3 = new HashMap<String, Object>();

				dataMap2.put("planName", "Total Cost");
				dataMap2.put("price", totalCost);
				fullListForProject.add(dataMap2);
				dataMap3.put("planName", "Total Profit");
				dataMap3.put("price", grossProfit);
				fullListForProject.add(dataMap3);
				return util.getJsonResult(fullListForProject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/AddRfc", method = RequestMethod.POST)
	public String AddRfc(@ModelAttribute(value = "/rfcFormBean") RfcLog log, HttpServletRequest request, HttpSession session) {
		try {
			JobDetail selectedProjectBean = (JobDetail) session.getAttribute("seletedProjectForRFC");
			UserDetail loginUserProfileBean = (UserDetail) session.getAttribute("selectedUser");
			request.setAttribute("paneSelected", request.getParameter("save"));
			RfcLog rfBean = changeOrderService.addRfcService(log, loginUserProfileBean, selectedProjectBean, session, request);
			request.setAttribute("paneSelected", request.getParameter("save"));
			request.setAttribute("rfcLogId", rfBean.getsNo());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return null;
	}

	@RequestMapping(value = "/addRfcUsingAjax", method = RequestMethod.POST)
	@ResponseBody
	public String AddRfcUsingAjax(@ModelAttribute(value = "/rfcFormBean") RfcLog log, HttpServletRequest request, HttpSession session) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JobDetail selectedProjectBean = (JobDetail) session.getAttribute("seletedProjectForRFC");
			UserDetail loginUserProfileBean = (UserDetail) session.getAttribute("selectedUser");
			RfcLog rfBean = changeOrderService.addRfcService(log, loginUserProfileBean, selectedProjectBean, session, request);
			resultMap.put("savedRfcId", rfBean);
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping(value = "/DeleteRfcBasedOnRfcId", method = RequestMethod.POST)
	@ResponseBody
	public String deleteRfcBasedOnRfcId(HttpServletRequest request, HttpServletResponse response) {
		String exp = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			int sNo = Integer.parseInt(request.getParameter("sNo"));
			boolean status = true;
			exp = changeOrderDAO.deleteRestoreRfcLogList(sNo, status);
			if (exp == null || exp.equals("")) {
				map.put("result", "success");
			} else {
				map.put("result", "failure");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultWithoutExposeString(map);
	}

	// Seperating Delete and Restore RFC into different methods.
	@RequestMapping(value = "/RestoreDeletedRfc", method = RequestMethod.POST)
	@ResponseBody
	public String restoreDeletedRfc(HttpServletRequest request, HttpServletResponse response) {
		String exp = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			int sNo = Integer.parseInt(request.getParameter("sNo"));
			boolean status = false;
			exp = changeOrderDAO.deleteRestoreRfcLogList(sNo, status);
			if (exp == null || exp.equals("")) {
				map.put("result", "success");
			} else {
				map.put("result", "failure");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultWithoutExposeString(map);
	}

	// This method retrieves list from RFC Status changes from database
	//
	@RequestMapping(value = "/GetRfcTrackingInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getRfcTrackingInfo(HttpServletRequest request, HttpServletResponse response) {
		String rfcLogId = request.getParameter("rfcLogId");
		List<RfcLogTracking> rfcTrackingList = null;
		HashMap<String, Object> rfcTrackingJson = new HashMap<String, Object>();
		try {
			rfcTrackingList = changeOrderDAO.getRfcTrackingDetails(Integer.parseInt(rfcLogId));
			for (int loop = 0; loop < rfcTrackingList.size(); loop++) {
				// Do not remove this SYSOUT otherwise the values will be
				// shown
				// different.
				System.out.println("calculations based on " + rfcTrackingList.get(loop).getCalBasedOn());
				System.out.println("total cost" + rfcTrackingList.get(loop).getTotalCost());
				System.out.println(" gross profit" + rfcTrackingList.get(loop).getGrossProfit());
				System.out.println("gross margin " + rfcTrackingList.get(loop).getGrossMargin());
			}
			rfcTrackingJson.put("rfcTrackingArray", rfcTrackingList);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultWithoutExposeString(rfcTrackingJson);
	}

	// // Sending Json Format Data In Case Of rfc
	// @RequestMapping(value = "/GetRfcSummaryPageDetails", method =
	// RequestMethod.POST)
	// public void getRfcSummaryPageDetails(HttpServletRequest request,
	// HttpServletResponse response) {
	// HttpSession session = request.getSession(false);
	// Utility util = new Utility();
	// try {
	//
	// JobDetail selectedProjectBean = (JobDetail)
	// session.getAttribute("selectedProject");
	// UserDetail loginUserProfile = (UserDetail)
	// session.getAttribute("selectedUser");
	// // ProjectProjectionBean projectionBean = new ProjectionDAO()
	// // .getProjectionDetails(selectedProjectBean.getProjectId());
	// // Getting Rfc List Based on Selected Project
	// ArrayList<RfcLog> rfcLogList =
	// changeOrderDAO.getAllRfcLogList(selectedProjectBean.getJobId().intValue());
	// // Getting Rfc List Based on Selected Projection Bean,Rfc
	// // List & Project
	// // HashMap<String, ArrayList<Double>> rfcSumMap = RfcLogDAO
	// // .getRfcSummaryVariables(projectionBean, rfcLogList,
	// // selectedProjectBean.getProjectId());
	//
	// HashMap<String, Object> resultMap = new HashMap<String, Object>();
	// resultMap.put("rfcLogList", rfcLogList);
	// // resultMap.put("rfcSumMap", rfcSumMap);
	// resultMap.put("loginUserRole", "ADMIN");// hardcoded later needs
	// // to
	// // change
	// Gson gson = new GsonBuilder().setDateFormat("dd-MMM-yyyy").create();
	// String json = gson.toJson(resultMap);
	// response.setContentType("application/json");
	// response.getWriter().write(json);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// util.sendExceptionEmail(e);
	// }
	// }

	// // Sending Json Format Data In Case Of rfc
	// @RequestMapping(value = "/GetRfcSubCostDetails", method =
	// RequestMethod.POST)
	// public void getRfcSubCostDetails(HttpServletRequest request,
	// HttpServletResponse response) {
	// try {
	// int rfcSNO = Integer.parseInt(request.getParameter("rfcSNO"));
	// List<RfcSubCostTypeBean> RfcSubCostTypeBeanList =
	// changeOrderDAO.getSubcostList(rfcSNO);
	// HashMap<String, Object> resultMap = new HashMap<String, Object>();
	// resultMap.put("rfcLogList", RfcSubCostTypeBeanList);
	// Gson gson = new GsonBuilder().create();
	// String json = gson.toJson(resultMap);
	// response.setContentType("application/json");
	// response.getWriter().write(json);
	// } catch (Exception e) {
	// e.printStackTrace();
	// util.sendExceptionEmail(e);
	// }
	// }

	// In Case admin re opens a Rfc , setting Status -Pending/ Rfc Customer
	// Approval Status as Sent For Customer Approval
	@RequestMapping(value = "/ReOpenApprovedRfc", method = RequestMethod.POST)
	public void reOpenApprovedRfc(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int rfcSNO = Integer.parseInt(request.getParameter("rfcSNO"));
			int result = changeOrderDAO.reOpenApprovedRfc(rfcSNO);
			if (result == 1) {
				resultMap.put("result", "success");
			} else {
				resultMap.put("result", "failed");
			}
			response.setContentType("application/json");
			response.getWriter().write(util.getJsonResultWithoutExposeString(resultMap));
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
	}

	@RequestMapping(value = "/getProjectListForRfcSelectpicker", method = RequestMethod.POST)
	@ResponseBody
	public String getProjectListForRfcSelectpicker(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			UserDetail selectedUser = (UserDetail) session.getAttribute("selectedUser");
			ArrayList<JobDetail> allActiveProjectList = jobsDao.getActiveJobDetails(selectedUser.getDomainDetail().getDomainId());
			resultMap.put("activeProjectList", allActiveProjectList);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultexcludeFieldsWithoutExposeAnnotation(resultMap);
	}

	@RequestMapping(value = "/getRfcForSelectedJob", method = RequestMethod.POST)
	@ResponseBody
	public String getRfcForSelectedJob(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			String jobId = request.getParameter("jobId").trim();
			JobDetail jobObj = jobsDao.getJobBean(Long.parseLong(jobId));
			session.setAttribute("seletedProjectForRFC", jobObj);
			ArrayList<RfcLog> allRfcList = changeOrderDAO.getAllRfcLogList(Integer.parseInt(jobId));
			for (RfcLog log : allRfcList) {
				System.out.println("calBasedOn" + log.getCalBasedOn() + "totalCost" + log.getTotalCost() + "grossProfit" + log.getGrossProfit() + "grossMargin" + log.getGrossMargin()
						+ "approval date" + log.getApprovedDate());
			}
			resultMap.put("rfcLogList", allRfcList);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultexcludeFieldsWithoutExposeAnnotation(resultMap);
	}

	@RequestMapping(value = "/downloadTakeOffSheets", method = RequestMethod.GET)
	public void downloadTakeOffSheets(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			if (request.getParameter("rfcTakeOffSheetNumber") != null || request.getParameter("rfcTakeOffSheetNumber") != "") {
				int takeOffSheetId = Integer.parseInt(request.getParameter("rfcTakeOffSheetNumber"));
				// int
				// rfcLogNumber=Integer.parseInt(request.getParameter("rfcSNO"));

				/*
				 * ArrayList<RfcTakeoffSheet> rfcTakeoffSheetList =
				 * changeOrderDAO.getAllTakeSheetsList(); RfcTakeoffSheet
				 * resultantSheet = null; for (RfcTakeoffSheet temp :
				 * rfcTakeoffSheetList) { if (temp.getFileId() ==
				 * takeOffSheetId) { resultantSheet = temp; break; } }
				 */
				FileUploadBean fileToDownload = fileUploadService.getFileInfo(takeOffSheetId);
				Map<String, Object> fileContent = fileUploadService.downloadFile(takeOffSheetId);
				File file = (File) fileContent.get("success");
				InputStream targetStream = new FileInputStream(file);
				String fileName = fileToDownload.getFileName().trim();
				response.setContentType("application/vnd.ms-excel");
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				FileCopyUtils.copy(targetStream, response.getOutputStream());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getAllTakeOffSheets", method = RequestMethod.POST)
	@ResponseBody
	public String getTakeOffSheetsList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String result = null;
		try {
			// if(request.getParameter("rfcSNO")!=null)
			// {
			HashMap<String, Object> rfcTakeOffSheetsMap = new HashMap<String, Object>();
			/*
			 * Integer
			 * rfcLogNumber=Integer.parseInt(request.getParameter("rfcSNO"));
			 */
			Long jobId=Long.parseLong(request.getParameter("jobId"));
			List<FileUploadBean> listofFiles = new LinkedList<FileUploadBean>();
			ArrayList<RfcTakeoffSheet> rfcTakeOffSheetList = changeOrderDAO.getAllTakeSheetsList(jobId);
			for (RfcTakeoffSheet rfcSheet : rfcTakeOffSheetList) {
				listofFiles.add(fileUploadService.getFileInfo(rfcSheet.getFileId()));
			}
			rfcTakeOffSheetsMap.put("rfcTakeOffSheetsList", rfcTakeOffSheetList);
			rfcTakeOffSheetsMap.put("rfcSheets", listofFiles);
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("dd-MMM-yyyy").create();
			result = gson.toJson(rfcTakeOffSheetsMap);
			return result;
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
