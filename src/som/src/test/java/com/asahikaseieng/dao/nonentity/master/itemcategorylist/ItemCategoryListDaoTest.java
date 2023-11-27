/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemcategorylist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemCategoryListDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemCategoryListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ItemCategoryListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemCategoryListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ItemCategoryListDao_init.xls");

		ItemCategoryListPagerCondition condition = new ItemCategoryListPagerCondition();
		condition.setSrhItemCategory("ITEM_CATEGORY001");

		/* getList 実行 */
		List<ItemCategoryList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ItemCategoryListDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM_CATEGORY");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
