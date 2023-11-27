/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.logindetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLoginDetailDaoクラス
 * @author t0011036
 */
public class MockLoginDetailDao implements LoginDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockLoginDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public LoginDetail getEntity(final String tantoCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(tantoCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		LoginDetail bean = new LoginDetail();

		/* LoginDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * LoginDetailを生成する
	 * @param bean bean
	 * @return LoginDetail
	 */
	private void createBean(final LoginDetail bean) {
		bean.setTantoCd("TANTO_CD01");
		bean.setTantoNm("NAME01");
	}
}
