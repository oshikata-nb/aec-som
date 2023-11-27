/*
 * Created on 2006/08/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.header;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractForm;

import org.apache.struts.action.ActionMapping;

/**
 * ヘッダー Formクラス.
 * @author jbd
 */
public final class HeaderForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* loginInfo */
	private LoginInfo loginInfo;

	/**
	 * コンストラクタ/
	 */
	public HeaderForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setLoginInfo(new LoginInfo());
	}

	/**
	 * userBeanを取得します。
	 * @return loginInfo
	 */
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	/**
	 * loginInfoを設定します。
	 * @param loginInfo LoginInfo
	 */
	public void setLoginInfo(final LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
}
