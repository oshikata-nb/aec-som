/*
 * Created on Fri Jan 16 16:23:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.carry;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CarryDaoクラスのテストケース
 * @author t0011036
 */
public final class CarryDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(CarryDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public CarryDaoTest(final String testname) {
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

		Carry lhs = new Carry();
		Carry rhs = new Carry();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
