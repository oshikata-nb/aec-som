/*
 * Created on 2009/05/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.financialclassdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockFinancialClassDetailDaoクラス
 * @author t0011036
 */
public class MockFinancialClassDetailDao implements FinancialClassDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockFinancialClassDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FinancialClassDetail getEntity(final String financialClassCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(financialClassCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(financialClassCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		FinancialClassDetail bean = new FinancialClassDetail();

		/* FinancialClassDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * FinancialClassDetailを生成する
	 * @param bean bean
	 * @return FinancialClassDetail
	 */
	private void createBean(final FinancialClassDetail bean) {
		bean.setFinancialClassCd("CD001");
		bean.setFinancialClassName("NAME001");
	}
}
