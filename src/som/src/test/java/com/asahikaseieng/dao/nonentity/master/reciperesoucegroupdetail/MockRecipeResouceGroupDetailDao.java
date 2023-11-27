/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucegroupdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRecipeResouceGroupDetailDaoクラス
 * @author t0011036
 */
public class MockRecipeResouceGroupDetailDao implements
		RecipeResouceGroupDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockRecipeResouceGroupDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public RecipeResouceGroupDetail getEntity(final String recipeResouceCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(recipeResouceCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(recipeResouceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		RecipeResouceGroupDetail bean = new RecipeResouceGroupDetail();

		/* RecipeResouceGroupDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * RecipeResouceGroupDetailを生成する
	 * @param bean bean
	 * @return RecipeResouceGroupDetail
	 */
	private void createBean(final RecipeResouceGroupDetail bean) {
		bean.setResouceGroupCd("RESOUCE_GROUP_CD01");
		bean.setResouceGroupName("NAME01");
	}
}
