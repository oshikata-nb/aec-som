/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermslistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * SalesTermsListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class SalesTermsListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private SalesTermsListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public SalesTermsListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("SalesTermsListForReportDao_init.xls");

		SalesTermsListConditionForReport condition = new SalesTermsListConditionForReport();
		condition.setSrhVenderCd("VENDER_CD001");
		condition.setSrhDeliveryCd("DELIVERY_CD001");
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");

		/* getList 実行 */
		List<SalesTermsListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("SalesTermsListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("SALES_TERMS");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
