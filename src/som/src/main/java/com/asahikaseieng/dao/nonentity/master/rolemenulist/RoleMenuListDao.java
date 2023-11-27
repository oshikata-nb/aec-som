/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolemenulist;

import java.util.List;

/**
 * RoleMenuListDaoクラス
 * @author t0011036
 */
public interface RoleMenuListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.rolemenulist.RoleMenuList.class;

	/** ARGSアノテーション getMenuList */
	String getMenuList_ARGS = "";

	/**
	 * Listメソッド
	 * 
	 * @return List<RoleMenuList>
	 */
	List<RoleMenuList> getMenuList();
}
