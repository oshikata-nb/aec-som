/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.mgrecipe;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.mgrecipe.MgrecipeRecipeHeaderForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本処方－原処方ｺｰﾄﾞAutoComplete用ロジック
 * @author tosco
 */
public interface MgrecipeRecipeHeaderForAutoCompleteLogic {

	/**
	 * 包装指図－基本処方ヘッダ情報のオートコンプリート用データの取得

	 * @param recipeCd レシピコード
	 * @return List<MgrecipeRecipeHeaderForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合

	 */
	List<MgrecipeRecipeHeaderForAutoComplete> getSearchList(
		final String recipeCd) throws NoDataException;
}
