/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.balance;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceList;
import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceListDao;
import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.balancelistdetail.BalanceListDetail;
import com.asahikaseieng.dao.nonentity.master.balancelistdetail.BalanceListDetailDao;
import com.asahikaseieng.dao.nonentity.master.balancelistforreport.BalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.balancelistforreport.BalanceListForReport;
import com.asahikaseieng.dao.nonentity.master.balancelistforreport.BalanceListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 帳合一覧ロジック 実装クラス.
 * @author t0011036
 */
public class BalanceListLogicImpl implements BalanceListLogic {

	private BalanceListDao balanceListDao;

	private BalanceListDetailDao balanceListDetailDao;

	private BalanceListForReportDao balanceListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public BalanceListLogicImpl() {
	}

	/**
	 * 帳合一覧検索
	 * @param condition 検索条件
	 * @return List<BalanceList>
	 * @throws NoDataException NoDataException
	 */
	public List<BalanceList> getList(final BalanceListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<BalanceList> list = balanceListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final BalanceListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 帳合一覧詳細検索
	 * @param balanceCd 帳合コード
	 * @param balanceType 区分
	 * @return BalanceListDetail
	 */
	public BalanceListDetail getEntity(final String balanceCd,
			final BigDecimal balanceType) {
		BalanceListDetail bean = balanceListDetailDao.getEntity(balanceCd,
			balanceType);
		return bean;
	}

	/**
	 * 帳合一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<BalanceListForReport>
	 */
	public List<BalanceListForReport> getListForReport(
			final BalanceListConditionForReport condition) {
		List<BalanceListForReport> list = balanceListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * balanceListDaoを設定します。
	 * @param balanceListDao balanceListDao
	 */
	public void setBalanceListDao(final BalanceListDao balanceListDao) {
		this.balanceListDao = balanceListDao;
	}

	/**
	 * balanceListForReportDaoを設定します。
	 * @param balanceListForReportDao balanceListForReportDao
	 */
	public void setBalanceListForReportDao(
			final BalanceListForReportDao balanceListForReportDao) {
		this.balanceListForReportDao = balanceListForReportDao;
	}

	/**
	 * balanceListDetailDaoを設定します。
	 * @param balanceListDetailDao balanceListDetailDao
	 */
	public void setBalanceListDetailDao(
			final BalanceListDetailDao balanceListDetailDao) {
		this.balanceListDetailDao = balanceListDetailDao;
	}
}
