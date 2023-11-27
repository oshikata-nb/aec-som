/*
 * Created on 2009/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorystocklist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InventoryStockListDaoクラスのテストケース
 * @author t0011036
 */
public final class InventoryStockListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private InventoryStockListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InventoryStockListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("InventoryStockListDao_init.xls");

		InventoryStockListPagerCondition condition = new InventoryStockListPagerCondition();
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");

		/* getList 実行 */
		List<InventoryStockList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("InventoryStockListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM_INVENTORY");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
