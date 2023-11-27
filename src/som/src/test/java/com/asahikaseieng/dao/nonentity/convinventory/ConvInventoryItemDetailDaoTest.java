/*
 * Created on 2009/04/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.convinventory;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ConvInventoryItemDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class ConvInventoryItemDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ConvInventoryItemDetailDaoTest(final String testname) {
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
		ConvInventoryItemDetail lhs = new ConvInventoryItemDetail();
		ConvInventoryItemDetail rhs = new ConvInventoryItemDetail();

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
	private void setValue(final ConvInventoryItemDetail bean) {
		bean.setItemCd("ITEM_CD001");
		bean.setTypeDivision(new BigDecimal("1"));
	}
}
