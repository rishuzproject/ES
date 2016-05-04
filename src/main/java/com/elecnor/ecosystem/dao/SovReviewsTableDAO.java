package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.SovReviewsTable;

public interface SovReviewsTableDAO {

	public String addUpdateSOVItemReview(SovReviewsTable sovReviewsTable) throws Exception;

	public ArrayList<SovReviewsTable> getSOVReviewsDetails(long itemNo) throws Exception;

}
