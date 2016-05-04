package com.elecnor.ecosystem.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.elecnor.ecosystem.bean.ItemDB;

public interface ItemDBDAO {

	public String addItemDetail(ItemDB itemForm) throws Exception;

	public ArrayList<ItemDB> getAllItems(Long domainId, Integer limitStart, Integer limitEnd) throws Exception;

	public String deleteItem(int itemId) throws Exception;

	public String updateItemDetail(ItemDB itemForm) throws Exception;

	// These Services will be Called from PD application to fetch the Data from ItemDB , By Vaibhav

	public List<String> getCategory1List() throws Exception;

	public List<String> getCategory2List(Map<String, String> selectedCategoryMap) throws Exception;

	public List<String> getCategory3List(Map<String, String> selectedCategoryMap) throws Exception;

	public List<ItemDB> getItemListByCategorySelection(Map<String,String> requestMap) throws Exception;

	public List<ItemDB> getDetailedItemListByCategorySelection(Map<String,String> requestMap) throws Exception;

	public List<ItemDB> getItemByItemDescription(Map<String,String> requestMap) throws Exception;

	public List<ItemDB> getDetailedItemByItemDescription(Map<String,String> requestMap) throws Exception;

	public List<ItemDB> getAllItems() throws Exception;

	public ArrayList<ItemDB> getItemDetailsByItemId(Long domainId, long itemId)throws Exception;

	// End
}
