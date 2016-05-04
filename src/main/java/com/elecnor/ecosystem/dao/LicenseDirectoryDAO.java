package com.elecnor.ecosystem.dao;
import java.util.ArrayList;

import com.elecnor.ecosystem.bean.LicenseDirectory;
public interface LicenseDirectoryDAO {

	public ArrayList<LicenseDirectory> getLicenseDetails(long signUpDomainId) throws Exception;
	public String addLicenseRecord(LicenseDirectory licenseDirectory) throws Exception;
	public String updateLicenseRecord(LicenseDirectory licenseDirectory) throws Exception;
	public String deleteLicense(long licenseId) throws Exception;
}
