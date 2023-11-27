/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.bumon;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BumonListForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class BumonForAutoCompleteDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BumonForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BumonForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("BumonListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<BumonForAutoComplete> list = dao.getSearchList("S100", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls("BumonListForAutoCompleteDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("BUMON");

		list = dao.getSearchList("S100", "50");
		assertEquals(0, list.size());
	}
}
