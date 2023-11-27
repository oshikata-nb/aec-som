/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage.mypageset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractAction;

/**
 * マイページ Actionクラス.
 * @author jbd
 */
public final class MyPageSetAction extends AbstractAction {

	/**
	 * コンストラクタ.
	 */
	public MyPageSetAction() {
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

		MyPageSetForm frm = (MyPageSetForm) form;

		// マイページ設定フラグを設定
		frm.setMyPageSetFlg("1");

		if (StringUtils.isEmpty(frm.getTantoCd())) {
			// 担当者コード設定
			frm.setTantoCd(getLoginInfo(request).getTantoCd());
		}

		return mapping.findForward("success");
	}

}
