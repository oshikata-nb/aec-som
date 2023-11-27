/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arledger;

import java.util.List;

import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerDetail;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerDetailDao;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerDetailPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * 売掛元帳詳細ロジッククラス
 * @author tosco
 */
public class ArLedgerDetailLogicImpl implements ArLedgerDetailLogic {

	private ArLedgerDetailDao arLedgerDetailDao;

	/**
	 * コンストラクタ.売掛元帳詳細ロジック
	 */
	public ArLedgerDetailLogicImpl() {
	}

	/**
	 * 検索処理を行う.売掛元帳（上段）
	 * @param depositNo depositNo
	 * @param targetKbn 対象区分
	 * @return ArLedgerDetail 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public ArLedgerDetail getSearchDetail(final String depositNo, final String targetKbn) throws NoDataException {

		checkParams(depositNo);

		final ArLedgerDetail bean = arLedgerDetailDao.getSearchDetail(depositNo, targetKbn);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 検索処理を行う.売掛元帳（下段）
	 * @param condition condition
	 * @param targetKbn 対象区分
	 * @return List<ArLedgerDetail> 詳細データ
	 * @throws Exception Exception
	 * @throws NoDataException NoDataException
	 */
	public List<ArLedgerDetail> getSearchDetailTable(
		final ArLedgerDetailPagerCondition condition
		, final String targetKbn) throws Exception, NoDataException {

		checkParamsTable(condition);

		final List<ArLedgerDetail> list = arLedgerDetailDao.getSearchDetailTable(condition, targetKbn);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.getSearchDetail
	 * @param depositNo 検索条件.売掛番号
	 */
	private void checkParams(final String depositNo) {

		if (depositNo == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchDetail");
		}
	}

	/**
	 * パラメータチェック.getSearchDetail
	 * @param condition 検索条件
	 */
	private void checkParamsTable(final ArLedgerDetailPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchDetailTable");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * ArLedgerDetailDaoを設定します。

	 * @param arLedgerDetailDao ArLedgerDetailDao
	 */
	public void setArLedgerDetailDao(final ArLedgerDetailDao arLedgerDetailDao) {
		this.arLedgerDetailDao = arLedgerDetailDao;
	}

}
