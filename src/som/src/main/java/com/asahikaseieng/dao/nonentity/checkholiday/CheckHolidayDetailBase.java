/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.checkholiday;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * CheckHolidayクラス.
 * @author t0011036
 */
public class CheckHolidayDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CheckHolidayDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション calHoliday */
	public static final String calHoliday_COLUMN = "CAL_HOLIDAY";

	/** COLUMNアノテーション calendarCd */
	public static final String calendarCd_COLUMN = "CALENDAR_CD";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal calHoliday;

	private String calendarCd;

	//
	// インスタンスメソッド
	//

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
	 * calendarCdを取得します。
	 * @return calendarCd
	 */
	public String getCalendarCd() {
		return calendarCd;
	}

	/**
	 * calendarCdを設定します。
	 * @param calendarCd calendarCd
	 */
	public void setCalendarCd(final String calendarCd) {
		this.calendarCd = calendarCd;
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
