/*
 * Created on Thu Jan 22 19:42:04 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.slipnumber;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * SlipNumberDaoクラスのテストケース
 * @author kanri-user
 */
public final class SlipNumberDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(SlipNumberDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public SlipNumberDaoTest(final String testname) {
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

		SlipNumber lhs = new SlipNumber();
		SlipNumber rhs = new SlipNumber();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
