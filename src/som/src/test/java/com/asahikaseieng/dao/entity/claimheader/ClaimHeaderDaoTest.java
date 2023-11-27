/*
 * Created on Mon Jan 19 16:56:18 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.claimheader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ClaimHeaderDaoクラスのテストケース
 * @author t0011036
 */
public final class ClaimHeaderDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ClaimHeaderDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ClaimHeaderDaoTest(final String testname) {
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

		ClaimHeader lhs = new ClaimHeader();
		ClaimHeader rhs = new ClaimHeader();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
