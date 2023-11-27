/*
 * Created on 2009/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * PaymentPlanListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class PaymentPlanListForReportDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private PaymentPlanListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public PaymentPlanListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("PaymentPlanListForReportDao_init.xls");

		PaymentPlanListConditionForReport condition = new PaymentPlanListConditionForReport();
		condition.setSrhOrganizationCd("ORGANIZATION_CD001");
		condition.setSrhVenderCd("VENDER_CD001");
		condition.setSrhTantoCd("TANTO_CD001");

		/* getList 実行 */
		List<PaymentPlanListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("PaymentPlanListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("PAYMENT_HEADER");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
