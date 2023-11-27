/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.purchasedelivery;

import java.util.List;

/**
 * 納期回答検索画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface PurchaseDeliveryListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<PurchaseDeliveryList> 検索結果リスト
	 */
	List<PurchaseDeliveryList> getSearchList(final PurchaseDeliveryListPagerCondition condition);

}
