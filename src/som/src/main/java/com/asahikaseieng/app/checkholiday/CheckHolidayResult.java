/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.checkholiday;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 休日チェック
 * @author t0011036
 */
public class CheckHolidayResult implements Serializable {

	private static final long serialVersionUID = 1L;

	/* 休日区分 */
	private BigDecimal calHoliday;

	/* 戻値 */
	private BigDecimal code;

	/**
	 * コンストラクタ
	 */
	public CheckHolidayResult() {

	}

	/**
	 * コンストラクタ
	 * @param code エラーコード
	 */
	public CheckHolidayResult(final BigDecimal code) {
		this.code = code;
	}

	/**
	 * codeを取得します。
	 * @return code
	 */
	public BigDecimal getCode() {
		return code;
	}

	/**
	 * codeを設定します。
	 * @param code code
	 */
	public void setCode(final BigDecimal code) {
		this.code = code;
	}

	/**
	 * calHolidayを取得します。
	 * @return calHoliday
	 */
	public BigDecimal getCalHoliday() {
		return calHoliday;
	}

	/**
	 * calHolidayを設定します。
	 * @param calHoliday calHoliday
	 */
	public void setCalHoliday(final BigDecimal calHoliday) {
		this.calHoliday = calHoliday;
	}
}
