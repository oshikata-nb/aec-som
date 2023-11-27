/*
 * Created on Wed Feb 04 16:11:07 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tankkaijyo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * TankKaijyoDaoクラスのテストケース
 * @author kanri-user
 */
public final class TankKaijyoDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(TankKaijyoDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TankKaijyoDaoTest(final String testname) {
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

		TankKaijyo lhs = new TankKaijyo();
		TankKaijyo rhs = new TankKaijyo();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
