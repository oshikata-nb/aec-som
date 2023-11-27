/*
 * Created on 2009/03/26
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.materialrinput;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputList;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputListPagerCondition;
import com.asahikaseieng.dao.nonentity.materialrinputforreport.MaterialRinputListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 外注原材料投入実績一覧 Actionクラス.
 * @author tosco
 * 
 */
public final class MaterialRinputListAction extends AbstractSearchAction {

	/** 外注原材料投入実績一覧ロジッククラス */
	private MaterialRinputListLogic materialRinputListLogic;

	/** 外注原材料投入実績一覧帳票Excelクラス */
	private MaterialRinputListExcelDecorator materialRinputListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputListAction() {
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

		// 初期表示
		MaterialRinputListForm frm = (MaterialRinputListForm) form;
		// 部署コード
		frm.setSrhOrganizationCd(getLoginInfo(request).getOrganizationCd());
		// 部署名称
		frm.setSrhOrganizationName(getLoginInfo(request).getOrganizationName());
		// 発注者コード
		frm.setSrhTantoCd(getLoginInfo(request).getTantoCd());
		// 発注者名称
		frm.setSrhTantoNm(getLoginInfo(request).getTantoNm());

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

		MaterialRinputListForm frm = (MaterialRinputListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<MaterialRinputList>());

		/* 検索条件を取得 */
		MaterialRinputListPagerCondition condition = (MaterialRinputListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition
				.setSrhBuySubcontractOrderNo(frm.getSrhBuySubcontractOrderNo()); // 発注番号
		condition.setSrhOrderDateFrom(frm.getSrhOrderDateFrom()); // 発注日FROM
		condition.setSrhOrderDateTo(frm.getSrhOrderDateTo()); // 発注日TO
		condition.setSrhSuggestedDeliverlimitDateFrom(frm
				.getSrhSuggestedDeliverlimitDateFrom()); // 納品希望日FROM
		condition.setSrhSuggestedDeliverlimitDateTo(frm
				.getSrhSuggestedDeliverlimitDateTo()); // 納品希望日TO
		condition.setSrhLocationCd(frm.getSrhLocationCd()); // 納入先コード
		condition.setSrhVenderCd(frm.getSrhVenderCd()); // 仕入先コード
		condition.setSrhItemCd(frm.getSrhItemCd()); // 品目コード
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1()); // 他社コード１
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd()); // 部署コード
		condition.setSrhTantoCd(frm.getSrhTantoCd()); // 発注者コード
		condition.setSrhOrderSheetNo(frm.getSrhOrderSheetNo()); // 発注書No
		condition.setSrhChargeOrganizationCd(frm.getSrhChargeOrganizationCd()); // 担当部署コード
		condition.setNotInputFlg(frm.getNotInputFlg());
		/* 帳票(Excel)用に検索条件を保持 */
		MaterialRinputListConditionForReport reportCondition = new MaterialRinputListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		/* 検索 */
		frm.setSearchList(materialRinputListLogic.getSearchList(condition));

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

		MaterialRinputListForm frm = (MaterialRinputListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = materialRinputListExcelDecorator
				.createReport(frm.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/* -------------------- setter -------------------- */

	/**
	 * 外注原材料投入実績一覧ロジッククラスを設定します。
	 * @param materialRinputListLogic 外注原材料投入実績一覧ロジッククラス
	 */
	public void setMaterialRinputListLogic(
			final MaterialRinputListLogic materialRinputListLogic) {
		this.materialRinputListLogic = materialRinputListLogic;
	}

	/**
	 * materialRinputListExcelDecoratorを設定します。
	 * @param materialRinputListExcelDecorator materialRinputListExcelDecorator
	 */
	public void setMaterialRinputListExcelDecorator(
			final MaterialRinputListExcelDecorator materialRinputListExcelDecorator) {
		this.materialRinputListExcelDecorator = materialRinputListExcelDecorator;
	}

}
