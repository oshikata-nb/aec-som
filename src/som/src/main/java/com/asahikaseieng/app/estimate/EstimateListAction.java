/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.estimate;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.estimatelist.EstimateList;
import com.asahikaseieng.dao.nonentity.estimatelist.EstimateListPagerCondition;
import com.asahikaseieng.dao.nonentity.estimatelistforreport.EstimateListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 見積/単価一覧 Actionクラス.
 * @author t0011036
 */
public final class EstimateListAction extends AbstractSearchAction {

	private EstimateListLogic estimateListLogic;

	private EstimateListExcelDecorator estimateListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public EstimateListAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		EstimateListForm frm = (EstimateListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<EstimateList>());

		/* 検索条件を取得 */
		EstimateListPagerCondition condition = (EstimateListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhEstimateNo(frm.getSrhEstimateNo());
		condition.setStrSrhEstimateInputDateFrom(frm
				.getStrSrhEstimateInputDateFrom());
		condition.setStrSrhEstimateInputDateTo(frm
				.getStrSrhEstimateInputDateTo());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());
		condition.setStrSrhEstimateValidDateFrom(frm
				.getStrSrhEstimateValidDateFrom());
		condition.setStrSrhEstimateValidDateTo(frm
				.getStrSrhEstimateValidDateTo());
		condition.setSrhEstimateStatus(frm.getSrhEstimateStatus());

		/* 帳票(Excel)用に検索条件を保持 */
		EstimateListConditionForReport reportCondition = new EstimateListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(estimateListLogic.getList(condition));

		return mapping.findForward("success");
	}

	/**
	 * クリア処理.
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

		EstimateListForm frm = (EstimateListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
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

		EstimateListForm frm = (EstimateListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = estimateListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/**
	 * 新規処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		return mapping.findForward("newPage");
	}

	/* -------------------- setter -------------------- */

	/**
	 * estimateListExcelDecoratorを設定します。
	 * @param estimateListExcelDecorator estimateListExcelDecorator
	 */
	public void setEstimateListExcelDecorator(
			final EstimateListExcelDecorator estimateListExcelDecorator) {
		this.estimateListExcelDecorator = estimateListExcelDecorator;
	}

	/**
	 * estimateListLogicを設定します。
	 * @param estimateListLogic estimateListLogic
	 */
	public void setEstimateListLogic(final EstimateListLogic estimateListLogic) {
		this.estimateListLogic = estimateListLogic;
	}
}
