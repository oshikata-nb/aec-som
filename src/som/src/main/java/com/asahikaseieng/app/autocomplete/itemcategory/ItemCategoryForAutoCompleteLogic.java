/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.itemcategory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.itemcategory.ItemCategoryForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目分類のAuto Complete用ロジック
 * @author t0011036
 */
public interface ItemCategoryForAutoCompleteLogic {
	/**
	 * 検索画面用品目分類のオートコンプリート用データの取得
	 * @param itemCategoryCd 品目分類コードまたは品目分類名称
	 * @return List<ItemCategoryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemCategoryForAutoComplete> getSearchList(String itemCategoryCd)
			throws NoDataException;
}
