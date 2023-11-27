/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.pkgdirection;

import java.util.List;

/**
 * 包装指図－基本処方ヘッダ情報AutoCompleteDaoクラス
 * @author tosco
 */
public interface PkgDirectionRecipeHeaderForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = PkgDirectionRecipeHeaderForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "recipeCdVersion,itemCd,line,otherCompanyCd1,rowlimit";

	/**
	 * 検索画面用納入先一覧取得用
	 * @param recipeCdVersion レシピコードバージョン
	 * @param itemCd 品目コード
	 * @param line 生産ライン
	 * @param otherCompanyCd1 他社コード１
	 * @param rowlimit 行上限
	 * @return List<PkgDirectionRecipeHeaderForAutoComplete>
	 */
	List<PkgDirectionRecipeHeaderForAutoComplete> getSearchList(
			final String recipeCdVersion, final String itemCd,
			final String line, final String otherCompanyCd1,
			final String rowlimit);
}
