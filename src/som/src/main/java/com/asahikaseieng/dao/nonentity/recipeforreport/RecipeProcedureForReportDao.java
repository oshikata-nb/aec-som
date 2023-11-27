/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.recipeforreport;

import java.util.List;

/**
 * RecipeProcedureForReportDaoクラス
 * @author kanri-user
 */
public interface RecipeProcedureForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.recipeforreport.RecipeProcedureForReport.class;

	/** ARGSアノテーション getProcedureList */
	String getProcedureList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<RecipeProcedureForReport>
	 */
	List<RecipeProcedureForReport> getProcedureList(
			final RecipeReportConditionForReport condition);
}
