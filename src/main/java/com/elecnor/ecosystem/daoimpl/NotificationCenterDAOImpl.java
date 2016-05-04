package com.elecnor.ecosystem.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.NotificationCenterBean;
import com.elecnor.ecosystem.dao.NotificationCenterDAO;

/**
 * 
 * @author Vaibhav
 *
 */
@Repository
public class NotificationCenterDAOImpl implements NotificationCenterDAO
{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void saveNotification(NotificationCenterBean notificationBean)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();		
		session.save(notificationBean);

	}

	@Override
	@Transactional
	public Integer markNotificationAsRead(Long emailNotificationId)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE NotificationCenterBean SET IS_EMAIL_READ = :isEmailRead WHERE "
				+ " EMAIL_NOTIFICATION_ID = :emailNotificationId");
		query.setParameter("isEmailRead", true);
		query.setParameter("emailNotificationId", emailNotificationId);
		
		return query.executeUpdate();	
	}

	@Override
	@Transactional
	public List<NotificationCenterBean> getNotificationListForUser(
			String userEmailId, Long domainId) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from NotificationCenterBean where (EMAIL_TO LIKE :userEmailId OR "
				+ " EMAIL_CC LIKE :userEmailId OR EMAIL_BCC LIKE :userEmailId OR EMAIL_FROM LIKE :userEmailId) AND DOMAIN_ID = :domainId");
		query.setParameter("userEmailId", userEmailId);
		query.setParameter("domainId", domainId);
		
		return query.list();
	}

	@Override
	@Transactional
	public NotificationCenterBean getNotificationByID(Long emailNotificationId)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from NotificationCenterBean where EMAIL_NOTIFICATION_ID = :emailNotificationId");
		query.setParameter("emailNotificationId", emailNotificationId);
		
		List<NotificationCenterBean> notificationList =query.list();
		
		if(notificationList!=null && !notificationList.isEmpty())
			return notificationList.get(0);
		
		return new NotificationCenterBean();
	}

	@Override
	@Transactional
	public Integer getNotificationCount(String userEmailId, Long domainId)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT COUNT(*) from NotificationCenterBean where (EMAIL_TO LIKE :userEmailId OR "
				+ " EMAIL_CC LIKE :userEmailId OR EMAIL_BCC LIKE :userEmailId OR EMAIL_FROM LIKE :userEmailId) AND DOMAIN_ID = :domainId"
				+ " AND IS_EMAIL_READ = :isEmailRead");
		query.setParameter("userEmailId", userEmailId);
		query.setParameter("domainId", domainId);
		query.setParameter("isEmailRead", false);
		
		return ((Long) query.uniqueResult()).intValue();
	}

}
