/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.aspimport;

import java.util.List;

/**
 * 処方プロシージャDaoインターフェース.
 *
 * @author tosco
 */
public interface AspImportRecipeProcedureListDao {

	/** BEANアノテーション */
	Class<AspImportRecipeProcedureList> BEAN = AspImportRecipeProcedureList.class;

	//
	// インスタンスメソッド
	//
	/** ARGSアノテーション getRecipeId(). */
	String getRecipeId_ARGS = "recipeId";
	/**
	 * レシピインデックス、ステップNoで検索
	 * @param recipeId レシピインデックス
	 * @return List<AspImportRecipeProcedureList>
	 */
	List<AspImportRecipeProcedureList> getRecipeId(String recipeId);
}
