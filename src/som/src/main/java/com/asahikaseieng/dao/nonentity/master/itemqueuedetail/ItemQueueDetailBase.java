/*
 * Created on 2009/05/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuedetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ItemQueueDetailクラス.
 * @author t0011036
 */
public class ItemQueueDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション itemSubName */
	public static final String itemSubName_COLUMN = "ITEM_SUB_NAME";

	/** COLUMNアノテーション researchItem */
	public static final String researchItem_COLUMN = "RESEARCH_ITEM";

	/** COLUMNアノテーション activeDate */
	public static final String activeDate_COLUMN = "ACTIVE_DATE";

	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";
	
	/** COLUMNアノテーション salesTaxCategory */
	public static final String salesTaxCategory_COLUMN = "SALES_TAX_CATEGORY";
	
	/** COLUMNアノテーション purchaseTaxCategory */
	public static final String purchaseTaxCategory_COLUMN = "PURCHASE_TAX_CATEGORY";
	
	/** COLUMNアノテーション itemDivision */
	public static final String itemDivision_COLUMN = "ITEM_DIVISION";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション numberOfInsertions */
	public static final String numberOfInsertions_COLUMN = "NUMBER_OF_INSERTIONS";

	/** COLUMNアノテーション allUpWeight */
	public static final String allUpWeight_COLUMN = "ALL_UP_WEIGHT";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション unitOfStockControl */
	public static final String unitOfStockControl_COLUMN = "UNIT_OF_STOCK_CONTROL";

	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション unitOfFractionManagement */
	public static final String unitOfFractionManagement_COLUMN = "UNIT_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション kgConversionCoefficient */
	public static final String kgConversionCoefficient_COLUMN = "KG_CONVERSION_COEFFICIENT";

	/** COLUMNアノテーション itemCategory */
	public static final String itemCategory_COLUMN = "ITEM_CATEGORY";

	/** COLUMNアノテーション parentItemCd */
	public static final String parentItemCd_COLUMN = "PARENT_ITEM_CD";

	/** COLUMNアノテーション parentItemName */
	public static final String parentItemName_COLUMN = "PARENT_ITEM_NAME";

	/** COLUMNアノテーション spotDivision */
	public static final String spotDivision_COLUMN = "SPOT_DIVISION";

	/** COLUMNアノテーション stockDivision */
	public static final String stockDivision_COLUMN = "STOCK_DIVISION";

	/** COLUMNアノテーション defaultLocation */
	public static final String defaultLocation_COLUMN = "DEFAULT_LOCATION";

	/** COLUMNアノテーション defaultLocationName */
	public static final String defaultLocationName_COLUMN = "DEFAULT_LOCATION_NAME";

	/** COLUMNアノテーション orderLocation */
	public static final String orderLocation_COLUMN = "ORDER_LOCATION";

	/** COLUMNアノテーション orderLocationName */
	public static final String orderLocationName_COLUMN = "ORDER_LOCATION_NAME";

	/** COLUMNアノテーション lotDivision */
	public static final String lotDivision_COLUMN = "LOT_DIVISION";

	/** COLUMNアノテーション waterDivision */
	public static final String waterDivision_COLUMN = "WATER_DIVISION";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal version;

	private String itemName;

	private String itemSubName;

	private java.math.BigDecimal researchItem;

	private java.sql.Timestamp activeDate;

	private java.math.BigDecimal status;

	private java.math.BigDecimal shipperDivision;

	private java.math.BigDecimal typeDivision;
	
	private java.math.BigDecimal salesTaxCategory;
	
	private java.math.BigDecimal purchaseTaxCategory;
	
	private java.math.BigDecimal itemDivision;

	private String otherCompanyCd1;

	private String styleOfPacking;

	private java.math.BigDecimal numberOfInsertions;

	private java.math.BigDecimal allUpWeight;

	private String unitOfOperationManagement;

	private String unitOfStockControl;

	private java.math.BigDecimal kgOfFractionManagement;

	private String unitOfFractionManagement;

	private java.math.BigDecimal kgConversionCoefficient;

	private String itemCategory;

	private String parentItemCd;

	private String parentItemName;

	private java.math.BigDecimal spotDivision;

	private java.math.BigDecimal stockDivision;

	private String defaultLocation;

	private String defaultLocationName;

	private String orderLocation;

	private String orderLocationName;

	private java.math.BigDecimal lotDivision;

	private java.math.BigDecimal waterDivision;

	private java.sql.Timestamp updateDate;

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
	public void setNumberOfInsertions(final java.math.BigDecimal numberOfInsertions) {
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
	public void setUnitOfOperationManagement(final String unitOfOperationManagement) {
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
	public void setKgOfFractionManagement(final java.math.BigDecimal kgOfFractionManagement) {
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
	public void setUnitOfFractionManagement(final String unitOfFractionManagement) {
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
	public void setKgConversionCoefficient(final java.math.BigDecimal kgConversionCoefficient) {
		this.kgConversionCoefficient = kgConversionCoefficient;
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
	 * parentItemName取得.
	 * @return parentItemName
	 */
	public String getParentItemName() {
		return this.parentItemName;
	}

	/**
	 * parentItemName設定.
	 * @param parentItemName parentItemName
	 */
	public void setParentItemName(final String parentItemName) {
		this.parentItemName = parentItemName;
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
	 * defaultLocationName取得.
	 * @return defaultLocationName
	 */
	public String getDefaultLocationName() {
		return this.defaultLocationName;
	}

	/**
	 * defaultLocationName設定.
	 * @param defaultLocationName defaultLocationName
	 */
	public void setDefaultLocationName(final String defaultLocationName) {
		this.defaultLocationName = defaultLocationName;
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
	 * orderLocationName取得.
	 * @return orderLocationName
	 */
	public String getOrderLocationName() {
		return this.orderLocationName;
	}

	/**
	 * orderLocationName設定.
	 * @param orderLocationName orderLocationName
	 */
	public void setOrderLocationName(final String orderLocationName) {
		this.orderLocationName = orderLocationName;
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

