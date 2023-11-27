/*
 * Created on Tue Mar 27 15:06:30 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.menu;

import java.util.List;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.extension.dataset.DataSet;

/**
 * MenuDaoクラスのテストケース
 * @author jbd
 */
public final class MenuDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(MenuDaoTest.class);

	/** Daoオブジェクト */
	private com.asahikaseieng.dao.entity.menu.MenuDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public MenuDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * addChideのテスト
	 */
	public void testChildTx() {
		Menu m = new Menu();
		try {
			m.addChild(null);
			fail("Should raise an " + IllegalArgumentException.class);
		} catch (IllegalArgumentException e) {

		}
	}

	/**
	 * エンティティのテスト.
	 */
	public void testEntityTx() {

		Menu lhs = new Menu();
		Menu rhs = new Menu();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());

		/* 初期値データ書き込み */
		readXlsAllReplaceDb("MenuDao_init.xls");

		Menu bean = dao.getEntity("1");

		/* 検証用データ読み込み */
		DataSet expected = readXls("MenuDao_expected.xls", "getEntity");
		/* 取得内容 検証 */
		assertEquals(expected, bean);

		/* データが取得できない場合 */
		bean = null;
		bean = dao.getEntity("9999999999");
		assertNull(bean);
	}

	/**
	 * getListのテスト.
	 */
	public void testGetListTx() {

		/* 初期値データ書き込み */
		readXlsAllReplaceDb("MenuDao_init.xls");

		/* データが取得できる場合 */
		List<Menu> list = dao.getList();

		/* 検証用データ読み込み */
		DataSet expected = readXls("MenuDao_expected.xls", "getList");
		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		list = null;
		/* テーブルの中身を消す */
		deleteTable("Menu");

		list = dao.getList();

		assertTrue(list.size() <= 0);
	}
}
