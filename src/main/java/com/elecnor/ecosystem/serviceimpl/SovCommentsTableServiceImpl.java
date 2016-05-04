package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.SovCommentsTable;
import com.elecnor.ecosystem.bean.SovDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.SovCommentsTableDAO;
import com.elecnor.ecosystem.service.SovCommentsTableService;

@Service
public class SovCommentsTableServiceImpl implements SovCommentsTableService{

	@Autowired
	SovCommentsTableDAO sovCommentsTableDAO;
	public String addSOVComment(SovCommentsTable sovComment, SovDirectory sovDirectory, UserDetail userDetail) throws Exception {
		
		String result = "";
		try {
			sovComment.setSovDirectory(sovDirectory);
			sovComment.setSubmittedBy(userDetail);
			sovComment.setSubmittedDate(new Date());
			result = sovCommentsTableDAO.addSOVComment(sovComment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
