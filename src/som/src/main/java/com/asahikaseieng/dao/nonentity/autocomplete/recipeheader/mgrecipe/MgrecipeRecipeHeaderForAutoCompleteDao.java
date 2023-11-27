/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.mgrecipe;

import java.util.List;

/**
 * 包装指図－基本処方ヘッダ情報AutoCompleteDaoクラス
 * @author tosco
 */
public interface MgrecipeRecipeHeaderForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = MgrecipeRecipeHeaderForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "recipeCd,rowlimit";

	/**
	 * 検索画面用レシピコード一覧取得用
	 * @param recipeCd レシピコード
	 * @param rowlimit 行上限
	 * @return List<MgrecipeRecipeHeaderForAutoComplete>
	 */
	List<MgrecipeRecipeHeaderForAutoComplete> getSearchList(String recipeCd,
			final String rowlimit);
}
