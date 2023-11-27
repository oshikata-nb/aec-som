/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.area;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 地区のオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class AreaForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public AreaForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public AreaForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
