/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apledger;

import java.util.List;

import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerList;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerListDao;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerPagerCondition;
import com.asahikaseieng.dao.nonentity.debt.aprollback.ApRollback;
import com.asahikaseieng.dao.nonentity.debt.aprollback.ApRollbackDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * 買掛元帳ロジッククラス
 * @author tosco
 */
public class ApLedgerListLogicImpl implements ApLedgerListLogic {

	private ApLedgerListDao apLedgerListDao;

	private ApRollbackDao apRollbackDao;

	/**
	 * コンストラクタ.買掛元帳ロジック
	 */
	public ApLedgerListLogicImpl() {
	}

	/**
	 * 検索処理を行う.買掛元帳
	 * @param condition condition
	 * @return List<ArLedgerList> 一覧データ
	 * @throws NoDataException NoDataException
	 */
	public List<ApLedgerList> getSearchList(
			final ApLedgerPagerCondition condition) throws NoDataException {

		checkParams(condition);

		final List<ApLedgerList> bean = apLedgerListDao
				.getSearchList(condition);

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
	private void checkParams(final ApLedgerPagerCondition condition) {

		if (condition.getSrhTargetMonth() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * ApLedgerListDaoを設定します。
	 * @param apLedgerListDao apLedgerListDao
	 */
	public void setApLedgerListDao(final ApLedgerListDao apLedgerListDao) {
		this.apLedgerListDao = apLedgerListDao;
	}

	/**
	 * apRollbackDaoを設定します。
	 * @param apRollbackDao apRollbackDao
	 */
	public void setApRollbackDao(final ApRollbackDao apRollbackDao) {
		this.apRollbackDao = apRollbackDao;
	}

}
