/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemQueueListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemQueueListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ItemQueueListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemQueueListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ItemQueueListForReportDao_init.xls");

		ItemQueueListConditionForReport condition = new ItemQueueListConditionForReport();
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhParentItemCd("PARENT_ITEM_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");
		condition.setSrhStatus(new BigDecimal("1"));
		condition.setStrSrhActiveDateFrom(null);
		condition.setStrSrhActiveDateTo(null);

		/* getList 実行 */
		List<ItemQueueListForReport> list = dao
				.getActivateListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ItemQueueListForReportDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM_QUEUE");

		list = dao.getActivateListForReport(condition);
		assertEquals(0, list.size());
	}
}
