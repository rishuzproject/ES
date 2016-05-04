package com.elecnor.ecosystem.service;

import com.elecnor.ecosystem.bean.SovReviewsTable;
import com.elecnor.ecosystem.bean.UserDetail;

public interface SovReviewsTableService {

	String addNewSOVItemReview(SovReviewsTable sovReviewsTable, UserDetail userDetail) throws Exception;

}
