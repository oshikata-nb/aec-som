/*
 * Created on Thu Jan 22 15:02:33 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inoutsource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InoutSourceDaoクラスのテストケース
 * @author t0011036
 */
public final class InoutSourceDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(InoutSourceDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public InoutSourceDaoTest(final String testname) {
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

		InoutSource lhs = new InoutSource();
		InoutSource rhs = new InoutSource();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
