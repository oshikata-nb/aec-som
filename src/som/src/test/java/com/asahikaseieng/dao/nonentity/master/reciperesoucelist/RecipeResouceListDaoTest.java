/*
 * Created on 2007/11/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucelist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipeResouceListDaoクラスのテストケース
 * @author t0011036
 */
public final class RecipeResouceListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RecipeResouceListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RecipeResouceListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("RecipeResourceListDao_init.xls");

		RecipeResouceListPagerCondition condition = new RecipeResouceListPagerCondition();
		condition.setSrhResouceCd("RESOURCE001");

		/* getList 実行 */
		List<RecipeResouceList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("RecipeResourceListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("RESOURCE");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
