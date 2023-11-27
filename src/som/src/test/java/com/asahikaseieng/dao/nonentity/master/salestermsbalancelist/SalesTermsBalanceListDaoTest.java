/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsbalancelist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * SalesTermsBalanceListDaoクラスのテストケース
 * @author t0011036
 */
public final class SalesTermsBalanceListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private SalesTermsBalanceListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public SalesTermsBalanceListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListのテスト
	 */
	public void testGetListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("SalesTermsBalanceListDao_init.xls");

		/* getList 実行 */
		List<SalesTermsBalanceList> list = dao.getList("BALANCE_CD001");

		/* 検証用データ読み込み */
		DataSet expected = readXls("SalesTermsBalanceListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("BALANCE");

		list = dao.getList("BALANCE_CD001");
		assertEquals(0, list.size());
	}
}
