/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companydetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCompanyDetailDaoクラス
 * @author t0011036
 */
public class MockCompanyDetailDao implements CompanyDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockCompanyDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public CompanyDetail getEntity(final String companyCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(companyCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(companyCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		CompanyDetail bean = new CompanyDetail();

		/* CompanyDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * CompanyDetailを生成する
	 * @param bean bean
	 * @return CompanyDetail
	 */
	private void createBean(final CompanyDetail bean) {
		bean.setCompanyCd("COMPANY001");
		bean.setHomeName1("NAME001");
	}
}
