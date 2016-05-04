package com.elecnor.ecosystem.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UnitAbbreviationDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.UnitAbbreviationDirectoryDAO;
import com.elecnor.ecosystem.service.UnitAbbreviationDirectoryService;

@Service
public class UnitAbbreviationDirectoryServiceImpl implements UnitAbbreviationDirectoryService{
	
	@Autowired
	UnitAbbreviationDirectoryDAO unitAbbreviationDirectoryDAO;

	@Override
	public String addUpdateUnitAbbreviationService(UnitAbbreviationDirectory unitAbbreviationBean, UserDetail userDetail,
			DomainDetail domainDetail) throws Exception {
		
		String result = "";
		unitAbbreviationBean.setStatus("ACTIVE");
		try {
			unitAbbreviationBean.setDomainId(domainDetail);
			int lastSerialNo = unitAbbreviationDirectoryDAO.getLastSerialNo(domainDetail.getDomainId());
			unitAbbreviationBean.setSerialNo(lastSerialNo+1);
			if (unitAbbreviationBean.getAbbreviationId() == null) {
				unitAbbreviationBean.setSubmittedDate(new Date());
				unitAbbreviationBean.setSubmittedBy(userDetail);
				result = unitAbbreviationDirectoryDAO.addUnitAbbreviation(unitAbbreviationBean);
			} else {
				unitAbbreviationBean.setUpdatedDate(new Date());
				unitAbbreviationBean.setUpdatedBy(userDetail);
				result = unitAbbreviationDirectoryDAO.updateUnitAbbreviation(unitAbbreviationBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}
	
	@Override
	public HashMap<Object, Object> getAllAbbreviations(Long domainId) throws Exception {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		ArrayList<UnitAbbreviationDirectory> allAbbreviationsList = null;
		map.put("0", "Choose Unit Type ");
		try {
			allAbbreviationsList = unitAbbreviationDirectoryDAO
					.getAllAbbreviationsDAO(domainId);
			for(UnitAbbreviationDirectory ob : allAbbreviationsList){
				if(ob.getStatus().equalsIgnoreCase("ACTIVE"))
				{
				map.put(ob.getSerialNo(), ob.getAbbreviationName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return map;
	}
}
