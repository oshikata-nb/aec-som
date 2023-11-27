/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OperationListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class OperationListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private OperationListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OperationListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("OperationListForReportDao_init.xls");

		OperationListConditionForReport condition = new OperationListConditionForReport();
		condition.setSrhOperationCd("OPERATION_CD001");

		/* getList 実行 */
		List<OperationListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("OperationListForReportDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("OPERATION");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
