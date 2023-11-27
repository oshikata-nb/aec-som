/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipeResouceListForAutoCompleteDaoクラスのテストケース
 * @author kanri-user
 */
public final class RecipeResouceForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RecipeResouceForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RecipeResouceForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("RecipeResouceListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<RecipeResouceForAutoComplete> list = dao.getSearchList(
			"RESOURCE001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"RecipeResouceListForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("RESOURCE");

		list = dao.getSearchList("RESOURCE001", "50");
		assertEquals(0, list.size());
	}
}
