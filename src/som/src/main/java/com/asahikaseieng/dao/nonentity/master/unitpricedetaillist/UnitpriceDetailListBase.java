/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpricedetaillist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * UnitpriceDetailListクラス.
 * @author t0011036
 */
public class UnitpriceDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public UnitpriceDetailListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション venderDivision */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション unitpriceDivision */
	public static final String unitpriceDivision_COLUMN = "UNITPRICE_DIVISION";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション materialMakerName */
	public static final String materialMakerName_COLUMN = "MATERIAL_MAKER_NAME";

	/** COLUMNアノテーション quantityFrom */
	public static final String quantityFrom_COLUMN = "QUANTITY_FROM";

	/** COLUMNアノテーション quantityTo */
	public static final String quantityTo_COLUMN = "QUANTITY_TO";

	/** COLUMNアノテーション unitprice */
	public static final String unitprice_COLUMN = "UNITPRICE";

	/** COLUMNアノテーション validDate */
	public static final String validDate_COLUMN = "VALID_DATE";

	/** COLUMNアノテーション consecutiveNo */
	public static final String consecutiveNo_COLUMN = "CONSECUTIVE_NO";

	/** COLUMNアノテーション remarks */
	public static final String remarks_COLUMN = "REMARKS";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション smallnumLength */
	public static final String smallnumLength_COLUMN = "SMALLNUM_LENGTH";

	/** COLUMNアノテーション roundDivision */
	public static final String roundDivision_COLUMN = "ROUND_DIVISION";

	/** COLUMNアノテーション unitpriceDivisionName */
	public static final String unitpriceDivisionName_COLUMN = "UNITPRICE_DIVISION_NAME";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション unitDivisionSitanka */
	public static final String unitDivisionSitanka_COLUMN = "UNIT_DIVISION_SITANKA";

	//
	// インスタンスフィールド
	//

	private String venderDivision;

	private String unitpriceDivision;

	private String venderCd;

	private String itemCd;

	private String otherCompanyCd1;

	private String styleOfPacking;

	private String materialMakerName;

	private java.math.BigDecimal quantityFrom;

	private java.math.BigDecimal quantityTo;

	private java.math.BigDecimal unitprice;

	private java.sql.Timestamp validDate;

	private java.math.BigDecimal consecutiveNo;

	private String remarks;

	private java.sql.Timestamp updateDate;

	private String venderName1;

	private String itemName;

	private java.math.BigDecimal version;

	private java.math.BigDecimal smallnumLength;

	private java.math.BigDecimal roundDivision;

	private String unitpriceDivisionName;

	private String unitOfOperationManagement;

	private String unitDivisionSitanka;

	//
	// インスタンスメソッド
	//

	/**
	 * venderDivision取得.
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return this.venderDivision;
	}

	/**
	 * venderDivision設定.
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * unitpriceDivision取得.
	 * @return unitpriceDivision
	 */
	public String getUnitpriceDivision() {
		return this.unitpriceDivision;
	}

	/**
	 * unitpriceDivision設定.
	 * @param unitpriceDivision unitpriceDivision
	 */
	public void setUnitpriceDivision(final String unitpriceDivision) {
		this.unitpriceDivision = unitpriceDivision;
	}

	/**
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

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
	 * materialMakerName取得.
	 * @return materialMakerName
	 */
	public String getMaterialMakerName() {
		return this.materialMakerName;
	}

	/**
	 * materialMakerName設定.
	 * @param materialMakerName materialMakerName
	 */
	public void setMaterialMakerName(final String materialMakerName) {
		this.materialMakerName = materialMakerName;
	}

	/**
	 * quantityFrom取得.
	 * @return quantityFrom
	 */
	public java.math.BigDecimal getQuantityFrom() {
		return this.quantityFrom;
	}

	/**
	 * quantityFrom設定.
	 * @param quantityFrom quantityFrom
	 */
	public void setQuantityFrom(final java.math.BigDecimal quantityFrom) {
		this.quantityFrom = quantityFrom;
	}

	/**
	 * quantityTo取得.
	 * @return quantityTo
	 */
	public java.math.BigDecimal getQuantityTo() {
		return this.quantityTo;
	}

	/**
	 * quantityTo設定.
	 * @param quantityTo quantityTo
	 */
	public void setQuantityTo(final java.math.BigDecimal quantityTo) {
		this.quantityTo = quantityTo;
	}

	/**
	 * unitprice取得.
	 * @return unitprice
	 */
	public java.math.BigDecimal getUnitprice() {
		return this.unitprice;
	}

	/**
	 * unitprice設定.
	 * @param unitprice unitprice
	 */
	public void setUnitprice(final java.math.BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	/**
	 * validDate取得.
	 * @return validDate
	 */
	public java.sql.Timestamp getValidDate() {
		return this.validDate;
	}

	/**
	 * validDate設定.
	 * @param validDate validDate
	 */
	public void setValidDate(final java.sql.Timestamp validDate) {
		this.validDate = validDate;
	}

	/**
	 * consecutiveNo取得.
	 * @return consecutiveNo
	 */
	public java.math.BigDecimal getConsecutiveNo() {
		return this.consecutiveNo;
	}

	/**
	 * consecutiveNo設定.
	 * @param consecutiveNo consecutiveNo
	 */
	public void setConsecutiveNo(final java.math.BigDecimal consecutiveNo) {
		this.consecutiveNo = consecutiveNo;
	}

	/**
	 * remarks取得.
	 * @return remarks
	 */
	public String getRemarks() {
		return this.remarks;
	}

	/**
	 * remarks設定.
	 * @param remarks remarks
	 */
	public void setRemarks(final String remarks) {
		this.remarks = remarks;
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
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
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
	 * smallnumLength取得.
	 * @return smallnumLength
	 */
	public java.math.BigDecimal getSmallnumLength() {
		return this.smallnumLength;
	}

	/**
	 * smallnumLength設定.
	 * @param smallnumLength smallnumLength
	 */
	public void setSmallnumLength(final java.math.BigDecimal smallnumLength) {
		this.smallnumLength = smallnumLength;
	}

	/**
	 * roundDivision取得.
	 * @return roundDivision
	 */
	public java.math.BigDecimal getRoundDivision() {
		return this.roundDivision;
	}

	/**
	 * roundDivision設定.
	 * @param roundDivision roundDivision
	 */
	public void setRoundDivision(final java.math.BigDecimal roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * unitpriceDivisionName取得.
	 * @return unitpriceDivisionName
	 */
	public String getUnitpriceDivisionName() {
		return this.unitpriceDivisionName;
	}

	/**
	 * unitpriceDivisionName設定.
	 * @param unitpriceDivisionName unitpriceDivisionName
	 */
	public void setUnitpriceDivisionName(final String unitpriceDivisionName) {
		this.unitpriceDivisionName = unitpriceDivisionName;
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
	 * unitDivisionSitanka取得.
	 * @return unitDivisionSitanka
	 */
	public String getUnitDivisionSitanka() {
		return this.unitDivisionSitanka;
	}

	/**
	 * unitDivisionSitanka設定.
	 * @param unitDivisionSitanka unitDivisionSitanka
	 */
	public void setUnitDivisionSitanka(final String unitDivisionSitanka) {
		this.unitDivisionSitanka = unitDivisionSitanka;
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

