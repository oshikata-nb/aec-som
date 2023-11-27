/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesoucegroup;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.reciperesoucegroup.RecipeResouceGroupForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 設備グループのAuto Complete用ロジック
 * @author t0011036
 */
public interface RecipeResouceGroupForAutoCompleteLogic {
	/**
	 * 検索画面用設備グループのオートコンプリート用データの取得
	 * @param recipeResouceGroupCd 設備グループコードまたは設備グループ名称
	 * @return List<RecipeResouceGroupForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<RecipeResouceGroupForAutoComplete> getSearchList(
			String recipeResouceGroupCd) throws NoDataException;
}
