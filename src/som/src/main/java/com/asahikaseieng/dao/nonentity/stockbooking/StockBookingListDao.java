/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.stockbooking;

import java.util.List;

/**
 * 検査待ち在庫計上一覧画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface StockBookingListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.stockbooking.StockBookingList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 検査待ち在庫計上画面　一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<StockBookingList> 検索結果リスト
	 */
	List<StockBookingList> getSearchList(final StockBookingPagerCondition condition);

}
