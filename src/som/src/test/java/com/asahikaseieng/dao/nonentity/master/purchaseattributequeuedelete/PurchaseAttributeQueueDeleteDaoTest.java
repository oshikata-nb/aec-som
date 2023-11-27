/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedelete;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * PurchaseAttributeQueueDeleteDaoクラスのテストケース
 * @author t0011036
 */
public final class PurchaseAttributeQueueDeleteDaoTest extends
		AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public PurchaseAttributeQueueDeleteDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * deleteのテスト
	 */
	public void testDeleteTx() {
		PurchaseAttributeQueueDelete lhs = new PurchaseAttributeQueueDelete();
		PurchaseAttributeQueueDelete rhs = new PurchaseAttributeQueueDelete();

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
	private void setValue(final PurchaseAttributeQueueDelete bean) {
		bean.setItemCd("CD001");
		bean.setVersion(new BigDecimal("1"));
	}
}
