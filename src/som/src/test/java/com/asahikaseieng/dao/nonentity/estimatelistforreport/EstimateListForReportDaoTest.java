/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatelistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * EstimateListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class EstimateListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private EstimateListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public EstimateListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("EstimateListForReportDao_init.xls");

		EstimateListConditionForReport condition = new EstimateListConditionForReport();
		condition.setSrhEstimateNo("ESTIMATE_NO001");
		condition.setStrSrhEstimateInputDateFrom("2009/01/01");
		condition.setStrSrhEstimateInputDateTo("2009/01/01");
		condition.setSrhVenderCd("VENDER_CD001");
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");
		condition.setStrSrhEstimateValidDateFrom("2009/01/01");
		condition.setStrSrhEstimateValidDateTo("2009/01/01");

		/* getList 実行 */
		List<EstimateListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("EstimateListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ESTIMATE");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
