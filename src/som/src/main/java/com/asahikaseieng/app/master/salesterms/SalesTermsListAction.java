/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salesterms;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.salestermslist.SalesTermsList;
import com.asahikaseieng.dao.nonentity.master.salestermslist.SalesTermsListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.salestermslistforreport.SalesTermsListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 販売条件一覧 Actionクラス.
 * @author t0011036
 */
public final class SalesTermsListAction extends AbstractSearchAction {

	private SalesTermsListLogic salesTermsListLogic;

	private SalesTermsListExcelDecorator salesTermsListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public SalesTermsListAction() {
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

		SalesTermsListForm frm = (SalesTermsListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<SalesTermsList>());

		/* 検索条件を取得 */
		SalesTermsListPagerCondition condition = (SalesTermsListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhDeliveryCd(frm.getSrhDeliveryCd());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());

		/* 帳票(Excel)用に検索条件を保持 */
		SalesTermsListConditionForReport reportCondition = new SalesTermsListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(salesTermsListLogic.getList(condition));

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

		SalesTermsListForm frm = (SalesTermsListForm) form;

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

		SalesTermsListForm frm = (SalesTermsListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = salesTermsListExcelDecorator.createReport(frm
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
	 * salesTermsListExcelDecoratorを設定します。
	 * @param salesTermsListExcelDecorator salesTermsListExcelDecorator
	 */
	public void setSalesTermsListExcelDecorator(
			final SalesTermsListExcelDecorator salesTermsListExcelDecorator) {
		this.salesTermsListExcelDecorator = salesTermsListExcelDecorator;
	}

	/**
	 * salesTermsListLogicを設定します。
	 * @param salesTermsListLogic salesTermsListLogic
	 */
	public void setSalesTermsListLogic(
			final SalesTermsListLogic salesTermsListLogic) {
		this.salesTermsListLogic = salesTermsListLogic;
	}
}
