package com.elecnor.ecosystem.helper;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.elecnor.ecosystem.bean.CouponDetails;

public class CouponDetailsHelper {

	public long setCouponIdToDelete(HttpServletRequest request) throws Exception {
		
		long couponId = 0L;
		try {
			if (request.getParameter("couponIdToDel") != null && request.getParameter("couponIdToDel") != "") {
				couponId = Long.parseLong(request.getParameter("couponIdToDel").trim());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		}
		return couponId;
	}

	public int checkIfActivityNumberExists(ArrayList<CouponDetails> couponList) throws Exception{
		try {
			if (!couponList.isEmpty()) {
				return 1;
			}
			else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
