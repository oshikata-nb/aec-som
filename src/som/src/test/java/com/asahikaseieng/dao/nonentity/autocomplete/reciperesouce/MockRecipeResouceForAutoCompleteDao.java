/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRecipeResouceListForAutoCompleteDaoクラス
 * @author kanri-user
 */
public class MockRecipeResouceForAutoCompleteDao implements
		RecipeResouceForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockRecipeResouceForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RecipeResouceForAutoComplete> getSearchList(
			final String resouceCd, final String rowlimit) {
		List<RecipeResouceForAutoComplete> list = new ArrayList<RecipeResouceForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(resouceCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(resouceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RecipeResouceListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RecipeResouceListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return RecipeResouceListForAutoComplete
	 */
	private RecipeResouceForAutoComplete createBean(final int i) {
		RecipeResouceForAutoComplete bean = new RecipeResouceForAutoComplete();
		bean.setResouceCd("RESOURCE" + i);
		return bean;
	}
}
