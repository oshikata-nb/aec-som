/*
 * Created on 2008/07/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.cal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.cal.Cal;
import com.asahikaseieng.dao.nonentity.check.PasswordCheck;
import com.asahikaseieng.dao.nonentity.master.caldetaillist.CalDetailList;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * カレンダー詳細 Actionクラス.
 * @author tosco
 */
public final class CalDetailAction extends AbstractAction {

	/** 初期休日（日曜） */
	private static final String HOLIDAY_SUNDAY = "0";

	/** 初期休日（土曜） */
	private static final String HOLIDAY_SATURDAY = "6";

	/** １週間＊６週 */
	private static final int WEEK_6 = 42;

	/** フラグ（平日） */
	private static final String FLG_WEEKDAY = "0";

	/** フラグ（休日） */
	private static final String FLG_HOLIDAY = "1";

	private CalDetailLogic calDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public CalDetailAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		CalDetailForm frm = (CalDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_CAL,
			Constants.TAB_ID_CAL_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		List<CalDetailList> bean = calDetailLogic.getDetailList(frm.getCalCd(),
			frm.getCalYear());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		/* １件目のBeanの内容をFormにコピーする */
		frm.setCalCd(bean.get(0).getCalCd());
		frm.setCalName(bean.get(0).getCalName());
		frm.setCalYear(bean.get(0).getCalYear().toString());

		try {
			/* カレンダー開始月取得 */
			int startMonth = Integer.valueOf(bean.get(0).getCalMm()) - 1;
			setCalendarList(frm, startMonth, bean);
		} catch (NoDataException e) {
			throw e;
		}

		return mapping.findForward("success");
	}

	/**
	 * 登録処理処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}

		CalDetailForm frm = (CalDetailForm) form;

		/* 日付作成 */
		Timestamp calDate = AecDateUtils.getTimestampYmdFormat(frm
				.getCalDetailList().get(0).getCalYyyy()
				+ "/"
				+ AecNumberUtils.decimalFormat(new BigDecimal(frm
						.getCalDetailList().get(0).getCalMm()), "00") + "/01");

		if (frm.getNewFlg().equals("true")) {
			Cal bean = calDetailLogic.getEntity(frm.getCalCd(), calDate);

			if (bean != null) {
				/* メッセージ */
				saveError(request, "errors.duplicate.data");

				return mapping.findForward("success");
			}
		}

		/* 登録処理を実行 */
		calDetailLogic.insert(frm, getLoginInfo(request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
	}

	/**
	 * 削除処理処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("delete");
		}

		CalDetailForm frm = (CalDetailForm) form;

		/* 削除処理を実行 */
		calDetailLogic.deleteCalList(frm.getCalCd(), frm.getCalYear());

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");
	}

	/**
	 * 新規処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		CalDetailForm frm = (CalDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_CAL,
			Constants.TAB_ID_CAL_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		Calendar cal = new GregorianCalendar();
		frm.setCalYear(Integer.toString(cal.get(Calendar.YEAR)));

		/* デフォルト初期休日設定(日曜、土曜) */
		String[] holiday = {HOLIDAY_SUNDAY, HOLIDAY_SATURDAY};
		frm.setHoliday(holiday);

