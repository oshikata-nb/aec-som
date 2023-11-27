/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carrylistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCarryListForReportDaoクラス
 * @author t0011036
 */
public class MockCarryListForReportDao implements CarryListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockCarryListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CarryListForReport> getListForReport(
			final CarryListConditionForReport condition) {
		List<CarryListForReport> list = new ArrayList<CarryListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhCarryCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD
				.equals(condition.getSrhCarryCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* CarryListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CarryListForReportを生成する
	 * @param i インデックス
	 * @return CarryListForReport
	 */
	private CarryListForReport createBean(final int i) {
		CarryListForReport bean = new CarryListForReport();
		bean.setCarryCd("CARRY" + i);
		bean.setCarryName1("NAME" + i);
		return bean;
	}
}
