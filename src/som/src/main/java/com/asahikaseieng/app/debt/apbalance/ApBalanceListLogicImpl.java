/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apbalance;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.dao.nonentity.debt.apbalance.ApBalanceList;
import com.asahikaseieng.dao.nonentity.debt.apbalance.ApBalanceListDao;
import com.asahikaseieng.dao.nonentity.debt.apbalance.ApBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.debt.apbalanceforreport.ApBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.debt.apbalanceforreport.ApBalanceListForReport;
import com.asahikaseieng.dao.nonentity.debt.apbalanceforreport.ApBalanceListForReportDao;
import com.asahikaseieng.dao.nonentity.debt.aprollback.ApRollback;
import com.asahikaseieng.dao.nonentity.debt.aprollback.ApRollbackDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * 買掛残高一覧ロジッククラス
 * @author tosco
 */
public class ApBalanceListLogicImpl implements ApBalanceListLogic {

	private ApBalanceListDao apBalanceListDao;

	private ApBalanceListForReportDao apBalanceListForReportDao;

	private ApRollbackDao apRollbackDao;

	/**
	 * コンストラクタ.買掛残高一覧ロジック
	 */
	public ApBalanceListLogicImpl() {
	}

	/**
	 * 検索処理を行う.買掛残高一覧
	 * @param condition condition
	 * @return ArBalanceList 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public List<ApBalanceList> getSearchList(
			final ApBalancePagerCondition condition) throws NoDataException {
		List<ApBalanceList> bean = null;

		checkParams(condition);

		if (condition.getSrhTargetDivision().equals("0")) {
			// 本締め
			bean = apBalanceListDao.getSearchList(condition);
		} else {
			// 仮締め
			bean = apBalanceListDao.getSearchListTemp(condition);
		}

		if (bean.isEmpty()) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 買掛ヘッダーからMAX(買掛締め日)の年月を取得
	 * 
	 * @param organizationCd 部署コード
	 * @return ApRollback 買掛ヘッダーデータ(買掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	public ApRollback getSearch(final String organizationCd)
			throws NoDataException {

		ApRollback bean = apRollbackDao.getSearch(organizationCd);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * パラメータチェック.getSearchList
	 * @param condition 検索条件
	 */
	private void checkParams(final ApBalancePagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 買掛残高一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<ApBalanceListForReport>
	 */
	public List<ApBalanceListForReport> getListForReport(
			final ApBalanceListConditionForReport condition) {
		List<ApBalanceListForReport> list = new ArrayList<ApBalanceListForReport>();

		if (condition.getSrhTargetDivision().equals("0")) {
			/* 本締め */
			list = apBalanceListForReportDao.getListForReport(condition);
		} else {
			/* 仮締め */
			list = apBalanceListForReportDao.getListTempForReport(condition);
		}

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * ApBalanceListDaoを設定します。
	 * 
	 * @param apBalanceListDao apBalanceListDao
	 */
	public void setApBalanceListDao(final ApBalanceListDao apBalanceListDao) {
		this.apBalanceListDao = apBalanceListDao;
	}

	/**
	 * apRollbackDaoを設定します。
	 * @param apRollbackDao apRollbackDao
	 */
	public void setApRollbackDao(final ApRollbackDao apRollbackDao) {
		this.apRollbackDao = apRollbackDao;
	}

	/**
	 * apBalanceListForReportDaoを設定します。
	 * @param apBalanceListForReportDao apBalanceListForReportDao
	 */
	public void setApBalanceListForReportDao(
			final ApBalanceListForReportDao apBalanceListForReportDao) {
		this.apBalanceListForReportDao = apBalanceListForReportDao;
	}
}
