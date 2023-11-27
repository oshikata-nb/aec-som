/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.search.itemqueue;

import java.util.List;

/**
 * 品目マスタキュー検索(ポップアップ)Daoクラス
 * @author tosco
 */
public interface ItemQueueSearchListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.search.itemqueue.ItemQueueSearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 品目マスタキュー検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<ItemQueueSearchList> 検索結果リスト
	 */
	List<ItemQueueSearchList> getSearchList(final ItemQueueSearchListPagerCondition condition);
}
