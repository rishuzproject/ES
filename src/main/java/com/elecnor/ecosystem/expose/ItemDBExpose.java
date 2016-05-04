package com.elecnor.ecosystem.expose;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.ItemDB;
import com.elecnor.ecosystem.service.ItemDBService;

@Controller
public class ItemDBExpose {
	
	@Autowired
	ItemDBService itemDetailService;
	@RequestMapping(value = "/excludeInterceptor/getCategory1List", method = RequestMethod.POST)
	public @ResponseBody List<ItemDB> getItemListByCategorySelection(HttpServletRequest request ,
			@RequestBody Map<String,String> requestMap) throws Exception {

		return itemDetailService.getItemListByCategorySelection(requestMap);
	}
	
	@RequestMapping(value = "/excludeInterceptor/getCategory2List", method = RequestMethod.POST)
	public @ResponseBody List<String> getCategory2List(HttpServletRequest request ,
			@RequestBody Map<String, String> requestMap) throws Exception {

		return itemDetailService.getCategory2List(requestMap);
	}
	@RequestMapping(value = "/excludeInterceptor/getCategory3List", method = RequestMethod.POST)
	public @ResponseBody List<String> getCategory3List(HttpServletRequest request ,
			@RequestBody Map<String, String> requestMap) throws Exception {

		return itemDetailService.getCategory3List(requestMap);
	}

}
