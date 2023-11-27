/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bank;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.banklist.BankList;
import com.asahikaseieng.dao.nonentity.master.banklist.BankListDao;
import com.asahikaseieng.dao.nonentity.master.banklist.BankListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.banklistforreport.BankListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.banklistforreport.BankListForReport;
import com.asahikaseieng.dao.nonentity.master.banklistforreport.BankListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 銀行一覧ロジック 実装クラス.
 * @author t0011036
 */
public class BankListLogicImpl implements BankListLogic {

	private BankListDao bankListDao;

	private BankListForReportDao bankListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public BankListLogicImpl() {
	}

	/**
	 * 銀行一覧検索
	 * @param condition 検索条件
	 * @return List<BankList>
	 * @throws NoDataException NoDataException
	 */
	public List<BankList> getList(final BankListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<BankList> list = bankListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final BankListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 銀行一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<BankListForReport>
	 */
	public List<BankListForReport> getListForReport(
			final BankListConditionForReport condition) {
		List<BankListForReport> list = bankListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * bankListDaoを設定します。
	 * @param bankListDao bankListDao
	 */
	public void setBankListDao(final BankListDao bankListDao) {
		this.bankListDao = bankListDao;
	}

	/**
	 * bankListForReportDaoを設定します。
	 * @param bankListForReportDao bankListForReportDao
	 */
	public void setBankListForReportDao(
			final BankListForReportDao bankListForReportDao) {
		this.bankListForReportDao = bankListForReportDao;
	}
}
