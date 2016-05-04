package com.elecnor.ecosystem.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.ItemDB;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ItemDBDAO;
import com.elecnor.ecosystem.service.ItemDBService;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;

/**
 * @author Vaibhav
 *
 */
@Service
public class ItemDBServiceImpl implements ItemDBService {

	@Autowired
	ItemDBDAO itemDetailDAO;

	@Override
	public String addItemDetail(ItemDB itemForm, UserDetail userDetail, DomainDetail domainDetail) throws Exception {

		String result = "";
		try {
			itemForm.setStatus("ACTIVE");
			itemForm.setDomainDetail(domainDetail);

			if (!(itemForm.getItemId() == -1)) {
				itemForm.setUpdatedDate(new Date());
				itemForm.setUpdatedBy(userDetail);
				result = itemDetailDAO.updateItemDetail(itemForm);
			} else {
				itemForm.setSubmittedDate(new Date());
				itemForm.setSubmittedBy(userDetail);
				result = itemDetailDAO.addItemDetail(itemForm);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> uploadItemFile(MultipartFile fileUploaded, HttpSession session, int confirmUploadId)
			throws Exception {
		// confirmUploadId- Default value -1, it implies that Excel is being for
		// the first time, If 1 It means user accepts that even if problem
		// exists in present excel, continue with Save operation.
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		/*HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<ExcelErrorDetails> errorList = new ArrayList<ExcelErrorDetails>();
		ArrayList<ItemDB> itemList = new ArrayList<ItemDB>();

		Utility util = new Utility();
		UploadFileUtility upUltil = new UploadFileUtility();
		ItemDbHelper itemDbHelper = new ItemDbHelper();

		boolean hasErrorOccured = false;
		boolean isValidSchema;
		int i;

		try {
			UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");

			if (!fileUploaded.isEmpty()) {
				Workbook workBook = upUltil.readExcelFileFromMultipart(fileUploaded);
				if (workBook == null) {
					return upUltil.getErrorMessage(ConstantUtil.ERROR_FILE_READING_ERROR);
				}
				// Schema Validation - Checks Whether Row header name is same as we
				// specified.
				// Validate schema only when the document is uploaded and not when
				// user sends confirmation
				if (confirmUploadId != 1) {
					isValidSchema = upUltil.isSchemaValid(workBook, ConstantUtil.ITEM_DB_SHEETNUM,
							ConstantUtil.ITEM_DB_HEADER_ROWNUM, ConstantUtil.ITEM_DB_EXCEL_FORMAT);
					if (!isValidSchema) {
						return upUltil.getErrorMessage(ConstantUtil.ERROR_HEADER_VALIDATION_ERROR);
					}
				}
				// Schema Validation Ends
				// Check Validation For Confirmation from user-- Pending

				// Data Validation Starts
				Sheet sheet = workBook.getSheetAt(ConstantUtil.ITEM_DB_SHEETNUM);
				for (i = 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					if (row == null) {
						continue;
					}
					Map<String, Object> rowValidationResult = new HashMap<String, Object>();
					// if (confirmUploadId != 1) {
					rowValidationResult = itemDbHelper.validateRowDataAndFetchBean(row, userDetail);
					if (rowValidationResult.get("itemDbBean") == null) {
						errorList.addAll((Collection<? extends ExcelErrorDetails>) rowValidationResult.get("errorList"));
						hasErrorOccured = true;
					} else {
						itemList.add((ItemDB) rowValidationResult.get("itemDbBean"));
					}
					// }
				}

				if ((confirmUploadId == 1) || (confirmUploadId != 1 && !hasErrorOccured)) {
					errorList = saveItemList(itemList);
					if (errorList.isEmpty()) {
						errorList = null;
					}
					return util.responseBuilder(errorList);

				} else {

					return util.responseBuilder(errorList);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return resultMap;*/
		return resultMap;
	}

	
	@Override
	public List<ItemDB> getAllItems() throws Exception{
		return itemDetailDAO.getAllItems();
	}
	public ArrayList<ExcelErrorDetails> saveItemList(List<ItemDB> itemList) {
		String itemDAOResult = "";
		UploadFileUtility upUltil = new UploadFileUtility();
		int i = 1;
		ArrayList<ExcelErrorDetails> itemDAOErrorList = new ArrayList<ExcelErrorDetails>();
		try {
			for (ItemDB itemDbObj : itemList) {
				itemDAOResult = "";
				try {
					itemDAOResult = itemDetailDAO.addItemDetail(itemDbObj);
				} catch (Exception e) {
					// e.printStackTrace();
				}
				if (itemDAOResult != null) {
					itemDAOErrorList.add(upUltil.getExcelErrorDetails(i, -1, "Error in saving to database"));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return itemDAOErrorList;
	}

	// These Services will be Called from PD application to fetch the Data from ItemDB , By Vaibhav

	/**
	 * Get List of Category-1 from Item DB
	 * @return List<String> - Containing Unique Category-1 label from Item DB
	 */
	@Override
	public List<String> getCategory1List() throws Exception{
		// TODO Auto-generated method stub
		
		return itemDetailDAO.getCategory1List();
	}

	/**
	 * Get List of Category-2 under given Category-1 from Item DB
	 * @param requestMap
	 * @return List<String> - Containing Unique Category-2 based on the selection
	 */
	@Override
	public List<String> getCategory2List(Map<String, String> selectedCategoryMap) throws Exception {
		// TODO Auto-generated method stub
		
		return itemDetailDAO.getCategory2List(selectedCategoryMap);
	}

	/**
	 * Get List of Category-3 based on Category-1 and 2 selection
	 * @param requestMap
	 * @return List<String> - Containing Unique Category-3 based on the selection
	 */
	@Override
	public List<String> getCategory3List(Map<String, String> selectedCategoryMap) throws Exception{
		// TODO Auto-generated method stub

		return itemDetailDAO.getCategory3List(selectedCategoryMap);
	}

	/**
	 * Get List of Items based on the category selection - 
	 * (This list will containing only ItemId,ItemDescription,BelcoLaborCode,BelcoLaborDesc,BelcoMaterialCode,BelcoMaterialDesc)
	 * @param requestJson (Containing the category selections)
	 * @return List<ItemDB>
	 */
	@Override
	public List<ItemDB> getItemListByCategorySelection(Map<String,String> requestMap) throws Exception{
		// TODO Auto-generated method stub
		
		return itemDetailDAO.getItemListByCategorySelection(requestMap);
	}

	/**
	 * Get List of Items based on the category selection - 
	 * (All bean attribute)
	 * @param requestMap (Containing the category selections)
	 * @return List<ItemDB>
	 */
	@Override
	public List<ItemDB> getDetailedItemListByCategorySelection(Map<String,String> requestMap) throws Exception {
		// TODO Auto-generated method stub
		
		return itemDetailDAO.getDetailedItemListByCategorySelection(requestMap);
	}
	
	/**
	 * Get Item Detail by Item Description
	 * (This list will containing only ItemId,ItemDescription,BelcoLaborCode,BelcoLaborDesc,BelcoMaterialCode,BelcoMaterialDesc)
	 * @param requestMap
	 * @return ItemDB Entity Bean
	 */
	@Override
	public List<ItemDB> getItemByItemDescription(Map<String,String> requestMap) throws Exception {
		// TODO Auto-generated method stub
		
		return itemDetailDAO.getItemByItemDescription(requestMap);
	}
	
	/**
	 * Get Item Detail by Item Description
	 * (All bean attribute)
	 * @param requestMap
	 * @return ItemDB Entity Bean
	 */
	@Override
	public List<ItemDB> getDetailedItemByItemDescription(Map<String,String> requestMap) throws Exception {
		// TODO Auto-generated method stub
		
		return itemDetailDAO.getDetailedItemByItemDescription(requestMap);
	}
	// End 
}
