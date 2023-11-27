/*
 * Created on 2009/05/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelastactive;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemQueueLastActiveDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemQueueLastActiveDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemQueueLastActiveDaoTest(final String testname) {
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
		ItemQueueLastActive lhs = new ItemQueueLastActive();
		ItemQueueLastActive rhs = new ItemQueueLastActive();

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
	private void setValue(final ItemQueueLastActive bean) {
		bean.setItemCd("ITEM001");
		bean.setItemName("NAME001");
	}
}
