/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
 * 月次受払更新処理 Actionクラス.
 * @author t0011036
 */
public final class InoutMonthlyUpdateAction extends AbstractAction {

	private InoutMonthlyUpdateLogic inoutMonthlyUpdateLogic;

	/**
	 * コンストラクタ.
	 */
	public InoutMonthlyUpdateAction() {
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

		InoutMonthlyUpdateForm frm = (InoutMonthlyUpdateForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm,
			Constants.MENU_ID_INOUT_MONTHLY_UPDATE,
			Constants.TAB_ID_INOUT_MONTHLY_UPDATE_DETAIL);

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

		InoutMonthlyUpdateForm frm = (InoutMonthlyUpdateForm) form;

		String inputDate = frm.getSrhStrInputYear()
				+ AecNumberUtils.decimalFormat(new BigDecimal(frm
						.getSrhStrInputMonth()), "00");

		/* 前月取得 */
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(frm.getSrhStrInputYear()), Integer
				.parseInt(frm.getSrhStrInputMonth()) - 2, 1);

		/* Calendar--->String(yyyyMM) */
		Date d = cal.getTime();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMM");
		String inputPrevDate = s.format(d);

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

		/* 受払履歴 */
		if (inoutMonthlyUpdateLogic.getInoutCount(inputDateFrom, inputDateTo) == 0) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.inout.monthly.update.error1"));
		}

		/* 月次更新 */
		if (0 < inoutMonthlyUpdateLogic.getMonthlyCount(inputDate)) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.inout.monthly.update.error2"));
		}

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.findForward("success");
		}

		/* 月次更新作成 */
		if (inoutMonthlyUpdateLogic.insertMonthly(inputDate, inputPrevDate,
			inputDateFrom, inputDateTo, tantoCd)) {
			/* 受払履歴更新 */
			if (inoutMonthlyUpdateLogic.updateInout(inputDateFrom, inputDateTo,
				tantoCd)) {
				saveMessage(request, "message.complete.regist");
			}
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * inoutMonthlyUpdateLogicを設定します。
	 * @param inoutMonthlyUpdateLogic inoutMonthlyUpdateLogic
	 */
	public void setInoutMonthlyUpdateLogic(
			final InoutMonthlyUpdateLogic inoutMonthlyUpdateLogic) {
		this.inoutMonthlyUpdateLogic = inoutMonthlyUpdateLogic;
	}
}
