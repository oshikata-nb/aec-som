/*
 * Created on 2009/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.cost.costaccounting;

/**
 * 原価計算データ送信ロジック interface.
 * @author t0011036
 */
public interface CostAccountingLogic {
	/**
	 * 実行
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 */
	void execute(final CostAccountingForm frm, final String tantoCd);

	/**
	 * 取込
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 */
	void importData(final CostAccountingForm frm, final String tantoCd);
}
