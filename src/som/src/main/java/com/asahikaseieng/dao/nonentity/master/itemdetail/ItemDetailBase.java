/*
 * Created on 2009/06/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemdetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ItemDetailクラス.
 * @author t0011036
 */
public class ItemDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemDetailBase() {
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
	
	/** COLUMNアノテーション itemDivision */
	public static final String itemDivision_COLUMN = "ITEM_DIVISION";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

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

	/** COLUMNアノテーション activeDate */
	public static final String activeDate_COLUMN = "ACTIVE_DATE";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";
	
	/** COLUMNアノテーション salesTaxCategory */
	public static final String salesTaxCategory_COLUMN = "SALES_TAX_CATEGORY";
	
	/** COLUMNアノテーション purchaseTaxCategory */
	public static final String purchaseTaxCategory_COLUMN = "PURCHASE_TAX_CATEGORY";


	/** COLUMNアノテーション parentItemCd */
	public static final String parentItemCd_COLUMN = "PARENT_ITEM_CD";

	/** COLUMNアノテーション lotDivision */
	public static final String lotDivision_COLUMN = "LOT_DIVISION";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal version;

	private String itemName;
	
	private java.math.BigDecimal itemDivision;

	private String otherCompanyCd1;

	private String styleOfPacking;

	private String unitOfOperationManagement;

	private String unitOfStockControl;

	private java.math.BigDecimal kgOfFractionManagement;

	private String unitOfFractionManagement;

	private java.math.BigDecimal kgConversionCoefficient;

	private java.sql.Timestamp activeDate;

	private java.math.BigDecimal typeDivision;
	
	private java.math.BigDecimal salesTaxCategory;
	
	private java.math.BigDecimal purchaseTaxCategory;


	private String parentItemCd;

	private java.math.BigDecimal lotDivision;

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

