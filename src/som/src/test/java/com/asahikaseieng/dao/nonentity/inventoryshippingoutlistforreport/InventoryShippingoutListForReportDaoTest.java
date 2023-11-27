/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventoryshippingoutlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InventoryShippingoutListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class InventoryShippingoutListForReportDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private InventoryShippingoutListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InventoryShippingoutListForReportDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListForReportのテスト
	 */
	public void testGetListForReportTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("InventoryShippingoutListForReportDao_init.xls");

		InventoryShippingoutListConditionForReport condition = new InventoryShippingoutListConditionForReport();

		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");
		condition.setSrhLocationCd("LOCATION_CD001");

		/* getList 実行 */
		List<InventoryShippingoutListForReport> list = dao
				.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"InventoryShippingoutListForReportDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LOT_INVENTORY");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
