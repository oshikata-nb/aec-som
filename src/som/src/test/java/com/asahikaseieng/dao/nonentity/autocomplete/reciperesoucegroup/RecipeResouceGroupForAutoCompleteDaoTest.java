/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.reciperesoucegroup;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipeResouceGroupListForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class RecipeResouceGroupForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RecipeResouceGroupForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RecipeResouceGroupForAutoCompleteDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListForAutoCompleteのテスト
	 */
	public void testGetListForAutoCompleteTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("RecipeResouceGroupListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<RecipeResouceGroupForAutoComplete> list = dao.getSearchList(
			"RESOURCE_GROUP001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"RecipeResouceGroupListForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("RESOURCE_GROUP");

		list = dao.getSearchList("RESOURCE_GROUP001", "50");
		assertEquals(0, list.size());
	}
}
