/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydetaillotduplicate;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InventoryDetailLotDuplicateDaoクラスのテストケース
 * @author t0011036
 */
public final class InventoryDetailLotDuplicateDaoTest extends
		AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InventoryDetailLotDuplicateDaoTest(final String testname) {
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
		InventoryDetailLotDuplicate lhs = new InventoryDetailLotDuplicate();
		InventoryDetailLotDuplicate rhs = new InventoryDetailLotDuplicate();

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
	private void setValue(final InventoryDetailLotDuplicate bean) {
		bean.setItemCd("ITEM001");
		bean.setLocationCd("LOCATION001");
		bean.setLotNo("LOT001");
	}
}
