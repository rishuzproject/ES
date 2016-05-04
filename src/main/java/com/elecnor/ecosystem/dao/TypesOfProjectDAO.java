package com.elecnor.ecosystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.elecnor.ecosystem.bean.ProjectType;

public interface TypesOfProjectDAO {
	public ArrayList<ProjectType> getAllProjectTypes(long signUpDomainId) throws Exception;
    public String addProjectTypeRecord(Object projType) throws Exception;
//    public String updateProjectTypeRecord(long projTypeId,String projTypeName);
    public String deleteProjectTypeRecord(long projTypeId) throws Exception;
   public ProjectType getProjectById(long projTypeId) throws Exception;
   public String updateProjectTypeRecord(ProjectType projTypeBean) throws Exception;
   
   public List<ProjectType> getProjectTypeListByDomainId(Long domainId) throws Exception;


}
