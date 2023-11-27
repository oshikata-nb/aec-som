/*
 * Created on 2009/05/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.caldetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCalDetailDaoクラス
 * @author t0011036
 */
public class MockCalDetailDao implements CalDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockCalDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public CalDetail getEntity(final String calCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(calCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(calCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		CalDetail bean = new CalDetail();

		/* CalDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * CalDetailを生成する
	 * @param bean bean
	 * @return CalDetail
	 */
	private void createBean(final CalDetail bean) {
		bean.setCalCd("CAL001");
	}
}
