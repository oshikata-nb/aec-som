/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.recipeforreport;

import java.util.List;

/**
 * RecipeHeaderForReportDaoクラス
 * @author kanri-user
 */
public interface RecipeHeaderForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.recipeforreport.RecipeHeaderForReport.class;

	/** ARGSアノテーション getHeadList */
	String getHeadList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<RecipeHeaderForReport>
	 */
	List<RecipeHeaderForReport> getHeadList(
			RecipeReportConditionForReport condition);
}
