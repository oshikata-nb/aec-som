/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.checkholiday;

import java.sql.Timestamp;

/**
 * CheckHolidayDaoクラス
 * @author t0011036
 */
public interface CheckHolidayDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.checkholiday.CheckHolidayDetail.class;

	/** ARGSアノテーション getCalHoliday */
	String getCalHoliday_ARGS = "calCd, calDate";

	/**
	 * CheckHolidayメソッド
	 * 
	 * @param calCd calCd
	 * @param calDate calDate
	 * @return CheckHoliday
	 */
	CheckHolidayDetail getCalHoliday(final String calCd, final Timestamp calDate);

	/** ARGSアノテーション getVenderCal */
	String getVenderCal_ARGS = "venderDivision, venderCd";

	/**
	 * CheckHolidayメソッド
	 * 
	 * @param venderDivision venderDivision
	 * @param venderCd venderCd
	 * @return CheckHoliday
	 */
	CheckHolidayDetail getVenderCal(final String venderDivision,
			final String venderCd);
}
