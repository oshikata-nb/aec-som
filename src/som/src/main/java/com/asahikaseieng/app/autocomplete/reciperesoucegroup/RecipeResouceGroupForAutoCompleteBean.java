/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesoucegroup;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 設備グループのオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class RecipeResouceGroupForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public RecipeResouceGroupForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public RecipeResouceGroupForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
