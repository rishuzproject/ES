package com.elecnor.ecosystem.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.DepartmentHierarchyDetails;
import com.elecnor.ecosystem.dao.DepartmentHierarchyDAO;

@Repository
public class DepartmentHierarchyDAOImpl implements DepartmentHierarchyDAO {

	@Autowired
//	@Qualifier("masterDBSessionFactory")
	SessionFactory sessionFactory;

	@Override
//	@Transactional("masterDB")
	@Transactional
	public String addDepartmentHierarchy(DepartmentHierarchyDetails departmentHierarchyToBeAdded) {
		// TODO Auto-generated method stub
		String status = "success";
		try {
			Session session = (Session) sessionFactory.getCurrentSession();
			session.save(departmentHierarchyToBeAdded);
		} catch (HibernateException e) {
			status = e.getMessage();
			e.printStackTrace();
		}
		return status;
	}

	@Override
//	@Transactional("masterDB")
	@Transactional
	public String deleteDepartmentHierarchy(int departmentId) {
		// TODO Auto-generated method stub
		String status = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("delete from DepartmentHierarchyDetail  where  departmeniId=:departmentId or associatedDepartment=:departmentId");
			query.setParameter("departmentId", departmentId);
		} catch (HibernateException e) {
			status = e.getMessage();
			e.printStackTrace();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
//	@Transactional("masterDB")
	@Transactional
	@Override
	public List<DepartmentHierarchyDetails> getParentDepartment(Integer deptId) {
		List<DepartmentHierarchyDetails> parentDept = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from DepartmentHierarchyDetails where DEPARTMENT_ID =:deptId AND TYPE = 'PARENT'");
			query.setParameter("deptId", deptId);
			parentDept = (List<DepartmentHierarchyDetails>)query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return parentDept;
	}
}
