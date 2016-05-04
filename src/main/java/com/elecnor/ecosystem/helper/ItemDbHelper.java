package com.elecnor.ecosystem.helper;


public class ItemDbHelper {

/*	public HashMap<String, Object> validateRowDataAndFetchBean(Row row, UserDetail userDetail) {

		int rowNumber = row.getRowNum();
		ArrayList<ExcelErrorDetails> rowErrorList = new ArrayList<ExcelErrorDetails>();
		String stringToCheck = "";
		UploadFileUtility upUtil = new UploadFileUtility();
		int colNum;
		double doubleValue;
		boolean isRowHavingErrors = false;
		BigInteger val;
		ItemDB itemDb = new ItemDB();
		Map<String, Object> rowValidationReturn = new HashMap<String, Object>();
		
		// validation for Item Description
		colNum = ConstantUtil.ITEM_DB_DATA_ITEM_DESCRIPTION;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			itemDb.setItemDescription(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for Item Code
		colNum = ConstantUtil.ITEM_DB_DATA_ITEM_CODE;
		try {
			doubleValue = (long) row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			stringToCheck = String.valueOf(doubleValue);
			itemDb.setItemCode(BigInteger.valueOf((long) doubleValue));
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_INTEGER_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for Category Level 1
		colNum = ConstantUtil.ITEM_DB_DATA_CATEGORY1;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			itemDb.setCategoryLevel1(stringToCheck);
		} catch (Exception e) {
			rowErrorList.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}
		
		itemDb.setSubmittedBy(userDetail);
		itemDb.setSubmittedDate(new Date());
		itemDb.setDomainDetail(userDetail.getDomainDetail());
		itemDb.setStatus("ACTIVE");

		try {
			itemDb.setCategoryLevel2(row.getCell(ConstantUtil.ITEM_DB_DATA_CATEGORY2, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setCategoryLevel2("");
		}

		try {
			itemDb.setCategoryLevel3(row.getCell(ConstantUtil.ITEM_DB_DATA_CATEGORY3, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setCategoryLevel3("");
		}

		try {
			itemDb.setPriceCode(row.getCell(ConstantUtil.ITEM_DB_DATA_PRICE_CODE, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setPriceCode("");
		}

		try {
			itemDb.setMaterialUnit(row.getCell(ConstantUtil.ITEM_DB_DATA_MATERIAL_UNIT, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setMaterialUnit("");
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_MATERIAL_PRICE, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			// checkValue = String.valueOf((double)doubleValue);
			if (doubleValue == 0.0) {
				itemDb.setMaterialPrice(null);
			} else {
				itemDb.setMaterialPrice(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setMaterialPrice(null);
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_MATERIAL_DISCOUNT, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setMaterialDiscount(null);
			} else {
				itemDb.setMaterialDiscount(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setMaterialDiscount(null);
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_MATERIAL_NET_COST, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setMaterialNetCost(null);
			} else {
				itemDb.setMaterialNetCost(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setMaterialNetCost(null);
		}

		try {
			itemDb.setNetCondition(row.getCell(ConstantUtil.ITEM_DB_DATA_NET_CONDITION, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setNetCondition("");
		}

		try {
			itemDb.setMaterialCondition(row.getCell(ConstantUtil.ITEM_DB_DATA_MATERIAL_CONDITION, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setMaterialCondition("");
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_PRICE_FACTOR, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setPriceFactor(null);
			} else {
				itemDb.setPriceFactor(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setPriceFactor(null);
		}

		try {
			itemDb.setLaborUnit(row.getCell(ConstantUtil.ITEM_DB_DATA_LABOR_UNIT, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setLaborUnit("");
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_ESTIMATING_LEVEL, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setEstimatingLevel(null);
			} else {
				itemDb.setEstimatingLevel(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setEstimatingLevel(null);
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_COL2LABOR, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setCol2Labor(null);
			} else {
				itemDb.setCol2Labor(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setCol2Labor(null);
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_COL3LABOR, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setCol3Labor(null);
			} else {
				itemDb.setCol3Labor(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setCol3Labor(null);
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_NECA1, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setNeca1(null);
			} else {
				itemDb.setNeca1(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setNeca1(null);
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_NECA2, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setNeca2(null);
			} else {
				itemDb.setNeca2(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setNeca2(null);
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_NECA3, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setNeca3(null);
			} else {
				itemDb.setNeca3(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setNeca3(null);
		}

		try {
			itemDb.setLaborCondition(row.getCell(ConstantUtil.ITEM_DB_DATA_LABOR_CONDITION, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setLaborCondition("");
		}

		try {
			itemDb.setBasedOn(row.getCell(ConstantUtil.ITEM_DB_DATA_BASED_ON, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setBasedOn("");
		}

		try {
			itemDb.setLaborCode(row.getCell(ConstantUtil.ITEM_DB_DATA_LABOR_CODE, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setLaborCode("");
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_ACTUAL_LABOR, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setActualLabor(null);
			} else {
				itemDb.setActualLabor(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setActualLabor(null);
		}

		try {
			itemDb.setBelcoLabor(row.getCell(ConstantUtil.ITEM_DB_DATA_BELCO_LABOR, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setBelcoLabor("");
		}

		try {
			itemDb.setBelcoLaborDescription(row.getCell(ConstantUtil.ITEM_DB_DATA_BELCO_LABOR_DESCRIPTION, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setBelcoLaborDescription("");
		}

		try {
			itemDb.setBelcoMaterial(row.getCell(ConstantUtil.ITEM_DB_DATA_BELCO_MATERIAL, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setBelcoMaterial("");
		}

		try {
			itemDb.setBelcoMaterialDescription(row.getCell(ConstantUtil.ITEM_DB_DATA_MATERIAL_DESCRIPTION, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setBelcoMaterialDescription("");
		}

		try {
			itemDb.setWeightUnit(row.getCell(ConstantUtil.ITEM_DB_DATA_WEIGHT_UNIT, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setWeightUnit("");
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_WEIGHT, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setWeight(null);
			} else {
				itemDb.setWeight(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setWeight(null);
		}

		try {
			itemDb.setManufacturerName(row.getCell(ConstantUtil.ITEM_DB_DATA_MANUFACTURER_NAME, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setManufacturerName("");
		}

		try {
			itemDb.setCatalogNumber(row.getCell(ConstantUtil.ITEM_DB_DATA_CATALOG_NO, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setCatalogNumber("");
		}

		try {
			itemDb.setSupplierName(row.getCell(ConstantUtil.ITEM_DB_DATA_SUPPLIER_NAME, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e2) {
			itemDb.setSupplierName("");
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_SUPPLIER_CODE, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setSupplierCode(null);
			} else {
				itemDb.setSupplierCode(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setSupplierCode(null);
		}

		try {
			val = BigInteger.valueOf((long) row.getCell(ConstantUtil.ITEM_DB_DATA_REFERENCE, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
			if (val.intValue() == 0) {
				itemDb.setReference(null);
			} else {
				itemDb.setReference(val);
			}
		} catch (Exception e1) {
			itemDb.setReference(null);
		}

		try {
			doubleValue = row.getCell(ConstantUtil.ITEM_DB_DATA_UNIVERSAL_ITEM_CODE, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			if (doubleValue == 0.0) {
				itemDb.setUniversalItemCode(null);
			} else {
				itemDb.setUniversalItemCode(doubleValue);
			}
		} catch (Exception e) {
			itemDb.setUniversalItemCode(null);
		}

		try {
			val = BigInteger.valueOf((long) row.getCell(ConstantUtil.ITEM_DB_DATA_QUICK_TAKEOFF_CODE, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
			if (val.intValue() == 0) {
				itemDb.setQuickTakeoffCode(null);
			} else {
				itemDb.setQuickTakeoffCode(val);
			}
		} catch (Exception e1) {
			itemDb.setQuickTakeoffCode(null);
		}
		ExcelErrorDetails excelErrorDetailsBasedOnBeanValidations = upUtil.getExcelErrorDetailsBasedOnBeanValidations(
				row.getRowNum(), itemDb);
		if (excelErrorDetailsBasedOnBeanValidations != null) {
			isRowHavingErrors = true;
			rowErrorList.add(excelErrorDetailsBasedOnBeanValidations);
		}

		if (isRowHavingErrors) {
			rowValidationReturn.put("errorList", rowErrorList);
			rowValidationReturn.put("itemDbBean", null);

		} else {
			rowValidationReturn.put("errorList", null);
			rowValidationReturn.put("itemDbBean", itemDb);
		}
		return (HashMap<String, Object>) rowValidationReturn;
	}*/
}
