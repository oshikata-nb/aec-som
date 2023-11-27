/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesouce;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.reciperesoucelist.RecipeResouceList;
import com.asahikaseieng.dao.nonentity.master.reciperesoucelist.RecipeResouceListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport.RecipeResouceListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport.RecipeResouceListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 設備一覧ロジック interface.
 * @author t0011036
 */
public interface RecipeResouceListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<RecipeResouceList>
	 */
	List<RecipeResouceList> getList(
			final RecipeResouceListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<RecipeResouceListForReport>
	 */
	List<RecipeResouceListForReport> getListForReport(
			final RecipeResouceListConditionForReport condition);
}
