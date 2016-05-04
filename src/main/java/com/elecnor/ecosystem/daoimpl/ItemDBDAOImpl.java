package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.ItemDB;
import com.elecnor.ecosystem.dao.ItemDBDAO;
import com.elecnor.ecosystem.querybuilder.ItemDBQueryBuilder;

@Repository
public class ItemDBDAOImpl implements ItemDBDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	ItemDBQueryBuilder queryBuilderUtil;
	private final String  iDisplayStart="iDisplayStart";

	@Override
	@Transactional
	public String addItemDetail(ItemDB itemForm) throws Exception{
		String result = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			hibSes.merge(itemForm);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ItemDB> getAllItems(Long domainId, Integer limitStart, Integer limitEnd) throws Exception{
		ArrayList<ItemDB> itemList =  new ArrayList<ItemDB>();
		try {
			StringBuilder queryStr = new StringBuilder("SELECT * from ITEM_DB where ITEM_STATUS='ACTIVE' AND DOMAIN_ID=" + domainId +" LIMIT "+limitStart+", 100;" );
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery(queryStr.toString()).addEntity(ItemDB.class);
			itemList = (ArrayList<ItemDB>) query.list();
			System.out.println(itemList.size());
			/*for (Object[] row : itemArrayList) {
				ItemDB itemDb = new ItemDB();
				itemDb.setItemId(Long.valueOf(String.valueOf(row[0])));
				itemDb.setCategoryLevel1(String.valueOf(row[1]));
				itemDb.setCategoryLevel2(String.valueOf(row[2]));
				itemDb.setCategoryLevel3(String.valueOf(row[3]));
				itemDb.setItemDescription(String.valueOf(row[4]));
				itemDb.setPriceCode(String.valueOf(row[5]));
				itemDb.setMaterialPrice(Double.valueOf(String.valueOf(row[6])));
				itemDb.setMaterialDiscount(Double.valueOf(String.valueOf(row[7])));
				itemDb.setMaterialNetPrice(Double.valueOf(String.valueOf(row[8])));
				itemDb.setBasedOn(String.valueOf(row[9]));
				itemDb.setBelcoLaborCode(String.valueOf(row[10]));
				itemDb.setBelcoLaborDescription(String.valueOf(row[11]));
				itemDb.setBelcoMaterialCode(String.valueOf(row[12]));
				itemDb.setBelcoMaterialDescription(String.valueOf(row[13]));
				itemDb.setManufacturerName(String.valueOf(row[14]));
				itemDb.setCatalogNumber(String.valueOf(row[15]));
				itemDb.setNetCondition(String.valueOf(row[16]));
				itemDb.setMaterialCondition(String.valueOf(row[17]));
				itemDb.setMaterialAdj1(Double.valueOf(String.valueOf(row[18])));
				itemDb.setMaterialAdj2(Double.valueOf(String.valueOf(row[19])));
				itemDb.setMaterialUnit(String.valueOf(row[20]));
				itemDb.setPriceFactor(Double.valueOf(String.valueOf(row[21])));
				itemDb.setLaborUnit(String.valueOf(row[22]));
				itemDb.setEstimatingLevel(Double.valueOf(String.valueOf(row[23])));
				itemDb.setCol2Labor(Double.valueOf(String.valueOf(row[24])));
				itemDb.setCol3Labor(Double.valueOf(String.valueOf(row[25])));
				itemDb.setNeca1(Double.valueOf(String.valueOf(row[26])));
				itemDb.setNeca2(Double.valueOf(String.valueOf(row[27])));
				itemDb.setNeca3(Double.valueOf(String.valueOf(row[28])));
				itemDb.setLaborCode(String.valueOf(row[29]));
				itemDb.setLaborFactoring(String.valueOf(row[30]));
				itemDb.setLaborCode(String.valueOf(row[31]));
				itemDb.setWeightUnit(String.valueOf(row[32]));
				itemDb.setWeight(Double.valueOf(String.valueOf(row[33])));
				itemDb.setSupplierName(String.valueOf(row[34]));
				itemDb.setSupplierCode(String.valueOf(row[35]));
				itemDb.setReference(String.valueOf(row[36]));
				itemDb.setUniversalItemCode(String.valueOf(row[37]));
				itemDb.setQuickTakeoffCode(String.valueOf(row[38]));
				if(String.valueOf(row[39]).equalsIgnoreCase("null")){
					itemDb.setActualLabor(Double.valueOf(0));
				}else{
					itemDb.setActualLabor(Double.valueOf(String.valueOf(row[39])));
				}
				itemList.add(itemDb);
			}*/
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return itemList;	
	}
	
	@Override
	@Transactional
	public String deleteItem(int itemId) throws Exception{
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update ItemDB set ITEM_STATUS='INACTIVE' where ITEM_ID=:itemId");
			query.setParameter("itemId",itemId);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@Override
	@Transactional
	public String updateItemDetail(ItemDB itemForm) throws Exception{
		String result = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			hibSes.merge(itemForm);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<String> getCategory1List() throws Exception {
		// TODO Auto-generated method stub
		String sqlQuery = "SELECT DISTINCT CATEGORY_LEVEL_1 FROM ITEM_DB";
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sqlQuery);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<String> getCategory2List(Map<String, String> selectedCategoryMap) throws Exception {
		// TODO Auto-generated method stub
		
		StringBuilder sqlQuery = new StringBuilder("SELECT DISTINCT CATEGORY_LEVEL_2 FROM ITEM_DB ");
		sqlQuery = queryBuilderUtil.appendCategoryQueryClause(sqlQuery, selectedCategoryMap);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sqlQuery.toString());
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<String> getCategory3List(Map<String, String> selectedCategoryMap) throws Exception {
		// TODO Auto-generated method stub

		StringBuilder sqlQuery = new StringBuilder("SELECT DISTINCT CATEGORY_LEVEL_3 FROM ITEM_DB ");
		sqlQuery = queryBuilderUtil.appendCategoryQueryClause(sqlQuery, selectedCategoryMap);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sqlQuery.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ItemDB> getItemListByCategorySelection(Map<String,String> requestMap)
			throws Exception {
		// TODO Auto-generated method stub
		
		
		StringBuilder queryStr = new StringBuilder("select itemId,itemDescription,materialNetPrice,belcoLaborCode,"
				+ "belcoLaborDescription,belcoMaterialCode,belcoMaterialDescription from ItemDB ");
		queryStr = queryBuilderUtil.appendCategoryQueryClauseForItemSearch(queryStr, requestMap);
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryStr.toString());
		//query.setFirstResult(Integer.parseInt(requestMap.get(iDisplayStart)));
		query.setMaxResults(1000);
		return (ArrayList<ItemDB>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ItemDB>  getAllItems() throws Exception{
	Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery("from ItemDB");
	return (List<ItemDB>) query.list();
}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ItemDB> getDetailedItemListByCategorySelection(Map<String,String> requestMap) throws Exception {
		// TODO Auto-generated method stub
		
		StringBuilder queryStr = new StringBuilder("from ItemDB ");
		queryStr = queryBuilderUtil.appendCategoryQueryClauseForItemSearch(queryStr, requestMap);
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryStr.toString());
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ItemDB> getItemByItemDescription(Map<String,String> requestMap) throws Exception {
		// TODO Auto-generated method stub

		StringBuilder queryStr = new StringBuilder("select new ItemDB(itemId,itemDescription,materialNetPrice,belcoLaborCode,"
				+ "belcoLaborDescription,belcoMaterialCode,belcoMaterialDescription) from ItemDB ");
		queryStr = queryBuilderUtil.appendItemDescQueryClause(queryStr, requestMap);
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryStr.toString());
		query.setFirstResult(Integer.parseInt(requestMap.get(iDisplayStart)));
		query.setMaxResults(100);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ItemDB> getDetailedItemByItemDescription(Map<String,String> requestMap) throws Exception {
		// TODO Auto-generated method stub
		
		StringBuilder queryStr = new StringBuilder("from ItemDB ");
		queryStr = queryBuilderUtil.appendItemDescQueryClause(queryStr, requestMap);
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryStr.toString());
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ItemDB> getItemDetailsByItemId(Long domainId, long itemId)
			throws Exception {
		ArrayList<ItemDB> itemList =  null;
		try{
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from ItemDB where ITEM_STATUS='ACTIVE' and ITEM_ID=:itemId and DOMAIN_ID=:domainId");
		query.setParameter("itemId",itemId);
		query.setParameter("domainId",domainId);
		itemList = (ArrayList<ItemDB>)query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return itemList;
		
	}
}
