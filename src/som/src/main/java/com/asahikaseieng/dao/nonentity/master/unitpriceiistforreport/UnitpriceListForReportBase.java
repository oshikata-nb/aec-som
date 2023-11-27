/*
 * Created on 2009/08/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * UnitpriceListForReportクラス.
 * @author t0011036
 */
public class UnitpriceListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public UnitpriceListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション venderDivision */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション outsideCd */
	public static final String outsideCd_COLUMN = "OUTSIDE_CD";

	/** COLUMNアノテーション outsideName */
	public static final String outsideName_COLUMN = "OUTSIDE_NAME";

	/** COLUMNアノテーション validDate */
	public static final String validDate_COLUMN = "VALID_DATE";

	/** COLUMNアノテーション endDate */
	public static final String endDate_COLUMN = "END_DATE";

	/** COLUMNアノテーション consecutiveNo */
	public static final String consecutiveNo_COLUMN = "CONSECUTIVE_NO";

	/** COLUMNアノテーション quantityFrom */
	public static final String quantityFrom_COLUMN = "QUANTITY_FROM";

	/** COLUMNアノテーション quantityTo */
	public static final String quantityTo_COLUMN = "QUANTITY_TO";

	/** COLUMNアノテーション unitprice */
	public static final String unitprice_COLUMN = "UNITPRICE";

	/** COLUMNアノテーション expenceRatio */
	public static final String expenceRatio_COLUMN = "EXPENCE_RATIO";

	/** COLUMNアノテーション minBuyQuantity */
	public static final String minBuyQuantity_COLUMN = "MIN_BUY_QUANTITY";

	/** COLUMNアノテーション remarks */
	public static final String remarks_COLUMN = "REMARKS";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション vender01Cd */
	public static final String vender01Cd_COLUMN = "VENDER01_CD";

	/** COLUMNアノテーション vender02Cd */
	public static final String vender02Cd_COLUMN = "VENDER02_CD";

	/** COLUMNアノテーション vender03Cd */
	public static final String vender03Cd_COLUMN = "VENDER03_CD";

	/** COLUMNアノテーション vender04Cd */
	public static final String vender04Cd_COLUMN = "VENDER04_CD";

	/** COLUMNアノテーション vender05Cd */
	public static final String vender05Cd_COLUMN = "VENDER05_CD";

	/** COLUMNアノテーション unitpriceDivision */
	public static final String unitpriceDivision_COLUMN = "UNITPRICE_DIVISION";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション materialMakerName */
	public static final String materialMakerName_COLUMN = "MATERIAL_MAKER_NAME";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String venderDivision;

	private String venderCd;

	private String itemCd;

	private java.math.BigDecimal version;

	private String outsideCd;

	private String outsideName;

	private java.sql.Timestamp validDate;

	private java.sql.Timestamp endDate;

	private java.math.BigDecimal consecutiveNo;

	private java.math.BigDecimal quantityFrom;

	private java.math.BigDecimal quantityTo;

	private java.math.BigDecimal unitprice;

	private java.math.BigDecimal expenceRatio;

	private java.math.BigDecimal minBuyQuantity;

	private String remarks;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String vender01Cd;

	private String vender02Cd;

	private String vender03Cd;

	private String vender04Cd;

	private String vender05Cd;

	private String unitpriceDivision;

	private String venderName1;

	private String itemName;

	private String styleOfPacking;

	private String materialMakerName;

	private String inputorName;

	private String updatorName;

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
	 * outsideCd取得.
	 * @return outsideCd
	 */
	public String getOutsideCd() {
		return this.outsideCd;
	}

	/**
	 * outsideCd設定.
	 * @param outsideCd outsideCd
	 */
	public void setOutsideCd(final String outsideCd) {
		this.outsideCd = outsideCd;
	}

	/**
	 * outsideName取得.
	 * @return outsideName
	 */
	public String getOutsideName() {
		return this.outsideName;
	}

	/**
	 * outsideName設定.
	 * @param outsideName outsideName
	 */
	public void setOutsideName(final String outsideName) {
		this.outsideName = outsideName;
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
	 * endDate取得.
	 * @return endDate
	 */
	public java.sql.Timestamp getEndDate() {
		return this.endDate;
	}

	/**
	 * endDate設定.
	 * @param endDate endDate
	 */
	public void setEndDate(final java.sql.Timestamp endDate) {
		this.endDate = endDate;
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
	 * expenceRatio取得.
	 * @return expenceRatio
	 */
	public java.math.BigDecimal getExpenceRatio() {
		return this.expenceRatio;
	}

	/**
	 * expenceRatio設定.
	 * @param expenceRatio expenceRatio
	 */
	public void setExpenceRatio(final java.math.BigDecimal expenceRatio) {
		this.expenceRatio = expenceRatio;
	}

	/**
	 * minBuyQuantity取得.
	 * @return minBuyQuantity
	 */
	public java.math.BigDecimal getMinBuyQuantity() {
		return this.minBuyQuantity;
	}

	/**
	 * minBuyQuantity設定.
	 * @param minBuyQuantity minBuyQuantity
	 */
	public void setMinBuyQuantity(final java.math.BigDecimal minBuyQuantity) {
		this.minBuyQuantity = minBuyQuantity;
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
	 * vender01Cd取得.
	 * @return vender01Cd
	 */
	public String getVender01Cd() {
		return this.vender01Cd;
	}

	/**
	 * vender01Cd設定.
	 * @param vender01Cd vender01Cd
	 */
	public void setVender01Cd(final String vender01Cd) {
		this.vender01Cd = vender01Cd;
	}

	/**
	 * vender02Cd取得.
	 * @return vender02Cd
	 */
	public String getVender02Cd() {
		return this.vender02Cd;
	}

	/**
	 * vender02Cd設定.
	 * @param vender02Cd vender02Cd
	 */
	public void setVender02Cd(final String vender02Cd) {
		this.vender02Cd = vender02Cd;
	}

	/**
	 * vender03Cd取得.
	 * @return vender03Cd
	 */
	public String getVender03Cd() {
		return this.vender03Cd;
	}

	/**
	 * vender03Cd設定.
	 * @param vender03Cd vender03Cd
	 */
	public void setVender03Cd(final String vender03Cd) {
		this.vender03Cd = vender03Cd;
	}

	/**
	 * vender04Cd取得.
	 * @return vender04Cd
	 */
	public String getVender04Cd() {
		return this.vender04Cd;
	}

	/**
	 * vender04Cd設定.
	 * @param vender04Cd vender04Cd
	 */
	public void setVender04Cd(final String vender04Cd) {
		this.vender04Cd = vender04Cd;
	}

	/**
	 * vender05Cd取得.
	 * @return vender05Cd
	 */
	public String getVender05Cd() {
		return this.vender05Cd;
	}

	/**
	 * vender05Cd設定.
	 * @param vender05Cd vender05Cd
	 */
	public void setVender05Cd(final String vender05Cd) {
		this.vender05Cd = vender05Cd;
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
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
	}

	/**
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
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

