package com.elecnor.ecosystem.serviceimpl;



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.ProjectType;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.TypesOfProjectDAO;
import com.elecnor.ecosystem.service.TypeOfProjectsService;

@Service
public class TypeOfProjectServiceImpl implements TypeOfProjectsService {
	@Autowired
	TypesOfProjectDAO typeofProjectDAO;

	@Override
	public String addProjectType(ProjectType projTypeBean, HttpSession session) {
		// TODO Auto-generated method stub
		String ex = null;
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetails.getDomainDetail();
			projTypeBean.setDomainDetail(domainDetail);
			projTypeBean.setStatus("ACTIVE");
			if (projTypeBean.getProjectTypeId() == null) {
				projTypeBean.setSubmittedBy(userDetails);
				projTypeBean.setSubmittedDate(new Date());
				return typeofProjectDAO.addProjectTypeRecord(projTypeBean);
			} else {
				projTypeBean.setUpdatedBy(userDetails);
				projTypeBean.setUpdatedDate(new Date());
				return typeofProjectDAO.updateProjectTypeRecord(projTypeBean);
			}
		} catch (Exception e) {
			ex = e.getMessage();
			return ex;
		}
	}

	@Override
	public List<ProjectType> getProjectTypeListByDomainId(Long domainId)
			throws Exception {
		// TODO Auto-generated method stub
		return typeofProjectDAO.getProjectTypeListByDomainId(domainId);
	}
	
	
	

	
	

	
}
