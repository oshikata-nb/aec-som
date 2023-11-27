/*
 * Created on 2007/12/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.picking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asahikaseieng.Constants;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 出荷指図詳細 Actionクラス.
 * @author jbd
 */
public final class PickingAction extends AbstractAction {

//	private PickingLogic pickingLogic;
	private PickingExcelDecorator pickingExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public PickingAction() {
		super();
	}


	/**
	 * EXCEL作成処理.
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

		PickingForm frm = (PickingForm) form;

		/* Excel作成 */
		FileDownloadInfo info = pickingExcelDecorator
			.createReport(getLoginInfo(request).getTantoCd(), AecDateUtils.getCurrentTimestamp());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY, info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */
	/**
	 * pickingExcelDecoratorを設定します。
	 * @param pickingExcelDecorator pickingExcelDecorator
	 */
	public void setPickingReportDecorator(
			final PickingExcelDecorator pickingExcelDecorator) {
		this.pickingExcelDecorator = pickingExcelDecorator;
	}

//	/**
//	 * pickingLogicを設定します。
//	 * @param pickingLogic pickingLogic
//	 */
//	public void setPickingLogic(
//			final PickingLogic pickingLogic) {
//		this.pickingLogic = pickingLogic;
//	}

}
