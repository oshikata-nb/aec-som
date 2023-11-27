/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationlist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLocationListDaoクラス
 * @author t0011036
 */
public class MockLocationListDao implements LocationListDao {

	/**
	 * コンストラクタ.
	 */
	public MockLocationListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LocationList> getList(final LocationListPagerCondition condition) {
		List<LocationList> list = new ArrayList<LocationList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* LocationListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LocationListを生成する
	 * @param i インデックス
	 * @return LocationList
	 */
	private LocationList createBean(final int i) {
		LocationList bean = new LocationList();
		bean.setLocationCd("LOCATION_CD" + i);
		bean.setLocationName("NAME" + i);
		return bean;
	}
}
