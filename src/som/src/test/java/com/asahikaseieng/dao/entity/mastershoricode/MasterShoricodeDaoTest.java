/*
 * Created on Wed Feb 04 16:12:28 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.mastershoricode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * MasterShoricodeDaoクラスのテストケース
 * @author kanri-user
 */
public final class MasterShoricodeDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(MasterShoricodeDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public MasterShoricodeDaoTest(final String testname) {
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

		MasterShoricode lhs = new MasterShoricode();
		MasterShoricode rhs = new MasterShoricode();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
