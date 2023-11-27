/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRecipeResouceGroupListDaoクラス
 * @author t0011036
 */
public class MockRecipeResouceGroupListDao implements RecipeResouceGroupListDao {

	/**
	 * コンストラクタ.
	 */
	public MockRecipeResouceGroupListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RecipeResouceGroupList> getList(
			final RecipeResouceGroupListPagerCondition condition) {
		List<RecipeResouceGroupList> list = new ArrayList<RecipeResouceGroupList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhResouceGroupCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhResouceGroupCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RecipeResouceGroupListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RecipeResouceGroupListを生成する
	 * @param i インデックス
	 * @return RecipeResouceGroupList
	 */
	private RecipeResouceGroupList createBean(final int i) {
		RecipeResouceGroupList bean = new RecipeResouceGroupList();
		bean.setResouceGroupCd("RESOURCE" + i);
		return bean;
	}
}
