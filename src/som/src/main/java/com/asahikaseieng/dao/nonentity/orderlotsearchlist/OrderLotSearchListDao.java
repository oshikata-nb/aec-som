/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderlotsearchlist;

import java.util.List;

/**
 * ロット検索(ポップアップ)Daoクラス
 * @author tosco
 */
public interface OrderLotSearchListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.orderlotsearchlist.OrderLotSearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";
	/**
	 * ロット検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<OrderLotSearchList> 検索結果一覧
	 */
	List<OrderLotSearchList> getSearchList(final OrderLotSearchListPagerCondition condition);

}
