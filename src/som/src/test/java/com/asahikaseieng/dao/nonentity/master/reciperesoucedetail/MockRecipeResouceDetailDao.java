/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucedetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRecipeResouceDetailDaoクラス
 * @author kanri-user
 */
public class MockRecipeResouceDetailDao implements RecipeResouceDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockRecipeResouceDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public RecipeResouceDetail getEntity(final Object resoucecd) {
		RecipeResouceDetail entity = new RecipeResouceDetail();
		// TODO 実装
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	public RecipeResouceDetail getEntity(final String resouceCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(resouceCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(resouceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		RecipeResouceDetail bean = new RecipeResouceDetail();

		/* RecipeResouceDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * RecipeResouceDetailを生成する
	 * @param bean RecipeResouceDetail
	 * @return RecipeResouceDetail
	 */
	private void createBean(final RecipeResouceDetail bean) {
		bean.setResouceCd("RESOURCE001");
	}
}
