/*
 * Created on 2009/05/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * VenderListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class VenderListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private VenderListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public VenderListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("VenderListForReportDao_init.xls");

		VenderListConditionForReport condition = new VenderListConditionForReport();
		condition.setSrhVenderDivision("TS");
		condition.setSrhVenderCd("VENDER001");
		condition.setSrhOrganizationCd("ORGANIZATION001");

		/* getList 実行 */
		List<VenderListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("VenderListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("VENDER");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
