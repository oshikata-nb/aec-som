/*
 * Created on Mon Jan 19 09:03:30 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.aspoperation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * AspOperationDaoクラスのテストケース
 * @author t0011036
 */
public final class AspOperationDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(AspOperationDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public AspOperationDaoTest(final String testname) {
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

		AspOperation lhs = new AspOperation();
		AspOperation rhs = new AspOperation();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
