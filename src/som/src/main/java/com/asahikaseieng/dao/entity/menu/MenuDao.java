/*
 * Created on Tue Mar 27 15:06:30 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.menu;

import java.util.List;

/**
 * MenuDaoインターフェース.
 * @author jbd
 */
public interface MenuDao {

	/** BEANアノテーション. */
	Class BEAN = Menu.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Menu
	 * @return Insert件数
	 */
	int insert(final Menu bean);

	/**
	 * Update.
	 * @param bean Menu
	 * @return Update件数
	 */
	int update(final Menu bean);

	/**
	 * Delete.
	 * @param bean Menu
	 * @return Delete件数
	 */
	int delete(final Menu bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "MENU_ID";

	/**
	 * エンティティ取得.
	 * @param menuId menuId
	 * @return Menu
	 */
	Menu getEntity(final String menuId);

	/**
	 * リスト取得.
	 * @return Menuのリスト
	 */
	List<Menu> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
	/** ARGSアノテーション getMenuList */
	String getMenuList_ARGS = "tantoRoleIds, belongRoleIds";

	/**
	 * メニューList取得メソッド
	 * 
	 * @param tantoRoleIds String[]
	 * @param belongRoleIds String[]
	 * @return List
	 */
	List<Menu> getMenuList(final String[] tantoRoleIds, final String[] belongRoleIds);

}
