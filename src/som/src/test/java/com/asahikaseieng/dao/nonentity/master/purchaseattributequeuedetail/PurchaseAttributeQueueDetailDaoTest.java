/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * PurchaseAttributeQueueDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class PurchaseAttributeQueueDetailDaoTest extends
		AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public PurchaseAttributeQueueDetailDaoTest(final String testname) {
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
		PurchaseAttributeQueueDetail lhs = new PurchaseAttributeQueueDetail();
		PurchaseAttributeQueueDetail rhs = new PurchaseAttributeQueueDetail();

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
	private void setValue(final PurchaseAttributeQueueDetail bean) {
		bean.setItemCd("CD001");
		bean.setVersion(new BigDecimal("1"));
	}
}
