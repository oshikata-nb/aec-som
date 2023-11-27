/*
 * Created on 2008/08/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arledger;

import java.util.List;

import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerDetail;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerDetailPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * 売掛元帳詳細Logicクラス
 * @author tosco
 */
public interface ArLedgerDetailLogic {

	/**
	 * 売掛元帳詳細（上段）検索を行う.
	 * @param depositNo depositNo
	 * @param targetKbn 対象区分
	 * @return ArLedgerDetail 詳細データ
	 * @throws NoDataException NoDataException
	 */
	ArLedgerDetail getSearchDetail(final String depositNo, final String targetKbn) throws NoDataException;

	/**
	 * 売掛元帳詳細一覧（下段）検索を行う.
	 * @param condition condition
	 * @param targetKbn 対象区分
	 * @return List<ArLedgerDetail> 詳細データ
	 * @throws Exception Exception
	 * @throws NoDataException NoDataException
	 */
	List<ArLedgerDetail> getSearchDetailTable(final ArLedgerDetailPagerCondition condition, final String targetKbn)
			throws Exception, NoDataException;

}
