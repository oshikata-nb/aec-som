/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apledger;

import java.util.List;

/**
 * 
 * ApLedgerListDao．買掛元帳
 * @author tosco
 */
public interface ApLedgerDetailDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerDetail.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchDetail(). */
	String getSearchDetail_ARGS = "payableNo, targetKbn";

	/**
	 * 詳細取得.
	 * @param payableNo 買掛番号
	 * @param targetKbn 対象区分
	 * @return ApLedgerDetail 買掛元帳詳細
	 */
	ApLedgerDetail getSearchDetail(String payableNo, String targetKbn);

	/** ARGSアノテーション getSearchDetailTable(). */
	String getSearchDetailTable_ARGS = "condition, targetKbn";

	/**
	 * 詳細一覧取得.
	 * @param condition 検索条件
	 * @param targetKbn 対象区分
	 * @return List<ApLedgerDetail> 買掛元帳詳細一覧
	 */
	List<ApLedgerDetail> getSearchDetailTable(
			ApLedgerDetailPagerCondition condition, String targetKbn);

}
