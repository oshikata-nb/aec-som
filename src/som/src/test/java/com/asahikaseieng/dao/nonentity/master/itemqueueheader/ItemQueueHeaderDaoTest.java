/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueueheader;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemQueueHeaderDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemQueueHeaderDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemQueueHeaderDaoTest(final String testname) {
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
		ItemQueueHeader lhs = new ItemQueueHeader();
		ItemQueueHeader rhs = new ItemQueueHeader();

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
	private void setValue(final ItemQueueHeader bean) {
		bean.setHeadItemCd("ITEM001");
		bean.setHeadItemName("NAME001");
	}
}
