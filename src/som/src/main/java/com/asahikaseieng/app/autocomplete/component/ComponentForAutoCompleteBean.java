/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.component;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 成分のオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class ComponentForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public ComponentForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public ComponentForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
