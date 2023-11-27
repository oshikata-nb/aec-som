/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.itemcategory;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemCategoryListForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemCategoryForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ItemCategoryForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemCategoryForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ItemCategoryListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<ItemCategoryForAutoComplete> list = dao.getSearchList(
			"ITEM_CATEGORY001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"ItemCategoryListForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM_CATEGORY");

		list = dao.getSearchList("ITEM_CATEGORY001", "50");
		assertEquals(0, list.size());
	}
}
