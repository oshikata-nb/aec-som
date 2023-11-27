/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OperationDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class OperationDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OperationDetailDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getEntityのテスト
	 */
	public void testGetEntityTx() {
		OperationDetail lhs = new OperationDetail();
		OperationDetail rhs = new OperationDetail();

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
	private void setValue(final OperationDetail bean) {
		bean.setOperationCd("OPERATION_CD01");
		bean.setOperationName("NAME01");
	}
}
