/*
 * Created on Thu Jan 22 13:14:41 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directioninspection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DirectionInspectionDaoクラスのテストケース
 * @author t0011036
 */
public final class DirectionInspectionDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(DirectionInspectionDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public DirectionInspectionDaoTest(final String testname) {
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

		DirectionInspection lhs = new DirectionInspection();
		DirectionInspection rhs = new DirectionInspection();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
