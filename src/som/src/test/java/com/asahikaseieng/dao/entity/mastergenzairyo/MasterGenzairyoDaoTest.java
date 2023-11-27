/*
 * Created on Wed Feb 04 16:11:50 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.mastergenzairyo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * MasterGenzairyoDaoクラスのテストケース
 * @author kanri-user
 */
public final class MasterGenzairyoDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(MasterGenzairyoDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public MasterGenzairyoDaoTest(final String testname) {
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

		MasterGenzairyo lhs = new MasterGenzairyo();
		MasterGenzairyo rhs = new MasterGenzairyo();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
