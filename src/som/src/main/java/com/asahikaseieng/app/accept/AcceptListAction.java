/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.accept;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.accept.AcceptList;
import com.asahikaseieng.dao.nonentity.accept.AcceptListPagerCondition;
import com.asahikaseieng.dao.nonentity.acceptforreport.AcceptListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 受入仕入検索画面 Actionクラス.
 * @author tosco
 * 
 */
public final class AcceptListAction extends AbstractSearchAction {

	/** 受入・仕入検索画面ロジッククラス */
	private AcceptListLogic acceptListLogic;

	private AcceptListListExcelDecorator acceptListListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public AcceptListAction() {
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

		AcceptListForm frm = (AcceptListForm) form;

		// 仕入区分コンボボックスの作成
		frm.setStockinDivisionCombo(acceptListLogic
				.createAcceptStockingDivisionCombobox());

		/* 初期検索無し */
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

		AcceptListForm frm = (AcceptListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<AcceptList>());

		/* 検索条件を取得 */
		AcceptListPagerCondition condition = (AcceptListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition
				.setSrhBuySubcontractOrderNo(frm.getSrhBuySubcontractOrderNo()); // 発注番号
		condition.setSrhSlipNo(frm.getSrhSlipNo()); // 仕入番号
		condition.setSrhItemCd(frm.getSrhItemCd()); // 品目コード
		condition.setSrhVenderCd(frm.getSrhVenderCd()); // 仕入先コード
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1()); // 他社コード１
		condition.setSrhChargeOrganizationCd(frm.getSrhChargeOrganizationCd()); // 担当部署コード
		condition.setSrhSuggestedDeliverlimitDateFrom(frm
				.getSrhSuggestedDeliverlimitDateFrom()); // 納品希望日FROM
		condition.setSrhSuggestedDeliverlimitDateTo(frm
				.getSrhSuggestedDeliverlimitDateTo()); // 納品希望日TO
		condition.setSrhAcceptDateFrom(frm.getSrhAcceptDateFrom()); // 受入日FROM
		condition.setSrhAcceptDateTo(frm.getSrhAcceptDateTo()); // 受入日TO
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd()); // 部署コード
		condition.setSrhTantoCd(frm.getSrhTantoCd()); // 発注者コード
		condition.setSrhOrderDivision(frm.getSrhOrderDivision()); // オーダー区分
		condition.setSrhLocationCd(frm.getSrhLocationCd()); // 納入先コード
		condition.setSrhStatus(frm.getSrhStatus()); // 購買ステータス
		condition.setSrhStatus2(frm.getSrhStatus2()); // 仕入ステータス
		condition.setSrhCategoryDivision(frm.getSrhCategoryDivision()); // 分類コード
		condition.setSrhMonthlyUpdCheck(frm.isSrhMonthlyUpdCheck()); // 月次更新済み

		/* 帳票(Excel)用に検索条件を保持 */
		AcceptListConditionForReport reportCondition = new AcceptListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		/* 検索 */
		frm.setSearchList(acceptListLogic.getSearchList(condition));

		return mapping.findForward("success");
	}

	/**
	 * 帳票処理(検索画面の帳票(Excel)ボタン押下時)
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

		AcceptListForm frm = (AcceptListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = acceptListListExcelDecorator.createReport(frm
				.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/* -------------------- setter -------------------- */

	/**
	 * 受入・仕入検索画面ロジッククラスを設定します。
	 * @param acceptListLogic 受入・仕入検索画面ロジッククラス
	 */
	public void setAcceptListLogic(final AcceptListLogic acceptListLogic) {
		this.acceptListLogic = acceptListLogic;
	}

	/**
	 * 受入・仕入検索帳票Excelクラスを設定します。
	 * @param acceptListListExcelDecorator 受入・仕入検索帳票Excelクラス
	 */
	public void setAcceptListListExcelDecorator(
			final AcceptListListExcelDecorator acceptListListExcelDecorator) {
		this.acceptListListExcelDecorator = acceptListListExcelDecorator;
	}

}
