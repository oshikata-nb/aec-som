/*
 * Created on Tue Sep 08 18:52:17 JST 2015
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.salesextended;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SalesExtendedBaseクラス.
 * @author a1041072
 */
public class SalesExtendedBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesExtendedBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SALES_EXTENDED";

	// /** IDアノテーション salesNo. */
	// public static final String salesNo_ID = "assigned";

	/** COLUMNアノテーション salesNo. */
	public static final String salesNo_COLUMN = "SALES_NO";

	/** COLUMNアノテーション slipSendComp. */
	public static final String slipSendComp_COLUMN = "SLIP_SEND_COMP";

	/** COLUMNアノテーション slipSendDate. */
	public static final String slipSendDate_COLUMN = "SLIP_SEND_DATE";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String salesNo;

	private java.math.BigDecimal slipSendComp;

	private java.sql.Timestamp slipSendDate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * salesNo取得.
	 * @return salesNo
	 */
	public String getSalesNo() {
		return this.salesNo;
	}

	/**
	 * salesNo設定.
	 * @param salesNo salesNo
	 */
	public void setSalesNo(final String salesNo) {
		this.salesNo = salesNo;
	}

	/**
	 * slipSendDate取得.
	 * @return slipSendDate
	 */
	public java.sql.Timestamp getSlipSendDate() {
		return this.slipSendDate;
	}

	/**
	 * slipSendDate設定.
	 * @param slipSendDate slipSendDate
	 */
	public void setSlipSendDate(final java.sql.Timestamp slipSendDate) {
		this.slipSendDate = slipSendDate;
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

	/**
	 * slipSendCompを取得します。
	 * @return slipSendComp
	 */
	public java.math.BigDecimal getSlipSendComp() {
		return slipSendComp;
	}

	/**
	 * slipSendCompを設定します。
	 * @param slipSendComp slipSendComp
	 */
	public void setSlipSendComp(final java.math.BigDecimal slipSendComp) {
		this.slipSendComp = slipSendComp;
	}
}
