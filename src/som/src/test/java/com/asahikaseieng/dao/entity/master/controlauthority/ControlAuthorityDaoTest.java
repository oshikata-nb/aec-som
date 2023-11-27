/*
 * Created on Fri Mar 27 09:29:10 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.controlauthority;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ControlAuthorityDaoクラスのテストケース
 * @author t0011036
 */
public final class ControlAuthorityDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ControlAuthorityDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ControlAuthorityDaoTest(final String testname) {
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

		ControlAuthority lhs = new ControlAuthority();
		ControlAuthority rhs = new ControlAuthority();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
