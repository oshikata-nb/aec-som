/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.recipeforreport;

import java.util.List;

/**
 * RecipeRemarkForReportDaoクラス
 * @author kanri-user
 */
public interface RecipeRemarkForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.recipeforreport.RecipeRemarkForReport.class;

	/** ARGSアノテーション getRemarkList */
	String getRemarkList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<RecipeProcedureForReport>
	 */
	List<RecipeRemarkForReport> getRemarkList(
			final RecipeReportConditionForReport condition);
}
