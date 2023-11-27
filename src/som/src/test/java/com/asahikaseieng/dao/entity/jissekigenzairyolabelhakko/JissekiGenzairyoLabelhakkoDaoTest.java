/*
 * Created on Wed Feb 04 16:08:52 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekigenzairyolabelhakko;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * JissekiGenzairyoLabelhakkoDaoクラスのテストケース
 * @author kanri-user
 */
public final class JissekiGenzairyoLabelhakkoDaoTest extends
		AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(JissekiGenzairyoLabelhakkoDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public JissekiGenzairyoLabelhakkoDaoTest(final String testname) {
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

		JissekiGenzairyoLabelhakko lhs = new JissekiGenzairyoLabelhakko();
		JissekiGenzairyoLabelhakko rhs = new JissekiGenzairyoLabelhakko();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
