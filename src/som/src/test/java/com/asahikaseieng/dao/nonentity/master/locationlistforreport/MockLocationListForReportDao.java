/*
 * Created on 2009/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationlistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLocationListForReportDaoクラス
 * @author t0011036
 */
public class MockLocationListForReportDao implements LocationListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockLocationListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LocationListForReport> getListForReport(
			final LocationListConditionForReport condition) {
		List<LocationListForReport> list = new ArrayList<LocationListForReport>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* LocationListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LocationListForReportを生成する
	 * @param i インデックス
	 * @return LocationListForReport
	 */
	private LocationListForReport createBean(final int i) {
		LocationListForReport bean = new LocationListForReport();
		bean.setLocationCd("LOCATION_CD" + i);
		bean.setLocationName("NAME" + i);
		return bean;
	}
}
