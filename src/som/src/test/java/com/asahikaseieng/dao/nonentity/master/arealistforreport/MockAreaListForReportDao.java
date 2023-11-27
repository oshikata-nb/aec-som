/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.arealistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockAreaListForReportDaoクラス
 * @author t0011036
 */
public class MockAreaListForReportDao implements AreaListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockAreaListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AreaListForReport> getListForReport(
			final AreaListConditionForReport condition) {
		List<AreaListForReport> list = new ArrayList<AreaListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhAreaCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhAreaCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* AreaListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * AreaListForReportを生成する
	 * @param i インデックス
	 * @return AreaListForReport
	 */
	private AreaListForReport createBean(final int i) {
		AreaListForReport bean = new AreaListForReport();
		bean.setAreaCd("AREA" + i);
		bean.setAreaName("地区名" + i);
		return bean;
	}
}
