/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.linedetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLineDetailDaoクラス
 * @author t0011036
 */
public class MockLineDetailDao implements LineDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockLineDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public LineDetail getEntity(final String productionLine) {
		if (Constants.TEST_PARAMETER_NODATA.equals(productionLine)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(productionLine)) {
			throw new LargeAmountDataRuntimeException();
		}

		LineDetail bean = new LineDetail();

		/* LineDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * LineDetailを生成する
	 * @param bean bean
	 * @return LineDetail
	 */
	private void createBean(final LineDetail bean) {
		bean.setProductionLine("PRODUCTION_LINE01");
		bean.setProductionLineName("NAME01");
	}
}
