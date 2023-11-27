/*
 * Created on 2007/04/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;

/**
 * MenuDaoのMockクラス
 * @author jbd
 */
public class MockMenuDao implements MenuDao {

	/**
	 * デフォルトコンストラクタ
	 */
	public MockMenuDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int insert(final Menu bean) {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	public int update(final Menu bean) {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	public int delete(final Menu bean) {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	public Menu getEntity(final String id) {
		if (Constants.TEST_PARAMETER_NODATA.equals(id)) {
			return null;
		}
		Menu menu = new Menu();
		menu.setMenuId(new BigDecimal(1));
		menu.setParentMenuId(null);
		menu.setMenuName("name01");
		menu.setAction(null);
		menu.setSortNo(BigDecimal.ZERO);
		// menu.setMenuType(getMenuType(1, true));
		menu.setParent(null);
		return menu;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Menu> getList() {
		List<Menu> list = new ArrayList<Menu>();

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

		list.add(menu);
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

		list.add(menu);

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Menu> getMenuList(final String[] tantoRoleIds,
			final String[] belongRoleIds) {
		List<Menu> list = new ArrayList<Menu>();

		int count = 0;
		// １件目：

		Menu menu = new Menu();
		menu.setMenuId(new BigDecimal(count));
		menu.setParentMenuId(null);
		menu.setMenuName("name01");
		menu.setAction(null);
		menu.setSortNo(BigDecimal.ZERO);
		menu.setParent(null);

		list.add(menu);
		count++;

		// ２件目
		menu = new Menu();
		menu.setMenuId(new BigDecimal(count));
		menu.setParentMenuId(null);
		menu.setMenuName("name02");
		menu.setAction(null);
		menu.setSortNo(new BigDecimal(1));
		menu.setParent(null);

		count++;

		Menu childMenu = new Menu();
		childMenu.setMenuId(new BigDecimal(count));
		childMenu.setParentMenuId(new BigDecimal(2));
		childMenu.setMenuName("name03");
		childMenu.setAction("test.do?op=init");
		childMenu.setSortNo(BigDecimal.ZERO);
		childMenu.setParent(menu);

		list.add(menu);

		return list;
	}

	// /**
	// * メニュー種別を取得する
	// * @return MenuType
	// */
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
