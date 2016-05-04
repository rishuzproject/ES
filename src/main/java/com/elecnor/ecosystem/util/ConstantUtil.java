package com.elecnor.ecosystem.util;

/**
 * 
 * @author Harsh Verma
 * 
 */
public class ConstantUtil {
	public static final String ERROR_MAIL_SUBJECT_PRODUCTION = "Exception Occurred in Ecosystem Production System";
	public static final String ERROR_MAIL_SUBJECT_TEST = "Exception Occurred in Ecosystem Test System";

	public static final String ENVIRONMENT_DEVELOPMENT = "DEVELOPMENT";
	public static final String ENVIRONMENT_TEST = "TEST";
	public static final String ENVIRONMENT_PRODUCTION = "PRODUCTION";

	public final static String HEADER_PROPERTIES = "header.properties";

	public final static String CUSTOMER_EXCEL_FORMAT = "CUSTOMER_EXCEL_FORMAT";

	public final static String CUSTOMER_DIRECTORY_HEADER_ROWNUM = "CUSTOMER_DIRECTORY_HEADER_ROWNUM";

	public final static String CUSTOMER_DIRECTORY_DATA_STARTNO = "CUSTOMER_DIRECTORY_DATA_STARTNO";

	public final static String VENDOR_EXCEL_FORMAT = "VendorDirectory";

	public final static String VENDOR_DIRECTORY_HEADER_ROWNUM = "VENDOR_DIRECTORY_HEADER_ROWNUM";

	public final static String VENDOR_DIRECTORY_DATA_STARTNO = "VENDOR_DIRECTORY_DATA_STARTNO";

	public final static String PROJECT_TYPE_HEADER_ROWNUM = "PROJECT_TYPE_HEADER_ROWNUM";

	public static final String ITEM_DB_HEADER_ROWNUM = "ITEM_DB_HEADER_ROWNUM";

	public final static String PHONE_FORMAT = "[0-9]+";

	public final static String EMAIL_FORMAT = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	public final static String ACTIVE = "ACTIVE";

	public final static String INACTIVE = "INACTIVE";

	public final static String PENDINGACTIVE = "PENDING ACTIVATION";

	public final static String PHONE = "PHONE";

	public final static String EMAIL = "EMAIL";

	public final static String SUCCESS = "success";

	public final static String FAILURE = "failure";

	public final static String PHONE_NO_NOT_AVAILABLE = "Phone Number is not available";

	public final static String EMAIL_NOT_AVAILABLE = "E-Mail is not available";

	public final static String HEADER_EXCEPTION = "Header is not valid";

	public final static String[] columnMapping = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	
	public final static String CUSTOMER_DIRECTORY_EXCEL_FORMAT = "CustomerDirectory";

	public final static String DEPARTMENT_TYPE_EXCEL_FORMAT = "DepartmentType";

	public final static String DEPARTMENT_TYPE_HEADER_ROWNUM = "DEPARTMENT_TYPE_HEADER_ROWNUM";

	public final static String BUDGET_CODE_HEADER_ROWNUM = "BUDGET_CODE_HEADER_ROWNUM";

	public final static String JOB_TYPE_EXCEL_FORMAT = "JobDetail";

	public static final String PROJECT_TYPE_EXCEL_FORMAT = "ProjectType";

	public static final String CONTRACTOR_DIRECTORY_EXCEL_FORMAT = "ContractorDirectory";

	public static final String BUDGET_CODE_EXCEL_FORMAT = "BudgetCode";

	public static final String ITEM_DB_EXCEL_FORMAT = "ItemDb";

	public static final String PACKAGE_NAME="com.elecnor.ecosystem.bean.";

	public static final String APPLICATION_INVOICE_IS_LATE_FEE = "APPLICATION_INVOICE_IS_LATE_FEE";

	public static final String APPLICATION_INVOICE_IS_INTEREST = "APPLICATION_INVOICE_IS_INTEREST";
	public static final String ERROR_FILE_READING_ERROR = "Error Reading the File.";

