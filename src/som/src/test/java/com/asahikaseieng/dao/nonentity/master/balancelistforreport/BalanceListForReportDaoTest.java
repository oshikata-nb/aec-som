/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelistforreport;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BalanceListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class BalanceListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BalanceListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BalanceListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("BalanceListForReportDao_init.xls");

		BalanceListConditionForReport condition = new BalanceListConditionForReport();
		condition.setSrhBalanceType(new BigDecimal("1"));
		condition.setSrhVenderCd("VENDER_CD001");

		/* getListForReport 実行 */
		List<BalanceListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("BalanceListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("BALANCE");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
