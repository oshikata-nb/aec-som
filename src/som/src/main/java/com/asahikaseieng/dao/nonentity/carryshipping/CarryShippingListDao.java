/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.carryshipping;

import java.util.List;

/**
 * 運送店別出荷指図画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface CarryShippingListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.carryshipping.CarryShippingList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 運送店別出荷指図画面一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<CarryShippingList> 検索結果リスト
	 */
	List<CarryShippingList> getSearchList(final CarryShippingPagerCondition condition);

}
