/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.itemcategory;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 品目分類のオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class ItemCategoryForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public ItemCategoryForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public ItemCategoryForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
