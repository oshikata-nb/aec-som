/*
 * Created on 2009/05/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarklistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRemarkListForReportDaoクラス
 * @author t0011036
 */
public class MockRemarkListForReportDao implements RemarkListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockRemarkListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RemarkListForReport> getListForReport(
			final RemarkListConditionForReport condition) {
		List<RemarkListForReport> list = new ArrayList<RemarkListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhDeliveryCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhDeliveryCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RemarkListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RemarkListForReportを生成する
	 * @param i インデックス
	 * @return RemarkListForReport
	 */
	private RemarkListForReport createBean(final int i) {
		RemarkListForReport bean = new RemarkListForReport();
		bean.setRemarkNo(new BigDecimal(i));
		return bean;
	}
}
