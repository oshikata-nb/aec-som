/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.productionplan;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.production.ProductionList;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanCalendar;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanForReport;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanForReportCondition;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 生産計画入力一覧 Actionクラス.
 * @author tosco
 * 
 */
public final class ProductionPlanAction extends AbstractSearchAction {
	/** 生産計画入力一覧 ロジッククラス */
	private ProductionPlanLogic productionPlanLogic;

	private ProductionPlanExcelDecorator productionPlanExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public ProductionPlanAction() {
		super();
	}

	/**
	 * 初期処理(メニューから遷移時)
	 * 
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

		// フォーム取得
		ProductionPlanForm frm = (ProductionPlanForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PRODUCTION_PLAN,
			Constants.TAB_ID_PRODUCTION_PLAN_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 生産工場コンボボックス
		frm
				.setProductionLineCombo(productionPlanLogic
						.createLineCombobox(true));

		// 初期表示時は検索を行わない
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
	 * 印刷処理(印刷ボタン押下時)
	 * 
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
		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		// formを生産計画入力一覧 Formクラスにキャスト
		ProductionPlanForm frm = (ProductionPlanForm) form;

		// 一覧検索結果をクリア
		frm.setSearchList(new ArrayList<ProductionList>());

		// 検索条件の取得
		RepProductionPlanForReportCondition condition = new RepProductionPlanForReportCondition();

		// 検索条件をセット
		condition.setSrhShipperDivision(frm.getSrhShipperDivision());
		condition.setSrhTypeDivision(frm.getSrhTypeDivision());
		condition.setSrhProductionLine(frm.getSrhProductionLine());
		condition.setSrhOrderLet(frm.getSrhOrderLet());

		List<RepProductionPlanForReport> listAll = new ArrayList<RepProductionPlanForReport>();
		List<RepProductionPlanForReport> listKao = new ArrayList<RepProductionPlanForReport>();
		List<RepProductionPlanCalendar> calendarList;

		try {
			// 月間生産計画表表示データ取得
			listAll = productionPlanLogic.getReportListAll(condition);

			// 荷主区分が全てか花王の場合
			if (condition.getSrhShipperDivision().equals("0")
					|| condition.getSrhShipperDivision().equals("2")) {
				// 荷主区分を花王のみとして処理
				condition.setSrhShipperDivision("2");
				listKao = productionPlanLogic.getReportListKao(condition);
			}

		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata");
			return mapping.getInputForward();
		}

		try {
			// 処理日付のカレンダーデータ取得
			calendarList = productionPlanLogic.getCalendarList(frm
					.getSrhOrderLet());
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.production.years.not.exist.calendar");
			return mapping.getInputForward();
		}

		FileDownloadInfo info = null;

		/* 生産計画表出力処理 */
		info = productionPlanExcelDecorator.createReport(loginUserId, listAll,
			listKao, calendarList, request.getRemoteAddr());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		// ExcelダウンロードフラグＯＮ
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/**
	 * フォームに表示されている項目のクリア処理.
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clear(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}

		// formを仕入一覧 Formクラスにキャスト
		ProductionPlanForm frm = (ProductionPlanForm) form;

		// フォームのclearメソッドを使用し、フォームをクリアする
		frm.clear();

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 生産計画入力一覧 ロジッククラスを設定します。
	 * @param productionPlanLogic 生産計画入力一覧 ロジッククラス
	 */
	public void setProductionPlanLogic(
			final ProductionPlanLogic productionPlanLogic) {
		this.productionPlanLogic = productionPlanLogic;
	}

	/**
	 * 生産計画表用エクセルデコレイターを設定します。
	 * @param productionPlanExcelDecorator 生産計画表用エクセルデコレイター
	 */
	public void setProductionPlanExcelDecorator(
			final ProductionPlanExcelDecorator productionPlanExcelDecorator) {
		this.productionPlanExcelDecorator = productionPlanExcelDecorator;
	}

}
