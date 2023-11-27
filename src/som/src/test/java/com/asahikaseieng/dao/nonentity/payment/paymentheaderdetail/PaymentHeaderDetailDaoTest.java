/*
 * Created on 2009/08/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentheaderdetail;

import java.sql.Timestamp;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * PaymentHeaderDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class PaymentHeaderDetailDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private PaymentHeaderDetailDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public PaymentHeaderDetailDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getEntityのテスト
	 */
	public void testGetEntityTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("PaymentHeaderListDao_init.xls");

		String organizationCd = "ORGANIZATION_CD01";
		String supplierCd = "SUPPLIER_CD01";
		Timestamp paymentDate = AecDateUtils.getCurrentTimestamp();

		/* getList 実行 */
		List<PaymentHeaderDetail> list = dao.getEntity(organizationCd,
			supplierCd, paymentDate);

		/* 検証用データ読み込み */
		DataSet expected = readXls("PaymentHeaderDetailDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("PAYMENT_HEADER");

		list = dao.getEntity(organizationCd, supplierCd, paymentDate);
		assertEquals(0, list.size());
	}
}
