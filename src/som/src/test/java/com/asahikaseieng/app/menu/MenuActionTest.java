/*
 * Created on 2007/04/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.menu;

import com.asahikaseieng.Constants;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.test.AbstractS2StrutsTestCase;

/**
 * メニューActionテストクラス.
 * @author jbd
 */
public class MenuActionTest extends AbstractS2StrutsTestCase {

	/**
	 * コンストラクタ.
	 * @param testName テスト名
	 */
	public MenuActionTest(final String testName) {
		super(testName, "/Menu.do");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		include("actiontest.dicon");
		getSession().setAttribute(Constants.LOGIN_KEY, new LoginInfo());
	}

	/**
	 * initテスト.
	 */
	public void testInit() {

		/* 正常に遷移できる場合 */
		super.reset();
		addRequestParameter("op", "init");
		actionPerform();
		verifyNoActionErrors();
		verifyForwardPath("/jsp/common/menu.jsp");

		/* メニューがnullはありえない */
		MenuForm form = (MenuForm) getActionForm();
		assertNotNull(form.getMenus());
	}

	// /**
	// * getJsテスト.
	// */
	// public void testGetJs() {
	//
	// super.reset();
	// addRequestParameter("op", "init");
	// actionPerform();
	//
	// MenuForm form = (MenuForm) getActionForm();
	//
	// SortedSet<Menu> menus = new TreeSet<Menu>();
	//
	// MenuType mt = new MenuType();
	// Menu m1 = new Menu();
	// m1.setMenuId(new BigDecimal(1));
	// m1.setMenuType(mt);
	// Menu m2 = new Menu();
	// m2.setMenuId(new BigDecimal(2));
	// m2.setMenuType(mt);
	// m1.addChild(m2);
	// menus.add(m1);
	//
	// form.setMenus(menus);
	// assertTrue(form.getJs().contains("menu1"));
	// assertTrue(form.getJs().contains("menu2"));
	// }
}
