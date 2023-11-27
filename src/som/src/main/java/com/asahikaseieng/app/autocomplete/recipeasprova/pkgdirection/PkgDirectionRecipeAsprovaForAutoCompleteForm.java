/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeasprova.pkgdirection;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 包装指図－処方ASPROVAオートコンプリート用Form
 * @author tosco
 */
public class PkgDirectionRecipeAsprovaForAutoCompleteForm extends
		GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** レシピインデックス */
	private String recipeId;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionRecipeAsprovaForAutoCompleteForm() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 */
	public PkgDirectionRecipeAsprovaForAutoCompleteForm(final String code) {
		super(code);
	}
	//setter,getter-------------------------------------------------------
	/**
	 * レシピインデックスを取得します。
	 * @return レシピインデックス
	 */
	public String getRecipeId() {
		return recipeId;
	}

	/**
	 * レシピインデックスを設定します。
	 * @param recipeId レシピインデックス
	 */
	public void setRecipeId(final String recipeId) {
		this.recipeId = recipeId;
	}

}
