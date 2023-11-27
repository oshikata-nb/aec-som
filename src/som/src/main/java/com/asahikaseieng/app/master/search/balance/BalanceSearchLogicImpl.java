/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.balance;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceList;
import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceListDao;
import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.balancelistdetail.BalanceListDetail;
import com.asahikaseieng.dao.nonentity.master.balancelistdetail.BalanceListDetailDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 帳合検索ロジック 実装クラス.
 * @author t0011036
 */
public class BalanceSearchLogicImpl implements BalanceSearchLogic {

	private BalanceListDao balanceListDao;

	private BalanceListDetailDao balanceListDetailDao;

	/**
	 * コンストラクタ.
	 */
	public BalanceSearchLogicImpl() {
	}

	/**
	 * {@inheritDoc}
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
		if (null == condition) {
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

	/* -------------------- setter -------------------- */

	/**
	 * balanceListDaoを設定します。
	 * @param balanceListDao balanceListDao
	 */
	public void setBalanceListDao(final BalanceListDao balanceListDao) {
		this.balanceListDao = balanceListDao;
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
