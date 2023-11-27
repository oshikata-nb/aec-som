/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.commonattributequeuedetail;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CommonAttributeQueueDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class CommonAttributeQueueDetailDaoTest extends
		AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CommonAttributeQueueDetailDaoTest(final String testname) {
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
		CommonAttributeQueueDetail lhs = new CommonAttributeQueueDetail();
		CommonAttributeQueueDetail rhs = new CommonAttributeQueueDetail();

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
	private void setValue(final CommonAttributeQueueDetail bean) {
		bean.setItemCd("ITEM001");
		bean.setVersion(new BigDecimal("1"));
	}
}
