/*
 * Created on Wed Feb 04 16:12:37 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.mastertantosha;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * MasterTantoshaDaoクラスのテストケース
 * @author kanri-user
 */
public final class MasterTantoshaDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(MasterTantoshaDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public MasterTantoshaDaoTest(final String testname) {
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

		MasterTantosha lhs = new MasterTantosha();
		MasterTantosha rhs = new MasterTantosha();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
