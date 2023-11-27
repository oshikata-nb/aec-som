/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemCategoryListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemCategoryListForReportDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ItemCategoryListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemCategoryListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ItemCategoryListForReportDao_init.xls");

		ItemCategoryListConditionForReport condition = new ItemCategoryListConditionForReport();
		condition.setSrhItemCategory("ITEM_CATEGORY001");

		/* getList 実行 */
		List<ItemCategoryListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ItemCategoryListForReportDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM_CATEGORY");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