	public static final String HEADER_PROPERTY_FILE_NAME = "header.properties";
	public static final String ERROR_HEADER_VALIDATION_ERROR = "Error in Template Header";
	public static final String ERROR_STRING_VALIDATION_ERROR = "This field should have alphabetical characters";
	public static final String ERROR_NULL_VALIDATION_ERROR = "This field cannot be null";
	public static final String ERROR_INTEGER_VALIDATION_ERROR = "This field should have only numbers";
	public static final String ERROR_PHONE_FORMAT_VALIDATION_ERROR = "Wrong Phone Format";
	public static final String ERROR_PHONE_MAX_LENGTH_VALIDATION_ERROR = "Phone No. length cannot be more than 15";
	public static final String ERROR_NULL_EMAIL_FORMAT_VALIDATION_ERROR = "Invalid email format";
	public static final String ERROR_COMPANY_NO_MAX_LENGTH_ERROR = "Company No. length cannot be more than 10";

	public static final int VENDOR_DIRECTORY_DATA_VENDOR_NAME = 0;
	public static final int VENDOR_DIRECTORY_DATA_VENDOR_LEGAL_NAME = 1;
	public static final int VENDOR_DIRECTORY_DATA_VENDOR_LEGAL_STATUS = 2;
	public static final int VENDOR_DIRECTORY_DATA_OWNERSHIP = 3;
	public static final int VENDOR_DIRECTORY_DATA_ADDRESS = 4;
	public static final int VENDOR_DIRECTORY_DATA_MAILING_ADDRESS = 5;
	public static final int VENDOR_DIRECTORY_DATA_PHONE = 6;
	public static final int VENDOR_DIRECTORY_DATA_FAX = 7;
	public static final int VENDOR_DIRECTORY_DATA_EMAIL = 8;
	public static final int VENDOR_DIRECTORY_DATA_WEBSITE = 9;
	public static final int VENDOR_DIRECTORY_DATA_REPRESENTATIVE_NAME = 10;
	public static final int VENDOR_DIRECTORY_DATA_REPRESENTATIVE_PHONE = 11;
	public static final int VENDOR_DIRECTORY_DATA_REPRESENTATIVE_ADDRESS = 12;
	public static final int VENDOR_DIRECTORY_DATA_IRS = 13;
	public static final int VENDOR_DIRECTORY_DATA_BUSINESS_TYPE = 14;
	public static final int VENDOR_DIRECTORY_SHEETNUM = 1;

	public final static int CUSTOMER_DIRECTORY_SHEETNUM = 1;
	public static final int CUSTOMER_DIRECTORY_DATA_COMPANY_NAME = 0;
	public static final int CUSTOMER_DIRECTORY_DATA_COMPANY_NUMBER = 1;
	public static final int CUSTOMER_DIRECTORY_DATA_OFFICE_ADDRESS = 2;
	public static final int CUSTOMER_DIRECTORY_COMPANY_EMAIL = 3;
	public static final int CUSTOMER_DIRECTORY_DATA_PHONE = 4;
	public static final int CUSTOMER_DIRECTORY_DATA_FAX = 5;
	public static final int CUSTOMER_DIRECTORY_DATA_REPRESENTATIVE_NAME = 6;
	public static final int CUSTOMER_DIRECTORY_DATA_REPRESENTATIVE_PHONE = 7;
	public static final int CUSTOMER_DIRECTORY_DATA_REPRESENTATIVE_ADDRESS = 8;
	public static final int CUSTOMER_DIRECTORY_DATA_IRS = 9;
	public static final int CUSTOMER_DIRECTORY_DATA_BUSINESS_TYPE = 10;
	
	
	
	

	public final static int SHEETNUM = 1;
	public static final int PROJECT_TYPE_DATA_PROJECT_NAME = 0;

	
	
