/*
 * Created on 2009/09/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatelistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * EstimateListForReportクラス.
 * @author t0011036
 */
public class EstimateListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public EstimateListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション estimateNo */
	public static final String estimateNo_COLUMN = "ESTIMATE_NO";

	/** COLUMNアノテーション estimateInputDate */
	public static final String estimateInputDate_COLUMN = "ESTIMATE_INPUT_DATE";

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderName2 */
	public static final String venderName2_COLUMN = "VENDER_NAME2";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション consecutiveNo */
	public static final String consecutiveNo_COLUMN = "CONSECUTIVE_NO";

	/** COLUMNアノテーション standardUnitPrice */
	public static final String standardUnitPrice_COLUMN = "STANDARD_UNIT_PRICE";

	/** COLUMNアノテーション standardDiscount */
	public static final String standardDiscount_COLUMN = "STANDARD_DISCOUNT";

	/** COLUMNアノテーション specialDiscount */
	public static final String specialDiscount_COLUMN = "SPECIAL_DISCOUNT";

	/** COLUMNアノテーション unitprice */
	public static final String unitprice_COLUMN = "UNITPRICE";

	/** COLUMNアノテーション standardAmount */
	public static final String standardAmount_COLUMN = "STANDARD_AMOUNT";

	/** COLUMNアノテーション matss */
	public static final String matss_COLUMN = "MATSS";

	/** COLUMNアノテーション estimateValidDateFrom */
	public static final String estimateValidDateFrom_COLUMN = "ESTIMATE_VALID_DATE_FROM";

	/** COLUMNアノテーション estimateValidDateTo */
	public static final String estimateValidDateTo_COLUMN = "ESTIMATE_VALID_DATE_TO";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	/** COLUMNアノテーション estimateStatus */
	public static final String estimateStatus_COLUMN = "ESTIMATE_STATUS";

	//
	// インスタンスフィールド
	//

	private String estimateNo;

	private java.sql.Timestamp estimateInputDate;

	private String balanceCd;

	private String venderCd;

	private String venderName1;

	private String venderName2;

	private String venderShortedName;

	private String itemCd;

	private String itemName;

	private String styleOfPacking;

	private String otherCompanyCd1;

	private java.math.BigDecimal consecutiveNo;

	private java.math.BigDecimal standardUnitPrice;

	private java.math.BigDecimal standardDiscount;

	private java.math.BigDecimal specialDiscount;

	private java.math.BigDecimal unitprice;

	private java.math.BigDecimal standardAmount;

	private java.math.BigDecimal matss;

	private java.sql.Timestamp estimateValidDateFrom;

	private java.sql.Timestamp estimateValidDateTo;

	private String remark;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String inputorName;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String updatorName;

	private java.math.BigDecimal estimateStatus;

	//
	// インスタンスメソッド
	//

	/**
	 * estimateNo取得.
	 * @return estimateNo
	 */
	public String getEstimateNo() {
		return this.estimateNo;
	}

	/**
	 * estimateNo設定.
	 * @param estimateNo estimateNo
	 */
	public void setEstimateNo(final String estimateNo) {
		this.estimateNo = estimateNo;
	}

	/**
	 * estimateInputDate取得.
	 * @return estimateInputDate
	 */
	public java.sql.Timestamp getEstimateInputDate() {
		return this.estimateInputDate;
	}

	/**
	 * estimateInputDate設定.
	 * @param estimateInputDate estimateInputDate
	 */
	public void setEstimateInputDate(final java.sql.Timestamp estimateInputDate) {
		this.estimateInputDate = estimateInputDate;
	}

	/**
	 * balanceCd取得.
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return this.balanceCd;
	}

	/**
	 * balanceCd設定.
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
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
	 * venderName2取得.
	 * @return venderName2
	 */
	public String getVenderName2() {
		return this.venderName2;
	}

	/**
	 * venderName2設定.
	 * @param venderName2 venderName2
	 */
	public void setVenderName2(final String venderName2) {
		this.venderName2 = venderName2;
	}

	/**
	 * venderShortedName取得.
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return this.venderShortedName;
	}

	/**
	 * venderShortedName設定.
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
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
	 * standardUnitPrice取得.
	 * @return standardUnitPrice
	 */
	public java.math.BigDecimal getStandardUnitPrice() {
		return this.standardUnitPrice;
	}

	/**
	 * standardUnitPrice設定.
	 * @param standardUnitPrice standardUnitPrice
	 */
	public void setStandardUnitPrice(final java.math.BigDecimal standardUnitPrice) {
		this.standardUnitPrice = standardUnitPrice;
	}

	/**
	 * standardDiscount取得.
	 * @return standardDiscount
	 */
	public java.math.BigDecimal getStandardDiscount() {
		return this.standardDiscount;
	}

	/**
	 * standardDiscount設定.
	 * @param standardDiscount standardDiscount
	 */
	public void setStandardDiscount(final java.math.BigDecimal standardDiscount) {
		this.standardDiscount = standardDiscount;
	}

	/**
	 * specialDiscount取得.
	 * @return specialDiscount
	 */
	public java.math.BigDecimal getSpecialDiscount() {
		return this.specialDiscount;
	}

	/**
	 * specialDiscount設定.
	 * @param specialDiscount specialDiscount
	 */
	public void setSpecialDiscount(final java.math.BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
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
	 * standardAmount取得.
	 * @return standardAmount
	 */
	public java.math.BigDecimal getStandardAmount() {
		return this.standardAmount;
	}

	/**
	 * standardAmount設定.
	 * @param standardAmount standardAmount
	 */
	public void setStandardAmount(final java.math.BigDecimal standardAmount) {
		this.standardAmount = standardAmount;
	}

	/**
	 * matss取得.
	 * @return matss
	 */
	public java.math.BigDecimal getMatss() {
		return this.matss;
	}

	/**
	 * matss設定.
	 * @param matss matss
	 */
	public void setMatss(final java.math.BigDecimal matss) {
		this.matss = matss;
	}

	/**
	 * estimateValidDateFrom取得.
	 * @return estimateValidDateFrom
	 */
	public java.sql.Timestamp getEstimateValidDateFrom() {
		return this.estimateValidDateFrom;
	}

	/**
	 * estimateValidDateFrom設定.
	 * @param estimateValidDateFrom estimateValidDateFrom
	 */
	public void setEstimateValidDateFrom(final java.sql.Timestamp estimateValidDateFrom) {
		this.estimateValidDateFrom = estimateValidDateFrom;
	}

	/**
	 * estimateValidDateTo取得.
	 * @return estimateValidDateTo
	 */
	public java.sql.Timestamp getEstimateValidDateTo() {
		return this.estimateValidDateTo;
	}

	/**
	 * estimateValidDateTo設定.
	 * @param estimateValidDateTo estimateValidDateTo
	 */
	public void setEstimateValidDateTo(final java.sql.Timestamp estimateValidDateTo) {
		this.estimateValidDateTo = estimateValidDateTo;
	}

	/**
	 * remark取得.
	 * @return remark
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * remark設定.
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
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
	 * estimateStatus取得.
	 * @return estimateStatus
	 */
	public java.math.BigDecimal getEstimateStatus() {
		return this.estimateStatus;
	}

	/**
	 * estimateStatus設定.
	 * @param estimateStatus estimateStatus
	 */
	public void setEstimateStatus(final java.math.BigDecimal estimateStatus) {
		this.estimateStatus = estimateStatus;
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

