/*
 * Created on Wed Feb 04 16:10:57 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.sijiseizo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * SijiSeizoDaoクラスのテストケース
 * @author kanri-user
 */
public final class SijiSeizoDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(SijiSeizoDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public SijiSeizoDaoTest(final String testname) {
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

		SijiSeizo lhs = new SijiSeizo();
		SijiSeizo rhs = new SijiSeizo();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
