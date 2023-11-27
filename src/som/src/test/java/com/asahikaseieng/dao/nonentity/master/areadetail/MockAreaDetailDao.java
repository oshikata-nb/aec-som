/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.areadetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockAreaDetailDaoクラス
 * @author t0011036
 */
public class MockAreaDetailDao implements AreaDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockAreaDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public AreaDetail getEntity(final String areaCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(areaCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(areaCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		AreaDetail bean = new AreaDetail();

		/* AreaDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * AreaDetailを生成する
	 * @param bean bean
	 * @return AreaDetail
	 */
	private void createBean(final AreaDetail bean) {
		bean.setAreaCd("AREA001");
		bean.setAreaName("地区名００１");
	}
}
