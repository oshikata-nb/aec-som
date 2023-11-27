/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.accountsdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockAccountsDetailDaoクラス
 * @author t0011036
 */
public class MockAccountsDetailDao implements AccountsDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockAccountsDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public AccountsDetail getEntity(final String accountsCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(accountsCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(accountsCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		AccountsDetail bean = new AccountsDetail();

		/* AccountsDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * AccountsDetailを生成する
	 * @param bean AccountsDetail
	 * @return AccountsDetail
	 */
	private void createBean(final AccountsDetail bean) {
		bean.setAccountsCd("ACCOUNTS01");
		bean.setAccountsName("現金");
		bean.setTaxationDivision("001");
	}
}
