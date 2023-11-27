/*
 * Created on 2009/07/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * EstimateListクラス.
 * @author t0011036
 */
public class EstimateListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public EstimateListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション estimateNo */
	public static final String estimateNo_COLUMN = "ESTIMATE_NO";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderName2 */
	public static final String venderName2_COLUMN = "VENDER_NAME2";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション estimateValidDateTo */
	public static final String estimateValidDateTo_COLUMN = "ESTIMATE_VALID_DATE_TO";

	/** COLUMNアノテーション estimateInputDate */
	public static final String estimateInputDate_COLUMN = "ESTIMATE_INPUT_DATE";

	/** COLUMNアノテーション estimateStatus */
	public static final String estimateStatus_COLUMN = "ESTIMATE_STATUS";

	/** COLUMNアノテーション estimateStatusName */
	public static final String estimateStatusName_COLUMN = "ESTIMATE_STATUS_NAME";

	//
	// インスタンスフィールド
	//

	private String estimateNo;

	private String venderCd;

	private String venderName1;

	private String venderName2;

	private String venderShortedName;

	private java.sql.Timestamp estimateValidDateTo;

	private java.sql.Timestamp estimateInputDate;

	private java.math.BigDecimal estimateStatus;

	private String estimateStatusName;

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
	 * estimateStatusName取得.
	 * @return estimateStatusName
	 */
	public String getEstimateStatusName() {
		return this.estimateStatusName;
	}

	/**
	 * estimateStatusName設定.
	 * @param estimateStatusName estimateStatusName
	 */
	public void setEstimateStatusName(final String estimateStatusName) {
		this.estimateStatusName = estimateStatusName;
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

