package com.elecnor.ecosystem.restservicecontroller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.util.PropertyFileReader;
import com.elecnor.ecosystem.util.Utility;
import com.google.gson.Gson;

@RestController
public class ReportToIssueTrackerController {

	@Value("${application.name}")
	String applicationName;

	@Value("${application.id}")
	Integer applicationId;
	
	@Value("${issueTracker.url}")
	String issueTrackerURL;

	MultiValueMap<String, Object> uploadForm = new LinkedMultiValueMap<String, Object>();

	@RequestMapping(value = "/RedirectedFromDownStreamApp/getApplicationDetailsFromIssueTracker")
	public String getApplicationDetails(HttpServletRequest request) {

		Utility util = new Utility();
		HashMap<Object, Object> applicationDetails = new HashMap<Object, Object>();
		applicationDetails.put(applicationId, applicationName);
		return util.getJsonResult(applicationDetails);
	}

	@RequestMapping(value = "/getAllIssueTypesFromIssueTracker", method = RequestMethod.GET)
	public String getAllIssueTypesFromIssueTracker() {

		Utility util = new Utility();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> issueTypesList = null;
		try {
			issueTypesList = restTemplate
					.getForEntity(
							issueTrackerURL+"RedirectedFromDownStreamApp/getAllIssueTypesFromIssueTracker",
							String.class);
		} catch (Exception e) {

			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			util.handleException(resultMap, e);
			e.printStackTrace();
			return null;
		}
		return issueTypesList.getBody();
	}

	/*@RequestMapping(value = "/getAllStatusFromIssueTracker", method = RequestMethod.GET)
	public String getAllStatusFromIssueTracker() {

		Utility util = new Utility();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> statusList = null;
		try {
			statusList = restTemplate
					.getForEntity(
							issueTrackerUrl+"RedirectedFromDownStreamApp/getAllStatusFromIssueTracker",
							String.class);
		} catch (Exception e) {

			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			util.handleException(resultMap, e);
			e.printStackTrace();
		}
		return statusList.getBody();
	}
*/
	@RequestMapping(value = "/getAllSeverityFromIssueTracker", method = RequestMethod.GET)
	public String getAllSeverityFromIssueTracker() {
		Utility util = new Utility();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> severityList = null;
		try {
			severityList = restTemplate
					.getForEntity(
							issueTrackerURL+"RedirectedFromDownStreamApp/getAllSeveritiesFromIssueTracker",
							String.class);
		} catch (Exception e) {

			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			util.handleException(resultMap, e);
			e.printStackTrace();
			return null;
		}
		return severityList.getBody();
	}

