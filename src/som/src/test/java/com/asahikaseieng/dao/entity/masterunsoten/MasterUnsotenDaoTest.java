/*
 * Created on Wed Feb 04 16:12:47 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterunsoten;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * MasterUnsotenDaoクラスのテストケース
 * @author kanri-user
 */
public final class MasterUnsotenDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(MasterUnsotenDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public MasterUnsotenDaoTest(final String testname) {
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

		MasterUnsoten lhs = new MasterUnsoten();
		MasterUnsoten rhs = new MasterUnsoten();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
