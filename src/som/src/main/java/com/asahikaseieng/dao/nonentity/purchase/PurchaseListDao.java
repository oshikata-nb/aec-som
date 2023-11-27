/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.purchase;

import java.util.List;

/**
 * 発注一覧画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface PurchaseListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.purchase.PurchaseList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 発注一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<PurchaseList> 検索結果リスト
	 */
	List<PurchaseList> getSearchList(final PurchasePagerCondition condition);

}
