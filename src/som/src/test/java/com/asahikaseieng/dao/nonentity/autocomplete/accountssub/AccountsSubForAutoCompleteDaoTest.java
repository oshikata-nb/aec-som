/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.accountssub;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * AccountsSubForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class AccountsSubForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private AccountsSubForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public AccountsSubForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("AccountsSubListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<AccountsSubForAutoComplete> list = dao.getSearchList("S100", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"AccountsSubListForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ACCOUNTS");

		list = dao.getSearchList("S100", "50");
		assertEquals(0, list.size());
	}
}
