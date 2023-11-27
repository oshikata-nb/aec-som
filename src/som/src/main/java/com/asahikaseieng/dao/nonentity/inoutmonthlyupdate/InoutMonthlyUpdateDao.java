/*
 * Created on 2009/07/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutmonthlyupdate;

/**
 * InoutMonthlyUpdateDaoクラス
 * @author t0011036
 */
public interface InoutMonthlyUpdateDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inoutmonthlyupdate.InoutMonthlyUpdate.class;

	/** ARGSアノテーション getInoutCount */
	String getInoutCount_ARGS = "inputDateFrom, inputDateTo";

	/**
	 * InoutMonthlyUpdateメソッド
	 * @param inputDateFrom 処理開始日
	 * @param inputDateTo 処理終了日
	 * @return InoutMonthlyUpdate
	 */
	InoutMonthlyUpdate getInoutCount(final String inputDateFrom,
			final String inputDateTo);

	/** ARGSアノテーション getMonthlyCount */
	String getMonthlyCount_ARGS = "inputDate";

	/**
	 * InoutMonthlyUpdateメソッド
	 * @param inputDate 処理年月
	 * @return InoutMonthlyUpdate
	 */
	InoutMonthlyUpdate getMonthlyCount(final String inputDate);

	/** ARGSアノテーション insertMonthly */
	String insertMonthly_ARGS = "inputDate, inputDateFrom, inputDateTo, tantoCd";

	/**
	 * InoutMonthlyUpdateメソッド
	 * @param inputDate 処理年月
	 * @param inputDateFrom 処理開始日
	 * @param inputDateTo 処理終了日
	 * @param tantoCd 担当者コード
	 * @return int 該当件数
	 */
	int insertMonthly(final String inputDate, final String inputDateFrom,
			final String inputDateTo, final String tantoCd);

	/** ARGSアノテーション insertDifferenceMonthly */
	String insertDifferenceMonthly_ARGS = "inputDate, inputPrevDate, tantoCd";

	/**
	 * InoutMonthlyUpdateメソッド
	 * @param inputDate 処理年月
	 * @param inputPrevDate 前月処理月
	 * @param tantoCd 担当者コード
	 * @return int 該当件数
	 */
	int insertDifferenceMonthly(final String inputDate,
			final String inputPrevDate, final String tantoCd);

	/** ARGSアノテーション deleteMonthly */
	String deleteMonthly_ARGS = "inputDate, inputDateFrom, inputDateTo, tantoCd";

	/**
	 * InoutMonthlyUpdateメソッド
	 * @param inputDate 処理年月
	 * @return int 該当件数
	 */
	int deleteMonthly(final String inputDate);

	/** ARGSアノテーション updateInout */
	String updateInout_ARGS = "inputDateFrom, inputDateTo, tantoCd";

	/**
	 * InoutMonthlyUpdateメソッド
	 * @param inputDateFrom 処理開始日
	 * @param inputDateTo 処理終了日
	 * @param tantoCd 担当者コード
	 * @return int 該当件数
	 */
	int updateInout(final String inputDateFrom, final String inputDateTo,
			final String tantoCd);

	/** ARGSアノテーション updateInoutCancel */
	String updateInoutCancel_ARGS = "inputDateFrom, inputDateTo, tantoCd";

	/**
	 * InoutMonthlyUpdateメソッド
	 * @param inputDateFrom 処理開始日
	 * @param inputDateTo 処理終了日
	 * @param tantoCd 担当者コード
	 * @return int 該当件数
	 */
	int updateInoutCancel(final String inputDateFrom, final String inputDateTo,
			final String tantoCd);
}
