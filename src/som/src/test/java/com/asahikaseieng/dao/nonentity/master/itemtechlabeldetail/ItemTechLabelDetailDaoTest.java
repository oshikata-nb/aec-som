/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemtechlabeldetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemTechLabelDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemTechLabelDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ItemTechLabelDetailDaoTest(final String testname) {
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
		ItemTechLabelDetail lhs = new ItemTechLabelDetail();
		ItemTechLabelDetail rhs = new ItemTechLabelDetail();

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
	private void setValue(final ItemTechLabelDetail bean) {
		bean.setCommonCd("COMMON001");
		bean.setLabelCd("LABEL001");
	}
}
