/*
 * Created on Wed Feb 04 17:44:45 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.role;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RoleDaoクラスのテストケース
 * @author t0011036
 */
public final class RoleDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(RoleDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public RoleDaoTest(final String testname) {
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

		Role lhs = new Role();
		Role rhs = new Role();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
