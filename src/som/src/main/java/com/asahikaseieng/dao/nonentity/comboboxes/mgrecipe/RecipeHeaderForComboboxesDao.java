/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe;

import java.util.List;

/**
 * 処方ヘッダーメンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface RecipeHeaderForComboboxesDao {

	/** BEANアノテーション */
	Class<RecipeHeaderForComboboxes> BEAN = RecipeHeaderForComboboxes.class;

	/** ARGSアノテーション getOperationPatternList */
	String getOperationPatternList_ARGS = "use";
	/**
	 * ヘッダー情報－工程パターン一覧取得
	 * @param use 用途
	 * @return List<RecipeHeader> 検索結果リスト
	 */
	List<RecipeHeaderForComboboxes> getOperationPatternList(final String use);

}
