/*
 * Created on 2007/04/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.menu;

import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

import com.asahikaseieng.dao.entity.menu.Menu;

/**
 * メニューロジック Mockクラス.
 * @author jbd
 */
public class MockMenuLogicImpl implements MenuLogic {

	/**
	 * コンストラクタ.
	 */
	public MockMenuLogicImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public SortedSet<Menu> getMenus(final String tantoCd) {

		SortedSet<Menu> menus = createMenus();
		return menus;
	}

	/**
	 * {@inheritDoc}
	 */
	public SortedSet<Menu> getMenus(final String[] tantoRoleIds,
			final String[] belongRoleIds) {

		SortedSet<Menu> menus = createMenus();
		return menus;
	}

	/**
	 * Menuを作成する.
	 * @return Menu
	 */
	private SortedSet<Menu> createMenus() {

		SortedSet<Menu> menus = new TreeSet<Menu>(Menu.createSortComparator());
		int count = 0;
		// １件目：
		Menu menu = new Menu();
		menu.setMenuId(new BigDecimal(count));
		menu.setParentMenuId(null);
		menu.setMenuName("name01");
		menu.setAction(null);
		menu.setSortNo(BigDecimal.ZERO);
		// menu.setMenuType(getMenuType(count, true));
		menu.setParent(null);

		menus.add(menu);
		count++;

		// ２件目
		menu = new Menu();
		menu.setMenuId(new BigDecimal(count));
		menu.setParentMenuId(null);
		menu.setMenuName("name02");
		menu.setAction(null);
		menu.setSortNo(new BigDecimal(1));
		// menu.setMenuType(getMenuType(count, false));
		menu.setParent(null);

		count++;

		Menu childMenu = new Menu();
		childMenu.setMenuId(new BigDecimal(count));
		childMenu.setParentMenuId(new BigDecimal(2));
		childMenu.setMenuName("name03");
		childMenu.setAction("test.do?op=init");
		childMenu.setSortNo(BigDecimal.ZERO);
		// childMenu.setMenuType(getMenuType(count, true));
		childMenu.setParent(menu);

		menus.add(menu);

		return menus;
	}

	/**
	 * メニュー種別を取得する.
	 * @return MenuType
	 */
	// private MenuType getMenuType(final int count, final boolean isFile) {
	//
	// MenuType menuType = new MenuType();
	// menuType.setMenuTypeId(new BigDecimal(count));
	// // ファイル or ディレクトリ
	// if (isFile) {
	// menuType.setImgName("page.gif");
	// menuType.setMenuTypeName("FILE");
	// menuType.setFileKbn("1");
	// } else {
	// menuType.setMenuTypeName("DIRECTORY");
	// menuType.setOpenImgName("folderopen.gif");
	// menuType.setCloseImgName("folder.gif");
	// menuType.setFileKbn("0");
	// }
	// return menuType;
	// }
}
