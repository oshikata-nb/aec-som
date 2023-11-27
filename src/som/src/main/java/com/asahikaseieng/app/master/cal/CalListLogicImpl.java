/*
 * Created on 2008/07/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.master.cal;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.callist.CalList;
import com.asahikaseieng.dao.nonentity.master.callist.CalListDao;
import com.asahikaseieng.dao.nonentity.master.callist.CalListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.callistforreport.CalListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.callistforreport.CalListForReport;
import com.asahikaseieng.dao.nonentity.master.callistforreport.CalListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * カレンダー一覧ロジック 実装クラス.
 * @author tosco
 */
public class CalListLogicImpl implements CalListLogic {

	private CalListDao calListDao;

	private CalListForReportDao calListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public CalListLogicImpl() {
	}

	/**
	 * カレンダー一覧検索
	 * @param condition 検索条件
	 * @return List<CalList>
	 * @throws NoDataException NoDataException
	 */
	public List<CalList> getList(final CalListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<CalList> list = calListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final CalListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * カレンダー一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<CalListForReport>
	 */
	public List<CalListForReport> getListForReport(
			final CalListConditionForReport condition) {
		List<CalListForReport> list = calListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * calListDaoを設定します。
	 * @param calListDao calListDao
	 */
	public void setCalListDao(final CalListDao calListDao) {
		this.calListDao = calListDao;
	}

	/**
	 * calListForReportDaoを設定します。
	 * @param calListForReportDao calListForReportDao
	 */
	public void setCalListForReportDao(
			final CalListForReportDao calListForReportDao) {
		this.calListForReportDao = calListForReportDao;
	}
}
