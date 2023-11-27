/*
 * Created on Mon Mar 16 17:00:51 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.estimate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * EstimateDaoクラスのテストケース
 * @author t0011036
 */
public final class EstimateDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(EstimateDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public EstimateDaoTest(final String testname) {
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

		Estimate lhs = new Estimate();
		Estimate rhs = new Estimate();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
