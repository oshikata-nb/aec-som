/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemDetailDaoTest(final String testname) {
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
		ItemDetail lhs = new ItemDetail();
		ItemDetail rhs = new ItemDetail();

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
	private void setValue(final ItemDetail bean) {
		bean.setItemCd("ITEM_CD001");
		bean.setItemName("NAME001");
	}
}
