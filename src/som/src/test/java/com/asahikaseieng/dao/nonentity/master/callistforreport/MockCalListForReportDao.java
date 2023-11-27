/*
 * Created on 2009/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.callistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCalListForReportDaoクラス
 * @author t0011036
 */
public class MockCalListForReportDao implements CalListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockCalListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CalListForReport> getListForReport(
			final CalListConditionForReport condition) {
		List<CalListForReport> list = new ArrayList<CalListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhCalCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhCalCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhCalYear())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD
				.equals(condition.getSrhCalYear())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* CalListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CalListForReportを生成する
	 * @param i インデックス
	 * @return CalListForReport
	 */
	private CalListForReport createBean(final int i) {
		CalListForReport bean = new CalListForReport();
		bean.setCalCd("CAL_CD" + i);
		bean.setCalName("NAME" + i);
		return bean;
	}
}
