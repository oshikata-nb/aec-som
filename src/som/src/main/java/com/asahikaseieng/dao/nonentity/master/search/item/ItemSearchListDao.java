/*
 * Created on 2009/03/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.search.item;

import java.util.List;

/**
 * 品目マスタ検索(ポップアップ)Daoクラス
 * @author tosco
 */
public interface ItemSearchListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.search.item.ItemSearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 品目マスタ検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<ItemSearchList> 検索結果リスト
	 */
	List<ItemSearchList> getSearchList(final ItemSearchListPagerCondition condition);
}
