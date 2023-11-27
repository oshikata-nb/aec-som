/*
 * Created on 2009/08/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companylistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCompanyListForReportDaoクラス
 * @author t0011036
 */
public class MockCompanyListForReportDao implements CompanyListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockCompanyListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CompanyListForReport> getListForReport(
			final CompanyListConditionForReport condition) {
		List<CompanyListForReport> list = new ArrayList<CompanyListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhHomeName1())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhHomeName1())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* CompanyListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CompanyListForReportを生成する
	 * @param i インデックス
	 * @return CompanyListForReport
	 */
	private CompanyListForReport createBean(final int i) {
		CompanyListForReport bean = new CompanyListForReport();
		bean.setCompanyCd("COMPANY" + i);
		bean.setHomeName1("NAME" + i);
		return bean;
	}
}
