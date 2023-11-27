/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelowerdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBalanceLowerDetailDaoクラス
 * @author t0011036
 */
public class MockBalanceLowerDetailDao implements BalanceLowerDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockBalanceLowerDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public BalanceLowerDetail getEntity(final String upperBalanceCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(upperBalanceCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(upperBalanceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		BalanceLowerDetail bean = new BalanceLowerDetail();

		/* BalanceLowerDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * BalanceLowerDetailを生成する
	 * @param bean bean
	 * @return BalanceLowerDetail
	 */
	private void createBean(final BalanceLowerDetail bean) {
		bean.setBalanceCd("BALANCE001");
	}
}
