/*
 * Created on 2007/11/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucelist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRecipeResouceListDaoクラス
 * @author t0011036
 */
public class MockRecipeResouceListDao implements RecipeResouceListDao {

	/**
	 * コンストラクタ.
	 */
	public MockRecipeResouceListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RecipeResouceList> getList(
			final RecipeResouceListPagerCondition condition) {
		List<RecipeResouceList> list = new ArrayList<RecipeResouceList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhResouceCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhResouceCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RecipeResouceListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RecipeResouceListを生成する
	 * @param i インデックス
	 * @return RecipeResouceList
	 */
	private RecipeResouceList createBean(final int i) {
		RecipeResouceList bean = new RecipeResouceList();
		bean.setResouceCd("RESOURCE" + i);
		return bean;
	}
}
