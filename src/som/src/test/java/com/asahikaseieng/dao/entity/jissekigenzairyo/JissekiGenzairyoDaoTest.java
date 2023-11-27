/*
 * Created on Wed Feb 04 16:08:38 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekigenzairyo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * JissekiGenzairyoDaoクラスのテストケース
 * @author kanri-user
 */
public final class JissekiGenzairyoDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(JissekiGenzairyoDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public JissekiGenzairyoDaoTest(final String testname) {
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

		JissekiGenzairyo lhs = new JissekiGenzairyo();
		JissekiGenzairyo rhs = new JissekiGenzairyo();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
