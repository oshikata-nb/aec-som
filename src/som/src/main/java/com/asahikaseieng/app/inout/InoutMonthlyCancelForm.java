/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 月次受払ロールバック処理 Formクラス.
 * @author t0011036
 */
public class InoutMonthlyCancelForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 処理年 */
	private String srhStrInputYear;

	/* 処理月 */
	private String srhStrInputMonth;

	/* 処理年月 */
	private String srhStrInputDate;

	/**
	 * コンストラクタ.
	 */
	public InoutMonthlyCancelForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);
		if ("init".equals(getOp())) {
			/* 初期化 */
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
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSrhStrInputYear(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy"));
		setSrhStrInputMonth(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "MM"));
		setSrhStrInputDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));
	}

	/**
	 * srhStrInputMonthを取得します。
	 * @return srhStrInputMonth
	 */
	public String getSrhStrInputMonth() {
		return srhStrInputMonth;
	}

	/**
	 * srhStrInputMonthを設定します。
	 * @param srhStrInputMonth srhStrInputMonth
	 */
	public void setSrhStrInputMonth(final String srhStrInputMonth) {
		this.srhStrInputMonth = srhStrInputMonth;
	}

	/**
	 * srhStrInputYearを取得します。
	 * @return srhStrInputYear
	 */
	public String getSrhStrInputYear() {
		return srhStrInputYear;
	}

	/**
	 * srhStrInputYearを設定します。
	 * @param srhStrInputYear srhStrInputYear
	 */
	public void setSrhStrInputYear(final String srhStrInputYear) {
		this.srhStrInputYear = srhStrInputYear;
	}

	/**
	 * srhStrInputDateを取得します。
	 * @return srhStrInputDate
	 */
	public String getSrhStrInputDate() {
		return srhStrInputDate;
	}

	/**
	 * srhStrInputDateを設定します。
	 * @param srhStrInputDate srhStrInputDate
	 */
	public void setSrhStrInputDate(final String srhStrInputDate) {
		this.srhStrInputDate = srhStrInputDate;
	}
}
