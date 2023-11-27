/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorymovedetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InventoryMoveDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class InventoryMoveDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InventoryMoveDetailDaoTest(final String testname) {
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
		InventoryMoveDetail lhs = new InventoryMoveDetail();
		InventoryMoveDetail rhs = new InventoryMoveDetail();

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
	private void setValue(final InventoryMoveDetail bean) {
		bean.setOutLocationCd("LOCATION_CD001");
		bean.setOutLocationName("LOCATION_NAME001");
		bean.setItemCd("ITEM_CD001");
		bean.setItemName("ITEM_NAME001");
		bean.setLotNo("LOT_NO001");
	}
}
