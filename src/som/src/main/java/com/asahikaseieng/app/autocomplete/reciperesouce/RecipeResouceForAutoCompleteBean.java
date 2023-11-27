/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesouce;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 設備のオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class RecipeResouceForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public RecipeResouceForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public RecipeResouceForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
