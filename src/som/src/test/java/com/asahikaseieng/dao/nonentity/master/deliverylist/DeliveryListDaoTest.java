/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.deliverylist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DeliveryListDaoクラスのテストケース
 * @author t0011036
 */
public final class DeliveryListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private DeliveryListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public DeliveryListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("DeliveryListDao_init.xls");

		DeliveryListPagerCondition condition = new DeliveryListPagerCondition();
		condition.setSrhDeliveryCd("DELIVERY001");

		/* getList 実行 */
		List<DeliveryList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("DeliveryListDao.expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("DELIVERY");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
