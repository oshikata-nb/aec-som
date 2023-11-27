/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bankdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBankDetailDaoクラス
 * @author t0011036
 */
public class MockBankDetailDao implements BankDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockBankDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public BankDetail getEntity(final String bankCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(bankCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(bankCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		BankDetail bean = new BankDetail();

		/* BankDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * BankDetailを生成する
	 * @param bean bean
	 * @return BankDetail
	 */
	private void createBean(final BankDetail bean) {
		bean.setBankCd("BANK001");
		bean.setBankName("NAME001");
	}
}
