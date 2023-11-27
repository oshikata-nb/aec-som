/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.pkgdirection;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.pkgdirection.PkgDirectionRecipeHeaderForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－基本処方ヘッダ情報AutoComplete用ロジック
 * @author tosco
 */
public interface PkgDirectionRecipeHeaderForAutoCompleteLogic {

	/**
	 * 包装指図－基本処方ヘッダ情報のオートコンプリート用データの取得

	 * @param recipeCdVersion レシピコード+レシピバージョンまたは基本処方名称
	 * @param itemCd 品目コード
	 * @param line 生産ライン
	 * @param otherCompanyCd1 他社コード１
	 * @return List<PkgDirectionRecipeHeaderForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合

	 */
	List<PkgDirectionRecipeHeaderForAutoComplete> getSearchList(
		final String recipeCdVersion, final String itemCd, final String line,
		final String otherCompanyCd1) throws NoDataException;
}
