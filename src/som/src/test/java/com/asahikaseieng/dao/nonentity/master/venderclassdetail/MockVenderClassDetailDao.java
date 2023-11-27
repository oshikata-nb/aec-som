/*
 * Created on 2009/07/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderclassdetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockVenderClassDetailDaoクラス
 * @author t0011036
 */
public class MockVenderClassDetailDao implements VenderClassDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockVenderClassDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public VenderClassDetail getEntity(final BigDecimal dataType,
			final String categoryDivision) {
		if (Constants.TEST_PARAMETER_NODATA.equals(categoryDivision)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(categoryDivision)) {
			throw new LargeAmountDataRuntimeException();
		}

		VenderClassDetail bean = new VenderClassDetail();

		/* VenderClassDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * VenderClassDetailを生成する
	 * @param bean VenderClassDetail
	 * @return VenderClassDetail
	 */
	private void createBean(final VenderClassDetail bean) {
		bean.setCategoryDivision("1");
		bean.setCategoryName("NAME01");
		bean.setNoteDivision(new BigDecimal("1"));
	}
}
