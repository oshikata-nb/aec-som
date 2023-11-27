/*
 * Created on 2009/03/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongrolelist;

import java.util.List;

/**
 * BelongRoleListDaoクラス
 * @author t0011036
 */
public interface BelongRoleListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.belongrolelist.BelongRoleList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<BelongRoleList>
	 */
	List<BelongRoleList> getList(final BelongRoleListPagerCondition condition);
}
