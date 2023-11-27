/*
 * Created on Wed Feb 25 08:59:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.dailynumber;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DailyNumberDaoクラスのテストケース
 * @author t0011036
 */
public final class DailyNumberDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(DailyNumberDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public DailyNumberDaoTest(final String testname) {
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

		DailyNumber lhs = new DailyNumber();
		DailyNumber rhs = new DailyNumber();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
