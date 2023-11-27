/*
 * Created on Fri Mar 27 09:13:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.viewauthority;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ViewAuthorityDaoクラスのテストケース
 * @author t0011036
 */
public final class ViewAuthorityDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ViewAuthorityDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ViewAuthorityDaoTest(final String testname) {
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

		ViewAuthority lhs = new ViewAuthority();
		ViewAuthority rhs = new ViewAuthority();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
