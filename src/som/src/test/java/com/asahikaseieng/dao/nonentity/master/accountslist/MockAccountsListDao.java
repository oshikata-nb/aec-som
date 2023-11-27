/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.accountslist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockAccountsListDaoクラス
 * @author t0011036
 */
public class MockAccountsListDao implements AccountsListDao {

	/**
	 * コンストラクタ.
	 */
	public MockAccountsListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AccountsList> getList(final AccountsListPagerCondition condition) {
		List<AccountsList> list = new ArrayList<AccountsList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhAccountsCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhAccountsCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* AccountsListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * AccountsListを生成する
	 * @param i インデックス
	 * @return AccountsList
	 */
	private AccountsList createBean(final int i) {
		AccountsList bean = new AccountsList();
		bean.setAccountsCd("ACCOUNTS" + i);
		bean.setAccountsName("NAME" + i);
		return bean;
	}
}
