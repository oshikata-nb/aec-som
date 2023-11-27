/*
 * Created on 2009/08/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.nameslistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockNamesListForReportDaoクラス
 * @author t0011036
 */
public class MockNamesListForReportDao implements NamesListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockNamesListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<NamesListForReport> getListForReport(
			final NamesListConditionForReport condition) {
		List<NamesListForReport> list = new ArrayList<NamesListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhNameDivision())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhNameDivision())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhNameCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhNameCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* NamesListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * NamesListForReportを生成する
	 * @param i インデックス
	 * @return NamesListForReport
	 */
	private NamesListForReport createBean(final int i) {
		NamesListForReport bean = new NamesListForReport();
		bean.setNameDivision("NAME_DIVISION" + i);
		bean.setNameCd("NAME_CD" + i);
		bean.setName01("NAME01" + i);
		bean.setName02("NAME02" + i);
		bean.setName03("NAME03" + i);
		return bean;
	}
}
