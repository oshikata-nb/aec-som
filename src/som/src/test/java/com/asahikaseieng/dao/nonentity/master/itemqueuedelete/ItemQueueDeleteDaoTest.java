/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuedelete;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemQueueDeleteDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemQueueDeleteDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemQueueDeleteDaoTest(final String testname) {
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
		ItemQueueDelete lhs = new ItemQueueDelete();
		ItemQueueDelete rhs = new ItemQueueDelete();

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
	private void setValue(final ItemQueueDelete bean) {
		bean.setItemCd("ITEM001");
		bean.setItemName("NAME001");
	}
}
