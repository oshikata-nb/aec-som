/*
 * Created on 2009/05/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * UnitpriceListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class UnitpriceListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private UnitpriceListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public UnitpriceListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("UnitpriceListForReportDao_init.xls");

		UnitpriceListConditionForReport condition = new UnitpriceListConditionForReport();
		condition.setSrhVenderDivision("SI");
		condition.setSrhVenderCd("VENDER001");
		condition.setSrhItemCd("ITEM001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY001");

		/* getList 実行 */
		List<UnitpriceListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("UnitpriceListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("UNITPRICE");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
