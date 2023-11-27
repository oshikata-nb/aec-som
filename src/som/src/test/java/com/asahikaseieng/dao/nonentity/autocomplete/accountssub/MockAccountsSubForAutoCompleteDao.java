/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.accountssub;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockAccountsSubForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockAccountsSubForAutoCompleteDao implements
		AccountsSubForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockAccountsSubForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AccountsSubForAutoComplete> getSearchList(
			final String accountsSubCd, final String rowlimit) {
		List<AccountsSubForAutoComplete> list = new ArrayList<AccountsSubForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(accountsSubCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(accountsSubCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* AccountsSubListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * AccountsSubListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return AccountsSubListForAutoComplete
	 */
	private AccountsSubForAutoComplete createBean(final int i) {
		AccountsSubForAutoComplete bean = new AccountsSubForAutoComplete();
		bean.setAccountsSubCd("ACCOUNTS_SUB" + i);
		bean.setAccountsSubName("現金");
		return bean;
	}
}
