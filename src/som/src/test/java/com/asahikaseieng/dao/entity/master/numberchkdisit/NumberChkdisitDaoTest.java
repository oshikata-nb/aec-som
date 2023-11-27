/*
 * Created on Wed Feb 04 11:54:05 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.numberchkdisit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * NumberChkdisitDaoクラスのテストケース
 * @author t0011036
 */
public final class NumberChkdisitDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(NumberChkdisitDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public NumberChkdisitDaoTest(final String testname) {
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

		NumberChkdisit lhs = new NumberChkdisit();
		NumberChkdisit rhs = new NumberChkdisit();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
