/*
 * Created on Tue Apr 28 09:04:05 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.transnote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * TransNoteDaoクラスのテストケース
 * @author t0011036
 */
public final class TransNoteDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(TransNoteDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TransNoteDaoTest(final String testname) {
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

		TransNote lhs = new TransNote();
		TransNote rhs = new TransNote();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
