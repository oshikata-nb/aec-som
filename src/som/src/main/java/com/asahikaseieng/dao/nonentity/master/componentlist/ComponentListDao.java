/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentlist;

import java.util.List;

/**
 * ComponentListDaoクラス
 * @author t0011036
 */
public interface ComponentListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.componentlist.ComponentList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<ComponentList>
	 */
	List<ComponentList> getList(final ComponentListPagerCondition condition);
}
