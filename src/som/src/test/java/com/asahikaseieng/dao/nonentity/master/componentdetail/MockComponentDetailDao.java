/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockComponentDetailDaoクラス
 * @author t0011036
 */
public class MockComponentDetailDao implements ComponentDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockComponentDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ComponentDetail getEntity(final String componentCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(componentCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(componentCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ComponentDetail bean = new ComponentDetail();

		/* ComponentDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ComponentDetailを生成する
	 * @param bean bean
	 * @return ComponentDetail
	 */
	private void createBean(final ComponentDetail bean) {
		bean.setComponentCd("COMPONENT001");
		bean.setComponentName("NAME001");
	}
}
