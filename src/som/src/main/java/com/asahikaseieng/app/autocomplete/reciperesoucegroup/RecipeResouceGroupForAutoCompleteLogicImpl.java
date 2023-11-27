/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesoucegroup;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.reciperesoucegroup.RecipeResouceGroupForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.reciperesoucegroup.RecipeResouceGroupForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 設備グループのAuto Complete用ロジック
 * @author t0011036
 */
public class RecipeResouceGroupForAutoCompleteLogicImpl implements
		RecipeResouceGroupForAutoCompleteLogic {
	/* 設備グループ操作DAO */
	private RecipeResouceGroupForAutoCompleteDao recipeResouceGroupForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public RecipeResouceGroupForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用設備グループのオートコンプリート用データの取得
	 * @param recipeResouceGroupCd 設備グループコードまたは設備グループ名称
	 * @return List<RecipeResouceGroupForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<RecipeResouceGroupForAutoComplete> getSearchList(
			final String recipeResouceGroupCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(recipeResouceGroupCd);
		List<RecipeResouceGroupForAutoComplete> list = recipeResouceGroupForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * recipeResouceGroupForAutoCompleteDaoを設定します。
	 * @param recipeResouceGroupForAutoCompleteDao
	 *            recipeResouceGroupForAutoCompleteDao
	 */
	public void setRecipeResouceGroupForAutoCompleteDao(
			final RecipeResouceGroupForAutoCompleteDao recipeResouceGroupForAutoCompleteDao) {
		this.recipeResouceGroupForAutoCompleteDao = recipeResouceGroupForAutoCompleteDao;
	}
}
