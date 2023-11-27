/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apbalanceforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ApBalanceListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class ApBalanceListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ApBalanceListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ApBalanceListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ApBalanceListForReportDao_init.xls");

		ApBalanceListConditionForReport condition = new ApBalanceListConditionForReport();
		condition.setSrhOrganizationCd("ORGANIZATION_CD001");
		condition.setSrhTantoCd("TANTO_CD001");
		condition.setSrhVenderCd("VENDER_CD001");
		condition.setSrhTargetMonth("2009/08");

		/* getList 実行 */
		List<ApBalanceListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ApBalanceListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("PAYABLE_HEADER");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
