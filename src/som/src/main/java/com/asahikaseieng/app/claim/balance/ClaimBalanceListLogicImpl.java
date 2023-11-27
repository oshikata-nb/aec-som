/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.balance;

import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.balance.ClaimBalanceList;
import com.asahikaseieng.dao.nonentity.claim.balance.ClaimBalanceListDao;
import com.asahikaseieng.dao.nonentity.claim.balance.ClaimBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.claim.balancelistforreport.ClaimBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.balancelistforreport.ClaimBalanceListForReport;
import com.asahikaseieng.dao.nonentity.claim.balancelistforreport.ClaimBalanceListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * BalanceListLogicImplクラス.請求残高一覧
 * @author tosco
 */
public class ClaimBalanceListLogicImpl implements ClaimBalanceListLogic {

	private ClaimBalanceListDao balanceListDao;

	private ClaimBalanceListForReportDao claimBalanceListForReportDao;

	/**
	 * コンストラクタ.請求残高一覧ロジック
	 */
	public ClaimBalanceListLogicImpl() {
	}

	/**
	 * 検索処理を行う.請求残高一覧
	 * @param condition condition
	 * @return BalanceList 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public List<ClaimBalanceList> getSearchList(
			final ClaimBalancePagerCondition condition) throws NoDataException {

		checkParams(condition);

		final List<ClaimBalanceList> bean = balanceListDao
				.getSearchList(condition);

		if (bean.isEmpty()) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * パラメータチェック.getSearchList
	 * @param condition 検索条件
	 */
	private void checkParams(final ClaimBalancePagerCondition condition) {

		if (condition.getSrhTargetMonth() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 請求残高一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<ClaimBalanceListForReport>
	 */
	public List<ClaimBalanceListForReport> getListForReport(
			final ClaimBalanceListConditionForReport condition) {
		List<ClaimBalanceListForReport> list = claimBalanceListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * BalanceListDaoを設定します。
	 * 
	 * @param balanceListDao balanceListDao
	 */
	public void setBalanceListDao(final ClaimBalanceListDao balanceListDao) {
		this.balanceListDao = balanceListDao;
	}

	/**
	 * claimBalanceListForReportDaoを設定します。
	 * @param claimBalanceListForReportDao claimBalanceListForReportDao
	 */
	public void setClaimBalanceListForReportDao(
			final ClaimBalanceListForReportDao claimBalanceListForReportDao) {
		this.claimBalanceListForReportDao = claimBalanceListForReportDao;
	}
}
