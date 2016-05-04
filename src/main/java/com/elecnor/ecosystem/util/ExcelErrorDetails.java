package com.elecnor.ecosystem.util;

public class ExcelErrorDetails {

	private int rowNumber;
	private int colNumber;
	private String errorMessage;
	private String excelCell;
	
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public int getColNumber() {
		return colNumber;
	}
	public void setColNumber(int colNumber) {
		this.colNumber = colNumber;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getExcelCell() {
		return excelCell;
	}
	public void setExcelCell(String excelCell) {
		this.excelCell = excelCell;
	}
}
