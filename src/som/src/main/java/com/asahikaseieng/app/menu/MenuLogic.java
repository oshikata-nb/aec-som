/*
 * Created on 2007/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.menu;

import java.util.SortedSet;

import com.asahikaseieng.dao.entity.menu.Menu;

/**
 * メニューロジック Interface.
 * @author jbd
 */
public interface MenuLogic {

	/**
	 * メニューを取得.
	 * @param tantoRoleIds 担当者に紐づくロール
	 * @param belongRoleIds 組織・役職に紐づくロール
	 * @return SortedSet<Menu> メニューリスト	 */
	SortedSet<Menu> getMenus(final String[] tantoRoleIds, final String[] belongRoleIds);
}
