package com.elecnor.ecosystem.helper;

import java.util.HashMap;

public class DomainDetailHelper {

	public HashMap<Object, Object> getResultMap(String result) throws Exception{
		
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			if (result == null) {
				resultMap.put("ajaxResult", "success");
			} else {
				resultMap.put("ajaxResult", "failure");
				resultMap.put("reason", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultMap;
	}

}
