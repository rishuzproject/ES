package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.DepartmentType;
import com.elecnor.ecosystem.dao.DepartmentsDetailDAO;

@Repository
public class DepartmentDetailDAOImpl implements DepartmentsDetailDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
 public DepartmentType setAddDepartmentDetails(DepartmentType departmentForm) throws Exception {
		DepartmentType result = null;

		try {
			Session hibSes = sessionFactory.getCurrentSession();
			result=(DepartmentType) hibSes.merge(departmentForm);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<DepartmentType> getAllDepartments() throws Exception{

		ArrayList<DepartmentType> departmentList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from DepartmentType where STATUS='ACTIVE'");
			departmentList = (ArrayList<DepartmentType>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return departmentList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<DepartmentType> getAllDepartments(Long domainId) throws Exception{

		ArrayList<DepartmentType> departmentList = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from DepartmentType where STATUS='ACTIVE' AND DOMAIN_ID=:domainId")
					.setParameter("domainId", domainId);
			departmentList = (ArrayList<DepartmentType>)query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return departmentList;
	}

	@Override
	@Transactional
	public String updateDepartmentDetails(DepartmentType departmentForm) throws Exception{
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update DepartmentType set DEPARTMENT_NAME=:deptname,UPDATED_BY=:updatedBy,UPDATED_DATE=:updatedDate where DEPARTMENT_ID=:deptid");
			query.setParameter("deptname", departmentForm.getDepartmentName());
			query.setParameter("deptid", departmentForm.getDepartmentId());
			query.setParameter("updatedBy", departmentForm.getUpdatedBy());
			query.setParameter("updatedDate", departmentForm.getUpdatedDate());
			query.executeUpdate();
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@Override
	@Transactional
	public String deleteDepartment(long deptId) throws Exception{

		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update DepartmentType set STATUS='INACTIVE' where DEPARTMENT_ID=:deptId");
			query.setParameter("deptId", deptId);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public DepartmentType getDepartmentById(long deptId) throws Exception{
		
		DepartmentType departmentType=null;
			try {
				System.out.println("Entered The DepartmentType");
				Query query = sessionFactory
						.getCurrentSession()
						.createQuery(
								"from DepartmentType  where DEPARTMENT_ID=:deptId");
				query.setParameter("deptId", deptId);
				departmentType=(DepartmentType)query.list().get(0);

			} catch (HibernateException e) {
				System.out.println(e.getMessage());
			}
			return departmentType;
		
	}
}
