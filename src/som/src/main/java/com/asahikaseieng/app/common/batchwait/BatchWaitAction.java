/*
 * Created on 2014/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.batchwait;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.names.Names;
import com.asahikaseieng.dao.entity.procparam.ProcParam;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 
 * バッチ待ち画面Action
 * @author atts
 */
public class BatchWaitAction extends AbstractAction {

	private BatchWaitLogic batchWaitLogic;

	/**
	 * コンストラクタ.バッチ待ち画面Action
	 */
	public BatchWaitAction() {
		super();
	}

	/**
	 * バッチ待ち画面 初期表示処理
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
		BatchWaitForm frm = (BatchWaitForm) form;
		String titleName = "";

		// プロシージャ名取得
		Names namesBean = getProcName(frm.getScreenId() + frm.getNum());

		if (namesBean == null) {
			saveError(request, "errors.batch.no.proccd");
		} else {
			titleName = namesBean.getNmevalue1();
			frm.setTitleName(titleName);
		}

		return mapping.findForward("success");
	}

	/**
	 * 検索処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward search(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		BatchWaitForm frm = (BatchWaitForm) form;
		ActionRedirect redirect = new ActionRedirect();

		// プロシージャ名取得
		Names namesBean = getProcName(frm.getScreenId() + frm.getNum());
		String procCd = namesBean.getName01();

		if (StringUtils.isEmpty(procCd)) {
			saveError(request, "errors.batch.no.proccd");
			return mapping.findForward("success");
		}

		// パラメータ情報取得
		ProcParam procParamBean = batchWaitLogic.getProcParam(procCd);

		if (procParamBean == null) {
			if (namesBean.getName02().equals(
				String.valueOf(Constants.MENU_ID_AR_UPDATE))) {
				// 売掛更新処理画面へ
				redirect = new ActionRedirect(mapping.findForward("arUpdate"));
			} else if (namesBean.getName02().equals(
				String.valueOf(Constants.MENU_ID_AR_ROLLBACK))) {
				// 売掛更新ロールバック処理画面へ
				redirect = new ActionRedirect(mapping.findForward("arRollback"));
			} else if (namesBean.getName02().equals(
				String.valueOf(Constants.MENU_ID_CLAIM_UPDATE))) {
				// 請求更新処理画面へ
				redirect = new ActionRedirect(mapping
						.findForward("claimUpdateCsm"));
			} else if (namesBean.getName02().equals(
				String.valueOf(Constants.MENU_ID_CLAIM_ROLLBACK))) {
				// 請求更新ロールバック処理画面へ
				redirect = new ActionRedirect(mapping
						.findForward("claimRollbackCsm"));
			} else if (namesBean.getName02().equals(
				String.valueOf(Constants.MENU_ID_AP_UPDATE))) {
				// 買掛更新処理画面へ
				redirect = new ActionRedirect(mapping.findForward("apUpdate"));
			} else if (namesBean.getName02().equals(
				String.valueOf(Constants.MENU_ID_AP_ROLLBACK))) {
				// 買掛更新ロールバック処理画面へ
				redirect = new ActionRedirect(mapping.findForward("apRollback"));
			} else if (namesBean.getName02().equals(
				String.valueOf(Constants.MENU_ID_PAYMENT_UPDATE))) {
				// 支払更新処理画面へ
				redirect = new ActionRedirect(mapping
						.findForward("paymentUpdate"));
			} else if (namesBean.getName02().equals(
				String.valueOf(Constants.MENU_ID_PAYMENT_ROLLBACK))) {
				// 支払更新ロールバック処理画面へ
				redirect = new ActionRedirect(mapping
						.findForward("paymentRollback"));
			}

			redirect.addParameter("screenId", frm.getScreenId()); // (2)
			redirect.addParameter("num", frm.getNum());

			// 処理中画面へ
			return redirect;
		}

		return mapping.findForward("success");
	}

	/**
	 * プロシージャ名を取得
	 * @param procCd String
	 * @return Names
	 */
	public Names getProcName(final String procCd) {
		// プロシージャ名取得
		Names namesBean = batchWaitLogic.getNames(procCd);
		return namesBean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * batchWaitLogicを設定します。
	 * @param batchWaitLogic batchWaitLogic
	 */
	public void setBatchWaitLogic(final BatchWaitLogic batchWaitLogic) {
		this.batchWaitLogic = batchWaitLogic;
	}
}
