package com.asahikaseieng.dao.nonentity.master.linelistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 生産ライン検索条件
 * @author a1020630
 */
public class LineListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public LineListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	/* 生産ラインコード */
	private String srhProductionLine;

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhProductionLineを取得します。
	 * @return srhProductionLine
	 */
	public String getSrhProductionLine() {
		return srhProductionLine;
	}

	/**
	 * srhProductionLineを設定します。
	 * @param srhProductionLine srhProductionLine
	 */
	public void setSrhProductionLine(final String srhProductionLine) {
		this.srhProductionLine = AecTextUtils.likeFilter(srhProductionLine);
	}
}
