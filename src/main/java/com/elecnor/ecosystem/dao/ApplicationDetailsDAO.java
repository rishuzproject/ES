package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.ApplicationDirectory;

public interface ApplicationDetailsDAO {
public ArrayList<ApplicationDirectory> getApplicationDetails();
public String addOrUpdateApplicationDetail(ApplicationDirectory applicationDirectoryBean);
public String deleteApplicationDetailsRecord(long appDetailId);
public ApplicationDirectory getApplicationDetailsByApplicationId(int applicationId)throws Exception;
public ApplicationDirectory getApplicationDetailsByApplicationName(String applicationName)throws Exception;
}
