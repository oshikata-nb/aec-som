/*
 * Created on 2009/05/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.deliverylistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DeliveryListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class DeliveryListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private DeliveryListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public DeliveryListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("DeliveryListForReportDao_init.xls");

		DeliveryListConditionForReport condition = new DeliveryListConditionForReport();
		condition.setSrhDeliveryCd("DELIVERY001");

		/* getList 実行 */
		List<DeliveryListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("DeliveryListForReportDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("DELIVERY");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
