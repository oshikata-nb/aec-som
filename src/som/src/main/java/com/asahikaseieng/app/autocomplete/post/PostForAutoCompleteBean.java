/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.post;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 役職のオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class PostForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public PostForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public PostForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
