/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belong;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.belonglist.BelongList;
import com.asahikaseieng.dao.nonentity.master.belonglist.BelongListDao;
import com.asahikaseieng.dao.nonentity.master.belonglist.BelongListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.belonglistforreport.BelongListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.belonglistforreport.BelongListForReport;
import com.asahikaseieng.dao.nonentity.master.belonglistforreport.BelongListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 所属一覧ロジック 実装クラス.
 * @author t0011036
 */
public class BelongListLogicImpl implements BelongListLogic {

	private BelongListDao belongListDao;

	private BelongListForReportDao belongListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public BelongListLogicImpl() {
	}

	/**
	 * 所属一覧検索
	 * @param condition 検索条件
	 * @return List<BelongList>
	 * @throws NoDataException NoDataException
	 */
	public List<BelongList> getList(final BelongListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<BelongList> list = belongListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final BelongListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 所属一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<BelongListForReport>
	 */
	public List<BelongListForReport> getListForReport(
			final BelongListConditionForReport condition) {
		List<BelongListForReport> list = belongListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * belongListDaoを設定します。
	 * @param belongListDao belongListDao
	 */
	public void setBelongListDao(final BelongListDao belongListDao) {
		this.belongListDao = belongListDao;
	}

	/**
	 * belongListForReportDaoを設定します。
	 * @param belongListForReportDao belongListForReportDao
	 */
	public void setBelongListForReportDao(
			final BelongListForReportDao belongListForReportDao) {
		this.belongListForReportDao = belongListForReportDao;
	}
}
