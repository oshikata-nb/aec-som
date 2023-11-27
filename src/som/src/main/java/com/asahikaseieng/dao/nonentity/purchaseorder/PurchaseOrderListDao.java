/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.purchaseorder;

import java.util.List;

/**
 * 発注書一覧画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface PurchaseOrderListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.purchaseorder.PurchaseOrderList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 発注書一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<PurchaseOrderList> 検索結果リスト
	 */
	List<PurchaseOrderList> getSearchList(final PurchaseOrderPagerCondition condition);
}
