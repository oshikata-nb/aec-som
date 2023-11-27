/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeasprova.direction;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.recipeasprova.direction.DirectionRecipeAsprovaForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * レシピASPROVAのAuto Complete用ロジック
 * @author tosco
 */
public interface DirectionRecipeAsprovaForAutoCompleteLogic {
	/**
	 * レシピインデックスでASPROVAの設備を取得
	 * @param recipeId レシピインデックス
	 * @param resourceCd 設備コード
	 * @return List<DirectionRecipeAsprovaForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<DirectionRecipeAsprovaForAutoComplete>
		getSearchList(String recipeId, String resourceCd) throws NoDataException;
}
