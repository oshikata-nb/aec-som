/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBalanceListForReportDaoクラス
 * @author t0011036
 */
public class MockBalanceListForReportDao implements BalanceListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockBalanceListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BalanceListForReport> getListForReport(
			final BalanceListConditionForReport condition) {
		List<BalanceListForReport> list = new ArrayList<BalanceListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BalanceListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BalanceListForReportを生成する
	 * @param i インデックス
	 * @return BalanceListForReport
	 */
	private BalanceListForReport createBean(final int i) {
		BalanceListForReport bean = new BalanceListForReport();
		bean.setBalanceCd("BALANCE" + i);
		bean.setBalanceTypeName("BALANCE_NAME" + i);
		bean.setVenderName1("VENDER_NAME1" + i);
		bean.setVenderName2("VENDER_NAME2" + i);
		bean.setVenderName3("VENDER_NAME3" + i);
		bean.setVenderName4("VENDER_NAME4" + i);
		bean.setVenderName5("VENDER_NAME5" + i);
		return bean;
	}
}