		return mapping.findForward("success");
	}

	/**
	 * 作成ボタン押下処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward make(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("make.");
		}

		CalDetailForm frm = (CalDetailForm) form;

		try {
			/* カレンダー開始月を取得 */
			BigDecimal settlement = getSettlement(request);

			if (settlement != null) {
				int startMonth = settlement.intValue();

				if (12 <= startMonth) {
					/* 決算月が12月の場合、指定年度の1月始まりにする */
					startMonth = 0;
				}

				/* カレンダー設定 */
				setCalendarList(frm, startMonth, null);
			}
		} catch (NoDataException e) {
			throw e;
		}

		return mapping.findForward("success");
	}

	/**
	 * コピー作成ボタン押下処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward copy(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("copy.");
		}

		CalDetailForm frm = (CalDetailForm) form;

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		frm.setCalCd(null);

		/* デフォルト初期休日設定(日曜、土曜) */
		String[] holiday = {HOLIDAY_SUNDAY, HOLIDAY_SATURDAY};
		frm.setHoliday(holiday);

		return mapping.findForward("success");
	}

	/**
	 * 自社マスタの決算月取得
	 * @param request HttpServletRequest
	 * @return BigDecimal 決算月
	 * @exception NoDataException
	 */
	private BigDecimal getSettlement(final HttpServletRequest request)
			throws NoDataException {
		/* セッションから自社コード取得 */
		HttpSession session = request.getSession(false);

		if (session == null) {
			return null;
		}

		PasswordCheck chk = (PasswordCheck) (session
				.getAttribute(Constants.COMPANY_INFO_KEY));

		/* 自社マスタから決算月取得 */
		CompanyDetail bean = calDetailLogic
				.getCompanyEntity(chk.getCompanyCd());

		return bean.getSettlement();
	}

	/**
	 * カレンダー日にちリスト設定(42日×12ヶ月分)
	 * @param frm カレンダーマスタ詳細画面Form
	 * @param startMonth カレンダー開始月(決算月の翌月)
	 * @param calDetailList カレンダーマスタデータ(新規の場合はnull)
	 * @exception Exception
	 */
	private void setCalendarList(final CalDetailForm frm, final int startMonth,
			final List<CalDetailList> calDetailList) throws Exception {
		CalDetailList flgBean = null; /* １ヶ月(42日)の平日休日フラグ */
		CalDetailList daysBean = null; /* １ヶ月(42日)の日にち */
		CalDetailList dbBean = null; /* DB取得データ(変更時) */

		List<CalDetailList> newHolidayList = new ArrayList<CalDetailList>();
		List<CalDetailList> calendarList = new ArrayList<CalDetailList>();
		String[] calTitle = new String[12];

		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(frm.getCalYear()), startMonth, 1);

		for (int m = 0; m < 12; m++) {
			/* カレンダータイトル設定 */
			String year = Integer.toString(cal.get(Calendar.YEAR)) + "年";
			String month = Integer.toString(cal.get(Calendar.MONTH) + 1) + "月";
			calTitle[m] = year + month;

			if (calDetailList == null) {
				/* 新規の場合、基本となる平日休日フラグリスト設定 */
				flgBean = setCalHolidayListBase(frm);
			} else {
				/* 変更の場合 */
				flgBean = new CalDetailList();
				dbBean = calDetailList.get(m);
			}

			daysBean = new CalDetailList();

			/* 曜日を取得 */
			switch (cal.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.SUNDAY:
				calendarList.add(setDaysBean(0, cal, flgBean, dbBean));
				break;
			case Calendar.MONDAY:
				flgBean.setDay01(FLG_WEEKDAY);
				daysBean.setDay01("");
				calendarList.add(setDaysBean(1, cal, flgBean, dbBean));
				break;
			case Calendar.TUESDAY:
				flgBean.setDay01(FLG_WEEKDAY);
				flgBean.setDay02(FLG_WEEKDAY);
				daysBean.setDay01("");
				daysBean.setDay02("");
				calendarList.add(setDaysBean(2, cal, flgBean, dbBean));
				break;
			case Calendar.WEDNESDAY:
				flgBean.setDay01(FLG_WEEKDAY);
				flgBean.setDay02(FLG_WEEKDAY);
				flgBean.setDay03(FLG_WEEKDAY);
				daysBean.setDay01("");
				daysBean.setDay02("");
				daysBean.setDay03("");
				calendarList.add(setDaysBean(3, cal, flgBean, dbBean));
				break;
			case Calendar.THURSDAY:
				flgBean.setDay01(FLG_WEEKDAY);
				flgBean.setDay02(FLG_WEEKDAY);
				flgBean.setDay03(FLG_WEEKDAY);
				flgBean.setDay04(FLG_WEEKDAY);
				daysBean.setDay01("");
				daysBean.setDay02("");
				daysBean.setDay03("");
				daysBean.setDay04("");
				calendarList.add(setDaysBean(4, cal, flgBean, dbBean));
				break;
			case Calendar.FRIDAY:
				flgBean.setDay01(FLG_WEEKDAY);
				flgBean.setDay02(FLG_WEEKDAY);
				flgBean.setDay03(FLG_WEEKDAY);
				flgBean.setDay04(FLG_WEEKDAY);
				flgBean.setDay05(FLG_WEEKDAY);
				daysBean.setDay01("");
				daysBean.setDay02("");
				daysBean.setDay03("");
				daysBean.setDay04("");
				daysBean.setDay05("");
				calendarList.add(setDaysBean(5, cal, flgBean, dbBean));
				break;
			case Calendar.SATURDAY:
				flgBean.setDay01(FLG_WEEKDAY);
				flgBean.setDay02(FLG_WEEKDAY);
				flgBean.setDay03(FLG_WEEKDAY);
				flgBean.setDay04(FLG_WEEKDAY);
				flgBean.setDay05(FLG_WEEKDAY);
				flgBean.setDay06(FLG_WEEKDAY);
				daysBean.setDay01("");
				daysBean.setDay02("");
				daysBean.setDay03("");
				daysBean.setDay04("");
				daysBean.setDay05("");
				daysBean.setDay06("");
				calendarList.add(setDaysBean(6, cal, flgBean, dbBean));
				break;
			default:
			}

			newHolidayList.add(flgBean);
		}

		frm.setTitle(calTitle);
		frm.setCalHolidayList(newHolidayList);
		frm.setCalDetailList(calendarList);
	}

	/**
	 * 初期休日に合わせた平日／休日フラグリスト設定(1ヶ月(42日)×12ヶ月分)
	 * @param frm カレンダーマスタ詳細画面Form
	 * @return CalDetailList カレンダーマスタ詳細画面Bean
	 * @exception Exception
	 */
	private CalDetailList setCalHolidayListBase(final CalDetailForm frm)
			throws Exception {
		CalDetailList flgBean = new CalDetailList();
		String property = "";
		String flgValue = FLG_WEEKDAY; /* 平日 */

		/* Mapへ初期休日の設定 */
		String[] holiday = frm.getHoliday();
		Map<Integer, String> holiMap = new HashMap<Integer, String>();

		if (holiday != null) {
			for (int i = 0; i < holiday.length; i++) {
				holiMap.put(Integer.parseInt(holiday[i]), holiday[i]);
			}
		}

		/* ６週 */
		for (int i = 0; i < WEEK_6; i = i + 7) {
			/* ７日 */
			for (int j = 0; j < 7; j++) {
				if (i + j + 1 < 10) {
					property = "day0" + Integer.toString(i + j + 1);
				} else {
					property = "day" + Integer.toString(i + j + 1);
				}

				if (holiMap.get(j) != null) {
					flgValue = FLG_HOLIDAY; /* 休日 */
				} else {
					flgValue = FLG_WEEKDAY; /* 平日 */
				}

				/* Beanに平日／休日フラグ設定 */
				PropertyUtils.setSimpleProperty(flgBean, property, flgValue);
			}
		}

		return flgBean;
	}

	/**
	 * 当月のカレンダー日付、平日休日フラグを設定
	 * @param start 当月１日の開始インデックス
	 * @param cal カレンダー
	 * @param flgBean １ヶ月(42日)の平日休日フラグ(基本パターン)
	 * @param dbBean カレンダーマスタデータ(新規の場合はnull)
	 * @return CalDetailList 当月のカレンダー日付、平日休日フラグが設定されたBean
	 * @throws Exception
	 */
	private CalDetailList setDaysBean(final int start, final Calendar cal,
			final CalDetailList flgBean, final CalDetailList dbBean)
			throws Exception {
		String propetyName = "";
		String value = "";
		String calcDays = "";
		String dayFlg = "";
		int beginIdx = 0;

		CalDetailList daysBean = new CalDetailList();

		/* 当月取得 */
		int month = cal.get(Calendar.MONTH) + 1;
		daysBean.setCalYyyy(Integer.toString(cal.get(Calendar.YEAR))); /* 西暦年 */
		daysBean.setCalMm(Integer.toString(month)); /* 月 */

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
				if (dbBean == null) {
					/* 新規の場合、日にちに対応した平日/休日フラグ取得 */
					calcDays = calcDays
							+ (String) PropertyUtils.getSimpleProperty(flgBean,
								propetyName);
				} else {
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
				}

				/* 月末まではBeanのプロパティに日付を設定 */
				value = Integer.toString(cal.get(Calendar.DATE));

				/* 日付を１日プラス */
				cal.add(Calendar.DATE, 1);
			} else {
				/* 月末以降～42日まで */
				/* daysBeanのプロパティにブランクを設定 */
				value = "";

				/* flgBeanのプロパティに平日(0)を再設定 */
				PropertyUtils.setSimpleProperty(flgBean, propetyName,
					FLG_WEEKDAY);
			}

			/* daysBeanに日付設定 */
			PropertyUtils.setSimpleProperty(daysBean, propetyName, value);
		}

		/* 変更の場合 */
		if (dbBean != null) {
			calcDays = dbBean.getCalDd();
		}

		daysBean.setCalDd(calcDays); /* 平日/休日フラグ */

		return daysBean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * calDetailLogicを設定します。
	 * @param calDetailLogic calDetailLogic
	 */
	public void setCalDetailLogic(final CalDetailLogic calDetailLogic) {
		this.calDetailLogic = calDetailLogic;
	}
}
