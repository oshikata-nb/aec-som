/*
 * Created on 2009/05/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.accountsnamelist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockAccountsNameListDaoクラス
 * @author t0011036
 */
public class MockAccountsNameListDao implements AccountsNameListDao {

	/**
	 * コンストラクタ.
	 */
	public MockAccountsNameListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AccountsNameList> getNameList(final String accountsCd) {
		List<AccountsNameList> list = new ArrayList<AccountsNameList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(accountsCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(accountsCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* AccountsNameListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * AccountsNameListを生成する
	 * @param i インデックス
	 * @return AccountsNameList
	 */
	private AccountsNameList createBean(final int i) {
		AccountsNameList bean = new AccountsNameList();
		bean.setAccountsCd("ACCOUNTS" + i);
		bean.setAccountsName("NAME" + i);
		return bean;
	}
}
