/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeasprova.pkgdirection;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.recipeasprova.pkgdirection.PkgDirectionRecipeAsprovaForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－処方ASPROVAのAuto Complete用ロジック
 * @author tosco
 */
public interface PkgDirectionRecipeAsprovaForAutoCompleteLogic {
	/**
	 * レシピインデックスでASPROVAの設備を取得
	 * @param recipeId レシピインデックス
	 * @param resourceCd 設備コード
	 * @return List<DirectionRecipeAsprovaForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<PkgDirectionRecipeAsprovaForAutoComplete>
		getSearchList(String recipeId, String resourceCd) throws NoDataException;
}
