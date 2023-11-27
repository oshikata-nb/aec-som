/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationgroupdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OperationGroupDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class OperationGroupDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OperationGroupDetailDaoTest(final String testname) {
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
		OperationGroupDetail lhs = new OperationGroupDetail();
		OperationGroupDetail rhs = new OperationGroupDetail();

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
	private void setValue(final OperationGroupDetail bean) {
		bean.setOperationGroupCd("OPERATION_GROUP_CD01");
		bean.setOperationGroupName("NAME01");
	}
}
