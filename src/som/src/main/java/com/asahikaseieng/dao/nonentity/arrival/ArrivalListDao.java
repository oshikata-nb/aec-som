/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.arrival;

import java.util.List;

/**
 * 入荷一覧画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface ArrivalListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.arrival.ArrivalList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<ArrivalList> 検索結果リスト
	 */
	List<ArrivalList> getSearchList(final ArrivalListPagerCondition condition);

}
