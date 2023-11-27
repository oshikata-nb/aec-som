/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.itemcategory;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.itemcategory.ItemCategoryForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.itemcategory.ItemCategoryForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 品目分類のAuto Complete用ロジック
 * @author t0011036
 */
public class ItemCategoryForAutoCompleteLogicImpl implements
		ItemCategoryForAutoCompleteLogic {
	/* 品目分類操作DAO */
	private ItemCategoryForAutoCompleteDao itemCategoryForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public ItemCategoryForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用品目分類のオートコンプリート用データの取得
	 * @param itemCategoryCd 品目分類コードまたは品目分類名称
	 * @return List<ItemCategoryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemCategoryForAutoComplete> getSearchList(
			final String itemCategoryCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCategoryCd);
		List<ItemCategoryForAutoComplete> list = itemCategoryForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * itemCategoryForAutoCompleteDaoを設定します。
	 * @param itemCategoryForAutoCompleteDao itemCategoryForAutoCompleteDao
	 */
	public void setItemCategoryForAutoCompleteDao(
			final ItemCategoryForAutoCompleteDao itemCategoryForAutoCompleteDao) {
		this.itemCategoryForAutoCompleteDao = itemCategoryForAutoCompleteDao;
	}
}
