/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasonlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ReasonListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class ReasonListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ReasonListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ReasonListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ReasonListForReportDao_init.xls");

		ReasonListConditionForReport condition = new ReasonListConditionForReport();
		condition.setSrhRyCd("RY001");

		/* getList 実行 */
		List<ReasonListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ReasonListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("REASON");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
