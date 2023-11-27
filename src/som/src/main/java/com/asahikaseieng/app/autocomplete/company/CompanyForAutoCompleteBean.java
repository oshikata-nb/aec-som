/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.company;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 自社のオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class CompanyForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public CompanyForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public CompanyForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
