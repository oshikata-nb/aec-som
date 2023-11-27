/*
 * Created on Mon Dec 10 14:53:53 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.organization;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * OrganizationDaoクラスのテストケース
 * @author t0011036
 */
public final class OrganizationDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(OrganizationDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public OrganizationDaoTest(final String testname) {
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

		Organization lhs = new Organization();
		Organization rhs = new Organization();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
