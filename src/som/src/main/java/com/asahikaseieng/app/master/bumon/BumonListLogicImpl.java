/*
 * Created on 2007/08/08
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.master.bumon;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.bumonlist.BumonList;
import com.asahikaseieng.dao.nonentity.master.bumonlist.BumonListDao;
import com.asahikaseieng.dao.nonentity.master.bumonlist.BumonListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.bumonlistforreport.BumonListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.bumonlistforreport.BumonListForReport;
import com.asahikaseieng.dao.nonentity.master.bumonlistforreport.BumonListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロジック 実装クラス. 会計部門マスタ一覧
 * @author tanaka
 */
public class BumonListLogicImpl implements BumonListLogic {

	private BumonListDao bumonListDao;

	private BumonListForReportDao bumonListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public BumonListLogicImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BumonList> getList(final BumonListPagerCondition condition)
			throws NoDataException {

		checkParams(condition);
		List<BumonList> list = bumonListDao.getList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;

	}

	/**
	 * 会計部門一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<BumonListForReport>
	 */
	public List<BumonListForReport> getListForReport(
			final BumonListConditionForReport condition) {
		List<BumonListForReport> list = bumonListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * パラメータチェック.getSearchList
	 * @param condition 検索条件.bumon
	 */
	private void checkParams(final BumonListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * bumonListDaoを設定します。
	 * @param bumonListDao BumonListDao
	 */
	public void setBumonListDao(final BumonListDao bumonListDao) {
		this.bumonListDao = bumonListDao;
	}

	/**
	 * bumonListForReportDaoを設定します。
	 * @param bumonListForReportDao bumonListForReportDao
	 */
	public void setBumonListForReportDao(
			final BumonListForReportDao bumonListForReportDao) {
		this.bumonListForReportDao = bumonListForReportDao;
	}
}
