/*
 * Created on 2009/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemupdate;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * ItemUpdateDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemUpdateDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemUpdateDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * updateのテスト
	 */
	public void testUpdateTx() {
		ItemUpdate lhs = new ItemUpdate();
		ItemUpdate rhs = new ItemUpdate();

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
	private void setValue(final ItemUpdate bean) {
		bean.setItemCd("ITEM01");
		bean.setVersion(new BigDecimal("1"));
		bean.setUpdateDate(AecDateUtils.getCurrentTimestamp());
	}
}
