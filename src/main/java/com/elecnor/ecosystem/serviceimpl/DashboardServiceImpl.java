package com.elecnor.ecosystem.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.DomainApplicationPlanMapping;
import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.UserRoles;
import com.elecnor.ecosystem.dao.DomainApplicationPlanMappingDAO;
import com.elecnor.ecosystem.dao.JobsDetailDAO;
import com.elecnor.ecosystem.service.DashboardService;
import com.elecnor.ecosystem.service.UserRolesMappingService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.Utility;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	JobsDetailDAO jobsDetailDAO;

	@Autowired
	DomainApplicationPlanMappingDAO domainApplicationPlanMappingDAO;
	@Autowired
	UserRolesMappingService userRolesMappingService;

	@SuppressWarnings("unchecked")
	public HashMap<Object, Object> getUserJobDetails(UserDetail userDetail, Long domainId, Boolean isBelcoUser,
			Long userRole, HttpSession session) throws Exception {
		Utility util = new Utility();
		HashMap<Object, Object> userRolesMap = new HashMap<Object, Object>();
		ArrayList<JobDetail> allJobDetailsForAdmin = new ArrayList<JobDetail>();
		try {
			ArrayList<JobDetail> allUserRoleDetails = jobsDetailDAO.getJobDetailsInvolvingUser(userDetail.getUserId(),
					domainId);
			if (userRole == ConstantUtil.ADMIN_ROLE_ID) {
				allJobDetailsForAdmin = jobsDetailDAO.getAllJobDetails(domainId);
			}
			userRolesMap = util.getUserRolesInJobs(allUserRoleDetails, allJobDetailsForAdmin, userDetail,
					(isBelcoUser ? ((ArrayList<ApplicationDirectory>) session.getAttribute("plans"))
							: (ArrayList<DomainApplicationPlanMapping>) session.getAttribute("plans")));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return userRolesMap;

	}

	@Override
	public UserRoles getUserRoleBeanByRoleName(String userRoleName) throws Exception {
		UserRoles userRoleBean = null;
		try {
			ArrayList<UserRoles> userRolesList = userRolesMappingService.getAllUserRolesToInitializeInStaticList();
			for (UserRoles userRole : userRolesList) {
				if (userRole.getRoleName().equalsIgnoreCase(userRoleName)) {
					userRoleBean = userRole;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return userRoleBean;
	}
}
