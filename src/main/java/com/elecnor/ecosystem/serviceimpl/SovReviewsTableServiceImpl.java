package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.SovReviewsTable;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.SovReviewsTableDAO;
import com.elecnor.ecosystem.service.SovReviewsTableService;

@Service
public class SovReviewsTableServiceImpl implements SovReviewsTableService {
	
	@Autowired
	SovReviewsTableDAO sovReviewsTableDAO;

	@Override
	@Transactional
	public String addNewSOVItemReview(SovReviewsTable sovReviewsTable, UserDetail userDetail) throws Exception{
		
		String result = "";
		if(sovReviewsTable.getSovReviewId() == null){		//create new review
			sovReviewsTable.setSubmittedBy(userDetail);
			sovReviewsTable.setSubmittedDate(new Date());
		}
		else{
			sovReviewsTable.setUpdatedBy(userDetail);
			sovReviewsTable.setUpdatedDate(new Date());
		}
		try {
			result = sovReviewsTableDAO.addUpdateSOVItemReview(sovReviewsTable);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
