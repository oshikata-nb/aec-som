/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRecipeResouceGroupListForReportDaoクラス
 * @author t0011036
 */
public class MockRecipeResouceGroupListForReportDao implements
		RecipeResouceGroupListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockRecipeResouceGroupListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RecipeResouceGroupListForReport> getListForReport(
			final RecipeResouceGroupListConditionForReport condition) {
		List<RecipeResouceGroupListForReport> list = new ArrayList<RecipeResouceGroupListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhResouceGroupCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhResouceGroupCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RecipeResouceGroupListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RecipeResouceGroupListForReportを生成する
	 * @param i インデックス
	 * @return RecipeResouceGroupListForReport
	 */
	private RecipeResouceGroupListForReport createBean(final int i) {
		RecipeResouceGroupListForReport bean = new RecipeResouceGroupListForReport();
		bean.setResouceGroupCd("RESOURCE" + i);
		return bean;
	}
}
