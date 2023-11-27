/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.recipeheader;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.mgrecipe.MgrecipeConst;
import com.asahikaseieng.dao.nonentity.comboboxes.master.search.recipeheader.SearchRecipeHeaderLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.search.recipeheader.SearchRecipeHeaderLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.search.recipeheader.RecipeHeaderSearchList;
import com.asahikaseieng.dao.nonentity.master.search.recipeheader.RecipeHeaderSearchListDao;
import com.asahikaseieng.dao.nonentity.master.search.recipeheader.RecipeHeaderSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本処方検索(ポップアップ)ロジック 実装クラス.
 * @author tosco
 */
public class RecipeHeaderSearchLogicImpl implements RecipeHeaderSearchLogic {

	/** 生産ラインコンボボックス用DAo */
	private SearchRecipeHeaderLineForComboboxesDao searchRecipeHeaderLineForComboboxesDao;

	/** 基本処方検索(ポップアップ)Dao */
	private RecipeHeaderSearchListDao recipeHeaderSearchListDao;

	/**
	 * コンストラクタ.
	 */
	public RecipeHeaderSearchLogicImpl() {
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<MgrecipeLine>
	 */
	public List<SearchRecipeHeaderLineForComboboxes> getAllLines() {
		return searchRecipeHeaderLineForComboboxesDao.findAll();
	}

	/**
	 * 生産ラインコンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createLineCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 生産ライン検索
		List<SearchRecipeHeaderLineForComboboxes> lineList = getAllLines();
		for (SearchRecipeHeaderLineForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getProductionLine());
			item.setLabales(bean.getProductionLineName());
			res.add(item);
		}

		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues(MgrecipeConst.COMBO_ALL_VALUE);
		allItem.setLabales(MgrecipeConst.COMBO_ALL_LABEL);
		res.add(0, allItem);

		return res;
	}

	/**
	 * 基本処方の検索を行う。
	 * @param condition 検索条件
	 * @return List<RecipeHeaderSearchList>
	 * @throws NoDataException データが取得できなかった場合
	 */
	public List<RecipeHeaderSearchList> getList(
			final RecipeHeaderSearchListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<RecipeHeaderSearchList> list = recipeHeaderSearchListDao
				.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(
			final RecipeHeaderSearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 生産ラインコンボボックス用DAoを設定します。
	 * @param searchRecipeHeaderLineForComboboxesDao 生産ラインコンボボックス用DAo
	 */
	public void setSearchRecipeHeaderLineForComboboxesDao(
			final SearchRecipeHeaderLineForComboboxesDao searchRecipeHeaderLineForComboboxesDao) {
		this.searchRecipeHeaderLineForComboboxesDao = searchRecipeHeaderLineForComboboxesDao;
	}

	/**
	 * 基本処方検索(ポップアップ)Daoを設定します。
	 * @param recipeHeaderSearchListDao 基本処方検索(ポップアップ)Dao
	 */
	public void setRecipeHeaderSearchListDao(
			final RecipeHeaderSearchListDao recipeHeaderSearchListDao) {
		this.recipeHeaderSearchListDao = recipeHeaderSearchListDao;
	}

}
