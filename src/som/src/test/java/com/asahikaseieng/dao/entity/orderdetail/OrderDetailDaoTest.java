/*
 * Created on Tue Feb 03 16:36:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderdetail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OrderDetailDaoクラスのテストケース
 * @author kanri-user
 */
public final class OrderDetailDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(OrderDetailDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public OrderDetailDaoTest(final String testname) {
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

		OrderDetail lhs = new OrderDetail();
		OrderDetail rhs = new OrderDetail();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
