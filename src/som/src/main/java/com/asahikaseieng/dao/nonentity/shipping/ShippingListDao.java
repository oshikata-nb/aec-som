/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shipping;

import java.util.List;

/**
 * 出荷指図メンテ 一覧画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface ShippingListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shipping.ShippingList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<ShippingList> 検索結果リスト
	 */
	List<ShippingList> getSearchList(final ShippingListPagerCondition condition);

}
