/*
 * Created on Thu Jan 22 19:01:01 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.lineoperationgroup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LineOperationGroupDaoクラスのテストケース
 * @author t0011036
 */
public final class LineOperationGroupDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(LineOperationGroupDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public LineOperationGroupDaoTest(final String testname) {
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

		LineOperationGroup lhs = new LineOperationGroup();
		LineOperationGroup rhs = new LineOperationGroup();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
