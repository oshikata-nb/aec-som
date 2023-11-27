/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reason;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.reasonlist.ReasonList;
import com.asahikaseieng.dao.nonentity.master.reasonlist.ReasonListDao;
import com.asahikaseieng.dao.nonentity.master.reasonlist.ReasonListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.reasonlistforreport.ReasonListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.reasonlistforreport.ReasonListForReport;
import com.asahikaseieng.dao.nonentity.master.reasonlistforreport.ReasonListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 理由一覧ロジック 実装クラス.
 * @author t0011036
 */
public class ReasonListLogicImpl implements ReasonListLogic {

	private ReasonListDao reasonListDao;

	private ReasonListForReportDao reasonListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public ReasonListLogicImpl() {
	}

	/**
	 * 理由一覧検索
	 * @param condition 検索条件
	 * @return List<ReasonList>
	 * @throws NoDataException NoDataException
	 */
	public List<ReasonList> getList(final ReasonListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<ReasonList> list = reasonListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final ReasonListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 理由一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<ReasonListForReport>
	 */
	public List<ReasonListForReport> getListForReport(
			final ReasonListConditionForReport condition) {
		List<ReasonListForReport> list = reasonListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * reasonListDaoを設定します。
	 * @param reasonListDao reasonListDao
	 */
	public void setReasonListDao(final ReasonListDao reasonListDao) {
		this.reasonListDao = reasonListDao;
	}

	/**
	 * reasonListForReportDaoを設定します。
	 * @param reasonListForReportDao reasonListForReportDao
	 */
	public void setReasonListForReportDao(
			final ReasonListForReportDao reasonListForReportDao) {
		this.reasonListForReportDao = reasonListForReportDao;
	}
}
