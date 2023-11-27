/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.aspimport;

import java.util.List;

/**
 * 処方検査Daoインターフェース.
 *
 * @author tosco
 */
public interface AspImportRecipeInspectionListDao {

	/** BEANアノテーション */
	Class<AspImportRecipeInspectionList> BEAN = AspImportRecipeInspectionList.class;

	//
	// インスタンスメソッド
	//


	/** ARGSアノテーション getRecipeId(). */
	String getRecipeId_ARGS = "recipeId";
	/**
	 * レシピインデックス、で検索
	 * @param recipeId レシピインデックス
	 * @return List<AspImportRecipeInspectionList>
	 */
	List<AspImportRecipeInspectionList> getRecipeId(String recipeId);

}
