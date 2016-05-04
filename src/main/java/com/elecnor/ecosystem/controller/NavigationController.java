package com.elecnor.ecosystem.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.ApplicationInvoice;
import com.elecnor.ecosystem.bean.ApplicationPlanDirectory;
import com.elecnor.ecosystem.bean.ApplicationUserRole;
import com.elecnor.ecosystem.bean.BudgetCode;
import com.elecnor.ecosystem.bean.ContractorDirectory;
import com.elecnor.ecosystem.bean.CouponDetails;
import com.elecnor.ecosystem.bean.CustomerDirectory;
import com.elecnor.ecosystem.bean.DepartmentDirectory;
import com.elecnor.ecosystem.bean.DepartmentType;
import com.elecnor.ecosystem.bean.DepartmentUserMapping;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.ItemDB;
import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.LicenseDirectory;
import com.elecnor.ecosystem.bean.ProjectType;
import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.bean.SovCommentsTable;
import com.elecnor.ecosystem.bean.SovDirectory;
import com.elecnor.ecosystem.bean.SovReviewsTable;
import com.elecnor.ecosystem.bean.SovTable;
import com.elecnor.ecosystem.bean.SovTableTracking;
import com.elecnor.ecosystem.bean.UnitAbbreviationDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.UserRolesMapping;
import com.elecnor.ecosystem.bean.VendorDirectory;
import com.elecnor.ecosystem.service.ContractorDirectoryService;
import com.elecnor.ecosystem.service.CustomerDirectoryService;
import com.elecnor.ecosystem.service.DepartmentDirectoryService;
import com.elecnor.ecosystem.service.JobsDetailService;
import com.elecnor.ecosystem.service.TypeOfProjectsService;
import com.elecnor.ecosystem.service.VendorDirectoryService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * This controller will have all Navigation API and model mapping API.
 */
@Controller
public class NavigationController {
	
	@Autowired
	VendorDirectoryService vendorDirectoryService;
	@Autowired
	TypeOfProjectsService projectTypeService;
	@Autowired
	DepartmentDirectoryService deptDirectoryService;
	@Autowired
	CustomerDirectoryService custDirectoryService;
	@Autowired
	ContractorDirectoryService contractorDirService;
	@Autowired
	JobsDetailService jobsService;
	@Autowired
	Utility util;
	
	
	/**
	 * This method will return "applicationDetail" view.
	 * @return
	 */
	@RequestMapping("/applicationDetail")
	public String getApplicationDetailPage() {
		return "applicationDetail";
	}

	/**
	 * This method will map "ApplicationDirectory" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "appDetailForm")
	public ApplicationDirectory getApplicationDetails() {
		return new ApplicationDirectory();
	}
	
	/**
	 * This method will return "applicationInvoice" view.
	 * @return
	 */
	@RequestMapping("/applicationInvoice")
	public String getApplicationInvoicePage() {
		return "applicationInvoice";
	}

	/**
	 * This method will map "ApplicationInvoice" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/applicationInvoiceForm")
	public ApplicationInvoice getApplicationInvoiceForm() {
		return new ApplicationInvoice();
	}
	
	/**
	 * This method will return "applicationInvoiceInDetails" OR "applicationInvoice" view based on requestParameter sent.
	 * @return
	 */
	@RequestMapping("/applicationInvoiceInDetail")
	public String getApplicationInvoicePage(HttpServletRequest request,
			HttpSession session) {
		if (request.getParameter("invoiceId") != null && request.getParameter("invoiceId") != "") {
			session.setAttribute("invoiceId",Long.parseLong(request.getParameter("invoiceId")));
			return ConstantUtil.REDIRECT_TO_APPLICATION_INVOICE_IN_DETAILS;
		}
		else {
			return ConstantUtil.REDIRECT_TO_APPLICATION_INVOICE;
		}
	}
	
	/**
	 * This method will return "applicationPurchasePlanDetails" view.
	 * @return
	 */
	@RequestMapping("/applicationPurchasePlanDetails")
	public String getApplicationPurchasePlanDetailsPage() {
		return "applicationPurchasePlanDetails";
	}

	/**
	 * This method will map "ApplicationPlanDirectory" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/purchasePlanForm")
	public ApplicationPlanDirectory getpPurchasePlanForm() {
		return new ApplicationPlanDirectory();
	}
	
	/**
	 * This method will return "applicationStore1" view.
	 * @return
	 */
	@RequestMapping("/applicationStore1")
	public String getApplicationStore1Page() {
		return "applicationStore1";
	}
	
