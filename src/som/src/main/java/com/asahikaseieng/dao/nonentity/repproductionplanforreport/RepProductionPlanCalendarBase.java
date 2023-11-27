/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repproductionplanforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepProductionPlanCalendarクラス.
 * @author kanri-user
 */
public class RepProductionPlanCalendarBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepProductionPlanCalendarBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション calDate */
	public static final String calDate_COLUMN = "CAL_DATE";

	/** COLUMNアノテーション calWeek */
	public static final String calWeek_COLUMN = "CAL_WEEK";

	/** COLUMNアノテーション calHoliday */
	public static final String calHoliday_COLUMN = "CAL_HOLIDAY";

	//
	// インスタンスフィールド
	//

	private java.sql.Timestamp calDate;

	private java.math.BigDecimal calWeek;

	private java.math.BigDecimal calHoliday;

	//
	// インスタンスメソッド
	//

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

