/**
 * 
 */
package com.elecnor.ecosystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.NotificationCenterBean;
import com.elecnor.ecosystem.dao.NotificationCenterDAO;
import com.elecnor.ecosystem.service.NotificationCenterService;

/**
 * @author Vaibhav
 *
 */
@Service
public class NotificationCenterServiceImpl implements NotificationCenterService{

	@Autowired
	NotificationCenterDAO notificationCenterDao;
	
	@Override
	public void saveNotification(NotificationCenterBean notificationBean) throws Exception {
		// TODO Auto-generated method stub
		//Setting isEmailRead as False
		notificationBean.setIsEmailRead(false);
		
		notificationCenterDao.saveNotification(notificationBean);
	}

	@Override
	public Integer markNotificationAsRead(Long emailNotificationId) throws Exception {
		// TODO Auto-generated method stub
		return notificationCenterDao.markNotificationAsRead(emailNotificationId);
	}

	@Override
	public List<NotificationCenterBean> getNotificationListForUser(
			String userEmailId, Long domainId) throws Exception {
		// TODO Auto-generated method stub
		return notificationCenterDao.getNotificationListForUser(userEmailId, domainId);
	}

	@Override
	public NotificationCenterBean getNotificationByID(Long emailNotificationId)
			throws Exception {
		// TODO Auto-generated method stub
		return notificationCenterDao.getNotificationByID(emailNotificationId);
	}

	@Override
	public Integer getNotificationCount(String userEmailId, Long domainId)
			throws Exception {
		// TODO Auto-generated method stub
		return notificationCenterDao.getNotificationCount(userEmailId, domainId);
	}

}
