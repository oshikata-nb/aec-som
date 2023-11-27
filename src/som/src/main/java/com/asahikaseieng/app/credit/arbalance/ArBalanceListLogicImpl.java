/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arbalance;

import java.util.List;

import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalanceList;
import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalanceListDao;
import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.credit.arbalanceforreport.ArBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.credit.arbalanceforreport.ArBalanceListForReport;
import com.asahikaseieng.dao.nonentity.credit.arbalanceforreport.ArBalanceListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * 売掛残高一覧ロジッククラス
 * @author tosco
 */
public class ArBalanceListLogicImpl implements ArBalanceListLogic {

	private ArBalanceListDao arBalanceListDao;

	private ArBalanceListForReportDao arBalanceListForReportDao;

	/**
	 * コンストラクタ.売掛残高一覧ロジック
	 */
	public ArBalanceListLogicImpl() {
	}

	/**
	 * 検索処理を行う.売掛残高一覧
	 * @param condition condition
	 * @return ArBalanceList 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public List<ArBalanceList> getSearchList(
			final ArBalancePagerCondition condition) throws NoDataException {

		checkParams(condition);

		final List<ArBalanceList> bean = arBalanceListDao
				.getSearchList(condition);

		if (bean.isEmpty()) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 検索処理を行う.MAX(締め日)の年月
	 * @param sectionCd 部門コード
	 * @return ArBalanceList 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public ArBalanceList getSearchDate(final String sectionCd)
			throws NoDataException {
		ArBalanceList bean = arBalanceListDao.getSearchDate(sectionCd);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * パラメータチェック.getSearchList
	 * @param condition 検索条件
	 */
	private void checkParams(final ArBalancePagerCondition condition) {

		if (condition.getSrhTargetMonth() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 売掛残高一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<ArBalanceListForReport>
	 */
	public List<ArBalanceListForReport> getListForReport(
			final ArBalanceListConditionForReport condition) {
		List<ArBalanceListForReport> list = arBalanceListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * ArBalanceListDaoを設定します。
	 * 
	 * @param arBalanceListDao arBalanceListDao
	 */
	public void setArBalanceListDao(final ArBalanceListDao arBalanceListDao) {
		this.arBalanceListDao = arBalanceListDao;
	}

	/**
	 * arBalanceListForReportDaoを設定します。
	 * @param arBalanceListForReportDao arBalanceListForReportDao
	 */
	public void setArBalanceListForReportDao(
			final ArBalanceListForReportDao arBalanceListForReportDao) {
		this.arBalanceListForReportDao = arBalanceListForReportDao;
	}
}
