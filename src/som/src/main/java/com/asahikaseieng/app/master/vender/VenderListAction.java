/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.vender;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.venderlist.VenderList;
import com.asahikaseieng.dao.nonentity.master.venderlist.VenderListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.venderlistforreport.VenderListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 取引先一覧 Actionクラス.
 * @author t0011036
 */
public final class VenderListAction extends AbstractSearchAction {

	private VenderListLogic venderListLogic;

	private VenderListExcelDecorator venderListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public VenderListAction() {
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

		VenderListForm frm = (VenderListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<VenderList>());

		/* 検索条件を取得 */
		VenderListPagerCondition condition = (VenderListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhVenderDivision(frm.getSrhVenderDivision());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());

		/* 帳票(Excel)用に検索条件を保持 */
		VenderListConditionForReport reportCondition = new VenderListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(venderListLogic.getList(condition));

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

		VenderListForm frm = (VenderListForm) form;

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

		VenderListForm frm = (VenderListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = venderListExcelDecorator.createReport(frm
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
	 * venderListExcelDecoratorを設定します。
	 * @param venderListExcelDecorator venderListExcelDecorator
	 */
	public void setVenderListExcelDecorator(
			final VenderListExcelDecorator venderListExcelDecorator) {
		this.venderListExcelDecorator = venderListExcelDecorator;
	}

	/**
	 * venderListLogicを設定します。
	 * @param venderListLogic venderListLogic
	 */
	public void setVenderListLogic(final VenderListLogic venderListLogic) {
		this.venderListLogic = venderListLogic;
	}
}
