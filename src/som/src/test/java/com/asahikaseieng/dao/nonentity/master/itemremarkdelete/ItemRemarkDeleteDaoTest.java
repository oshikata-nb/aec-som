/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemremarkdelete;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemRemarkDeleteDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemRemarkDeleteDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemRemarkDeleteDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * deleteのテスト
	 */
	public void testDeleteTx() {
		ItemRemarkDelete lhs = new ItemRemarkDelete();
		ItemRemarkDelete rhs = new ItemRemarkDelete();

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
	private void setValue(final ItemRemarkDelete bean) {
		bean.setItemCd("ITEM001");
		bean.setVersion(new BigDecimal("1"));
	}
}
