/*
 * Created on Mon Jan 19 09:01:53 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.aspinventoryprevday;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * AspInventoryPrevdayDaoクラスのテストケース
 * @author t0011036
 */
public final class AspInventoryPrevdayDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(AspInventoryPrevdayDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public AspInventoryPrevdayDaoTest(final String testname) {
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

		AspInventoryPrevday lhs = new AspInventoryPrevday();
		AspInventoryPrevday rhs = new AspInventoryPrevday();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
