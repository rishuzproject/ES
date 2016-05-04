package com.elecnor.ecosystem.daoimpl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.dao.UpdateAccountInfoDAO;

@Repository
public class UpdateAccountInfoDAOImpl  implements UpdateAccountInfoDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	//method for updating password
	@Override
	@Transactional
	public String updatePassword(String email, String encPassword) throws Exception {
		String result = null;
			Query query = sessionFactory.getCurrentSession().createQuery("update UserDetail set PASSWORD =:password where EMAIL_ID =:emailId");
			query.setParameter("password", encPassword);
			query.setParameter("emailId", email);
			query.executeUpdate();
		return result;
		
	}

}
