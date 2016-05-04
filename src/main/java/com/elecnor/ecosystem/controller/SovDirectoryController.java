package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.SovDirectory;
import com.elecnor.ecosystem.bean.SovTable;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.JobsDetailDAO;
import com.elecnor.ecosystem.dao.SovDirectoryDAO;
import com.elecnor.ecosystem.dao.SovTableDAO;
import com.elecnor.ecosystem.service.SovDirectoryService;
import com.elecnor.ecosystem.util.Utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class SovDirectoryController {

	@Autowired
	SovDirectoryService sovDirectoryService;

	@Autowired
	SovTableDAO sovTableDAO;

	@Autowired
	SovDirectoryDAO sovDirectoryDAO;

	@Autowired
	JobsDetailDAO jobDetailsDao;

	/*@RequestMapping("/sovDirectory")
	public String getSovDirectory() {
		return "sovDirectory";
	}

	@ModelAttribute(value = "/sovDirectoryBean")
	public SovDirectory getSovDirectoryBean() {
		System.out.println("Intitializing SOV Directory Bean");
		return new SovDirectory();
	}*/

	@RequestMapping(value = "/deleteSOV", method = RequestMethod.POST)
	public @ResponseBody String deleteSOV(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "sovId") String sovId) {
		Utility util = new Utility();

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			String result = sovDirectoryDAO.deleteSov(Long.parseLong(sovId));
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping("/redirectToItemView")
	public @ResponseBody String redirectToItemView(@RequestParam("sessionSovId") String sovId, HttpSession session,
			HttpServletRequest request) {
		Utility util = new Utility();
		session.setAttribute("sovId", Long.parseLong(sovId));
		return util.getJsonResult(session.getAttribute("userRole"));

		// return new ModelAndView("sovTable");
	}

	@RequestMapping(value = "/addUpdateSOV", method = RequestMethod.POST)
	public @ResponseBody String addNewSOVToProject(@ModelAttribute("sovDirectoryBean") SovDirectory sovDirectory,
			HttpSession session, HttpServletRequest request) {

		Utility util = new Utility();
		JobDetail selectedProject = (JobDetail) session.getAttribute("selectedprojectforSOV");
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
		try {
			String result = sovDirectoryService.addUpdateSOV(sovDirectory, userDetail, selectedProject);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping(value = "/totalsForCharts", method = RequestMethod.POST)
	public @ResponseBody String getTotalsForCharts() {

		List<Object> fullList = new ArrayList<Object>();
		try {

			ArrayList<SovTable> sovItems = sovTableDAO.getAllItemDetailsFromSOV();
			double totalQuoted = 0.0;
			double totalApproved = 0.0;
			for (SovTable item : sovItems) {
				if (item.getIsApprovedExternal()) {
					totalApproved += item.getTotalCost();
				}
				totalQuoted += (item.getTotalCost()+item.getScheduledValue());
			}
			Map<String, Object> dataMap2 = new HashMap<String, Object>();
			Map<String, Object> dataMap3 = new HashMap<String, Object>();

			dataMap2.put("planName", "Total Quoted");
			dataMap2.put("price", totalQuoted);
			fullList.add(dataMap2);
			dataMap3.put("planName", "Total Approved");
			dataMap3.put("price", totalApproved);
			fullList.add(dataMap3);
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

			String json = gson.toJson(fullList);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/totalForChartsForSelectedProject",method = RequestMethod.POST)
	public @ResponseBody String getTotalForChartsForSelectedProject(HttpSession session, HttpServletRequest request)
	{
		List<Object> fullList = new ArrayList<Object>();
		try{
			
		ArrayList<SovTable> sovItems=sovTableDAO.getSOVDetailsFromSOVTableForProject(Long.parseLong(request.getParameter("selectedProjectId")));
		double totalQuoted=0.0;
		double totalApproved=0.0;
		for(SovTable item:sovItems)
		{
			if(item.getIsApprovedExternal())
			{
				totalApproved+=item.getTotalCost();
			}
			totalQuoted += (item.getTotalCost()+item.getScheduledValue());
		}
		Map<String, Object> dataMap2 = new HashMap<String, Object>();
		Map<String, Object> dataMap3 = new HashMap<String, Object>();

		dataMap2.put("planName", "Total Quoted");
		dataMap2.put("price", totalQuoted);
		fullList.add(dataMap2);
		dataMap3.put("planName", "Total Approved");
		dataMap3.put("price", totalApproved);
		fullList.add(dataMap3);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		
		String json = gson.toJson(fullList);
		return json;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getAllSOVList", method = RequestMethod.POST)
	public @ResponseBody String getAllSOVList(@RequestParam(value = "projectIdTemp") String projectId,
			HttpSession session) {

		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Long projId = Long.parseLong(projectId);
		session.setAttribute("selectedprojectforSOV", jobDetailsDao.getJobById(projId));
		Utility util = new Utility();
		try {
			ArrayList<SovDirectory> allSOVList = sovDirectoryDAO.getSOVListFromDirectory(projId);
			map.put("ajaxResult", "success");
			map.put("allSOVList", allSOVList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}
}
