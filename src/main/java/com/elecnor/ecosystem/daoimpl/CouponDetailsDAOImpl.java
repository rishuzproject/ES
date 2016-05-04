package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.CouponDetails;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.CouponDetailsDAO;
import com.elecnor.ecosystem.helper.CouponDetailsHelper;

@Repository
public class CouponDetailsDAOImpl implements CouponDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<CouponDetails> getAllCouponDetails() throws Exception {
		ArrayList<CouponDetails> allCouponDetails = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from CouponDetails where STATUS = 'ACTIVE'");
			allCouponDetails = (ArrayList<CouponDetails>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return allCouponDetails;
	}

	@Override
	@Transactional
	public String setCouponDelete(long couponId, UserDetail userDetails) throws Exception {
		String result = null;
		try {
			Session hybSes = sessionFactory.getCurrentSession();
			Query query = hybSes
					.createQuery("update CouponDetails set STATUS = :status,UPDATED_BY=:updBy, UPDATED_DATE=:updDate where COUPON_ID = :couponId");
			query.setParameter("status", "INACTIVE");
			query.setParameter("updBy", userDetails.getUserId());
			query.setParameter("updDate", new Date());
			query.setParameter("couponId", couponId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public String setSaveCouponDetails(CouponDetails couponDetails) throws Exception {
		String result = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			hibSes.merge(couponDetails);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public String setUpdateCouponDetails(CouponDetails couponDetails) throws Exception {
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update CouponDetails set COUPON_CODE=:couponCode,VALIDITY=:validity,"
									+ "DISCOUNT_AMOUNT=:discountAmount,ADDITIONAL_USER=:addUsers,STATUS=:status, ADDITIONAL_SPACE=:addSpace,IS_ONE_TIME_APPLICABLE=:isOneTime, "
									+ "UPDATED_BY=:updatedBy,UPDATED_DATE =:updatedDate where COUPON_ID=:couponId");
			query.setParameter("couponId", couponDetails.getCouponId());
			query.setParameter("couponCode", couponDetails.getCouponCode());
			query.setParameter("validity", couponDetails.getValidity());
			query.setParameter("discountAmount", couponDetails.getDiscountAmount());
			query.setParameter("addUsers", couponDetails.getAdditionalUser());
			query.setParameter("status", couponDetails.getStatus());
			query.setParameter("addSpace", couponDetails.getAdditionalSpace());
			query.setParameter("isOneTime", couponDetails.getIsOneTimeApplicable());
			query.setParameter("updatedBy", couponDetails.getUpdatedBy());
			query.setParameter("updatedDate", couponDetails.getUpdatedDate());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public int checkCouponCodeisExist(long couponId, String couponCode) throws Exception {
		int isExist = 0;
		ArrayList<CouponDetails> couponList = null;
		CouponDetailsHelper couponDetailsHelper = new CouponDetailsHelper();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session
					.createQuery("from CouponDetails where COUPON_CODE=:couponCode and STATUS!='INACTIVE' and COUPON_ID != :couponId");
			query.setParameter("couponCode", couponCode);
			query.setParameter("couponId", couponId);
			couponList = (ArrayList<CouponDetails>) query.list();
			isExist = couponDetailsHelper.checkIfActivityNumberExists(couponList);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return isExist;
	}

}
