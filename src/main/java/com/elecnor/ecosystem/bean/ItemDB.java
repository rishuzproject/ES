package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.google.gson.annotations.Expose;

/**
 * @author Vaibhav 
 * The persistent entity for the item_db database table.
 * @Vaibhav
 * Modifing Bi-Direction Mappings and Expose for the unwanted attributes.
 */

@Entity
@Table(name = "ITEM_DB")
public class ItemDB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ITEM_ID")  
	@Expose
	private Long itemId;

	@Expose
	@Column(name = "CATEGORY_LEVEL_1")
	@NotBlank(message = "Category Level 1 is a Mandatory Field")
	@Size(max = 200, message = "Category Level 1 should not exceed 200 character limit")
	private String categoryLevel1;

	@Expose
	@Column(name = "CATEGORY_LEVEL_2")
	@Size(max = 200, message = "Category Level 2 should not exceed 200 character limit")
	private String categoryLevel2;

	@Expose
	@Column(name = "CATEGORY_LEVEL_3")
	@Size(max = 200, message = "Category Level 3 should not exceed 200 character limit")
	private String categoryLevel3;

	@Expose
	@Column(name = "ITEM_DESCRIPTION")
	@NotBlank(message = "Item Description is a Mandatory Field")
	@Size(max = 200, message = "Item Description should not exceed 200 character limit")
	private String itemDescription;

	@Expose
	@Column(name = "PRICE_CODE")
	@Size(max = 100, message = "Price Code should not exceed 200 character limit")
	private String priceCode;

	@Expose
	@Column(name = "MATERIAL_PRICE")
	@Digits(integer = 20, fraction = 3, message = "Material Price should only have numerical values upto 3 decimal places")
	private Double materialPrice;

	@Expose
	@Column(name = "MATERIAL_DISCOUNT")
	@Digits(integer = 20, fraction = 3, message = "Material Discount should only have numerical values upto 3 decimal places")
	private Double materialDiscount;

	@Expose
	@Column(name = "MATERIAL_NET_PRICE")
	@Digits(integer = 20, fraction = 3, message = "Material Net Cost should only have numerical values upto 3 decimal places")
	private Double materialNetPrice;

	@Expose
	@Column(name = "BASED_ON")
	@Size(max = 5, message = "Based On field should not exceed 5 character limit")
	private String basedOn;

	@Expose
	@Column(name = "BELCO_LABOR_CODE")
	@Size(max = 45, message = "Company Labor Code should not exceed 45 character limit")
	private String belcoLaborCode;

	@Expose
	@Column(name = "BELCO_LABOR_DESCRIPTION")
	@Size(max = 100, message = "Company Labor Description should not exceed 100 character limit")
	private String belcoLaborDescription;

	@Expose
	@Column(name = "BELCO_MATERIAL_CODE")
	@Size(max = 45, message = "Company Material Code should not exceed 45 character limit")
	private String belcoMaterialCode;

	@Expose
	@Column(name = "BELCO_MATERIAL_DESCRIPTION")
	@Size(max = 100, message = "Company Material Description should not exceed 100 character limit")
	private String belcoMaterialDescription;

	@Expose
	@Column(name = "MANUFACTURER_NAME")
	@Size(max = 45, message = "Manufacturer Name should not exceed 45 character limit")
	private String manufacturerName;

	@Expose
	@Column(name = "CATALOG_NUMBER")
	@Size(max = 45, message = "Catalog Number should not exceed 45 character limit")
	private String catalogNumber;

	@Expose
	@Column(name = "NET_CONDITION")
	@Size(max = 45, message = "Net Condition field should not exceed 45 character limit")
	private String netCondition;

	@Expose
	@Column(name = "MATERIAL_CONDITION")
	@Size(max = 45, message = "Material Condition field should not exceed 45 character limit")
	private String materialCondition;

	@Expose
	@Column(name = "MATERIAL_ADJ_1")
	@Digits(integer = 20, fraction = 3, message = "Price Factor should only have numerical values upto 3 decimal places")
	private Double materialAdj1;

	@Expose
	@Column(name = "MATERIAL_ADJ_2")
	@Digits(integer = 20, fraction = 3, message = "Price Factor should only have numerical values upto 3 decimal places")
	private Double materialAdj2;

	@Expose
	@Column(name = "MATERIAL_UNIT")
	@Size(max = 45, message = "Material Unit field should not exceed 45 character limit")
	private String materialUnit;

	@Expose
	@Column(name = "PRICE_FACTOR")
	@Digits(integer = 20, fraction = 3, message = "Price Factor should only have numerical values upto 3 decimal places")
	private Double priceFactor;

	@Expose
	@Column(name = "LABOR_UNIT")
	@Size(max = 45, message = "Labor Unit field should not exceed 45 character limit")
	private String laborUnit;

	@Expose
	@Column(name = "ESTIMATING_LEVEL")
	@Digits(integer = 20, fraction = 3, message = "Estimating Level should only have numerical values upto 3 decimal places")
	private Double estimatingLevel;

	@Expose
	@Column(name = "COL_2_LABOR")
	@Digits(integer = 20, fraction = 3, message = "Col 2 Labor should only have numerical values upto 3 decimal places")
	private Double col2Labor;

	@Expose
	@Column(name = "COL_3_LABOR")
	@Digits(integer = 20, fraction = 3, message = "Col 3 Labor should only have numerical values upto 3 decimal places")
	private Double col3Labor;

	@Expose
	@Column(name = "NECA_1")
	@Digits(integer = 20, fraction = 3, message = "NECA 1 should only have numerical values upto 3 decimal places")
	private Double neca1;

	@Expose
	@Column(name = "NECA_2")
	@Digits(integer = 20, fraction = 3, message = "NECA 2 should only have numerical values upto 3 decimal places")
	private Double neca2;

	@Expose
	@Column(name = "NECA_3")
	@Digits(integer = 20, fraction = 3, message = "NECA 3 should only have numerical values upto 3 decimal places")
	private Double neca3;

	@Expose
	@Column(name = "LABOR_CONDITION")
	@Size(max = 45, message = "Labor Condition field should not exceed 45 character limit")
	private String laborCondition;

	@Expose
	@Column(name = "LABOR_FACTORING")
	@Size(max = 45, message = "Labor Factoring field should not exceed 45 character limit")
	private String laborFactoring;

	@Expose
	@Column(name = "LABOR_CODE")
	@Size(max = 45, message = "Labor Code field should not exceed 45 character limit")
	private String laborCode;

	@Expose
	@Column(name = "WEIGHT_UNIT")
	@Size(max = 45, message = "Weight Unit field should not exceed 45 character limit")
	private String weightUnit;

	@Expose
	@Column(name = "WEIGHT")
	@Digits(integer = 20, fraction = 3, message = "Weight should only have numerical values upto 3 decimal places")
	private Double weight;

	@Expose
	@Column(name = "SUPPLIER_NAME")
	@Size(max = 45, message = "Supplier Name should not exceed 45 character limit")
	private String supplierName;

	@Expose
	@Column(name = "SUPPLIER_CODE")
	@Size(max = 45, message = "Supplier Code should not exceed 45 character limit")
	private String supplierCode;

	@Expose
	@Column(name = "ITEM_REFERENCES")
	@Size(max = 45, message = "Reference field should not exceed 45 character limit")
	private String reference;

	@Expose
	@Column(name = "UNIVERSAL_ITEM_CODE")
	@Size(max = 45, message = "Universal Item Code should not exceed 45 character limit")
	private String universalItemCode;

	@Expose
	@Column(name = "QUICK_TAKEOFF_CODE")
	@Size(max = 45, message = "Quick Take-off Code field should not exceed 45 character limit")
	private String quickTakeoffCode;

	@Expose
	@Column(name = "ACTUAL_LABOR")
	@Digits(integer = 20, fraction = 3, message = "Actual Labor should only have numerical values upto 3 decimal places")
	private Double actualLabor;

	// No Need to add a validation here, since the default value of this coulmn
	// is "ACTIVE". This field will not be added in the bulk upload template.
	@Column(name = "ITEM_STATUS")
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	// Uni-directional many-to-one association to DomainDetail
	// This field will be removed under the final ecosystem deployment.
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	/**
	 * 
	 */
	public ItemDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param itemDescription
	 * @param materialNetPrice
	 * @param belcoLaborCode
	 * @param belcoLaborDescription
	 * @param belcoMaterialCode
	 * @param belcoMaterialDescription
	 */
	public ItemDB(Long itemId,String itemDescription, Double materialNetPrice,
			String belcoLaborCode, String belcoLaborDescription,
			String belcoMaterialCode, String belcoMaterialDescription) {
		super();
		this.itemId=itemId;
		this.itemDescription = itemDescription;
		this.materialNetPrice = materialNetPrice;
		this.belcoLaborCode = belcoLaborCode;
		this.belcoLaborDescription = belcoLaborDescription;
		this.belcoMaterialCode = belcoMaterialCode;
		this.belcoMaterialDescription = belcoMaterialDescription;
	}
	
	/**
	 * @return the itemId
	 */
	public Long getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the categoryLevel1
	 */
	public String getCategoryLevel1() {
		return categoryLevel1;
	}

	/**
	 * @param categoryLevel1
	 *            the categoryLevel1 to set
	 */
	public void setCategoryLevel1(String categoryLevel1) {
		this.categoryLevel1 = categoryLevel1;
	}

	/**
	 * @return the categoryLevel2
	 */
	public String getCategoryLevel2() {
		return categoryLevel2;
	}

	/**
	 * @param categoryLevel2
	 *            the categoryLevel2 to set
	 */
	public void setCategoryLevel2(String categoryLevel2) {
		this.categoryLevel2 = categoryLevel2;
	}

	/**
	 * @return the categoryLevel3
	 */
	public String getCategoryLevel3() {
		return categoryLevel3;
	}

	/**
	 * @param categoryLevel3
	 *            the categoryLevel3 to set
	 */
	public void setCategoryLevel3(String categoryLevel3) {
		this.categoryLevel3 = categoryLevel3;
	}

	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * @param itemDescription
	 *            the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * @return the priceCode
	 */
	public String getPriceCode() {
		return priceCode;
	}

	/**
	 * @param priceCode
	 *            the priceCode to set
	 */
	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}

	/**
	 * @return the materialPrice
	 */
	public Double getMaterialPrice() {
		return materialPrice;
	}

	/**
	 * @param materialPrice
	 *            the materialPrice to set
	 */
	public void setMaterialPrice(Double materialPrice) {
		this.materialPrice = materialPrice;
	}

	/**
	 * @return the materialDiscount
	 */
	public Double getMaterialDiscount() {
		return materialDiscount;
	}

	/**
	 * @param materialDiscount
	 *            the materialDiscount to set
	 */
	public void setMaterialDiscount(Double materialDiscount) {
		this.materialDiscount = materialDiscount;
	}

	/**
	 * @return the materialNetPrice
	 */
	public Double getMaterialNetPrice() {
		return materialNetPrice;
	}

	/**
	 * @param materialNetPrice
	 *            the materialNetPrice to set
	 */
	public void setMaterialNetPrice(Double materialNetPrice) {
		this.materialNetPrice = materialNetPrice;
	}

	/**
	 * @return the basedOn
	 */
	public String getBasedOn() {
		return basedOn;
	}

	/**
	 * @param basedOn
	 *            the basedOn to set
	 */
	public void setBasedOn(String basedOn) {
		this.basedOn = basedOn;
	}

	/**
	 * @return the belcoLaborCode
	 */
	public String getBelcoLaborCode() {
		return belcoLaborCode;
	}

	/**
	 * @param belcoLaborCode
	 *            the belcoLaborCode to set
	 */
	public void setBelcoLaborCode(String belcoLaborCode) {
		this.belcoLaborCode = belcoLaborCode;
	}

	/**
	 * @return the belcoLaborDescription
	 */
	public String getBelcoLaborDescription() {
		return belcoLaborDescription;
	}

	/**
	 * @param belcoLaborDescription
	 *            the belcoLaborDescription to set
	 */
	public void setBelcoLaborDescription(String belcoLaborDescription) {
		this.belcoLaborDescription = belcoLaborDescription;
	}

	/**
	 * @return the belcoMaterialCode
	 */
	public String getBelcoMaterialCode() {
		return belcoMaterialCode;
	}

	/**
	 * @param belcoMaterialCode
	 *            the belcoMaterialCode to set
	 */
	public void setBelcoMaterialCode(String belcoMaterialCode) {
		this.belcoMaterialCode = belcoMaterialCode;
	}

	/**
	 * @return the belcoMaterialDescription
	 */
	public String getBelcoMaterialDescription() {
		return belcoMaterialDescription;
	}

	/**
	 * @param belcoMaterialDescription
	 *            the belcoMaterialDescription to set
	 */
	public void setBelcoMaterialDescription(String belcoMaterialDescription) {
		this.belcoMaterialDescription = belcoMaterialDescription;
	}

	/**
	 * @return the manufacturerName
	 */
	public String getManufacturerName() {
		return manufacturerName;
	}

	/**
	 * @param manufacturerName
	 *            the manufacturerName to set
	 */
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	/**
	 * @return the catalogNumber
	 */
	public String getCatalogNumber() {
		return catalogNumber;
	}

	/**
	 * @param catalogNumber
	 *            the catalogNumber to set
	 */
	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	/**
	 * @return the netCondition
	 */
	public String getNetCondition() {
		return netCondition;
	}

	/**
	 * @param netCondition
	 *            the netCondition to set
	 */
	public void setNetCondition(String netCondition) {
		this.netCondition = netCondition;
	}

	/**
	 * @return the materialCondition
	 */
	public String getMaterialCondition() {
		return materialCondition;
	}

	/**
	 * @param materialCondition
	 *            the materialCondition to set
	 */
	public void setMaterialCondition(String materialCondition) {
		this.materialCondition = materialCondition;
	}

	/**
	 * @return the materialAdj1
	 */
	public Double getMaterialAdj1() {
		return materialAdj1;
	}

	/**
	 * @param materialAdj1
	 *            the materialAdj1 to set
	 */
	public void setMaterialAdj1(Double materialAdj1) {
		this.materialAdj1 = materialAdj1;
	}

	/**
	 * @return the materialAdj2
	 */
	public Double getMaterialAdj2() {
		return materialAdj2;
	}

	/**
	 * @param materialAdj2
	 *            the materialAdj2 to set
	 */
	public void setMaterialAdj2(Double materialAdj2) {
		this.materialAdj2 = materialAdj2;
	}

	/**
	 * @return the materialUnit
	 */
	public String getMaterialUnit() {
		return materialUnit;
	}

	/**
	 * @param materialUnit
	 *            the materialUnit to set
	 */
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	/**
	 * @return the priceFactor
	 */
	public Double getPriceFactor() {
		return priceFactor;
	}

	/**
	 * @param priceFactor
	 *            the priceFactor to set
	 */
	public void setPriceFactor(Double priceFactor) {
		this.priceFactor = priceFactor;
	}

	/**
	 * @return the laborUnit
	 */
	public String getLaborUnit() {
		return laborUnit;
	}

	/**
	 * @param laborUnit
	 *            the laborUnit to set
	 */
	public void setLaborUnit(String laborUnit) {
		this.laborUnit = laborUnit;
	}

	/**
	 * @return the estimatingLevel
	 */
	public Double getEstimatingLevel() {
		return estimatingLevel;
	}

	/**
	 * @param estimatingLevel
	 *            the estimatingLevel to set
	 */
	public void setEstimatingLevel(Double estimatingLevel) {
		this.estimatingLevel = estimatingLevel;
	}

	/**
	 * @return the col2Labor
	 */
	public Double getCol2Labor() {
		return col2Labor;
	}

	/**
	 * @param col2Labor
	 *            the col2Labor to set
	 */
	public void setCol2Labor(Double col2Labor) {
		this.col2Labor = col2Labor;
	}

	/**
	 * @return the col3Labor
	 */
	public Double getCol3Labor() {
		return col3Labor;
	}

	/**
	 * @param col3Labor
	 *            the col3Labor to set
	 */
	public void setCol3Labor(Double col3Labor) {
		this.col3Labor = col3Labor;
	}

	/**
	 * @return the neca1
	 */
	public Double getNeca1() {
		return neca1;
	}

	/**
	 * @param neca1
	 *            the neca1 to set
	 */
	public void setNeca1(Double neca1) {
		this.neca1 = neca1;
	}

	/**
	 * @return the neca2
	 */
	public Double getNeca2() {
		return neca2;
	}

	/**
	 * @param neca2
	 *            the neca2 to set
	 */
	public void setNeca2(Double neca2) {
		this.neca2 = neca2;
	}

	/**
	 * @return the neca3
	 */
	public Double getNeca3() {
		return neca3;
	}

	/**
	 * @param neca3
	 *            the neca3 to set
	 */
	public void setNeca3(Double neca3) {
		this.neca3 = neca3;
	}

	/**
	 * @return the laborCondition
	 */
	public String getLaborCondition() {
		return laborCondition;
	}

	/**
	 * @param laborCondition
	 *            the laborCondition to set
	 */
	public void setLaborCondition(String laborCondition) {
		this.laborCondition = laborCondition;
	}

	/**
	 * @return the laborFactoring
	 */
	public String getLaborFactoring() {
		return laborFactoring;
	}

	/**
	 * @param laborFactoring
	 *            the laborFactoring to set
	 */
	public void setLaborFactoring(String laborFactoring) {
		this.laborFactoring = laborFactoring;
	}

	/**
	 * @return the laborCode
	 */
	public String getLaborCode() {
		return laborCode;
	}

	/**
	 * @param laborCode
	 *            the laborCode to set
	 */
	public void setLaborCode(String laborCode) {
		this.laborCode = laborCode;
	}

	/**
	 * @return the weightUnit
	 */
	public String getWeightUnit() {
		return weightUnit;
	}

	/**
	 * @param weightUnit
	 *            the weightUnit to set
	 */
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName
	 *            the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return the supplierCode
	 */
	public String getSupplierCode() {
		return supplierCode;
	}

	/**
	 * @param supplierCode
	 *            the supplierCode to set
	 */
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the universalItemCode
	 */
	public String getUniversalItemCode() {
		return universalItemCode;
	}

	/**
	 * @param universalItemCode
	 *            the universalItemCode to set
	 */
	public void setUniversalItemCode(String universalItemCode) {
		this.universalItemCode = universalItemCode;
	}

	/**
	 * @return the quickTakeoffCode
	 */
	public String getQuickTakeoffCode() {
		return quickTakeoffCode;
	}

	/**
	 * @param quickTakeoffCode
	 *            the quickTakeoffCode to set
	 */
	public void setQuickTakeoffCode(String quickTakeoffCode) {
		this.quickTakeoffCode = quickTakeoffCode;
	}

	/**
	 * @return the actualLabor
	 */
	public Double getActualLabor() {
		return actualLabor;
	}

	/**
	 * @param actualLabor
	 *            the actualLabor to set
	 */
	public void setActualLabor(Double actualLabor) {
		this.actualLabor = actualLabor;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the submittedDate
	 */
	public Date getSubmittedDate() {
		return submittedDate;
	}

	/**
	 * @param submittedDate
	 *            the submittedDate to set
	 */
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the domainDetail
	 */
	public DomainDetail getDomainDetail() {
		return domainDetail;
	}

	/**
	 * @param domainDetail
	 *            the domainDetail to set
	 */
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}

	/**
	 * @return the submittedBy
	 */
	public UserDetail getSubmittedBy() {
		return submittedBy;
	}

	/**
	 * @param submittedBy
	 *            the submittedBy to set
	 */
	public void setSubmittedBy(UserDetail submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * @return the updatedBy
	 */
	public UserDetail getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(UserDetail updatedBy) {
		this.updatedBy = updatedBy;
	}

}