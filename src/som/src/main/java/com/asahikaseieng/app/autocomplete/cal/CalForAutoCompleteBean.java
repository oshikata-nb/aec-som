/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.cal;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * カレンダーのオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class CalForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public CalForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public CalForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
