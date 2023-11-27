/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.organization;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.organizationlist.OrganizationList;
import com.asahikaseieng.dao.nonentity.master.organizationlist.OrganizationListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.organizationlistforreport.OrganizationListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 部署一覧 Actionクラス.
 * @author t0011036
 */
public final class OrganizationListAction extends AbstractSearchAction {

	private OrganizationListLogic organizationListLogic;

	private OrganizationListExcelDecorator organizationListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public OrganizationListAction() {
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

		OrganizationListForm frm = (OrganizationListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<OrganizationList>());

		/* 検索条件を取得 */
		OrganizationListPagerCondition condition = (OrganizationListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());

		/* 帳票(Excel)用に検索条件を保持 */
		OrganizationListConditionForReport reportCondition = new OrganizationListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(organizationListLogic.getList(condition));

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

		OrganizationListForm frm = (OrganizationListForm) form;

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

		OrganizationListForm frm = (OrganizationListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = organizationListExcelDecorator.createReport(frm
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
	 * organizationListExcelDecoratorを設定します。
	 * @param organizationListExcelDecorator organizationListExcelDecorator
	 */
	public void setOrganizationListExcelDecorator(
			final OrganizationListExcelDecorator organizationListExcelDecorator) {
		this.organizationListExcelDecorator = organizationListExcelDecorator;
	}

	/**
	 * organizationListLogicを設定します。
	 * @param organizationListLogic organizationListLogic
	 */
	public void setOrganizationListLogic(
			final OrganizationListLogic organizationListLogic) {
		this.organizationListLogic = organizationListLogic;
	}
}
