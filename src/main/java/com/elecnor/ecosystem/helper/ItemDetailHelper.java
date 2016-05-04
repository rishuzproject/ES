package com.elecnor.ecosystem.helper;

import javax.servlet.http.HttpServletRequest;

public class ItemDetailHelper {

	public int setItemIdToDelete(HttpServletRequest request) throws Exception{

		int itemId = 0;
		try {
			if (request.getParameter("itemIdToDel") != null
					&& request.getParameter("itemIdToDel") != "") {
				itemId = Integer.parseInt(request.getParameter("itemIdToDel")
						.trim());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return itemId;
	}

}
