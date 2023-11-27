/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.itemcategory;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemCategoryListForComboboxesDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemCategoryListForComboboxesDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ItemCategoryListForComboboxesDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemCategoryListForComboboxesDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListForComboboxesのテスト
	 */
	public void testGetListForComboboxesTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("ItemCategoryListForComboboxesDao_init.xls");

		/* getListForComboboxes 実行 */
		List<ItemCategoryListForComboboxes> list = dao.getListForComboboxes();

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"ItemCategoryListForComboboxesDao_expected.xls",
			"getListForComboboxes");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM_CATEGORY");

		list = dao.getListForComboboxes();
		assertEquals(0, list.size());
	}
}
