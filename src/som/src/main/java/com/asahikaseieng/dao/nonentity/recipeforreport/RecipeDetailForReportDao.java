/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.recipeforreport;

import java.util.List;

/**
 * RecipeDetailForReportDaoクラス
 * @author kanri-user
 */
public interface RecipeDetailForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.recipeforreport.RecipeDetailForReport.class;

	/** ARGSアノテーション getDetailList */
	String getDetailList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<RecipeDetailForReport>
	 */
	List<RecipeDetailForReport> getDetailList(
			RecipeReportConditionForReport condition);
}
