/*
 * Created on Wed Feb 04 16:09:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekiseizohead;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * JissekiSeizoHeadDaoクラスのテストケース
 * @author kanri-user
 */
public final class JissekiSeizoHeadDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(JissekiSeizoHeadDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public JissekiSeizoHeadDaoTest(final String testname) {
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

		JissekiSeizoHead lhs = new JissekiSeizoHead();
		JissekiSeizoHead rhs = new JissekiSeizoHead();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
