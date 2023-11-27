/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarkdetailgetmaxno;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RemarkDetailGetMaxRemarkNoDaoクラスのテストケース
 * @author kanri-user
 */
public final class RemarkDetailGetMaxRemarkNoDaoTest extends
		AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RemarkDetailGetMaxRemarkNoDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * getEntityのテスト
	 */
	public void testGetEntityTx() {
		RemarkDetailGetMaxRemarkNo lhs = new RemarkDetailGetMaxRemarkNo();
		RemarkDetailGetMaxRemarkNo rhs = new RemarkDetailGetMaxRemarkNo();

		/* 検索条件設定 */
		setValue(lhs);
		setValue(rhs);

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
	}

	/**
	 * 検索条件設定
	 * @param bean
	 */
	private void setValue(final RemarkDetailGetMaxRemarkNo bean) {
		bean.setMax(new BigDecimal(1));
	}
}
