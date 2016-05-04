package com.elecnor.ecosystem.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.BudgetFormLabor;
import com.elecnor.ecosystem.bean.BudgetFormMat;
import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.BudgetCodeDAO;
import com.elecnor.ecosystem.service.ExcelSheetService;
import com.elecnor.ecosystem.util.PropertyFileReader;

@Service
public class ExcelSheetServiceImpl implements ExcelSheetService{
	@Autowired
	BudgetCodeDAO budgetFormDAO;
	
	@Override
	public String updateBudgetCosts(File file, JobDetail selectedProjectBean, UserDetail loginUserProfileBean, RfcLog log) {

		FileInputStream fileStream = null;
		String exception = null;
		try {
			BudgetFormMat materialBudgetFormBean = null;
			BudgetFormLabor laborBudgetFormBean = null;
			// BudgetFormDAO budgetFormDAO = new BudgetFormDAO();

			fileStream = new FileInputStream(file);

			String[] columnIndexStringForCode = PropertyFileReader.getInstance().getStringProperty("readcolumunnumberforcode").split(",");
			int[] coumnIndexCheck = new int[columnIndexStringForCode.length];

			int i = 0;
			for (String string : columnIndexStringForCode) {
				coumnIndexCheck[i] = Integer.parseInt(string);
				i++;
			}

			String[] columnIndexStringForquote = PropertyFileReader.getInstance().getStringProperty("readcolumunnumberforquote").split(",");
			int[] coumnIndexCheckQuote = new int[columnIndexStringForquote.length];

			i = 0;
			for (String string : columnIndexStringForquote) {
				coumnIndexCheckQuote[i] = Integer.parseInt(string);
				i++;
			}

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook;

			workbook = new XSSFWorkbook(fileStream);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(1);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int rowindex = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// RfcSubCostTypeBean subcost = null;

				if (rowindex > PropertyFileReader.getInstance().getIntProperty("readrownum")) {
					int columnIndex = 0;

					if (row.getPhysicalNumberOfCells() < 8)
						break;

					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
					double quantity = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						// Check the cell type and format accordingly

						if (columnIndex == PropertyFileReader.getInstance().getIntProperty("quantityFieldIndex")) {
							quantity = (double) cell.getNumericCellValue();
						}
						for (int index : coumnIndexCheck) {
							if (columnIndex == index) {
								// At Position 3 , we get Material
								// Activity
								// Code, forming initial Bean
								if (index == 3) {
									materialBudgetFormBean = new BudgetFormMat();
									// materialBudgetFormBean
									// .setProjectMaster(selectedProjectBean);
									materialBudgetFormBean.setUpdatedByUserDetail(loginUserProfileBean);
									materialBudgetFormBean.setRfcLog(log);
									materialBudgetFormBean.setMaterial(quantity);
									switch (cell.getCellType()) {
									case Cell.CELL_TYPE_NUMERIC:
										materialBudgetFormBean.setActivityNumber((int) cell.getNumericCellValue());

										break;
									case Cell.CELL_TYPE_STRING:
										materialBudgetFormBean.setActivityNumber(Integer.valueOf(cell.getStringCellValue()));
										break;
									}

								} else if (index == 6) {
									laborBudgetFormBean = new BudgetFormLabor();
									// laborBudgetFormBean
									// .setProjectMasterBean(selectedProjectBean);
									laborBudgetFormBean.setUpdatedByUserDetail(loginUserProfileBean);
									laborBudgetFormBean.setRfcLog(log);
									switch (cell.getCellType()) {
									case Cell.CELL_TYPE_NUMERIC:
										laborBudgetFormBean.setActivityNumber((int) cell.getNumericCellValue());

										break;
									case Cell.CELL_TYPE_STRING:
										laborBudgetFormBean.setActivityNumber(Integer.valueOf(cell.getStringCellValue()));
										break;
									}
								}
							}
						}

						for (int index : coumnIndexCheckQuote) {
							if (columnIndex == index) {
								// At Index 4, we get Material Quoted
								// Price
								if (index == 4) {
									System.out.println("cell values"+cell);
									switch (cell.getCellType()) {
									case Cell.CELL_TYPE_NUMERIC:
										materialBudgetFormBean.setQuotedItems(cell.getNumericCellValue());
										break;
									case Cell.CELL_TYPE_STRING:
										materialBudgetFormBean
												.setQuotedItems(Double.parseDouble((cell.getStringCellValue().split("$"))[0]));
										break;
									}
									if (materialBudgetFormBean.getActivityNumber() != 0) {
//										System.out.println("Updating Material Budget Form for Activity Number "
//												+ materialBudgetFormBean.getActivityNumber());

										// Update Material Budget
										// Form.
										String exp = budgetFormDAO.updateMaterialFormDataBasedOnRfcIdAndActivityNumber(materialBudgetFormBean);
									}
								} else if (index == 7) {
									switch (cell.getCellType()) {
									case Cell.CELL_TYPE_NUMERIC:
										laborBudgetFormBean.setHours(cell.getNumericCellValue());

										break;
									case Cell.CELL_TYPE_STRING:
										laborBudgetFormBean.setHours(Double.parseDouble((cell.getStringCellValue().split("$"))[0]));
										break;
									}
									if (laborBudgetFormBean.getActivityNumber() != 0) {
//										System.out.println("Updating laborBudgetFormBean for Activity Number "
//												+ laborBudgetFormBean.getActivityNumber());
										// Update laborBudgetFormBean
										// Budget
										// Form.
										budgetFormDAO.updateLaborFormDataRfcIdAndActivityNumber(laborBudgetFormBean);

									}

								}

							}
						}

						columnIndex++;
					}

				}
				rowindex++;
				System.out.println("rowindex" + rowindex);
			}
		} catch (IOException e) {
			exception = e.getMessage();
			e.printStackTrace();
		} catch (Exception e) {
			exception = e.getMessage();
//			e.printStackTrace();
		} finally {
			try {
				fileStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return exception;

	}
}
