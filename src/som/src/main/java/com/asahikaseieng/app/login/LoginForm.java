/*
 * Created on 2006/08/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.login;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.struts.AbstractForm;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * ログイン Formクラス.
 * @author jbd
 */
public final class LoginForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* userId */
	private String userId;

	/* password */
	private String password;

	/* ログインフラグ */
	private boolean loginFlag;

	/**
	 * コンストラクタ.
	 */
	public LoginForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		/* ログインフラグは毎回倒す */
		setLoginFlag(false);

		if ("unspecified".equals(getOp())) {
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("login".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setUserId(null);
		setPassword(null);
		setLoginFlag(false);
	}

	/**
	 * userIdを取得します。
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * userIdを設定します。
	 * @param userId userId
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 * passwordを取得します。
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * passwordを設定します。
	 * @param password password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * loginFlagを取得します。
	 * @return loginFlag
	 */
	public boolean isLoginFlag() {
		return loginFlag;
	}

	/**
	 * loginFlagを設定します。
	 * @param loginFlag loginFlag
	 */
	public void setLoginFlag(final boolean loginFlag) {
		this.loginFlag = loginFlag;
	}

}
