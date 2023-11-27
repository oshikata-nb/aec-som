/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemlist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemListDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ItemListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ItemListListDao_init.xls");

		ItemListPagerCondition condition = new ItemListPagerCondition();
		condition.setItemCd("ITEM001");

		/* getList 実行 */
		List<ItemList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ItemListListDao.expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
