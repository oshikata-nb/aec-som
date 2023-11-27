/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.recipeforreport;

import java.util.List;

/**
 * RecipeAsprovaForReportDaoクラス
 * @author kanri-user
 */
public interface RecipeAsprovaForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.recipeforreport.RecipeAsprovaForReport.class;

	/** ARGSアノテーション getAsprovaList */
	String getAsprovaList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<RecipeHeaderForReport>
	 */
	List<RecipeAsprovaForReport> getAsprovaList(
			RecipeReportConditionForReport condition);
}
