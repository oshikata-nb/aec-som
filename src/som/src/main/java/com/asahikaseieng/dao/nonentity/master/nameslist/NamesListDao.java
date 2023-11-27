/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.nameslist;

import java.util.List;

/**
 * NamesListDaoクラス
 * @author t0011036
 */
public interface NamesListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.nameslist.NamesList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<NamesList>
	 */
	List<NamesList> getList(final NamesListPagerCondition condition);
}
