/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasonlistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockReasonListForReportDaoクラス
 * @author t0011036
 */
public class MockReasonListForReportDao implements ReasonListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockReasonListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ReasonListForReport> getListForReport(
			final ReasonListConditionForReport condition) {
		List<ReasonListForReport> list = new ArrayList<ReasonListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhRyCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhRyCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ReasonListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ReasonListForReportを生成する
	 * @param i インデックス
	 * @return ReasonListForReport
	 */
	private ReasonListForReport createBean(final int i) {
		ReasonListForReport bean = new ReasonListForReport();
		bean.setRyCd("RY" + i);
		bean.setRyDescription("DESCRIPTION" + i);
		bean.setDefaultFlg(new BigDecimal("0"));
		return bean;
	}
}
