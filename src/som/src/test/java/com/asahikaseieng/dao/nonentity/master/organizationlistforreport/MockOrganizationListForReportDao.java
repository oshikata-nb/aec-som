/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationlistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOrganizationListForReportDaoクラス
 * @author t0011036
 */
public class MockOrganizationListForReportDao implements
		OrganizationListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockOrganizationListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OrganizationListForReport> getListForReport(
			final OrganizationListConditionForReport condition) {
		List<OrganizationListForReport> list = new ArrayList<OrganizationListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOrganizationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOrganizationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OrganizationListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OrganizationListForReportを生成する
	 * @param i インデックス
	 * @return OrganizationListForReport
	 */
	private OrganizationListForReport createBean(final int i) {
		OrganizationListForReport bean = new OrganizationListForReport();
		bean.setOrganizationCd("ORGANIZATION" + i);
		bean.setOrganizationName("NAME" + i);
		return bean;
	}
}
