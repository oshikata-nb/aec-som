/*
 * Created on Tue Apr 28 09:03:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.fbdata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * FbDataDaoクラスのテストケース
 * @author t0011036
 */
public final class FbDataDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(FbDataDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public FbDataDaoTest(final String testname) {
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

		FbData lhs = new FbData();
		FbData rhs = new FbData();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
