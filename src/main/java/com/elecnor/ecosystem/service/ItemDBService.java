package com.elecnor.ecosystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.ItemDB;
import com.elecnor.ecosystem.bean.UserDetail;

/**
 * @author Vaibhav
 * 
 */
public interface ItemDBService {
	
	public String addItemDetail(ItemDB itemForm, UserDetail userDetail, DomainDetail domainDetail) throws Exception;

	public HashMap<String, Object> uploadItemFile(MultipartFile fileUploaded,
			HttpSession session, int confirmItemDbUploadId) throws Exception;
	
	// These Services will be Called from PD application to fetch the Data from ItemDB , By Vaibhav

	public List<String> getCategory1List() throws Exception;
	
	public List<String> getCategory2List(Map<String, String> selectedCategoryMap) throws Exception;
	
	public List<String> getCategory3List(Map<String, String> selectedCategoryMap) throws Exception;
	
	public List<ItemDB> getItemListByCategorySelection(Map<String,String> requestMap) throws Exception;
	
	public List<ItemDB> getDetailedItemListByCategorySelection(Map<String,String> requestMap) throws Exception;
	
	public List<ItemDB> getItemByItemDescription(Map<String,String> requestMap) throws Exception;
	
	public List<ItemDB> getDetailedItemByItemDescription(Map<String,String> requestMap) throws Exception;

	public List<ItemDB> getAllItems() throws Exception;
	
	// End
	

}
