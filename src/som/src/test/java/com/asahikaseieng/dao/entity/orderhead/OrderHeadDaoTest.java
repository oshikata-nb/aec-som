/*
 * Created on Tue Feb 03 16:36:35 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderhead;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OrderHeadDaoクラスのテストケース
 * @author kanri-user
 */
public final class OrderHeadDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(OrderHeadDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public OrderHeadDaoTest(final String testname) {
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

		OrderHead lhs = new OrderHead();
		OrderHead rhs = new OrderHead();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
