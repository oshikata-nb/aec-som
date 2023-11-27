/*
 * Created on Thu Jan 22 18:03:55 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporaryclaimcredit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * TemporaryClaimCreditDaoクラスのテストケース
 * @author kanri-user
 */
public final class TemporaryClaimCreditDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(TemporaryClaimCreditDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TemporaryClaimCreditDaoTest(final String testname) {
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

		TemporaryClaimCredit lhs = new TemporaryClaimCredit();
		TemporaryClaimCredit rhs = new TemporaryClaimCredit();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
