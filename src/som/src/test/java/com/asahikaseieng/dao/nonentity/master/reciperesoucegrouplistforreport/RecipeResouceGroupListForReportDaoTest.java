/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipeResouceGroupListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class RecipeResouceGroupListForReportDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RecipeResouceGroupListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RecipeResouceGroupListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("RecipeResouceGroupListForReportDao_init.xls");

		RecipeResouceGroupListConditionForReport condition = new RecipeResouceGroupListConditionForReport();
		condition.setSrhResouceGroupCd("RESOURCE001");

		/* getListForReport 実行 */
		List<RecipeResouceGroupListForReport> list = dao
				.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"RecipeResouceGroupListForReportDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("RESOURCE_RESOUCE_GROUP");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
