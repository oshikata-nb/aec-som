/*
 * Created on 2009/08/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arbalanceforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ArBalanceListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class ArBalanceListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ArBalanceListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ArBalanceListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ArBalanceListForReportDao_init.xls");

		ArBalanceListConditionForReport condition = new ArBalanceListConditionForReport();
		condition.setSrhSectionCd("ORGANIZATION_CD001");
		condition.setSrhTantoCd("TANTO_CD001");
		condition.setSrhVenderCd("VENDER_CD001");
		condition.setSrhTargetMonth("2009/08");
		condition.setSrhAccruedDebitDivi("0");
		condition.setSrhNormalFlg(true);

		/* getList 実行 */
		List<ArBalanceListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ArBalanceListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("DEPOSIT_HEADER");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
