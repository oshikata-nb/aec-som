/*
 * Created on Mon Jan 19 09:06:48 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.aspproduction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * AspProductionDaoクラスのテストケース
 * @author t0011036
 */
public final class AspProductionDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(AspProductionDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public AspProductionDaoTest(final String testname) {
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

		AspProduction lhs = new AspProduction();
		AspProduction rhs = new AspProduction();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
