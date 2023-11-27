/*
 * Created on 2009/05/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.financialclass;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * FinancialClassForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class FinancialClassForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private FinancialClassForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public FinancialClassForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("FinancialClassListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<FinancialClassForAutoComplete> list = dao.getSearchList("CD001",
			"50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"FinancialClassListForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("FINANCIAL_CLASS");

		list = dao.getSearchList("CD001", "50");
		assertEquals(0, list.size());
	}
}
