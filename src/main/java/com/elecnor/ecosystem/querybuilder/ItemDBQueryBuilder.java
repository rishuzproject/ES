package com.elecnor.ecosystem.querybuilder;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ItemDBQueryBuilder {
	
	private final String category1LabelInJson = "selectedCategory1List";
	private final String category2LabelInJson = "selectedCategory2List";
	private final String category3LabelInJson = "selectedCategory3List";
	private final String itemDescriptionLabelInJson = "itemDescription";
	
	
	/**
	 * 
	 * @param queryStr
	 * @param selectedCategoryMap
	 * @return
	 */
	public StringBuilder appendCategoryQueryClause(StringBuilder queryStr , Map<String, String> selectedCategoryMap){
		
		
		if(selectedCategoryMap.containsKey(category1LabelInJson) && selectedCategoryMap.get(category1LabelInJson) !=null 
				&& !selectedCategoryMap.get(category1LabelInJson).isEmpty()){
			queryStr.append("WHERE CATEGORY_LEVEL_1 IN ('" + selectedCategoryMap.get(category1LabelInJson) + "')");
		}
		
		if(selectedCategoryMap.containsKey(category2LabelInJson) && selectedCategoryMap.get(category2LabelInJson) !=null 
				&& !selectedCategoryMap.get(category2LabelInJson).isEmpty()){
			queryStr.append(" AND CATEGORY_LEVEL_2 IN ('" + selectedCategoryMap.get(category2LabelInJson) + "')");
		}
		
		return queryStr;
	}
	
	/**
	 * 
	 * @param queryStr
	 * @param selectedCategoryMap
	 * @return
	 */
	public StringBuilder appendCategoryQueryClauseForItemSearch(StringBuilder queryStr , Map<String, String> selectedCategoryMap){
		
	
		if(selectedCategoryMap.containsKey(category1LabelInJson) && selectedCategoryMap.get(category1LabelInJson) !=null 
				&& !selectedCategoryMap.get(category1LabelInJson).isEmpty()){
			queryStr.append("WHERE CATEGORY_LEVEL_1 IN ('" + selectedCategoryMap.get(category1LabelInJson).trim() + "')");
		}
		
		if(selectedCategoryMap.containsKey(category2LabelInJson) && selectedCategoryMap.get(category2LabelInJson) !=null 
				&& !selectedCategoryMap.get(category2LabelInJson).isEmpty()){
			queryStr.append("AND CATEGORY_LEVEL_2 IN ('" + selectedCategoryMap.get(category2LabelInJson).trim() + "')");
		}
		
		if(selectedCategoryMap.containsKey(category3LabelInJson) && selectedCategoryMap.get(category3LabelInJson) !=null 
				&& !selectedCategoryMap.get(category3LabelInJson).isEmpty()){
			queryStr.append("AND CATEGORY_LEVEL_3 IN ('"+ selectedCategoryMap.get(category3LabelInJson).trim() +"')");
		}
		
		return queryStr;
	}
	
	/**
	 * 
	 * @param queryStr
	 * @param itemDescMap
	 * @return
	 */
	public StringBuilder appendItemDescQueryClause(StringBuilder queryStr , Map<String, String> itemDescMap){
		
		
		if(itemDescMap.containsKey(itemDescriptionLabelInJson) && itemDescMap.get(itemDescriptionLabelInJson)!=null 
				&& !itemDescMap.get(itemDescriptionLabelInJson).isEmpty()){
			
			queryStr.append(" WHERE itemDescription like '%"+itemDescMap.get(itemDescriptionLabelInJson).trim()+"%'");
		}
		return queryStr;
	}

}
