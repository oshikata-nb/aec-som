/*
 * Created on 2007/04/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.dao.entity.menu.Menu;
import com.asahikaseieng.dao.nonentity.authority.Authority;
import com.asahikaseieng.test.AbstractS2TestCase;

/**
 * MenuLogicImplのテストクラス.
 * @author jbd
 */
public class MenuLogicImplTest extends AbstractS2TestCase {

	// private MenuLogicImpl menuLogic;

	/**
	 * コンストラクタ.
	 * @param name string
	 */
	public MenuLogicImplTest(final String name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		include("logictest.dicon");
		this.getMenus();
		this.creteAuthorityList();
	}

	/**
	 * loginテスト.
	 * @throws NoDataException NoDataException
	 */
	// public void testGetMenus() throws NoDataException {
	//
	// MockInterceptor mi = new MockInterceptor();
	// menuLogic.setMenuDao((MenuDao) mi.createProxy(MenuDao.class));
	//
	// mi.setReturnValue("getList", getMenus());
	//
	// /* データが取得できる場合 */
	// mi.setReturnValue("getList", getMenus());
	// SortedSet<Menu> menus = null;
	// menus = menuLogic.getMenus("tantoCd");
	// /* 検証 */
	// assertTrue(0 <= menus.size());
	//
	// /* parentIdの実態が存在しない場合 */
	// List<Menu> list = getMenus();
	// list.get(2).setParentMenuId(new BigDecimal(99));
	// mi.setReturnValue("getList", list);
	// menus = menuLogic.getMenus("tantoCd");
	// assertEquals(2, menus.size());
	//
	// /* メニュー種別が存在しない場合 */
	// // メニュー種別が存在しない為非表示
	// list = getMenus();
	// list.get(2).setMenuType(null);
	// mi.setReturnValue("getList", list);
	// menus = menuLogic.getMenus("tantoCd");
	// assertEquals(2, menus.size());
	//
	// // 親にメニュー種別が存在しない為子も非表示
	// list = getMenus();
	// list.get(1).setMenuType(null);
	// mi.setReturnValue("getList", list);
	// menus = menuLogic.getMenus("tantoCd");
	// assertEquals(1, menus.size());
	//
	// /* ファイルなのにActionが存在しない場合 */
	// list = getMenus();
	// list.get(2).setAction(null);
	// mi.setReturnValue("getList", list);
	// menus = menuLogic.getMenus("tantoCd");
	// assertEquals(2, menus.size());
	//
	// /* ファイルなのに子供が存在する場合 */
	// list = getMenus();
	// list.get(4).setParentMenuId(new BigDecimal(2));
	// mi.setReturnValue("getList", list);
	// menus = menuLogic.getMenus("tantoCd");
	// assertEquals(2, menus.size());
	//
	// /* ディレクトリなのにActionが存在する場合 */
	// list = getMenus();
	// list.get(1).setAction("test.do");
	// mi.setReturnValue("getList", list);
	// menus = menuLogic.getMenus("tantoCd");
	// assertEquals(1, menus.size());
	//
	// /* 権限テスト */
	//
	// /* (getMenusのデータに合わせて、id=0・2・4に権限を付与) */
	// MockInterceptor miAuth = new MockInterceptor();
	// menuLogic.setAuthorityLogic((AuthorityLogic) miAuth
	// .createProxy(AuthorityLogic.class));
	//
	// mi.setReturnValue("getList", getMenus()); // デフォルトなので5件
	//
	// // ①メニュー権限設定あり(Read)
	// List<Authority> ahtuList = creteAuthorityList();
	// miAuth.setReturnValue("getMenuAuthorityList", ahtuList);
	//
	// menus = menuLogic.getMenus("tantoCd");
	// // 検証：メニュールートは２つ
	// assertEquals(2, menus.size());
	// // 検証：メニュールート２の下のmenusは２つ
	// assertEquals(2, menus.last().getMenus().size());
	//
	// for (int i = 0; i < 2; i++) {
	//
	// // １：権限はDisable
	// // ２：権限が設定されていない
	// ahtuList = creteAuthorityList(); // 初期化
	//
	// // ②disableなメニューを削除：親がいない場合は自分を削除(id=0)
	// if (i == 0) {
	// ahtuList.get(0).setAuthorityKbn(Constants.AUTHORITY_DISABLE);
	// } else {
	// ahtuList.remove(0);
	// }
	// miAuth.setReturnValue("getMenuAuthorityList", ahtuList);
	// mi.setReturnValue("getList", getMenus()); // デフォルトなので5件
	//
	// menus = menuLogic.getMenus("tantoCd");
	// // 検証：メニュールートは１つ
	// assertEquals(1, menus.size());
	// // 検証：メニュールート２の下のmenusは２つ
	// assertEquals(2, menus.last().getMenus().size());
	//
	// // ③disableなメニューを削除：親の下の子供がいなくなる場合は親も削除(id=4)
	// if (i == 0) {
	// ahtuList.get(2).setAuthorityKbn(Constants.AUTHORITY_DISABLE);
	// } else {
	// ahtuList.remove(1); // ②で１つ減っている
	// }
	// miAuth.setReturnValue("getMenuAuthorityList", ahtuList);
	// mi.setReturnValue("getList", getMenus()); // デフォルトなので5件
	//
	// menus = menuLogic.getMenus("tantoCd");
	// // 検証：メニュールートは１つ
	// assertEquals(1, menus.size());
	// // 検証：メニュールート２の下のmenusは１つ
	// assertEquals(1, menus.last().getMenus().size());
	// }
	// }
	/**
	 * メニューリストを取得.
	 * @return List<Menu>
	 */
	private List<Menu> getMenus() {

		// テストデータモデル
		// id=0(s0)
		// id=1(s1) - id=2(s0)
		// ---------- id=3(s0) - id=4(s0)<br>

		List<Menu> list = new ArrayList<Menu>();

		// １件目：
		Menu menu = new Menu();
		menu.setMenuId(new BigDecimal(0));
		menu.setParentMenuId(null);
		menu.setMenuName("name01");
		menu.setAction("test01.do");
		menu.setSortNo(BigDecimal.ZERO);
		// menu.setMenuType(getMenuType(0, true));
		menu.setParent(null);

		list.add(menu);

		// ２件目
		menu = new Menu();
		menu.setMenuId(new BigDecimal(1));
		menu.setParentMenuId(null);
		menu.setMenuName("name02");
		menu.setAction(null);
		menu.setSortNo(new BigDecimal(1));
		// menu.setMenuType(getMenuType(1, false));
		menu.setParent(null);

		list.add(menu);

		// ３件目
		menu = new Menu();
		menu.setMenuId(new BigDecimal(2));
		menu.setParentMenuId(new BigDecimal(1));
		menu.setMenuName("name03");
		menu.setAction("test.do?op=init");
		menu.setSortNo(BigDecimal.ZERO); // Mene.createSortComparatorの「SortNoが＝の時」用
		// menu.setMenuType(getMenuType(2, true));
		menu.setParent(list.get(1));

		list.add(menu);

		// ４件目
		menu = new Menu();
		menu.setMenuId(new BigDecimal(3));
		menu.setParentMenuId(new BigDecimal(1));
		menu.setMenuName("name04");
		menu.setAction(null);
		menu.setSortNo(BigDecimal.ZERO);
		// menu.setMenuType(getMenuType(3, false));
		menu.setParent(null);

		list.add(menu);

		// ５件目
		menu = new Menu();
		menu.setMenuId(new BigDecimal(4));
		menu.setParentMenuId(new BigDecimal(3));
		menu.setMenuName("name04");
		menu.setAction("test.action");
		menu.setSortNo(BigDecimal.ZERO);
		// menu.setMenuType(getMenuType(4, true));
		menu.setParent(list.get(3));

		list.add(menu);

		return list;
	}

	// /**
	// * メニュー種別を取得.
	// * @return MenuType
	// */
	// private MenuType getMenuType(final int count, final boolean isFile) {
	//
	// MenuType bean = new MenuType();
	// bean.setMenuTypeId(new BigDecimal(count));
	// // ファイル or ディレクトリ
	// if (isFile) {
	// bean.setImgName("page.gif");
	// bean.setMenuTypeName("FILE");
	// bean.setFileKbn("1");
	// } else {
	// bean.setMenuTypeName("DIRECTORY");
	// bean.setOpenImgName("folderopen.gif");
	// bean.setCloseImgName("folder.gif");
	// bean.setFileKbn("0");
	// }
	// return bean;
	// }

	/**
	 * 権限リストを生成する getMenusに合わせて、id=0・2・4の権限を設定する.
	 * @return List<Authority>
	 */
	private List<Authority> creteAuthorityList() {
		List<Authority> list = new ArrayList<Authority>();
		for (int i = 0; i < 3; i++) {
			Authority bean = new Authority();
			// bean.setId("" + (2 * i));
			// bean.setAuthorityKbn(Constants.AUTHORITY_READ);
			list.add(bean);
		}
		return list;
	}
}
