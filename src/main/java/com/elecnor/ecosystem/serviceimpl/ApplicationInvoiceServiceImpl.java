package com.elecnor.ecosystem.serviceimpl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.ApplicationInvoice;
import com.elecnor.ecosystem.bean.ApplicationInvoicePlanMapping;
import com.elecnor.ecosystem.bean.ApplicationPlanDirectory;
import com.elecnor.ecosystem.bean.DomainApplicationPlanMapping;
import com.elecnor.ecosystem.bean.DomainApplicationPlanMappingTracking;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ApplicationDetailsDAO;
import com.elecnor.ecosystem.dao.ApplicationInvoiceDAO;
import com.elecnor.ecosystem.dao.ApplicationInvoiceInDetailDAO;
import com.elecnor.ecosystem.dao.ApplicationPurchasePlanDAO;
import com.elecnor.ecosystem.dao.DomainApplicationPlanMappingDAO;
import com.elecnor.ecosystem.dao.DomainApplicationPlanMappingTrackingDAO;
import com.elecnor.ecosystem.service.ApplicationInvoiceService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.PropertyFileReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service
public class ApplicationInvoiceServiceImpl implements ApplicationInvoiceService {

	@Autowired
	ApplicationInvoiceDAO applicationInvoiceDAO;
	@Autowired
	ApplicationDetailsDAO applicationDetailsDAO;
	@Autowired
	ApplicationPurchasePlanDAO applicationPurchasePlanDAO;
	@Autowired
	DomainApplicationPlanMappingDAO domainApplicationPlanMappingDAO;
	@Autowired
	DomainApplicationPlanMappingTrackingDAO domainApplicationPlanMappingTrackingDAO;
	@Autowired
	ApplicationInvoiceInDetailDAO applicationInvoiceInDetailDAO;

