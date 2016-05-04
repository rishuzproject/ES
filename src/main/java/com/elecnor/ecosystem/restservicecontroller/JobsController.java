package com.elecnor.ecosystem.restservicecontroller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.dao.ContractorDirectoryDAO;
import com.elecnor.ecosystem.dao.CustomerDirectoryDAO;
import com.elecnor.ecosystem.dao.DepartmentsDetailDAO;
import com.elecnor.ecosystem.dao.JobsDetailDAO;
import com.elecnor.ecosystem.dao.TypesOfProjectDAO;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.dao.VendorDirectoryDAO;
import com.elecnor.ecosystem.service.JobsDetailService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class JobsController {
	
	@Autowired
	JobsDetailService jobsService;
	@Autowired
	JobsDetailDAO jobsDao;
	@Autowired
	UserDetailDAO userDao;
	@Autowired
	VendorDirectoryDAO  vendorDirectoryDAO;
	@Autowired
	TypesOfProjectDAO typesOfProjectDAO;
	@Autowired
	CustomerDirectoryDAO customerDirectoryDAO;
	@Autowired
	ContractorDirectoryDAO  contractorDirectoryDAO ; 
	@Autowired
	DepartmentsDetailDAO departmentDetailsDAO;
	@Autowired
	JobsDetailDAO jobsDetailDAO;

	/**
	 * Harsh Verma
	 * Method for get all jobs using REST Service
	 * @param domainID
	 * @return
	 */
	@RequestMapping(value="/jobs")
	@ResponseBody
	public String fetchAllJobs(@ModelAttribute("domainID") String domainID){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Long domainId = Long.valueOf(domainID);
			ArrayList<JobDetail> allJobsList = jobsDao.getAllJobDetails(domainId);

//			ArrayList<VendorDirectory> allVendorsList= vendorDirectoryDAO.getAllVendorsForJobs();
//			ArrayList<ProjectType> allProjectTypes=typesOfProjectDAO.getAllProjectTypes(domainId);
//			ArrayList<DepartmentType> allDepartmentTypes=departmentDetailsDAO.getAllDepartments(domainId);
//			ArrayList<CustomerDirectory> allCustomersList=customerDirectoryDAO.getAllCustomersList(domainId);
//			ArrayList<ContractorDirectory> allContractorsList=contractorDirectoryDAO.getAllContractorList(domainId);

//			ArrayList<UserDetail> allUserList = userDao.getAllUserDetails(domainId);

			map.put("allJobsList", allJobsList);
//			map.put("userList", allUserList);
//			map.put("allVendorsList", allVendorsList);
//			map.put("allProjectTypes", allProjectTypes);
//			map.put("allDepartments", allDepartmentTypes);
//			map.put("allCustomersList", allCustomersList);
//			map.put("allContractorsList", allContractorsList);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "failure");
			map.put("reason", e.getMessage());
		}
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("MM-dd-yyyy").create();
		String json = gson.toJson(map);
		return json;
	}
	
}
