/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeasprova.direction;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.recipeasprova.direction.DirectionRecipeAsprovaForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.recipeasprova.direction.DirectionRecipeAsprovaForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * レシピASPROVAのAuto Complete用ロジック
 * @author tosco
 */
public class DirectionRecipeAsprovaForAutoCompleteLogicImpl implements
		DirectionRecipeAsprovaForAutoCompleteLogic {

	/** 処方ASPROVA操作DAO */
	private DirectionRecipeAsprovaForAutoCompleteDao directionRecipeAsprovaForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public DirectionRecipeAsprovaForAutoCompleteLogicImpl() {
	}

	/**
	 * レシピインデックスでASPROVAの設備を取得
	 * @param recipeId レシピインデックス
	 * @param resourceCd 設備コード
	 * @return List<DirectionRecipeAsprovaForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<DirectionRecipeAsprovaForAutoComplete> getSearchList(
			final String recipeId, final String resourceCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(resourceCd);
		List<DirectionRecipeAsprovaForAutoComplete> list = directionRecipeAsprovaForAutoCompleteDao
				.getSearchList(recipeId, val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 処方ASPROVA操作DAOを設定します。
	 * @param directionRecipeAsprovaForAutoCompleteDao 処方ASPROVA操作DAO
	 */
	public void setDirectionRecipeAsprovaForAutoCompleteDao(
			final DirectionRecipeAsprovaForAutoCompleteDao directionRecipeAsprovaForAutoCompleteDao) {
		this.directionRecipeAsprovaForAutoCompleteDao = directionRecipeAsprovaForAutoCompleteDao;
	}

}
