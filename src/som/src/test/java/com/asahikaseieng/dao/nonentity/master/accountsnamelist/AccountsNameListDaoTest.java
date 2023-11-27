/*
 * Created on 2009/05/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.accountsnamelist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * AccountsNameListDaoクラスのテストケース
 * @author t0011036
 */
public final class AccountsNameListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private AccountsNameListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public AccountsNameListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getNameListのテスト
	 */
	public void testGetNameListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("AccountsNameListDao_init.xls");

		String accountsCd = "ACCOUNTS01";

		/* getList 実行 */
		List<AccountsNameList> list = dao.getNameList(accountsCd);

		/* 検証用データ読み込み */
		DataSet expected = readXls("AccountsNameListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ACCOUNTS");

		list = dao.getNameList(accountsCd);
		assertEquals(0, list.size());
	}
}
