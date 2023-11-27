/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport;

import java.util.List;

/**
 * RecipeResouceListForReportDaoクラス
 * @author t0011036
 */
public interface RecipeResouceListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport.RecipeResouceListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * RecipeResouceListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return RecipeResouceListForReport
	 */
	List<RecipeResouceListForReport> getListForReport(
			final RecipeResouceListConditionForReport condition);
}
