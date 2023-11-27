/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.location;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLocationListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockLocationForAutoCompleteDao implements
		LocationForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockLocationForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LocationForAutoComplete> getSearchList(final String locationCd,
			final String rowlimit) {
		List<LocationForAutoComplete> list = new ArrayList<LocationForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(locationCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(locationCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* LocationListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LocationListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return LocationListForAutoComplete
	 */
	private LocationForAutoComplete createBean(final int i) {
		LocationForAutoComplete bean = new LocationForAutoComplete();
		bean.setLocationCd("LOCATION_CD" + i);
		bean.setLocationName("NAME" + i);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LocationForAutoComplete> getSearchAvailableList(
			final String locationCd, final String rowlimit) {
		List<LocationForAutoComplete> list = new ArrayList<LocationForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(locationCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(locationCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* LocationListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}
}