	public final static String STATE_LICENSE_EXCEL_FORMAT = "SLicenseDirectory";
    public static final int STATE_LICENSE_SHEETNUM=1;
    public static final String STATE_LICENSE_HEADER_ROWNUM="STATE_LICENSE_HEADER_ROWNUM";
    public static final int STATE_LICENSE_DATA_STARTNO = 1;
    public static final int STATE_LICENSE_DATA_LICENSE_NO = 0;
    public static final int	STATE_LICENSE_DATA_STATE = 1;
    public static final int	STATE_LICENSE_DATA_EXPIRY_DATE = 2;
    public static final int	STATE_LICENSE_DATA_PRIMARY_PERSON = 3;
    public static final int STATE_LICENSE_DATA_LICENSE_DESCRIPTION = 4;
    
   
    
    
    public static final String LOCAL_LICENSE_EXCEL_FORMAT = "LLicenseDirectory";
    public static final int LOCAL_LICENSE_SHEETNUM=1;
    public static final String LOCAL_LICENSE_HEADER_ROWNUM="LOCAL_LICENSE_HEADER_ROWNUM";
    public static final int LOCAL_LICENSE_DATA_STARTNO = 1;
    public static final int LOCAL_LICENSE_DATA_LICENSE_NO = 0;
    public static final int	LOCAL_LICENSE_DATA_LOCAL_JURISDICTION = 1;
    public static final int	LOCAL_LICENSE_DATA_EXPIRY_DATE = 2;
    public static final int	LOCAL_LICENSE_DATA_PRIMARY_PERSON = 3;
    
    
   
   
    public static final int CONTRACTOR_DIRECTORY_SHEETNUM=1;
    public static final String CONTRACTOR_DIRECTORY_HEADER_ROWNUM="CONTRACTOR_DIRECTORY_HEADER_ROWNUM";
    public static final int CONTRACTOR_DIRECTORY_DATA_STARTNO = 1;
    public static final int CONTRACTOR_DIRECTORY_DATA_CONTRACTOR_NAME = 0;
    public static final int	CONTRACTOR_DIRECTORY_DATA_CONTRACTOR_NO = 1;
    public static final int	CONTRACTOR_DIRECTORY_DATA_OFFICE_ADDRESS = 2;
    public static final int	CONTRACTOR_DIRECTORY_DATA_CONTRACTOR_EMAIL = 3;
    public static final int CONTRACTOR_DIRECTORY_DATA_PHONE = 4;
    public static final int CONTRACTOR_DIRECTORY_DATA_FAX = 5;
    public static final int CONTRACTOR_DIRECTORY_DATA_REPRESENTATIVE_NAME = 6;
    public static final int	CONTRACTOR_DIRECTORY_DATA_REPRESENTATIVE_PHONE = 7;
    public static final int	CONTRACTOR_DIRECTORY_DATA_REPRESENTATIVE_ADDRESS = 8;
    public static final int	CONTRACTOR_DIRECTORY_DATA_BUSINESS_TYPE = 9;
    
    


	public static final int DEPARTMENT_TYPE_SHEETNUM = 1;
	public static final int DEPARTMENT_TYPE_DATA_DEPARTMENT_NAME = 0;

	public static final int ITEM_DB_SHEETNUM = 1;
	public static final int ITEM_DB_DATA_ITEM_DESCRIPTION = 0;
	public static final int ITEM_DB_DATA_ITEM_CODE = 1;
	public static final int ITEM_DB_DATA_CATEGORY1 = 2;
	public static final int ITEM_DB_DATA_CATEGORY2 = 3;
	public static final int ITEM_DB_DATA_CATEGORY3 = 4;
	public static final int ITEM_DB_DATA_PRICE_CODE = 5;
	public static final int ITEM_DB_DATA_MATERIAL_UNIT = 6;
	public static final int ITEM_DB_DATA_MATERIAL_PRICE = 7;
	public static final int ITEM_DB_DATA_MATERIAL_DISCOUNT = 8;
	public static final int ITEM_DB_DATA_MATERIAL_NET_COST = 9;
	public static final int ITEM_DB_DATA_NET_CONDITION = 10;
	public static final int ITEM_DB_DATA_MATERIAL_CONDITION = 11;
	public static final int ITEM_DB_DATA_PRICE_FACTOR = 12;
	public static final int ITEM_DB_DATA_LABOR_UNIT = 13;
	public static final int ITEM_DB_DATA_ESTIMATING_LEVEL = 14;
	public static final int ITEM_DB_DATA_COL2LABOR = 15;
	public static final int ITEM_DB_DATA_COL3LABOR = 16;
	public static final int ITEM_DB_DATA_NECA1 = 17;
	public static final int ITEM_DB_DATA_NECA2 = 18;
	public static final int ITEM_DB_DATA_NECA3 = 19;
	public static final int ITEM_DB_DATA_LABOR_CONDITION = 20;
	public static final int ITEM_DB_DATA_BASED_ON = 21;
	public static final int ITEM_DB_DATA_LABOR_CODE = 22;
	public static final int ITEM_DB_DATA_ACTUAL_LABOR = 23;
	public static final int ITEM_DB_DATA_BELCO_LABOR = 24;
	public static final int ITEM_DB_DATA_BELCO_LABOR_DESCRIPTION = 25;
	public static final int ITEM_DB_DATA_BELCO_MATERIAL = 26;
	public static final int ITEM_DB_DATA_MATERIAL_DESCRIPTION = 27;
	public static final int ITEM_DB_DATA_WEIGHT_UNIT = 28;
	public static final int ITEM_DB_DATA_WEIGHT = 29;
	public static final int ITEM_DB_DATA_MANUFACTURER_NAME = 30;
	public static final int ITEM_DB_DATA_CATALOG_NO = 31;
	public static final int ITEM_DB_DATA_SUPPLIER_NAME = 32;
	public static final int ITEM_DB_DATA_SUPPLIER_CODE = 33;
	public static final int ITEM_DB_DATA_REFERENCE = 34;
	public static final int ITEM_DB_DATA_UNIVERSAL_ITEM_CODE = 35;
	public static final int ITEM_DB_DATA_QUICK_TAKEOFF_CODE = 36;

