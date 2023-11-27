/*
 * Created on 2009/04/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinputlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * InquiryInputListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class InquiryInputListForReportDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private InquiryInputListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InquiryInputListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("InquiryInputListForReportDao_init.xls");

		InquiryInputListConditionForReport condition = new InquiryInputListConditionForReport();

		condition.setSrhCountDate(AecDateUtils.getCurrentTimestamp());
		condition.setSrhLocationCd("LOCATION_CD001");
		condition.setSrhCountDivision("COUNT_DIVISION001");
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");
		condition.setSrhAliasLotNo("ALIAS_LOT_NO001");

		/* getList 実行 */
		List<InquiryInputListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("InquiryInputListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("INVENTORY_COUNT");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
