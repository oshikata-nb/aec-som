/*
 * Created on 2009/09/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuenewitemcd;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemQueueNewItemCdDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemQueueNewItemCdDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemQueueNewItemCdDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getNewItemCdのテスト
	 */
	public void testGetNewItemCdTx() {
		ItemQueueNewItemCd lhs = new ItemQueueNewItemCd();
		ItemQueueNewItemCd rhs = new ItemQueueNewItemCd();

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
	private void setValue(final ItemQueueNewItemCd bean) {
		bean.setNextval(BigDecimal.ONE);
	}
}
