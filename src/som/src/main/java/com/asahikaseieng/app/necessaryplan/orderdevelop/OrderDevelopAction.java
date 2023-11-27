/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.necessaryplan.orderdevelop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.procedurecall.ProNecOrderDevepolCallDto;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 
 * 発注点発注展開処理Action
 * @author tosco
 */
public class OrderDevelopAction extends AbstractAction {

	private OrderDevelopLogic calculationLogic;

	/**
	 * コンストラクタ.発注点発注展開処理Action
	 */
	public OrderDevelopAction() {
		super();
	}

	/**
	 * 発注点発注展開処理画面 初期表示処理
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
	 * 発注点発注展開処理
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

		OrderDevelopForm frm = (OrderDevelopForm) form;

		// 請求更新処理(PL/SQL)呼出
		ProNecOrderDevepolCallDto dto = calculationLogic.setProcedureDto(frm);
		String rtn = calculationLogic.callProcedure(dto);

		if (rtn.equals("")) {
			/* メッセージ */
			System.out.println("請求更新処理プロシージャ結果がブランク");
		}
		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("success");

	}

	/* -------------------- setter -------------------- */

	/**
	 * calculationLogicを設定します。
	 * @param calculationLogic calculationLogic
	 */
	public void setCalculationLogic(final OrderDevelopLogic calculationLogic) {
		this.calculationLogic = calculationLogic;
	}
}
