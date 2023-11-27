/*
 * Created on Thu Jan 15 17:09:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.articleattributequeue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ArticleAttributeQueueDaoクラスのテストケース
 * @author t0011036
 */
public final class ArticleAttributeQueueDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(ArticleAttributeQueueDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ArticleAttributeQueueDaoTest(final String testname) {
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

		ArticleAttributeQueue lhs = new ArticleAttributeQueue();
		ArticleAttributeQueue rhs = new ArticleAttributeQueue();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
