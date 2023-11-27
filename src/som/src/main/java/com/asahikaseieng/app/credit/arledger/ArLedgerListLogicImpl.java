/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arledger;

import java.util.List;

import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalanceList;
import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalanceListDao;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerList;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerListDao;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * 売掛元帳ロジッククラス
 * @author tosco
 */
public class ArLedgerListLogicImpl implements ArLedgerListLogic {

	private ArLedgerListDao arLedgerListDao;

	private ArBalanceListDao arBalanceListDao;

	/**
	 * コンストラクタ.売掛元帳ロジック
	 */
	public ArLedgerListLogicImpl() {
	}

	/**
	 * 検索処理を行う.売掛元帳
	 * @param condition condition
	 * @return ArBalanceList 一覧データ
	 * @throws NoDataException NoDataException
	 */
	public List<ArLedgerList> getSearchList(
			final ArLedgerPagerCondition condition) throws NoDataException {

		checkParams(condition);

		final List<ArLedgerList> bean = arLedgerListDao
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
	private void checkParams(final ArLedgerPagerCondition condition) {

		if (condition.getSrhTargetMonth() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * ArLedgerListDaoを設定します。
	 * 
	 * @param arLedgerListDao arLedgerListDao
	 */
	public void setArLedgerListDao(final ArLedgerListDao arLedgerListDao) {
		this.arLedgerListDao = arLedgerListDao;
	}

	/**
	 * arBalanceListDaoを設定します。
	 * @param arBalanceListDao arBalanceListDao
	 */
	public void setArBalanceListDao(final ArBalanceListDao arBalanceListDao) {
		this.arBalanceListDao = arBalanceListDao;
	}

}
