/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.accountslistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * AccountsListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class AccountsListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private AccountsListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public AccountsListForReportDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListForReportのテスト
	 */
	public void testGetListForReportTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("AccountsListForReportDao_init.xls");

		AccountsListConditionForReport condition = new AccountsListConditionForReport();
		condition.setSrhAccountsCd("ACCOUNTS_CD001");

		/* getList 実行 */
		List<AccountsListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("AccountsListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ACCOUNTS");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
