/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.itemqueue;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemQueueListForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemQueueListForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ItemQueueForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemQueueListForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ItemQueueListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<ItemQueueForAutoComplete> list = dao
				.getSearchList("ITEM001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"ItemQueueListForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM_QUEUE");

		list = dao.getSearchList("ITEM001", "50");
		assertEquals(0, list.size());
	}
}
