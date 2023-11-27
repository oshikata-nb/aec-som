/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLocationDetailDaoクラス
 * @author t0011036
 */
public class MockLocationDetailDao implements LocationDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockLocationDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public LocationDetail getEntity(final String locationCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(locationCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(locationCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		LocationDetail bean = new LocationDetail();

		/* LocationDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * LocationDetailを生成する
	 * @param bean bean
	 * @return LocationDetail
	 */
	private void createBean(final LocationDetail bean) {
		bean.setLocationCd("LOCATION_CD01");
		bean.setLocationName("NAME01");
	}

	/**
	 * {@inheritDoc}
	 */
	public LocationDetail getAvailableEntity(final String locationCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(locationCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(locationCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		LocationDetail bean = new LocationDetail();

		/* LocationDetailを生成する */
		createBean(bean);

		return bean;
	}
}
