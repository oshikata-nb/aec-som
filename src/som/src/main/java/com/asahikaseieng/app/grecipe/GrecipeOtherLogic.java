/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeRemarkList;
import com.asahikaseieng.exception.NoDataException;



/**
 * 原処方-その他 ロジッククラス
 * @author tosco
 */
public interface GrecipeOtherLogic {

	/**
	 * 処方その他-初期表示処理
	 * @param recipeId RECIPE_ID|レシピインデックス
	 * @return GrecipeRecipeRemarkList
	 * @throws NoDataException データが存在しない例外
	 */
	GrecipeRecipeRemarkList getEntity(final String recipeId) throws NoDataException;

	/**
	 * 処方その他-登録処理
	 * @param bean その他タブ
	 * @throws Exception データが存在しない例外
	 */
	void regist(final GrecipeRecipeRemarkList bean) throws Exception;
}