	/**
	 * This method will return "belcoApplicationStore" OR "applicationStore" view based on logged-in user.
	 * @return
	 */
	@RequestMapping("/applicationStore")
	public String getApplicationStorePage(HttpSession session) {
		if ((Boolean) session.getAttribute("isBelcoUser")) {
			return "belcoApplicationStore";
		} else {
			return "applicationStore";
		}
	}

	/**
	 * This method will return "belcoApplicationStore" view.
	 * @return
	 */
	@RequestMapping("/belcoApplicationStore")
	public String getBelcoApplicationStorePage() {
		return "belcoApplicationStore";
	}
	
	/**
	 * This method will return "appUserMapping" view.
	 * @return
	 */
	@RequestMapping("/appUserMapping")
	public String getAppUserMappingPage() {
		return "appUserMapping";
	}
	
	/**
	 * This method will return "belcoHome" view.
	 * @return
	 */
	@RequestMapping("/belcoHome")
	public String getBelcoHomePage() {
		return "belcoHome";
	}
	
	/**
	 * This method will return "belcoLogin" view.
	 * @return
	 */
	@RequestMapping("/belcoLogin")
	public String getBelcoLoginPage() {
		return "belcoLogin";
	}
	
	/**
	 * This method will return "budgetForm" view.
	 * @return
	 */
	@RequestMapping(value = "/budgetForm")
	public String getBudgetFormPage(HttpSession session) {
		long userId = (Long) session.getAttribute("userRole");
		String page = "unauthorizedPage";
		if(userId == 1 || userId == 2 || userId == 3){
			page = "budgetForm";
		}
		return page;
	}

	/**
	 * This method will map "BudgetCode" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/budgetCodeForm")
	public BudgetCode getBudgetCodeForm() {
		return new BudgetCode();
	}
	
	/**
	 * This method will return "changeOrderProcess" view.
	 * @return
	 */
	@RequestMapping("/changeOrder")
	public String getChangeOrderPage() {
		return "changeOrderProcess";
	}

	/**
	 * This method will map "RfcLog" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/rfcFormBean")
	public RfcLog getRfcFormBean() {
		return new RfcLog();
	}
	
	/**
	 * This method will return "contractorDirectory" view.
	 * @return
	 */
	@RequestMapping("/contractorDirectory")
	public String getContractorDirectoryPage(HttpSession session) {
		long userId = (Long) session.getAttribute("userRole");
		String page = "unauthorizedPage";
		if(userId == 1 || userId == 2 || userId == 3){
			page = "contractorDirectory";
		}
		return page;
	}
	
	/**
	 * This method will map "ContractorDirectory" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/contracterForm")
	public ContractorDirectory getContracterForm() {
		return new ContractorDirectory();
	}
	
	/**
	 * This method will return "couponDetails" view.
	 * @return
	 */
	@RequestMapping("/couponDetails")
	public String getCouponDetailsPage() {
		return "couponDetails";
	}

	/**
	 * This method will map "CouponDetails" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/couponForm")
	public CouponDetails getCouponForm() {
		return new CouponDetails();
	}
	
	/**
	 * This method will return "customerDirectory" view.
	 * @return
	 */
	@RequestMapping("/customer")
	public String getCustomerPage(HttpSession session) {
		long userId = (Long) session.getAttribute("userRole");
		String page = "unauthorizedPage";
		if(userId == 1 || userId == 2 || userId == 3){
			page = "customerDirectory";
		}
		return page;
	}

	/**
	 * This method will map "CustomerDirectory" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/customerForm")
	public CustomerDirectory getCustomerForm() {
		return new CustomerDirectory();
	}
	
	/**
	 * This method will return "dashboard" view.
	 * @return
	 */
	@RequestMapping("/dashboard")
	public String getDashboardPage() {
		return "dashboard";
	}
	
	/**
	 * This method will return "departmentDetails" view.
	 * @return
	 */
	@RequestMapping("/departmentDetails")
	public String getDepartmentDetailsPage(HttpSession session) {
		long userId = (Long) session.getAttribute("userRole");
		String page = "unauthorizedPage";
		if(userId == 1 || userId == 2 || userId == 3){
			page = "departmentDetails";
		}
		return page;
	}

	/**
	 * This method will map "DepartmentType" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/departmentForm")
	public DepartmentType getDepartmentForm() {
		return new DepartmentType();
	}
	
	/**
	 * This method will map "DepartmentUserMapping" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/departmentUserMapping")
	public DepartmentUserMapping getDepartmentUserMappingForm() {
		return new DepartmentUserMapping();
	}
	
	/**
	 * This method will return "documentCenter" view.
	 * @return
	 */
	@RequestMapping("/documentCenter")
	public String getDocumentCenterPage() {
		return "documentCenter";
	}
	
