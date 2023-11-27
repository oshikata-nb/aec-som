/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.materialrinput;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.materialrinput
									.MaterialRinputRecipeHeaderForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 外注原材料投入実績入力－基本処方ヘッダ情報AutoComplete用ロジック
 * @author tosco
 */
public interface MaterialRinputRecipeHeaderForAutoCompleteLogic {

	/**
	 * 外注原材料投入実績入力－基本処方ヘッダ情報のオートコンプリート用データの取得
	 * @param recipeCdVersion レシピコード+レシピバージョンまたは基本処方名称
	 * @param itemCd 品目コード
	 * @return List<MaterialRinputRecipeHeaderForAutoComplete> 検索結果リスト
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<MaterialRinputRecipeHeaderForAutoComplete> getSearchList(
		final String recipeCdVersion, final String itemCd) throws NoDataException;
}
