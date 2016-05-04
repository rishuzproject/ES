package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elecnor.ecosystem.dao.BulkUploadDAO;


@Repository
public class BulkUploadDAOImpl implements BulkUploadDAO{

	@Autowired
	private SessionFactory sessionFactory;
	@SuppressWarnings("rawtypes")
	@Override
	public String saveBulkUpdate(ArrayList list) {
		
		Session session = sessionFactory.openSession();
		String result = "";
       System.out.println(list.size());
		Transaction tx = session.getTransaction();
		tx.begin();
		int loopCounter = 0;		
		try {
			for (Object temp : list) {
				
				session.save(temp);
				if (loopCounter % 20 == 0) {
					session.flush();
					session.clear();
				}
				loopCounter++;
		}	
			
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
			result = e.getCause().getMessage();
		}
		finally {
			session.close();
		}
		return result;
	}
}
