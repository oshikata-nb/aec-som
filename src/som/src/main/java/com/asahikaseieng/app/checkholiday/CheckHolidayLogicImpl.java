/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.checkholiday;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.checkholiday.CheckHolidayDetail;
import com.asahikaseieng.dao.nonentity.checkholiday.CheckHolidayDetailDao;

/**
 * 休日チェック
 * @author t0011036
 */
public class CheckHolidayLogicImpl implements CheckHolidayLogic, Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private CheckHolidayDetailDao checkHolidayDetailDao;

	private static final BigDecimal OK = new BigDecimal("0");

	private static final BigDecimal VENDER_NODATA = new BigDecimal("1");

	private static final BigDecimal VENDER_NOCAL = new BigDecimal("2");

	private static final BigDecimal CAL_NODATA = new BigDecimal("3");

	/**
	 * コンストラクタ
	 */
	public CheckHolidayLogicImpl() {
	}

	/**
	 * 取引先のカレンダーから休日を判定する
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @param calDate 年月日
	 * @return CheckHolidayResult checkHolidayResult
	 */
	public CheckHolidayResult getVenderHoliday(final String venderDivision,
			final String venderCd, final Timestamp calDate) {
		CheckHolidayResult result = new CheckHolidayResult(OK);

		/* 取引先検索 */
		CheckHolidayDetail beanVender = checkHolidayDetailDao.getVenderCal(
			venderDivision, venderCd);

		/* 取引先未登録 */
		if (beanVender == null) {
			result.setCode(VENDER_NODATA);
			return result;
		}

		/* カレンダーコード未登録 */
		if (StringUtils.isEmpty(beanVender.getCalendarCd())) {
			result.setCode(VENDER_NOCAL);
			return result;
		}

		/* カレンダー検索 */
		CheckHolidayDetail beanCal = checkHolidayDetailDao.getCalHoliday(
			beanVender.getCalendarCd(), calDate);

		/* カレンダー未登録 */
		if (beanCal == null) {
			result.setCode(CAL_NODATA);
			return result;
		}

		result.setCalHoliday(beanCal.getCalHoliday());
		result.setCode(OK);

		return result;
	}

	/**
	 * カレンダーから休日を判定する
	 * @param calCd カレンダーコード
	 * @param calDate 年月日
	 * @return CheckHolidayResult checkHolidayResult
	 */
	public CheckHolidayResult getCalHoliday(final String calCd,
			final Timestamp calDate) {
		CheckHolidayResult result = new CheckHolidayResult(OK);

		/* カレンダー検索 */
		CheckHolidayDetail bean = checkHolidayDetailDao.getCalHoliday(calCd,
			calDate);

		/* カレンダー未登録 */
		if (bean == null) {
			result.setCode(CAL_NODATA);
			return result;
		}

		result.setCalHoliday(bean.getCalHoliday());
		result.setCode(OK);

		return result;
	}

	// setter------------------------------------------------------------------

	/**
	 * checkHolidayDetailDaoを設定します。
	 * @param checkHolidayDetailDao checkHolidayDetailDao
	 */
	public void setCheckHolidayDetailDao(
			final CheckHolidayDetailDao checkHolidayDetailDao) {
		this.checkHolidayDetailDao = checkHolidayDetailDao;
	}
}