	public static final int BUDGET_CODE_SHEETNUM = 1;
	public static final int BUDGET_CODE_DATA_COST_TYPE = 0;
	public static final int BUDGET_CODE_DATA_ACTIVITY_NO = 1;
	public static final int BUDGET_CODE_DATA_ACTIVITY_DESCRIPTION = 2;
	
	public static final String REJECT_ITEM_IN_SOV = "REJECT";
	public static final String APPROVE_ITEM_IN_SOV = "APPROVE";
	
	public static final String REDIRECT_TO_SOV_TABLE = "/home#sovTable";
	public static final String SOV_CUSTOMER_APPROVAL_REQUEST_EMAIL_BODY = "SOV Items have been requested for approval.";
	
	public static final Long ADMIN_ROLE_ID = 1L;
	public static final Long EXECUTIVE_ROLE_ID = 2L;
	public static final Long SUPERVISOR_ROLE_ID = 3L;
	public static final Long MANAGER_ROLE_ID = 4L;
	public static final Long FOREMAN_ROLE_ID = 5L;
	public static final Long PURCHASING_AGENT_ROLE_ID = 6L;
	public static final Long WAREHOUSE_ROLE_ID = 7L;
	public static final Long SUPERINTENDENT_ROLE_ID = 8L;
	public static final Long ACCOUNTANT_ROLE_ID = 9L;
	



	public static final String EMAIL_LOG_ECOSYSTEM_DATA_SAVED = "EMAIL_LOG_ECOSYSTEM_DATA_SAVED";
	public static final String EMAIL_LOG_ECOSYSTEM_DATA_NOT_SAVED = "EMAIL_LOG_ECOSYSTEM_DATA_NOT_SAVED";
	 
	public static final String REDIRECT_TO_APPLICATION_INVOICE_IN_DETAILS = "applicationInvoiceInDetails";
	public static final String REDIRECT_TO_APPLICATION_INVOICE = "applicationInvoice";
	
	public static final String DEPARTMENT_HEAD = "DEPARTMENT HEAD";
	public static final String DEPARTMENT_STAFF = "DEPARTMENT STAFF";
	
	
	public static final String APP_NAME_ECOSYSTEM = "ECOSYSTEM";
	public static final String APP_NAME_MPR = "MPR";
	
	public static final String EXCEPTION_EMAIL_SUBJECT_BUDGET_FORMS = "Exception while adding Budget Form in ECOSYSTEM";
	public static final String EXCEPTION_EMAIL_SUBJECT_CASHFLOW = "Exception while adding Cashflow and Manpower rows in MPR";

	public static final String MODULE_NAME_FOR_JOB_TYPE = "JOB_SETUP";
	
}
