/*
 * Created on Mon Apr 02 20:09:58 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.menutype;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.extension.dataset.DataSet;

/**
 * MenuTypeDaoクラスのテストケース
 * @author jbd
 */
public final class MenuTypeDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(MenuTypeDaoTest.class);

	/** Daoオブジェクト */
	private com.asahikaseieng.dao.entity.menutype.MenuTypeDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public MenuTypeDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * エンティティのテスト.
	 */
	public void testEntityTx() {

		MenuType lhs = new MenuType();
		MenuType rhs = new MenuType();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());

		/* 初期値データ書き込み */
		readXlsAllReplaceDb("MenuTypeDao_init.xls");

		MenuType bean = dao.getEntity(new BigDecimal(1));

		/* 検証用データ読み込み */
		DataSet expected = readXls("MenuTypeDao_expected.xls", "getEntity");
		/* 取得内容 検証 */
		assertEquals(expected, bean);

		/* データが取得できない場合 */
		bean = null;
		bean = dao.getEntity(new BigDecimal(999999999));
		assertNull(bean);
	}

	/**
	 * getListのテスト.
	 */
	public void testGetListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("MenuTypeDao_init.xls");

		/* データが取得できる場合 */
		List<MenuType> list = dao.getList();

		/* 検証用データ読み込み */
		DataSet expected = readXls("MenuTypeDao_expected.xls", "getList");
		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		list = null;
		/* テーブルの中身を消す */
		deleteTable("MENU_TYPE");

		list = dao.getList();

		assertTrue(list.size() <= 0);
	}
}
