/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.accountslistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockAccountsListForReportDaoクラス
 * @author t0011036
 */
public class MockAccountsListForReportDao implements AccountsListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockAccountsListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AccountsListForReport> getListForReport(
			final AccountsListConditionForReport condition) {
		List<AccountsListForReport> list = new ArrayList<AccountsListForReport>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhAccountsCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhAccountsCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* AccountsListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * AccountsListForReportを生成する
	 * @param i インデックス
	 * @return AccountsListForReport
	 */
	private AccountsListForReport createBean(final int i) {
		AccountsListForReport bean = new AccountsListForReport();
		bean.setAccountsCd("ACCOUNTS" + i);
		bean.setAccountsName("NAME" + i);
		return bean;
	}
}
