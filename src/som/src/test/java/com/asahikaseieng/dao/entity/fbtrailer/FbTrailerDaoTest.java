/*
 * Created on Wed Apr 29 11:48:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.fbtrailer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * FbTrailerDaoクラスのテストケース
 * @author t0011036
 */
public final class FbTrailerDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(FbTrailerDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public FbTrailerDaoTest(final String testname) {
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

		FbTrailer lhs = new FbTrailer();
		FbTrailer rhs = new FbTrailer();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
