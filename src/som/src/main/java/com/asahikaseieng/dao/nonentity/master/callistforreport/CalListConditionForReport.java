/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.callistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * カレンダー検索条件
 * @author jbd
 */
public class CalListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CalListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhCalCd; /* カレンダーコード */

	private String srhCalYear; /* 会計年度 */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhCalCdを取得します。
	 * @return srhCalCd
	 */
	public String getSrhCalCd() {
		return srhCalCd;
	}

	/**
	 * srhCalCdを設定します。
	 * @param srhCalCd srhCalCd
	 */
	public void setSrhCalCd(final String srhCalCd) {
		this.srhCalCd = AecTextUtils.likeFilter(srhCalCd);
	}

	/**
	 * srhCalYearを取得します。
	 * @return srhCalYear
	 */
	public String getSrhCalYear() {
		return srhCalYear;
	}

	/**
	 * srhCalYearを設定します。
	 * @param srhCalYear srhCalYear
	 */
	public void setSrhCalYear(final String srhCalYear) {
		this.srhCalYear = srhCalYear;
	}
}
