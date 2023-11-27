/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.line;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 生産ラインのオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class LineForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public LineForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public LineForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
