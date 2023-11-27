/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.deliverydetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DeliveryDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class DeliveryDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public DeliveryDetailDaoTest(final String testname) {
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
		DeliveryDetail lhs = new DeliveryDetail();
		DeliveryDetail rhs = new DeliveryDetail();

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
	private void setValue(final DeliveryDetail bean) {
		bean.setDeliveryCd("DELIVERY001");
		bean.setDeliveryName1("NAME001");
	}
}
