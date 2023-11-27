/*
 * Created on Tue Feb 03 18:39:35 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.shipping;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ShippingDaoクラスのテストケース
 * @author kanri-user
 */
public final class ShippingDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ShippingDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ShippingDaoTest(final String testname) {
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

		Shipping lhs = new Shipping();
		Shipping rhs = new Shipping();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
