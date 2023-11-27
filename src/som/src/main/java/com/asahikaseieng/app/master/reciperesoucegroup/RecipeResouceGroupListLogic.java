/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesoucegroup;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist.RecipeResouceGroupList;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist.RecipeResouceGroupListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport.RecipeResouceGroupListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport.RecipeResouceGroupListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 設備グループ一覧ロジック interface.
 * @author t0011036
 */
public interface RecipeResouceGroupListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<RecipeResouceGroupList>
	 */
	List<RecipeResouceGroupList> getList(
			final RecipeResouceGroupListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<RecipeResouceGroupListForReport>
	 */
	List<RecipeResouceGroupListForReport> getListForReport(
			final RecipeResouceGroupListConditionForReport condition);
}
