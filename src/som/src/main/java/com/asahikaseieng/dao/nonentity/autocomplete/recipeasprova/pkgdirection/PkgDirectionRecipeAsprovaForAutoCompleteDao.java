/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.recipeasprova.pkgdirection;

import java.util.List;

/**
 * 包装指図－処方ASPROVA_Daoインターフェース.
 * 
 * @author tosco
 */
public interface PkgDirectionRecipeAsprovaForAutoCompleteDao {

	/** BEANアノテーション */
	Class<PkgDirectionRecipeAsprovaForAutoComplete> BEAN = PkgDirectionRecipeAsprovaForAutoComplete.class;

	// method---------------------------------------------------------------
	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "recipeId,resourceCd,rowlimit";

	/**
	 * レシピIDに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @param resourceCd 設備コード
	 * @param rowlimit 行上限
	 * @return List<DirectionRecipeAsprovaForAutoComplete>
	 */
	List<PkgDirectionRecipeAsprovaForAutoComplete> getSearchList(
			String recipeId, String resourceCd, final String rowlimit);

}
