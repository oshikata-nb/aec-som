/*
 * Created on 2008/08/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apledger;

import java.util.List;

import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerDetail;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerDetailPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * 買掛元帳詳細Logicクラス
 * @author tosco
 */
public interface ApLedgerDetailLogic {

	/**
	 * 買掛元帳詳細（上段）検索を行う.
	 * @param payableNo payableNo
	 * @param targetKbn 対象区分
	 * @return ApLedgerDetail 詳細データ
	 * @throws NoDataException NoDataException
	 */
	ApLedgerDetail getSearchDetail(final String payableNo,
			final String targetKbn) throws NoDataException;

	/**
	 * 買掛元帳詳細一覧（下段）検索を行う.
	 * @param condition condition
	 * @param targetKbn 対象区分
	 * @return List<ApLedgerDetail> 詳細データ
	 * @throws Exception Exception
	 * @throws NoDataException NoDataException
	 */
	List<ApLedgerDetail> getSearchDetailTable(
			final ApLedgerDetailPagerCondition condition, final String targetKbn)
			throws Exception, NoDataException;

}
