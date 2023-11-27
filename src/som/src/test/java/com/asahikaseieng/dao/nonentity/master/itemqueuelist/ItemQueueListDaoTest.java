/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelist;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemQueueListDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemQueueListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ItemQueueListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemQueueListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListのテスト
	 */
	public void testGetListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("ItemQueueListDao_init.xls");

		ItemQueueListPagerCondition condition = new ItemQueueListPagerCondition();
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhParentItemCd("PARENT_ITEM_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");
		condition.setSrhStatus(new BigDecimal("1"));

		/* getList 実行 */
		List<ItemQueueList> list = dao.getActivateList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ItemQueueListDao.expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ITEM_QUEUE");

		list = dao.getActivateList(condition);
		assertEquals(0, list.size());
	}
}
