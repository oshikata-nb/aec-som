/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesouce;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.RecipeResouceForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 設備のAuto Complete用ロジック
 * @author t0011036
 */
public interface RecipeResouceForAutoCompleteLogic {
	/**
	 * 検索画面用設備のオートコンプリート用データの取得
	 * @param recipeResouceCd 設備コードまたは設備名称
	 * @return List<RecipeResouceForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<RecipeResouceForAutoComplete> getSearchList(String recipeResouceCd)
			throws NoDataException;
}
