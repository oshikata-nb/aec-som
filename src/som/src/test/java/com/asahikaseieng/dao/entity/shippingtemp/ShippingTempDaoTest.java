/*
 * Created on Tue Feb 17 16:40:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.shippingtemp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ShippingTempDaoクラスのテストケース
 * @author kanri-user
 */
public final class ShippingTempDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ShippingTempDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ShippingTempDaoTest(final String testname) {
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

		ShippingTemp lhs = new ShippingTemp();
		ShippingTemp rhs = new ShippingTemp();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
