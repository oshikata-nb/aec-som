/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.materialrinput;

/**
 * 外注原材料投入実績入力－基本処方ヘッダ情報AutoCompleteデータ格納クラス
 * @author tosco
 */
public class MaterialRinputRecipeHeaderForAutoComplete extends MaterialRinputRecipeHeaderForAutoCompleteBase {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション recipeCdVersion */
	public static final String recipeCdVersion_COLUMN = "RECIPE_CD_VERSION";

	/** レシピコードバージョン **/
	private String recipeCdVersion;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputRecipeHeaderForAutoComplete() {
		super();
	}

	/**
	 * レシピコードバージョン取得.
	 * @return レシピコードバージョン
	 */
	public String getRecipeCdVersion() {
		return this.recipeCdVersion;
	}

	/**
	 * レシピコードバージョン設定.
	 * @param recipeCdVersion レシピコードバージョン
	 */
	public void setRecipeCdVersion(final String recipeCdVersion) {
		this.recipeCdVersion = recipeCdVersion;
	}

}
