/*
 * Created on 2009/05/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumonlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BumonListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class BumonListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BumonListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BumonListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("BumonListForReportDao_init.xls");

		BumonListConditionForReport condition = new BumonListConditionForReport();
		condition.setSrhSectionCd("SECTION_CD001");

		/* getList 実行 */
		List<BumonListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("BumonListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("BUMON");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
