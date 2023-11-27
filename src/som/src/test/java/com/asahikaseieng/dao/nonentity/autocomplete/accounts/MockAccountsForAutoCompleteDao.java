/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.accounts;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockAccountsForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockAccountsForAutoCompleteDao implements
		AccountsForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockAccountsForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AccountsForAutoComplete> getSearchList(final String accountsCd,
			final String rowlimit) {
		List<AccountsForAutoComplete> list = new ArrayList<AccountsForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(accountsCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(accountsCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* AccountsListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * AccountsListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return AccountsListForAutoComplete
	 */
	private AccountsForAutoComplete createBean(final int i) {
		AccountsForAutoComplete bean = new AccountsForAutoComplete();
		bean.setAccountsCd("ACCOUNTS" + i);
		bean.setAccountsName("現金");
		return bean;
	}
}
