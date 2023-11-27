/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.operation;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 工程マスタキューのオートコンプリート用Form
 * @author tosco
 */
public class OperationForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public OperationForAutoCompleteForm() {
	}

	/** 用途 */
	private String recipeUse;

	/**
	 * 用途を取得します。
	 * @return recipeUse
	 */
	public String getRecipeUse() {
		return recipeUse;
	}

	/**
	 * 用途を設定します。
	 * @param recipeUse 用途
	 */
	public void setRecipeUse(final String recipeUse) {
		this.recipeUse = recipeUse;
	}
}
