/*
 * Created on 2009/08/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.nameslistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * NamesListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class NamesListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private NamesListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public NamesListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("NamesListForReportDao_init.xls");

		NamesListConditionForReport condition = new NamesListConditionForReport();
		condition.setSrhNameDivision("NAME_DIVISION001");
		condition.setSrhNameCd("NAME_CD001");

		/* getList 実行 */
		List<NamesListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("NamesListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("NAMES");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
