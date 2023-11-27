/*
 * Created on 2009/08/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companylistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CompanyListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class CompanyListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private CompanyListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CompanyListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("CompanyListForReportDao_init.xls");

		CompanyListConditionForReport condition = new CompanyListConditionForReport();
		condition.setSrhHomeName1("HOME_NAME1");

		/* getList 実行 */
		List<CompanyListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("CompanyListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("COMPANY");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
