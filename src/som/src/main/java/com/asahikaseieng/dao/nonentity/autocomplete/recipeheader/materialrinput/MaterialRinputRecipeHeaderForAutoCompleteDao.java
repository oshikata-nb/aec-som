/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.materialrinput;

import java.util.List;

/**
 * 外注原材料投入実績入力－基本処方ヘッダ情報AutoCompleteDaoクラス
 * @author tosco
 */
public interface MaterialRinputRecipeHeaderForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = MaterialRinputRecipeHeaderForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "recipeCdVersion,itemCd,rowlimit";

	/**
	 * 投入実績入力画面用基本処方コード取得用
	 * @param recipeCdVersion レシピコードバージョン
	 * @param itemCd 品目コード
	 * @param rowlimit 行上限
	 * @return List<MaterialRinputRecipeHeaderForAutoComplete> 検索結果リスト
	 */
	List<MaterialRinputRecipeHeaderForAutoComplete> getSearchList(
			final String recipeCdVersion, final String itemCd,
			final String rowlimit);
}
