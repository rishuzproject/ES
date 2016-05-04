package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.SovCommentsTable;
import com.elecnor.ecosystem.bean.SovDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.SovCommentsTableDAO;
import com.elecnor.ecosystem.service.SovCommentsTableService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class SovCommentsTableController {
	
	@Autowired
	SovCommentsTableService sovCommentsTableService;
	
	@Autowired
	SovCommentsTableDAO sovCommentsTableDAO;

	/*@RequestMapping("/sovCommentsTable")
	public String getSovComments() {
		return "sovCommentsTable";
	}

	@ModelAttribute(value = "/sovCommentsBean")
	public SovCommentsTable getSovCommentsBean() {
		System.out.println("Intitializing SOV Comments Bean");
		return new SovCommentsTable();
	}*/

	@RequestMapping("/addSOVComments")
	public @ResponseBody String addSOVComments(
			HttpSession session, HttpServletRequest request) {

		Utility util = new Utility();
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
		SovDirectory sovDirectory = (SovDirectory) session.getAttribute("selectedSOV");
		try {
			SovCommentsTable sovComment = new SovCommentsTable();
			sovComment.setSupervisorComments(request.getParameter("commentToSave"));
			String result = sovCommentsTableService.addSOVComment(sovComment, sovDirectory, userDetail);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
	
	@RequestMapping("/getSOVComments")
	public @ResponseBody String getSOVComments(HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		ArrayList<SovCommentsTable> allSOVComments = null;
		SovDirectory sovDirectory = (SovDirectory) session.getAttribute("selectedSOV");
		try {
			allSOVComments = sovCommentsTableDAO.getAllSOVComments(sovDirectory.getSovId());
			map.put("ajaxResult", "success");
			map.put("allSOVComments", allSOVComments);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}
}
