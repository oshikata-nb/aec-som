/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.categorylist;

import java.util.List;

/**
 * CategoryListDaoクラス
 * @author t0011036
 */
public interface CategoryListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.categorylist.CategoryList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<CategoryList>
	 */
	List<CategoryList> getList(final CategoryListPagerCondition condition);
}
