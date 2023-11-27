/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offsetlistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOffsetListForReportDaoクラス
 * @author t0011036
 */
public class MockOffsetListForReportDao implements OffsetListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockOffsetListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OffsetListForReport> getListForReport(
			final OffsetListConditionForReport condition) {
		List<OffsetListForReport> list = new ArrayList<OffsetListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOrganizationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhTantoCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOrganizationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD
				.equals(condition.getSrhTantoCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OffsetListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OffsetListForReportを生成する
	 * @param i インデックス
	 * @return OffsetListForReport
	 */
	private OffsetListForReport createBean(final int i) {
		OffsetListForReport bean = new OffsetListForReport();
		bean.setOrganizationCd("CD" + i);
		bean.setOrganizationName("NAME" + i);
		return bean;
	}
}
