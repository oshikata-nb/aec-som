/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.line;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.linelist.LineList;
import com.asahikaseieng.dao.nonentity.master.linelist.LineListDao;
import com.asahikaseieng.dao.nonentity.master.linelist.LineListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.linelistforreport.LineListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.linelistforreport.LineListForReport;
import com.asahikaseieng.dao.nonentity.master.linelistforreport.LineListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 生産ライン一覧ロジック 実装クラス.
 * @author t0011036
 */
public class LineListLogicImpl implements LineListLogic {

	private LineListDao lineListDao;

	private LineListForReportDao lineListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public LineListLogicImpl() {
	}

	/**
	 * 生産ライン一覧検索
	 * @param condition 検索条件
	 * @return List<LineList>
	 * @throws NoDataException NoDataException
	 */
	public List<LineList> getList(final LineListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<LineList> list = lineListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final LineListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 生産ライン一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<LineListForReport>
	 */
	public List<LineListForReport> getListForReport(
			final LineListConditionForReport condition) {
		List<LineListForReport> list = lineListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * lineListDaoを設定します。
	 * @param lineListDao lineListDao
	 */
	public void setLineListDao(final LineListDao lineListDao) {
		this.lineListDao = lineListDao;
	}

	/**
	 * lineListForReportDaoを設定します。
	 * @param lineListForReportDao lineListForReportDao
	 */
	public void setLineListForReportDao(
			final LineListForReportDao lineListForReportDao) {
		this.lineListForReportDao = lineListForReportDao;
	}
}
