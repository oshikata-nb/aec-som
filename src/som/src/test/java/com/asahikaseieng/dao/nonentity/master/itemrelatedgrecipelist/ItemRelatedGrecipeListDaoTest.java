/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemrelatedgrecipelist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemRelatedGrecipeListDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemRelatedGrecipeListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ItemRelatedGrecipeListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemRelatedGrecipeListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ItemRelatedGrecipeListDao_init.xls");

		String itemCd = "ITEM_CD001";

		/* getList 実行 */
		List<ItemRelatedGrecipeList> list = dao.getList(itemCd);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ItemRelatedGrecipeListDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM_QUEUE");

		list = dao.getList(itemCd);
		assertEquals(0, list.size());
	}
}
