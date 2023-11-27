/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRecipeResouceListForReportDaoクラス
 * @author t0011036
 */
public class MockRecipeResouceListForReportDao implements
		RecipeResouceListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockRecipeResouceListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RecipeResouceListForReport> getListForReport(
			final RecipeResouceListConditionForReport condition) {
		List<RecipeResouceListForReport> list = new ArrayList<RecipeResouceListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhResouceCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhResouceCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RecipeResouceListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RecipeResouceListForReportを生成する
	 * @param i インデックス
	 * @return RecipeResouceListForReport
	 */
	private RecipeResouceListForReport createBean(final int i) {
		RecipeResouceListForReport bean = new RecipeResouceListForReport();
		bean.setResouceCd("RESOUCE_CD" + i);
		bean.setResouceName("NAME" + i);
		return bean;
	}
}
