package com.elecnor.ecosystem.service;

import com.elecnor.ecosystem.bean.CouponDetails;
import com.elecnor.ecosystem.bean.UserDetail;

public interface CouponDetailsService {

	String setSaveOrUpdateCoupon(CouponDetails couponDetails,
			UserDetail userDetails) throws Exception;

}
