/*
 * Created on Fri Jan 23 17:04:25 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.purchaseattributequeue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * PurchaseAttributeQueueDaoクラスのテストケース
 * @author kanri-user
 */
public final class PurchaseAttributeQueueDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(PurchaseAttributeQueueDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public PurchaseAttributeQueueDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * エンティティのテスト.
	 */
	public void testEntityTx() {

		PurchaseAttributeQueue lhs = new PurchaseAttributeQueue();
		PurchaseAttributeQueue rhs = new PurchaseAttributeQueue();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
