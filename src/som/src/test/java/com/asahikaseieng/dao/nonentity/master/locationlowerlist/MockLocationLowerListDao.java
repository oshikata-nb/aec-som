/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationlowerlist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLocationLowerListDaoクラス
 * @author t0011036
 */
public class MockLocationLowerListDao implements LocationLowerListDao {

	/**
	 * コンストラクタ.
	 */
	public MockLocationLowerListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LocationLowerList> getList(final String upperLocationCd) {
		List<LocationLowerList> list = new ArrayList<LocationLowerList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(upperLocationCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(upperLocationCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* LocationLowerListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LocationLowerListを生成する
	 * @param i インデックス
	 * @return LocationLowerList
	 */
	private LocationLowerList createBean(final int i) {
		LocationLowerList bean = new LocationLowerList();
		bean.setLocationCd("LOCATION_CD" + i);
		bean.setLocationName("NAME" + i);
		return bean;
	}
}
