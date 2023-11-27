/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.materialrinput;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.materialrinput.MaterialRinputRecipeHeaderForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.materialrinput.MaterialRinputRecipeHeaderForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 外注原材料投入実績入力－基本処方ヘッダ情報AutoComplete用ロジック
 * @author tosco
 */
public class MaterialRinputRecipeHeaderForAutoCompleteLogicImpl implements
		MaterialRinputRecipeHeaderForAutoCompleteLogic {

	/** 外注原材料投入実績入力－基本処方ヘッダ情報操作DAO */
	private MaterialRinputRecipeHeaderForAutoCompleteDao materialRinputRecipeHeaderForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public MaterialRinputRecipeHeaderForAutoCompleteLogicImpl() {
	}

	/**
	 * 外注原材料投入実績入力－基本処方ヘッダ情報のオートコンプリート用データの取得
	 * 
	 * @param recipeCdVersion レシピコード+レシピバージョンまたは基本処方名称
	 * @param itemCd 品目コード
	 * @return List<MaterialRinputRecipeHeaderForAutoComplete> 検索結果リスト
	 * @throws NoDataException 検索結果が存在しない場合
	 * 
	 */
	public List<MaterialRinputRecipeHeaderForAutoComplete> getSearchList(
			final String recipeCdVersion, final String itemCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(recipeCdVersion);
		List<MaterialRinputRecipeHeaderForAutoComplete> list = materialRinputRecipeHeaderForAutoCompleteDao
				.getSearchList(val, itemCd, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 外注原材料投入実績入力－基本処方ヘッダ情報操作DAOを設定します。
	 * @param materialRinputRecipeHeaderForAutoCompleteDao
	 *            外注原材料投入実績入力－基本処方ヘッダ情報操作DAO
	 */
	public void setMaterialRinputRecipeHeaderForAutoCompleteDao(
			final MaterialRinputRecipeHeaderForAutoCompleteDao materialRinputRecipeHeaderForAutoCompleteDao) {
		this.materialRinputRecipeHeaderForAutoCompleteDao = materialRinputRecipeHeaderForAutoCompleteDao;
	}

}
