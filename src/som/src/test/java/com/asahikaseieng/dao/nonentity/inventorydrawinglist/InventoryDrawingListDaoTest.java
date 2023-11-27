/*
 * Created on 2009/04/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydrawinglist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InventoryDrawingListDaoクラスのテストケース
 * @author t0011036
 */
public final class InventoryDrawingListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private InventoryDrawingListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InventoryDrawingListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("InventoryDrawingListDao_init.xls");

		InventoryDrawingListPagerCondition condition = new InventoryDrawingListPagerCondition();
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");

		/* getList 実行 */
		List<InventoryDrawingList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("InventoryDrawingListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM_INVENTORY");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
