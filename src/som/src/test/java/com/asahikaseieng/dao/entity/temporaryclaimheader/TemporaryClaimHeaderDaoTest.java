/*
 * Created on Thu Jan 22 17:57:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporaryclaimheader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * TemporaryClaimHeaderDaoクラスのテストケース
 * @author kanri-user
 */
public final class TemporaryClaimHeaderDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(TemporaryClaimHeaderDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TemporaryClaimHeaderDaoTest(final String testname) {
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

		TemporaryClaimHeader lhs = new TemporaryClaimHeader();
		TemporaryClaimHeader rhs = new TemporaryClaimHeader();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
