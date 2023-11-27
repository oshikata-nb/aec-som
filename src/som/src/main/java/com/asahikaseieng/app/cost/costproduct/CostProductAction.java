/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.cost.costproduct;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asahikaseieng.dao.nonentity.procedurecall.ProCostProductCallDto;
import com.asahikaseieng.struts.AbstractAction;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * 原価積上処理Action
 * @author tosco
 */
public class CostProductAction extends AbstractAction {

	private CostProductLogic costProductLogic;

	/**
	 * コンストラクタ.原価積上処理Action
	 */
	public CostProductAction() {
		super();
	}

	/**
	 * 原価積上処理画面 初期表示処理
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

		CostProductForm frm = (CostProductForm) form;

		// システム日付
		Calendar sysCal = Calendar.getInstance();
		// 日付を表示用に変換
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = sdf.format(sysCal.getTime());
		/* 処方有効日：初期表示 */
		frm.setStrRecipeDate(strDate);

		return mapping.findForward("success");
	}

	/**
	 * 原価積上処理
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

		CostProductForm frm = (CostProductForm) form;

		// 展開開始日
		frm.setRecipeDate(getFormatDate(frm.getStrRecipeDate()));

		// 請求更新処理(PL/SQL)呼出
		ProCostProductCallDto dto = costProductLogic.setProcedureDto(frm);
		String rtn = costProductLogic.callProcedure(dto);

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
	 * @param costProductLogic costProductLogic
	 */
	public void setCostProductLogic(final CostProductLogic costProductLogic) {
		this.costProductLogic = costProductLogic;
	}
}
