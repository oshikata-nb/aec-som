package com.asahikaseieng.app.monthlyvender;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.monthlyvenderforreport.RepMonthlyVender;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 得意先別管理月報 Actionクラス
 * @author t1344224
 */
public final class MonthlyVenderAction extends AbstractSearchAction {

	private MonthlyVenderLogic monthlyVenderLogic;

	private MonthlyVenderExcelDecorator monthlyVenderExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public MonthlyVenderAction() {
		super();
	}

	/**
	 * 初期処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}
		MonthlyVenderForm frm = (MonthlyVenderForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_MONTHLY_VENDER,
			Constants.TAB_ID_SALES_MONTHLY_VENDER);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");
	}

	/**
	 * 検索処理(検索ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}
		return mapping.findForward("success");
	}

	/**
	 * 受払月報作成処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		MonthlyVenderForm frm = (MonthlyVenderForm) form;

		String tantoNm = getLoginInfo(request).getTantoNm();
		FileDownloadInfo info = null;

		List<RepMonthlyVender> list = monthlyVenderLogic.getList(frm
				.getSrhOrganizationCd(), frm.getSrhDateFrom(), frm
				.getSrhDateTo());

		if (!list.isEmpty()) {

			// 得意先別管理月報を作成
			info = monthlyVenderExcelDecorator.createReport(list, frm
					.getSrhDateFrom(), frm.getSrhDateTo(), tantoNm,
				AecDateUtils.getCurrentTimestamp());
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			frm.setExcelDownloadFlg(Boolean.TRUE);
		} else {
			// データが無い場合
			saveError(request, "errors.nodata");

		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * monthlyVenderLogicを設定します。
	 * @param monthlyVenderLogic monthlyVenderLogic
	 */
	public void setMonthlyVenderLogic(
			final MonthlyVenderLogic monthlyVenderLogic) {
		this.monthlyVenderLogic = monthlyVenderLogic;
	}

	/**
	 * 得意先別管理月報ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param monthlyVenderExcelDecorator 得意先別管理月報ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setMonthlyVenderExcelDecorator(
			final MonthlyVenderExcelDecorator monthlyVenderExcelDecorator) {
		this.monthlyVenderExcelDecorator = monthlyVenderExcelDecorator;
	}
}
