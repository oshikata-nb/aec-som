/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.checkholiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.asahikaseieng.Constants;

/**
 * 休日チェック
 * @author t0011036
 */
public final class CheckHoliday {

	/**
	 * コンストラクタ
	 */
	private CheckHoliday() {
	}

	/**
	 * 休日チェック
	 * @param request HttpServletRequest
	 * @return CheckHolidayLogic
	 */
	public static CheckHolidayLogic getHoliday(final HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (CheckHolidayLogic) session
				.getAttribute(Constants.CHECK_HOLIDAY_KEY);
	}
}
