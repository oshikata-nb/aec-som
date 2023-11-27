/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentinformationqueuedeletelist;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ComponentInformationQueueDeleteListDaoクラスのテストケース
 * @author t0011036
 */
public final class ComponentInformationQueueDeleteListDaoTest extends
		AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ComponentInformationQueueDeleteListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * deleteListのテスト
	 */
	public void testDeleteListTx() {
		ComponentInformationQueueDeleteList lhs = new ComponentInformationQueueDeleteList();
		ComponentInformationQueueDeleteList rhs = new ComponentInformationQueueDeleteList();

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
	private void setValue(final ComponentInformationQueueDeleteList bean) {
		bean.setItemCd("ITEM_CD001");
		bean.setVersion(new BigDecimal("1"));
	}
}
