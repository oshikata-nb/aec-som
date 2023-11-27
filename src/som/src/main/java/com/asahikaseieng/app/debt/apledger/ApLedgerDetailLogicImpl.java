/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apledger;

import java.util.List;

import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerDetail;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerDetailDao;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerDetailPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * 買掛元帳詳細ロジッククラス
 * @author tosco
 */
public class ApLedgerDetailLogicImpl implements ApLedgerDetailLogic {

	private ApLedgerDetailDao apLedgerDetailDao;

	/**
	 * コンストラクタ.買掛元帳詳細ロジック
	 */
	public ApLedgerDetailLogicImpl() {
	}

	/**
	 * 検索処理を行う.買掛元帳（上段）
	 * @param payableNo payableNo
	 * @param targetKbn 対象区分
	 * @return ApLedgerDetail 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public ApLedgerDetail getSearchDetail(final String payableNo,
			final String targetKbn) throws NoDataException {

		checkParams(payableNo);

		final ApLedgerDetail bean = apLedgerDetailDao.getSearchDetail(
			payableNo, targetKbn);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 検索処理を行う.買掛元帳（下段）
	 * @param condition condition
	 * @param targetKbn 対象区分
	 * @return List<ApLedgerDetail> 詳細データ
	 * @throws Exception Exception
	 * @throws NoDataException NoDataException
	 */
	public List<ApLedgerDetail> getSearchDetailTable(
			final ApLedgerDetailPagerCondition condition, final String targetKbn)
			throws Exception, NoDataException {

		checkParamsTable(condition);

		final List<ApLedgerDetail> list = apLedgerDetailDao
				.getSearchDetailTable(condition, targetKbn);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.getSearchDetail
	 * @param depositNo 検索条件.買掛番号
	 */
	private void checkParams(final String payableNo) {

		if (payableNo == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchDetail");
		}
	}

	/**
	 * パラメータチェック.getSearchDetail
	 * @param condition 検索条件
	 */
	private void checkParamsTable(final ApLedgerDetailPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchDetailTable");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * ApLedgerDetailDaoを設定します。
	 * 
	 * @param apLedgerDetailDao ApLedgerDetailDao
	 */
	public void setApLedgerDetailDao(final ApLedgerDetailDao apLedgerDetailDao) {
		this.apLedgerDetailDao = apLedgerDetailDao;
	}

}
