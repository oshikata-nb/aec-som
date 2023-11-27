/*
 * Created on Fri Jan 16 15:04:21 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.bumon;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * BumonDaoクラスのテストケース
 * @author t0011036
 */
public final class BumonDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(BumonDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public BumonDaoTest(final String testname) {
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

		Bumon lhs = new Bumon();
		Bumon rhs = new Bumon();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
