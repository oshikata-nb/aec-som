/*
 * Created on Fri Jan 16 11:06:50 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.belong;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * BelongDaoクラスのテストケース
 * @author t0011036
 */
public final class BelongDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(BelongDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public BelongDaoTest(final String testname) {
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

		Belong lhs = new Belong();
		Belong rhs = new Belong();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
