/*
 * Created on Fri Jan 23 15:07:13 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.operationgroup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OperationGroupDaoクラスのテストケース
 * @author t0011036
 */
public final class OperationGroupDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(OperationGroupDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public OperationGroupDaoTest(final String testname) {
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

		OperationGroup lhs = new OperationGroup();
		OperationGroup rhs = new OperationGroup();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
