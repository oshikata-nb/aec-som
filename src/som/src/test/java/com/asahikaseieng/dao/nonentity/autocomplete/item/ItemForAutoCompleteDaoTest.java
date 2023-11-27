/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.item;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.item.pkgdirection.PkgDirectionItemForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.item.pkgdirection.PkgDirectionItemForAutoCompleteDao;
import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemListForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemForAutoCompleteDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ItemForAutoCompleteDao dao;

	/** Daoオブジェクト */
	private PkgDirectionItemForAutoCompleteDao pkgDirectionItemForAutoCompleteDao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ItemListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<ItemForAutoComplete> list = dao.getSearchList("ITEM001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls("ItemListForAutoCompleteDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM");

		list = dao.getSearchList("ITEM001", "50");
		assertEquals(0, list.size());
	}

	/**
	 * getListForAutoCompleteのテスト
	 */
	public void testPkgForAutoCompleteTx() {
		List<PkgDirectionItemForAutoComplete> list = pkgDirectionItemForAutoCompleteDao
				.getSearchList("%", Constants.AUTOCOMPLETTE_ROW_LIMIT, "YES");
		if (list.isEmpty()) {
			assertFalse(true);
		} else {
			assertTrue(true);
		}
	}
}
