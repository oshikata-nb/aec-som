/*
 * Created on Wed Feb 04 16:09:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jokyoseihinbetsushukko;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * JokyoSeihinbetsuShukkoDaoクラスのテストケース
 * @author kanri-user
 */
public final class JokyoSeihinbetsuShukkoDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(JokyoSeihinbetsuShukkoDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public JokyoSeihinbetsuShukkoDaoTest(final String testname) {
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

		JokyoSeihinbetsuShukko lhs = new JokyoSeihinbetsuShukko();
		JokyoSeihinbetsuShukko rhs = new JokyoSeihinbetsuShukko();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
