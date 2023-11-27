/*
 * Created on 2009/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorystocktotalqty;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InventoryStockTotalQtyDaoクラスのテストケース
 * @author t0011036
 */
public final class InventoryStockTotalQtyDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InventoryStockTotalQtyDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getTotalQtyのテスト
	 */
	public void testGetTotalQtyTx() {
		InventoryStockTotalQty lhs = new InventoryStockTotalQty();
		InventoryStockTotalQty rhs = new InventoryStockTotalQty();

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
	private void setValue(final InventoryStockTotalQty bean) {
		bean.setInventoryQty(new BigDecimal("1"));
	}
}
