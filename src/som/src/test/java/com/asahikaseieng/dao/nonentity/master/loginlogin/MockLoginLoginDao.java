/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginlogin;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLoginLoginDaoクラス
 * @author t0011036
 */
public class MockLoginLoginDao implements LoginLoginDao {

	/**
	 * コンストラクタ.
	 */
	public MockLoginLoginDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public LoginLogin getEntity(final String tantoCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(tantoCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		LoginLogin bean = new LoginLogin();

		/* LoginLoginを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * LoginLoginを生成する
	 * @param bean bean
	 * @return LoginLogin
	 */
	private void createBean(final LoginLogin bean) {
		bean.setTantoCd("TANTO_CD01");
		bean.setTantoNm("NAME01");
	}
}
