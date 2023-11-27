/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.mgrecipe;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 基本処方－原処方ｺｰﾄﾞAutoCompleteFormクラス
 * @author tosco
 */
public class MgrecipeRecipeHeaderForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public MgrecipeRecipeHeaderForAutoCompleteForm() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 */
	public MgrecipeRecipeHeaderForAutoCompleteForm(final String code) {
		super(code);
	}

}
