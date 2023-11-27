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
import com.asahikaseieng.dao.entity.inoutmonthlyreportstatus.InoutMonthlyReportStatus;
import com.asahikaseieng.dao.entity.inoutmonthlyreportstatus.InoutMonthlyReportStatusDao;
import com.asahikaseieng.dao.nonentity.inoutmonthlyreportforreport.RepMothlyLotInventory;
import com.asahikaseieng.dao.nonentity.inoutmonthlyreportforreport.RepMothlyLotInventoryDao;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 受払月報 Actionクラス
 * @author t1344224
 */
public final class InoutMonthlyReportAction extends AbstractSearchAction {

	/** 受払月報ＥＸＣＥＬファイル作成ロジッククラス */
	private InoutMonthlyReportExcelDecorator inoutMonthlyReportExcelDecorator;

	private RepMothlyLotInventoryDao repMothlyLotInventoryDao;

	private InoutMonthlyReportStatusDao inoutMonthlyReportStatusDao;

	/**
	 * コンストラクタ.
	 */
	public InoutMonthlyReportAction() {
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
		InoutMonthlyReportForm frm = (InoutMonthlyReportForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm,
			Constants.MENU_ID_INOUT_MONTHLY_REPORT,
			Constants.TAB_ID_INOUT_MONTHLY_REPORT_DETAIL);

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
	 * 受払月報TEMP作成処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reporttemp(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("reporttemp.");
		}

		InoutMonthlyReportForm frm = (InoutMonthlyReportForm) form;

		String tantoCd = getLoginInfo(request).getTantoCd();
		String tantoNm = getLoginInfo(request).getTantoNm();

		// 受払月報ステータス取得
		InoutMonthlyReportStatus statusBean = inoutMonthlyReportStatusDao
				.getEntity(tantoCd);

		if (statusBean != null) {
			if (statusBean.getStatus().equals(new BigDecimal("1"))) {
				// ステータスが実行中の場合、処理を抜ける
				saveError(request, "message.inoutmonthlyreport.error");
				return mapping.findForward("success");
			}
		}

		// 対象月の月次受払実績データがあるか検索
		RepMothlyLotInventory monlhlyInoutRecordFromBean = repMothlyLotInventoryDao
				.getTargetMonth(frm.getSrhDateFrom());

		RepMothlyLotInventory monlhlyInoutRecordToBean = repMothlyLotInventoryDao
				.getTargetMonth(frm.getSrhDateTo());

		if (monlhlyInoutRecordFromBean != null
				&& monlhlyInoutRecordToBean != null) {

			// 受払月報ステータスに1:実行中をセット
			inoutMonthlyReportExcelDecorator.setStatus(frm.getSrhDateFrom(),
				frm.getSrhDateTo(), tantoCd, "1");

			// データ検索し受払月報TEMPにセット
			inoutMonthlyReportExcelDecorator.createTemp(frm.getSrhDateFrom(),
				frm.getSrhDateTo(), tantoCd, tantoNm, AecDateUtils
						.getCurrentTimestamp());

			// 作成完了メッセージの登録
			ActionMessages messages = new ActionMessages();
			messages.add("", new ActionMessage(
					"message.inoutmonthlyreport.complete"));
			saveMessages(request, messages);

			// 受払月報ステータスに2:完了をセット
			inoutMonthlyReportExcelDecorator.setStatus(frm.getSrhDateFrom(),
				frm.getSrhDateTo(), tantoCd, "2");

		} else {
			// 月末在庫データが無い場合
			saveError(request, "errors.nodata");
		}

		return mapping.findForward("success");
	}

	/**
	 * 受払月報出力処理.
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

		InoutMonthlyReportForm frm = (InoutMonthlyReportForm) form;

		String tantoCd = getLoginInfo(request).getTantoCd();
		String tantoNm = getLoginInfo(request).getTantoNm();
		FileDownloadInfo info = null;

		// 受払月報TEMPから受払月報を作成
		info = inoutMonthlyReportExcelDecorator.createReport(frm
				.getSrhDateFrom(), frm.getSrhDateTo(), tantoCd, tantoNm,
			AecDateUtils.getCurrentTimestamp());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		frm.setExcelDownloadFlg(Boolean.TRUE);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 受払月報ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param inoutMonthlyReportExcelDecorator 受払月報ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setInoutMonthlyReportExcelDecorator(
			final InoutMonthlyReportExcelDecorator inoutMonthlyReportExcelDecorator) {
		this.inoutMonthlyReportExcelDecorator = inoutMonthlyReportExcelDecorator;
	}

	/**
	 * 受払月報検索用Dao
	 * @param repMothlyLotInventoryDao 受払月報検索用Dao
	 */
	public void setRepMothlyLotInventoryDao(
			final RepMothlyLotInventoryDao repMothlyLotInventoryDao) {
		this.repMothlyLotInventoryDao = repMothlyLotInventoryDao;
	}

	/**
	 * inoutMonthlyReportStatusDaoを設定します。
	 * @param inoutMonthlyReportStatusDao inoutMonthlyReportStatusDao
	 */
	public void setInoutMonthlyReportStatusDao(
			final InoutMonthlyReportStatusDao inoutMonthlyReportStatusDao) {
		this.inoutMonthlyReportStatusDao = inoutMonthlyReportStatusDao;
	}

}
