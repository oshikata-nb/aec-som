/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorymovelist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InventoryMoveListDaoクラスのテストケース
 * @author t0011036
 */
public final class InventoryMoveListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private InventoryMoveListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InventoryMoveListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("InventoryMoveListDao_init.xls");

		InventoryMoveListPagerCondition condition = new InventoryMoveListPagerCondition();
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");
		condition.setSrhLocationCd("LOCATION_CD001");

		/* getList 実行 */
		List<InventoryMoveList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("InventoryMoveListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LOT_INVENTORY");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
