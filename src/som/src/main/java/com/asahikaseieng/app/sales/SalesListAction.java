/*
 * Created on 2009/02/27
 *
 * $copyright$
 * 売上トランザクション
 *
 */
package com.asahikaseieng.app.sales;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.sales.SalesList;
import com.asahikaseieng.dao.nonentity.sales.SalesListPagerCondition;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesInoutListForReport;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesListForReport;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesListReportCondition;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 売上トランザクション一覧 Actionクラス.
 * @author tosco
 * 
 */
public final class SalesListAction extends AbstractSearchAction {

	/** 売上共通ロジッククラス */
	private SalesCommonsLogic salesCommonsLogic;

	private SalesListLogic salesListLogic;

	private SalesListExcelDecorator salesListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public SalesListAction() {
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

		SalesListForm listForm = (SalesListForm) form;

		// 売上区分コンボボックス
		listForm.setCategoryCombo(salesCommonsLogic
				.createCategoryDivisionAllCombobox());

		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * 売上トランザクション検索処理(検索ボタン押下時)
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

		SalesListForm frm = (SalesListForm) form;

		// Excelダウンロードフラグクリア
		frm.setExcelDownloadFlg(false);

		// クリア
		frm.setSearchList(new ArrayList<SalesList>());

		// 検索条件を取得
		SalesListPagerCondition condition = (SalesListPagerCondition) frm
				.getPager().getPagerCondition();
		// 検索条件をセット
		condition.setSrhSalesNo(frm.getSrhSalesNo());
		condition.setSrhCategoryDivision(frm.getSrhCategoryDivision());
		condition.setSrhCancelFlg(frm.getSrhCancelFlg());
		condition.setSrhOrderNoFrom(frm.getSrhOrderNoFrom());
		condition.setSrhOrderNoTo(frm.getSrhOrderNoTo());
		condition.setSrhSalesDateFrom(frm.getSrhSalesDateFrom());
		condition.setSrhSalesDateTo(frm.getSrhSalesDateTo());
		String years = StringUtils.replace(frm.getSrhAccountYears(), "/", "");
		condition.setSrhAccountYears(years);
		
		
		String yearsTo = StringUtils.replace(frm.getSrhAccountYearsTo(), "/", "");
		condition.setSrhAccountYearsTo(yearsTo);
		
		condition.setSrhTmpUnitpriceFlg(frm.getSrhTmpUnitpriceFlg());
		condition.setSrhKeepFlag(frm.getSrhKeepFlag());
		condition.setSrhMonthlyUpdateFlag(frm.getSrhMonthlyUpdateFlag());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhChargeOrganizationCd(frm.getSrhChargeOrganizationCd());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());

		// 2010/01/14 検索条件追加
		condition.setSrhDeliveryCd(frm.getSrhDeliveryCd());
		condition.setSrhCustomerOrderNo(frm.getSrhCustomerOrderNo());

		/* 帳票出力用検索条件 */
		SalesListReportCondition reportCondition = new SalesListReportCondition();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// 検索
		frm.setSearchList(salesListLogic.getSearchList(condition));
		return mapping.findForward("success");
	}

	/**
	 * 正単価更新(検索画面の正単価更新ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward updateUnitPrice(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("updateUnitPrice.");
		}

		SalesListForm frm = (SalesListForm) form;

		// 正単価更新
		try {
			// 指図自動作成処理
			salesListLogic.updateSalseUnitprice(frm, getLoginInfo(request)
					.getTantoCd());
		} catch (LogicExceptionEx e) {
			salesListLogic.outPutErrorLog(frm.getErrorCd(), frm.getErrorMsg(),
				getLoginInfo(request).getTantoCd());
			String errMsg = "errors.sales.update.unitprice.error";
			if (errMsg.equals(e.getMessage())) {
				// 在庫更新に失敗
				saveError(request, errMsg);
				return mapping.getInputForward();
			} else {
				throw e;
			}
		}

		// メッセージ
		saveMessage(request, "message.sales.complete.update.unitprice");

		return mapping.findForward("reSearch");

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

		SalesListForm frm = (SalesListForm) form;

		// Excelダウンロードフラグクリア
		frm.setExcelDownloadFlg(false);

		List<SalesListForReport> list = salesListLogic.getListForReport(frm
				.getReportCondition());

		List<SalesInoutListForReport> listInout = salesListLogic
				.getInoutListForReport(frm.getReportCondition());

		/* Excel作成 */
		FileDownloadInfo info = salesListExcelDecorator.createReport(list,
			listInout);

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 売上共通ロジッククラスを設定します。
	 * @param salesCommonsLogic 売上ロジッククラス
	 */
	public void setSalesCommonLogic(final SalesCommonsLogic salesCommonsLogic) {
		this.salesCommonsLogic = salesCommonsLogic;
	}

	/**
	 * 売上検索ロジッククラスを設定します。
	 * @param salesListLogic 売上検索ロジッククラス
	 */
	public void setSalesListLogic(final SalesListLogic salesListLogic) {
		this.salesListLogic = salesListLogic;
	}

	/**
	 * 売上Excel出力用
	 * @param salesListExcelDecorator 売上Excel出力用
	 */
	public void setSalesListExcelDecorator(
			final SalesListExcelDecorator salesListExcelDecorator) {
		this.salesListExcelDecorator = salesListExcelDecorator;
	}

}
