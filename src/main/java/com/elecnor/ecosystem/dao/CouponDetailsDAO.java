package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.CouponDetails;
import com.elecnor.ecosystem.bean.UserDetail;

public interface CouponDetailsDAO {

	ArrayList<CouponDetails> getAllCouponDetails() throws Exception;

	String setCouponDelete(long couponId, UserDetail userDetails) throws Exception;

	String setSaveCouponDetails(CouponDetails couponDetails)throws Exception;

	String setUpdateCouponDetails(CouponDetails couponDetails)throws Exception;

	int checkCouponCodeisExist(long couponId, String couponCode) throws Exception;

}
