/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.recipeheader;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.master.search.recipeheader.RecipeHeaderSearchList;
import com.asahikaseieng.dao.nonentity.master.search.recipeheader.RecipeHeaderSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本処方検索(ポップアップ)ロジック interface.
 * @author tosco
 */
public interface RecipeHeaderSearchLogic {

	/**
	 * 基本処方の検索を行う。
	 * @param condition 検索条件
	 * @return List<RecipeHeaderSearchList>
	 * @throws NoDataException データが取得できなかった場合
	 */
	List<RecipeHeaderSearchList> getList(
			RecipeHeaderSearchListPagerCondition condition)
			throws NoDataException;

	/**
	 * 生産ラインコンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox();

}
