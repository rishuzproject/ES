/**
 * 
 */
package com.elecnor.ecosystem.dao;

import java.util.List;

import com.elecnor.ecosystem.bean.NotificationCenterBean;

/**
 * @author Vaibhav
 *
 */
public interface NotificationCenterDAO {

	public void saveNotification(NotificationCenterBean notificationBean) throws Exception;
	
	public Integer markNotificationAsRead(Long emailNotificationId) throws Exception;
	
	public List<NotificationCenterBean> getNotificationListForUser(String userEmailId, Long domainId) throws Exception;
	
	public NotificationCenterBean getNotificationByID(Long emailNotificationId) throws Exception;
	
	public Integer getNotificationCount(String userEmailId, Long domainId) throws Exception;
}
