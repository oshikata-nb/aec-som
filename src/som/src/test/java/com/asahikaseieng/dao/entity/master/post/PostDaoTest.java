/*
 * Created on Fri Mar 20 14:58:22 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.post;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * PostDaoクラスのテストケース
 * @author t0011036
 */
public final class PostDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(PostDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public PostDaoTest(final String testname) {
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

		Post lhs = new Post();
		Post rhs = new Post();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
