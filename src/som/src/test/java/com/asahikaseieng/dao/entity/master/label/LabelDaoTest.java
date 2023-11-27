/*
 * Created on Fri Jan 23 13:53:49 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.label;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LabelDaoクラスのテストケース
 * @author t0011036
 */
public final class LabelDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(LabelDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public LabelDaoTest(final String testname) {
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

		Label lhs = new Label();
		Label rhs = new Label();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