	/**
	 * This method will return "updateDomainInfo" view.
	 * @return
	 */
	//This needs to look - Vaibhav. Same API is defined in DomainDetailController.java.
	/*@RequestMapping(value = "/updateDomainInfo", method = RequestMethod.GET)
	public String getUpdateDomainInfoPage() {
		return "updateDomainInfo";
	}*/

	/**
	 * This method will map "DomainDetail" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/updateDomainInfoForm")
	public DomainDetail getUpdateDomainInfoForm() {
		return new DomainDetail();
	}
	
	/**
	 * This method will return "domainInfoInDetail" view.
	 * @return
	 */
	@RequestMapping("/domainInfoInDetail")
	public String getDomainInfoInDetailPage(HttpServletRequest request, HttpSession session) {
		if (request.getParameter("domainId") != null && request.getParameter("domainId") != "") {
			session.setAttribute("domainId", Long.parseLong(request.getParameter("domainId")));
		}
		return "domainInfoInDetail";
	}
	
	/**
	 * This method will return "error" view.
	 * @return
	 */
	@RequestMapping("/error")
	public String getErrorPage() {
		return "error";
	}

	/**
	 * This method will return "sessionExpire" view.
	 * @return
	 */
	@RequestMapping("/sessionExpire")
	public String getSessionExpirePage() {
		return "sessionExpire";
	}
	
	/**
	 * This method will return "companyCalendar" view.
	 * @return
	 */
	@RequestMapping("/companyCalendar")
	public String getCompanyCalendarPage() {
		return "companyCalendar";
	}

	/**
	 * This method will map "CompanyCalendar" Bean to modelAttribute.
	 * @return
	 *//*
	@RequestMapping("/companyCalendarForm")
	public CompanyCalendar getCompanyCalendarForm() {
		return new CompanyCalendar();
	}*/
	
	/**
	 * This method will return "itemDetail" view.
	 * @return
	 */
	@RequestMapping("/itemDetail")
	public String getItemDetailPage(HttpSession session) {
		long userId = (Long) session.getAttribute("userRole");
		String page = "unauthorizedPage";
		if(userId == 1 || userId == 2 || userId == 3){
			page = "itemDetail";
		}
		return page;
	}

