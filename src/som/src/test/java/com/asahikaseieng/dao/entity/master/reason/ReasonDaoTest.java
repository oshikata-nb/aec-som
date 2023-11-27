/*
 * Created on Fri Jan 23 16:47:12 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.reason;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ReasonDaoクラスのテストケース
 * @author kanri-user
 */
public final class ReasonDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ReasonDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ReasonDaoTest(final String testname) {
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

		Reason lhs = new Reason();
		Reason rhs = new Reason();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
