/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatedelete;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * EstimateDeleteListクラス.
 * @author t0011036
 */
public class EstimateDeleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public EstimateDeleteBase() {
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

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

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

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション estimateStatus */
	public static final String estimateStatus_COLUMN = "ESTIMATE_STATUS";

	//
	// インスタンスフィールド
	//

	private String estimateNo;

	private java.sql.Timestamp estimateInputDate;

	private String balanceCd;

	private String venderCd;

	private String itemCd;

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

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private java.math.BigDecimal estimateStatus;

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
	 * balanceCdを取得します。
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return balanceCd;
	}

	/**
	 * balanceCdを設定します。
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * consecutiveNoを取得します。
	 * @return consecutiveNo
	 */
	public java.math.BigDecimal getConsecutiveNo() {
		return consecutiveNo;
	}

	/**
	 * consecutiveNoを設定します。
	 * @param consecutiveNo consecutiveNo
	 */
	public void setConsecutiveNo(final java.math.BigDecimal consecutiveNo) {
		this.consecutiveNo = consecutiveNo;
	}

	/**
	 * estimateInputDateを取得します。
	 * @return estimateInputDate
	 */
	public java.sql.Timestamp getEstimateInputDate() {
		return estimateInputDate;
	}

	/**
	 * estimateInputDateを設定します。
	 * @param estimateInputDate estimateInputDate
	 */
	public void setEstimateInputDate(final java.sql.Timestamp estimateInputDate) {
		this.estimateInputDate = estimateInputDate;
	}

	/**
	 * estimateNoを取得します。
	 * @return estimateNo
	 */
	public String getEstimateNo() {
		return estimateNo;
	}

	/**
	 * estimateNoを設定します。
	 * @param estimateNo estimateNo
	 */
	public void setEstimateNo(final String estimateNo) {
		this.estimateNo = estimateNo;
	}

	/**
	 * estimateStatusを取得します。
	 * @return estimateStatus
	 */
	public java.math.BigDecimal getEstimateStatus() {
		return estimateStatus;
	}

	/**
	 * estimateStatusを設定します。
	 * @param estimateStatus estimateStatus
	 */
	public void setEstimateStatus(final java.math.BigDecimal estimateStatus) {
		this.estimateStatus = estimateStatus;
	}

	/**
	 * estimateValidDateFromを取得します。
	 * @return estimateValidDateFrom
	 */
	public java.sql.Timestamp getEstimateValidDateFrom() {
		return estimateValidDateFrom;
	}

	/**
	 * estimateValidDateFromを設定します。
	 * @param estimateValidDateFrom estimateValidDateFrom
	 */
	public void setEstimateValidDateFrom(
			final java.sql.Timestamp estimateValidDateFrom) {
		this.estimateValidDateFrom = estimateValidDateFrom;
	}

	/**
	 * estimateValidDateToを取得します。
	 * @return estimateValidDateTo
	 */
	public java.sql.Timestamp getEstimateValidDateTo() {
		return estimateValidDateTo;
	}

	/**
	 * estimateValidDateToを設定します。
	 * @param estimateValidDateTo estimateValidDateTo
	 */
	public void setEstimateValidDateTo(
			final java.sql.Timestamp estimateValidDateTo) {
		this.estimateValidDateTo = estimateValidDateTo;
	}

	/**
	 * matssを取得します。
	 * @return matss
	 */
	public java.math.BigDecimal getMatss() {
		return matss;
	}

	/**
	 * matssを設定します。
	 * @param matss matss
	 */
	public void setMatss(final java.math.BigDecimal matss) {
		this.matss = matss;
	}

	/**
	 * remarkを取得します。
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * remarkを設定します。
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * specialDiscountを取得します。
	 * @return specialDiscount
	 */
	public java.math.BigDecimal getSpecialDiscount() {
		return specialDiscount;
	}

	/**
	 * specialDiscountを設定します。
	 * @param specialDiscount specialDiscount
	 */
	public void setSpecialDiscount(final java.math.BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	/**
	 * standardAmountを取得します。
	 * @return standardAmount
	 */
	public java.math.BigDecimal getStandardAmount() {
		return standardAmount;
	}

	/**
	 * standardAmountを設定します。
	 * @param standardAmount standardAmount
	 */
	public void setStandardAmount(final java.math.BigDecimal standardAmount) {
		this.standardAmount = standardAmount;
	}

	/**
	 * standardDiscountを取得します。
	 * @return standardDiscount
	 */
	public java.math.BigDecimal getStandardDiscount() {
		return standardDiscount;
	}

	/**
	 * standardDiscountを設定します。
	 * @param standardDiscount standardDiscount
	 */
	public void setStandardDiscount(final java.math.BigDecimal standardDiscount) {
		this.standardDiscount = standardDiscount;
	}

	/**
	 * standardUnitPriceを取得します。
	 * @return standardUnitPrice
	 */
	public java.math.BigDecimal getStandardUnitPrice() {
		return standardUnitPrice;
	}

	/**
	 * standardUnitPriceを設定します。
	 * @param standardUnitPrice standardUnitPrice
	 */
	public void setStandardUnitPrice(
			final java.math.BigDecimal standardUnitPrice) {
		this.standardUnitPrice = standardUnitPrice;
	}

	/**
	 * unitpriceを取得します。
	 * @return unitprice
	 */
	public java.math.BigDecimal getUnitprice() {
		return unitprice;
	}

	/**
	 * unitpriceを設定します。
	 * @param unitprice unitprice
	 */
	public void setUnitprice(final java.math.BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
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
