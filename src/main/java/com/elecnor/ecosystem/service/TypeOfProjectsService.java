package com.elecnor.ecosystem.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.elecnor.ecosystem.bean.ProjectType;

public interface TypeOfProjectsService {
   public String addProjectType(ProjectType projTypeBean, HttpSession session) ;  //UserDetails must be include
   
   public List<ProjectType> getProjectTypeListByDomainId(Long domainId) throws Exception;

}
