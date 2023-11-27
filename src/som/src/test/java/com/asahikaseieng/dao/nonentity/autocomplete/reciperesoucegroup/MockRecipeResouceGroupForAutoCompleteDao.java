/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.reciperesoucegroup;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRecipeResouceGroupListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockRecipeResouceGroupForAutoCompleteDao implements
		RecipeResouceGroupForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockRecipeResouceGroupForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RecipeResouceGroupForAutoComplete> getSearchList(
			final String resouceGroupCd, final String rowlimit) {
		List<RecipeResouceGroupForAutoComplete> list = new ArrayList<RecipeResouceGroupForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(resouceGroupCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(resouceGroupCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RecipeResouceGroupListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RecipeResouceGroupListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return RecipeResouceGroupListForAutoComplete
	 */
	private RecipeResouceGroupForAutoComplete createBean(final int i) {
		RecipeResouceGroupForAutoComplete bean = new RecipeResouceGroupForAutoComplete();
		bean.setResouceGroupCd("RESOURCE_GROUP" + i);
		bean.setResouceGroupName("NAME" + i);
		return bean;
	}
}
