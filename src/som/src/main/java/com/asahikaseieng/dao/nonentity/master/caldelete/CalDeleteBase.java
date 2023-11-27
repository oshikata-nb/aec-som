/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.caldelete;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * CalDeleteListクラス.
 * @author t0011036
 */
public class CalDeleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CalDeleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション calCd. */
	public static final String calCd_COLUMN = "CAL_CD";

	/** COLUMNアノテーション calName. */
	public static final String calName_COLUMN = "CAL_NAME";

	/** COLUMNアノテーション calYear. */
	public static final String calYear_COLUMN = "CAL_YEAR";

	/** COLUMNアノテーション calDate. */
	public static final String calDate_COLUMN = "CAL_DATE";

	/** COLUMNアノテーション calWeek. */
	public static final String calWeek_COLUMN = "CAL_WEEK";

	/** COLUMNアノテーション calHoliday. */
	public static final String calHoliday_COLUMN = "CAL_HOLIDAY";

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

	private String calCd;

	private String calName;

	private java.math.BigDecimal calYear;

	private java.sql.Timestamp calDate;

	private java.math.BigDecimal calWeek;

	private java.math.BigDecimal calHoliday;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * calCd取得.
	 * @return calCd
	 */
	public String getCalCd() {
		return this.calCd;
	}

	/**
	 * calCd設定.
	 * @param calCd calCd
	 */
	public void setCalCd(final String calCd) {
		this.calCd = calCd;
	}

	/**
	 * calName取得.
	 * @return calName
	 */
	public String getCalName() {
		return this.calName;
	}

	/**
	 * calName設定.
	 * @param calName calName
	 */
	public void setCalName(final String calName) {
		this.calName = calName;
	}

	/**
	 * calYear取得.
	 * @return calYear
	 */
	public java.math.BigDecimal getCalYear() {
		return this.calYear;
	}

	/**
	 * calYear設定.
	 * @param calYear calYear
	 */
	public void setCalYear(final java.math.BigDecimal calYear) {
		this.calYear = calYear;
	}

	/**
	 * calDate取得.
	 * @return calDate
	 */
	public java.sql.Timestamp getCalDate() {
		return this.calDate;
	}

	/**
	 * calDate設定.
	 * @param calDate calDate
	 */
	public void setCalDate(final java.sql.Timestamp calDate) {
		this.calDate = calDate;
	}

	/**
	 * calWeek取得.
	 * @return calWeek
	 */
	public java.math.BigDecimal getCalWeek() {
		return this.calWeek;
	}

	/**
	 * calWeek設定.
	 * @param calWeek calWeek
	 */
	public void setCalWeek(final java.math.BigDecimal calWeek) {
		this.calWeek = calWeek;
	}

	/**
	 * calHoliday取得.
	 * @return calHoliday
	 */
	public java.math.BigDecimal getCalHoliday() {
		return this.calHoliday;
	}

	/**
	 * calHoliday設定.
	 * @param calHoliday calHoliday
	 */
	public void setCalHoliday(final java.math.BigDecimal calHoliday) {
		this.calHoliday = calHoliday;
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
}
