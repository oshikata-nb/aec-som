/*
 * Created on Wed Feb 04 16:10:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.labelyobiyokai;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LabelYobiyokaiDaoクラスのテストケース
 * @author kanri-user
 */
public final class LabelYobiyokaiDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(LabelYobiyokaiDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public LabelYobiyokaiDaoTest(final String testname) {
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

		LabelYobiyokai lhs = new LabelYobiyokai();
		LabelYobiyokai rhs = new LabelYobiyokai();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
