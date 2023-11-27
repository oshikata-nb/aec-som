/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 月次受払ロールバック処理 Actionクラス.
 * @author t0011036
 */
public final class InoutMonthlyCancelAction extends AbstractAction {

	private InoutMonthlyCancelLogic inoutMonthlyCancelLogic;

	/**
	 * コンストラクタ.
	 */
	public InoutMonthlyCancelAction() {
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

		InoutMonthlyCancelForm frm = (InoutMonthlyCancelForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm,
			Constants.MENU_ID_INOUT_MONTHLY_CANCEL,
			Constants.TAB_ID_INOUT_MONTHLY_CANCEL_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}

		InoutMonthlyCancelForm frm = (InoutMonthlyCancelForm) form;

		String inputDate = frm.getSrhStrInputYear()
				+ AecNumberUtils.decimalFormat(new BigDecimal(frm
						.getSrhStrInputMonth()), "00");
		String inputDateFrom = frm.getSrhStrInputYear()
				+ "/"
				+ AecNumberUtils.decimalFormat(new BigDecimal(frm
						.getSrhStrInputMonth()), "00") + "/" + "01";
		String inputDateTo = frm.getSrhStrInputYear()
				+ "/"
				+ AecNumberUtils.decimalFormat(new BigDecimal(frm
						.getSrhStrInputMonth()), "00") + "/" + "31";
		String tantoCd = getLoginInfo(request).getTantoCd();

		/* データ存在チェック */
		ActionMessages messages = new ActionMessages();

		/* 月次更新 */
		if (inoutMonthlyCancelLogic.getMonthlyCount(inputDate) == 0) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.inout.monthly.cancel.error1"));
		}

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.findForward("success");
		}

		/* 月次更新削除 */
		if (inoutMonthlyCancelLogic.deleteMonthly(inputDate)) {
			/* 受払履歴更新取消 */
			if (inoutMonthlyCancelLogic.updateInoutCancel(inputDateFrom,
				inputDateTo, tantoCd)) {
				saveMessage(request, "message.complete.regist");
			}
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * inoutMonthlyCancelLogicを設定します。
	 * @param inoutMonthlyCancelLogic inoutMonthlyCancelLogic
	 */
	public void setInoutMonthlyCancelLogic(
			final InoutMonthlyCancelLogic inoutMonthlyCancelLogic) {
		this.inoutMonthlyCancelLogic = inoutMonthlyCancelLogic;
	}
}
