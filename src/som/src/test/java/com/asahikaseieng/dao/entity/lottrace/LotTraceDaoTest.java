/*
 * Created on Thu Jan 22 20:04:45 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.lottrace;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LotTraceDaoクラスのテストケース
 * @author t0011036
 */
public final class LotTraceDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(LotTraceDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public LotTraceDaoTest(final String testname) {
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

		LotTrace lhs = new LotTrace();
		LotTrace rhs = new LotTrace();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
