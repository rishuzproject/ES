package com.elecnor.ecosystem.helper;

import java.util.HashMap;
import java.util.List;

public class RfcBudgetFormHelper {

	public HashMap<String, Object> checkListAndSetResultMap(
			List<?> listToCheck, String fieldName) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(listToCheck != null && listToCheck.size() > 0){
				resultMap.put("ajaxResult", "success");
				resultMap.put(fieldName, listToCheck);
			}else{
				resultMap.put("ajaxResult", "failure");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw e;
		}
		return resultMap;
	}
}
