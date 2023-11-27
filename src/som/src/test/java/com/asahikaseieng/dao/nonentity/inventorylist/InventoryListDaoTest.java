/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorylist;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InventoryListDaoクラスのテストケース
 * @author t0011036
 */
public final class InventoryListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private InventoryListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InventoryListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getLocItemLotListのテスト
	 */
	public void testGetLocItemLotListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("InventoryListDao_init.xls");

		InventoryListPagerCondition condition = new InventoryListPagerCondition();
		condition.setSrhLotNo("LOT_NO001");
		condition.setSrhAvailableFlg(new BigDecimal("0"));
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhLocationCd("LOCATION_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");

		/* getList 実行 */
		List<InventoryList> list = dao.getLocItemLotList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("InventoryListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LOT_INVENTORY");

		list = dao.getLocItemLotList(condition);
		assertEquals(0, list.size());
	}
}
