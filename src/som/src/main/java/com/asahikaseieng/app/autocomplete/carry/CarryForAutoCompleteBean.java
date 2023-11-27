/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.carry;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 運送会社のオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class CarryForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public CarryForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public CarryForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
