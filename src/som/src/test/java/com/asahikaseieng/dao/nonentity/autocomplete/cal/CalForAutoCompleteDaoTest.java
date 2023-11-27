/*
 * Created on 2009/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.cal;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CalForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class CalForAutoCompleteDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private CalForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CalForAutoCompleteDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getSearchListのテスト
	 */
	public void testGetSearchListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("CalListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<CalForAutoComplete> list = dao.getSearchList("CAL_CD001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls("CalListForAutoCompleteDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("CAL");

		list = dao.getSearchList("CAL_CD001", "50");
		assertEquals(0, list.size());
	}
}
