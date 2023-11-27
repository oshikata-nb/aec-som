/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.delivery.buyingapproval;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BuyingApprovalDeliveryForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class BuyingApprovalDeliveryForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BuyingApprovalDeliveryForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BuyingApprovalDeliveryForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("BuyingApprovalDeliveryForAutoCompleteDao_init.xls");

		/* getSearchList 実行 */
		List<BuyingApprovalDeliveryForAutoComplete> list = dao.getSearchList(
			"DELIVERY001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"BuyingApprovalDeliveryForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("DELIVERY");

		list = dao.getSearchList("DELIVERY001", "50");
		assertEquals(0, list.size());
	}
}
