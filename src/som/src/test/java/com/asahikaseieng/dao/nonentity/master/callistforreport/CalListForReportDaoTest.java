/*
 * Created on 2009/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.callistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CalListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class CalListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private CalListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CalListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("CalListForReportDao_init.xls");

		CalListConditionForReport condition = new CalListConditionForReport();
		condition.setSrhCalCd("CAL_CD001");
		condition.setSrhCalYear("2009");

		/* getList 実行 */
		List<CalListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("CalListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("CAL");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
