/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.necessaryplan.purchasedecide;

import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.procedurecall.ProNecPurchaseDecideCallDto;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 
 * 購買計画確定Action
 * @author tosco
 */
public class PurchaseDecideAction extends AbstractAction {

	private PurchaseDecideLogic purchaseDecideLogic;

	/**
	 * コンストラクタ.購買計画確定Action
	 */
	public PurchaseDecideAction() {
		super();
	}

	/**
	 * 購買計画確定画面 初期表示処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		return mapping.findForward("success");
	}

	/**
	 * 購買計画確定
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}

		PurchaseDecideForm frm = (PurchaseDecideForm) form;

		// 発注開始日
		frm.setOrderStartDate(getFormatDate(frm.getStrOrderStartDate()));
		// 発注終了日
		frm.setOrderEndDate(getFormatDate(frm.getStrOrderEndDate()));
		// 納期開始日
		frm.setDeadlineStartDate(getFormatDate(frm.getStrDeliverStartDate()));
		// 納期終了日
		frm.setDeadlineEndDate(getFormatDate(frm.getStrDeliverEndDate()));

		// 請求更新処理(PL/SQL)呼出
		ProNecPurchaseDecideCallDto dto = purchaseDecideLogic.setProcedureDto(frm);
		String rtn = purchaseDecideLogic.callProcedure(dto);

		if (rtn.equals("")) {
			/* メッセージ */
			System.out.println("請求更新処理プロシージャ結果がブランク");
		}
		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("success");

	}

	/**
	 * 日にちを正しい年月日に補正した請求締め日取得
	 * 
	 * @param  strDate 画面文字列型日付
	 * @return Date    Date型日付
	 */
	private Date getFormatDate(final String strDate) {
		if (StringUtils.isEmpty(strDate)) {
			return null;
		}

		// スラッシュ分割
		String[] ymd = strDate.split("/");
		if (ymd.length != 3) {
			return null;
		}
		int year = Integer.parseInt(ymd[0]);
		int month = Integer.parseInt(ymd[1]);
		int day = Integer.parseInt(ymd[2]);

		Calendar cal = Calendar.getInstance();

		// 画面請求締め日
		cal.set(year, month - 1, day);

		// 月が変わってしまう場合
		if (month - 1 != cal.get(Calendar.MONTH)) {
			cal.set(year, month - 1, 1);
			// 画面年月の月末日取得
			int endDay = cal.getActualMaximum(Calendar.DATE);
			cal.set(year, month - 1, endDay);
		}

		Date formatDate = new Date(cal.getTimeInMillis());

		return formatDate;
	}

	/* -------------------- setter -------------------- */

	/**
	 * purchaseDecideLogicを設定します。
	 * @param purchaseDecideLogic purchaseDecideLogic
	 */
	public void setPurchaseDecideLogic(final PurchaseDecideLogic purchaseDecideLogic) {
		this.purchaseDecideLogic = purchaseDecideLogic;
	}

}
