/*
 * Created on 2009/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorystocklistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InventoryStockListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class InventoryStockListForReportDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private InventoryStockListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InventoryStockListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("InventoryStockListForReportDao_init.xls");

		String itemCd = "ITEM_CD001";
		String otherCompanyCd1 = "OTHER_COMPANY_CD001";

		/* getList 実行 */
		List<InventoryStockListForReport> list = dao.getListForReport(itemCd,
			otherCompanyCd1);

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"InventoryStockListForReportDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM_INVENTORY");

		list = dao.getListForReport(itemCd, otherCompanyCd1);
		assertEquals(0, list.size());
	}
}
