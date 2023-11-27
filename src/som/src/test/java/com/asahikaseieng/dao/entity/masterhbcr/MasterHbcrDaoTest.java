/*
 * Created on Wed Feb 04 16:11:59 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterhbcr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * MasterHbcrDaoクラスのテストケース
 * @author kanri-user
 */
public final class MasterHbcrDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(MasterHbcrDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public MasterHbcrDaoTest(final String testname) {
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

		MasterHbcr lhs = new MasterHbcr();
		MasterHbcr rhs = new MasterHbcr();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
