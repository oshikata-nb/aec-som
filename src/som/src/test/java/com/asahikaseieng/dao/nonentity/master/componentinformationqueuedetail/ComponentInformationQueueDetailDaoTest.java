/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentinformationqueuedetail;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ComponentInformationQueueDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class ComponentInformationQueueDetailDaoTest extends
		AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ComponentInformationQueueDetailDaoTest(final String testname) {
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
		ComponentInformationQueueDetail lhs = new ComponentInformationQueueDetail();
		ComponentInformationQueueDetail rhs = new ComponentInformationQueueDetail();

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
	private void setValue(final ComponentInformationQueueDetail bean) {
		bean.setItemCd("ITEM001");
		bean.setVersion(new BigDecimal("1"));
	}
}
