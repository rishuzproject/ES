package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.CouponDetails;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.CouponDetailsDAO;
import com.elecnor.ecosystem.service.CouponDetailsService;

@Service
public class CouponDetailsServiceImpl implements CouponDetailsService {
	
	@Autowired
	CouponDetailsDAO couponDetailsDAO;

	@Override
	public String setSaveOrUpdateCoupon(CouponDetails couponDetails,
			UserDetail userDetails) throws Exception {
		String result = null;
		long couponId =  0;
		if (couponDetails.getCouponId() != null) {
			couponId = couponDetails.getCouponId();
		}
		int isExist = couponDetailsDAO.checkCouponCodeisExist(couponId,
				couponDetails.getCouponCode());
		if (isExist != 0) {
			result = "couponCodeExist";
		} else {
		couponDetails.setStatus("ACTIVE");
			if (couponDetails.getCouponId() == null) {
				couponDetails.setSubmittedDate(new Date());
				couponDetails.setSubmittedBy(userDetails);
				result = couponDetailsDAO.setSaveCouponDetails(couponDetails);

			} else {
				couponDetails.setUpdatedDate(new Date());
				couponDetails.setUpdatedBy(userDetails);
				result = couponDetailsDAO.setUpdateCouponDetails(couponDetails);
			}
		}
		return result;
	}

}
