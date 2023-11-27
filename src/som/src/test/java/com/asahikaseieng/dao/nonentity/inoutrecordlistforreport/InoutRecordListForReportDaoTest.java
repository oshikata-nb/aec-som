/*
 * Created on 2009/04/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutrecordlistforreport;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InoutRecordListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class InoutRecordListForReportDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private InoutRecordListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InoutRecordListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("InoutRecordListForReportDao_init.xls");

		InoutRecordReportCondition condition = new InoutRecordReportCondition();

		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");
		condition.setSrhLocationCd("LOCATION_CD001");
		condition.setSrhInoutDivision(new BigDecimal("1"));

		/* getList 実行 */
		List<InoutRecordListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("InoutRecordListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("INOUT_RECORD");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
