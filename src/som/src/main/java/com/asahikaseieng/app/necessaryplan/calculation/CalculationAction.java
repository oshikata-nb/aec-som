/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.necessaryplan.calculation;

import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.procedurecall.ProNecCalculationCallDto;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 
 * 原材料所要量計算Action
 * @author tosco
 */
public class CalculationAction extends AbstractAction {

	private CalculationLogic calculationLogic;

	/**
	 * コンストラクタ.原材料所要量計算Action
	 */
	public CalculationAction() {
		super();
	}

	/**
	 * 原材料所要量計算画面 初期表示処理
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
	 * 原材料所要量計算
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

		CalculationForm frm = (CalculationForm) form;

		// 展開開始日
		frm.setStartDate(getFormatDate(frm.getStrStartDate()));
		// 展開終了日
		frm.setEndDate(getFormatDate(frm.getStrEndDate()));

		// 請求更新処理(PL/SQL)呼出
		ProNecCalculationCallDto dto = calculationLogic.setProcedureDto(frm);
		String rtn = calculationLogic.callProcedure(dto);

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
	 * calculationLogicを設定します。
	 * @param calculationLogic calculationLogic
	 */
	public void setCalculationLogic(final CalculationLogic calculationLogic) {
		this.calculationLogic = calculationLogic;
	}
}
