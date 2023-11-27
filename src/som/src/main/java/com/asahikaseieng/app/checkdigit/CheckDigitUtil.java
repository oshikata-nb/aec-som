/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.checkdigit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.asahikaseieng.Constants;

/**
 * 桁数チェックロジッククラスを呼び出すユーティリティ
 * @author 917
 */
public final class CheckDigitUtil {

	/**
	 * コンストラクタ
	 */
	private CheckDigitUtil() {
	}
	/**
	 * 数値桁数チェックユーティリティを取得する
	 * @param request HttpServletRequest
	 * @return CheckDigitUtilsLogic
	 */
	public static CheckDigitUtilsLogic getCheckDigitUtils(final HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (CheckDigitUtilsLogic) session.getAttribute(Constants.CHECK_DIGIT_UTIL_KEY);
	}
}
