/*
 * Created on Wed Feb 06 19:01:45 JST 2008
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.remark;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.dao.nonentity.autocomplete.remark.RemarkForAutoComplete;
import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RemarkDaoクラスのテストケース
 * @author a1020630
 */
public final class RemarkDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(RemarkDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public RemarkDaoTest(final String testname) {
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

		RemarkForAutoComplete lhs = new RemarkForAutoComplete();
		RemarkForAutoComplete rhs = new RemarkForAutoComplete();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
