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
 * 品目登録理由 Formクラス.
 * @author t0011036
 */
public final class ItemRegistForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 登録理由 */
	private String reason;

	/**
	 * コンストラクタ.
	 */
	public ItemRegistForm() {
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
		setReason(null);
	}

	/**
	 * reasonを取得します。
	 * @return reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * reasonを設定します。
	 * @param reason reason
	 */
	public void setReason(final String reason) {
		this.reason = reason;
	}
}
