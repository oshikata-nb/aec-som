/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.operation;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.operationlist.OperationList;
import com.asahikaseieng.dao.nonentity.master.operationlist.OperationListDao;
import com.asahikaseieng.dao.nonentity.master.operationlist.OperationListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.operationlistforreport.OperationListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.operationlistforreport.OperationListForReport;
import com.asahikaseieng.dao.nonentity.master.operationlistforreport.OperationListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 工程一覧ロジック 実装クラス.
 * @author t0011036
 */
public class OperationListLogicImpl implements OperationListLogic {

	private OperationListDao operationListDao;

	private OperationListForReportDao operationListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public OperationListLogicImpl() {
	}

	/**
	 * 工程一覧検索
	 * @param condition 検索条件
	 * @return List<OperationList>
	 * @throws NoDataException NoDataException
	 */
	public List<OperationList> getList(
			final OperationListPagerCondition condition) throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<OperationList> list = operationListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final OperationListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 工程一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<OperationListForReport>
	 */
	public List<OperationListForReport> getListForReport(
			final OperationListConditionForReport condition) {
		List<OperationListForReport> list = operationListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * operationListDaoを設定します。
	 * @param operationListDao operationListDao
	 */
	public void setOperationListDao(final OperationListDao operationListDao) {
		this.operationListDao = operationListDao;
	}

	/**
	 * operationListForReportDaoを設定します。
	 * @param operationListForReportDao operationListForReportDao
	 */
	public void setOperationListForReportDao(
			final OperationListForReportDao operationListForReportDao) {
		this.operationListForReportDao = operationListForReportDao;
	}
}
