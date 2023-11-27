/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.mgrecipe;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 基本処方－原処方ｺｰﾄﾞAutoComplete結果表示用Bean
 * @author tosco
 */
public class MgrecipeRecipeHeaderForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** レシピコード */
	private String recipeCd;

	/**
	 * コンストラクタ
	 */
	public MgrecipeRecipeHeaderForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public MgrecipeRecipeHeaderForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}

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
