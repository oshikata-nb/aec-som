/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asahikaseieng.Constants;
import com.asahikaseieng.struts.AbstractAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * ログアウト Actionクラス.
 * @author jbd
 */
public class LogoutAction extends AbstractAction {

	/**
	 * デフォルトコンストラクタ.
	 */
	public LogoutAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward unspecified(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		request.getSession(false).removeAttribute(Constants.LOGIN_KEY);

		return mapping.findForward("success");
	}
}
