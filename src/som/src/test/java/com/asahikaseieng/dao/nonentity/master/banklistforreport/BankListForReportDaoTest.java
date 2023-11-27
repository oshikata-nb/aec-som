/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.banklistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BankListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class BankListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BankListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BankListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("BankListForReportDao_init.xls");

		BankListConditionForReport condition = new BankListConditionForReport();
		condition.setSrhBankCd("BANK001");
		condition.setSrhBranchCd("BRANCH001");
		condition.setSrhBankMasterCd("BANK_MASTER001");

		/* getList 実行 */
		List<BankListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("BankListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("BANK");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
