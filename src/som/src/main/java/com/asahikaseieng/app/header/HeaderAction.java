/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.header;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asahikaseieng.struts.AbstractAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * ヘッダー Actionクラス.
 * @author jbd
 */
public final class HeaderAction extends AbstractAction {

	/**
	 * コンストラクタ.
	 */
	public HeaderAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		HeaderForm frm = (HeaderForm) form;

		/* ユーザー情報をセッションから取り出す */
		frm.setLoginInfo(getLoginInfo(request));

		return mapping.findForward("success");
	}
}
