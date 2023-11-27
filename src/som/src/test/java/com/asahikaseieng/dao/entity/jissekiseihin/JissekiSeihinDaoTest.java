/*
 * Created on Wed Feb 04 16:09:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekiseihin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * JissekiSeihinDaoクラスのテストケース
 * @author kanri-user
 */
public final class JissekiSeihinDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(JissekiSeihinDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public JissekiSeihinDaoTest(final String testname) {
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

		JissekiSeihin lhs = new JissekiSeihin();
		JissekiSeihin rhs = new JissekiSeihin();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
