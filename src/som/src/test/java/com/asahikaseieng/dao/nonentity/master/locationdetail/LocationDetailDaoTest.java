/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LocationDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class LocationDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LocationDetailDaoTest(final String testname) {
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
		LocationDetail lhs = new LocationDetail();
		LocationDetail rhs = new LocationDetail();

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
	private void setValue(final LocationDetail bean) {
		bean.setLocationCd("LOCATION_CD01");
		bean.setLocationName("NAME01");
	}
}
