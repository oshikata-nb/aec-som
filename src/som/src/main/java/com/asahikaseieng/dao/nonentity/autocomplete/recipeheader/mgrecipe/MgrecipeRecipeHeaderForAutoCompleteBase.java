/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.mgrecipe;

import java.io.Serializable;

/**
 * 基本処方－原処方ｺｰﾄﾞAutoCompleteデータ格納クラス
 * @author tosco
 */
public class MgrecipeRecipeHeaderForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeRecipeHeaderForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "RECIPE_HEADER";

	/** COLUMNアノテーション recipeCd */
	public static final String recipeCd_COLUMN = "RECIPE_CD";

	//
	// インスタンスフィールド
	//

	/** レシピコード */
	private String recipeCd;

	//
	// インスタンスメソッド
	//

	/**
	 * レシピコード取得
	 * @return String レシピコード
	*/
	public String getRecipeCd() {
		return this.recipeCd;
	}

	/**
	 * レシピコード設定
	 * @param recipeCd レシピコード
	*/
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

}
