/*
 * Created on Fri Jan 23 14:34:54 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.operation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OperationDaoクラスのテストケース
 * @author t0011036
 */
public final class OperationDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(OperationDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public OperationDaoTest(final String testname) {
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

		Operation lhs = new Operation();
		Operation rhs = new Operation();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
