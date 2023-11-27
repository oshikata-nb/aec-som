/*
 * Created on Wed Jan 21 16:56:46 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.vender;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * VenderDaoクラスのテストケース
 * @author kanri-user
 */
public final class VenderDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(VenderDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public VenderDaoTest(final String testname) {
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

		Vender lhs = new Vender();
		Vender rhs = new Vender();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