	/**
	 * This method will map "ItemDB" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/itemFormData")
	public ItemDB getItemFormData() {
		return new ItemDB();
	}
	
	/**
	 * This method will return "jobsDetail" view.
	 * @return
	 */
	@RequestMapping("/project")
	public ModelAndView getProjectPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// Set all the required data in the model as attribute.
		try {
			UserDetail loggedInUser = (UserDetail) session.getAttribute("selectedUser");
			Long domainId = loggedInUser.getDomainDetail().getDomainId();
			//Long domainId = 1L;

			List<VendorDirectory> vendorsList = vendorDirectoryService.getVendorListByDomainId(domainId);
			List<ProjectType> projectTypeList = projectTypeService.getProjectTypeListByDomainId(domainId);
			List<DepartmentDirectory> departmentList = deptDirectoryService.getDepartmentListByDomainId(domainId);
			List<CustomerDirectory> customersList = custDirectoryService.getCustomerListByDomainId(domainId);
			List<ContractorDirectory> contractorsList = contractorDirService.getContractorListByDomainId(domainId);

			HashMap<Object,Object> roleBasedUserMap = jobsService.getRoleBasedUserDetails(domainId);

			mav.addObject("vendorsList", util.getJsonResult(vendorsList));
			mav.addObject("projectTypeList", util.getJsonResult(projectTypeList));
			mav.addObject("departmentList", util.getJsonResult(departmentList));
			mav.addObject("customersList", util.getJsonResult(customersList));
			mav.addObject("contractorsList", util.getJsonResult(contractorsList));
			mav.addObject("roleBasedUserMap", util.getJsonResult(roleBasedUserMap));
			
			mav.setViewName("jobsDetail");
		}catch(Exception ex){
			ex.printStackTrace();
			util.sendExceptionEmail(ex);
		}
		return mav;
	}

	/**
	 * This method will map "JobDetail" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute("manageJobForm")
	public JobDetail getManageJobForm() {
		return new JobDetail();
	}

	/**
	 * This method will return "localLicense" view.
	 * @return
	 */
	@RequestMapping("/localLicense")
	public String getLocalLicensePage(HttpSession session) {
		long userId = (Long) session.getAttribute("userRole");
		String page = "unauthorizedPage";
		if(userId == 1 || userId == 2 || userId == 3){
			page = "localLicense";
		}
		return page;
	}
	
	/**
	 * This method will map "LicenseDirectory" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "localLicenseForm")
	public LicenseDirectory getLocalLicenseForm() {
		return new LicenseDirectory();
	}
	
	/**
	 * This method will return "belcoLogin" OR "staticPageHome" view.
	 * @return
	 */
	@RequestMapping("/userLogout")
	public String logout(HttpSession session, HttpServletRequest request) {

		try {
			String redirectPage;
			if((Boolean) session.getAttribute("isBelcoUser")){
				redirectPage = "belcoLogin";
			}
			else{
				redirectPage = "staticPageHome";
			}
			session = request.getSession(false);
			session.setAttribute("selectedUser", null);
			session.setAttribute("isTemporaryUser", null);
			session.setAttribute("isBelcoUser", null);
			session.invalidate();
			return redirectPage;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * This method will return "manageAccount" view.
	 * @return
	 */
	@RequestMapping(value = "/manageAccount")
	public String getManageAccountPage() {
		return "manageAccount";
	}
	
	/**
	 * This method will return "manageAccount" view.
	 * @return
	 */
	@RequestMapping("/notificationCenter")
	public String getNotificationCenterPage() {
		return "notificationCenter";
	}
	
	/**
	 * This method will return "payment" view.
	 * @return
	 */
	@RequestMapping("/payment")
	public String getPaymentPage() {
		return "payment";
	}
	
	/**
	 * This method will map "UserDetail" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/userFormInPayment")
	public UserDetail getUserFormInPayment() {
		return new UserDetail();
	}
	
	/**
	 * This method will return "rolesDescription" view.
	 * @return
	 */
	@RequestMapping("/rolesDescription")
	public String getRolesDescriptionPage() {
		return "rolesDescription";
	}

	/**
	 * This method will map "ApplicationUserRole" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "roleDescriptionForm")
	public ApplicationUserRole getRoleDescriptionForm() {
		return new ApplicationUserRole();
	}
	
	/**
	 * This method will return "sovCommentsTable" view.
	 * @return
	 */
	@RequestMapping("/sovCommentsTable")
	public String getSovCommentsTablePage() {
		return "sovCommentsTable";
	}

	/**
	 * This method will map "SovCommentsTable" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/sovCommentsBean")
	public SovCommentsTable getSovCommentsBeanForm() {
		return new SovCommentsTable();
	}
	
	/**
	 * This method will return "sovDirectory" view.
	 * @return
	 */
	@RequestMapping("/sovDirectory")
	public String getSovDirectoryPage() {
		return "sovDirectory";
	}

	/**
	 * This method will map "SovDirectory" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/sovDirectoryBean")
	public SovDirectory getSovDirectoryBeanForm() {
		return new SovDirectory();
	}
	
	/**
	 * This method will return "sovReviewsTable" view.
	 * @return
	 */
	@RequestMapping("/sovReviewsTable")
	public String getSovReviewsTablePage() {
		return "sovReviewsTable";
	}

	/**
	 * This method will map "SovReviewsTable" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/sovReviewsTableBean")
	public SovReviewsTable getSovReviewsTableBeanForm() {
		return new SovReviewsTable();
	}
	
	/**
	 * This method will return "sovTable" view.
	 * @return
	 */
	@RequestMapping("/sovTable")
	public String getSovTablePage() {
		return "sovTable";
	}

	/**
	 * This method will map "SovTable" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/sovTableBean")
	public SovTable getSovTableBeanForm() {
		return new SovTable();
	}
	
	/**
	 * This method will return "sovTableTracking" view.
	 * @return
	 */
	@RequestMapping("/sovTableTracking")
	public String getSovTableTrackingPage() {
		return "sovTableTracking";
	}

	/**
	 * This method will map "SovTableTracking" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute("/sovTableTrackingBean")
	public SovTableTracking getSovTableTrackingBeanForm() {
		return new SovTableTracking();
	}
	
	/**
	 * This method will return "stateLicense" view.
	 * @return
	 */
	@RequestMapping("/stateLicense")
	public String getStateLicensePage(HttpSession session) {
		long userId = (Long) session.getAttribute("userRole");
		String page = "unauthorizedPage";
		if(userId == 1 || userId == 2 || userId == 3){
			page = "stateLicense";
		}
		return page;
	}

	/**
	 * This method will map "LicenseDirectory" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "stateLicenseForm")
	public LicenseDirectory getsLicense() {
		return new LicenseDirectory();
	}
	
	/**
	 * This method will return "staticPageAbout" view.
	 * @return
	 */
	@RequestMapping("/staticAbout")
	public String getStaticHomePage() {
		return "staticPageAbout";
	}
	
	/**
	 * This method will return "staticPageContact" view.
	 * @return
	 */
	@RequestMapping("/staticContact")
	public String getStaticContactPage() {
		return "staticPageContact";
	}
	
	/**
	 * This method will return "staticHeaderFooter" view.
	 * @return
	 */
	@RequestMapping("/staticHeaderFooter")
	public String getStaticIndexPage() {
		return "staticHeaderFooter";
	}
	
	// This needs to look - Vaibhav. Same API is defined in StaticHeaderFooterController.java.
	/*@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHomePage() {
		return "home";
	}*/

	/*@RequestMapping(value = "/belcoHome", method = RequestMethod.GET)
	public String getBelcoHomePage() {
		return "belcoHome";
	}*/
	
	/**
	 * This method will return "staticPageHome" view.
	 * @return
	 */
	@RequestMapping("/staticHome")
	public String getStaticIndex() {
		return "staticPageHome";
	}
	
	/**
	 * This method will return "staticPageProduct" view.
	 * @return
	 */
	@RequestMapping("/staticProduct")
	public String getStaticProductPage() {
		return "staticPageProduct";
	}
	
	/**
	 * This method will return "ticketCenter" view.
	 * @return
	 */
	@RequestMapping(value = "/ticketCenter")
	public String getTicketCenterPage(){
		return "ticketCenter";
	}
	
	/**
	 * This method will return "typesOfProject" view.
	 * @return
	 */
	@RequestMapping("/typesOfProject")
	public String getTypesOfProjectPage(HttpSession session) {
		long userId = (Long) session.getAttribute("userRole");
		String page = "unauthorizedPage";
		if(userId == 1 || userId == 2 || userId == 3){
			page = "typesOfProject";
		}
		return page;
	}

	/**
	 * This method will map "ProjectType" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "projectTypeForm")
	public ProjectType getProjectTypeForm() {
		return new ProjectType();
	}
	
	/**
	 * This method will return "unitAbbreviation" view.
	 * @return
	 */
	@RequestMapping("/unitAbbreviation")
	public String getUnitAbbreviationPage(HttpSession session) {
		long userId = (Long) session.getAttribute("userRole");
		String page = "unauthorizedPage";
		if(userId == 1 || userId == 2 || userId == 3){
			page = "unitAbbreviation";
		}
		return page;
	}
	
	/**
	 * This method will return "unitAbbreviationDirectory" view.
	 * @return
	 */
	@RequestMapping("/unitAbbreviationDirectory")
	public String getUnitAbbreviationDirectoryPage(HttpSession session) {
		long userId = (Long) session.getAttribute("userRole");
		String page = "unauthorizedPage";
		if(userId == 1 || userId == 2 || userId == 3){
			page = "unitAbbreviationDirectory";
		}
		return page;
	}
	
	/**
	 * This method will map "UnitAbbreviationDirectory" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/unitAbbreviationDirectoryBean")
	public UnitAbbreviationDirectory getUnitAbbreviationDirectoryBeanForm() {
		return new UnitAbbreviationDirectory();
	}
	
	/**
	 * This method will return "updateAccountInfo" view.
	 * @return
	 */
	@RequestMapping("/updateAccountInfo")
	public String getUpdateAccountInfoPage() {
		return "updateAccountInfo";
	}
	
	/**
	 * This method will return "manageUsers" view.
	 * @return
	 */
	@RequestMapping("/manageUser")
	public String getManageUserPage(HttpSession session) {
		long userId = (Long) session.getAttribute("userRole");
		String page = "unauthorizedPage";
		if(userId == 1 || userId == 2 || userId == 3){
			page = "manageUsers";
		}
		return page;
	}

	/**
	 * This method will map "UserDetail" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/applicationUserForm")
	public UserDetail getApplicationUserForm() {
		return new UserDetail();
	}
	
	/**
	 * This method will return "vendorDirectory" view.
	 * @return
	 */
	@RequestMapping("/vendorDirectory")
	public String getVendorDirectoryPage(HttpSession session) {
		long userId = (Long) session.getAttribute("userRole");
		String page = "unauthorizedPage";
		if(userId == 1 || userId == 2 || userId == 3){
			page = "vendorDirectory";
		}
		return page;
	}

	/**
	 * This method will map "VendorDirectory" Bean to modelAttribute.
	 * @return
	 */
	@ModelAttribute(value = "/venderForm")
	public VendorDirectory getVenderForm() {
		return new VendorDirectory();
	}
	
	@RequestMapping(value = "/navigateToDownstreamApp", method = RequestMethod.GET)
	public @ResponseBody String navigateToDownstreamApp(){
		return "success";
	}
}
