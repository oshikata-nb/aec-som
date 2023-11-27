/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.financialclass;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 財務分類のオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class FinancialClassForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public FinancialClassForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public FinancialClassForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
