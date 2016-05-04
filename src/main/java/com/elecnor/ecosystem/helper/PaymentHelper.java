package com.elecnor.ecosystem.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.ApplicationInvoice;
import com.elecnor.ecosystem.bean.ApplicationPlanDirectory;
import com.elecnor.ecosystem.bean.DomainApplicationPlanMapping;
import com.elecnor.ecosystem.bean.DomainApplicationPlanMappingTracking;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.PaymentPojo;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.UserRoles;
import com.elecnor.ecosystem.bean.UserRolesMapping;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.PDFUtil;
import com.elecnor.ecosystem.util.PropertyFileReader;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PaymentHelper {
	public DomainDetail getDomainDetailBeanFromRequest(HttpServletRequest request, UserDetail userDetails, MultipartFile domainLogo) {
		DomainDetail domainDetails = new DomainDetail();
		try {
			domainDetails.setDomainId(Long.parseLong(request.getParameter("domainId")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		// domain logo code..do not delete
		/*
		 * if (!domainLogo.isEmpty() && domainLogo != null) { try {
		 * domainDetails.setDomainLogo(domainLogo.getBytes()); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */
		domainDetails.setCompanyAddress(request.getParameter("companyAddress"));
		domainDetails.setCompanyName(request.getParameter("companyName"));
		domainDetails.setCompanyPhoneCarrier(request.getParameter("companyPhoneCarrier"));
		domainDetails.setCompanyPhoneNumber(request.getParameter("companyPhoneNumber"));
		domainDetails.setDomainName(request.getParameter("domainName"));
		domainDetails.setStatus("ACTIVE");
		if (domainDetails.getDomainId() == null) {
			domainDetails.setDomainCreatedDate(new Date());
		} else {
			domainDetails.setSubmittedEmailId(userDetails.getEmailId());
			domainDetails.setSubmittedName(userDetails.getFirstName());
			domainDetails.setSubmittedUserId(userDetails.getUserId());
			domainDetails.setUpdatedDate(new Date());
		}
		return domainDetails;
	}

	public PaymentPojo getPaymentPojoBeanFromRequest(HttpServletRequest request, String loggedInUserEmailId) {
		PaymentPojo paymentPojo = new PaymentPojo();
		try {
			paymentPojo.setFinalPrice(Float.parseFloat(request.getParameter("finalPrice")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		paymentPojo.setStripeToken(request.getParameter("stripeToken"));
		paymentPojo.setLoggedUserEmailId(loggedInUserEmailId);

		return paymentPojo;
	}

	public UserDetail getUpdatedUserDetailsBean(HttpServletRequest request, UserDetail userDetails, UserDetail loggedInUserDetails) throws Exception {
		userDetails.setUserId(loggedInUserDetails.getUserId());
		userDetails.setEmailId(loggedInUserDetails.getEmailId());
		userDetails.setPassword(loggedInUserDetails.getPassword());
		userDetails.setStatus(loggedInUserDetails.getStatus());
		userDetails.setUpdatedBy(userDetails);
		userDetails.setUpdatedDate(new Date());
		userDetails.setSubmittedBy(loggedInUserDetails.getSubmittedBy());
		userDetails.setSubmittedDate(loggedInUserDetails.getSubmittedDate());
		// userDetails.setRoleNameEcosystem("ADMIN");
		return userDetails;
	}

	public String[] getSelectedPlanIds(HttpServletRequest request) {
		String tempTableData = request.getParameter("summarisedSubscriptionArrayIds");
		tempTableData = tempTableData.substring(1, tempTableData.length() - 1);
		return tempTableData.split(",");
	}

	public UserRolesMapping getUserRoleMappingForAdminFromUser(UserDetail userDetails) {
		UserRolesMapping userRolesMapping = new UserRolesMapping();
		UserRoles userRoles = new UserRoles();
		userRolesMapping.setUserRolesDetail(userDetails);
		userRoles.setRoleId(ConstantUtil.ADMIN_ROLE_ID);
		userRolesMapping.setIsDefaultRole(false);
		userRolesMapping.setRoleName("ADMIN");
		userRolesMapping.setUserEmailId(userDetails.getEmailId());
		userRolesMapping.setUserRolesDetail(userDetails);
		userRolesMapping.setUserRolesId(userRoles);
		return userRolesMapping;
	}

	public ApplicationInvoice getNewInvoiceDeatils() {
		ApplicationInvoice applicationInvoice = new ApplicationInvoice();
		applicationInvoice.setInvoiceGeneratedDate(new Date());
		applicationInvoice.setInvoiceDueDate(new Date());
		applicationInvoice.setInvoiceType("Paid");
		applicationInvoice.setStatus("ACTIVE");
		applicationInvoice.setSubmittedDate(new Date());
		applicationInvoice.setInterestApplicable(false);
		applicationInvoice.setLateFeesApplicable(false);
		return applicationInvoice;
	}

	public DomainApplicationPlanMapping getDomainPlanMappingFromPlan(ApplicationPlanDirectory plan) {
		DomainApplicationPlanMapping domainApplicationPlanMapping = new DomainApplicationPlanMapping();
		domainApplicationPlanMapping.setApplicationPlanDirectory(plan);
		domainApplicationPlanMapping.setApplicationDirectory(plan.getApplicationDirectory());
		domainApplicationPlanMapping.setPlanStartDate(new Date());
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int months = plan.getPlanValidityInMonths().intValue();
		c.add(Calendar.MONTH, months);
		Date planExpiryDate = c.getTime();
		domainApplicationPlanMapping.setPlanExpiryDate(planExpiryDate);
		return domainApplicationPlanMapping;
	}

	public DomainApplicationPlanMappingTracking getTrackingDetails(DomainApplicationPlanMapping oldPlanDetails) {
		// subscriptionId = oldPlanDetails.getSubscriptionId();
		DomainApplicationPlanMappingTracking domainApplicationPlanMappingTracking = new DomainApplicationPlanMappingTracking();
		domainApplicationPlanMappingTracking.setApplicationPlanDirectory(oldPlanDetails.getApplicationPlanDirectory());
		domainApplicationPlanMappingTracking.setDomainApplicationPlanMapping(oldPlanDetails);
		domainApplicationPlanMappingTracking.setPlanExpiryDate(oldPlanDetails.getPlanExpiryDate());
		domainApplicationPlanMappingTracking.setPlanStartDate(oldPlanDetails.getPlanStartDate());
		domainApplicationPlanMappingTracking.setSubmittedBy(oldPlanDetails.getSubmittedBy());
		domainApplicationPlanMappingTracking.setSubmittedDate(oldPlanDetails.getSubmittedDate());
		domainApplicationPlanMappingTracking.setSubscriptionTrackingId(oldPlanDetails.getSubscriptionId());
		domainApplicationPlanMappingTracking.setUpdatedBy(oldPlanDetails.getUpdatedBy());
		domainApplicationPlanMappingTracking.setUpdatedDate(oldPlanDetails.getUpdatedDate());
		domainApplicationPlanMappingTracking.setUserDetail(oldPlanDetails.getUserDetail());
		domainApplicationPlanMappingTracking.setApplicationDirectory(oldPlanDetails.getApplicationDirectory());
		domainApplicationPlanMappingTracking.setDomainDetail(oldPlanDetails.getDomainDetail());

		// TODO Auto-generated method stub
		return domainApplicationPlanMappingTracking;
	}

	public void addReadPDFData(UserDetail userDetails, ArrayList<ApplicationPlanDirectory> selectedPlans, ApplicationInvoice applicationInvoiceBean, HttpServletRequest request, HttpSession httpSession)
			throws DocumentException, MalformedURLException, IOException, FileNotFoundException {
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font smallNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font largeBold = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);

		ServletContext context = httpSession.getServletContext();

		String imageFile = context.getRealPath("resource/logo.png");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		float sum = 0;
		String filePath = context.getRealPath("resource/Invoice.pdf");
		File newFile = new File(filePath);
		if (!newFile.exists()) {
			newFile.createNewFile();
		} else {
		}
		// Font tdue = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD,
		// BaseColor.BLACK);
		BaseColor basecolor = new BaseColor(230, 230, 243);

		Document document = new Document();

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(newFile));
		PDFUtil pdfHeader = new PDFUtil();
		writer.setPageEvent(pdfHeader);
		document.open();
		for (int i = 0; i < selectedPlans.size(); i++) {
			sum = (float) (sum + selectedPlans.get(i).getPrice());
		}
		Paragraph preface = new Paragraph();
		Paragraph p = new Paragraph("INVOICE", largeBold);
		p.setAlignment(Element.ALIGN_RIGHT);
		preface.add(p);
		Image image = Image.getInstance(imageFile);
		image.setAlignment(Element.ALIGN_LEFT);
		image.setAbsolutePosition(30f, 760f);
		image.scaleAbsolute(image.getWidth() / 2, image.getHeight() / 2);
		preface.add(image);
		preface.add(new Paragraph(" "));
		preface.add(new Paragraph(" "));
		preface.add(new Paragraph("Belco Elecnor,Inc.", smallBold));
		preface.add(new Paragraph("4331 scheafer Ave China", smallNormal));
		preface.add(new Paragraph("CA 91710", smallNormal));
		preface.add(new Paragraph("P: (909)993-5470 F:(909)993-5476", smallNormal));
		Paragraph number = new Paragraph("                                                                                                               Invoice # :"
				+ applicationInvoiceBean.getInvoiceId());
		preface.add(number);
		Paragraph date = new Paragraph(userDetails.getDomainDetail().getDomainName()
				+ "                                                                                                         Invoice Date :"
				+ simpleDateFormat.format(applicationInvoiceBean.getInvoiceGeneratedDate()));
		// date.setAlignment(Element.ALIGN_RIGHT);
		preface.add(date);
		Paragraph pDue = new Paragraph();
		Chunk address = new Chunk("address" + "                ");
		Chunk totalDue = new Chunk("Total Due :" + sum);
		Chunk space = new Chunk("                                                                                  ");
		totalDue.setBackground(basecolor);
		totalDue.setFont(smallBold);
		pDue.add(address);
		pDue.add(space);
		pDue.add(totalDue);

		preface.add(pDue);
		preface.add(new Paragraph(" "));
		preface.add(new Paragraph(" "));
		preface.add(new Paragraph(userDetails.getDomainDetail().getCompanyAddress(), smallNormal));
		preface.add(new Paragraph(" "));
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setSpacingAfter(10f);
		table.setSpacingBefore(15f);
		table.setWidths(new float[] { 2f, 2f, 2f, 2f, 2f });

		PdfPCell c1 = new PdfPCell(new Phrase("Qty", smallBold));
		c1.setBackgroundColor(new BaseColor(230, 230, 243));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(5f);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Application", smallBold));
		c1.setBackgroundColor(new BaseColor(230, 230, 243));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(5f);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Plan", smallBold));
		c1.setBackgroundColor(new BaseColor(230, 230, 243));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(5f);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Description", smallBold));
		c1.setBackgroundColor(new BaseColor(230, 230, 243));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(5f);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Price", smallBold));
		c1.setBackgroundColor(new BaseColor(230, 230, 243));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(5f);
		table.addCell(c1);
		table.setHeaderRows(1);
		int total = selectedPlans.size();
		for (int i = 0; i < total; i++) {
			c1 = new PdfPCell(new Phrase("1", smallNormal));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			String appName = selectedPlans.get(i).getApplicationDirectory().getApplicationName().toString();
			c1 = new PdfPCell(new Phrase(appName, smallNormal));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			c1 = new PdfPCell(new Phrase(selectedPlans.get(i).getPlanName().toString(), smallNormal));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			c1 = new PdfPCell(new Phrase(selectedPlans.get(i).getPlanDescription().toString(), smallNormal));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			c1 = new PdfPCell(new Phrase(selectedPlans.get(i).getPrice().toString(), smallNormal));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

		}

		sum = sum - 100;
		c1 = new PdfPCell(new Phrase("Total ", smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(4);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase(String.valueOf(sum), smallNormal));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		preface.add(table);
		preface.add(new Paragraph(" "));
		preface.add(new Paragraph(" "));
		Paragraph para = new Paragraph();

		Image image1 = Image.getInstance(context.getRealPath("resource/mastercard.png"));
		image1.scaleAbsolute(image1.getWidth() / 2, image1.getHeight() / 2);
		image1.setAbsolutePosition(20, writer.getPageSize().getBottom() + 35f);
		para.add(image1);
		Image image2 = Image.getInstance(context.getRealPath("resource/visa.png"));
		image2.scaleAbsolute(image2.getWidth() / 2, image2.getHeight() / 2);
		image2.setAbsolutePosition(image1.getWidth(), writer.getPageSize().getBottom() + 35f);
		para.add(image2);
		Image image3 = Image.getInstance(context.getRealPath("resource/american.png"));
		image3.scaleAbsolute(image3.getWidth() / 2, image3.getHeight() / 2);
		image3.setAbsolutePosition(image1.getWidth() + image2.getWidth() - 20f, writer.getPageSize().getBottom() + 35f);
		para.add(image3);
		Image image4 = Image.getInstance(context.getRealPath("resource/paypal.png"));
		image4.scaleAbsolute(image4.getWidth() / 2, image4.getHeight() / 2);
		image4.setAbsolutePosition(image1.getWidth() + image2.getWidth() + image3.getWidth() - 40f, writer.getPageSize().getBottom() + 35f);
		para.add(image4);
		// PdfPTable imageTable=new PdfPTable(4);
		// imageTable.setWidthPercentage(70f);
		// float[] columnWidths = new float[] {2f, 2f, 2f, 2f};
		// imageTable.setWidths(columnWidths);
		// Image image1= Image.getInstance("D:\\mastercard.png");
		// image1.scaleAbsolute(image.getWidth() / 4, image.getHeight() / 4);
		// PdfPCell c=new PdfPCell(image1);
		// c.setBorder(0);
		// c.setMinimumHeight(20f);
		// imageTable.addCell(c);
		// image1= Image.getInstance("D:\\visa.png");
		// image1.scaleAbsolute(image.getWidth() / 4, image.getHeight() / 4);
		// c=new PdfPCell(image1);
		// c.setBorder(0);
		// c.setPaddingLeft(5f);
		// c.setMinimumHeight(20f);
		// imageTable.addCell(c);
		// image1= Image.getInstance("D:\\american.png");
		// image1.scaleAbsolute(image.getWidth() / 4, image.getHeight() / 4);
		// c=new PdfPCell(image1);
		// c.setBorder(0);
		// c.setPaddingLeft(5f);
		// c.setMinimumHeight(20f);
		// imageTable.addCell(c);
		// image1= Image.getInstance("D:\\paypal.png");
		// image1.scaleAbsolute(image.getWidth() / 4, image.getHeight() / 4);
		// c=new PdfPCell(image1);
		// c.setBorder(0);
		// c.setPaddingLeft(5f);
		// c.setMinimumHeight(20f);
		// imageTable.addCell(c);
		// para.add(imageTable);
		// para.setAlignment(Element.ALIGN_LEFT);
		preface.add(para);
		document.add(preface);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		// table.setHeaderRows(1);

		// table.addCell("1.0");
		// table.addCell("1.1");
		// table.addCell("1.2");
		// table.addCell("2.1");
		// table.addCell("2.2");
		// table.addCell("2.3");
		// PdfWriter writer = PdfWriter.getInstance(document, new
		// FileOutputStream("D:\\sampleInvoiceMail.pdf"));
		// PdfPageEvent event = new PdfPageEvent() ;
		// event.onEndPage(writer, document);
		// writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
		// writer.setPageEvent((PdfPageEvent) event);
		document.close();
	}

	// sending details of domain and user and the products to the user
	public Map<Object, Object> sendDomainAndUserDetails(DomainDetail domainDetails, UserDetail userDetails, HttpSession session) throws Exception {

		ServletContext context = session.getServletContext();
		String hostName = PropertyFileReader.getInstance().getStringProperty("hostName");
		String portNumber = PropertyFileReader.getInstance().getStringProperty("portNumber");
		String actionUrl = "Here are the domain details you entered.Please check below.\nDomainName : " + domainDetails.getDomainName() + "\nCompany Name : " + domainDetails.getCompanyName()
				+ "\nCompany Address : " + domainDetails.getCompanyAddress() + "\nHere are the User Details.Please check below. \n Email Id : " + userDetails.getEmailId().trim() + "\n Password : "
				+ userDetails.getDecryptedPassword();
		String emailText = "Hello " + userDetails.getFirstName() + ", ";
		emailText += "\nThanks for showing interest in using <a href='http://" + hostName + ":" + portNumber + "/Ecosystem/'> Ecosystem<a>. " + "\n" + actionUrl
				+ "\nWe are pleased to inform that your order is under process and plans will be activated within a few days...."
				+ "\nPlease find your invoice for the transaction with this mail.....";
		try {
			// emailHandlerUtility needs to be integrated after some
			// clarification @Ashutosh
			
			// Thread thread = new Thread(new
			// com.elecnor.ecosystem.util.EmailThread(userDetails.getEmailId().trim(),
			// null, null,
			// "Ecosystem : Invoice Copy", emailText, false,
			// context.getRealPath("resource/Invoice.pdf") + "&&&&" +
			// "BelcoInvoice.pdf"));
			// thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// set email log
		Map<Object, Object> logMap = new HashMap<Object, Object>();
		logMap.put("emailTo", userDetails.getEmailId().trim());
		logMap.put("emailFrom", "ECOSYSTEM");
		logMap.put("emailCc", null);
		logMap.put("emailBcc", null);
		logMap.put("emailSubject", "Ecosystem : Invoice Copy");
		logMap.put("emailContent", emailText);
		logMap.put("logRead", false);
		return logMap;
		// result =
		// emailLogDetailsService.setEmailLogDetailsBean(cutomerApprovalEmailTo,
		// userDetail.getEmailId(), cutomerApprovalEmailCc,
		// cutomerApprovalEmailBcc,
		// "Request For Approval", emailText, null, userDetail);
	}
}
