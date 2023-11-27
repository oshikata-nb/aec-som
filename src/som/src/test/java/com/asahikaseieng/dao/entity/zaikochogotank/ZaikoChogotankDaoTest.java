/*
 * Created on Wed Feb 04 16:11:18 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.zaikochogotank;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ZaikoChogotankDaoクラスのテストケース
 * @author kanri-user
 */
public final class ZaikoChogotankDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ZaikoChogotankDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ZaikoChogotankDaoTest(final String testname) {
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

		ZaikoChogotank lhs = new ZaikoChogotank();
		ZaikoChogotank rhs = new ZaikoChogotank();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
