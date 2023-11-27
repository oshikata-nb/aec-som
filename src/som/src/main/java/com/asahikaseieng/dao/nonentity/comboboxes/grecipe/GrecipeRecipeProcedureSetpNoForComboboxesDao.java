/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.grecipe;

import java.math.BigDecimal;
import java.util.List;

/**
 * 原処方－工程順序コンボボックス用Daoインターフェース.
 *
 * @author tosco
 */
public interface GrecipeRecipeProcedureSetpNoForComboboxesDao {

	/** BEANアノテーション */
	Class<GrecipeRecipeProcedureSetpNoForComboboxes> BEAN = GrecipeRecipeProcedureSetpNoForComboboxes.class;

	/** ARGSアノテーション findByRecipeId. */
	String findByRecipeId_ARGS = "recipeId";
	/**
	 * レシピインデックスで検索
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedure>
	 */
	List<GrecipeRecipeProcedureSetpNoForComboboxes> findByRecipeId(BigDecimal recipeId);
}
