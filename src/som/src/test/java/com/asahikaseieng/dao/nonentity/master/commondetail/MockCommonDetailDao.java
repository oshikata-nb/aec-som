/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.commondetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCommonDetailDaoクラス
 * @author t0011036
 */
public class MockCommonDetailDao implements CommonDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockCommonDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public CommonDetail getEntity(final String commonCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(commonCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(commonCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		CommonDetail bean = new CommonDetail();

		/* CommonDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * CommonDetailを生成する
	 * @param bean bean
	 * @return CommonDetail
	 */
	private void createBean(final CommonDetail bean) {
		bean.setCommonCd("CD001");
		bean.setCommonName("NAME001");
		bean.setCommonValue("VALUE001");
	}
}
