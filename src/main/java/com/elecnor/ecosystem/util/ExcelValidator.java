package com.elecnor.ecosystem.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Harsh Verma
 *
 */
public class ExcelValidator {

	public static ArrayList<ExcelValidatePojoUtil> errorListObj = new ArrayList<ExcelValidatePojoUtil>();
	public static ArrayList<String> successListObj = new ArrayList<String>();
	public static Pattern phonePattern = Pattern.compile(ConstantUtil.PHONE_FORMAT);
	public static Pattern emailPattern = Pattern.compile(ConstantUtil.EMAIL_FORMAT);
	public static Matcher matcher;

	/**
	 * Method for validate excel header and for getting row and sheet value from HEADER_PROPERTIES file
	 * 
	 * @param excelHeader
	 * @param header
	 * @return
	 */
	public Integer validateExcelFileHeader(ArrayList<String> excelHeader,String header,String value) {
		int result = 0;
		try {
			ClassLoader cl = PropertyFileReader.class.getClassLoader();
			InputStream in = cl.getResourceAsStream(ConstantUtil.HEADER_PROPERTIES);
			Properties prop = new Properties();
			prop.load(in);
			if(value==null){
				String excelUploadedHeader = excelHeader.toString().substring(1,excelHeader.toString().indexOf("]"));
				if (excelUploadedHeader.equalsIgnoreCase(prop.getProperty(header))) {
					result = 1;
				} else {
					result = 0;
				}
			}else{
				result = Integer.valueOf(prop.getProperty(value));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Method for validate excel data
	 * 
	 * @param cellValue
	 * @param row
	 * @param cell
	 * @param errorMsg
	 * @param value
	 */
	public ExcelValidatePojoUtil validateExcelData(String cellValue, int row,
			int cell, String errorMsg, String value) {
		ExcelValidatePojoUtil validateObj = null;

		if (value == null) {
			if (cellValue.trim().equalsIgnoreCase("")) {
				validateObj = errorMessagesofExcelData(row, cell, errorMsg);
			}
		} else if (value == ConstantUtil.PHONE) {
			matcher = phonePattern.matcher(cellValue);
			if (cellValue.trim().equalsIgnoreCase("")) {
				validateObj = errorMessagesofExcelData(row, cell,ConstantUtil.PHONE_NO_NOT_AVAILABLE);
			} else if (!cellValue.trim().equalsIgnoreCase("")
					&& !matcher.matches()) {
				validateObj = errorMessagesofExcelData(row, cell, errorMsg);
			}
		} else if (value == ConstantUtil.EMAIL) {
			matcher = emailPattern.matcher(cellValue);
			if (cellValue.trim().equalsIgnoreCase("")) {
				validateObj = errorMessagesofExcelData(row, cell,ConstantUtil.EMAIL_NOT_AVAILABLE);
			} else if (!cellValue.trim().equalsIgnoreCase("")
					&& !matcher.matches()) {
				validateObj = errorMessagesofExcelData(row, cell, errorMsg);
			}
		}
			return validateObj;

	}

	/**
	 * Setting error details
	 * 
	 * @param row
	 * @param cell
	 * @param error
	 */
	
	private ExcelValidatePojoUtil errorMessagesofExcelData(int row, int cell,String error) {
		ExcelValidatePojoUtil validateObj = new ExcelValidatePojoUtil();
		validateObj.setRow(row);
		validateObj.setCell(cell);
		validateObj.setError(error);
		return validateObj;
	}
	
	/**
	 * Method for set values if header error is there
	 * @param fileName
	 * @param errorMsg
	 * @return
	 */
	public ExcelValidatePojoUtil headerError(String fileName, String errorMsg){
		ExcelValidatePojoUtil validateObj = new ExcelValidatePojoUtil();
		validateObj.setError(errorMsg);
		validateObj.setFileName(fileName);
		return validateObj;
	}

}
