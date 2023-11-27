/*
 * Created on 2009/05/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.bank;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BankForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class BankForAutoCompleteDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BankForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BankForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("BankListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<BankForAutoComplete> list = dao.getSearchList("BANK_CD001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls("BankListForAutoCompleteDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("BANK");

		list = dao.getSearchList("BANK_CD001", "50");
		assertEquals(0, list.size());
	}
}
