package com.elecnor.ecosystem.service;

import java.io.File;

import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.bean.UserDetail;

public interface ExcelSheetService {

	String updateBudgetCosts(File file, JobDetail selectedProjectBean, UserDetail loginUserProfileBean, RfcLog log);

}
