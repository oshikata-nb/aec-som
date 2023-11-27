/*
 * Created on 2009/05/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumonlistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBumonListForReportDaoクラス
 * @author t0011036
 */
public class MockBumonListForReportDao implements BumonListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockBumonListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BumonListForReport> getListForReport(
			final BumonListConditionForReport condition) {
		List<BumonListForReport> list = new ArrayList<BumonListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhSectionCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhSectionCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BumonListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BumonListForReportを生成する
	 * @param i インデックス
	 * @return BumonListForReport
	 */
	private BumonListForReport createBean(final int i) {
		BumonListForReport bean = new BumonListForReport();
		bean.setSectionCd("SECTION" + i);
		bean.setSectionName("NAME" + i);
		return bean;
	}
}
