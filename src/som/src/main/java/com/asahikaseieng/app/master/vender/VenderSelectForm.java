/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.vender;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 取引先選択 Formクラス.
 * @author t0011036
 */
public final class VenderSelectForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public VenderSelectForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		return errors;
	}
}
