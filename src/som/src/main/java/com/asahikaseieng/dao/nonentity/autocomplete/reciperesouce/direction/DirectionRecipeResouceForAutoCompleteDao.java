/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.direction;

import java.util.List;

/**
 * 設備用Daoインターフェース.
 * 
 * @author tosco
 */
public interface DirectionRecipeResouceForAutoCompleteDao {

	/** BEANアノテーション */
	Class<DirectionRecipeResouceForAutoComplete> BEAN = DirectionRecipeResouceForAutoComplete.class;

	// method-----------------------------------------------------------------

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "productionLine,dissolutionTankNo,rowlimit";

	/**
	 * エンティティ取得.
	 * @param productionLine 生産ライン
	 * @param dissolutionTankNo 予備溶解タンクNO
	 * @param rowlimit 行上限
	 * @return List<DirectionRecipeResouceForAutoComplete>
	 */
	List<DirectionRecipeResouceForAutoComplete> getSearchList(
			String productionLine, String dissolutionTankNo,
			final String rowlimit);

}