	@Override
	public String setSaveOrUpdateInvoice(JsonArray jsonArray, UserDetail userDetails, DomainDetail domainDetailsBean) throws Exception {

		// HArdCoded values, needed to changed later
		String result = null;
		Long subscriptionId = 0L;
		ApplicationInvoice applicationInvoiceDetail = new ApplicationInvoice();
		DomainApplicationPlanMapping domainApplicationPlanMapping = new DomainApplicationPlanMapping();
		ApplicationInvoicePlanMapping applicationInvoicePlanMapping = new ApplicationInvoicePlanMapping();
		DomainApplicationPlanMappingTracking domainApplicationPlanMappingTracking = new DomainApplicationPlanMappingTracking();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		try {
			applicationInvoiceDetail.setDomainDetail(domainDetailsBean);
			ArrayList<ApplicationDirectory> allApplicationDetailsList = applicationDetailsDAO.getApplicationDetails();
			ArrayList<ApplicationPlanDirectory> allPlanList = applicationPurchasePlanDAO.getPurchasePlanDAO();
			JsonObject jObject = jsonArray.get(jsonArray.size() - 1).getAsJsonObject();
			String invoiceId = jObject.get("invoiceId").getAsString();
			if (!invoiceId.isEmpty()) {
				applicationInvoiceDetail.setInvoiceId(Long.parseLong(invoiceId));
			} else {
				applicationInvoiceDetail.setInvoiceId(0L);
			}
			String additionalCharges = jObject.get("additionalCharges").getAsString();
			if (!additionalCharges.isEmpty()) {
				applicationInvoiceDetail.setAdditionalCharges(jObject.get("additionalCharges").getAsDouble());
			} else {
				applicationInvoiceDetail.setAdditionalCharges(0D);
			}
			applicationInvoiceDetail.setInterestApplicable(jObject.get("isInterestApplicable").getAsBoolean());
			Date invoiceDueDate = formatter.parse(jObject.get("invoiceDueDate").getAsString());
			Date invoiceGeneratedDate = formatter.parse(jObject.get("invoiceGeneratedDate").getAsString());
			applicationInvoiceDetail.setInvoiceDueDate(invoiceDueDate);
			applicationInvoiceDetail.setInvoiceGeneratedDate(invoiceGeneratedDate);
			applicationInvoiceDetail.setLateFeesApplicable(jObject.get("isLateFeesApplicable").getAsBoolean());
			applicationInvoiceDetail.setInvoiceType(jObject.get("invoiceType").getAsString());
			applicationInvoiceDetail.setStatus("ACTIVE");
			if (applicationInvoiceDetail.getInvoiceId() == 0) {
				applicationInvoiceDetail.setSubmittedDate(new Date());
				applicationInvoiceDetail.setSubmittedBy(userDetails);
				ApplicationInvoice applicationInvoiceDetailBean = applicationInvoiceDAO.setSaveInvoice(applicationInvoiceDetail);
				if (applicationInvoiceDetailBean != null) {
					for (int i = 0; i < jsonArray.size() - 1; i++) {
						ApplicationDirectory appSelected = null;
						ApplicationPlanDirectory planSelected = null;
						JsonObject json = jsonArray.get(i).getAsJsonObject();
						for (ApplicationDirectory app : allApplicationDetailsList) {
							if (app.getApplicationName().equalsIgnoreCase(json.get("applicationName").getAsString())) {
								appSelected = app;
							}
						}
						for (ApplicationPlanDirectory planList : allPlanList) {
							if (planList.getPlanName().equalsIgnoreCase(json.get("planName").getAsString())) {
								planSelected = planList;
							}
						}
						Date planStartDate = formatter.parse(json.get("startDate").getAsString());
						Date planExpiryDate = formatter.parse(json.get("endDate").getAsString());
						domainApplicationPlanMapping.setApplicationDirectory(appSelected);
						domainApplicationPlanMapping.setApplicationPlanDirectory(planSelected);
						domainApplicationPlanMapping.setDomainDetail(domainDetailsBean);
						domainApplicationPlanMapping.setPlanExpiryDate(planExpiryDate);
						domainApplicationPlanMapping.setPlanStartDate(planStartDate);
						domainApplicationPlanMapping.setUserDetail(userDetails);
						// checking whether the selected application is existed
						// or not
						DomainApplicationPlanMapping details = domainApplicationPlanMappingDAO.getPlanIfExistForDomain(
								domainDetailsBean.getDomainId(), appSelected.getApplicationId());
						// if the application existed we have to store the
						// details of
						// DomainApplicationPlanMapping
						// to the DomainApplicationPlanMappingTracking
						if (details != null) {
								
								subscriptionId = details.getSubscriptionId();
								applicationInvoicePlanMapping.setDomainApplicationPlanMapping(details);
								// adding the existed details to the
								// DomainApplicationPlanMappingTracking
								// from DomainApplicationPlanMapping
								domainApplicationPlanMappingTracking.setApplicationPlanDirectory(details.getApplicationPlanDirectory());
								domainApplicationPlanMappingTracking.setDomainApplicationPlanMapping(details);
								domainApplicationPlanMappingTracking.setPlanExpiryDate(details.getPlanExpiryDate());
								domainApplicationPlanMappingTracking.setPlanStartDate(details.getPlanStartDate());
								domainApplicationPlanMappingTracking.setSubmittedBy(details.getSubmittedBy());
								domainApplicationPlanMappingTracking.setSubmittedDate(details.getSubmittedDate());
								domainApplicationPlanMappingTracking.setSubscriptionTrackingId(details.getSubscriptionId());
								domainApplicationPlanMappingTracking.setUpdatedBy(details.getUpdatedBy());
								domainApplicationPlanMappingTracking.setUpdatedDate(details.getUpdatedDate());
								domainApplicationPlanMappingTracking.setUserDetail(details.getUserDetail());
								domainApplicationPlanMappingTracking.setApplicationDirectory(details.getApplicationDirectory());
								domainApplicationPlanMappingTracking.setDomainDetail(details.getDomainDetail());
							result = domainApplicationPlanMappingTrackingDAO.addUpdateTrackingDetails(domainApplicationPlanMappingTracking);
							domainApplicationPlanMapping.setUpdatedDate(new Date());
							domainApplicationPlanMapping.setUpdatedBy(userDetails);
							result = domainApplicationPlanMappingDAO.updateDomainApplicationPlanMappingDetails(domainApplicationPlanMapping, subscriptionId);
						} else {
							domainApplicationPlanMapping.setSubmittedBy(userDetails);
							domainApplicationPlanMapping.setSubmittedDate(new Date());
							DomainApplicationPlanMapping domainApplicationPlanMappingBean = domainApplicationPlanMappingDAO.addDomainApplicationPlanMappingDetails(domainApplicationPlanMapping);
							applicationInvoicePlanMapping.setDomainApplicationPlanMapping(domainApplicationPlanMappingBean);
						}
						applicationInvoicePlanMapping.setApplicationDirectory(appSelected);
						applicationInvoicePlanMapping.setApplicationInvoice(applicationInvoiceDetailBean);
						applicationInvoicePlanMapping.setDomainDetail(domainDetailsBean);
						applicationInvoicePlanMapping.setSubmittedBy(userDetails);
						applicationInvoicePlanMapping.setSubmittedDate(new Date());
						result = applicationInvoiceInDetailDAO.addPlansToInvoice(applicationInvoicePlanMapping);
					}
				}
			} else {
				applicationInvoiceDetail.setUpdatedDate(new Date());
				applicationInvoiceDetail.setUpdatedBy(userDetails);
				result = applicationInvoiceDAO.setUpdateInvoice(applicationInvoiceDetail);
				// domainApplicationPlanMapping.setUpdatedDate(new Date());
				// domainApplicationPlanMapping.setUpdatedBy(userDetails);
				// result =
				// domainApplicationPlanMappingDAO.updateAllDetails(domainApplicationPlanMapping,
				// subscriptionId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public HashMap<String, Object> uploadDepartmentFile() throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			ClassLoader cl = PropertyFileReader.class.getClassLoader();
			InputStream in = cl.getResourceAsStream("ecosystem.properties");
			Properties prop = new Properties();
			prop.load(in);
			String isLateFee = prop.getProperty(ConstantUtil.APPLICATION_INVOICE_IS_LATE_FEE);
			String isInterest = prop.getProperty(ConstantUtil.APPLICATION_INVOICE_IS_INTEREST);
			Double late = Double.parseDouble(isLateFee);
			Double interest = Double.parseDouble(isInterest);
			resultMap.put("isLateFee", late);
			resultMap.put("isInterest", interest);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultMap;
	}

}
