/*
 * Created on Fri Feb 06 14:36:19 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.tantorole;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * TantoRoleDaoクラスのテストケース
 * @author t0011036
 */
public final class TantoRoleDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(TantoRoleDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TantoRoleDaoTest(final String testname) {
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

		TantoRole lhs = new TantoRole();
		TantoRole rhs = new TantoRole();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
