/*
 * Created on 2009/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LocationListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class LocationListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private LocationListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LocationListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("LocationListForReportDao_init.xls");

		LocationListConditionForReport condition = new LocationListConditionForReport();

		condition.setSrhLocationCd("LOCATION_CD001");

		/* getListForReport 実行 */
		List<LocationListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("LocationListForReportDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LOCATION");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
