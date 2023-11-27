/*
 * Created on Fri Jan 23 16:47:30 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.payment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * PaymentDaoクラスのテストケース
 * @author t0011036
 */
public final class PaymentDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(PaymentDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public PaymentDaoTest(final String testname) {
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

		Payment lhs = new Payment();
		Payment rhs = new Payment();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
