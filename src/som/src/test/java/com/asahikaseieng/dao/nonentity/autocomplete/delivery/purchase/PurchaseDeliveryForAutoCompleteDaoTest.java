/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchase;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * PurchaseDeliveryForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class PurchaseDeliveryForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private PurchaseDeliveryForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public PurchaseDeliveryForAutoCompleteDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getSearchListDetailのテスト
	 */
	public void testGetSearchListDetailTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("PurchaseDeliveryForAutoCompleteDao_init.xls");

		/* getSearchList 実行 */
		List<PurchaseDeliveryForAutoComplete> list = dao.getSearchList(
			"DELIVERY001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"PurchaseDeliveryForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("DELIVERY");

		list = dao.getSearchList("DELIVERY001", "50");
		assertEquals(0, list.size());
	}
}
