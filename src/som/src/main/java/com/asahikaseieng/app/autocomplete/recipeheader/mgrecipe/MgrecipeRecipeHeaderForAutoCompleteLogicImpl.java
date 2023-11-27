/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.mgrecipe;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.mgrecipe.MgrecipeRecipeHeaderForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.mgrecipe.MgrecipeRecipeHeaderForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 基本処方－原処方ｺｰﾄﾞAutoComplete用ロジック
 * @author tosco
 */
public class MgrecipeRecipeHeaderForAutoCompleteLogicImpl implements
		MgrecipeRecipeHeaderForAutoCompleteLogic {

	/** 基本処方－原処方ｺｰﾄﾞ操作DAO */
	private MgrecipeRecipeHeaderForAutoCompleteDao mgrecipeRecipeHeaderForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public MgrecipeRecipeHeaderForAutoCompleteLogicImpl() {
	}

	/**
	 * 基本処方－原処方ｺｰﾄﾞのオートコンプリート用データの取得
	 * 
	 * @param recipeCd レシピコード
	 * @return List<MgrecipeRecipeHeaderForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 * 
	 */
	public List<MgrecipeRecipeHeaderForAutoComplete> getSearchList(
			final String recipeCd) throws NoDataException {

		String val = AecTextUtils.likeFilter(recipeCd);
		List<MgrecipeRecipeHeaderForAutoComplete> list = mgrecipeRecipeHeaderForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 基本処方－原処方ｺｰﾄﾞ検索DAOを設定します。
	 * 
	 * @param mgrecipeRecipeHeaderForAutoCompleteDao 基本処方－原処方ｺｰﾄﾞ検索DAO
	 */
	public void setMgrecipeRecipeHeaderForAutoCompleteDao(
			final MgrecipeRecipeHeaderForAutoCompleteDao mgrecipeRecipeHeaderForAutoCompleteDao) {
		this.mgrecipeRecipeHeaderForAutoCompleteDao = mgrecipeRecipeHeaderForAutoCompleteDao;
	}

}
