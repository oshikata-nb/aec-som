/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.categorydetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCategoryDetailDaoクラス
 * @author t0011036
 */
public class MockCategoryDetailDao implements CategoryDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockCategoryDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public CategoryDetail getEntity(final String categoryCd,
			final String categoryDivision) {
		if (Constants.TEST_PARAMETER_NODATA.equals(categoryCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(categoryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		CategoryDetail bean = new CategoryDetail();

		/* CategoryDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * CategoryDetailを生成する
	 * @param bean bean
	 * @return CategoryDetail
	 */
	private void createBean(final CategoryDetail bean) {
		bean.setCategoryDivision("DIVISION001");
		bean.setCategoryCd("CD001");
		bean.setCategoryName("NAME001");
	}
}
