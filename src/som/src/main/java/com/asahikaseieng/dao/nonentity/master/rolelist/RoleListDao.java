/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolelist;

import java.util.List;

/**
 * RoleListDaoクラス
 * @author t0011036
 */
public interface RoleListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.rolelist.RoleList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<RoleList>
	 */
	List<RoleList> getList(final RoleListPagerCondition condition);
}
