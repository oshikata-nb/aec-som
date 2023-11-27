/*
 * Created on Thu May 07 10:03:07 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.purchasemateinjection;

import java.util.List;

/**
 * PurchaseMateInjectionDaoインターフェース.
 * @author kanri-user
 */
public interface PurchaseMateInjectionDao {

	/** BEANアノテーション. */
	Class BEAN = PurchaseMateInjection.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean PurchaseMateInjection
	 * @return Insert件数
	 */
	int insert(PurchaseMateInjection bean);

	/**
	 * Update.
	 * @param bean PurchaseMateInjection
	 * @return Update件数
	 */
	int update(PurchaseMateInjection bean);

	/**
	 * Delete.
	 * @param bean PurchaseMateInjection
	 * @return Delete件数
	 */
	int delete(PurchaseMateInjection bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "BUY_SUBCONTRACT_ORDER_NO,LINE_NO,RECIPE_ID,SEQ,STEP_NO";

	/**
	 * エンティティ取得.
	 * @param buySubcontractOrderNo buySubcontractOrderNo
	 * @param lineNo lineNo
	 * @param recipeId recipeId
	 * @param seq seq
	 * @param stepNo stepNo
	 * @return PurchaseMateInjection
	 */
	PurchaseMateInjection getEntity(String buySubcontractOrderNo,
			java.math.BigDecimal lineNo, java.math.BigDecimal recipeId,
			java.math.BigDecimal seq, java.math.BigDecimal stepNo);

	/** QUERYアノテーション deleteByBuySubOrdNoSeqZero(). */
	String deleteByBuySubOrdNoSeqZero_QUERY = "SEQ = 0 AND BUY_SUBCONTRACT_ORDER_NO = ?";

	/**
	 * 一括削除処理.(発注番号をKEYに削除)
	 * @param buySubcontractOrderNo 発注番号
	 * @return Delete件数
	 */
	int deleteByBuySubOrdNoSeqZero(String buySubcontractOrderNo);

	/** QUERYアノテーション deleteByBuySubOrdNo(). */
	String deleteByBuySubOrdNo_QUERY = "BUY_SUBCONTRACT_ORDER_NO = ?";

	/**
	 * 一括削除処理.(発注番号をKEYに削除)
	 * @param buySubcontractOrderNo 発注番号
	 * @return Delete件数
	 */
	int deleteByBuySubOrdNo(String buySubcontractOrderNo);

	/** QUERYアノテーション getList(). */
	String getList_ARGS = "BUY_SUBCONTRACT_ORDER_NO";

	/**
	 * リスト取得
	 * @param buySubcontractOrderNo 発注番号
	 * @return List
	 */
	List<PurchaseMateInjection> getList(String buySubcontractOrderNo);
}
