/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetaillist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OrderDetailListDaoクラスのテストケース
 * @author kanri-user
 */
public final class OrderDetailListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private OrderDetailListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OrderDetailListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("OrderDetailListDao_init.xls");

		String orderNo = "ORDER_NO";

		/* getList 実行 */
		List<OrderDetailList> list = dao.getDetailList(orderNo);

		/* 検証用データ読み込み */
		DataSet expected = readXls("OrderDetailListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("VENDER");

		list = dao.getDetailList(orderNo);
		assertEquals(0, list.size());
	}
}
