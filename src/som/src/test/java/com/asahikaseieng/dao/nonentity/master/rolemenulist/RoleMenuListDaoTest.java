/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolemenulist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RoleMenuListDaoクラスのテストケース
 * @author t0011036
 */
public final class RoleMenuListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RoleMenuListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RoleMenuListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getMenuListのテスト
	 */
	public void testGetMenuListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("MenuHeadListDao_init.xls");

		/* getList 実行 */
		List<RoleMenuList> list = dao.getMenuList();

		/* 検証用データ読み込み */
		DataSet expected = readXls("RoleMenuListDao.expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("MENU_HEAD");

		list = dao.getMenuList();
		assertEquals(0, list.size());
	}
}
