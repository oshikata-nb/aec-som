/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.financialclasslistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * FinancialClassListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class FinancialClassListForReportDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private FinancialClassListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public FinancialClassListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("FinancialClassListDao_init.xls");

		FinancialClassListConditionForReport condition = new FinancialClassListConditionForReport();
		condition.setSrhFinancialClassCd("FINANCIAL001");

		/* getList 実行 */
		List<FinancialClassListForReport> list = dao
				.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("FinancialClassListDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("FINANCIAL_CLASS");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
