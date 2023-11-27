/*
 * Created on 2008/07/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.cal;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.cal.Cal;
import com.asahikaseieng.dao.entity.master.cal.CalDao;
import com.asahikaseieng.dao.nonentity.master.caldelete.CalDeleteDao;
import com.asahikaseieng.dao.nonentity.master.caldetaillist.CalDetailList;
import com.asahikaseieng.dao.nonentity.master.caldetaillist.CalDetailListDao;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetail;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * カレンダー詳細ロジック 実装クラス.
 * @author tosco
 */
public class CalDetailLogicImpl implements CalDetailLogic {

	private CalDao calDao;

	private CalDeleteDao calDeleteDao;

	private CalDetailListDao calDetailListDao;

	private CompanyDetailDao companyDetailDao;

	/**
	 * コンストラクタ.
	 */
	public CalDetailLogicImpl() {
	}

	/**
	 * カレンダー検索（登録用）
	 * @param calCd カレンダーコード
	 * @param calDate 日付
	 * @return Cal
	 */
	public Cal getEntity(final String calCd, final Timestamp calDate) {
		if (StringUtils.isEmpty(calCd)) {
			throw new IllegalArgumentException("calCd is empty");
		}

		Cal bean = calDao.getEntity(calCd, calDate);

		return bean;
	}

	/**
	 * カレンダー検索（詳細用）
	 * @param calCd カレンダーコード
	 * @param calYear 会計年度
	 * @return List<CalDetailList>
	 */
	public List<CalDetailList> getDetailList(final String calCd,
			final String calYear) {
		List<CalDetailList> bean = calDetailListDao.getList(calCd,
			AecNumberUtils.convertBigDecimal(calYear));
		return bean;
	}

	/**
	 * 自社検索
	 * @param companyCd 自社コード
	 * @return CompanyDetail
	 */
	public CompanyDetail getCompanyEntity(final String companyCd) {
		CompanyDetail bean = companyDetailDao.getEntity(companyCd);
		return bean;
	}

	/**
	 * 追加登録
	 * @param frm 登録データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public void insert(final CalDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Cal bean = new Cal();

		try {
			/* 一括削除処理 */
			deleteCalList(frm.getCalCd(), frm.getCalYear());

			/* 12ヶ月分追加 */
			for (int i = 0; i < frm.getCalDetailList().size(); i++) {
				/* 月の日数取得 */
				Calendar cal = new GregorianCalendar(AecNumberUtils
						.convertBigDecimal(
							frm.getCalDetailList().get(i).getCalYyyy())
						.intValue(), Integer.parseInt(frm.getCalDetailList()
						.get(i).getCalMm()) - 1, 1);
				cal.set(Calendar.YEAR, Integer.parseInt(frm.getCalDetailList()
						.get(i).getCalYyyy()));
				cal.set(Calendar.MONTH, Integer.parseInt(frm.getCalDetailList()
						.get(i).getCalMm()) - 1);
				int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

				for (int j = 0; j < days; j++) {
					/* 値を更新用Beanにコピる */
					IgnoreCaseBeanUtils.copyProperties(bean, frm
							.getCalDetailList().get(i));

					bean.setCalCd(frm.getCalCd());
					bean.setCalName(frm.getCalName());
					bean.setCalYear(new BigDecimal(frm.getCalYear()));

					/* 各月ごとの値設定 */
					bean.setCalDate(AecDateUtils.getTimestampYmdFormat(frm
							.getCalDetailList().get(i).getCalYyyy()
							+ "/"
							+ AecNumberUtils.decimalFormat(new BigDecimal(frm
									.getCalDetailList().get(i).getCalMm()),
								"00")
							+ "/"
							+ AecNumberUtils.decimalFormat(
								new BigDecimal(j + 1), "00")));

					if (AecDateUtils.dateFormat(bean.getCalDate(), "E").equals(
						"日")) {
						bean.setCalWeek(new BigDecimal("0"));
					} else if (AecDateUtils.dateFormat(bean.getCalDate(), "E")
							.equals("月")) {
						bean.setCalWeek(new BigDecimal("1"));
					} else if (AecDateUtils.dateFormat(bean.getCalDate(), "E")
							.equals("火")) {
						bean.setCalWeek(new BigDecimal("2"));
					} else if (AecDateUtils.dateFormat(bean.getCalDate(), "E")
							.equals("水")) {
						bean.setCalWeek(new BigDecimal("3"));
					} else if (AecDateUtils.dateFormat(bean.getCalDate(), "E")
							.equals("木")) {
						bean.setCalWeek(new BigDecimal("4"));
					} else if (AecDateUtils.dateFormat(bean.getCalDate(), "E")
							.equals("金")) {
						bean.setCalWeek(new BigDecimal("5"));
					} else if (AecDateUtils.dateFormat(bean.getCalDate(), "E")
							.equals("土")) {
						bean.setCalWeek(new BigDecimal("6"));
					}

					bean.setCalHoliday(new BigDecimal(frm.getCalDetailList()
							.get(i).getCalDd().substring(j, j + 1)));
					bean.setInputDate(bean.getCurrentTimestamp());
					bean.setInputorCd(tantoCd);
					bean.setUpdatorCd(tantoCd);

					/* 追加登録 */
					calDao.insert(bean);
				}
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * カレンダー一括削除
	 * @param calCd calCd
	 * @param calYear calYear
	 * @return 削除件数
	 */
	public int deleteCalList(final String calCd, final String calYear) {
		return calDeleteDao.deleteList(calCd, calYear);
	}

	/**
	 * 削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void delete(final Cal bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			calDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * calDaoを設定します。
	 * @param calDao calDao
	 */
	public void setCalDao(final CalDao calDao) {
		this.calDao = calDao;
	}

	/**
	 * calDetailListDaoを設定します。
	 * @param calDetailListDao calDetailListDao
	 */
	public void setCalDetailListDao(final CalDetailListDao calDetailListDao) {
		this.calDetailListDao = calDetailListDao;
	}

	/**
	 * companyDetailDaoを設定します。
	 * @param companyDetailDao companyDetailDao
	 */
	public void setCompanyDetailDao(final CompanyDetailDao companyDetailDao) {
		this.companyDetailDao = companyDetailDao;
	}

	/**
	 * calDeleteDaoを設定します。
	 * @param calDeleteDao calDeleteDao
	 */
	public void setCalDeleteDao(final CalDeleteDao calDeleteDao) {
		this.calDeleteDao = calDeleteDao;
	}
}
