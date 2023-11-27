/*
 * Created on Thu Jan 22 19:58:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.salesterms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * SalesTermsDaoクラスのテストケース
 * @author kanri-user
 */
public final class SalesTermsDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(SalesTermsDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public SalesTermsDaoTest(final String testname) {
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

		SalesTerms lhs = new SalesTerms();
		SalesTerms rhs = new SalesTerms();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
