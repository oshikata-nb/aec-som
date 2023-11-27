/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.checkholiday;

import java.sql.Timestamp;

/**
 * 休日チェック
 * @author t0011036
 */
public interface CheckHolidayLogic {
	/**
	 * 取引先のカレンダーから休日を判定する
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @param calDate 年月日
	 * @return CheckHolidayResult checkHolidayResult
	 */
	CheckHolidayResult getVenderHoliday(String venderDivision, String venderCd,
			Timestamp calDate);

	/**
	 * カレンダーから休日を判定する
	 * @param calCd カレンダーコード
	 * @param calDate 年月日
	 * @return CheckHolidayResult checkHolidayResult
	 */
	CheckHolidayResult getCalHoliday(String calCd, Timestamp calDate);
}
