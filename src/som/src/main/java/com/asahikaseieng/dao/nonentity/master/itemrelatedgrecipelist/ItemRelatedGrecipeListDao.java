/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemrelatedgrecipelist;

import java.util.List;

/**
 * ItemRelatedGrecipeListDaoクラス
 * @author t0011036
 */
public interface ItemRelatedGrecipeListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemrelatedgrecipelist.ItemRelatedGrecipeList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "itemCd";

	/**
	 * Listメソッド
	 * 
	 * @param itemCd itemCd
	 * @return List<ItemRelatedGrecipeList>
	 */
	List<ItemRelatedGrecipeList> getList(final String itemCd);
}
