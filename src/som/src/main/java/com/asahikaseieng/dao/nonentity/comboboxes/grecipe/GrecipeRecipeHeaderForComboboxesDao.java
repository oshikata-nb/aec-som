/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.grecipe;

import java.util.List;

/**
 * 処方ヘッダーメンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface GrecipeRecipeHeaderForComboboxesDao {

	/** BEANアノテーション */
	Class<GrecipeRecipeHeaderForComboboxes> BEAN = GrecipeRecipeHeaderForComboboxes.class;

	/** ARGSアノテーション getOperationPatternList */
	String getOperationPatternList_ARGS = "use";
	/**
	 * ヘッダー情報－工程パターン一覧取得
	 * @param use 用途
	 * @return List<RecipeHeader> 検索結果リスト
	 */
	List<GrecipeRecipeHeaderForComboboxes> getOperationPatternList(final String use);

}
