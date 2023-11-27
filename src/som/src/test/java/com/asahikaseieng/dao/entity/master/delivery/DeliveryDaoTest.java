/*
 * Created on Tue Jan 20 11:19:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.delivery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DeliveryDaoクラスのテストケース
 * @author t0011036
 */
public final class DeliveryDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(DeliveryDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public DeliveryDaoTest(final String testname) {
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

		Delivery lhs = new Delivery();
		Delivery rhs = new Delivery();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
