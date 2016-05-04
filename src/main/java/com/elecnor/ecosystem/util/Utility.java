package com.elecnor.ecosystem.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.CompanyCalendar;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.UserRolesMapping;
import com.email.utility.executor.EmailTaskExecutor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class Utility {
	@Autowired
	EmailTaskExecutor emailHandlerThread;

	private DecimalFormat f = new DecimalFormat("##.##");

	@CachePut("rowsValidate")
	public HashMap<String, ?> addToCache(HashMap<String, Object> resultMap) {
		return resultMap;
	}

	@Cacheable("rowsValidate")
	public HashMap<String, Object> findRows(HashMap<String, Object> resultMap, String s) {
		return resultMap;
	}

	public String getJsonResult(HashMap<Object, Object> resultMap) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(resultMap);
		return json;

	}

	public String getJsonResultWithoutExpose(HashMap<Object, Object> resultMap) {
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(resultMap);
		return json;

	}

	public String getJsonResultWithoutExposeString(HashMap<String, Object> resultMap) {
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(resultMap);
		return json;

	}

	public String getJsonResult(List<?> resultMap) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("MM-dd-yyyy").create();
		String json = gson.toJson(resultMap, new TypeToken<ArrayList<JobDetail>>() {
		}.getType());
		return json;

	}

	public String getJsonResult(Object resultMap) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("MM-dd-yyyy").create();
		String json = gson.toJson(resultMap);
		return json;

	}
	
	//to expose transient field
	public String getJsonResultExposingTransientField(Object resultMap) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().excludeFieldsWithModifiers().setDateFormat("MM-dd-yyyy").create();
		String json = gson.toJson(resultMap);
		return json;

	}

	public String getJsonResultexcludeFieldsWithoutExposeAnnotation(HashMap<Object, Object> resultMap) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(resultMap);
		return json;
	}

	public String getJsonResultForDate(HashMap<Object, Object> resultMap) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("MM-dd-yyyy").create();
		String json = gson.toJson(resultMap);
		return json;
	}

	public void sendExceptionEmail(Exception e) {
		e.printStackTrace();
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		emailHandlerThread.executeExceptionEmailTask(errors.toString());
	}

	public HashMap<Object, Object> responseBuilder(CompanyCalendar result) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		if (result.getEventId() != null) {
			resultMap.put("ajaxResult", "success");
			resultMap.put("eventId", result.getEventId());
		} else {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", result);
		}

		return resultMap;
	}

	public HashMap<Object, Object> responseBuilder(String result) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		if (result == null) {
			resultMap.put("ajaxResult", "success");
		} else {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", result);
		}

		return resultMap;
	}

	public HashMap<Object, Object> responseBuilder(Boolean result) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		if (result) {
			resultMap.put("ajaxResult", "success");
		} else {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", result);
		}

		return resultMap;
	}

	public HashMap<String, Object> responseBuilder(List<?> result) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if (result == null) {
			resultMap.put("ajaxResult", "success");
		} else {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", result);
		}

		return resultMap;
	}

	// @Rishabh
	public HashMap<String, Object> responseBuilderCustom(List<?> result) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if (result == null) {
			resultMap.put("ajaxResult", "failure");
		} else {
			resultMap.put("ajaxResult", "success");
			resultMap.put("reason", result);
		}

		return resultMap;
	}

	public String convertor(double d) throws Exception {
		return d == 0.0 || d == 0 ? "" : f.format(d) + "";
	}

	public String convertor(long d) throws Exception {
		return d == 0 || d == 0 ? "" : f.format(d) + "";
	}

	public String convertor(String d) throws Exception {
		return d == null ? "" : d;
	}

	public static double checkNullForGetters(Double d) {
		if (d != null) {
			return d;
		} else {
			return 0.0;
		}
	}

	public String convertor(Date d) throws Exception {
		SimpleDateFormat sdf = null;
		if (d == null) {
			return "";
		} else {
			sdf = new SimpleDateFormat("dd-MMM-yyyy");
			return sdf.format(d);
		}
	}

	public double convert2Double(String value) {
		try {
			if (value != null && !value.isEmpty())
				return convert2Double(Double.parseDouble(value));
			else
				return 0;
		} catch (Exception e) {
			return 0.0;
		}
	}

	public double convert2Double(double value) {
		return Math.round(value * 100.0) / 100.0;
	}

	public Date stringToDate(String sDate, String format) throws Exception {
		Date d = null;
		SimpleDateFormat sdf = null;
		try {
			if (sDate == null || sDate.trim().length() == 0) {
				return d;
			} else {
				sdf = new SimpleDateFormat(format);
				d = sdf.parse(sDate);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return d;
	}

	public String formatDate(Date dDate) throws Exception {
		SimpleDateFormat sdf = null;
		if (dDate == null)
			return "";
		sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String s1 = sdf.format(dDate);

		return s1;
	}

	public String formatDate2USStandard(Date dDate) throws Exception {
		SimpleDateFormat sdf = null;
		if (dDate == null)
			return "";
		sdf = new SimpleDateFormat("MM-dd-yyyy");
		String s1 = sdf.format(dDate);
		return s1;
	}

	public static Double returnValueForNull(Double value) {
		if (value == null) {
			return 0.0;
		} else
			return value;
	}

	public static Integer returnValueForNull(Integer value) {
		if (value == null) {
			return 0;
		} else
			return value;
	}

	public static Boolean returnValueForNull(Boolean value) {
		if (value == null) {
			return false;
		} else
			return value;
	}

	// Add number of days to a date
	public Date addDaysToDate(Date startDate, int noOfDays) {
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DATE, noOfDays); // Adding 5 days
		System.out.println("end date:" + c.getTime());
		return c.getTime();
	}

	public String addCashflowManpowerForProject(long jobId, String startDate, String endDate, String extendedDate, String coDate, long userId, String actionType) {
		String response = "success";
		try {

			PropertyFileReader propertyFileInstance = PropertyFileReader.getInstance();
			String hostName = propertyFileInstance.getStringProperty("hostName");
			String portNum = propertyFileInstance.getStringProperty("portNumber");
			String ecosystemDeployementName = propertyFileInstance.getStringProperty("MPRApplicationName");
			if ("save".equalsIgnoreCase(actionType)) {
				String url = "http://" + hostName + ":" + portNum + "/" + ecosystemDeployementName + "/addCashflowManpowerAPI?savedProjectID=" + jobId + "&&startDate="
						+ formatDate(stringToDate(startDate, "MM-dd-yyyy")) + "&&endDate=" + formatDate(stringToDate(endDate, "MM-dd-yyyy")) + "&&extendedDate="
						+ formatDate(stringToDate(extendedDate, "MM-dd-yyyy")) + "&&changeDate=" + formatDate(stringToDate(coDate, "MM-dd-yyyy")) + "&&logeduserID=" + userId;
				RestTemplate restTemplate = new RestTemplate();

				response = restTemplate.getForObject(url, String.class);
			} else {
				String url = "http://" + hostName + ":" + portNum + "/" + ecosystemDeployementName + "/addCashflowManpowerAPI?projectID=" + jobId + "&&startDate="
						+ formatDate(stringToDate(startDate, "MM-dd-yyyy")) + "&&endDate=" + formatDate(stringToDate(endDate, "MM-dd-yyyy")) + "&&extendedDate="
						+ formatDate(stringToDate(extendedDate, "MM-dd-yyyy")) + "&&changeDate=" + formatDate(stringToDate(coDate, "MM-dd-yyyy")) + "&&logeduserID=" + userId;
				RestTemplate restTemplate = new RestTemplate();

				response = restTemplate.getForObject(url, String.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response = errors.toString();
		}
		return response;

	}

	public String addRelatedDataIntoMPRForDuplicateProject(long originalJobId, long duplicateJobId) {
		String response = "success";
		try {

			PropertyFileReader propertyFileInstance = PropertyFileReader.getInstance();
			String hostName = propertyFileInstance.getStringProperty("hostName");
			String portNum = propertyFileInstance.getStringProperty("portNumber");
			String ecosystemDeployementName = propertyFileInstance.getStringProperty("MPRApplicationName");
			String url = "http://" + hostName + ":" + portNum + "/" + ecosystemDeployementName + "/DuplicateProject?originalJobId=" + originalJobId + "&&duplicateJobId=" + duplicateJobId;
			RestTemplate restTemplate = new RestTemplate();

			response = restTemplate.getForObject(url, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response = errors.toString();
		}
		return response;

	}

	public ExcelErrorDetails getExcelErrorDetails(int rowNum, int colNum, String msg) {
		ExcelErrorDetails e = new ExcelErrorDetails();
		e.setRowNumber(rowNum);
		e.setColNumber(colNum);
		e.setErrorMessage(msg);
		if (colNum != -1) {
			e.setExcelCell(ConstantUtil.columnMapping[colNum] + rowNum);
		}
		return e;
	}

	public boolean dateFormatValidation(String dateValueToCheck) {

		boolean returnValue = true;
		if (!dateValueToCheck.matches("^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)[0-9]{2}$")) {
			returnValue = false;
		}
		return returnValue;
	}

	public boolean emailFormatValidation(String emailValueToCheck) {

		boolean returnValue = false;
		Pattern emailPattern = Pattern.compile(ConstantUtil.EMAIL_FORMAT);
		Matcher matcher;
		matcher = emailPattern.matcher(emailValueToCheck);
		if (!(matcher.matches())) {
			returnValue = false;
		}
		return returnValue;
	}

	public String stringFetchingValidation(Row rowData, int colNumber) {
		// for strings,emails,dates
		String returnValue = "";
		try {
			returnValue = rowData.getCell(colNumber, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
		} catch (IllegalStateException illegalStateException) {
			returnValue = null;
		}
		return returnValue;
	}

	public String numericFetchingValidation(Row rowData, int colNumber) {
		// for integer,double
		String returnValue = "";
		try {
			returnValue = String.valueOf(rowData.getCell(colNumber, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
		} catch (IllegalStateException illegalStateException) {
			returnValue = null;
		}
		return returnValue;
	}

	public Date validateDateParsing(String dateToParse) {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date parsedDate = null;
		if (dateToParse.equalsIgnoreCase("")) {
			try {
				parsedDate = dateFormat.parse(dateToParse);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return parsedDate;
	}

	public ArrayList<ExcelErrorDetails> checkForNullValueInExcelValidation(int[] validationRequest, Row rowData, int colNumber, ArrayList<ExcelErrorDetails> rowErrorList) {
		int rowNumber = rowData.getRowNum();
		String isValidString;
		double isValidDouble;
		int isValidInteger;

		if (validationRequest[0] == 0) {
			// for string type
			isValidString = stringFetchingValidation(rowData, colNumber);
			if (isValidString == null) {
				rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field should have characters"));
			} else {
				if (validationRequest[1] == 1) {
					// check for blank also
					if (isValidString.equalsIgnoreCase("")) {
						rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field cannot be blank"));
					}
				}
			}
		}

		else if (validationRequest[0] == 1) {
			// for integer type
			isValidString = numericFetchingValidation(rowData, colNumber);
			if (isValidString == null) {
				rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field should only have integers and no characters"));
			} else {
				isValidDouble = Double.parseDouble(isValidString);
				if (validationRequest[1] == 1) {
					isValidInteger = (int) isValidDouble;
					if (isValidInteger == 0) {
						rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field cannot be blank"));
					}
				} else if (isValidDouble % 1 != 0.0) {
					rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field should only have integers and no fractional values"));
				}
			}
		}

		else if (validationRequest[0] == 2) {
			// for double type
			isValidString = numericFetchingValidation(rowData, colNumber);
			if (isValidString == null) {
				rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field should only have numbers and no characters"));
			} else {
				isValidDouble = Double.parseDouble(isValidString);
				if (validationRequest[1] == 1) {
					if (isValidDouble == 0.0) {
						rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field cannot be blank"));
					}
				}
			}
		}

		else if (validationRequest[0] == 3) { // for date type

			// validate fetching
			isValidString = stringFetchingValidation(rowData, colNumber);

			if (isValidString == null) { // validation fails
				rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "Date should be in proper format and with '-' or '.' as separators"));
			} else { // validation passes

				if (validationRequest[1] == 1) { // validate for blank values
													// also

					if (isValidString.equalsIgnoreCase("")) { // validation
																// fails
						rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field cannot be blank"));
					} else { // validation passes

						// value cannot be blank..therefore format validation
						// compulsory
						if (!dateFormatValidation(isValidString)) {
							rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "Wrong Date Format"));
						}
					}
				} else { // no validation for blank values..so values can be
							// blank

					if (!isValidString.equalsIgnoreCase("")) { // if values are
																// not blank
																// then format
																// validation
																// compulsory
						// validate format
						if (!dateFormatValidation(isValidString)) {
							rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "Wrong Date Format"));
						}
					}
				}
			}
		}

		else if (validationRequest[0] == 4) {
			// for email type
			isValidString = stringFetchingValidation(rowData, colNumber);
			if (isValidString == null) {
				rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field should have characters"));
			}

			else { // validation passes

				if (validationRequest[1] == 1) { // validate for blank values
													// also

					if (isValidString.equalsIgnoreCase("")) { // validation
																// fails
						rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field cannot be blank"));
					} else { // validation passes

						// value cannot be blank..therefore format validation
						// compulsory
						if (!emailFormatValidation(isValidString)) {
							rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "Wrong Email Format"));
						}
					}
				} else { // no validation for blank values..so values can be
							// blank

					if (!isValidString.equalsIgnoreCase("")) { // if values are
																// not blank
																// then format
																// validation
																// compulsory
						// validate format
						if (!emailFormatValidation(isValidString)) {
							rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "Wrong Email Format"));
						}
					}
				}
			}
		}

		else if (validationRequest[0] == 5) {
			// for phone type
			isValidString = numericFetchingValidation(rowData, colNumber);
			if (isValidString == null) {
				rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field should only have integers and no characters"));
			} else {
				isValidDouble = Double.parseDouble(isValidString);
				if (validationRequest[1] == 1) {
					isValidInteger = (int) isValidDouble;
					if (isValidInteger == 0) {
						rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field cannot be blank"));
					}
				} else if (isValidDouble % 1 != 0.0) {
					rowErrorList.add(getExcelErrorDetails(rowNumber, colNumber, "This field should only have integers and no fractional values"));
				}
			}
		}

		else if (validationRequest[0] == 6) {
			// for bigint type
		}

		return rowErrorList;
	}

	public HashMap<Object, Object> getUserListBasedOnRoles(List<UserRolesMapping> allUserList, Long domainId) throws Exception {
		List<UserDetail> foremanList = new ArrayList<UserDetail>();
		List<UserDetail> managerList = new ArrayList<UserDetail>();
		List<UserDetail> supervisorList = new ArrayList<UserDetail>();
		List<UserDetail> executiveList = new ArrayList<UserDetail>();
		List<UserDetail> purchasingAgentList = new ArrayList<UserDetail>();
		List<UserDetail> warehouseManagerList = new ArrayList<UserDetail>();
		List<UserDetail> superIntendentList = new ArrayList<UserDetail>();
		List<UserDetail> accountantList = new ArrayList<UserDetail>();
		HashMap<Object, Object> userMapBasedOnRole = new HashMap<Object, Object>();
		try {
			for (UserRolesMapping userObj : allUserList) {
				UserDetail userDetail = userObj.getUserRolesDetail();
				if (userDetail.getDomainDetail().getDomainId() == domainId) {
					if (userObj.getUserRolesId().getRoleId() == ConstantUtil.EXECUTIVE_ROLE_ID) {
						executiveList.add(userDetail);
					}
					if (userObj.getUserRolesId().getRoleId() == ConstantUtil.SUPERVISOR_ROLE_ID) {
						supervisorList.add(userDetail);
					}
					if (userObj.getUserRolesId().getRoleId() == ConstantUtil.MANAGER_ROLE_ID) {
						managerList.add(userDetail);
					}
					if (userObj.getUserRolesId().getRoleId() == ConstantUtil.FOREMAN_ROLE_ID) {
						foremanList.add(userDetail);
					}
					if (userObj.getUserRolesId().getRoleId() == ConstantUtil.PURCHASING_AGENT_ROLE_ID) {
						purchasingAgentList.add(userDetail);
					}
					if (userObj.getUserRolesId().getRoleId() == ConstantUtil.WAREHOUSE_ROLE_ID) {
						warehouseManagerList.add(userDetail);
					}
					if (userObj.getUserRolesId().getRoleId() == ConstantUtil.SUPERINTENDENT_ROLE_ID) {
						superIntendentList.add(userDetail);
					}
					if (userObj.getUserRolesId().getRoleId() == ConstantUtil.ACCOUNTANT_ROLE_ID) {
						accountantList.add(userDetail);
					}
				}
			}
			userMapBasedOnRole.put("executiveList", executiveList);
			userMapBasedOnRole.put("supervisorList", supervisorList);
			userMapBasedOnRole.put("managerList", managerList);
			userMapBasedOnRole.put("foremanList", foremanList);
			userMapBasedOnRole.put("purchasingAgentList", purchasingAgentList);
			userMapBasedOnRole.put("warehouseManagerList", warehouseManagerList);
			userMapBasedOnRole.put("superIntendentList", superIntendentList);
			userMapBasedOnRole.put("accountantList", accountantList);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return userMapBasedOnRole;
	}

	public HashMap<Object, Object> getUserRolesInJobs(ArrayList<JobDetail> allUserRoleDetails, ArrayList<JobDetail> allJobDetailsForAdmin, UserDetail userDetail, Object allusageList) throws Exception {

		ArrayList<JobDetail> jobsAsForeman = new ArrayList<JobDetail>();
		ArrayList<JobDetail> jobsAsManager = new ArrayList<JobDetail>();
		ArrayList<JobDetail> jobsAsSupervisor = new ArrayList<JobDetail>();
		ArrayList<JobDetail> jobsAsPurchasingAgent = new ArrayList<JobDetail>();
		ArrayList<JobDetail> jobsAsExecutive = new ArrayList<JobDetail>();
		HashMap<Object, Object> userRolesMap = new HashMap<Object, Object>();
		try {
			for (JobDetail jobObj : allUserRoleDetails) {
				if (jobObj.getForeman() != null && jobObj.getForeman().getUserId() == userDetail.getUserId()) {
					jobsAsForeman.add(jobObj);
				}
				if (jobObj.getSupervisor() != null && jobObj.getSupervisor().getUserId() == userDetail.getUserId()) {
					jobsAsSupervisor.add(jobObj);
				}
				if (jobObj.getManager() != null && jobObj.getManager().getUserId() == userDetail.getUserId()) {
					jobsAsManager.add(jobObj);
				}
				if (jobObj.getPurchasingAgent() != null && jobObj.getPurchasingAgent().getUserId() == userDetail.getUserId()) {
					jobsAsPurchasingAgent.add(jobObj);
				}
				if (jobObj.getExecutive() != null && jobObj.getExecutive().getUserId() == userDetail.getUserId()) {
					jobsAsExecutive.add(jobObj);
				}
			}
			userRolesMap.put("jobDetailsForAdmin", allJobDetailsForAdmin);
			userRolesMap.put("jobsAsForeman", jobsAsForeman);
			userRolesMap.put("jobsAsManager", jobsAsManager);
			userRolesMap.put("jobsAsSupervisor", jobsAsSupervisor);
			userRolesMap.put("jobsAsPurchasingAgent", jobsAsPurchasingAgent);
			userRolesMap.put("jobsAsExecutive", jobsAsExecutive);
			userRolesMap.put("allusageList", allusageList);
			userRolesMap.put("logedUserDetails", userDetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return userRolesMap;
	}

	public String getIssuetrackerUrlForReportIssue() throws Exception {

		String url = null;
		try {
			PropertyFileReader propertyFileInstance = PropertyFileReader.getInstance();
			String hostName = propertyFileInstance.getStringProperty("hostName");
			String portNum = propertyFileInstance.getStringProperty("portNumber");
			String protocol = propertyFileInstance.getStringProperty("protocol");
			String issueTrackerDeployementName = propertyFileInstance.getStringProperty("ecosystemIssueTrackerDeployementName");
			url = protocol + "://" + hostName + ":" + portNum + "/" + issueTrackerDeployementName + "/excludeIntercepterRedirectedFromApps";

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return url;

	}

	public HashMap<Object, Object> handleException(HashMap<Object, Object> resultMap, Exception e) {
		resultMap.put("ajaxResult", "failure");
		resultMap.put("reason", e.getMessage());
		sendExceptionEmail(e);
		return resultMap;
	}

	public int checkForNullValueFromHibernate(Object maxSerialNo) {
		if (maxSerialNo == null)
			return 0;
		else
			return (Integer) maxSerialNo;
	}

	public DomainDetail getMultipartFileAsBlob(DomainDetail domainDetail, MultipartFile domainLogo) {

		if (!domainLogo.isEmpty()) {
			try {
				domainDetail.setDomainLogo(domainLogo.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return domainDetail;
	}

	// for creating email json
	public String setEmailLogInEcosystem(String toText, String fromText, String ccText, String bccText, String subjectText, String emailText, Boolean logRead, Boolean isException, String attachment,
			UserDetail loggedUser) {
		Map<Object, Object> logMap = new HashMap<Object, Object>();
		logMap.put("emailTo", toText);
		logMap.put("emailFrom", fromText);
		logMap.put("emailCc", ccText);
		logMap.put("emailBcc", bccText);
		logMap.put("emailSubject", subjectText);
		logMap.put("emailContent", emailText);
		logMap.put("logRead", logRead);
		logMap.put("isException", isException);
		logMap.put("attachment", attachment);
		logMap.put("userDetails", loggedUser);
		return getJsonResult(logMap);
	}

	public Date getOlderDate(Date d1, Date d2) {
		if (d1 == null && d2 == null) {
			return null;
		} else if (d1 == null) {
			return d2;
		} else if (d2 == null) {
			return d1;
		}
		if (d1.compareTo(d2) == 0)
			return d1;
		else if (d1.compareTo(d2) > 0)
			return d1;
		else
			return d2;
	}

	public Date getOlderDate(Date d1, Date d2, Date d3) {
		if (d1 == null && d2 == null && d3 == null) {
			return null;
		} else if (d1 == null && d2 == null) {
			return d3;
		} else if (d2 == null && d3 == null) {
			return d1;
		} else if (d1 == null && d3 == null) {
			return d2;
		} else if (d1 == null) {
			return getOlderDate(d2, d3);
		} else if (d2 == null) {
			return getOlderDate(d1, d3);
		} else if (d3 == null) {
			return getOlderDate(d1, d2);
		}

		if (d1.compareTo(d2) > 0 && d1.compareTo(d3) > 0)
			return d1;
		else if (d2.compareTo(d1) > 0 && d2.compareTo(d3) > 0)
			return d2;
		else
			return d3;
	}
}
