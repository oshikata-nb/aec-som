/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

/**
 * 月次受払ロールバック処理ロジック interface.
 * @author t0011036
 */
public interface InoutMonthlyCancelLogic {
	/**
	 * 月次更新件数取得処理
	 * @param inputDate 処理年月
	 * @return int
	 * @throws Exception Exception
	 */
	int getMonthlyCount(final String inputDate) throws Exception;

	/**
	 * 月次更新データ削除処理
	 * @param inputDate 処理年月
	 * @return String[]
	 * @throws Exception Exception
	 */
	boolean deleteMonthly(final String inputDate) throws Exception;

	/**
	 * 受払更新取消処理
	 * @param inputDateFrom 処理開始日
	 * @param inputDateTo 処理終了日
	 * @param tantoCd 担当者コード
	 * @return String[]
	 * @throws Exception Exception
	 */
	boolean updateInoutCancel(final String inputDateFrom,
			final String inputDateTo, final String tantoCd) throws Exception;
}
