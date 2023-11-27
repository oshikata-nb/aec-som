/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuedetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemQueueDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemQueueDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemQueueDetailDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getEntityのテスト
	 */
	public void testGetEntityTx() {
		ItemQueueDetail lhs = new ItemQueueDetail();
		ItemQueueDetail rhs = new ItemQueueDetail();

		/* 検索条件設定 */
		setValue(lhs);
		setValue(rhs);

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
	}

	/**
	 * 検索条件設定
	 * @param bean
	 */
	private void setValue(final ItemQueueDetail bean) {
		bean.setItemCd("ITEM001");
		bean.setItemName("NAME001");
	}
}
