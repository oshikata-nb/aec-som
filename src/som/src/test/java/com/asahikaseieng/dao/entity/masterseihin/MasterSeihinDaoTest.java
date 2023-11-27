/*
 * Created on Wed Feb 04 16:12:19 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterseihin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * MasterSeihinDaoクラスのテストケース
 * @author kanri-user
 */
public final class MasterSeihinDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(MasterSeihinDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public MasterSeihinDaoTest(final String testname) {
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

		MasterSeihin lhs = new MasterSeihin();
		MasterSeihin rhs = new MasterSeihin();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
