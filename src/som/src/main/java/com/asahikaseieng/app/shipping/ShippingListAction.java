/*
 * Created on 2009/02/02
 *
 * $copyright$
 * 出荷指図
 *
 */
package com.asahikaseieng.app.shipping;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.shipping.ShippingList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingListPagerCondition;
import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingListConditionForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 出荷指図一覧 Actionクラス.
 * @author tosco
 * 
 */
public final class ShippingListAction extends AbstractSearchAction {

	private ShippingListLogic shippingListLogic;

	/** 出荷指図共通ロジッククラス */
	private ShippingCommonsLogic shippingCommonsLogic;

	/** 帳票Excel出力 */
	private ShippingListExcelDecorator shippingListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public ShippingListAction() {
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
		ShippingListForm listForm = (ShippingListForm) form;

		// 運送会社コンボボックス
		listForm.setCarryCombo(shippingCommonsLogic.createCarryAllCombobox());

		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * 出荷指図検索処理(検索ボタン押下時)
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

		ShippingListForm frm = (ShippingListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<ShippingList>());

		/* 検索条件を取得 */
		ShippingListPagerCondition condition = (ShippingListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhShippingNo(frm.getSrhShippingNo());
		condition.setSrhScheduledShippingDateFrom(frm
				.getSrhScheduledShippingDateFrom());
		condition.setSrhScheduledShippingDateTo(frm
				.getSrhScheduledShippingDateTo());
		condition.setSrhOrderNoFrom(frm.getSrhOrderNoFrom());
		condition.setSrhOrderNoTo(frm.getSrhOrderNoTo());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhDeliveryCd(frm.getSrhDeliveryCd());
		condition.setSrhShippingStatus(frm.getSrhShippingStatus());
		condition.setSrhCarryCd(frm.getSrhCarryCd());
		condition.setSrhLocationCd(frm.getSrhLocationCd());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());

		/* 帳票(Excel)用に検索条件を保持 */
		ShippingListConditionForReport reportCondition = new ShippingListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		/* 検索 */
		frm.setSearchList(shippingListLogic.getSearchList(condition));
		return mapping.findForward("success");
	}

	/**
	 * 確定取消処理(検索画面の確定取消ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward fixCancel(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("fixCancel.");
		}
		ShippingListForm frm = (ShippingListForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			/* 確定取消処理を実行 */
			shippingListLogic.cancelFix(frm.getSearchList(), tantoCd);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.shipping.fix.cancel");

		return mapping.findForward("reSearch");

	}

	/**
	 * 帳票処理(検索画面の帳票ボタン押下時)
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

		ShippingListForm frm = (ShippingListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = shippingListExcelDecorator.createReport(frm
				.getReportCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
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

		ShippingListForm frm = (ShippingListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 出荷指図共通ロジッククラスを設定します。
	 * @param shippingCommonsLogic 出荷指図共通ロジッククラス
	 */
	public void setShippingCommonLogic(
			final ShippingCommonsLogic shippingCommonsLogic) {
		this.shippingCommonsLogic = shippingCommonsLogic;
	}

	/**
	 * shippingListLogicを設定します。
	 * @param shippingListLogic shippingListLogic
	 */
	public void setShippingListLogic(final ShippingListLogic shippingListLogic) {
		this.shippingListLogic = shippingListLogic;
	}

	/**
	 * shippingListExcelDecoratorを設定します。
	 * @param shippingListExcelDecorator shippingListExcelDecorator
	 */
	public void setShippingListExcelDecorator(
			final ShippingListExcelDecorator shippingListExcelDecorator) {
		this.shippingListExcelDecorator = shippingListExcelDecorator;
	}

}
