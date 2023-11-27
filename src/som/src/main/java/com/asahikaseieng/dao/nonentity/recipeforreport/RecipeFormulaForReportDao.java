/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.recipeforreport;

import java.util.List;

/**
 * RecipeFormulaForReportDaoクラス
 * @author kanri-user
 */
public interface RecipeFormulaForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.recipeforreport.RecipeFormulaForReport.class;

	/** ARGSアノテーション getFormulaList */
	String getFormulaList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<RecipeHeaderForReport>
	 */
	List<RecipeFormulaForReport> getFormulaList(
			RecipeReportConditionForReport condition);
}
