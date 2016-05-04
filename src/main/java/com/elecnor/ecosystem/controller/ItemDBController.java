package com.elecnor.ecosystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.ItemDB;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ItemDBDAO;
import com.elecnor.ecosystem.helper.ItemDetailHelper;
import com.elecnor.ecosystem.service.ItemDBService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class ItemDBController {

	@Autowired
	ItemDBService itemDetailService;
	
	@Autowired 
	ItemDBDAO itemDetailDAO;

	/*@RequestMapping("/itemDetail")
	public String getApplicationDetailPage() {
		return "itemDetail";
	}

	@ModelAttribute(value = "/itemFormData")
	public ItemDB registerItem() {
		return new ItemDB();
	}*/

	/**
	 * Method for add or update Item data
	 * 
	 * @param itemForm
	 * @param itemDbNumber
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addUpdateItemFormData", method = RequestMethod.POST)
	public @ResponseBody String addUpdateItemData(
			@ModelAttribute("itemFormData") ItemDB itemForm,
			@RequestParam(value = "itemDbNumber", defaultValue = "-1") Long itemDbNumber,
			HttpSession session, HttpServletRequest request) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetail = (UserDetail) session
					.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetail.getDomainDetail();
			itemForm.setItemId(itemDbNumber);
			String result = null;
			result = itemDetailService.addItemDetail(itemForm, userDetail,
					domainDetail);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for get all Items list
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getItemDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getAllItems(HttpSession session,HttpServletRequest request) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetail = (UserDetail) session
					.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetail.getDomainDetail();
			Integer limitStart = Integer.parseInt(request.getParameter("limitStart"));
			Integer limitEnd = Integer.parseInt(request.getParameter("limitEnd"));
			ArrayList<ItemDB> allItemList = itemDetailDAO.getAllItems(domainDetail.getDomainId(),limitStart,limitEnd);
			map.put("statusReturned", "200");
			map.put("allItemList", allItemList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}
	
	@RequestMapping(value = "/getItemDetailsByItemId", method = RequestMethod.POST)
	@ResponseBody
	public String getItemDetailsByItemId(HttpSession session,HttpServletRequest request) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetail = (UserDetail) session
					.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetail.getDomainDetail();
			long itemId = Long.parseLong(request.getParameter("itemId"));
			ArrayList<ItemDB> allItemList = itemDetailDAO.getItemDetailsByItemId(domainDetail.getDomainId(),itemId);
			map.put("statusReturned", "200");
			map.put("allItemList", allItemList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}

	/**
	 * Method for delete
	 * 
	 * @param request
	 * @return
	 */
 	@RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
	@ResponseBody
	public String deleteItemEntry(HttpServletRequest request) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		ItemDetailHelper itemDetailHelper = new ItemDetailHelper();
		int itemId = 0;
		try {
			itemId = itemDetailHelper.setItemIdToDelete(request);
			String result = itemDetailDAO.deleteItem(itemId);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	} 

	/**
	 * Method for save data using template
	 * 
	 * @param confirmItemDbUploadId
	 * @param fileUploaded
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/itemTemplateController", method = RequestMethod.POST)
	@ResponseBody
	public String saveStandardTemplateDetails(
			@RequestParam(value = "confirmItemDbUploadId", defaultValue = "-1") int confirmItemDbUploadId,
			@RequestParam(value = "itemDbTemplateFile") MultipartFile fileUploaded,
			HttpServletRequest request, HttpSession session) throws IOException {
		Utility util = new Utility();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = itemDetailService.uploadItemFile(fileUploaded, session,
					confirmItemDbUploadId);
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultWithoutExposeString(resultMap);
	}
	
	
	// Methods written by Vaibhav Dixit , All these methods will be called from PD application for Item Search.
	
	/**
	 * This method is to get the list of Category-1 .
	 * 
	 * @param request
	 * @return List of String in json format
	 */
	@RequestMapping(value = "/getCategory1List", method = RequestMethod.POST)
	public @ResponseBody List<String> getCategory1List(HttpServletRequest request ) throws Exception{
       List<String> category1List=null;
       try{
    	   category1List=itemDetailService.getCategory1List();
       }
       catch(Exception e){
    	   e.printStackTrace();
       }
		return category1List;
	}
	
	/**
	 * This method is to get the list of Category-2 based on Category-1 Selection .
	 * 
	 * @param requestMap
	 * @return List of String in json format
	 */
	@RequestMapping(value = "/getCategory2List", method = RequestMethod.POST)
	public @ResponseBody List<String> getCategory2List(HttpServletRequest request ,
			@RequestBody Map<String, String> requestMap) throws Exception {

		 List<String> category2List=null;
	       try{
	    	   category2List=itemDetailService.getCategory2List(requestMap);
	       }
	       catch(Exception e){
	    	   e.printStackTrace();
	       }
			return category2List;
		}
	
	
	/**
	 * This method is to get the list of Category-3 based on Category-1 & Category-2 Selection .
	 * 
	 * @param requestMap
	 * @return List of String in json format
	 */
	@RequestMapping(value = "/getCategory3List", method = RequestMethod.POST)
	public @ResponseBody List<String> getCategory3List(HttpServletRequest request ,
			@RequestBody Map<String, String> requestMap) throws Exception {
		 List<String> category3List=null;
	       try{
	    	   category3List=itemDetailService.getCategory3List(requestMap);
	       }
	       catch(Exception e){
	    	   e.printStackTrace();
	       }
			return category3List;
		}
	
	/**
	 * This method is to get the list of Items Based on Category Selection . This List will contain only few item attributes which is required to list on the page.
	 * Please see the service/dao Implementation for the List of attributes.  
	 * 
	 * @param request , requestMap
	 * @return List of String in json format
	 */
	@RequestMapping(value = "/getItemListByCategorySelection", method = RequestMethod.POST)
	public @ResponseBody HashMap<Object,Object> getItemListByCategorySelection(HttpServletRequest request ,
			@RequestBody Map<String,String> requestMap) throws Exception {
		HashMap<Object,Object> itemDetails=new HashMap<Object,Object>();
		try{
//			int iDisplay=Integer.parseInt(requestMap.get("iDisplayStart"));
			//System.out.println("the display start is  "+iDisplay);
			
//			itemDetails.put("iTotalRecords", itemDetailService.getAllItems().size() );
//			itemDetails.put("iTotalDisplayRecords", itemDetailService.getItemListByCategorySelection(requestMap).size() );
//			itemDetails.put("sEcho", iDisplay+101);
			itemDetails.put("aaData", itemDetailService.getItemListByCategorySelection(requestMap));
			
		}
		catch(Exception e){
			itemDetails.put("failure",e.getMessage());
			e.printStackTrace();
		}
		return itemDetails;
	}
	
	/**
	 * This method is to get the list of Items (All Attributes) Based on Category Selection . 
	 * 
	 * @param request , requestMap
	 * @return List of String in json format
	 */
	@RequestMapping(value = "/getDetailedItemListByCategorySelection", method = RequestMethod.POST)
	public @ResponseBody List<ItemDB> getDetailedItemListByCategorySelection(HttpServletRequest request ,
			@RequestBody Map<String,String> requestMap) throws Exception {

		return itemDetailService.getDetailedItemListByCategorySelection(requestMap);
	}
	
	/**
	 * This method is to get the item based on Item Description. This will contain only few item attributes which is required to list on the page.
	 * Please see the service/dao Implementation for the List of attributes. 
	 * 
	 * @param request , requestMap
	 * @return Item Bean in json format
	 */
	@RequestMapping(value = "/getItemByItemDescription", method = RequestMethod.POST)
	public @ResponseBody List<ItemDB> getItemByItemDescription( HttpServletRequest request , 
			@RequestBody Map<String,String> requestMap) throws Exception {

		 List<ItemDB> itemsList=null;
	       try{
	    	   itemsList=itemDetailService.getItemByItemDescription(requestMap);
	       }
	       catch(Exception e){
	    	   e.printStackTrace();
	       }
			return itemsList;
		
	}
	
	/**
	 * This method is to get the detailed item based on Item Description. 
	 * @param request , requestMap
	 * @return Item Bean in json format
	 */
	@RequestMapping(value = "/getDetailedItemByItemDescription", method = RequestMethod.POST)
	public @ResponseBody List<ItemDB> getDetailedItemByItemDescription( HttpServletRequest request , 
			@RequestBody Map<String,String> requestMap) throws Exception {

		
		return itemDetailService.getDetailedItemByItemDescription(requestMap);
	}
	
}
