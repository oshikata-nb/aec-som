/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;


import java.math.BigDecimal;

import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeRemarkList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本処方-その他 ロジッククラス
 * @author tosco
 */
public interface MgrecipeOtherLogic {

	/**
	 * 処方その他-初期表示処理
	 * @param recipeId RECIPE_ID|レシピインデックス
	 * @return RecipeRemarkList
	 * @throws NoDataException データが存在しない例外
	 */
	RecipeRemarkList getByRecipeId(final BigDecimal recipeId) throws NoDataException;

	/**
	 * 処方その他-登録処理
	 * @param bean その他タブ
	 * @throws Exception データが存在しない例外
	 */
	void regist(final RecipeRemarkList bean) throws Exception;
}
