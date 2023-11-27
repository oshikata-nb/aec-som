/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.offsetgroup;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 相殺グループのオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class OffsetGroupForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public OffsetGroupForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public OffsetGroupForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}
}
