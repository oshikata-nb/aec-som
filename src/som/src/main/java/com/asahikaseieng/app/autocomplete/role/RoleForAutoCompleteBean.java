/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.role;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * ロールのオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class RoleForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public RoleForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public RoleForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
