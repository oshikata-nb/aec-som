/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

/**
 * 月次受払更新処理ロジック interface.
 * @author t0011036
 */
public interface InoutMonthlyUpdateLogic {
	/**
	 * 受払件数取得処理
	 * @param inputDateFrom 処理開始日
	 * @param inputDateTo 処理終了日
	 * @return int
	 * @throws Exception Exception
	 */
	int getInoutCount(final String inputDateFrom, final String inputDateTo)
			throws Exception;

	/**
	 * 月次更新件数取得処理
	 * @param inputDate 処理年月
	 * @return int
	 * @throws Exception Exception
	 */
	int getMonthlyCount(final String inputDate) throws Exception;

	/**
	 * 月次更新データ作成処理
	 * @param inputDate 処理年月
	 * @param inputPrevDate 前月処理年月
	 * @param inputDateFrom 処理開始日
	 * @param inputDateTo 処理終了日
	 * @param tantoCd 担当者コード
	 * @return String[]
	 * @throws Exception Exception
	 */
	boolean insertMonthly(final String inputDate, final String inputPrevDate,
			final String inputDateFrom, final String inputDateTo,
			final String tantoCd) throws Exception;

	/**
	 * 受払更新処理
	 * @param inputDateFrom 処理開始日
	 * @param inputDateTo 処理終了日
	 * @param tantoCd 担当者コード
	 * @return String[]
	 * @throws Exception Exception
	 */
	boolean updateInout(final String inputDateFrom, final String inputDateTo,
			final String tantoCd) throws Exception;
}
