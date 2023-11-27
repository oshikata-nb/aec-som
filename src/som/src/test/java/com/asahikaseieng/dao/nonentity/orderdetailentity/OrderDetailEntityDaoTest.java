/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailentity;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OrderDetailEntityDaoクラスのテストケース
 * @author kanri-user
 */
public final class OrderDetailEntityDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OrderDetailEntityDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * getEntityのテスト
	 */
	public void testGetEntityTx() {
		OrderDetailEntity lhs = new OrderDetailEntity();
		OrderDetailEntity rhs = new OrderDetailEntity();

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
	private void setValue(final OrderDetailEntity bean) {
		bean.setOrderNo("ORDER_NO");
	}
}
