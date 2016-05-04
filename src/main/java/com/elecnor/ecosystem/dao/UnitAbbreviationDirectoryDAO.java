package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.UnitAbbreviationDirectory;

public interface UnitAbbreviationDirectoryDAO {

	public String addUnitAbbreviation(UnitAbbreviationDirectory unitAbbreviationBean) throws Exception;

	public String updateUnitAbbreviation(UnitAbbreviationDirectory unitAbbreviationBean) throws Exception;

	public ArrayList<UnitAbbreviationDirectory> getAllAbbreviationsDAO(Long domainId) throws Exception;

	public int getLastSerialNo(Long domainId) throws Exception;

	public String deleteUnitAbbreviation(Long abbreviationId) throws Exception;

}
