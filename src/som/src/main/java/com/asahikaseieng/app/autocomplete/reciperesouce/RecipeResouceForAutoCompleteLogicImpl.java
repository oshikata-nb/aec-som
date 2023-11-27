/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesouce;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.RecipeResouceForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.RecipeResouceForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 設備のAuto Complete用ロジック
 * @author t0011036
 */
public class RecipeResouceForAutoCompleteLogicImpl implements
		RecipeResouceForAutoCompleteLogic {
	/* 設備操作DAO */
	private RecipeResouceForAutoCompleteDao recipeResouceForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public RecipeResouceForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用設備のオートコンプリート用データの取得
	 * @param recipeResouceCd 設備コードまたは設備名称
	 * @return List<RecipeResouceForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<RecipeResouceForAutoComplete> getSearchList(
			final String recipeResouceCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(recipeResouceCd);
		List<RecipeResouceForAutoComplete> list = recipeResouceForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * recipeResouceForAutoCompleteDaoを設定します。
	 * @param recipeResouceForAutoCompleteDao recipeResouceForAutoCompleteDao
	 */
	public void setRecipeResouceForAutoCompleteDao(
			final RecipeResouceForAutoCompleteDao recipeResouceForAutoCompleteDao) {
		this.recipeResouceForAutoCompleteDao = recipeResouceForAutoCompleteDao;
	}
}
