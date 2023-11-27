/*
 * Created on Mon Jan 19 17:33:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.commonattributequeue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CommonAttributeQueueDaoクラスのテストケース
 * @author t0011036
 */
public final class CommonAttributeQueueDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(CommonAttributeQueueDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public CommonAttributeQueueDaoTest(final String testname) {
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

		CommonAttributeQueue lhs = new CommonAttributeQueue();
		CommonAttributeQueue rhs = new CommonAttributeQueue();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
