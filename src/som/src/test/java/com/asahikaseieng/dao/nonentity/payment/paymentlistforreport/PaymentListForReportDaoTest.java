/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * PaymentListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class PaymentListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private PaymentListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public PaymentListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("PaymentListForReportDao_init.xls");

		PaymentListConditionForReport condition = new PaymentListConditionForReport();
		condition.setOrganizationCd("ORGANIZATION_CD001");
		condition.setSupplierCd("VENDER_CD001");
		condition.setTantoCd("TANTO_CD001");

		/* getList 実行 */
		List<PaymentListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("PaymentListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("PAYMENT");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
