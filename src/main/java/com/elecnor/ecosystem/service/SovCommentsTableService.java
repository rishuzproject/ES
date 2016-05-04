package com.elecnor.ecosystem.service;

import com.elecnor.ecosystem.bean.SovCommentsTable;
import com.elecnor.ecosystem.bean.SovDirectory;
import com.elecnor.ecosystem.bean.UserDetail;

public interface SovCommentsTableService {

	public String addSOVComment(SovCommentsTable sovComment, SovDirectory sovDirectory, UserDetail userDetail) throws Exception;

}
