/*
 * Created on Tue Apr 28 09:03:33 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.fbheader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * FbHeaderDaoクラスのテストケース
 * @author t0011036
 */
public final class FbHeaderDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(FbHeaderDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public FbHeaderDaoTest(final String testname) {
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

		FbHeader lhs = new FbHeader();
		FbHeader rhs = new FbHeader();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
