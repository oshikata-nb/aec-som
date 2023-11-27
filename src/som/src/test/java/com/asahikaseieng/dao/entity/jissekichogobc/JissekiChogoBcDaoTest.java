/*
 * Created on Wed Feb 04 16:08:26 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekichogobc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * JissekiChogoBcDaoクラスのテストケース
 * @author kanri-user
 */
public final class JissekiChogoBcDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(JissekiChogoBcDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public JissekiChogoBcDaoTest(final String testname) {
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

		JissekiChogoBc lhs = new JissekiChogoBc();
		JissekiChogoBc rhs = new JissekiChogoBc();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
