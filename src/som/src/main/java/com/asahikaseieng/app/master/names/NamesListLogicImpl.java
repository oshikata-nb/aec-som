/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.names;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.nameslist.NamesList;
import com.asahikaseieng.dao.nonentity.master.nameslist.NamesListDao;
import com.asahikaseieng.dao.nonentity.master.nameslist.NamesListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.nameslistforreport.NamesListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.nameslistforreport.NamesListForReport;
import com.asahikaseieng.dao.nonentity.master.nameslistforreport.NamesListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 名称一覧ロジック 実装クラス.
 * @author t0011036
 */
public class NamesListLogicImpl implements NamesListLogic {

	private NamesListDao namesListDao;

	private NamesListForReportDao namesListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public NamesListLogicImpl() {
	}

	/**
	 * 名称一覧検索
	 * @param condition 検索条件
	 * @return List<NamesList>
	 * @throws NoDataException NoDataException
	 */
	public List<NamesList> getList(final NamesListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<NamesList> list = namesListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final NamesListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 名称一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<NamesListForReport>
	 */
	public List<NamesListForReport> getListForReport(
			final NamesListConditionForReport condition) {
		List<NamesListForReport> list = namesListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * namesListDaoを設定します。
	 * @param namesListDao namesListDao
	 */
	public void setNamesListDao(final NamesListDao namesListDao) {
		this.namesListDao = namesListDao;
	}

	/**
	 * namesListForReportDaoを設定します。
	 * @param namesListForReportDao namesListForReportDao
	 */
	public void setNamesListForReportDao(
			final NamesListForReportDao namesListForReportDao) {
		this.namesListForReportDao = namesListForReportDao;
	}
}
