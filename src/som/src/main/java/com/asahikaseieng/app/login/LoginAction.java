/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.check.PasswordCheckLogic;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.crypt.MD5Crypt;

/**
 * ログイン Actionクラス.
 * @author jbd
 */
public final class LoginAction extends AbstractAction {

	private LoginLogic loginLogic;

	private PasswordCheckLogic passwordCheckLogic;

	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/**
	 * コンストラクタ.
	 */
	public LoginAction() {
		super();
	}

	/**
	 * loginLogicを設定します。
	 * @param loginLogic LoginLogic
	 */
	public void setLoginLogic(final LoginLogic loginLogic) {
		this.loginLogic = loginLogic;
	}

	/**
	 * passwordCheckLogicを設定します。
	 * @param passwordCheckLogic passwordCheckLogic
	 */
	public void setPasswordCheckLogic(
			final PasswordCheckLogic passwordCheckLogic) {
		this.passwordCheckLogic = passwordCheckLogic;
	}

	/**
	 * checkDigitUtilsLogicを設定します。
	 * @param checkDigitUtilsLogic checkDigitUtilsLogic
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward unspecified(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		/* ユーザー情報をクリア */
		request.getSession(false).removeAttribute(Constants.LOGIN_KEY);

		return mapping.findForward("success");
	}

	/**
	 * ログイン処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward login(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		String userId = "";

		try {
			LoginForm frm = (LoginForm) form;

			/* ユーザID取得 */
			userId = frm.getUserId();

			/* ユーザー情報を取得 */
			LoginInfo bean = loginLogic.login(frm.getUserId(), frm
					.getPassword());

			/* パスワードチェック */
			if (!checkPassword(request, frm, bean)) {
				return mapping.getInputForward();
			}

			/* セッションからログインエラー回数情報をクリア */
			request.getSession(false).removeAttribute(
				Constants.LOGIN_ERROR_COUNT_KEY);

			/* ログイン日時をセット */
			bean.setLoginDate(AecDateUtils.getCurrentTimestamp());

			/* ユーザー情報を格納 */
			request.getSession(false).setAttribute(Constants.LOGIN_KEY, bean);

			/* 数値桁数チェックロジッククラスをセッションに格納 */
			setCheckDegitUtil(request);

			/* 入力内容を消去 */
			frm.clear();

			/* window.openをする為に、ログインフラグを立てる */
			frm.setLoginFlag(true);
		} catch (NoDataException e) {
			passwordCheckLogic.countError(request, userId);
			saveError(request, "errors.nodata");
			return mapping.getInputForward();
		}

		return mapping.findForward("success");
	}

	/**
	 * メイン画面遷移処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return forward
	 * @throws Exception Exception
	 */
	public ActionForward popup(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return mapping.findForward("popup");

	}

	/**
	 * パスワードチェック
	 * 
	 * @param request HttpServletRequest
	 * @param frm 一覧画面用Form
	 * @param bean ログイン情報Bean
	 * @return boolean true:チェックOK false:チェックNG
	 * @throws NoDataException データ無し例外
	 * 
	 * @throws Exception Exception
	 */
	private boolean checkPassword(final HttpServletRequest request,
			final LoginForm frm, final LoginInfo bean) throws NoDataException,
			Exception {
		boolean res = true;

		/* パスワード一致チェック */
		String cryptPass = MD5Crypt.getMD5String(frm.getPassword());
		if (!passwordCheckLogic.checkPassword(bean.getPassword(), cryptPass)) {
			res = false;
			passwordCheckLogic.countError(request, frm.getUserId());
			saveError(request, "errors.nodata");
		}

		/* 自社マスタ情報取得 */
		passwordCheckLogic.getCompanyInfo(request);

		/* パスワード有効期限切れチェック */
		if (!passwordCheckLogic.checkPasswordValid(bean.getUpdatePass())) {
			res = false;
			passwordCheckLogic.countError(request, frm.getUserId());
			saveError(request, "errors.password.invalid");
		}

		/* ユーザデータ無効チェック */
		if (!passwordCheckLogic.checkActiveFlg(bean.getActiveFlg())) {
			res = false;
			saveError(request, "errors.password.invalid");
		}
		// String invalidDate =
		// passwordCheckLogic.calcPasswordInvalidDate(bean.getUpdatePass());
		// long days =
		// passwordCheckLogic.calcPasswordValidDays(bean.getUpdatePass());

		if (res) {
			// セッションからログインエラー回数情報をクリア
			request.getSession(false).removeAttribute(
				Constants.LOGIN_ERROR_COUNT_KEY);
		}

		return res;
	}

	/**
	 * 数値桁数チェックロジッククラスをセッションに格納
	 * @param request HttpServletRequest
	 */
	 private void setCheckDegitUtil(final HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.setAttribute(Constants.CHECK_DIGIT_UTIL_KEY,
			checkDigitUtilsLogic);
	}
}
