/*
 * Created on 2009/08/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.ordernameslist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OrderNamesListDaoクラスのテストケース
 * @author t0011036
 */
public final class OrderNamesListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private OrderNamesListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OrderNamesListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListのテスト
	 */
	public void testGetListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("OrderNamesListDao_init.xls");

		String nameDivision = "NAME_DIVISION01";

		/* getList 実行 */
		List<OrderNamesList> list = dao.getList(nameDivision);

		/* 検証用データ読み込み */
		DataSet expected = readXls("OrderNamesListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("NAMES");

		list = dao.getList(nameDivision);
		assertEquals(0, list.size());
	}
}
