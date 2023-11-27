/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.remark;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.remarklist.RemarkList;
import com.asahikaseieng.dao.nonentity.master.remarklist.RemarkListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.remarklistforreport.RemarkListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 備考一覧 Actionクラス.
 * @author t0011036
 */
public final class RemarkListAction extends AbstractSearchAction {

	private RemarkListLogic remarkListLogic;

	private RemarkListExcelDecorator remarkListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public RemarkListAction() {
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

		RemarkListForm frm = (RemarkListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<RemarkList>());

		/* 検索条件を取得 */
		RemarkListPagerCondition condition = (RemarkListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhVenderDivision(frm.getSrhVenderDivision());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhDeliveryCd(frm.getSrhDeliveryCd());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());

		/* 帳票(Excel)用に検索条件を保持 */
		RemarkListConditionForReport reportCondition = new RemarkListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(remarkListLogic.getList(condition));

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

		RemarkListForm frm = (RemarkListForm) form;

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

		RemarkListForm frm = (RemarkListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = remarkListExcelDecorator.createReport(frm
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
	 * remarkListExcelDecoratorを設定します。
	 * @param remarkListExcelDecorator remarkListExcelDecorator
	 */
	public void setRemarkListExcelDecorator(
			final RemarkListExcelDecorator remarkListExcelDecorator) {
		this.remarkListExcelDecorator = remarkListExcelDecorator;
	}

	/**
	 * remarkListLogicを設定します。
	 * @param remarkListLogic remarkListLogic
	 */
	public void setRemarkListLogic(final RemarkListLogic remarkListLogic) {
		this.remarkListLogic = remarkListLogic;
	}
}
