/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.accounts;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * AccountsListForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class AccountsForAutoCompleteDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private AccountsForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public AccountsForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("AccountsListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<AccountsForAutoComplete> list = dao.getSearchList("S100", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"AccountsListForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ACCOUNTS");

		list = dao.getSearchList("S100", "50");
		assertEquals(0, list.size());
	}
}
