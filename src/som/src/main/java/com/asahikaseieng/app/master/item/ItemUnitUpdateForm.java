/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 見積標準単価更新 Formクラス.
 * @author t0011036
 */
public final class ItemUnitUpdateForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 見積変更開始基準日 */
	private String strInputDate;

	/**
	 * コンストラクタ.
	 */
	public ItemUnitUpdateForm() {
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
	 * クリア処理.
	 */
	public void clear() {
		setStrInputDate(null);
	}

	/**
	 * strInputDateを取得します。
	 * @return strInputDate
	 */
	public String getStrInputDate() {
		return strInputDate;
	}

	/**
	 * strInputDateを設定します。
	 * @param strInputDate strInputDate
	 */
	public void setStrInputDate(final String strInputDate) {
		this.strInputDate = strInputDate;
	}
}
