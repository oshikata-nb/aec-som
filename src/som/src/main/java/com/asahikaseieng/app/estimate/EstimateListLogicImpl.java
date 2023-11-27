/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.estimate;

import java.util.List;

import com.asahikaseieng.dao.nonentity.estimatelist.EstimateList;
import com.asahikaseieng.dao.nonentity.estimatelist.EstimateListDao;
import com.asahikaseieng.dao.nonentity.estimatelist.EstimateListPagerCondition;
import com.asahikaseieng.dao.nonentity.estimatelistforreport.EstimateListConditionForReport;
import com.asahikaseieng.dao.nonentity.estimatelistforreport.EstimateListForReport;
import com.asahikaseieng.dao.nonentity.estimatelistforreport.EstimateListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 見積/単価一覧ロジック 実装クラス.
 * @author t0011036
 */
public class EstimateListLogicImpl implements EstimateListLogic {

	private EstimateListDao estimateListDao;

	private EstimateListForReportDao estimateListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public EstimateListLogicImpl() {
	}

	/**
	 * 見積/単価一覧検索
	 * @param condition 検索条件
	 * @return List<EstimateList>
	 * @throws NoDataException NoDataException
	 */
	public List<EstimateList> getList(final EstimateListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<EstimateList> list = estimateListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final EstimateListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 見積/単価一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<EstimateListForReport>
	 */
	public List<EstimateListForReport> getListForReport(
			final EstimateListConditionForReport condition) {
		List<EstimateListForReport> list = estimateListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * estimateListDaoを設定します。
	 * @param estimateListDao estimateListDao
	 */
	public void setEstimateListDao(final EstimateListDao estimateListDao) {
		this.estimateListDao = estimateListDao;
	}

	/**
	 * estimateListForReportDaoを設定します。
	 * @param estimateListForReportDao estimateListForReportDao
	 */
	public void setEstimateListForReportDao(
			final EstimateListForReportDao estimateListForReportDao) {
		this.estimateListForReportDao = estimateListForReportDao;
	}
}
