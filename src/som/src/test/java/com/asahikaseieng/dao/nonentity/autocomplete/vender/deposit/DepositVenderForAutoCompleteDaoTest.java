/*
 * Created on 2009/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.vender.deposit;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DepositVenderForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class DepositVenderForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private DepositVenderForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public DepositVenderForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("DepositVenderListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<DepositVenderForAutoComplete> list = dao.getSearchList("TS",
			"VENDER001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"DepositVenderListForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("VENDER");

		list = dao.getSearchList("TS", "VENDER001", "50");
		assertEquals(0, list.size());
	}
}
