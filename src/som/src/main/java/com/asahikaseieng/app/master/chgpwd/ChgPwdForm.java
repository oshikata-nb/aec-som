/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.chgpwd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.check.PasswordCheck;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 担当者パスワード変更 Formクラス
 * @author t0011036
 */
public final class ChgPwdForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* エラー項目名 パスワード */

	private static final String ERROR_ITEM_PASSWORD = "パスワード";

	//
	// インスタンスフィールド
	//

	/* 担当者コード */
	private String tantoCd;

	/* 担当者名称 */
	private String tantoNm;

	/* パスワード */
	private String password;

	//20220517 パスワードを２度入力させる。S.Fujimaki add
	/* パスワード */
	private String passwordConfirm;
	//20220517 パスワードを２度入力させる。S.Fujimaki add end

	/* 前回パスワード */
	private String lastPassword;

	/* パスワード更新日 */
	private java.sql.Timestamp updatePass;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/**
	 * コンストラクタ.ロケーションマスタ詳細
	 */
	public ChgPwdForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			/* クリア処理 */
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);

			if (!StringUtils.isEmpty(password)) {
				/* パスワードチェック */
				checkPassword(request, errors);
			}
		}

		return errors;
	}

	/**
	 * パスワードチェック
	 * @param request HttpServletRequest
	 * @param errors エラー内容
	 */
	private void checkPassword(final HttpServletRequest request,
			final ActionErrors errors) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}

		/* セッションからパスワード有効桁数取得 */
		PasswordCheck chk = (PasswordCheck) (session
				.getAttribute(Constants.COMPANY_INFO_KEY));
		int lower = chk.getPasswordKetaLowerLimit().intValue(); /* パスワード桁数下限 */
		int upper = chk.getPasswordKetaUpperLimit().intValue(); /* パスワード桁数上限 */

		/* パスワード有効桁数下限チェック */
		if (password.length() < lower) {
			String low = Integer.toString(lower);
			errors.add(password, new ActionMessage("errors.minlength",
					ERROR_ITEM_PASSWORD, low));
		}

		/* パスワード有効桁数上限チェック */
		if (upper < password.length()) {
			String up = Integer.toString(upper);
			errors.add(password, new ActionMessage("errors.maxlength",
					ERROR_ITEM_PASSWORD, up));
		}

		/* パスワード文字種(半角英数)チェック */
		if (!AecStringUtils.isAlphanumeric(password)) {
			errors.add(password, new ActionMessage("errors.alphanumeric",
					ERROR_ITEM_PASSWORD));
		}

		/* パスワード連続同一文字チェック */
		if (1 < password.length()) {
			String oldStr = password.substring(0, 1);
			String newStr = "";
			String chikanPassword = password.replace(oldStr, newStr);

			if (chikanPassword.length() == 0) {
				errors.add(password, new ActionMessage("errors.pass.same.char",
						ERROR_ITEM_PASSWORD));
			}
		}

		/* パスワード連続数値チェック */
		if (AecStringUtils.isNumeric(password) && 1 < password.length()) {
			int length = password.length(); /* パスワード桁数 */
			String topStr = password.substring(0, 1); /* パスワード先頭文字 */

			int topNum = Integer.parseInt(password.substring(0, 1)); /* パスワード先頭数値 */
			StringBuffer serialNum = new StringBuffer(topStr);

			/* 加算 */
			for (int i = 0; i < length - 1; i++) {
				topNum += 1;
				serialNum.append(Integer.toString(topNum));
			}

			if (password.equals(serialNum.toString())) {
				errors.add(password, new ActionMessage(
						"errors.pass.serial.number", ERROR_ITEM_PASSWORD));
			}

			topNum = Integer.parseInt(password.substring(0, 1)); /* パスワード先頭数値 */
			serialNum = new StringBuffer(topStr);

			/* 減算 */
			for (int i = 0; i < length - 1; i++) {
				topNum -= 1;

				if (topNum < 0) {
					break;
				}

				serialNum.append(Integer.toString(topNum));
			}

			if (password.equals(serialNum.toString())) {
				errors.add(password, new ActionMessage(
						"errors.pass.serial.number", ERROR_ITEM_PASSWORD));
			}
		}
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		setTantoCd(null);
		setTantoNm(null);
		setPassword(null);
		setPasswordConfirm(null);
		setLastPassword(null);
		setUpdatePass(null);
		setUpdateDate(null);
		setDirtyFlg(null);
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * lastPasswordを取得します。
	 * @return lastPassword
	 */
	public String getLastPassword() {
		return lastPassword;
	}

	/**
	 * lastPasswordを設定します。
	 * @param lastPassword lastPassword
	 */
	public void setLastPassword(final String lastPassword) {
		this.lastPassword = lastPassword;
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

//20220715 add S.Fujimaki
	/**
	 * passwordを取得します。
	 * @return password
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * passwordを設定します。
	 * @param password password
	 */
	public void setPasswordConfirm(final String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
//20220715
	/**
	 * tantoCdを取得します。
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * tantoCdを設定します。
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * tantoNmを取得します。
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * tantoNmを設定します。
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatePassを取得します。
	 * @return updatePass
	 */
	public java.sql.Timestamp getUpdatePass() {
		return updatePass;
	}

	/**
	 * updatePassを設定します。
	 * @param updatePass updatePass
	 */
	public void setUpdatePass(final java.sql.Timestamp updatePass) {
		this.updatePass = updatePass;
	}
}
