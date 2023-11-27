/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.remark;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.remarklist.RemarkList;
import com.asahikaseieng.dao.nonentity.master.remarklist.RemarkListDao;
import com.asahikaseieng.dao.nonentity.master.remarklist.RemarkListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.remarklistforreport.RemarkListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.remarklistforreport.RemarkListForReport;
import com.asahikaseieng.dao.nonentity.master.remarklistforreport.RemarkListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 備考一覧ロジック 実装クラス.
 * @author t0011036
 */
public class RemarkListLogicImpl implements RemarkListLogic {

	private RemarkListDao remarkListDao;

	private RemarkListForReportDao remarkListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public RemarkListLogicImpl() {
	}

	/**
	 * 備考一覧検索
	 * @param condition 検索条件
	 * @return List<RemarkList>
	 * @throws NoDataException NoDataException
	 */
	public List<RemarkList> getList(final RemarkListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<RemarkList> list = remarkListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final RemarkListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 備考一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<RemarkListForReport>
	 */
	public List<RemarkListForReport> getListForReport(
			final RemarkListConditionForReport condition) {
		List<RemarkListForReport> list = remarkListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * remarkListDaoを設定します。
	 * @param remarkListDao remarkListDao
	 */
	public void setRemarkListDao(final RemarkListDao remarkListDao) {
		this.remarkListDao = remarkListDao;
	}

	/**
	 * remarkListForReportDaoを設定します。
	 * @param remarkListForReportDao remarkListForReportDao
	 */
	public void setRemarkListForReportDao(
			final RemarkListForReportDao remarkListForReportDao) {
		this.remarkListForReportDao = remarkListForReportDao;
	}
}
