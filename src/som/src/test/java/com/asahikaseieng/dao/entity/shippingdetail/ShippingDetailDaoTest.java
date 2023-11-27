/*
 * Created on Thu Jan 22 20:09:34 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.shippingdetail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ShippingDetailDaoクラスのテストケース
 * @author kanri-user
 */
public final class ShippingDetailDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ShippingDetailDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ShippingDetailDaoTest(final String testname) {
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

		ShippingDetail lhs = new ShippingDetail();
		ShippingDetail rhs = new ShippingDetail();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
