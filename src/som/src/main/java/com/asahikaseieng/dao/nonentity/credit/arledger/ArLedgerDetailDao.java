/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arledger;

import java.util.List;

/**
 * 
 * ArLedgerListDao．売掛元帳
 * @author tosco
 */
public interface ArLedgerDetailDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerDetail.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchDetail(). */
	String getSearchDetail_ARGS = "depositNo,targetKbn";

	/**
	 * 詳細取得.
	 * @param depositNo 売掛番号
	 * @param targetKbn 対象区分
	 * @return ArLedgerDetail 売掛元帳詳細
	 */
	ArLedgerDetail getSearchDetail(String depositNo, String targetKbn);

	/** ARGSアノテーション getSearchDetailTable(). */
	String getSearchDetailTable_ARGS = "condition,targetKbn";

	/**
	 * 詳細一覧取得.
	 * @param condition 検索条件
	 * @param targetKbn 対象区分
	 * @return List<ArLedgerDetail> 売掛元帳詳細一覧
	 */
	List<ArLedgerDetail> getSearchDetailTable(ArLedgerDetailPagerCondition condition, String targetKbn);

}
