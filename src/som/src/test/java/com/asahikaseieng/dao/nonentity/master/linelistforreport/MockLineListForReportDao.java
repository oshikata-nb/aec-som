/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.linelistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLineListForReportDaoクラス
 * @author t0011036
 */
public class MockLineListForReportDao implements LineListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockLineListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LineListForReport> getListForReport(
			final LineListConditionForReport condition) {
		List<LineListForReport> list = new ArrayList<LineListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhProductionLine())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhProductionLine())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* LineListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LineListForReportを生成する
	 * @param i インデックス
	 * @return LineListForReport
	 */
	private LineListForReport createBean(final int i) {
		LineListForReport bean = new LineListForReport();
		bean.setProductionLine("PRODUCTION_LINE" + i);
		bean.setProductionLineName("NAME" + i);
		return bean;
	}
}
