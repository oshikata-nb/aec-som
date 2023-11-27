/*
 * Created on Thu Jan 22 13:17:18 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.erasercsm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * EraserCsmDaoクラスのテストケース
 * @author t0011036
 */
public final class EraserCsmDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(EraserCsmDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public EraserCsmDaoTest(final String testname) {
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

		EraserCsm lhs = new EraserCsm();
		EraserCsm rhs = new EraserCsm();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
