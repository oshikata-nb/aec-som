/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport;

import java.util.List;

/**
 * RecipeResouceGroupListForReportDaoクラス
 * @author t0011036
 */
public interface RecipeResouceGroupListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport.RecipeResouceGroupListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * RecipeResouceGroupListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<RecipeResouceGroupListForReport>
	 */
	List<RecipeResouceGroupListForReport> getListForReport(
			final RecipeResouceGroupListConditionForReport condition);
}
