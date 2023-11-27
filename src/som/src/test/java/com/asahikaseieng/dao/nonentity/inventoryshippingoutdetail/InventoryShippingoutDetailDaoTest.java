/*
 * Created on 2009/04/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventoryshippingoutdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InventoryShippingoutDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class InventoryShippingoutDetailDaoTest extends
		AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InventoryShippingoutDetailDaoTest(final String testname) {
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
		InventoryShippingoutDetail lhs = new InventoryShippingoutDetail();
		InventoryShippingoutDetail rhs = new InventoryShippingoutDetail();

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
	private void setValue(final InventoryShippingoutDetail bean) {
		bean.setLocationCd("LOCATION_CD001");
		bean.setLocationName("LOCATION_NAME001");
		bean.setItemCd("ITEM_CD001");
		bean.setItemName("ITEM_NAME001");
		bean.setLotNo("LOT_NO001");
	}
}
