/*
 * Created on Thu Jan 22 16:04:53 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.itemqueue;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ItemQueueBaseクラス.
 * @author t0011036
 */
public class ItemQueueBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ITEM_QUEUE";

	// /** IDアノテーション itemCd. */
	// public static final String itemCd_ID = "assigned";
	// /** IDアノテーション version. */
	// public static final String version_ID = "assigned";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version. */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション itemType. */
	public static final String itemType_COLUMN = "ITEM_TYPE";

	/** COLUMNアノテーション activeDate. */
	public static final String activeDate_COLUMN = "ACTIVE_DATE";

	/** COLUMNアノテーション status. */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション itemName. */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション itemSubName. */
	public static final String itemSubName_COLUMN = "ITEM_SUB_NAME";

	/** COLUMNアノテーション productDivision. */
	public static final String productDivision_COLUMN = "PRODUCT_DIVISION";

	/** COLUMNアノテーション articleDivision. */
	public static final String articleDivision_COLUMN = "ARTICLE_DIVISION";

	/** COLUMNアノテーション purchaseDivision. */
	public static final String purchaseDivision_COLUMN = "PURCHASE_DIVISION";

	/** COLUMNアノテーション parentItemCd. */
	public static final String parentItemCd_COLUMN = "PARENT_ITEM_CD";

	/** COLUMNアノテーション itemCategory. */
	public static final String itemCategory_COLUMN = "ITEM_CATEGORY";

	/** COLUMNアノテーション phantomDivision. */
	public static final String phantomDivision_COLUMN = "PHANTOM_DIVISION";

	/** COLUMNアノテーション spotDivision. */
	public static final String spotDivision_COLUMN = "SPOT_DIVISION";

	/** COLUMNアノテーション stockDivision. */
	public static final String stockDivision_COLUMN = "STOCK_DIVISION";

	/** COLUMNアノテーション bulkDivision. */
	public static final String bulkDivision_COLUMN = "BULK_DIVISION";

	/** COLUMNアノテーション defaultLocation. */
	public static final String defaultLocation_COLUMN = "DEFAULT_LOCATION";

	/** COLUMNアノテーション costDivision. */
	public static final String costDivision_COLUMN = "COST_DIVISION";

	/** COLUMNアノテーション lotDivision. */
	public static final String lotDivision_COLUMN = "LOT_DIVISION";

	/** COLUMNアノテーション newFlg. */
	public static final String newFlg_COLUMN = "NEW_FLG";

	/** COLUMNアノテーション researchItem. */
	public static final String researchItem_COLUMN = "RESEARCH_ITEM";

	/** COLUMNアノテーション shipperDivision. */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";

	/** COLUMNアノテーション typeDivision. */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";
	
	/** COLUMNアノテーション salesTaxCategory */
	public static final String salesTaxCategory_COLUMN = "SALES_TAX_CATEGORY";
	
	/** COLUMNアノテーション purchaseTaxCategory */
	public static final String purchaseTaxCategory_COLUMN = "PURCHASE_TAX_CATEGORY";
	
	/** COLUMNアノテーション itemDivision */
	public static final String itemDivision_COLUMN = "ITEM_DIVISION";

	/** COLUMNアノテーション otherCompanyCd1. */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション orderLocation. */
	public static final String orderLocation_COLUMN = "ORDER_LOCATION";

	/** COLUMNアノテーション styleOfPacking. */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション numberOfInsertions. */
	public static final String numberOfInsertions_COLUMN = "NUMBER_OF_INSERTIONS";

	/** COLUMNアノテーション allUpWeight. */
	public static final String allUpWeight_COLUMN = "ALL_UP_WEIGHT";

	/** COLUMNアノテーション unitOfOperationManagement. */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション unitOfStockControl. */
	public static final String unitOfStockControl_COLUMN = "UNIT_OF_STOCK_CONTROL";

	/** COLUMNアノテーション kgOfFractionManagement. */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション unitOfFractionManagement. */
	public static final String unitOfFractionManagement_COLUMN = "UNIT_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション kgConversionCoefficient. */
	public static final String kgConversionCoefficient_COLUMN = "KG_CONVERSION_COEFFICIENT";

	/** COLUMNアノテーション waterDivision. */
	public static final String waterDivision_COLUMN = "WATER_DIVISION";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション developmentRequestNo. */
	public static final String developmentRequestNo_COLUMN = "DEVELOPMENT_REQUEST_NO";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal version;

	private java.math.BigDecimal itemType;

	private java.sql.Timestamp activeDate;

	private java.math.BigDecimal status;

	private String itemName;

	private String itemSubName;

	private java.math.BigDecimal productDivision;

	private java.math.BigDecimal articleDivision;

	private java.math.BigDecimal purchaseDivision;

	private String parentItemCd;

	private String itemCategory;

	private java.math.BigDecimal phantomDivision;

	private java.math.BigDecimal spotDivision;

	private java.math.BigDecimal stockDivision;

	private java.math.BigDecimal bulkDivision;

	private String defaultLocation;

	private java.math.BigDecimal costDivision;

	private java.math.BigDecimal lotDivision;

	private java.math.BigDecimal newFlg;

	private java.math.BigDecimal researchItem;

	private java.math.BigDecimal shipperDivision;

	private java.math.BigDecimal typeDivision;
	
	private java.math.BigDecimal salesTaxCategory;
	
	private java.math.BigDecimal purchaseTaxCategory;
	
	private java.math.BigDecimal itemDivision;

	private String otherCompanyCd1;

	private String orderLocation;

	private String styleOfPacking;

	private java.math.BigDecimal numberOfInsertions;

	private java.math.BigDecimal allUpWeight;

	private String unitOfOperationManagement;

	private String unitOfStockControl;

	private java.math.BigDecimal kgOfFractionManagement;

	private String unitOfFractionManagement;

	private java.math.BigDecimal kgConversionCoefficient;

	private java.math.BigDecimal waterDivision;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String developmentRequestNo;

	//
	// インスタンスメソッド
	//

	/**
	 * itemCd取得.
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * version取得.
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * version設定.
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
	}

	/**
	 * itemType取得.
	 * @return itemType
	 */
	public java.math.BigDecimal getItemType() {
		return this.itemType;
	}

	/**
	 * itemType設定.
	 * @param itemType itemType
	 */
	public void setItemType(final java.math.BigDecimal itemType) {
		this.itemType = itemType;
	}

	/**
	 * activeDate取得.
	 * @return activeDate
	 */
	public java.sql.Timestamp getActiveDate() {
		return this.activeDate;
	}

	/**
	 * activeDate設定.
	 * @param activeDate activeDate
	 */
	public void setActiveDate(final java.sql.Timestamp activeDate) {
		this.activeDate = activeDate;
	}

	/**
	 * status取得.
	 * @return status
	 */
	public java.math.BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * status設定.
	 * @param status status
	 */
	public void setStatus(final java.math.BigDecimal status) {
		this.status = status;
	}

	/**
	 * itemName取得.
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * itemSubName取得.
	 * @return itemSubName
	 */
	public String getItemSubName() {
		return this.itemSubName;
	}

	/**
	 * itemSubName設定.
	 * @param itemSubName itemSubName
	 */
	public void setItemSubName(final String itemSubName) {
		this.itemSubName = itemSubName;
	}

	/**
	 * productDivision取得.
	 * @return productDivision
	 */
	public java.math.BigDecimal getProductDivision() {
		return this.productDivision;
	}

	/**
	 * productDivision設定.
	 * @param productDivision productDivision
	 */
	public void setProductDivision(final java.math.BigDecimal productDivision) {
		this.productDivision = productDivision;
	}

	/**
	 * articleDivision取得.
	 * @return articleDivision
	 */
	public java.math.BigDecimal getArticleDivision() {
		return this.articleDivision;
	}

	/**
	 * articleDivision設定.
	 * @param articleDivision articleDivision
	 */
	public void setArticleDivision(final java.math.BigDecimal articleDivision) {
		this.articleDivision = articleDivision;
	}

	/**
	 * purchaseDivision取得.
	 * @return purchaseDivision
	 */
	public java.math.BigDecimal getPurchaseDivision() {
		return this.purchaseDivision;
	}

	/**
	 * purchaseDivision設定.
	 * @param purchaseDivision purchaseDivision
	 */
	public void setPurchaseDivision(final java.math.BigDecimal purchaseDivision) {
		this.purchaseDivision = purchaseDivision;
	}

	/**
	 * parentItemCd取得.
	 * @return parentItemCd
	 */
	public String getParentItemCd() {
		return this.parentItemCd;
	}

	/**
	 * parentItemCd設定.
	 * @param parentItemCd parentItemCd
	 */
	public void setParentItemCd(final String parentItemCd) {
		this.parentItemCd = parentItemCd;
	}

	/**
	 * itemCategory取得.
	 * @return itemCategory
	 */
	public String getItemCategory() {
		return this.itemCategory;
	}

	/**
	 * itemCategory設定.
	 * @param itemCategory itemCategory
	 */
	public void setItemCategory(final String itemCategory) {
		this.itemCategory = itemCategory;
	}

	/**
	 * phantomDivision取得.
	 * @return phantomDivision
	 */
	public java.math.BigDecimal getPhantomDivision() {
		return this.phantomDivision;
	}

	/**
	 * phantomDivision設定.
	 * @param phantomDivision phantomDivision
	 */
	public void setPhantomDivision(final java.math.BigDecimal phantomDivision) {
		this.phantomDivision = phantomDivision;
	}

	/**
	 * spotDivision取得.
	 * @return spotDivision
	 */
	public java.math.BigDecimal getSpotDivision() {
		return this.spotDivision;
	}

	/**
	 * spotDivision設定.
	 * @param spotDivision spotDivision
	 */
	public void setSpotDivision(final java.math.BigDecimal spotDivision) {
		this.spotDivision = spotDivision;
	}

	/**
	 * stockDivision取得.
	 * @return stockDivision
	 */
	public java.math.BigDecimal getStockDivision() {
		return this.stockDivision;
	}

	/**
	 * stockDivision設定.
	 * @param stockDivision stockDivision
	 */
	public void setStockDivision(final java.math.BigDecimal stockDivision) {
		this.stockDivision = stockDivision;
	}

	/**
	 * bulkDivision取得.
	 * @return bulkDivision
	 */
	public java.math.BigDecimal getBulkDivision() {
		return this.bulkDivision;
	}

	/**
	 * bulkDivision設定.
	 * @param bulkDivision bulkDivision
	 */
	public void setBulkDivision(final java.math.BigDecimal bulkDivision) {
		this.bulkDivision = bulkDivision;
	}

	/**
	 * defaultLocation取得.
	 * @return defaultLocation
	 */
	public String getDefaultLocation() {
		return this.defaultLocation;
	}

	/**
	 * defaultLocation設定.
	 * @param defaultLocation defaultLocation
	 */
	public void setDefaultLocation(final String defaultLocation) {
		this.defaultLocation = defaultLocation;
	}

	/**
	 * costDivision取得.
	 * @return costDivision
	 */
	public java.math.BigDecimal getCostDivision() {
		return this.costDivision;
	}

	/**
	 * costDivision設定.
	 * @param costDivision costDivision
	 */
	public void setCostDivision(final java.math.BigDecimal costDivision) {
		this.costDivision = costDivision;
	}

	/**
	 * lotDivision取得.
	 * @return lotDivision
	 */
	public java.math.BigDecimal getLotDivision() {
		return this.lotDivision;
	}

	/**
	 * lotDivision設定.
	 * @param lotDivision lotDivision
	 */
	public void setLotDivision(final java.math.BigDecimal lotDivision) {
		this.lotDivision = lotDivision;
	}

	/**
	 * newFlg取得.
	 * @return newFlg
	 */
	public java.math.BigDecimal getNewFlg() {
		return this.newFlg;
	}

	/**
	 * newFlg設定.
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final java.math.BigDecimal newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * researchItem取得.
	 * @return researchItem
	 */
	public java.math.BigDecimal getResearchItem() {
		return this.researchItem;
	}

	/**
	 * researchItem設定.
	 * @param researchItem researchItem
	 */
	public void setResearchItem(final java.math.BigDecimal researchItem) {
		this.researchItem = researchItem;
	}

	/**
	 * shipperDivision取得.
	 * @return shipperDivision
	 */
	public java.math.BigDecimal getShipperDivision() {
		return this.shipperDivision;
	}

	/**
	 * shipperDivision設定.
	 * @param shipperDivision shipperDivision
	 */
	public void setShipperDivision(final java.math.BigDecimal shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * typeDivision取得.
	 * @return typeDivision
	 */
	public java.math.BigDecimal getTypeDivision() {
		return this.typeDivision;
	}

	/**
	 * typeDivision設定.
	 * @param typeDivision typeDivision
	 */
	public void setTypeDivision(final java.math.BigDecimal typeDivision) {
		this.typeDivision = typeDivision;
	}
	
	/**
	 * salesTaxCategory取得.
	 * @return salesTaxCategory
	 */
	public java.math.BigDecimal getSalesTaxCategory() {
		return this.salesTaxCategory;
	}

	/**
	 * salesTaxCategory設定.
	 * @param salesTaxCategory salesTaxCategory
	 */
	public void setSalesTaxCategory(final java.math.BigDecimal salesTaxCategory) {
		this.salesTaxCategory = salesTaxCategory;
	}
	
	/**
	 * purchaseTaxCategory取得.
	 * @return purchaseTaxCategory
	 */
	public java.math.BigDecimal getPurchaseTaxCategory() {
		return this.purchaseTaxCategory;
	}

	/**
	 * purchaseTaxCategory設定.
	 * @param purchaseTaxCategory purchaseTaxCategory
	 */
	public void setPurchaseTaxCategory(final java.math.BigDecimal purchaseTaxCategory) {
		this.purchaseTaxCategory = purchaseTaxCategory;
	}

	/**
	 * itemDivisionを取得します。
	 * @return itemDivision
	 */
	public java.math.BigDecimal getItemDivision() {
		return itemDivision;
	}

	/**
	 * itemDivisionを設定します。
	 * @param itemDivision itemDivision
	 */
	public void setItemDivision(java.math.BigDecimal itemDivision) {
		this.itemDivision = itemDivision;
	}

	/**
	 * otherCompanyCd1取得.
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1設定.
	 * @param otherCompanyCd1 otherCompanyCd1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * orderLocation取得.
	 * @return orderLocation
	 */
	public String getOrderLocation() {
		return this.orderLocation;
	}

	/**
	 * orderLocation設定.
	 * @param orderLocation orderLocation
	 */
	public void setOrderLocation(final String orderLocation) {
		this.orderLocation = orderLocation;
	}

	/**
	 * styleOfPacking取得.
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * styleOfPacking設定.
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * numberOfInsertions取得.
	 * @return numberOfInsertions
	 */
	public java.math.BigDecimal getNumberOfInsertions() {
		return this.numberOfInsertions;
	}

	/**
	 * numberOfInsertions設定.
	 * @param numberOfInsertions numberOfInsertions
	 */
	public void setNumberOfInsertions(
			final java.math.BigDecimal numberOfInsertions) {
		this.numberOfInsertions = numberOfInsertions;
	}

	/**
	 * allUpWeight取得.
	 * @return allUpWeight
	 */
	public java.math.BigDecimal getAllUpWeight() {
		return this.allUpWeight;
	}

	/**
	 * allUpWeight設定.
	 * @param allUpWeight allUpWeight
	 */
	public void setAllUpWeight(final java.math.BigDecimal allUpWeight) {
		this.allUpWeight = allUpWeight;
	}

	/**
	 * unitOfOperationManagement取得.
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return this.unitOfOperationManagement;
	}

	/**
	 * unitOfOperationManagement設定.
	 * @param unitOfOperationManagement unitOfOperationManagement
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * unitOfStockControl取得.
	 * @return unitOfStockControl
	 */
	public String getUnitOfStockControl() {
		return this.unitOfStockControl;
	}

	/**
	 * unitOfStockControl設定.
	 * @param unitOfStockControl unitOfStockControl
	 */
	public void setUnitOfStockControl(final String unitOfStockControl) {
		this.unitOfStockControl = unitOfStockControl;
	}

	/**
	 * kgOfFractionManagement取得.
	 * @return kgOfFractionManagement
	 */
	public java.math.BigDecimal getKgOfFractionManagement() {
		return this.kgOfFractionManagement;
	}

	/**
	 * kgOfFractionManagement設定.
	 * @param kgOfFractionManagement kgOfFractionManagement
	 */
	public void setKgOfFractionManagement(
			final java.math.BigDecimal kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * unitOfFractionManagement取得.
	 * @return unitOfFractionManagement
	 */
	public String getUnitOfFractionManagement() {
		return this.unitOfFractionManagement;
	}

	/**
	 * unitOfFractionManagement設定.
	 * @param unitOfFractionManagement unitOfFractionManagement
	 */
	public void setUnitOfFractionManagement(
			final String unitOfFractionManagement) {
		this.unitOfFractionManagement = unitOfFractionManagement;
	}

	/**
	 * kgConversionCoefficient取得.
	 * @return kgConversionCoefficient
	 */
	public java.math.BigDecimal getKgConversionCoefficient() {
		return this.kgConversionCoefficient;
	}

	/**
	 * kgConversionCoefficient設定.
	 * @param kgConversionCoefficient kgConversionCoefficient
	 */
	public void setKgConversionCoefficient(
			final java.math.BigDecimal kgConversionCoefficient) {
		this.kgConversionCoefficient = kgConversionCoefficient;
	}

	/**
	 * waterDivision取得.
	 * @return waterDivision
	 */
	public java.math.BigDecimal getWaterDivision() {
		return this.waterDivision;
	}

	/**
	 * waterDivision設定.
	 * @param waterDivision waterDivision
	 */
	public void setWaterDivision(final java.math.BigDecimal waterDivision) {
		this.waterDivision = waterDivision;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * developmentRequestNo取得.
	 * @return developmentRequestNo
	 */
	public String getDevelopmentRequestNo() {
		return this.developmentRequestNo;
	}

	/**
	 * developmentRequestNo設定.
	 * @param developmentRequestNo developmentRequestNo
	 */
	public void setDevelopmentRequestNo(final String developmentRequestNo) {
		this.developmentRequestNo = developmentRequestNo;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
