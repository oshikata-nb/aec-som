/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.linelistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LineListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class LineListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private LineListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LineListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("LineListForReportDao_init.xls");

		LineListConditionForReport condition = new LineListConditionForReport();
		condition.setSrhProductionLine("PRODUCTION_LINE001");

		/* getList 実行 */
		List<LineListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("LineListForReportDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LINE");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
