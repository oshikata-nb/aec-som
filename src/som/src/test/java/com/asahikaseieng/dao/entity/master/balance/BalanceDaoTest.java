/*
 * Created on Fri Jan 16 09:17:54 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.balance;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * BalanceDaoクラスのテストケース
 * @author t0011036
 */
public final class BalanceDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(BalanceDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public BalanceDaoTest(final String testname) {
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

		Balance lhs = new Balance();
		Balance rhs = new Balance();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
