/*
 * Created on 2009/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.vender.payment;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * PaymentVenderForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class PaymentVenderForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private PaymentVenderForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public PaymentVenderForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("PaymentVenderListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<PaymentVenderForAutoComplete> list = dao.getSearchList("SI",
			"VENDER001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"PaymentVenderListForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("VENDER");

		list = dao.getSearchList("SI", "VENDER001", "50");
		assertEquals(0, list.size());
	}
}
