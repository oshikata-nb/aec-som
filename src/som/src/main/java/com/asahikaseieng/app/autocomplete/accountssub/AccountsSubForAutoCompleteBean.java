/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.accountssub;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 補助科目のオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class AccountsSubForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public AccountsSubForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public AccountsSubForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
