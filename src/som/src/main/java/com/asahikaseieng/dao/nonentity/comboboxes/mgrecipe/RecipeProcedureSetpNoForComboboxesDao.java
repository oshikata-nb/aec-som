/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe;

import java.math.BigDecimal;
import java.util.List;

/**
 * 基本処方－工程順序コンボボックス用Daoインターフェース.
 *
 * @author tosco
 */
public interface RecipeProcedureSetpNoForComboboxesDao {

	/** BEANアノテーション */
	Class<RecipeProcedureSetpNoForComboboxes> BEAN = RecipeProcedureSetpNoForComboboxes.class;

	/** ARGSアノテーション findByRecipeId. */
	String findByRecipeId_ARGS = "recipeId";
	/**
	 * レシピインデックスで検索
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedure>
	 */
	List<RecipeProcedureSetpNoForComboboxes> findByRecipeId(BigDecimal recipeId);
}
