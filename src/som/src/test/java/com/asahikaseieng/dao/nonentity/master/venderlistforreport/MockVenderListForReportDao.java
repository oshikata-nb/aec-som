/*
 * Created on 2009/05/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderlistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockVenderListForReportDaoクラス
 * @author t0011036
 */
public class MockVenderListForReportDao implements VenderListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockVenderListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<VenderListForReport> getListForReport(
			final VenderListConditionForReport condition) {
		List<VenderListForReport> list = new ArrayList<VenderListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhVenderDivision())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderDivision())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOrganizationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOrganizationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* VenderListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * VenderListForReportを生成する
	 * @param i インデックス
	 * @return VenderListForReport
	 */
	private VenderListForReport createBean(final int i) {
		VenderListForReport bean = new VenderListForReport();
		bean.setVenderDivision("TS");
		bean.setVenderCd("VENDER" + i);
		bean.setVenderName1("NAME" + i);
		return bean;
	}
}
