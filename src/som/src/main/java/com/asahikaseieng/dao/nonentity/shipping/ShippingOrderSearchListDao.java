/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shipping;

import java.util.List;

/**
 * 受注検索(ポップアップ)Daoクラス
 * @author tosco
 */
public interface ShippingOrderSearchListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shipping.ShippingOrderSearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";
	/**
	 * 受注検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingOrderSearchList> 検索結果一覧
	 */
	List<ShippingOrderSearchList> getSearchList(final ShippingOrderSearchListPagerCondition condition);

}
