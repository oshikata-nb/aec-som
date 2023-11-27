/*
 * Created on Wed Nov 26 20:05:51 JST 2008
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.topic;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TopicDaoクラスのテストケース
 * @author a1020630
 */
public final class TopicDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(TopicDaoTest.class);

	/** Daoオブジェクト */
	private com.asahikaseieng.dao.entity.topic.TopicDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TopicDaoTest(final String testname) {
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

		Topic lhs = new Topic();
		Topic rhs = new Topic();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}

	// TODO テスト

	/**
	 * getListのテスト.
	 */
	public void testGetListTx() {
		assertNotNull(dao.getList());
	}
}
