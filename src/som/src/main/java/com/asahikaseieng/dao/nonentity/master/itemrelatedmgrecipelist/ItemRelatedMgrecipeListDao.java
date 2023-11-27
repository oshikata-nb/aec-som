/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemrelatedmgrecipelist;

import java.util.List;

/**
 * ItemRelatedMgrecipeListDaoクラス
 * @author t0011036
 */
public interface ItemRelatedMgrecipeListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemrelatedmgrecipelist.ItemRelatedMgrecipeList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "itemCd";

	/**
	 * Listメソッド
	 * 
	 * @param itemCd itemCd
	 * @return List<ItemRelatedMgrecipeList>
	 */
	List<ItemRelatedMgrecipeList> getList(final String itemCd);
}
