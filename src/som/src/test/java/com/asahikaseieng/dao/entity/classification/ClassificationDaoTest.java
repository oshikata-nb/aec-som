/*
 * Created on Mon Jan 19 16:58:24 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.classification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ClassificationDaoクラスのテストケース
 * @author t0011036
 */
public final class ClassificationDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ClassificationDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ClassificationDaoTest(final String testname) {
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

		Classification lhs = new Classification();
		Classification rhs = new Classification();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
