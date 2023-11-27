/*
 * Created on 2008/07/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.cal;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.master.caldetaillist.CalDetailList;
import com.asahikaseieng.struts.AbstractForm;

/**
 * カレンダー詳細 Formクラス.
 * @author tosco
 */
public final class CalDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* １週間＊６週 */
	private static final int WEEK_6 = 42;

	/* フラグ（平日） */
	private static final String FLG_WEEKDAY = "0";

	/* カレンダーコード */
	private String calCd;

	/* カレンダー用途名 */
	private String calName;

	/* 会計年度 */
	private String calYear;

	/* 年月日 */
	private java.sql.Timestamp calDate;

	/* 曜日 */
	private java.math.BigDecimal calWeek;

	/* 休日 */
	private String[] holiday;

	/* カレンダータイトル(12ヶ月分) */
	private String[] title;

	/* カレンダーリスト(12ヶ月分) */
	private List<CalDetailList> calDetailList;

	/* 平日／休日フラグリスト(1ヶ月(42日) * 12ヶ月分) */
	private List<CalDetailList> calHolidayList;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 会計年度変更フラグ */
	private Boolean dirtyYearFlg;

	/* 新規フラグ */
	private String newFlg;

	/**
	 * コンストラクタ.カレンダーマスタ詳細
	 */
	public CalDetailForm() {
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

		if ("make".equals(getOp())) {
			/* 初期休日 */
			setHoliday(null);
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

			/* フラグリスト再設定 */
			setCalendarList();
		}

		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);

			/* フラグリスト再設定 */
			setCalendarList();
		}

		if ("make".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * カレンダー平日休日フラグリスト再設定(42日×12ヶ月分) （エラー発生時の画面再表示のため）
	 * @exception Exception
	 */
	private void setCalendarList() {
		CalDetailList flgBean = null; /* １ヶ月(42日)の平日休日フラグ */
		CalDetailList dbBean = null; /* DB取得データ(変更時) */

		List<CalDetailList> newHolidayList = new ArrayList<CalDetailList>();
		List<CalDetailList> calDetailList = new ArrayList<CalDetailList>();

		Calendar cal = Calendar.getInstance();

		int startMonth = Integer.parseInt(getCalDetailList().get(0).getCalMm()) - 1;
		cal.set(Integer.parseInt(getCalDetailList().get(0).getCalYyyy()),
			startMonth, 1);

		for (int m = 0; m < 12; m++) {
			/* 変更の場合 */
			flgBean = new CalDetailList();
			dbBean = getCalDetailList().get(m);

			/* 曜日を取得 */
			switch (cal.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.SUNDAY:
				calDetailList.add(setDaysBean(0, cal, flgBean, dbBean));
				break;
			case Calendar.MONDAY:
				flgBean.setDay01(FLG_WEEKDAY);
				calDetailList.add(setDaysBean(1, cal, flgBean, dbBean));
				break;
			case Calendar.TUESDAY:
				flgBean.setDay01(FLG_WEEKDAY);
				flgBean.setDay02(FLG_WEEKDAY);
				calDetailList.add(setDaysBean(2, cal, flgBean, dbBean));
				break;
			case Calendar.WEDNESDAY:
				flgBean.setDay01(FLG_WEEKDAY);
				flgBean.setDay02(FLG_WEEKDAY);
				flgBean.setDay03(FLG_WEEKDAY);
				calDetailList.add(setDaysBean(3, cal, flgBean, dbBean));
				break;
			case Calendar.THURSDAY:
				flgBean.setDay01(FLG_WEEKDAY);
				flgBean.setDay02(FLG_WEEKDAY);
				flgBean.setDay03(FLG_WEEKDAY);
				flgBean.setDay04(FLG_WEEKDAY);
				calDetailList.add(setDaysBean(4, cal, flgBean, dbBean));
				break;
			case Calendar.FRIDAY:
				flgBean.setDay01(FLG_WEEKDAY);
				flgBean.setDay02(FLG_WEEKDAY);
				flgBean.setDay03(FLG_WEEKDAY);
				flgBean.setDay04(FLG_WEEKDAY);
				flgBean.setDay05(FLG_WEEKDAY);
				calDetailList.add(setDaysBean(5, cal, flgBean, dbBean));
				break;
			case Calendar.SATURDAY:
				flgBean.setDay01(FLG_WEEKDAY);
				flgBean.setDay02(FLG_WEEKDAY);
				flgBean.setDay03(FLG_WEEKDAY);
				flgBean.setDay04(FLG_WEEKDAY);
				flgBean.setDay05(FLG_WEEKDAY);
				flgBean.setDay06(FLG_WEEKDAY);
				calDetailList.add(setDaysBean(6, cal, flgBean, dbBean));
				break;
			default:
			}

			newHolidayList.add(flgBean);
		}

		setCalHolidayList(newHolidayList);
	}

	/**
	 * 当月(42日)の平日休日フラグを設定
	 * 
	 * @param start 当月１日の開始インデックス
	 * @param cal カレンダー
	 * @param flgBean １ヶ月(42日)の平日休日フラグ(基本パターン)
	 * @param dbBean カレンダーマスタデータ
	 * @return CalDetailList 当月のカレンダー日付、平日休日フラグが設定されたBean
	 * @throws Exception
	 */
	private CalDetailList setDaysBean(final int start, final Calendar cal,
			final CalDetailList flgBean, final CalDetailList dbBean) {
		String propetyName = "";
		String dayFlg = "";
		int beginIdx = 0;

		try {
			/* 当月取得 */
			int month = cal.get(Calendar.MONTH) + 1;

			for (int i = start; i < WEEK_6; i++) {
				/* Beanのプロパティ名取得 */
				if (i < 9) {
					propetyName = "day0" + Integer.toString(i + 1);
				} else {
					propetyName = "day" + Integer.toString(i + 1);
				}

				/* 現在の月を取得 */
				int todayMonth = cal.get(Calendar.MONTH) + 1;

				if (month == todayMonth) {
					/* 当月1日～月末まで */
					/* 変更の場合、BeanのプロパティにDBのフラグを設定 */
					if (beginIdx + 1 == dbBean.getCalDd().length()) {
						/* 最後 */
						dayFlg = dbBean.getCalDd().substring(beginIdx);
					} else {
						dayFlg = dbBean.getCalDd().substring(beginIdx,
							beginIdx + 1);
					}

					PropertyUtils.setSimpleProperty(flgBean, propetyName,
						dayFlg);
					beginIdx = beginIdx + 1;

					/* 日付を１日プラス */
					cal.add(Calendar.DATE, 1);
				} else {
					/* 月末以降～42日まで */
					/* flgBeanのプロパティに平日(0)を再設定 */
					PropertyUtils.setSimpleProperty(flgBean, propetyName,
						FLG_WEEKDAY);
				}
			}
		} catch (IllegalAccessException iae) {
			System.out.println(iae);
		} catch (InvocationTargetException ite) {
			System.out.println(ite);
		} catch (NoSuchMethodException ne) {
			System.out.println(ne);
		}

		return flgBean;
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		setCalCd(null);
		setCalName(null);
		setCalYear(null);
		setCalDate(null);
		setCalWeek(null);
		setHoliday(null);
		setTitle(null);
		setCalDetailList(new ArrayList<CalDetailList>());
		setCalHolidayList(new ArrayList<CalDetailList>());
		setUpdateDate(null);
		setDirtyFlg(null);
		setDirtyYearFlg(null);
		setNewFlg(null);
	}

	/**
	 * calCdを取得します。
	 * @return calCd
	 */
	public String getCalCd() {
		return calCd;
	}

	/**
	 * calCdを設定します。
	 * @param calCd calCd
	 */
	public void setCalCd(final String calCd) {
		this.calCd = calCd;
	}

	/**
	 * calDateを取得します。
	 * @return calDate
	 */
	public java.sql.Timestamp getCalDate() {
		return calDate;
	}

	/**
	 * calDateを設定します。
	 * @param calDate calDate
	 */
	public void setCalDate(final java.sql.Timestamp calDate) {
		this.calDate = calDate;
	}

	/**
	 * calDetailListを取得します。
	 * @return calDetailList
	 */
	public List<CalDetailList> getCalDetailList() {
		return calDetailList;
	}

	/**
	 * calDetailListを設定します。
	 * @param calDetailList calDetailList
	 */
	public void setCalDetailList(final List<CalDetailList> calDetailList) {
		this.calDetailList = calDetailList;
	}

	/**
	 * calHolidayListを取得します。
	 * @return calHolidayList
	 */
	public List<CalDetailList> getCalHolidayList() {
		return calHolidayList;
	}

	/**
	 * calHolidayListを設定します。
	 * @param calHolidayList calHolidayList
	 */
	public void setCalHolidayList(final List<CalDetailList> calHolidayList) {
		this.calHolidayList = calHolidayList;
	}

	/**
	 * calNameを取得します。
	 * @return calName
	 */
	public String getCalName() {
		return calName;
	}

	/**
	 * calNameを設定します。
	 * @param calName calName
	 */
	public void setCalName(final String calName) {
		this.calName = calName;
	}

	/**
	 * calWeekを取得します。
	 * @return calWeek
	 */
	public java.math.BigDecimal getCalWeek() {
		return calWeek;
	}

	/**
	 * calWeekを設定します。
	 * @param calWeek calWeek
	 */
	public void setCalWeek(final java.math.BigDecimal calWeek) {
		this.calWeek = calWeek;
	}

	/**
	 * calYearを取得します。
	 * @return calYear
	 */
	public String getCalYear() {
		return calYear;
	}

	/**
	 * calYearを設定します。
	 * @param calYear calYear
	 */
	public void setCalYear(final String calYear) {
		this.calYear = calYear;
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
	 * newFlgを取得します。
	 * @return newFlg
	 */
	public String getNewFlg() {
		return newFlg;
	}

	/**
	 * newFlgを設定します。
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final String newFlg) {
		this.newFlg = newFlg;
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
	 * holidayを取得します。
	 * @return holiday
	 */
	public String[] getHoliday() {
		return holiday;
	}

	/**
	 * holidayを設定します。
	 * @param holiday holiday
	 */
	public void setHoliday(final String[] holiday) {
		this.holiday = holiday;
	}

	/**
	 * titleを取得します。
	 * @return title
	 */
	public String[] getTitle() {
		return title;
	}

	/**
	 * titleを設定します。
	 * @param title title
	 */
	public void setTitle(final String[] title) {
		this.title = title;
	}

	/**
	 * dirtyYearFlgを取得します。
	 * @return dirtyYearFlg
	 */
	public Boolean getDirtyYearFlg() {
		return dirtyYearFlg;
	}

	/**
	 * dirtyYearFlgを設定します。
	 * @param dirtyYearFlg dirtyYearFlg
	 */
	public void setDirtyYearFlg(final Boolean dirtyYearFlg) {
		this.dirtyYearFlg = dirtyYearFlg;
	}
}
