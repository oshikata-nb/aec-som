/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeasprova.pkgdirection;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.recipeasprova.pkgdirection.PkgDirectionRecipeAsprovaForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.recipeasprova.pkgdirection.PkgDirectionRecipeAsprovaForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 包装指図－処方ASPROVAのAuto Complete用ロジック
 * @author tosco
 */
public class PkgDirectionRecipeAsprovaForAutoCompleteLogicImpl implements
		PkgDirectionRecipeAsprovaForAutoCompleteLogic {

	/** 包装指図－処方ASPROVA操作DAO */
	private PkgDirectionRecipeAsprovaForAutoCompleteDao pkgDirectionRecipeAsprovaForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionRecipeAsprovaForAutoCompleteLogicImpl() {
	}

	/**
	 * レシピインデックスでASPROVAの設備を取得
	 * @param recipeId レシピインデックス
	 * @param resourceCd 設備コード
	 * @return List<DirectionRecipeAsprovaForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<PkgDirectionRecipeAsprovaForAutoComplete> getSearchList(
			final String recipeId, final String resourceCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(resourceCd);
		List<PkgDirectionRecipeAsprovaForAutoComplete> list = pkgDirectionRecipeAsprovaForAutoCompleteDao
				.getSearchList(recipeId, val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 包装指図－処方ASPROVA操作DAOを設定します。
	 * @param pkgDirectionRecipeAsprovaForAutoCompleteDao 包装指図－処方ASPROVA操作DAO
	 */
	public void setDirectionRecipeAsprovaForAutoCompleteDao(
			final PkgDirectionRecipeAsprovaForAutoCompleteDao pkgDirectionRecipeAsprovaForAutoCompleteDao) {
		this.pkgDirectionRecipeAsprovaForAutoCompleteDao = pkgDirectionRecipeAsprovaForAutoCompleteDao;
	}

}
