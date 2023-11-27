/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.dailyeportforreport;

// import com.asahikaseieng.utils.AecTextUtils;

/**
 * LocationPagerConditionクラス.
 * @author fml
 */
public class DailyReportListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DailyReportListConditionForReport() {
	}

	//
	// 検索入力用.daily_report
	//

	/** 検索項目 */
	private String srhDate; // 製造日

	private String srhLine; // 生産工場

	private String srhTantoDiv; // 担当区分

	/**
	 * 検索.製造日の取得
	 * @return srhDate
	 */
	public String getSrhDate() {
		return this.srhDate;
	}

	/**
	 * 検索.製造日の設定
	 * @param srhDate srhDate
	 */
	public void setSrhDate(final String srhDate) {
		this.srhDate = srhDate;
		// this.srhDate = AecTextUtils.likeFilter(srhDate);
	}

	/**
	 * 検索.生産工場の取得
	 * @return srhLine
	 */
	public String getSrhLine() {
		return this.srhLine;
	}

	/**
	 * 検索.生産工場の設定
	 * @param srhLine srhLine
	 */
	public void setSrhLine(final String srhLine) {
		this.srhLine = srhLine;
		// this.srhLine = AecTextUtils.likeFilter(srhLine);
	}

	/**
	 * 担当区分の取得
	 * @return srhTantoDiv String
	 */
	public String getSrhTantoDiv() {
		return this.srhTantoDiv;
	}

	/**
	 * 担当区分の設定
	 * @param srhTantoDiv String
	 */
	public void setSrhTantoDiv(final String srhTantoDiv) {
		this.srhTantoDiv = srhTantoDiv;
	}
}
