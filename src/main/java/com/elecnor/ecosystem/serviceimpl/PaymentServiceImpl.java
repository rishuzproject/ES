package com.elecnor.ecosystem.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.PaymentPojo;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ApplicationDetailsDAO;
import com.elecnor.ecosystem.dao.ApplicationInvoiceDAO;
import com.elecnor.ecosystem.dao.ApplicationInvoiceInDetailDAO;
import com.elecnor.ecosystem.dao.ApplicationPurchasePlanDAO;
import com.elecnor.ecosystem.dao.DomainApplicationPlanMappingDAO;
import com.elecnor.ecosystem.dao.DomainApplicationPlanMappingTrackingDAO;
import com.elecnor.ecosystem.dao.DomainDetailDAO;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.dao.UserRolesMappingDAO;
import com.elecnor.ecosystem.service.PaymentService;
import com.elecnor.ecosystem.util.Utility;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.PdfWriter;
import com.stripe.Stripe;
import com.stripe.model.Charge;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	DomainDetailDAO domainDetailDAO;
	@Autowired
	UserDetailDAO applicationUserDetailDAO;
	@Autowired
	DomainApplicationPlanMappingDAO domainApplicationPlanMappingDAO;
	@Autowired
	DomainApplicationPlanMappingTrackingDAO domainApplicationPlanMappingTrackingDAO;
	@Autowired
	ApplicationInvoiceDAO applicationInvoiceDAO;
	@Autowired
	ApplicationDetailsDAO applicationDetailsDAO;
	@Autowired
	ApplicationPurchasePlanDAO applicationPurchasePlanDAO;
	@Autowired
	ApplicationInvoiceInDetailDAO applicationInvoiceInDetailDAO;
	@Autowired
	UserRolesMappingDAO userRolesMappingDAO;
	/*@Autowired
	EmailLogDetailsService emailLogDetailsService;*/

	// Following things need to be done:
	// 0- Payment Validation.
	// 1- Add/Update Domain/Company Details
	// 2- Add/Update User Details
	// 3- Assin Role to the user if User is new(temp)
	// 4- Generate a new Invoice
	// 5- Iterate through all the plans selected,If any plan already exist
	// for That Application and Domain, Update
	// Domain Application Plan Mapping and Keep Track of old Plan in
	// Tracking Table Table else add new Mapping Details.
	// 6- Add Plans to newly created Invoice.
	// 7- Write Invoice in PDF
	// 8- Send User an email , attaching invoice and domain details if any.

	// Need to make entire process transactional.@Ankur, need to re-think.
	@SuppressWarnings("unused")
	@Override
	public String saveOrUpdatePaymentDetails(UserDetail userDetails, DomainDetail domainDetails, HttpServletRequest request, HttpSession httpSession,
			Boolean isTempUser, String selectedPlanIds[], PaymentPojo paymentPojo) throws Exception {

		String paymentResult, domainResult, userResult, roleResult, invoiceResult, planResult, trackingTableResult, planInvoiceResult, sendEmailResult;
		paymentResult = domainResult = userResult = roleResult = invoiceResult = planResult = trackingTableResult = planInvoiceResult = sendEmailResult = null;
		Logger log = Logger.getLogger(PaymentServiceImpl.class.getName());
		Utility util = new Utility();

		String result = null;
		// creating bean variables
		// Commented by vaibhav.
		/*DomainApplicationPlanMapping domainApplicationPlanMapping = null;
		DomainApplicationPlanMappingTracking domainApplicationPlanMappingTracking = null;
		ApplicationInvoice applicationInvoice = null;
		ApplicationInvoicePlanMapping applicationInvoicePlanMapping = null;
		PaymentHelper helper = new PaymentHelper();
		ArrayList<ApplicationPlanDirectory> selectedPlans = new ArrayList<ApplicationPlanDirectory>();

		// 0- Payment Validation.
		// Integrating Stripes for payment purposes.
		try {
			if (request.getParameter("paymentType").toUpperCase().trim().equalsIgnoreCase("paymentByCard".toUpperCase())) {
				result = transferPayment(paymentPojo);
			}

			// sendDomainAndUserDetails(domainDetails, userDetails,
			// sendPassword);
			if (!result.startsWith("ERROR:")) {

				// 1- Add/Update Domain/Company Details
				if (domainDetails.getDomainId() == null) {
					domainDetails = domainDetailDAO.saveDomainDetail(domainDetails);
				} else {
					result = domainDetailDAO.updateDomainDetail(domainDetails, domainDetails.getDomainId());
				}
				// Update Domain Related Details in other beans
				userDetails.setDomainDetail(domainDetails);

				// 2- Add/Update User Details
				userDetails = applicationUserDetailDAO.setUserSave(userDetails);

				// if the user is temporary, adding the user role for that user
				// in User
				// Role Mapping.
				// 3- Assin Role to the user if User is new(temp)
				if (isTempUser) {
					UserRolesMapping userRolesMapping = helper.getUserRoleMappingForAdminFromUser(userDetails);
					result = userRolesMappingDAO.addUserRoleMappingForNewDomain(userRolesMapping);
				}

				// Need to change this line

				// 4- Generate a new Invoice
				applicationInvoice = helper.getNewInvoiceDeatils();
				applicationInvoice.setDomainDetail(domainDetails);
				applicationInvoice.setSubmittedBy(userDetails);

				ApplicationInvoice applicationInvoiceBean = applicationInvoiceDAO.setSaveInvoice(applicationInvoice);
				ApplicationPlanDirectory plan = null;
				// 5- Iterate through all the plans selected,If any plan already
				// exist
				// for That Application and Domain, Update
				// Domain Application Plan Mapping and Keep Track of old Plan in
				// Tracking Table Table else add new Mapping Details.

				if (applicationInvoiceBean != null) {
					for (int i = 0; i < selectedPlanIds.length; i++) {
						applicationInvoicePlanMapping = new ApplicationInvoicePlanMapping();

						// Fetching Plan Details from DB Based on Id.

						plan = applicationPurchasePlanDAO.getApplicationPlanDetailsByPlanId(Integer.parseInt(selectedPlanIds[i]));
						domainApplicationPlanMapping = helper.getDomainPlanMappingFromPlan(plan);
						domainApplicationPlanMapping.setUserDetail(userDetails);
						domainApplicationPlanMapping.setDomainDetail(domainDetails);
						domainApplicationPlanMapping.setApplicationPlanDirectory(plan);
						
						// Temp user cannot have any mapping in DB.
						if (isTempUser == false) {
							DomainApplicationPlanMapping oldDomainApplicationPlanMapping = domainApplicationPlanMappingDAO.getPlanIfExistForDomain(
									domainDetails.getDomainId(), plan.getApplicationDirectory().getApplicationId());
							
							if (oldDomainApplicationPlanMapping != null) {
								domainApplicationPlanMappingTracking = helper.getTrackingDetails(oldDomainApplicationPlanMapping);
								result = domainApplicationPlanMappingTrackingDAO.addUpdateTrackingDetails(domainApplicationPlanMappingTracking);

								domainApplicationPlanMapping.setUpdatedDate(new Date());
								domainApplicationPlanMapping.setUpdatedBy(userDetails);
								result = domainApplicationPlanMappingDAO.updateDomainApplicationPlanMappingDetails(domainApplicationPlanMapping,
										oldDomainApplicationPlanMapping.getSubscriptionId());
							}
						} else {
							domainApplicationPlanMapping.setSubmittedBy(userDetails);
							domainApplicationPlanMapping.setSubmittedDate(new Date());
							domainApplicationPlanMapping = domainApplicationPlanMappingDAO
									.addDomainApplicationPlanMappingDetails(domainApplicationPlanMapping);
						}

						// 6- Plans to that invoice
						applicationInvoicePlanMapping.setSubmittedBy(userDetails);
						applicationInvoicePlanMapping.setDomainDetail(domainDetails);
						applicationInvoicePlanMapping.setDomainApplicationPlanMapping(domainApplicationPlanMapping);
						applicationInvoicePlanMapping.setApplicationDirectory(plan.getApplicationDirectory());
						applicationInvoicePlanMapping.setApplicationInvoice(applicationInvoiceBean);
						applicationInvoicePlanMapping.setSubmittedDate(new Date());
						result = applicationInvoiceInDetailDAO.addPlansToInvoice(applicationInvoicePlanMapping);
						selectedPlans.add(plan);
					}
				}

				// 7- Write Invoice in PDF
				helper.addReadPDFData(userDetails, selectedPlans, applicationInvoiceBean, request, httpSession);

				// 8- Send User an email , attaching invoice and domain details
				// if any.
				Map<Object, Object> logMap = helper.sendDomainAndUserDetails(domainDetails, userDetails, httpSession);
				emailLogDetailsService.setEmailLogDetailsBean(util.getJsonResult(logMap), userDetails);
				
				httpSession.setAttribute("selectedUser", userDetails);
				httpSession.setAttribute("isTemporaryUser", false);
				//userRolesList = userRolesMappingDAO.getRoleForUser(userEmailId);
				httpSession.setAttribute("userRolesList",  userRolesMappingDAO.getRoleForUser(userDetails.getEmailId()));
				httpSession.setAttribute("userRole", ConstantUtil.ADMIN_ROLE_ID);
				
			} else {
				// Payment Failed With Stripes
			}

		} catch (Exception e) {
			e.printStackTrace();
		}*/

		return result;
	}

	/**
	 * Method for deduct payment
	 * 
	 * @param paymentObj
	 *            Harsh Verma
	 */
	private String transferPayment(PaymentPojo paymentObj) {
		Stripe.apiKey = "sk_test_fuXb1afgVTwbeWI0IkDO7NlI";
		String result = "";
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		int priceInCents = (int) (paymentObj.getFinalPrice() * 100);
		chargeParams.put("amount", priceInCents);
		chargeParams.put("currency", "usd");
		chargeParams.put("card", paymentObj.getStripeToken());
		try {
			Charge create = Charge.create(chargeParams);
			result = create.getId();
		} catch (Exception e) {
			e.printStackTrace();
			result = "ERROR : " + e.getMessage();
		}
		return result;
	}
}
