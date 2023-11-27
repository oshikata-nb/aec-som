/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.financialclasslistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockFinancialClassListForReportDaoクラス
 * @author t0011036
 */
public class MockFinancialClassListForReportDao implements
		FinancialClassListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockFinancialClassListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<FinancialClassListForReport> getListForReport(
			final FinancialClassListConditionForReport condition) {
		List<FinancialClassListForReport> list = new ArrayList<FinancialClassListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhFinancialClassCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhFinancialClassCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* FinancialClassListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * FinancialClassListForReportを生成する
	 * @param i インデックス
	 * @return FinancialClassListForReport
	 */
	private FinancialClassListForReport createBean(final int i) {
		FinancialClassListForReport bean = new FinancialClassListForReport();
		bean.setFinancialClassCd("CD" + i);
		bean.setFinancialClassName("NAME" + i);
		return bean;
	}
}
