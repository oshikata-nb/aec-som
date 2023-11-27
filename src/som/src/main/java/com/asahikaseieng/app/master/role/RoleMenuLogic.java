/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.role;

import java.util.SortedSet;

import com.asahikaseieng.dao.nonentity.master.rolemenulist.RoleMenuList;

/**
 * メニューロジック Interface.
 * @author t0011036
 */
public interface RoleMenuLogic {

	/**
	 * メニューを取得.
	 * @param tantoCd 担当者コード
	 * 
	 * @return munu
	 */
	SortedSet<RoleMenuList> getMenus(final String tantoCd);
}
