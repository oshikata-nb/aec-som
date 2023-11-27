/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.recipeforreport;

import java.util.List;

/**
 * RecipeInspectionForReportDaoクラス
 * @author kanri-user
 */
public interface RecipeInspectionForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.recipeforreport.RecipeInspectionForReport.class;

	/** ARGSアノテーション getInspectionList */
	String getInspectionList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<RecipeInspectionForReport>
	 */
	List<RecipeInspectionForReport> getInspectionList(
		RecipeReportConditionForReport condition);
}
