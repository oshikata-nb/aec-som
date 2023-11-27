/*
 * Created on 2009/05/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarklistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RemarkListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class RemarkListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RemarkListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RemarkListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("RemarkListForReportDao_init.xls");

		RemarkListConditionForReport condition = new RemarkListConditionForReport();
		condition.setSrhVenderDivision("TS");
		condition.setSrhVenderCd("VENDER001");
		condition.setSrhDeliveryCd("DELIVERY001");
		condition.setSrhItemCd("ITEM001");

		/* getList 実行 */
		List<RemarkListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("RemarkListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("VENDER");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
