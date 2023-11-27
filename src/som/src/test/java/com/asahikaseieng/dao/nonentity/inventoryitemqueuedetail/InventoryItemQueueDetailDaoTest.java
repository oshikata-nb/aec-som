/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventoryitemqueuedetail;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InventoryItemQueueDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class InventoryItemQueueDetailDaoTest extends
		AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InventoryItemQueueDetailDaoTest(final String testname) {
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
		InventoryItemQueueDetail lhs = new InventoryItemQueueDetail();
		InventoryItemQueueDetail rhs = new InventoryItemQueueDetail();

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
	private void setValue(final InventoryItemQueueDetail bean) {
		bean.setItemCd("ITEM_CD001");
		bean.setItemName("ITEM_NAME001");
		bean.setVersion(new BigDecimal("1"));
	}
}
