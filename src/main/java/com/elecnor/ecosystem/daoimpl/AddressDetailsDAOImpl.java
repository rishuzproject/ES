package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.AddressDetails;
import com.elecnor.ecosystem.bean.States;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.AddressDetailsDAO;

@Repository
public class AddressDetailsDAOImpl implements AddressDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public String saveUpdateAddress(List<AddressDetails> addressDetails) throws Exception {
		String result = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			for (AddressDetails info : addressDetails) {
				hibSes.merge(info);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public String updateAddress(AddressDetails addressDetails) throws Exception {
		return null;
	}
	// @Override
	// @Transactional
	// public String setAddressUpdate(AddressDetails addressDetails) throws
	// Exception {
	// String result = null;
	// try {
	// Query query = sessionFactory.getCurrentSession().createQuery(
	// "update AddressDetails set "
	// +
	// "DOMAIN_ID =:domainId,ADDRESS
	// =:address,LOCAL_ADDRESS_TYPE=:localAddressType,"
	// +
	// "UPDATED_BY =:updatedBy,UPDATED_DATE
	// =:updatedDate,ADDRESS_OF_TYPE=:addressOfType,"
	// + "ADDRESS_UNIQUE_ID =:addressUniqueId where ADDRESS_ID = :addressId");
	// query.setParameter("addressId", addressDetails.getAddressId());
	// query.setParameter("addressUniqueId",
	// addressDetails.getAddressUniqueId());
	// query.setParameter("domainId", addressDetails.getDomainDetail());
	// query.setParameter("address", addressDetails.getAddress());
	// query.setParameter("localAddressType",
	// addressDetails.getLocalAddressType());
	// query.setParameter("addressOfType", addressDetails.getAddressOfType());
	// query.setParameter("updatedBy", addressDetails.getUpdatedBy());
	// query.setParameter("updatedDate", addressDetails.getUpdatedDate());
	// query.executeUpdate();
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw e;
	// }
	// return result;
	// }

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<AddressDetails> getAddressDetails(Long moduleID,String moduleName) throws Exception {
		ArrayList<AddressDetails> allAddressDetails = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from AddressDetails where MODULE_ID=:moduleId and status = :status and MODULE_NAME=:moduleName");
			query.setParameter("status", "ACTIVE");
			query.setParameter("moduleId", moduleID);
			query.setParameter("moduleName", moduleName);
			allAddressDetails = (ArrayList<AddressDetails>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return allAddressDetails;
	}

	@Override
	@Transactional
	public String deleteAddress(Long addressId, UserDetail userDetails) throws Exception {
		String result = null;
		try {
			Session hybSes = sessionFactory.getCurrentSession();
			Query query = hybSes.createQuery("update AddressDetails set STATUS =:status,UPDATED_BY=:updBy, UPDATED_DATE=:updDate where MODULE_ID = :moduleId");
			query.setParameter("status", "INACTIVE");
			query.setParameter("updBy", userDetails.getUserId());
			query.setParameter("updDate", new Date());
			query.setParameter("moduleId", addressId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public String setDeleteAddressByUser(int moduleId, UserDetail userDetails) throws Exception {
		String result = null;
		try {
			Session hybSes = sessionFactory.getCurrentSession();

			Query query = hybSes.createQuery("update AddressDetails set STATUS = :status,UPDATED_BY=:updBy, UPDATED_DATE=:updDate where MODULE_ID =:moduleId");
			query.setParameter("status", "INACTIVE");
			query.setParameter("updBy", userDetails.getUserId());
			query.setParameter("updDate", new Date());
			query.setParameter("moduleId", moduleId);
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
	public List<States> getStates() {
		List<States> states = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from States");
			states = (List<States>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return states;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<String> getCities(String stateCode) {
		List<String> cities = null;
		try {
			Query query = sessionFactory.getCurrentSession().createSQLQuery("select city from CITIES where STATE_CODE =:stateCode");
			query.setParameter("stateCode", stateCode);
			cities = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cities;
	}
}
