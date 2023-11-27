/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arledger;

import java.util.List;

import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalanceList;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerList;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * ArLedgerListLogic.売掛元帳
 * @author tosco
 */
public interface ArLedgerListLogic {

	/**
	 * 検索処理を行う.売掛元帳
	 * @param condition condition
	 * @return ArBalanceList 一覧データ
	 * @throws NoDataException NoDataException
	 */
	List<ArLedgerList> getSearchList(final ArLedgerPagerCondition condition) throws NoDataException;

	/**
	 * 検索処理を行う.MAX(締め日)の年月
	 * @param sectionCd 部門コード
	 * @return ArBalanceList 詳細データ
	 * @throws NoDataException NoDataException
	 */
	ArBalanceList getSearchDate(final String sectionCd) throws NoDataException;

}
