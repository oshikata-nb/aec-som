/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdelivery;

import java.util.List;

/**
 * 納入先検索(ポップアップ)Daoクラス
 * @author tosco
 */
public interface OrderDeliverySearchListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.orderdelivery.OrderDeliverySearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 納入先検索メソッド 
	 * 
	 * @param condition 検索条件
	 * @return List<OrderDeliverySearchList> 検索結果一覧
	 */
	List<OrderDeliverySearchList> getSearchList(final OrderDeliverySearchListPagerCondition condition);

}
