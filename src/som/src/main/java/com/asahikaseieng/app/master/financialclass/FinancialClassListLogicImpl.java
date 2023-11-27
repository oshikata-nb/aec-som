/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.financialclass;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.financialclasslist.FinancialClassList;
import com.asahikaseieng.dao.nonentity.master.financialclasslist.FinancialClassListDao;
import com.asahikaseieng.dao.nonentity.master.financialclasslist.FinancialClassListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.financialclasslistforreport.FinancialClassListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.financialclasslistforreport.FinancialClassListForReport;
import com.asahikaseieng.dao.nonentity.master.financialclasslistforreport.FinancialClassListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 財務分類一覧ロジック 実装クラス.
 * @author t0011036
 */
public class FinancialClassListLogicImpl implements FinancialClassListLogic {

	private FinancialClassListDao financialClassListDao;

	private FinancialClassListForReportDao financialClassListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public FinancialClassListLogicImpl() {
	}

	/**
	 * 財務分類一覧検索
	 * @param condition 検索条件
	 * @return List<FinancialClassList>
	 * @throws NoDataException NoDataException
	 */
	public List<FinancialClassList> getList(
			final FinancialClassListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<FinancialClassList> list = financialClassListDao
				.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final FinancialClassListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 財務分類一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<FinancialClassListForReport>
	 */
	public List<FinancialClassListForReport> getListForReport(
			final FinancialClassListConditionForReport condition) {
		List<FinancialClassListForReport> list = financialClassListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * financialClassListDaoを設定します。
	 * @param financialClassListDao financialClassListDao
	 */
	public void setFinancialClassListDao(
			final FinancialClassListDao financialClassListDao) {
		this.financialClassListDao = financialClassListDao;
	}

	/**
	 * financialClassListForReportDaoを設定します。
	 * @param financialClassListForReportDao financialClassListForReportDao
	 */
	public void setFinancialClassListForReportDao(
			final FinancialClassListForReportDao financialClassListForReportDao) {
		this.financialClassListForReportDao = financialClassListForReportDao;
	}
}
