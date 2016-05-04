package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.ApplicationInvoice;
import com.elecnor.ecosystem.bean.BudgetCode;
import com.elecnor.ecosystem.bean.ContractorDirectory;
import com.elecnor.ecosystem.bean.CustomerDirectory;
import com.elecnor.ecosystem.bean.DepartmentType;
import com.elecnor.ecosystem.bean.DomainApplicationPlanMapping;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.ItemDB;
import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.LicenseDirectory;
import com.elecnor.ecosystem.bean.ProjectType;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.VendorDirectory;
import com.elecnor.ecosystem.dao.ApplicationInvoiceDAO;
import com.elecnor.ecosystem.dao.BudgetCodeDAO;
import com.elecnor.ecosystem.dao.ContractorDirectoryDAO;
import com.elecnor.ecosystem.dao.CustomerDirectoryDAO;
import com.elecnor.ecosystem.dao.DepartmentsDetailDAO;
import com.elecnor.ecosystem.dao.DomainApplicationPlanMappingDAO;
import com.elecnor.ecosystem.dao.DomainDetailDAO;
import com.elecnor.ecosystem.dao.ItemDBDAO;
import com.elecnor.ecosystem.dao.JobsDetailDAO;
import com.elecnor.ecosystem.dao.LicenseDirectoryDAO;
import com.elecnor.ecosystem.dao.TypesOfProjectDAO;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.dao.VendorDirectoryDAO;
import com.elecnor.ecosystem.helper.DomainInfoInDetailHelper;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class DomainInfoInDetailController {

	@Autowired
	UserDetailDAO userDetailDAO;
	@Autowired
	DomainDetailDAO domainDetailDAO;
	@Autowired
	ApplicationInvoiceDAO applicationInvoiceDAO;
	@Autowired
	DomainApplicationPlanMappingDAO domainApplicationPlanMappingDAO;
	@Autowired
	BudgetCodeDAO budgetCodeDAO;
	@Autowired
	TypesOfProjectDAO typesOfProjectDAO;
	@Autowired
	DepartmentsDetailDAO departmentsDetailDAO;
	@Autowired
	CustomerDirectoryDAO customerDetailsDAO;
	@Autowired
	JobsDetailDAO jobsDetailDAO;
	@Autowired
	VendorDirectoryDAO vendorDirectoryDAO;
	@Autowired
	LicenseDirectoryDAO licenseDirectoryDAO;
	@Autowired
	ContractorDirectoryDAO contractorDirectoryDAO;
	@Autowired
	ItemDBDAO itemDetailDAO;

	/*@RequestMapping("/domainInfoInDetail")
	public String getChangePasswordScreen(HttpServletRequest request, HttpSession session) {
		if (request.getParameter("domainId") != null && request.getParameter("domainId") != "") {
			session.setAttribute("domainId", Long.parseLong(request.getParameter("domainId")));
		}
		return "domainInfoInDetail";
	}*/

	/**
	 * getting all domain related details
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAllDomainRelatedDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getApplicationInvoiceDetails(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		DomainInfoInDetailHelper domainInfoInDetailHelper = new DomainInfoInDetailHelper();
		Utility util = new Utility();
		Long domainId = 0L;
		try {
			domainId = domainInfoInDetailHelper.getDomainIdFromSession(session);

			ArrayList<UserDetail> alluserDetails = userDetailDAO.getUserDetails(domainId);
			ArrayList<UserDetail> alluserDetailsList = userDetailDAO.getAllUserDetails(domainId);
			ArrayList<DomainApplicationPlanMapping> domainApplicationPlanMapping = domainApplicationPlanMappingDAO
					.getDetails(domainId);
			ArrayList<DomainDetail> allDomainDetails = domainDetailDAO.getDomainDetail(domainId);
			ArrayList<ApplicationInvoice> allInvoiceDetailsList = applicationInvoiceDAO.getInvoiceDetails(domainId);
			ArrayList<BudgetCode> allBudgetCodeList = budgetCodeDAO.getAllBudgetCodeDetails(domainId);
			ArrayList<ProjectType> allProjectTypes = typesOfProjectDAO.getAllProjectTypes(domainId);
			ArrayList<DepartmentType> allDepartmentList = departmentsDetailDAO.getAllDepartments(domainId);
			ArrayList<CustomerDirectory> customersList = customerDetailsDAO.getAllCustomersList(domainId);
			ArrayList<JobDetail> jobDetailList = jobsDetailDAO.getAllJobDetails(domainId);
			ArrayList<VendorDirectory> vendorDirectoryList = vendorDirectoryDAO.getAllVendors(domainId);
			ArrayList<LicenseDirectory> licenseDirectoryList = licenseDirectoryDAO.getLicenseDetails(domainId);
			ArrayList<ContractorDirectory> contractorDirectoryList = contractorDirectoryDAO
					.getAllContractorList(domainId);
			ArrayList<ItemDB> itemDbList = itemDetailDAO.getAllItems(domainId,0,100);
			map.put("alluserDetails", alluserDetails);
			map.put("allDomainDetails", allDomainDetails);
			map.put("allInvoiceDetailsList", allInvoiceDetailsList);
			map.put("domainApplicationPlanMapping", domainApplicationPlanMapping);
			map.put("alluserDetailsList", alluserDetailsList);
			map.put("allBudgetCodeList", allBudgetCodeList);
			map.put("allProjectTypes", allProjectTypes);
			map.put("allDepartmentList", allDepartmentList);
			map.put("customerDetails", customersList);
			map.put("jobDetailList", jobDetailList);
			map.put("vendorDirectoryList", vendorDirectoryList);
			map.put("licenseDirectoryList", licenseDirectoryList);
			map.put("contractorDirectoryList", contractorDirectoryList);
			map.put("itemDbList", itemDbList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);

	}

}