	@RequestMapping(value = "/getAllModulesFromIssueTracker", method = RequestMethod.POST)
	public String getAllModulesFromIssueTracker(HttpServletRequest request) {

		Utility util = new Utility();
		RestTemplate restTemplate = new RestTemplate();
		String json = null;
		try {
			json = restTemplate
					.getForObject(
							issueTrackerURL+"RedirectedFromDownStreamApp/getAllModulesFromIssueTracker?applicationId=1",
							String.class);
		} catch (Exception e) {

			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			util.handleException(resultMap, e);
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/getAllProjectsFromIssueTracker", method = RequestMethod.POST)
	public String getAllProjectsFromIssueTracker(HttpServletRequest request,HttpSession session) {

		Utility util = new Utility();
		RestTemplate restTemplate = new RestTemplate();
		String json = null;
		UserDetail userDetail=(UserDetail)session.getAttribute("selectedUser");
		Long domainId=userDetail.getDomainIdTransient();
		try {
			json = restTemplate
					.getForObject(
							issueTrackerURL+"RedirectedFromDownStreamApp/getAllProjectsFromIssueTracker?applicationId=1&domainId="+domainId,
							String.class);

		} catch (Exception e) {

			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			util.handleException(resultMap, e);
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/reportToIssueTracker", method = RequestMethod.POST)
	public String reportToIssueTracker(HttpServletRequest request, HttpSession session,
			@RequestParam("uploadFile") List<MultipartFile> attachedFile) {

		String status = null;
		try {
			UserDetail loggedInUser=(UserDetail)session.getAttribute("selectedUser");
			
			RestTemplate restTemplate = new RestTemplate();
			HashMap<String, Object> form = new HashMap<String, Object>();
			form.put("applicationId", request.getParameter("applicationId"));
			form.put("applicationModuleId",request.getParameter("applicationModuleId"));
			form.put("relatedProject", request.getParameter("relatedProject"));
			form.put("issueType", request.getParameter("issueTypeId"));
			form.put("summary", request.getParameter("summary"));
			form.put("description", request.getParameter("description"));
			form.put("statusId", request.getParameter("statusId"));
			form.put("severityId", request.getParameter("severityId"));
			form.put("reportedDate", request.getParameter("reportedDate"));
			form.put("dueDate", request.getParameter("dueDate"));
			form.put("reportedById",loggedInUser.getUserId().toString());
			form.put("reportedByName", loggedInUser.getFirstName());
			form.put("reportedByEmailId", loggedInUser.getEmailId());
			
			// Adding MultipartFiles in the MAP, Converting MultipartFile to ByteString and adding in a MAP with filename as key.
			HashMap<String, String> attachmentMap = new HashMap<String, String>();
			if(attachedFile!=null && !attachedFile.isEmpty()){
				Iterator<MultipartFile> iter = attachedFile.iterator();
				MultipartFile tempFile = null;
				while(iter.hasNext()){
					tempFile = iter.next();
					attachmentMap.put(tempFile.getOriginalFilename(), new String(tempFile.getBytes()));
				}
			}
			form.put("attchmentMap", attachmentMap);
			
			// Converting "form" MAP for JSON String and sending it to Ecosystem through rest API.
			Gson gson = new Gson();
			String requestStr = gson.toJson(form);

			/*status = restTemplate.postForObject(
							issueTrackerURL+"RedirectedFromDownStreamApp/saveRaisedTickets", form, String.class);*/
			
			status = restTemplate.postForObject(
					 issueTrackerURL+"RedirectedFromDownStreamApp/saveRaisedTickets", requestStr, String.class);
			
			// Commented by Vaibhav, No need for a separate call for attachment.
			/*if (attachedFile.size() > 1) {
				Iterator<MultipartFile> iter = attachedFile.iterator();
				while (iter.hasNext()) {
					MultipartFile temp = iter.next();
					String tempString = new String(temp.getBytes());
					uploadForm.add("Filename", temp.getOriginalFilename());
					uploadForm.add("uploadedFiles", tempString);
					String uploadedFileStatus = restTemplate
							.postForObject(
									issueTrackerURL+"RedirectedFromDownStreamApp/saveUploadedFiles",
									uploadForm, String.class);
					System.out.println(uploadedFileStatus);

				}
			}*/
			System.out.println(status);

		} catch (Exception e) {

			System.out.println("error while creating issue");
			e.printStackTrace();
			
		}
		return status;
	}
	
	@RequestMapping(value = "/redirectToIssueTrackerApp", method = RequestMethod.POST)
	public String redirectToIssueTrackerApp(HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Utility utilRef = new Utility();
		try {
			
			String issueTrackerUrl = issueTrackerURL
					+ "excludeIntercepterRedirectedFromApps";
			UserDetail logedInUserDetails = (UserDetail) session.getAttribute("selectedUser");
			responseMap.put("issueTrackerUrl", issueTrackerUrl);
			responseMap.put("emailId", logedInUserDetails.getEmailId());
			responseMap.put("password", logedInUserDetails.getPassword());
			responseMap.put("applicationId", applicationId);
			responseMap.put("ajaxResult", "success");
		} catch (Exception e) {
			responseMap.put("ajaxResult", "failure");
			responseMap.put("reason", e.getMessage());
			utilRef.sendExceptionEmail(e);
			e.printStackTrace();
		}
		return utilRef.getJsonResult(responseMap);
	}

}
