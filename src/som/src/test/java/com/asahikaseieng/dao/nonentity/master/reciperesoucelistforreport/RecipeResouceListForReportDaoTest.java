/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipeResouceListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class RecipeResouceListForReportDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RecipeResouceListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RecipeResouceListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("RecipeResouceListForReportDao_init.xls");

		RecipeResouceListConditionForReport condition = new RecipeResouceListConditionForReport();
		condition.setSrhResouceCd("RESOUCE_CD001");

		/* getList 実行 */
		List<RecipeResouceListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"RecipeResouceListForReportDao.expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("RECIPE_RESOUCE");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
