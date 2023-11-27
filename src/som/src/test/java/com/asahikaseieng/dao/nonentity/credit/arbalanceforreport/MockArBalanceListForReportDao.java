/*
 * Created on 2009/08/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arbalanceforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockArBalanceListForReportDaoクラス
 * @author t0011036
 */
public class MockArBalanceListForReportDao implements ArBalanceListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockArBalanceListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ArBalanceListForReport> getListForReport(
			final ArBalanceListConditionForReport condition) {
		List<ArBalanceListForReport> list = new ArrayList<ArBalanceListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhSectionCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhTantoCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhSectionCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD
				.equals(condition.getSrhTantoCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ArBalanceListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ArBalanceListForReportを生成する
	 * @param i インデックス
	 * @return ArBalanceListForReport
	 */
	private ArBalanceListForReport createBean(final int i) {
		ArBalanceListForReport bean = new ArBalanceListForReport();
		bean.setOrganizationCd("ORGANIZATION" + i);
		bean.setInputorCd("INPUTOR" + i);
		bean.setVenderCd("VENDER" + i);
		return bean;
	}
}
